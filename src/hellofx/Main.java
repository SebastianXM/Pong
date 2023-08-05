package hellofx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import java.util.HashSet;
import javafx.animation.AnimationTimer;



public class Main extends Application {

    // Setting Player Varibales
    private static final int P1_XCORD = 50;
    private static final int P2_XCORD = 850;
    private static final int YCORD = 800; 
    private static final int PLAYER_WIDTH = 20; 
    private static final int HEIGHT = 90; 

    // Setting Screen Variables
    private static final int XSCENE = 900; 
    private static final int YSCENE = 800;

    // Setting Ball Variables
    private static final int ballsize = 10;

    // GameOver varible
    private boolean gameOver = false;

    // HashSet for Keys
    private HashSet<KeyCode> keys = new HashSet<>();

    
    // Creating Players, Ball, Text
    private Score score = new Score();
    private Player p1 = new Player(P1_XCORD, YCORD, PLAYER_WIDTH, HEIGHT, 0, Color.WHITE);
    private Player p2 = new Player(P2_XCORD, YCORD, PLAYER_WIDTH, HEIGHT, 1, Color.WHITE);
    private Ball ball = new Ball(XSCENE, YSCENE, ballsize, Color.WHITE);
    private Text p1_score = new Text((XSCENE / 2) - 75, 50, String.valueOf(score.get_p1()));
    private Text p2_score = new Text((XSCENE / 2) + 50, 50, String.valueOf(score.get_p2()));

    @Override
    public void start(Stage primarStage) throws Exception{
        
        // Creating Stage and Scene
        primarStage.setTitle("Pong");
        Group root = new Group();
        Scene s = new Scene(root, XSCENE, YSCENE, Color.BLACK);
        primarStage.setScene(s);

        // Fixing Score
        p1_score.setFill(Color.WHITE);
        p2_score.setFill(Color.WHITE);
        p1_score.setFont(new Font(55));
        p2_score.setFont(new Font(55));
        
        // Adding objects
        root.getChildren().addAll(p1, p2, ball, p1_score, p2_score);

        // Adding Middle Line
        int temp = 15;
        while (temp < YSCENE){
            int width = 10;
            Rectangle middle = new Rectangle(center(XSCENE, width), temp, width, 65);
            middle.setFill(Color.WHITE);
            middle.setOpacity(0.2);
            root.getChildren().add(middle);
            temp += 100;
        }

        // Key Press and Relased input
        s.setOnKeyPressed(event -> keyPress(event.getCode()));
        s.setOnKeyReleased(event -> keyRelease(event.getCode()));

        // Displaying Screen
        primarStage.show();

        // Game Loop
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                input();
                updateGame();
            }
        };

        gameLoop.start();
    }

    public void updateGame(){
        if (!gameOver){

            // Update Ball Position
            ball.updatePosition();
            
            // Update Player Position
            p1.update_position(YSCENE);
            p2.update_position(YSCENE);
        
            // Check Collisions
            ball.screenCollision(XSCENE, YSCENE);
            ball.playerCollision(p1, p2);

            // Increment Score
            if (ball.getCenterX() - ball.getRadius() <= 0){
                score.increment_p2();
                p2_score.setText(String.valueOf(score.get_p2()));
                resetBall();
            }
            else if (ball.getCenterX() - ball.getRadius() >= XSCENE){
                score.increment_p1();
                p1_score.setText(String.valueOf(score.get_p1()));
                resetBall();
            }

            if (score.get_p1() == 10 || score.get_p2() == 10){
                gameOver = true;
                showWinner();
            }   
        }
    }
    
    // Display Winner
    public void showWinner(){
        Text winner;
        if (score.get_p1() == 10){
            winner = new Text(XSCENE / 2 - 140, YSCENE / 2 - 200, "Player 1 Wins!");
        }
        else{
            winner = new Text(XSCENE / 2 - 140 , YSCENE / 2 - 200, "Player 2 Wins!");
        }

        winner.setFont(new Font(50));
        winner.setFill(Color.WHITE);
        Group root = (Group) p1_score.getParent();
        root.getChildren().add(winner);
    }

    // Pressed Keys get added to HashSet
    public void keyPress(KeyCode code){
        keys.add(code);
    }

    // Released Keys get removed from HashSet
    public void keyRelease(KeyCode code){
        keys.remove(code);
    }

    // Handling Input
    public void input(){

        // Player 1 Movement
        if(keys.contains(KeyCode.W)){
            p1.MoveUp();
        }
        else if (keys.contains(KeyCode.S)){
            p1.MoveDown();
        }
        else{
            p1.Stop();
        }

        // Player 2 Movement
        if(keys.contains(KeyCode.UP)){
            p2.MoveUp();
        }
        else if (keys.contains(KeyCode.DOWN)){
            p2.MoveDown();
        }
        else{
            p2.Stop();
        }
    }
    
    // Reset Ball After Score
    public void resetBall(){
        ball.setCenterX(XSCENE / 2);
        ball.setCenterY(YSCENE / 2);
        ball.startDirection();
    }

    // Centering Method
    public static int center(int Cord, int diff){
        return (Cord / 2) - (diff / 2);
    }
    
    // Correctly position of objects on the right
    public static int rightAlign(int xCord_p2, int width){
        return xCord_p2 - width;
    }

    public static void main(String[] args){
        launch();
    }
}
