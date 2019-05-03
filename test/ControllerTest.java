package test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.Controller;
import model.Model;
import view.View;

class ControllerTest {
    
    @Test
    @Disabled
    @Deprecated
    @DisplayName("The controller exist")
    void testController() {
        Controller myController = new Controller();
    }
    
    @Test
    @DisplayName("Constructor with params")
    void testControllerConstructor() {
        Model myModel = new Model();
        View myView = new View(myModel);

        Controller myController = new Controller(myView, myModel);
    }
    
    @Test
    @DisplayName("Controller has Model")
    void testHasModel() {
        Model myModel = new Model();
        View myView = new View(myModel);
        Controller myController = new Controller(myView, myModel);

        assertNotNull(myController.getModel());
    }
    
    @Test
    @DisplayName("Controller has View")
    void testHasView() {
        Model myModel = new Model();
        View myView = new View(myModel);
        Controller myController = new Controller(myView, myModel);

        assertNotNull(myController.getView());
    }
    
    @Test
    @DisplayName("Controller has run method")
    void testHasRun() {
        Model myModel = new Model();
        View myView = new View(myModel);
        Controller myController = new Controller(myView, myModel);
        
        myController.run();
    }
}
