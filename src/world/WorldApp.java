package world;

import controller.Moving;
import javafx.geometry.Point2D;
import liveOrganisms.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class WorldApp extends Application {
    private Pane root;
    private Moving moving = new Moving();
    private List<Animal> animals = new ArrayList<>();
    private List<Plants> plants = new ArrayList<>();
    private Line upLine = new Line();
    private Line leftLine = new Line();
    private Line downLine = new Line();
    private Line rightLine = new Line();
    private int random1;
    private List<Animal> animalsCopy = new ArrayList<>();
    private SuperPredator superPredator = new SuperPredator('M', 18, 0);
    private boolean isPregnantPredator = false;
    private boolean isPregnantHerbivore = false;


    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(1500, 800);
        root.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
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

        root.getChildren().add(superPredator.getView());
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

    private void addPlants(Plants plant, double x, double y) {
        plants.add(plant);
        plant.getView().setTranslateX(x);
        plant.getView().setTranslateY(y);
        root.getChildren().add(plant.getView());
    }

    private void setFirstGeneration() {
        for (int i = 0; i < 5; i++) {
            addPred(new Predator('M', 1, 15, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
            addPred(new Predator('F', 1, 9, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
        }
        for (int i = 0; i < 25; i++) {
            addHerbivore(new Herbivore('M', 1, 11, 0), Math.random() * root.getPrefHeight() - 100, Math.random() * root.getPrefWidth() - 100);
            addHerbivore(new Herbivore('F', 1, 7, 0), Math.random() * root.getPrefHeight() - 100, Math.random() * root.getPrefWidth() - 100);
        }
        for (int i = 0; i < 20; i++) {
            addPlants(new Plants(), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
        }
    }

  /*  private void check() {
        for (animals.Animal animal : animals) {
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
            //TODO add nature instead animals and plants and increase performance
            for (Animal anotherAnimal : animals) {
                boolean isHerbivore = anotherAnimal instanceof Herbivore;
                //TODO move for to another place, now it works just with a couple herbivores
                for (Plants plant : plants) {
                    if (isHerbivore && anotherAnimal.getView().localToScene(anotherAnimal.getView().getLayoutBounds().getMinX(), anotherAnimal.getView().getLayoutBounds().getMinY()).distance(plant.getView().localToScene(plant.getView().getLayoutBounds().getMinX(), plant.getView().getLayoutBounds().getMinY())) < 40) {
                        moving.moveToCoordinate(anotherAnimal, plant.getView().getTranslateX(), plant.getView().getTranslateY());
                        if (anotherAnimal.getView().getBoundsInParent().intersects(plant.getView().getBoundsInParent())) {
                            plants.remove(plant);
                            root.getChildren().remove(plant.getView());
                            plant.setAlive(false);
                            break;
                        }

                    }

                }
                if (upLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveDown(anotherAnimal);
                } else if (leftLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveRight(anotherAnimal);
                } else if (downLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveUp(anotherAnimal);
                } else if (rightLine.getBoundsInLocal().intersects(anotherAnimal.getView().getBoundsInParent())) {
                    moving.moveLeft(anotherAnimal);
                }
                if (!Objects.equals(animal, anotherAnimal)) {
                    boolean isColliding = animal.isColliding(anotherAnimal);
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
                    /*else if (isColliding && (isPredator && anotherAnimal instanceof animals.Predator)) {
                        addPred(new animals.Predator('M', 1, 50, 1), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                        root.getChildren().remove(animal.getView());
                        animal.setAlive(false);
                        animal.update();
                        root.getChildren().remove(anotherAnimal.getView());
                        anotherAnimal.setAlive(false);
                        anotherAnimal.update();
                    } else if (isColliding && isHerbivore && animal instanceof animals.Herbivore) {
                        addHerbivore(new animals.Herbivore('F', 1, 50, 1), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                        root.getChildren().remove(animal.getView());
                        animal.setAlive(false);
                        animal.update();
                        root.getChildren().remove(anotherAnimal.getView());
                        anotherAnimal.setAlive(false);
                        anotherAnimal.update();
                    }*/
                   /* else if (isColliding && !(animal instanceof animals.Herbivore && anotherAnimal instanceof animals.Predator)) {
                        if ((animal.getSex() == 'M' && anotherAnimal.getSex() == 'F') || (animal.getSex() == 'F' && anotherAnimal.getSex() == 'M')) {
                            if (isPredator) {
                                addPred(new animals.Predator('M', 1, 50, 0), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                                root.getChildren().remove(animal.getView());
                                animal.setAlive(false);
                                animal.update();
                                root.getChildren().remove(anotherAnimal.getView());
                                anotherAnimal.setAlive(false);
                                anotherAnimal.update();

                            }
                            if (isHerbivore) {
                                addHerbivore(new animals.Herbivore('F', 1, 50, 0), animal.getView().getTranslateX(), animal.getView().getTranslateY());
                                root.getChildren().remove(animal.getView());
                                animal.setAlive(false);
                                animal.update();
                                root.getChildren().remove(anotherAnimal.getView());
                                anotherAnimal.setAlive(false);
                                anotherAnimal.update();
                            }
                        }
                    }*/
                } else if (animal.getId() != anotherAnimal.getId() && (isPredator && anotherAnimal instanceof Predator) && (animal.getSex() == 'M' && anotherAnimal.getSex() == 'F') || (animal.getSex() == 'F' && anotherAnimal.getSex() == 'M')) {
                    if (animal.isColliding(anotherAnimal)) {
                        animal.setPregnant(true);
                    }
                } else if (animal.getId() != anotherAnimal.getId() && (animal instanceof Herbivore && isHerbivore) && (animal.getSex() == 'M' && anotherAnimal.getSex() == 'F') || (animal.getSex() == 'F' && anotherAnimal.getSex() == 'M')) {
                    if (animal.isColliding(anotherAnimal)) {
                        animal.setPregnant(true);
                    }
                }
                if (superPredator.isColliding(anotherAnimal)) {
                    root.getChildren().remove(anotherAnimal.getView());
                    anotherAnimal.setAlive(false);
                    anotherAnimal.update();
                }
            }

        }
        superPredator.update();
        animals.removeIf(Animal::isDead);
        animals.forEach(Animal::update);
    }


    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(createContent()));
        stage.setResizable(true);

        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                superPredator.rotateLeft();
            } else if (e.getCode() == KeyCode.D) {
                superPredator.rotateRight();
            } else if (e.getCode() == KeyCode.SHIFT)
                superPredator.getView().setRotate(superPredator.getRotate() + 10000);
        });
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
                            if (random1 < 2) {
                                animal.rotateRight();
                            } else animal.rotateLeft();
                        } else {
                            if (random1 > 2) {
                                animal.rotateLeft();
                            } else animal.rotateRight();
                        }
                    }
                } else {
                    addPlants(new Plants(), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());

                }
                for (Animal animal : animals) {
                    if (animal instanceof Predator && animal.isPregnant()) {
                        if (new Random().nextInt(5) > 2)
                            addPred(new Predator('F', 1, 15, 0), 100, 100);
                        else
                            addPred(new Predator('M', 1, 9, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());

                    }
                    if (animal instanceof Herbivore && animal.isPregnant()) {
                        if (new Random().nextInt(5) > 2)
                            addHerbivore(new Herbivore('M', 1, 50, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
                        else
                            addHerbivore(new Herbivore('F', 1, 100, 0), Math.random() * root.getPrefHeight(), Math.random() * root.getPrefWidth());
                    }
                    break;
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
