package models;
import java.util.*;

// Creating Building class
public abstract class Building {
    private String name;
    private String type;
    private List<Villager> assignedVillagers = new ArrayList<>();

    // Function to assign the name of a building
    public Building(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Function to return the name of a building
    public String getName() {
        String capitalisedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalisedName;
    }

    // Function to return the type of a building
    public String getType() {
        String capitalisedType = type.substring(0, 1).toUpperCase() + type.substring(1);
        return capitalisedType;
    }

    // Function to assign a villager to a building
    public void assignVillager(Villager villager) {
        assignedVillagers.add(villager);
        villager.setAssigned(true);
    }

    // Function to unassign a villager from a building
    public void unassignVillager(Villager villager) {
        if (assignedVillagers.remove(villager)) {
            villager.setAssigned(true);
        }        
    }

    // Function to return the list of assigned villagers to a building
    public List<Villager> getAssignedVillagers() {
        return assignedVillagers;
    }
}
