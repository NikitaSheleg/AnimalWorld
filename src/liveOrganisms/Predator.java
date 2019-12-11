package liveOrganisms;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Predator extends Animal {

    public Predator(char sex, int age, double size, int fertilnost) {
        super(new Circle(40, 40, size, Color.DARKRED), sex, age, fertilnost);

       // this.uuid = UUID.randomUUID().toString();
    }


}
