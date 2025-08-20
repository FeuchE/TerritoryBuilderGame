package models;
import java.util.*;

// Creating Territory class
public class Territory {
    private String name;
    private String type;
    private List<Building> buildings = new ArrayList<>();
    private List<Villager> villagers = new ArrayList<>();

    public Territory(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Function to return the name of a territory
    public String getName() {
        String capitalisedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return capitalisedName;
    }

    // Function to return the type of a territory
    public String getType() {
        String capitalisedType = type.substring(0, 1).toUpperCase() + type.substring(1);
        return capitalisedType;
    }
    // Function to add a building to a territory
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // Function to add a villager to a territory
    public void addVillager(Villager villager) {
        villagers.add(villager);
    }

    // Function to check if a territory has buildings
    public boolean noBuildings() {
        return buildings.isEmpty();
    }

    // Function to check if a territory has villagers
    public boolean noVillagers() {
        return villagers.isEmpty();
    }

    // Save selected building in a variable
    int buildingChoice;

    // Function to select a building
    public void selectBuilding(Scanner scanner) {
        System.out.println("\nAvailable Buildings: ");
        // For loop to print all available buildings
        for (int i = 0; i < buildings.size(); i++) {
            System.out.println(String.format("%d. %s (%s)",
                i + 1,
                buildings.get(i).getName(),
                buildings.get(i).getType()
            ));
        }

        System.out.print("Choose building number: ");
        try {
            // Save selected building in a variable
            buildingChoice = Integer.parseInt(scanner.nextLine()) - 1;
            // Return error message if an invalid number is entered
            if (buildingChoice < 0 || buildingChoice >= buildings.size()) {
                System.out.println("You have entered an invalid building number.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid input. Please enter a number.");
            return;
        }
    }

    // Function to assign a villager to a building
    public void assignVillagerToBuilding(Scanner scanner) {
        // IF statement to handle error of no villagers or buildings
        if (noVillagers() || noBuildings()) {
            System.out.println("You need both buildings and villagers.");
            return;
        }

        // For loop to filter for unassigned villagers
        List<Villager> unassignedVillagers = new ArrayList<>();
        for (Villager v : villagers) {
            // IF statement to check if a villager is assigned to a building already
            if (!v.isAssigned()) {
                // Add unassigned villagers to the list
                unassignedVillagers.add(v);
            }
        }

        // IF statement to print error message if all villagers are currently assigned to buildings
        if (unassignedVillagers.isEmpty()) {
            System.out.println("All villagers are already assigned to buildings. Please chose another option.");
            return;
        }

        System.out.println("\nAvailable Villagers: ");
        // For loop to print all available villagers
        for (int i = 0; i < unassignedVillagers.size(); i++) {
            // IF/ELSE statement to print a different message depending on the type of villager 
            if (unassignedVillagers.get(i).getType().equals("knight")) {
                System.out.println(String.format("%d. %s",
                    i + 1,
                    unassignedVillagers.get(i).toString()
                ));
            } else {
                System.out.println(String.format("%d. %s (%s)",
                    i + 1, 
                    unassignedVillagers.get(i).getName(),
                    unassignedVillagers.get(i).getType()
                ));
            }
            
        }

        System.out.print("Choose villager number: ");
        int villagerChoice;
        try {
            // Save selected villager in a variable
            villagerChoice = Integer.parseInt(scanner.nextLine()) - 1;
            // Return error message if an invalid number is entered
            if (villagerChoice < 0 || villagerChoice >= unassignedVillagers.size()) {
                System.out.println("You have entered an invalid villager number.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid input. Please enter a number.");
            return;
        }

        selectBuilding(scanner);

        // Assign chosenVillager to the villager selected by the user
        Villager chosenVillager = unassignedVillagers.get(villagerChoice);

        // Assign chosenBuilding to the building selected by the user
        Building chosenBuilding = buildings.get(buildingChoice);

        // Assign villager to the building
        chosenBuilding.assignVillager(chosenVillager);

        // Print message to user informing them that the villager is assigned to the building
        System.out.println(String.format("%s has been assigned to %s",
            chosenVillager.getName(),
            chosenBuilding.getName()
        ));
    }

    // Function to print territory structure
    public void printStructure() {
        // Print title
        System.out.println("\n--- Territory Structure ---");
        // Print territory name
        System.out.println(String.format("Territory: %s (%s)",
            getName(),
            getType()
        ));

        // For loop printing buildings within the territory
        for (Building b : buildings) {
            System.out.println(String.format("  Building: %s (%s)",
                b.getName(),
                b.getType()
            ));
            // For loop printing villagers within the building
            for (Villager v : b.getAssignedVillagers()) {
                // IF/ELSE statement to print a different message depending on the type of villager
                if (v.getType().equals("knight")) {
                    System.out.println(String.format("    Villager: %s",
                        v.toString()
                    ));
                } else {
                    System.out.println(String.format("    Villager: %s (%s)",
                        v.getName(),
                        v.getType()
                    ));
                }
                
            }
        }
    }

    // Function to tell all buildings to produce resources
    public void produceResources(ResourceManager manager) {
        for (Building building : buildings) {
            if (building instanceof ResourceProducer producer) {
                producer.produceResources(manager);
            }
        }
    }

    // Function to delete a building from a territory
    public void removeBuilding(Scanner scanner) {
        // IF statement to check if there are buildings using noBuildings function
        if (noBuildings()) {
            System.out.println("There are no buildings to delete.");
            return;
        }

        // Call select building function
        selectBuilding(scanner);

        // Record selected building before removing
        Building removed = buildings.get(buildingChoice);

        // For loop to unassign villagers from that building
        for (Villager v : removed.getAssignedVillagers()) {
            v.setAssigned(false);
        }

        // Remove the building from the territory
        buildings.remove(buildingChoice);

        // Print message to user to inform them the building has been deleted
        System.out.println(String.format("%s (%s) has been deleted. All assigned villagers are now unassigned.",
            removed.getName(),
            removed.getType()
        ));
    }

    // Function to delete a villager from a territory
    public void removeVillager(Scanner scanner) {
        // IF statement to check if there are villagers using noBuildings function
        if (noVillagers()) {
            System.out.println("There are no villagers to delete.");
            return;
        }

        System.out.println("\nVillagers: ");
        // For loop to print all villagers
        for (int i = 0; i < villagers.size(); i++) {
            // IF/ELSE statement to print a different message depending on the type of villager 
            if (villagers.get(i).getType().equals("knight")) {
                System.out.println(String.format("%d. %s",
                    i + 1,
                    villagers.get(i).toString()
                ));
            } else {
                System.out.println(String.format("%d. %s (%s)",
                    i + 1, 
                    villagers.get(i).getName(),
                    villagers.get(i).getType()
                ));
            }
            
        }

        System.out.print("Choose villager number: ");
        int villagerChoice;
        try {
            // Save selected villager in a variable
            villagerChoice = Integer.parseInt(scanner.nextLine()) - 1;
            // Return error message if an invalid number is entered
            if (villagerChoice < 0 || villagerChoice >= villagers.size()) {
                System.out.println("You have entered an invalid villager number.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid input. Please enter a number.");
            return;
        }

        // Record selected villager before removing
        Villager removed = villagers.get(villagerChoice);

        // Unassign villager from their building (if assigned) using for loop and IF statement
        for (Building b : buildings) {
            if (b.getAssignedVillagers().contains(removed)) {
                b.unassignVillager(removed);
                break;
            }
        }

        removed.setAssigned(false);

        // Remove the villager from the territory
        villagers.remove(villagerChoice);

        // Print message to user to inform them the villager has been deleted
        System.out.println(String.format("%s (%s) has been deleted.",
            removed.getName(),
            removed.getType()
        ));
    }
}