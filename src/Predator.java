import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

public class Predator extends Animal {

    public Predator(char sex, int age, double size, int fertilnost) {
        super(new Circle(40, 40, size, Color.DARKRED), sex, age, fertilnost);

       // this.uuid = UUID.randomUUID().toString();
    }


}
