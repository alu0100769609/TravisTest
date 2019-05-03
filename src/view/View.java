/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 12 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.Model;

public class View extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private static final Integer WIDTH = 700;                                   // Width of the window
    private static final Integer HEIGHT = 500;                                  // Height of the window
    private static final Integer DIVIDER = 500;                                 // SplitPane Divider position

    // ATTRIBUTES
    private JSplitPane splitPane;                                               // SplitPane to divide the window
    private JPanel leftPanel;                                                   // Left panel of the splitPane
    private JPanel rightPanel;                                                  // Right panel of the splitPane
    private Controls controls;                                                  // Controls
    private Board board;
    private Model model;
    private Info information;
    
    // CONSTRUCTORS
    /**
     * Construct the view
     */
    public View(Model model) {
        // Initialize containers
        splitPane = new JSplitPane();                                           // Create the splitPane
        leftPanel = new JPanel();                                               // Create the leftPanel
        rightPanel = new JPanel();                                              // Create the rightPanel
        controls = new Controls();                                              // Create the controls
        this.model = model;
        board = new Board(model);
        information = new Info();
        
        // Define our window
        setTitle("Default title");                                              // Default title, must be replaced by Controller
        setPreferredSize(new Dimension(WIDTH, HEIGHT));                         // Open the window with a default size of 700x500
        setResizable(false);                                                    // Do not let user resize the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                         // Set close operation for close button
        
        // Configure the SplitPane
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);                  // Set split orientation
        splitPane.setDividerLocation(DIVIDER);                                  // The initial position of the divider
        splitPane.setEnabled(false);                                            // Disable the splitPane (make it no resizable)
        splitPane.setDividerSize(0);                                            // "Hide" the division bar

        splitPane.setLeftComponent(leftPanel);                                  // Add leftPanel to the left of the SplitPane
        splitPane.setRightComponent(rightPanel);                                // Add rightPanel to the right of the SplitPane

        add(splitPane);                                                         // Add the splitPane to the window
        
        // Configure the rightPanel
        rightPanel.setLayout(new GridLayout(3,1));
        rightPanel.add(controls);                                               // Add controls to the right panel
        rightPanel.add(new JPanel());
        rightPanel.add(information);

        // Configure the leftPanel
        leftPanel.setLayout(new GridLayout());
        leftPanel.add(board, BorderLayout.CENTER);
        
        pack();                                                                 // Apply every defined size before becoming visible
    }

    // GETTERS AND SETTERS
    /**
     * @return splitPane
     */
    public JSplitPane getSplitPane() {                                          // Get splitPane
        return splitPane;
    }

    /**
     * @param splitPane
     */
    public void setSplitPane(JSplitPane splitPane) {                            // Set splitPane
        this.splitPane = splitPane;
    }

    /**
     * @return leftPanel
     */
    public JPanel getLeftPanel() {                                              // Get leftPanel
        return leftPanel;
    }

    /**
     * @param leftPanel
     */
    public void setLeftPanel(JPanel leftPanel) {                                // Set leftPanel
        this.leftPanel = leftPanel;
    }

    /**
     * @return rightPanel
     */
    public JPanel getRightPanel() {                                             // Get rightPanel
        return rightPanel;
    }

    /**
     * @param rightPanel
     */
    public void setRightPanel(JPanel rightPanel) {                              // Set rightPanel
        this.rightPanel = rightPanel;
    }

    /**
     * @return controls
     */
    public Controls getControls() {                                             // Get controls
        return controls;
    }

    /**
     * @param controls
     */
    public void setControls(Controls controls) {                                // Set controls
        this.controls = controls;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Info getInformation() {
        return information;
    }

    public void setInformation(Info information) {
        this.information = information;
    }
}
