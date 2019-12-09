import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CoordinateHandle extends Thread {

    private List<Predator> herbivores = new ArrayList<>();

    public List<Predator> getPredators() {
        return predators;
    }

    public void setPredators(List<Predator> predators) {
        this.predators = predators;
    }

    private List<Predator> predators = new ArrayList<>();

    public List<Predator> getHerbivores() {
        return herbivores;
    }

    public void setHerbivores(List<Predator> herbivores) {
        this.herbivores = herbivores;
    }

    @Override
    public void run() {
        for (Predator herbivore : getHerbivores())
            for (Predator predator : getPredators()) {
              /*  if (predator.getVelocity().distance(herbivore.getVelocity()) < 5) {

                    TranslateTransition transition = new TranslateTransition();
                    transition.setNode(predator.getView());
                    transition.setDuration(Duration.millis(300));
                    transition.setCycleCount(Animation.INDEFINITE);
                    transition.setFromX(predator.getView().getTranslateX());
                    transition.setFromY(predator.getView().getTranslateY());
                    transition.setToX(herbivore.getView().getTranslateX());
                    transition.setToY(herbivore.getView().getTranslateY());
                    transition.play();
                }*/
            }
    }
}
