package liveOrganisms;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SuperPredator extends Animal {
    public SuperPredator(char sex, int age, int fertilnost) {
        super(new Circle(40, 40, 20, Color.DARKGOLDENROD), sex, age, fertilnost);
    }
}
