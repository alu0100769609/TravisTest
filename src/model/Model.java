/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 12 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package model;

import java.awt.Point;
import java.util.ArrayList;

public class Model {
    private static final Integer DEFAULT_DENSITY = 4;                           // Default density
    private static final Integer DEFAULT_WIDTH = 500;                           // Default width
    private static final Integer DEFAULT_HEIGHT = 500;                          // Default height

    // ATTRIBUTES
    private Integer density;                                                    // Density
    private Integer width;                                                      // Width
    private Integer height;                                                     // Height
    private ArrayList<ArrayList<Pair<Point, Boolean>>> matrixOfPoints;          // Matrix of points with associated boolean (enabled)
    private Integer numRowsAndCols;                                             // Number of rows and columns (square matrix)
    private Integer boxSize;                                                    // Size of each cell
    
    // CONSTRUCTORS
    public Model() {
        density = DEFAULT_DENSITY;                                              // Set default density
        width = DEFAULT_WIDTH;                                                  // Set default width
        height = DEFAULT_HEIGHT;                                                // Set default height
        matrixOfPoints = new ArrayList<ArrayList<Pair<Point, Boolean>>>();      // Create the matrix of points
        
        numRowsAndCols = sqrt(density);                                         // Calculate num rows and cols from density
        boxSize = width / numRowsAndCols;
        calculateMatrix();                                                      // Calculate the matrix of points
    }
    
    // METHODS
    private void calculateMatrix() {
        System.out.println("Rows: " + numRowsAndCols + " BoxSize: " + boxSize); // Show number of cells by row and its size
        while (!getMatrixOfPoints().isEmpty())                                  // If the arrayList is not empty
            getMatrixOfPoints().remove(0);                                      // Delete everything before initializing again
        
        Integer xAxis = 0;                                                      // Contains x position inside of the board
        Integer yAxis = 0;                                                      // Contains y position inside of the board
        for (int i = 0; i <= getNumRowsAndCols(); i++) {                        // Loop for every row
            ArrayList<Pair<Point, Boolean>> aux = new ArrayList<Pair<Point, Boolean>>();                      // Create a new arrayList of points (row)
            for (int j = 0; j <= getNumRowsAndCols(); j++) {                    // Loop for every column
                aux.add(new Pair<Point, Boolean>(new Point(xAxis, yAxis), true));                               // Add a new point to the row arrayList
                xAxis += getBoxSize();                                          // Modify the x position inside the board
            }
            getMatrixOfPoints().add(aux);                                       // Add the "row" arrayList to the matrix
            xAxis = 0;                                                          // Reset xAxis to zero (next col starts at 0)
            yAxis += getBoxSize();                                              // Modify yAxis to get the next row position
        }
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
        numRowsAndCols = sqrt(density);
        boxSize = width / numRowsAndCols;
        calculateMatrix();
    }

    /**
     * @return width
     */
    public Integer getWidth() {                                                 // Get width
        return width;
    }

    /**
     * @param width
     */
    public void setWidth(Integer width) {                                       // Set width
        this.width = width;
    }
    
    /**
     * @return height
     */
    public Integer getHeight() {                                                // Get height
        return height;
    }
    
    /**
     * @param height
     */
    public void setHeight(Integer height) {                                     // Set height
        this.height = height;
    }
    
    /**
     * @return matrixOfPoints
     */
    public ArrayList<ArrayList<Pair<Point, Boolean>>> getMatrixOfPoints() {                    // Get matrix of points
        return matrixOfPoints;
    }
    
    /**
     * @param matrixOfPoints
     */
    public void setMatrixOfPoints(ArrayList<ArrayList<Pair<Point, Boolean>>> matrixOfPoints) { // Set matrix of points
        this.matrixOfPoints = matrixOfPoints;
    }

    /**
     * @return numRowsAndCols
     */
    public Integer getNumRowsAndCols() {                                        // Get number of rows and cols
        return numRowsAndCols;
    }

    /**
     * @param numRowsAndCols
     */
    public void setNumRowsAndCols(Integer numRowsAndCols) {                     // Set number of rows and cols
        this.numRowsAndCols = numRowsAndCols;
    }

    /**
     * @return boxSize
     */
    public Integer getBoxSize() {                                               // Get the size of a cell
        return boxSize;
    }

    /**
     * @param boxSize
     */
    public void setBoxSize(Integer boxSize) {                                   // Set the size of a cell
        this.boxSize = boxSize;
    }
}
