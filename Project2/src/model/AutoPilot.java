package model;

import java.io.Serializable;

/** Represents an AutoPilot class for Tetris.
 * Based on the Tetris assignment in the Nifty Assignments Database, authored by Nick Parlante
 */
public class AutoPilot implements TetrisPilot, Serializable {

    /**
     * Given a piece and a board, return a move object that represents
     * the best move for that piece. Note that if the move returned is
     * invalid or impossible, it won't be played!
     *
     * @param board    The current board configuration
     * @param piece    The current piece
     * @param currentX  Where the current piece is currently located (X position)
     * @param currentY  Where the current piece is currently located (Y position)
     * @return The best move (Rotate, Left, or Right), based on an assessment of "good" moves
     */
    @Override
    public TetrisModel.MoveType bestMove(TetrisBoard board, TetrisPiece piece, int currentX, int currentY) {

        //The current strategy is to pick a random move from among the options: Left, Right, and Rotate
        //Why not try to improve on this!
        int index = (int) Math.round(Math.random()*(TetrisModel.MoveType.values().length - 3));
        return TetrisModel.MoveType.values()[index];

    }

    /**
     * Given a board, calculate a number that evaluates the board's "goodness".
     * A bigger number indicates a "worse" board.  You might want to
     * use this function to facilitate a "look ahead" at the tetris.boards you
     * can realize from your current position. If a move drives
     * you in the direction of a "good" board, maybe that's the right one to choose!
     * What are some metrics you might use to evaluate "goodness"?  Well,
     * you might count the number of "holes" buried beneath lines on the board.
     * A lot of holes indicates a pretty bad board.
     *
     * Note that if you alter the state of the board as you are assessing them, you
     * can always use your "undo" function to reset the board.  Note also that any implementation
     * will get you full marks for the exercise, so don't stress about it too much!!  Have fun
     * with it, if the task appeals :).
     *
     * @param board  The current board configuration
     * @return  A number that evaluates the board.
     */
    public double evaluateBoard(TetrisBoard board) {
        double point = 0.0;
        //The difference between the height of neighbor col should be small, the larger the worse.
        for(int i = 0;i < board.getWidth() - 1;i++){
            double diff = abs(board.getColumnHeight(i) - board.getColumnHeight(i+1));
            point += diff;
        }
        //Check 'holes'.
        for(int i = 1; i < board.getHeight() - 1; i++){
            for(int j = 1; j < board.getWidth() - 1; j++){
                if(!(board.getGrid(i, j))){
                    if(board.getGrid(i-1, j) && board.getGrid(i+1, j) && board.getGrid(i, j+1) && board.getGrid(i, j-1)){
                        point += 10;
                    }
                }
                }
            }
        return point;
    }



    /**
     *
     * @param i integer
     * @return absolute of i
     */
    private int abs(Integer i){
        if(i > 0){
            return i;
        }
        return i * -1;
    }


}
