/**
 * @author adexe
 * Adexe Sabina Pérez: alu0100769609@ull.edu.es
 * Date: 14 abr. 2019
 * Subject: PAI
 * Version: 1.0
 * Comments:
 *
 */

package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JPanel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Model;
import view.Board;

class BoardTest {
    
    @Test
    @Disabled
    @Deprecated
    @DisplayName("Board exist")
    void testBoard() {
        Board myBoard = new Board();
    }
    
    @Test
    @DisplayName("Board has model")
    void testModel() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals(myModel, myBoard.getModel());
    }
    
    @Test
    @DisplayName("Board extends from JPanel")
    void test() {
        assertTrue(JPanel.class.isAssignableFrom(Board.class));
    }
    
    @Test
    @Disabled
    @Deprecated
    @DisplayName("Board has default density")
    void testDensity() {
        Board myBoard = new Board();
        assertEquals(4, (int) myBoard.getDensity());
    }
    
    @Test
    @Disabled
    @Deprecated
    @DisplayName("Board initialized with density")
    void testDensityConstructor() {
        Board myBoard = new Board(50);
        assertEquals(50, (int) myBoard.getDensity());
    }
    
    @Test
    @DisplayName("Board has default density")
    void testNewDensity() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals(4, (int) myBoard.getDensity());
    }
    
    @Test
    @DisplayName("Board initialized with density")
    void testNewDensityConstructor() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel, 50);
        assertEquals(50, (int) myBoard.getDensity());
    }
}
