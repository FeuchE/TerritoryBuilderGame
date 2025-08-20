package models;

// Creating Farm class within Building class that produces food
public class Farm extends Building implements ResourceProducer {

    // Function to assign the name of a farm
    public Farm(String name) {
        super(name, "farm");
    }

    // Function to produce food
    @Override
    public void produceResources(ResourceManager manager) {
        // Add 10 units of food
        manager.addResource("Food", 10);
    }
}