package models;
import java.util.*;

// Creating Building class
public abstract class Building {
    private String name;
    private List<Villager> assignedVillagers = new ArrayList<>();

    // Function to assign the name of a building
    public Building(String name) {
        this.name = name;
    }

    // Function to return the name of a building
    public String getName() {
        return name;
    }

    // Function to assign a villager to a building
    public void assignVillager(Villager villager) {
        assignedVillagers.add(villager);
        villager.setAssigned(true);
    }

    // Function to return the list of assigned villagers to a building
    public List<Villager> getAssignedVillagers() {
        return assignedVillagers;
    }
}
