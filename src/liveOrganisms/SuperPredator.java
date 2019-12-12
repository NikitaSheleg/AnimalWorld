package liveOrganisms;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SuperPredator extends Animal {
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;
    public SuperPredator(char sex, int age, int fertilnost) {
        super(new Circle(40, 40, 20, Color.DARKVIOLET), sex, age, fertilnost);
    }
}
