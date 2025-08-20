package models;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<String, Integer> resources = new HashMap<>();

    public ResourceManager() {
        resources.put("Weapons", 0);
        resources.put("Food", 0);
    }

    // Function to add resources
    public void addResource(String type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }

    // Function to return the amount of resources
    public int getResource(String type) {
        return resources.getOrDefault(type, 0);
    }

    // Function to print the quantities of resources to the user
    public void printResources() {
        System.out.println("\n--- Resources ---");
        // For loop to print the value of each resource
        for (Map.Entry<String, Integer> entry : resources.entrySet()) {
            System.out.println(String.format("%s: %s",
                entry.getKey(),
                entry.getValue()
            ));
        }
    }
}
