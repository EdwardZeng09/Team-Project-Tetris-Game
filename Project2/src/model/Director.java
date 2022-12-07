package model;

import views.TetrisView;

import javax.swing.*;
import java.awt.*;

public class Director {

    public MapBuilder b1;


    public void getBuilder(){

        Frame frame = new Frame();

        int i = JOptionPane.showConfirmDialog(frame,
                "Do you need visual help or zoom in on the game panel?",
                "Accessible Option 1",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        int j = JOptionPane.showConfirmDialog(frame,
                "Do you need red-green color blindness help to remove red and green colors from the game?",
                "Accessible Option 2",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);


        if(i == 0 && j == 0){

            this.b1 = new BothBuilder();

        } else if (i == 0) {

            this.b1 = new LMapBuilder();

        } else if (j == 0) {

            this.b1 = new CbMapBuilder();

        }else {
            this.b1 = new NoBuilder();
        }


    }

    public void buildModel(TetrisModel model) {

        if(this.b1.direction() == 0){


        } else if (this.b1.direction() == 1) {

        } else if (this.b1.direction() == 2) {

        }

    }

    public void buildView(TetrisView view) {

        if(this.b1.direction() == 0){

        } else if (this.b1.direction() == 1) {

        } else if (this.b1.direction() == 2) {

        }

    }
}
