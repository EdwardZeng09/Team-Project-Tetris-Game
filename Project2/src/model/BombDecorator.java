package model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BombDecorator extends TetrisPieceDecorator{
    private TetrisPiece TP;


    public BombDecorator(TetrisPiece tp){
        TP = tp;
    }
    @Override
    public int getWidth() {
        return TP.getWidth();
    }

    @Override
    public int getHeight() {
        return TP.getHeight();
    }

    @Override
    public TetrisPoint[] getBody() {
        return TP.getBody();
    }

    @Override
    public int[] getLowestYVals() {
        return TP.getLowestYVals();
    }

    @Override
    public boolean equals(Object obj) {
        return TP.equals(obj);
    }

    @Override
    public TetrisPiece[] getPieces() {
        return TetrisPiece.getPieces();
    }

    @Override
    public TetrisPiece fastRotation() {
       return TP.fastRotation();
    }

    @Override
    public TetrisPiece makeFastRotations(TetrisPiece root) {
        return TetrisPiece.makeFastRotations(TP);

    }

    @Override
    public TetrisPiece computeNextRotation() {
        return TP.computeNextRotation();
    }

    @Override
    public String toString() {
        return TP.toString();
    }

    private static TetrisPoint[] parsePoints(String string) {
        List<TetrisPoint> points = new ArrayList<TetrisPoint>();
        StringTokenizer tok = new StringTokenizer(string);
        try {
            while(tok.hasMoreTokens()) {
                int x = Integer.parseInt(tok.nextToken());
                int y = Integer.parseInt(tok.nextToken());

                points.add(new TetrisPoint(x, y));
            }
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("Could not parse x,y string:" + string);
        }
        // Make an array out of the collection
        TetrisPoint[] array = points.toArray(new TetrisPoint[0]);
        return array;
    }
}
