/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 12 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.View;

public class Controller {
    // ATTRIBUTES
    private Model model;                                                        // Model
    private View view;                                                          // View
    
    // CONSTRUCTORS
    /**
     * @param view
     * @param model
     * Assign view and model to the controller
     */
    public Controller(View view, Model model) {
        setView(view);                                                          // Set view
        setModel(model);                                                        // Set model
        getView().getControls().changeListener(new DensityListener());          // Add listener to the change button
        getView().getControls().StartListener(new StartListener());             // Add listener to the start button
        getView().getControls().EndListener(new EndListener());                 // Add listener to the end button
        getView().getControls().ColorListener(new ColorListener());             // Add listener to the end button
        getView().getBoard().setInfo(getView().getInformation());
    }

    // METHODS
    /**
     * Run the program
     */
    public void run() {
        getView().setTitle("Random walk");                                      // Set the title of the view
        getView().setLocationRelativeTo(null);                                  // Makes the window appear centered to the screen
        
        getView().setVisible(true);                                             // Set the window visible
    }
    
    // GETTERS AND SETTERS
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
     * @return view
     */
    public View getView() {                                                     // Get view
        return view;
    }

    /**
     * @param view
     */
    public void setView(View view) {                                            // Set view
        this.view = view;
    }
    
    class DensityListener implements ActionListener {                           // Density listener
        public void actionPerformed(ActionEvent e) {
            // Get the new density from the text field
            Integer newDensity = Integer.parseInt(getView().getControls().getDensityTextField().getText());

            // And add it to the board
            getView().getBoard().setDensity(newDensity);
            
            // Get the board's size and give it to the model
            getModel().setWidth(getView().getBoard().getWidth());
            getModel().setHeight(getView().getBoard().getHeight());
            
            // Finally give the new density to the model
            getModel().setDensity(newDensity);
            
            getView().repaint();
        }
    }
    
    class StartListener implements ActionListener {                             // Start listener
        public void actionPerformed(ActionEvent e) {
            getView().getBoard().start();                                       // Start running and printing the path
        }
    }
    
    class EndListener implements ActionListener {                               // End listener
        public void actionPerformed(ActionEvent e) {
            getView().getBoard().finish();                                      // Finish the execution and printing of the path
        }
    }

    class ColorListener implements ActionListener {                             // Color listener
        public void actionPerformed(ActionEvent e) {
            getView().getBoard().changePathColor();                             // Change the color of the path
        }
    }
}
