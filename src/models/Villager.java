package models;

// Creating Villager class
public abstract class Villager {
    private String name;
    private String type;
    private boolean assigned = false;

    // Function to assign the name and type of a villager
    public Villager(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Function to return the name of a villager
    public String getName() {
        String capitalisedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalisedName;
    }

    // Function to return the type of a villager
    public String getType() {
        String capitalisedType = type.substring(0, 1).toUpperCase() + type.substring(1);
        return capitalisedType;
    }

    // Function to check if a villager is currently assigned to a building
    public boolean isAssigned() {
        return assigned;
    }

    // Function to set a villager as assigned
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
