package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public interface Piece {
    void action();

    int getWidth();

    int getHeight();

    TetrisPoint[] getBody();

    int[] getLowestYVals();

    boolean equals(Object obj);

    static Piece[] getPieces() {
        return null;
    }

    Piece fastRotation();

    static Piece makeFastRotations(Piece root) {
        return null;
    }

    Piece computeNextRotation();
    String toString();

    private static TetrisPoint[] parsePoints(String string) {
        return null;
    }
}
