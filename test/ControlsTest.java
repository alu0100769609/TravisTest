/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 14 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JPanel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import view.Controls;
import view.View;

class ControlsTest {
    
    @Test
    @DisplayName("Controls extends from JPanel")
    void test() {
        assertTrue(JPanel.class.isAssignableFrom(Controls.class));
    }

    @Test
    @DisplayName("\"Start\" button exist")
    void testStartButton() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getStartBtn());
        assertEquals("Start", myControls.getStartBtn().getText());
    }

    @Test
    @DisplayName("\"End\" button exist")
    void testEndButton() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getEndBtn());
        assertEquals("End", myControls.getEndBtn().getText());
    }

    @Test
    @DisplayName("\"Change color\" button exist")
    void testColorButton() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getColorBtn());
        assertEquals("Change color", myControls.getColorBtn().getText());
    }

    @Test
    @DisplayName("\"Density\" label exist")
    void testInputTextField() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getDensityLabel());
    }

    @Test
    @DisplayName("\"Density\" Text field exist")
    void testDensityTextField() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getDensityTextField());
    }

    @Test
    @DisplayName("\"Change\" button exist")
    void testCreateButton() {
        Controls myControls = new Controls();
        assertNotNull(myControls.getChangeBtn());
        assertEquals("Change", myControls.getChangeBtn().getText());
    }

}
