package hellofx;

public class Score {
    private int p1_score;
    private int p2_score;

    // Score Constrcutor
    public Score(){
        this.p1_score = 0;
        this.p2_score = 0;
    }

    // Get Player 1 Score
    public int get_p1(){
        return p1_score;
    }

    // Get Player 2 Score
    public int get_p2(){
        return p2_score;
    }

    // Increment Player 1 Score
    public void increment_p1(){
        p1_score++;
    }

    // Increment Player 2 Score
    public void increment_p2(){
        p2_score++;
    }
    
}
