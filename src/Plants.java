import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plants implements Nature {
    private Node view;
    private boolean alive = true;

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

    public Plants() {
        this.setView(new Circle(40, 40, 3, Color.SPRINGGREEN));
    }

}
