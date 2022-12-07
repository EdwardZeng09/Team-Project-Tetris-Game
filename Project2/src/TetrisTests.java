import model.TetrisPiece;
import model.TetrisBoard;

import model.TetrisPoint;
import org.junit.jupiter.api.Test;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TetrisTests {

    //Piece tests
    @Test
    void testPiece() {

        TetrisPiece piece = new TetrisPiece("1 0  1 1  1 2  0 1");
        int [] output = piece.getLowestYVals();
        int [] target = {1,0};
        for (int i =0; i< output.length; i++) {
            assertEquals(output[i], target[i], "Error when testing lowest Y values");
        }

    }
    @Test
    void testequal() {
        TetrisPiece piece = new TetrisPiece("0 1 1 0 1 1 2 0");
        TetrisPiece np = new TetrisPiece("1 0 0 1 1 1 2 0");
        assertTrue(np.equals(piece), "Error when testing piece equality");
        }
    @Test
    void testMakeFastRotations() {
        TetrisPiece piece = new TetrisPiece(TetrisPiece.S2_STR);
        piece = TetrisPiece.makeFastRotations(piece);
        String[] target = {"0 0 0 1 1 1 1 2", "0 1 1 0 1 1 2 0", "0 0 0 1 1 1 1 2", "0 1 1 0 1 1 2 0"};
        int counter = 0;
        while(counter < 4){
            TetrisPiece np = new TetrisPiece(target[counter]);
            piece = piece.fastRotation();
            assertTrue(np.equals(piece), "Error when testing piece equality");
            counter++;
        }

    }

    @Test
    void testEquals() {

        TetrisPiece pieceA = new TetrisPiece("1 0  1 1  1 2  0 1");
        TetrisPiece pieceB = new TetrisPiece("1 0  1 1  1 2  0 1");
        assertTrue(pieceB.equals(pieceA), "Error when testing piece equality");
        assertTrue(pieceA.equals(pieceB), "Error when testing piece equality");

    }

    @Test
    void testheightofpice() {
        TetrisPiece pieceA = new TetrisPiece("1 0  1 1  1 2  0 1");
        TetrisBoard board = new TetrisBoard(10,24);
        HashMap<Integer, Integer> a = board.heightofpice(pieceA);
        HashMap<Integer, Integer> b = new HashMap<>();
        b.put(0, 1);
        b.put(1, 3);
        assertEquals(a,b);

    }

    @Test
    void testPlacePiece() {

        TetrisBoard board = new TetrisBoard(10,24);
        TetrisPiece pieceA = new TetrisPiece(TetrisPiece.SQUARE_STR);

        board.commit();
        int retval = board.placePiece(pieceA, 0,0);
        assertEquals(TetrisBoard.ADD_OK,retval);

        board.commit();
        retval = board.placePiece(pieceA, 10,10); //out of bounds
        assertEquals(TetrisBoard.ADD_OUT_BOUNDS,retval);

        board.commit();
        retval = board.placePiece(pieceA, 0,0);
        assertEquals(TetrisBoard.ADD_BAD, retval);

        //fill the entire row
        retval = board.placePiece(pieceA, 2,0); board.commit();
        retval = board.placePiece(pieceA, 4,0); board.commit();
        retval = board.placePiece(pieceA, 6,0); board.commit();
        retval = board.placePiece(pieceA, 8,0);
        assertEquals(TetrisBoard.ADD_ROW_FILLED, retval);

        for (int i = 0; i < board.getWidth(); i++) {
            assertEquals(board.getGrid(i,0), true);
            assertEquals(board.getGrid(i,1), true);
            assertEquals(board.getGrid(i,2), false);
        }

    }

    @Test
    void testPlacementHeight() {
        TetrisPiece pieceA = new TetrisPiece(TetrisPiece.SQUARE_STR);
        TetrisBoard board = new TetrisBoard(10,24); board.commit();
        int retval = board.placePiece(pieceA, 0,0); board.commit();
        int x = board.placementHeight(pieceA, 0);
        assertEquals(2,x);
        retval = board.placePiece(pieceA, 0,2); board.commit();
        x = board.placementHeight(pieceA, 0);
        assertEquals(4,x);

    }

    @Test
    void testClearRows() {
        TetrisBoard board = new TetrisBoard(10,24); board.commit();
        TetrisPiece pieceA = new TetrisPiece(TetrisPiece.SQUARE_STR);

        //fill two rows completely
        int retval = board.placePiece(pieceA, 0,0); board.commit();
        retval = board.placePiece(pieceA, 2,0); board.commit();
        retval = board.placePiece(pieceA, 4,0); board.commit();
        retval = board.placePiece(pieceA, 6,0); board.commit();
        retval = board.placePiece(pieceA, 8,0);

        int rcleared = board.clearRows();
        assertEquals(2, rcleared);
    }


    @Test
    void testClearRows1() {
        TetrisBoard board = new TetrisBoard(10,24); board.commit();
        TetrisPiece pieceA = new TetrisPiece(TetrisPiece.SQUARE_STR);
        TetrisPiece pieceB = new TetrisPiece(TetrisPiece.L1_STR);
        TetrisPiece pieceC = new TetrisPiece(TetrisPiece.STICK_STR);
        TetrisPiece pieceD = pieceC.computeNextRotation();


        //fill two rows completely
        int retval = board.placePiece(pieceA, 0,0); board.commit();
        retval = board.placePiece(pieceB, 2,0); board.commit();
        retval = board.placePiece(pieceA, 4,0); board.commit();
        retval = board.placePiece(pieceA, 6,0); board.commit();
        retval = board.placePiece(pieceA, 8,0); board.commit();
        retval = board.placePiece(pieceD, 3,2); board.commit();
        retval = board.placePiece(pieceA, 7,2); board.commit();
        retval = board.placePiece(pieceC, 9,2); board.commit();
        retval = board.placePiece(pieceA, 0,2);
        System.out.println(board);


        int rcleared = board.clearRows();
        assertEquals(2, rcleared);
        System.out.println(board);
    }

    @Test
    void testClearRows2() {
        TetrisBoard board = new TetrisBoard(10,24); board.commit();
        TetrisPiece pieceA = new TetrisPiece(TetrisPiece.SQUARE_STR);
        TetrisPiece pieceB = new TetrisPiece(TetrisPiece.L1_STR);
        TetrisPiece pieceC = new TetrisPiece(TetrisPiece.STICK_STR);
        TetrisPiece pieceD = pieceC.computeNextRotation();


        //fill two rows completely
        int retval = board.placePiece(pieceA, 0,0); board.commit();
        retval = board.placePiece(pieceB, 2,0); board.commit();
        retval = board.placePiece(pieceA, 4,0); board.commit();

        retval = board.placePiece(pieceA, 8,0); board.commit();
        retval = board.placePiece(pieceD, 3,2); board.commit();
        retval = board.placePiece(pieceA, 7,2); board.commit();
        retval = board.placePiece(pieceC, 9,2); board.commit();
        retval = board.placePiece(pieceA, 0,2);
        System.out.println(board);


        int rcleared = board.clearRows();
        assertEquals(1, rcleared);
        System.out.println(board);
    }

}
