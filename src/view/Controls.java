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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Controls extends JPanel{
    private static final long serialVersionUID = 1L;

    private static final Integer TF_COLUMNS = 2;                                // TextField columns
    private static final Integer HGAP = 5;                                      // Horizontal gap for border layout
    private static final Integer VGAP = 5;                                      // Vertical gap for border layout
    private static final Integer ROWS = 3;                                      // Rows for buttonPanel
    private static final Integer COLS = 1;                                      // Cols for buttonPanel
    
    // ATTRIBUTES
    private JButton startBtn;                                                   // Button start, to start simulation
    private JButton endBtn;                                                     // Button end, to end simulation
    private JButton colorBtn;                                                   // Button color, to change line color
    private JButton changeBtn;                                                  // Button change, to change grid size
    private JLabel densityLabel;                                                // Label with "density" text
    private JTextField densityTextField;                                        // Text field for density input
    
    private JPanel inputPanel;                                                  // Panel north located, contains what density needs
    private JPanel buttonPanel;                                                 // Panel center located, contains buttons

    // CONSTRUCTORS
    /**
     * Construct the control panel
     */
    public Controls() {
        // Define attributes
        startBtn = new JButton("Start");                                        // Create button with "Start" text
        endBtn = new JButton("End");                                            // Create button with "End" text
        colorBtn = new JButton("Change color");                                 // Create button with "Change color" text
        changeBtn = new JButton("Change");                                      // Create button with "Change" text
        densityLabel = new JLabel("Density: ");                                 // Create label with "Density:" text
        densityTextField = new JTextField(TF_COLUMNS);                          // Create textfield with width of TF_COLUMNS
        
        inputPanel = new JPanel();                                              // Create panel to contain density input
        buttonPanel = new JPanel();                                             // Create panel to contain buttons
        
        // Configure our control panel
        setLayout(new BorderLayout(HGAP, VGAP));                                // Set border layout with HGAP and VGAP
        setBackground(new Color(0,0,0,0));                                      // Set background color to transparent (4th param)
        add(inputPanel, BorderLayout.NORTH);                                    // Insert inputPanel at north
        add(buttonPanel, BorderLayout.CENTER);                                  // Insert buttonPanel at center
        
        // Configure InputPanel
        inputPanel.setLayout(new FlowLayout());                                 // Set flowLayout to inputPanel
        inputPanel.setBackground(new Color(0,0,0,0));                           // Set background color to transparent (4th param)
        inputPanel.add(densityLabel);                                           // Add density label as 1st element (left)
        inputPanel.add(densityTextField);                                       // Add density textField as 2nd element (center)
        inputPanel.add(changeBtn);                                              // Add change button as 3rd element (right)
        
        // Configure ButtonPanel
        buttonPanel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));          // Set gridLayout with 3 rows and 1 column
        buttonPanel.setBackground(new Color(0,0,0,0));                          // Set background color to transparent (4th param)
        buttonPanel.add(startBtn);                                              // Add start button at the top
        buttonPanel.add(endBtn);                                                // Add end button at the middle
        buttonPanel.add(colorBtn);                                              // Add color button at the bottom
    }
    
    // METHODS
    public void changeListener(ActionListener listenForChangeButton) {          // Whenever the button is clicked, the controller
        changeBtn.addActionListener(listenForChangeButton);                     // will be alerted of that fact
    }

    public void StartListener(ActionListener listenForStartButton) {            // Whenever the button is clicked, the controller
        startBtn.addActionListener(listenForStartButton);                       // will be alerted of that fact
    }
    
    public void EndListener(ActionListener listenForEndButton) {                // Whenever the button is clicked, the controller
        endBtn.addActionListener(listenForEndButton);                           // will be alerted of that fact
    }

    public void ColorListener(ActionListener listenForColorButton) {            // Whenever the button is clicked, the controller
        colorBtn.addActionListener(listenForColorButton);                         // will be alerted of that fact
    }

    // GETTERS AND SETTERS
    /**
     * @return startBtn
     */
    public JButton getStartBtn() {                                              // Get start button
        return startBtn;
    }

    /**
     * @param startBtn
     */
    public void setStartBtn(JButton startBtn) {                                 // Set start button
        this.startBtn = startBtn;
    }

    /**
     * @return endBtn
     */
    public JButton getEndBtn() {                                                // Get end button
        return endBtn;
    }

    /**
     * @param endBtn
     */
    public void setEndBtn(JButton endBtn) {                                     // Set end button
        this.endBtn = endBtn;
    }

    /**
     * @return colorBtn
     */
    public JButton getColorBtn() {                                              // Get color button
        return colorBtn;
    }

    /**
     * @param colorBtn
     */
    public void setColorBtn(JButton colorBtn) {                                 // Set color button
        this.colorBtn = colorBtn;
    }

    /**
     * @return changeBtn
     */
    public JButton getChangeBtn() {                                             // Get change button
        return changeBtn;
    }

    /**
     * @param changeBtn
     */
    public void setChangeBtn(JButton changeBtn) {                               // Set change button
        this.changeBtn = changeBtn;
    }

    /**
     * @return densityLabel
     */
    public JLabel getDensityLabel() {                                           // Get density label
        return densityLabel;
    }

    /**
     * @param densityLabel
     */
    public void setDensityLabel(JLabel densityLabel) {                          // Set density label
        this.densityLabel = densityLabel;
    }

    /**
     * @return densityTextField
     */
    public JTextField getDensityTextField() {                                   // Get density text field
        return densityTextField;
    }

    /**
     * @param densityTextField
     */
    public void setDensityTextField(JTextField densityTextField) {              // Set density text field
        this.densityTextField = densityTextField;
    }
}
