public class PopulationControl extends WorldApp implements Runnable {


    private Animal firstAnimal, secondAnimal;

    public void setAnimals(Animal firstAnimal, Animal secondAnimal) {
        this.firstAnimal = firstAnimal;
        this.secondAnimal = secondAnimal;
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

    @Override
    public void run() {
        addPred(new Predator('M',1,50,1),0,0);
    }
}
