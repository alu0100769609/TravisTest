/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 13 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Model;
import view.View;

class ViewTest {
    
    @Test
    @DisplayName("The view exist")
    void testView() {
        Model model = new Model();
        View myView = new View(model);
    }
    
    @Test
    @DisplayName("View extends from JFrame")
    void testExtension() {
        Model model = new Model();
        View myView = new View(model);
        assertTrue(JFrame.class.isAssignableFrom(View.class));
    }
    
    @Test
    @DisplayName("Window has default title")
    void testTitle() {
        Model model = new Model();
        View myView = new View(model);
        assertEquals("Default title", myView.getTitle());
    }
    
    @Test
    @DisplayName("Window has size")
    void testSize() {
        Model model = new Model();
        View myView = new View(model);
        assertNotNull(myView.getPreferredSize());
    }
    
    @Test
    @DisplayName("Window can be closed")
    void testCloseOperation() {
        Model model = new Model();
        View myView = new View(model);
        assertEquals(JFrame.EXIT_ON_CLOSE, myView.getDefaultCloseOperation());
    }
    
    @Test
    @DisplayName("Window has a SplitPane")
    void testSplitPane() {
        Model model = new Model();
        View myView = new View(model);
        assertNotNull(myView.getSplitPane());
    }

    @Test
    @DisplayName("SplitPane is horizontal split")
    void testSplitPane2() {
        Model model = new Model();
        View myView = new View(model);
        assertEquals(JSplitPane.HORIZONTAL_SPLIT, myView.getSplitPane().getOrientation());
    }

    @Test
    @DisplayName("SplitPane has Left panel")
    void testLeftPanel() {
        Model model = new Model();
        View myView = new View(model);
        assertEquals(myView.getLeftPanel(), myView.getSplitPane().getLeftComponent());
    }

    @Test
    @DisplayName("SplitPane has Right panel")
    void testRightPanel() {
        Model model = new Model();
        View myView = new View(model);
        assertEquals(myView.getRightPanel(), myView.getSplitPane().getRightComponent());
    }

    @Test
    @DisplayName("View has controls")
    void testControls() {
        Model model = new Model();
        View myView = new View(model);
        assertNotNull(myView.getControls());
    }
}
