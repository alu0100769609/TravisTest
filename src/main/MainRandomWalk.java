/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 12 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package main;

import java.awt.Color;

import controller.Controller;
import model.Model;
import view.View;

public class MainRandomWalk {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Model myModel = new Model();                                            // Create the model
        View myView = new View(myModel);                                        // Create the view
        Controller myController = new Controller(myView, myModel);              // Create the controller and pass view and model to it
        
        // Visual test for our left and right panel
        myView.getLeftPanel().setBackground(Color.BLUE);                        // Set blue to left panel
        myView.getRightPanel().setBackground(Color.LIGHT_GRAY);                 // Set light gray to right panel

        myController.run();                                                     // Run the program
    }
}
