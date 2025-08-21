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
                System.out.println("Error: You have entered an invalid building number. Please try again.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("Error: You have entered an invalid input. Please enter a number.");
            return;
        }
    }

    // Function to assign a villager to a building
    public void assignVillagerToBuilding(Scanner scanner) {
        // IF statement to handle error of no villagers or buildings
        if (noVillagers() || noBuildings()) {
            System.out.println("Error: You need both buildings and villagers.");
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
            System.out.println("Error: All villagers are already assigned to buildings. Please chose another option.");
            return;
        }

        System.out.println("\nAvailable Villagers: ");
        // For loop to print all available villagers
        for (int i = 0; i < unassignedVillagers.size(); i++) {
            Villager v = unassignedVillagers.get(i);
            // IF/ELSE statement to print a different message depending on the type of villager 
            if (v instanceof Knight knight) {
                System.out.println(String.format("%d. %s (Knight) [HP: %d, STA: %d, STR: %d, DEF: %d]",
                    i + 1,        
                    knight.getName(),
                    knight.getHealth(),
                    knight.getStamina(),
                    knight.getStrength(),
                    knight.getDefense()
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
                System.out.println("Error: You have entered an invalid villager number. Please try again.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("Error: You have entered an invalid input. Please enter a number.");
            return;
        }

        selectBuilding(scanner);

        // Assign chosenVillager to the villager selected by the user
        Villager chosenVillager = unassignedVillagers.get(villagerChoice);

        // Assign chosenBuilding to the building selected by the user
        Building chosenBuilding = buildings.get(buildingChoice);

        // Check if villager type is allowed in this building type
        if (isValidAssignment(chosenVillager, chosenBuilding)) {
            chosenBuilding.assignVillager(chosenVillager);
            System.out.println(String.format("%s (%s) has been assigned to %s (%s)",
                chosenVillager.getName(),
                chosenVillager.getType(),
                chosenBuilding.getName(),
                chosenBuilding.getType()
            ));
        } else {
            System.out.println(String.format("Error: A %s cannot be assigned to a %s.",
                chosenVillager.getType(),
                chosenBuilding.getType()
            ));
        }
    }
    
    // Function to check if a villager type is assigned to the correct building type
    private boolean isValidAssignment(Villager villager, Building building) {
        String villagerType = villager.getType().toLowerCase();
        String buildingType = building.getType().toLowerCase();

        return switch (villagerType) {
            case "farmer" -> buildingType.equals("farm");
            case "blacksmith" -> buildingType.equals("forge");
            case "knight" -> buildingType.equals("barracks");
            default -> false;
        };
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
                if (v instanceof Knight knight) {
                    System.out.println(String.format("    Villager: %s (Knight) [HP: %d, STA: %d, STR: %d, DEF: %d]",
                        v.getName(),
                        knight.getHealth(),
                        knight.getStamina(),
                        knight.getStrength(),
                        knight.getDefense()
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

    // Function to modify a knight's stats
    public void modifyKnightStats(Scanner scanner) {
        // Filter knights from villagers into a new list
        List<Knight> knights = new ArrayList<>();
        for (Villager v : villagers) {
            if (v instanceof Knight knight) {
                knights.add(knight);
            }
        }

        // Display error message if there are no knights
        if (knights.isEmpty()) {
            System.out.println("Error: There are no knights in this territory.");
            return;
        }

        // Display list of knights
        System.out.println("\nAvailable Knights:");
        for (int i = 0; i < knights.size(); i++) {
            Knight k = knights.get(i);
            System.out.println(String.format("%d. %s (Knight) [HP: %d, STA: %d, STR: %d, DEF: %d]",
                i + 1,
                k.getName(),
                k.getHealth(),
                k.getStamina(),
                k.getStrength(),
                k.getDefense()
            ));
        }

        // Ask user to chose a knight
        System.out.println("Chose knight number: ");
        int choice;
        // Error handling
        try {
            choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice < 0 || choice >= knights.size()) {
                System.out.println("Error: Invalid choice. Please try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a number.");
            return;
        }

        // Store user chose as variable
        Knight chosenKnight = knights.get(choice);

        // While loop until valid stats are entered
        while (true) {
            try {
                System.out.println("\nA knight's total stats cannot exceed 100");
                System.out.println("\nEnter new stats for " + chosenKnight.getName() + ":");

                System.out.print("Health (" + chosenKnight.getHealth() + "): ");
                int newHealth = Integer.parseInt(scanner.nextLine());

                System.out.print("Stamina (" + chosenKnight.getStamina() + "): ");
                int newStamina = Integer.parseInt(scanner.nextLine());

                System.out.print("Strength (" + chosenKnight.getStrength() + "): ");
                int newStrength = Integer.parseInt(scanner.nextLine());

                System.out.print("Defense (" + chosenKnight.getDefense() + "): ");
                int newDefense = Integer.parseInt(scanner.nextLine());

                int total = newHealth + newStamina + newStrength + newDefense;

                // IF statement to check if total new stats exceed 100
                if (total > 100) {
                    System.out.println("Error: Total stats cannot exceed 100. Current total = " + total + ". Please try again.");
                    continue;
                }

                // If user input is valid then update knight stats
                chosenKnight.setHealth(newHealth);
                chosenKnight.setStamina(newStamina);                
                chosenKnight.setStrength(newStrength);                
                chosenKnight.setDefense(newDefense);

                // Print message to inform user stats updated successfully
                System.out.println("Knight stats updated successfully!");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter valid numbers for stats.");
            }
        }
    }

    // Function to delete a building from a territory
    public void removeBuilding(Scanner scanner) {
        // IF statement to check if there are buildings using noBuildings function
        if (noBuildings()) {
            System.out.println("Error: There are no buildings to delete.");
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
            System.out.println("Error: There are no villagers to delete.");
            return;
        }

        System.out.println("\nVillagers: ");
        // For loop to print all villagers
        for (int i = 0; i < villagers.size(); i++) {
            Villager v = villagers.get(i);
            // IF/ELSE statement to print a different message depending on the type of villager 
            if (v instanceof Knight knight) {
                System.out.println(String.format("%d. %s (Knight) [HP: %d, STA: %d, STR: %d, DEF: %d]",
                    i + 1,        
                    knight.getName(),
                    knight.getHealth(),
                    knight.getStamina(),
                    knight.getStrength(),
                    knight.getDefense()
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
                System.out.println("Error: You have entered an invalid villager number. Please try again.");
                return;
            }
        // Return error message if an invalid input format is entered
        } catch (NumberFormatException e) {
            System.out.println("Error: You have entered an invalid input. Please enter a number.");
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