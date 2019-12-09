import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class Animal extends Moving {

    private boolean alive = true;
    private char sex;
    private int age;
    protected String uuid;
    private int fertilnost;
    private double size;


    public int getFertilnost() {
        return fertilnost;
    }

    public void setFertilnost(int fertilnost) {
        this.fertilnost = fertilnost;
    }

    public void incrementFertilnost() {
        this.fertilnost++;
    }



    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isDead() {
        return !alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(uuid, animal.uuid);
    }


    public Animal(Node view, char sex, int age, int fertilnost) {
        this.setSex(sex);
        this.setAge(age);
        this.setView(view);
        this.setFertilnost(fertilnost);


    }

    public void growUp() {
        this.getView().resize(50, 50);
    }

    public boolean isColliding(Animal other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}