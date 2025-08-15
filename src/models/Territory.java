package models;
import java.util.*;

// Creating Territory class
public class Territory {
    private String name;
    private List<Building> buildings = new ArrayList<>();
    private List<Villager> villagers = new ArrayList<>();

    public Territory(String name) {
        this.name = name;
    }

    // Function to add a building to a territory
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    // Function to add a villager to a territory
    public void addVillager(Villager villager) {
        villagers.add(villager);
    }

    // Function to assign a villager to a building
    public void assignVillagerToBuilding(Scanner scanner) {
        // IF statement to handle error of no villagers or buildings
        if (villagers.isEmpty() || buildings.isEmpty()) {
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
            System.out.println((i + 1) + ". " + unassignedVillagers.get(i).getName() + " (" + unassignedVillagers.get(i).getType() + ")");
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

        System.out.println("\nAvailable Buildings: ");
        // For loop to print all available buildings
        for (int i = 0; i < buildings.size(); i++) {
            System.out.println((i + 1) + ". " + buildings.get(i).getName());
        }

        System.out.print("Choose building number: ");
        // Save selected building in a variable
        int buildingChoice;
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

        // Assign chosenVillager to the villager selected by the user
        Villager chosenVillager = unassignedVillagers.get(villagerChoice);

        // Assign chosenBuilding to the building selected by the user
        Building chosenBuilding = buildings.get(buildingChoice);

        // Assign villager to the building
        chosenBuilding.assignVillager(chosenVillager);

        // Print message to user informing them that the villager is assigned to the building
        System.out.println(chosenVillager.getName() + " has been assigned to " + chosenBuilding.getName());
    }

    // Function to print territory structure
    public void printStructure() {
        // Print title
        System.out.println("\n--- Territory Structure ---");
        // Print territory name
        System.out.println("Territory: " + name);

        // For loop printing buildings within the territory
        for (Building b : buildings) {
            System.out.println("  Building: " + b.getName());
            // For loop printing villagers within the building
            for (Villager v : b.getAssignedVillagers()) {
                System.out.println("    Villager: " + v.getName() + " (" + v.getType() + ")");
            }
        }
    }
}