package models;

// Creating Forge class within Building class that produces weapons
public class Forge extends Building implements ResourceProducer {

    // Function to assign the name of a forge
    public Forge(String name) {
        super(name, "forge");
    }

    // Function to produce weapons
    @Override
    public void produceResources(ResourceManager manager) {
        // Add 1 unit of weapons
        manager.addResource("Weapons", 1);
    }
}
