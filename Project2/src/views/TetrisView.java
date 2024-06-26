
package views;

import model.TetrisContext;
import model.DestroyDecorator;
import model.TetrisBoard;
import model.TetrisModel;
import model.TetrisPiece;
import model.Achievement;
import model.Director;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.HashMap;





/**
 * Tetris View
 *
 * Based on the Tetris assignment in the Nifty Assignments Database, authored by Nick Parlante
 */
public class TetrisView {

    TetrisModel model; //reference to model
    Stage stage;

    Button startButton, stopButton, loadButton, saveButton, newButton; //buttons for functions
    Label scoreLabel = new Label("");
    Label gameModeLabel = new Label("");

    Label AchievementLable = new Label((""));

    BorderPane borderPane;
    Canvas canvas;
    GraphicsContext gc; //the graphics context will be linked to the canvas

    Boolean paused;
    Timeline timeline;

    int pieceWidth = 20; //width of block on display
    private double width; //height and width of canvas
    private double height;
    private String piecec;
    private TetrisContext context;
    
    public int textSize = 20;

    public int bottonSize = 12;

    public Color viewColor1 = Color.GREEN;

    public Color viewColor2 = Color.RED;

    public Color viewColor3 = Color.BLACK;

    /**
     * Constructor
     *
     * @param model reference to tetris model
     * @param stage application stage
     */

    public TetrisView(TetrisModel model, Stage stage, TetrisContext context, Director d) {
        this.model = model;
        this.stage = stage;
        
        if( d.b1.direction()== 0 ){

            changePieceSize(35);

            changeTextSize(30);

            changeBottonSize(20);

        } else if (d.b1.direction()== 1) {

            changeColor();

        } else if (d.b1.direction()== 2) {

            changePieceSize(35);

            changeTextSize(30);

            changeBottonSize(20);

            changeColor();

        }
        
        initUI(context);
    }

    /**
     * Initialize interface
     */
    private void initUI(TetrisContext context) {
        this.paused = false;
        this.stage.setTitle("CSC207 Tetris");
        this.width = this.model.getWidth()*pieceWidth + 2;
        this.height = this.model.getHeight()*pieceWidth + 2;

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #121212;");

        //add canvas
        canvas = new Canvas(this.width, this.height);
        canvas.setId("Canvas");
        gc = canvas.getGraphicsContext2D();

        //labels
        gameModeLabel.setId("GameModeLabel");
        scoreLabel.setId("ScoreLabel");

        gameModeLabel.setText("Player is: Human");
        gameModeLabel.setMinWidth(250);
        gameModeLabel.setFont(new Font(this.textSize));
        gameModeLabel.setStyle("-fx-text-fill: #e8e6e3");

        final ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton pilotButtonHuman = new RadioButton("Human");
        pilotButtonHuman.setToggleGroup(toggleGroup);
        pilotButtonHuman.setSelected(true);
        pilotButtonHuman.setUserData(Color.SALMON);
        pilotButtonHuman.setFont(new Font(this.textSize));
        pilotButtonHuman.setStyle("-fx-text-fill: #e8e6e3");

        RadioButton pilotButtonComputer = new RadioButton("Computer (Default)");
        pilotButtonComputer.setToggleGroup(toggleGroup);
        pilotButtonComputer.setUserData(Color.SALMON);
        pilotButtonComputer.setFont(new Font(this.textSize));
        pilotButtonComputer.setStyle("-fx-text-fill: #e8e6e3");

        scoreLabel.setText("Score is: 0");
        scoreLabel.setFont(new Font(this.textSize));
        scoreLabel.setStyle("-fx-text-fill: #e8e6e3");


        AchievementLable.setText("Achievements:");
        AchievementLable.setFont(new Font(this.textSize));
        AchievementLable.setStyle("-fx-text-fill: #e8e6e3");


        this.context = context;


        //add buttons
        Button oneButton = new Button("Level One");
        oneButton.setId("Level One");
        oneButton.setPrefSize(150, 50);
        oneButton.setFont(new Font(bottonSize));
        oneButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        Button twoButton = new Button("Level Two");
        twoButton.setId("Level Two");
        twoButton.setPrefSize(150, 50);
        twoButton.setFont(new Font(bottonSize));
        twoButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        Button threeButton = new Button("Level Three");
        threeButton.setId("Level Three");
        threeButton.setPrefSize(150, 50);
        threeButton.setFont(new Font(bottonSize));
        threeButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        startButton = new Button("Start");
        startButton.setId("Start");
        startButton.setPrefSize(150, 50);
        startButton.setFont(new Font(bottonSize));
        startButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        stopButton = new Button("Stop");
        stopButton.setId("Start");
        stopButton.setPrefSize(150, 50);
        stopButton.setFont(new Font(bottonSize));
        stopButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        saveButton = new Button("Save");
        saveButton.setId("Save");
        saveButton.setPrefSize(150, 50);
        saveButton.setFont(new Font(bottonSize));
        saveButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        loadButton = new Button("Load");
        loadButton.setId("Load");
        loadButton.setPrefSize(150, 50);
        loadButton.setFont(new Font(bottonSize));
        loadButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        newButton = new Button("New Game");
        newButton.setId("New");
        newButton.setPrefSize(150, 50);
        newButton.setFont(new Font(bottonSize));
        newButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");

        HBox controls = new HBox(20, saveButton, loadButton, newButton, startButton, stopButton, oneButton, twoButton, threeButton);
        controls.setPadding(new Insets(20, 20, 20, 20));
        controls.setAlignment(Pos.CENTER);

        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setStyle("-fx-control-inner-background: palegreen;");

        VBox vBox = new VBox(20, slider);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setAlignment(Pos.TOP_CENTER);

        VBox scoreBox = new VBox(20, scoreLabel, gameModeLabel, pilotButtonHuman, pilotButtonComputer, AchievementLable);
        scoreBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setAlignment(Pos.TOP_CENTER);

        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> swapPilot(newVal));

        //timeline structures the animation, and speed between application "ticks"
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), e -> updateBoard()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //configure this such that you start a new game when the user hits the newButton
        //Make sure to return the focus to the borderPane once you're done!
        newButton.setOnAction(e -> {
            this.model.newGame();
            borderPane.requestFocus();
        });

        //configure this such that you restart the game when the user hits the startButton
        //Make sure to return the focus to the borderPane once you're done!
        startButton.setOnAction(e -> {
            paused = false;
            this.model.Continue();
            borderPane.requestFocus();
        });

        //configure this such that you pause the game when the user hits the stopButton
        //Make sure to return the focus to the borderPane once you're done!
        stopButton.setOnAction(e -> {
            this.model.stopGame();
            borderPane.requestFocus();
        });

        //configure this such that the save view pops up when the saveButton is pressed.
        //Make sure to return the focus to the borderPane once you're done!
        saveButton.setOnAction(e -> {
            SaveView sv = new SaveView(this);
            borderPane.requestFocus();
        });

        //configure this such that the load view pops up when the loadButton is pressed.
        //Make sure to return the focus to the borderPane once you're done!
        loadButton.setOnAction(e -> {
            LoadView lv = new LoadView(this);
            borderPane.requestFocus();
        });

        oneButton.setOnAction(e -> {
            context.getState().atone();
            timeline.setRate(0.1);
        });
        twoButton.setOnAction(e -> {
            context.getState().atTwo();
            timeline.setRate(1);
        });
        threeButton.setOnAction(e -> {
            context.getState().atThree();
            timeline.setRate(10);
        });

        //configure this such that you adjust the speed of the timeline to a value that
        //ranges between 0 and 3 times the default rate per model tick.  Make sure to return the
        //focus to the borderPane once you're done!
        slider.setOnMouseReleased(e -> {
            timeline.setRate(slider.getValue()*0.03);
        });

        //configure this such that you can use controls to rotate and place pieces as you like!!
        //You'll want to respond to tie key presses to these moves:
        // TetrisModel.MoveType.DROP, TetrisModel.MoveType.ROTATE, TetrisModel.MoveType.LEFT
        //and TetrisModel.MoveType.RIGHT
        //make sure that you don't let the human control the board
        //if the autopilot is on, however.
        borderPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {

                if(k.getCode() == KeyCode.A){
                    model.modelTick(TetrisModel.MoveType.LEFT);
                }else if (k.getCode() == KeyCode.D) {
                    model.modelTick(TetrisModel.MoveType.RIGHT);
                }else if (k.getCode() == KeyCode.W) {
                    model.modelTick(TetrisModel.MoveType.ROTATE);
                }else if (k.getCode() == KeyCode.S) {
                    model.modelTick(TetrisModel.MoveType.DROP);
                }
            }
        });

        borderPane.setTop(controls);
        borderPane.setRight(scoreBox);
        borderPane.setCenter(canvas);
        borderPane.setBottom(vBox);

        var scene = new Scene(borderPane, 800, 800);
        this.stage.setScene(scene);
        this.stage.show();
    }

    /**
     * Get user selection of "autopilot" or human player
     *
     * @param value toggle selector on UI
     */
    private void swapPilot(Toggle value) {
        RadioButton chk = (RadioButton)value.getToggleGroup().getSelectedToggle();
        String strVal = chk.getText();
        if (strVal.equals("Computer (Default)")){
            this.model.setAutoPilotMode();
            gameModeLabel.setText("Player is: Computer (Default)");
        } else if (strVal.equals("Human")) {
            this.model.setHumanPilotMode();
            gameModeLabel.setText("Player is: Human");
        }
        borderPane.requestFocus(); //give the focus back to the pane with the blocks.
    }

    /**
     * Update board (paint pieces and score info)
     */
    private void updateBoard() {
        if (this.paused != true) {
            TetrisPiece a = new TetrisPiece(this.model.currentPiece.getBody());
            TetrisBoard board = new TetrisBoard(10,24);
            DestroyDecorator b = new DestroyDecorator(this.model.currentPiece.getBody(), this.model);
            if(this.model.currentPiece.getClass().getSimpleName() == b.getClass().getSimpleName()){
                this.piecec = "Yellow";
            } else if (this.model.currentPiece.getClass().getSimpleName() == a.getClass().getSimpleName()) {
                this.piecec = "Red";
            }else{
                this.piecec = "Black";
            }
            paintBoard();
            this.model.modelTick(TetrisModel.MoveType.DOWN);
            updateScore();
            updateAchievements();
        }
    }

    /**
     * Update score on UI
     */
    private void updateScore() {
        if (this.paused != true) {
            scoreLabel.setText("Score is: " + model.getScore() + "\nPieces placed:" + model.getCount());
        }
    }

    private void updateAchievements() {
        if (this.paused != true) {
            StringBuilder sb = new StringBuilder();
            HashMap<String, Boolean> acs = Achievement.getInstance().getachievents();
            for(String ac: acs.keySet()){
                if(acs.get(ac)){
                    sb.append(ac);
                    sb.append("\n");
                }
            }
            AchievementLable.setText("Achievements: " + "\n" + sb);
        }
    }

    /**
     * Methods to calibrate sizes of pixels relative to board size
     */
    private final int yPixel(int y) {
        return (int) Math.round(this.height -1 - (y+1)*dY());
    }
    private final int xPixel(int x) {
        return (int) Math.round((x)*dX());
    }
    private final float dX() {
        return( ((float)(this.width-2)) / this.model.getBoard().getWidth() );
    }
    private final float dY() {
        return( ((float)(this.height-2)) / this.model.getBoard().getHeight() );
    }

    /**
     * Draw the board
     */
    public void paintBoard() {

        // Draw a rectangle around the whole screen
        gc.setStroke(viewColor1);
        gc.setFill(viewColor1);
        gc.fillRect(0, 0, this.width-1, this.height-1);

        // Draw the line separating the top area on the screen
        gc.setStroke(viewColor3);
        int spacerY = yPixel(this.model.getBoard().getHeight() - this.model.BUFFERZONE - 1);
        gc.strokeLine(0, spacerY, this.width-1, spacerY);

        // Factor a few things out to help the optimizer
        final int dx = Math.round(dX()-2);
        final int dy = Math.round(dY()-2);
        final int bWidth = this.model.getBoard().getWidth();

        int x, y;
        // Loop through and draw all the blocks; sizes of blocks are calibrated relative to screen size
        for (x=0; x<bWidth; x++) {
            int left = xPixel(x);	// the left pixel
            // draw from 0 up to the col height
            final int yHeight = this.model.getBoard().getColumnHeight(x);
                        if(this.piecec == "Red"){
                for (y=0; y<yHeight; y++) {
                    if (this.model.getBoard().getGrid(x, y)) {
                        gc.setFill(viewColor2);
                        gc.fillRect(left+1, yPixel(y)+1, dx, dy);
                        gc.setFill(viewColor1);
                    }
                }
            }
            else if(this.piecec == "Yellow"){
                for (y=0; y<yHeight; y++) {
                    if (this.model.getBoard().getGrid(x, y)) {
                        gc.setFill(Color.YELLOW);
                        gc.fillRect(left+1, yPixel(y)+1, dx, dy);
                        gc.setFill(viewColor1);
                    }
                }
            }
            else{
                for (y=0; y<yHeight; y++) {
                    if (this.model.getBoard().getGrid(x, y)) {
                        gc.setFill(Color.GREY);
                        gc.fillRect(left+1, yPixel(y)+1, dx, dy);
                        gc.setFill(viewColor1);
                    }
                }
            }
        }

    }

    /**
     * Create the view to save a board to a file
     */
    private void createSaveView(){
        SaveView saveView = new SaveView(this);
    }

    /**
     * Create the view to select a board to load
     */
    private void createLoadView(){
        LoadView loadView = new LoadView(this);
    }
    
    public void changePieceSize(int newSize){

        this.pieceWidth = newSize;

    }

    public void changeTextSize(int newSize){

        this.textSize = newSize;

    }

    public void changeBottonSize(int newSize){

        this.bottonSize = newSize;

    }

    public void changeColor(){

        this.viewColor1 = Color.BLACK;

        this.viewColor2 = Color.WHITE;

    }

}