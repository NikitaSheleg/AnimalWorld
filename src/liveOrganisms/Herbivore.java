package liveOrganisms;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Herbivore extends Animal {
    public Herbivore(char sex, int age, double size, int fertilnost) {
        super(new Circle(40, 40, size, Color.DARKGREEN), sex, age, fertilnost);
       // this.uuid = UUID.randomUUID().toString();
    }
}
