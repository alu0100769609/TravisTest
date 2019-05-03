package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Model;
import view.Board;

class ModelTest {
    
    @Test
    @DisplayName("The model exist")
    void testModel() {
        Model myModel = new Model();
    }
    
    @Test
    @DisplayName("The model has default density")
    void testDefaultDensity() {
        Model myModel = new Model();
        assertEquals((Integer) 4, myModel.getDensity());
    }

    @Test
    @DisplayName("The model has default size")
    void testDefaultSize() {
        Model myModel = new Model();
        assertEquals((Integer) 500, myModel.getWidth());
        assertEquals((Integer) 500, myModel.getHeight());
    }
    
    @Test
    @DisplayName("The model has matrix of points")
    void testMatrixOfPoints() {
        Model myModel = new Model();
        assertNotNull(myModel.getMatrixOfPoints());
    }
    
    @Test
    @Disabled
    @Deprecated
    @DisplayName("When default, Matrix has values")
    void testMatrixValues() {
        Model myModel = new Model();
        assertEquals(0, myModel.getMatrixOfPoints().get(0).get(0).x);
        assertEquals(0, myModel.getMatrixOfPoints().get(0).get(0).y);
        assertEquals(50, myModel.getMatrixOfPoints().get(0).get(1).x);
        assertEquals(0, myModel.getMatrixOfPoints().get(0).get(1).y);
        assertEquals(100, myModel.getMatrixOfPoints().get(0).get(2).x);
        assertEquals(0, myModel.getMatrixOfPoints().get(0).get(2).y);

        assertEquals(0, myModel.getMatrixOfPoints().get(1).get(0).x);
        assertEquals(50, myModel.getMatrixOfPoints().get(1).get(0).y);
        assertEquals(50, myModel.getMatrixOfPoints().get(1).get(1).x);
        assertEquals(50, myModel.getMatrixOfPoints().get(1).get(1).y);
        assertEquals(100, myModel.getMatrixOfPoints().get(1).get(2).x);
        assertEquals(50, myModel.getMatrixOfPoints().get(1).get(2).y);

        assertEquals(0, myModel.getMatrixOfPoints().get(2).get(0).x);
        assertEquals(100, myModel.getMatrixOfPoints().get(2).get(0).y);
        assertEquals(50, myModel.getMatrixOfPoints().get(2).get(1).x);
        assertEquals(100, myModel.getMatrixOfPoints().get(2).get(1).y);
        assertEquals(100, myModel.getMatrixOfPoints().get(2).get(2).x);
        assertEquals(100, myModel.getMatrixOfPoints().get(2).get(2).y);
    }
    
    @Test
    @DisplayName("Model has size of the board")
    void testSize() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals((Integer) myBoard.getWidth(), myModel.getWidth());
        assertEquals((Integer) myBoard.getHeight(), myModel.getHeight());
    }
    
    @Test
    @DisplayName("Model has density of the board")
    void testDensity() {
        Model myModel = new Model();
        Board myBoard = new Board(myModel);
        assertEquals(myBoard.getDensity(), myModel.getDensity());
    }
    
}
