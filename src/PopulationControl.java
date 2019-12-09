public class PopulationControl extends Thread {


    private Animal firstAnimal, secondAnimal;

    public void setAnimals(Animal firstAnimal, Animal secondAnimal) {
        this.firstAnimal = firstAnimal;
        this.secondAnimal = secondAnimal;
    }

    @Override
    public synchronized void start() {
        populationControl(getFirstAnimal(), getSecondAnimal());
    }

    private void populationControl(Animal firstAnimal, Animal secondAnimal) {
        if (firstAnimal instanceof Herbivore && secondAnimal instanceof Herbivore) {


        }

    }

    public Animal getFirstAnimal() {
        return firstAnimal;
    }

    public Animal getSecondAnimal() {
        return secondAnimal;
    }
}
