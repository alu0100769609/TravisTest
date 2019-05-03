package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JPanel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import model.Model;
import view.Board;

class Test {
    
    @org.junit.jupiter.api.Test
    @Disabled
    @Deprecated
    @DisplayName("Board exist")
    void testBoard() {
        Board myBoard = new Board();
    }
    
    @DisplayName("Board has model")
    void testModel() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals(myModel, myBoard.getModel());
    }
    
    @DisplayName("Board extends from JPanel")
    void test() {
        assertTrue(JPanel.class.isAssignableFrom(Board.class));
    }
    
    @Disabled
    @Deprecated
    @DisplayName("Board has default density")
    void testDensity() {
        Board myBoard = new Board();
        assertEquals(4, (int) myBoard.getDensity());
    }
    
    @Disabled
    @Deprecated
    @DisplayName("Board initialized with density")
    void testDensityConstructor() {
        Board myBoard = new Board(50);
        assertEquals(50, (int) myBoard.getDensity());
    }
    
    @DisplayName("Board has default density")
    void testNewDensity() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals(4, (int) myBoard.getDensity());
    }
    
    @DisplayName("Board initialized with density")
    void testNewDensityConstructor() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel, 50);
        assertEquals(50, (int) myBoard.getDensity());
    }
    
}
