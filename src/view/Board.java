/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 14 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import model.Model;

public class Board extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final Integer DEFAULT_DENSITY = 4;                           // Default density
    private static final Color DEFAULT_COLOR = Color.RED;                       // Default color for path
    private static final Integer TIME_SLEEPING = 300;                          // Sleep for 1 second
    private static final Integer STROKE = 3;                                    // Stroke of the path line
    private static Boolean counter[] = {false, false, false, false};            // Counter for path (north, south, east, west)

    private enum Movement { 
        NORTH,
        SOUTH,
        EAST,
        WEST;
        public static Movement getRandomMove() {                                // Create random method to get a movement value
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }


    // ATTRIBUTES
    private Integer density;                                                    // Density of our board
    private Model model;                                                        // The model
    private ArrayList<Point> path;                                              // ArrayList containing the path we will paint later
    private Integer row;                                                        // Row tracker, path will use it
    private Integer column;                                                     // Column tracker, path will use it
    private Thread thread;                                                      // Thread for paint the path
    private Color pathColor;
    private Info info;
    
    private volatile Boolean stopThread;                                        // Stop the thread
    /* It is important to mark this variable volatile, otherwise, it's
     * possible for the running thread to cache its value and never check
     * back to main memory for updated value and running infinitely.
     */
    
    // CONSTRUCTORS
    /**
     * Default constructor, initialize density to 4
     */
    public Board(Model model) {                                                 // Default constructor
        this(model, DEFAULT_DENSITY);
    }
    
    /**
     * @param density
     * Constructor with params
     */
    public Board(Model model, Integer density) {
        setPathColor(DEFAULT_COLOR);
        setModel(model);                                                        // Set the model
        setDensity(density);                                                    // Set given density
        getModel().setDensity(density);                                         // Give the density to the model
        getModel().setWidth(getWidth());                                        // Give the width to the model
        getModel().setHeight(getHeight());                                      // Give the height to the model
        
        setPath(new ArrayList<Point>());                                        // Create the arrayList of points
        setRow(getModel().getNumRowsAndCols() / 2);                             // Look for center pos in row
        setColumn(getModel().getNumRowsAndCols() / 2);                          // Look for center pos in column
        setInfo(new Info());

    }
    
    // METHODS
    public void changePathColor() {
        double R = Math.random() * 255;
        double G = Math.random() * 255;
        double B = Math.random() * 255;
        
        setPathColor(new Color((int)R, (int)G, (int)B));                        // Create new random color for path
        repaint();
    }
    /**
     * This method ends the execution of the path painting thread
     */
    public void finish() {
        stopThread = true;                                                      // Stop the thread that paint the path
    }
    
    /**
     * This method start new thread and paints the path
     */
    public void start() {
        stopThread = false;                                                     // We don't want to stop this thread
        // First of all if path has something, delete it
        while (!getPath().isEmpty())
            getPath().remove(0);
        
        setRow(getModel().getNumRowsAndCols() / 2);                             // Look for center pos in row
        setColumn(getModel().getNumRowsAndCols() / 2);                          // Look for center pos in column
        getPath().add(getModel().getMatrixOfPoints().get(getColumn()).get(getRow()).getPoint()); // Add first point to the path
        getModel().getMatrixOfPoints().get(getColumn()).get(getRow()).setEnabled(false); // Disable it
        
        thread = new Thread() {                                                 // Anonymous class
            public void run() {
                try {
                    do {
                        if (stopThread)                                         // Exit while if we want to end execution
                            break;
                        nextStep();                                             // Paint the next step
                    } while (true);
                } 
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

          thread.start();                                                       // Start the thread
    }

    /**
     * This method generate random moves and add them to
     * the path
     * @throws InterruptedException 
     */
    public void nextStep() throws InterruptedException {
        Point point = new Point();                                              // Create a new point
        Movement move = Movement.getRandomMove();
        Integer rowAux = getRow();
        Integer colAux = getColumn();
        
        switch (move) {                                                         // Get new random move
            case NORTH:                                                         // If move == north
                setRow(getRow() - 1);                                           // Sub one to the row
                break;
            case SOUTH:                                                         // If move == south
                setRow(getRow() + 1);                                           // Add one to the row
                break;
            case EAST:                                                          // If move == east
                setColumn(getColumn() + 1);                                     // Add one to the column
                break;
            case WEST:                                                          // If move == west
                setColumn(getColumn() - 1);                                     // Sub one to the column
                break;
        }
        
        if (getModel().getMatrixOfPoints().get(getColumn()).get(getRow()).getEnabled() != false) {  // If the point is not in use
            // Get the point stored in matrixOfPoints(row, column)
            point = getModel().getMatrixOfPoints().get(getColumn()).get(getRow()).getPoint();
            // Add it to the path
            getPath().add(point);
            // And disable it
            getModel().getMatrixOfPoints().get(getColumn()).get(getRow()).setEnabled(false);
    
            repaint();                                                          // Repaint to show the new path
    
            if (getRow() == 0 || getRow() == getModel().getNumRowsAndCols() ||  // If we reach any border
                getColumn() == 0 || getColumn() == getModel().getNumRowsAndCols()) {
                System.out.println("SUCCESS");                                  // Print success
                info.getLblText().setForeground(Color.GREEN);
                info.getLblText().setText("SUCCESS");
                
                finish();                                                       // End thread execution
            }
            for (int i = 0; i < counter.length; i++) {
                counter[i] = false;                                             // We moved, reset the counter to false
            }

            Thread.sleep(TIME_SLEEPING);                                        // Wait TIME_SLEEPING miliseconds
        }
        
        else {                                                                  // Else the point is being used, find a new way
            switch (move) {
                case NORTH: counter[0] = true; break;
                case SOUTH: counter[1] = true; break;
                case EAST: counter[2] = true; break;
                case WEST: counter[3] = true; break;
            }
            setColumn(colAux);                                                  // Revert the column change
            setRow(rowAux);                                                     // Revert the row change

            if (counter[0] == true)
                if (counter[1] == true)
                    if (counter[2] == true)
                        if (counter[3] == true) {
                System.out.println("FAILURE");                                  // We failed, not enough movements
                info.getLblText().setForeground(Color.RED);
                info.getLblText().setText("FAILURE");
                finish();
            }
        }
    }
    
    /**
     * @param g2
     * Paint the path
     */
    private void paintPath(Graphics2D g2) {
        g2.setColor(getPathColor());
        g2.setStroke(new BasicStroke(STROKE));                                  // Modify the stroke of the line

        for (int i = 1; i < getPath().size(); i++) {                            // Draw every line in path
            g2.drawLine(getPath().get(i - 1).x, getPath().get(i - 1).y,
                        getPath().get(i).x, getPath().get(i).y);
        }
    }

    /**
     * @param g2
     * Paint the initial grid (same rows and columns)
     */
    private void paintGrid(Graphics2D g2) {
        Integer numberOfCellsByRow = sqrt(getDensity());                        // Get the number of cells by row (and column)
        Integer gridSize = getWidth() / numberOfCellsByRow;                     // The size of the grid
        Integer linePosition = 0;                                               // Tracker for our lines
        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= numberOfCellsByRow; i++) {                         // Loop for all row cells
            g2.drawLine(0, linePosition, getWidth(), linePosition);             // Draw horizontal line
            g2.drawLine(linePosition, 0, linePosition, getHeight());            // Draw vertical line
            linePosition += gridSize;                                           // Step 1 cell size
        }
    }
    
    /**
     * paintComponent, call paintGrid and paintPath
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        paintGrid(g2);                                                          // Paint grid
        paintPath(g2);                                                          // Paint path
    }

    /**
     * @param density
     * @return sqrt
     * Return the square root of the given density, that is,
     * the number of cols and rows for our grid.
     */
    private Integer sqrt(Integer density) {
        double sqrt = Math.round(Math.sqrt(density));                           // Round the value of the square root of the density
        return (int) sqrt;
    }
    
    
    // GETTERS AND SETTERS
    /**
     * @return density
     */
    public Integer getDensity() {                                               // Get density
        return density;
    }

    /**
     * @param density
     */
    public void setDensity(Integer density) {                                   // Set density
        this.density = density;
    }

    /**
     * @return model
     */
    public Model getModel() {                                                   // Get model
        return model;
    }

    /**
     * @param model
     */
    public void setModel(Model model) {                                         // Set model
        this.model = model;
    }

    /**
     * @return path
     */
    public ArrayList<Point> getPath() {                                         // Get path
        return path;
    }

    /**
     * @param path
     */
    public void setPath(ArrayList<Point> path) {                                // Set path
        this.path = path;
    }

    /**
     * @return row
     */
    public Integer getRow() {                                                   // Get row
        return row;
    }

    /**
     * @param row
     */
    public void setRow(Integer row) {                                           // Set row
        this.row = row;
    }

    /**
     * @return column
     */
    public Integer getColumn() {                                                // Get column
        return column;
    }

    /**
     * @param column
     */
    public void setColumn(Integer column) {                                     // Set column
        this.column = column;
    }

    public Color getPathColor() {
        return pathColor;
    }

    public void setPathColor(Color pathColor) {
        this.pathColor = pathColor;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
