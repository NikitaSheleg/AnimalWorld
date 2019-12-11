package controller;

import liveOrganisms.Animal;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;


public class Moving {
    private Point2D velocity = new Point2D(0, 0);
    private Node view;
    private TranslateTransition transition = new TranslateTransition();

    public Node getView() {
        return view;
    }

    public double getRotate() {
        return view.getRotate();
    }

    public void setView(Node view) {
        this.view = view;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public void rotateRight() {
        getView().setRotate(getRotate() + 5);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public void moveToCoordinate(Animal animal, double xCoordinate, double yCoordinate) {
        transition.setNode(animal.getView());
        transition.setDuration(Duration.millis(250));
        transition.setFromX(animal.getView().getTranslateX());
        transition.setFromY(animal.getView().getTranslateY());
        transition.setToX(xCoordinate);
        transition.setToY(yCoordinate);
        transition.play();
    }

    public void moveTo(Animal predator, Animal predator1) {
        transition.setNode(predator.getView());
        transition.setDuration(Duration.millis(250));
        transition.setFromX(predator.getView().getTranslateX());
        transition.setFromY(predator.getView().getTranslateY());
        transition.setToX(predator1.getView().getTranslateX());
        transition.setToY(predator1.getView().getTranslateY());
        transition.play();
    }

    public void moveDown(Animal animal) {
        transition.setNode(animal.getView());
        transition.setDuration(Duration.millis(400));
        transition.setFromX(animal.getView().getTranslateX());
        transition.setFromY(animal.getView().getTranslateY());
        transition.setToX(animal.getView().getTranslateX());
        transition.setToY(animal.getView().getTranslateY() + 100);
        transition.play();
    }

    public void moveRight(Animal animal) {
        transition.setNode(animal.getView());
        transition.setDuration(Duration.millis(400));
        transition.setFromX(animal.getView().getTranslateX());
        transition.setFromY(animal.getView().getTranslateY());
        transition.setToX(animal.getView().getTranslateX() + 100);
        transition.setToY(animal.getView().getTranslateY() + 100);
        transition.play();
    }

    public void moveUp(Animal animal) {

        transition.setNode(animal.getView());
        transition.setDuration(Duration.millis(400));
        transition.setFromX(animal.getView().getTranslateX());
        transition.setFromY(animal.getView().getTranslateY());
        transition.setToX(animal.getView().getTranslateX());
        transition.setToY(animal.getView().getTranslateY() - 100);
        transition.play();
    }


    public void moveLeft(Animal animal) {
        transition.setNode(animal.getView());
        transition.setDuration(Duration.millis(400));
        transition.setFromX(animal.getView().getTranslateX());
        transition.setFromY(animal.getView().getTranslateY());
        transition.setToX(animal.getView().getTranslateX() - 100);
        transition.setToY(animal.getView().getTranslateY() + 100);
        transition.play();
    }

    public void rotateLeft() {
        getView().setRotate(getView().getRotate() - 5);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public void update() {
        getView().setTranslateX(getVelocity().getX() + getView().getTranslateX());
        getView().setTranslateY(getVelocity().getY() + getView().getTranslateY());

    }

}
