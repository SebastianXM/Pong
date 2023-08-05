package hellofx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;


public class Ball extends Circle{

    private double xvelocity;
    private double yvelocity;

    // Ball Constructor
    public Ball(int xscene, int yscene, int size, Color color){
        super(xscene / 2, yscene / 2, size, color);
        startDirection();
    }

    // Random start Direction
    public void startDirection(){
        Random random = new Random();
        int xrandom = random.nextInt(2);
        xvelocity = xrandom == 0 ? -5 : 5;
        int yrandom = random.nextInt(2);
        yvelocity = yrandom == 0 ? -1 : 1;
    }

    // Updating Position
    public void updatePosition(){
        setCenterX(getCenterX() + (xvelocity));
        setCenterY(getCenterY() + (yvelocity));
    }

    // Collisions with edges of screen
    public void screenCollision(int screenWidth, int screenHeight){
        if (getCenterY() - getRadius() <= 0 || getCenterY() + getRadius() >= screenHeight){
            yvelocity = yvelocity * -1;
        }

        if (getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= screenWidth){
            xvelocity = -xvelocity * -1;
            
        }
    }

    // Collisions with players
    public void playerCollision(Player p1, Player p2){
        if (intersects(p1.getBoundsInLocal())){
            // Increase Ball Velocity after every hit
            xvelocity *= -1.05;
            yvelocity *= 1.05;
        }

        if (intersects(p2.getBoundsInLocal())){
            // Increase Ball Velocity after every hit
            xvelocity *= -1.05;  
            yvelocity *= 1.05; 
        }
    }
}
