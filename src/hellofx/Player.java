package hellofx;

import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
    
    // Initialize player velocity
    private int velocity = 0; 

    // Player Constructor
    public Player(int xCord, int yCord, int width, int height, int position, Color color){

        // Left Player
        super(width, height, color);

        if (position == 0){
            setX(xCord);
            setY(Main.center(yCord, height));
        }
        // Right Player
        else{
            setX(Main.rightAlign(xCord, width));
            setY(Main.center(yCord, height));
        }
    }

    // Move Player Up
    public void MoveUp(){
        if (velocity > -4 )
        {
            velocity = -4;
        }
               
    }

    // Move Player Down
    public void MoveDown(){
        if (velocity < 4)
        {
            velocity = 4;
        }
    }

    // Stop Player
    public void Stop(){
        velocity = 0;
    }

    // Update Player Position
    public void update_position(int YSCENE){

        double newY = getY() + velocity;

        // Checking for Out of Bounds
        if (newY < 0){
            newY = 0;
        }
        else if (newY + getHeight() > YSCENE){
            newY = YSCENE - getHeight();
        }

        setY(newY);
    }
}
