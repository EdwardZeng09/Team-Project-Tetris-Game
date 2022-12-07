package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public abstract class TetrisPieceDecorator implements Serializable, Piece{
    public TetrisPoint[] points;

    public static TetrisModel tm;

    public TetrisPieceDecorator(TetrisPoint[] points, TetrisModel tm){
        this.points = points;
        this.tm = tm;
    }

    @Override
    public void action() {
        return;
    }
}
