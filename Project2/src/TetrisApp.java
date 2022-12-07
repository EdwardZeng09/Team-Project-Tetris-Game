
import model.Director;
import model.TetrisContext;
import model.TetrisModel;
import views.TetrisView;

import javafx.application.Application;
import javafx.stage.Stage;

/** 
 * A Tetris Application, in JavaFX
 * 
 * Based on the Tetris assignment in the Nifty Assignments Database, authored by Nick Parlante
 */
public class TetrisApp extends Application {
    TetrisModel model;
    TetrisView view;

    /** 
     * Main method
     * 
     * @param args agument, if any
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * Start method.  Control of application flow is dictated by JavaFX framework
     * 
     * @param primaryStage stage upon which to load GUI elements
     */
    @Override
    public void start(Stage primaryStage) {

        Director newDirector = new Director();
        newDirector.getBuilder();

        this.model = new TetrisModel(); // create a model

        newDirector.buildModel(this.model);

        this.view = new TetrisView(model, primaryStage, new TetrisContext(), newDirector); //tie the model to the view

        newDirector.buildView(this.view);

        this.model.startGame(); //begin
    }

}