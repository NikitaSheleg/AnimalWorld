
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class WorldApp extends Application {
    private Pane root;
    private Moving moving = new Moving();
    private char[] dict = new char[2];
    private List<Animal> animals = new ArrayList<>();
    private Line upLine = new Line();
    private Line leftLine = new Line();
    private Line downLine = new Line();
    private Line rightLine = new Line();
    private int random1;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(1500, 800);
        dict[0] = 'M';
        dict[1] = 'F';

        upLine.setFill(Color.rgb(0, 0, 0));
        upLine.setStartX(1500);
        upLine.setStartY(0);

        leftLine.setFill(Color.rgb(0, 0, 0));
        leftLine.setStartX(0);
        leftLine.setStartY(900);

        rightLine.setFill(Color.rgb(0, 0, 0));
        rightLine.setStartX(1450);
        rightLine.setStartY(0);
        rightLine.setEndX(1450);
        rightLine.setEndY(800);

        downLine.setFill(Color.rgb(0, 0, 0));
        downLine.setStartX(0);
        downLine.setStartY(800);
        downLine.setEndX(1500);
        downLine.setEndY(800);


        root.getChildren().add(upLine);
        root.getChildren().add(leftLine);
        root.getChildren().add(downLine);
        root.getChildren().add(rightLine);
        setFirstGeneration();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                random1 = new Random().nextInt(10);
                onUpdate();
            }
        };
        timer.start();
        return root;

    }

    private void addObjects(Animal predator, double x, double y) {
        predator.getView().setTranslateX(x);
        predator.getView().setTranslateY(y);
        root.getChildren().add(predator.getView());
    }

    private void addHerbivore(Animal herbivore, double x, double y) {
        animals.add(herbivore);
        addObjects(herbivore, x, y);
    }

    private void addPred(Predator predator, double x, double y) {
        animals.add(predator);
        addObjects(predator, x, y);
    }


    private void setFirstGeneration() {
        for (int i = 0; i < 5; i++) {
            addPred(new Predator('M', 1, 15, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
            addPred(new Predator('F', 1, 9, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
        }
        for (int i = 0; i < 50; i++) {
            addHerbivore(new Herbivore('M', 1, 11, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
            addHerbivore(new Herbivore('F', 1, 7, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
        }

    }

  /*  private void check() {
        for (Animal animal : animals) {
            if (upLine.getBoundsInParent().intersects(animal.getView().getBoundsInLocal())) {
                moving.moveDown(animal);
            }
            if (leftLine.getBoundsInParent().intersects(animal.getView().getBoundsInLocal())) {
                moving.moveRight(animal);
            }
            if (downLine.getBoundsInParent().intersects(animal.getView().getBoundsInLocal())) {
                moving.moveUp(animal);
            }
            if (rightLine.getBoundsInParent().intersects(animal.getView().getBoundsInLocal())) {
                moving.moveLeft(animal);
            }
        }
    }*/


    private void onUpdate() {

        for (Animal animal : animals) {

            boolean isPredator = animal instanceof Predator;

            for (Animal anotherAnimal : animals) {

                if (upLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveDown(anotherAnimal);
                }
                if (leftLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveRight(anotherAnimal);
                }
                if (downLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveUp(anotherAnimal);
                }
                if (rightLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveLeft(anotherAnimal);
                }


                if (!Objects.equals(animal, anotherAnimal)) {
                    boolean isColliding = animal.isColliding(anotherAnimal);

                    boolean isHerbivore = anotherAnimal instanceof Herbivore;
                    if ((isPredator && isHerbivore)) {
                        if (anotherAnimal.getView().localToScene(anotherAnimal.getView().getLayoutBounds().getMinX(), anotherAnimal.getView().getLayoutBounds().getMinY()).distance(animal.getView().localToScene(animal.getView().getLayoutBounds().getMinX(), animal.getView().getLayoutBounds().getMinY())) < 100) {
                            moving.moveTo(animal, anotherAnimal);
                            anotherAnimal.update();
                            animal.update();
                        }

                        if (isColliding) {
                            root.getChildren().remove(anotherAnimal.getView());
                            anotherAnimal.setAlive(false);
                            anotherAnimal.update();
                        }

                    }

                    if (isHerbivore && animal instanceof Herbivore) {
                        if (isColliding) {
                            addHerbivore(new Herbivore('F', 1, 50, 1), 0, 0);
                        }
                    }
                    if (isPredator && anotherAnimal instanceof Predator) {
                        if (isColliding) {
                            addPred(new Predator('M', 1, 50, 1), 0, 0);
                        }
                    }
                    /*else if (isColliding && (isPredator && anotherAnimal instanceof Predator)) {
                        addPred(new Predator('M', 1, 50, 1), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                        root.getChildren().remove(animal.getView());
                        animal.setAlive(false);
                        animal.update();
                        root.getChildren().remove(anotherAnimal.getView());
                        anotherAnimal.setAlive(false);
                        anotherAnimal.update();
                    } else if (isColliding && isHerbivore && animal instanceof Herbivore) {
                        addHerbivore(new Herbivore('F', 1, 50, 1), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                        root.getChildren().remove(animal.getView());
                        animal.setAlive(false);
                        animal.update();
                        root.getChildren().remove(anotherAnimal.getView());
                        anotherAnimal.setAlive(false);
                        anotherAnimal.update();
                    }*/
                   /* else if (isColliding && !(animal instanceof Herbivore && anotherAnimal instanceof Predator)) {
                        if ((animal.getSex() == 'M' && anotherAnimal.getSex() == 'F') || (animal.getSex() == 'F' && anotherAnimal.getSex() == 'M')) {
                            if (isPredator) {
                                addPred(new Predator('M', 1, 50, 0), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                                root.getChildren().remove(animal.getView());
                                animal.setAlive(false);
                                animal.update();
                                root.getChildren().remove(anotherAnimal.getView());
                                anotherAnimal.setAlive(false);
                                anotherAnimal.update();

                            }
                            if (isHerbivore) {
                                addHerbivore(new Herbivore('F', 1, 50, 0), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                                root.getChildren().remove(animal.getView());
                                animal.setAlive(false);
                                animal.update();
                                root.getChildren().remove(anotherAnimal.getView());
                                anotherAnimal.setAlive(false);
                                anotherAnimal.update();
                            }
                        }
                    }*/


                }
            }


        }
        animals.removeIf(Animal::isDead);
        animals.forEach(Animal::update);
    }


    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(createContent()));
        stage.setResizable(true);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(90), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (random1 < 3) {
                    for (Animal animal : animals) {
                        if (animal instanceof Predator) {
                            animal.rotateLeft();
                        } else {
                            animal.rotateRight();
                        }

                    }

                } else if (random1 > 3) {
                    for (Animal animal : animals) {
                        random1 = new Random().nextInt(4);
                        if (animal instanceof Predator) {
                            if (random1 < 2)
                                animal.rotateRight();
                            else animal.rotateLeft();
                        } else {
                            if (random1 > 2)
                                animal.rotateLeft();
                            else animal.rotateRight();
                        }

                    }
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}