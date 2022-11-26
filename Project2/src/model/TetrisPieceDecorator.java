package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

abstract class TetrisPieceDecorator implements Serializable, Piece{

    public abstract int getWidth();


    public abstract int getHeight();

    public abstract TetrisPoint[] getBody();

    public abstract int[] getLowestYVals();

    public abstract boolean equals(Object obj);

    public abstract TetrisPiece[] getPieces();

    public abstract TetrisPiece fastRotation();

    public abstract TetrisPiece makeFastRotations(TetrisPiece root);

    public abstract TetrisPiece computeNextRotation();

    public abstract String toString();

}
