import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.UUID;

public class Herbivore extends Animal {
    public Herbivore(char sex, int age, double size, int fertilnost) {
        super(new Circle(40, 40, size, Color.DARKGREEN), sex, age, fertilnost);
       // this.uuid = UUID.randomUUID().toString();
    }
}
