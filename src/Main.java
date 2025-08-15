import models.*;
import java.util.*;

// Creating Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my territory builder game!");

        // Asking user to input territory name
        System.out.println("Please enter the name of your territory: ");

        // Save the user input as territory name as a variable
        String territoryName = scanner.nextLine();

        // Create a new territory using the saved name
        Territory territory = new Territory(territoryName);

        // Initially set running boolean to true to the menu loop runs
        boolean running = true;

        // Menu loop
        while (running) {
            // Print menu options
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Building");
            System.out.println("2. Add Villager");
            System.out.println("3. Assign Villager to Building");
            System.out.println("4. Show Territory Structure");
            System.out.println("5. Exit");

            // Ask user to select an option from the menu
            System.out.print("Please choose an option: ");

            // Save the users selected option as a variable
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Print message to user to explain their error
                System.out.println("You have entered an invalid option. Please enter a number between 1 and 5.");
                continue;
            }

            // Switch statement
            switch (choice) {
                // Code block executed when user selects option 1
                case 1:
                    // Ask user to input building type
                    System.out.print("Please enter the building type (barracks/forge/farm): ");

                    // Save building type inputted as a variable
                    String buildingType = scanner.nextLine().toLowerCase();

                    // Ask user to input building name
                    System.out.print("Please enter the building name: ");

                    // Save building name inputted as a variable
                    String buildingName = scanner.nextLine().toLowerCase();

                    // Switch statement to create a new building based on building type
                    Building building = switch (buildingType) {
                        case "barracks" -> new Barracks(buildingName);
                        case "forge" -> new Forge(buildingName);
                        case "farm" -> new Farm(buildingName);
                        default -> null;
                    };

                    // IF statement to check if user has entered a valid building type
                    if (building != null) {
                        // Call addBuilding function from the territory class
                        territory.addBuilding(building);
                        // Print message to inform the user that their building has been added
                        System.out.println(buildingName + " has been added!");                        
                    } else {
                        System.out.println("You have entered an invalid building type.");
                    }
                    break;
                
                // Code block executed when user selects option 2
                case 2:
                    // Ask user to input villager type
                    System.out.print("Please enter the villager type (knight/blacksmith/farmer): ");

                    // Save villager type inputted as a variable
                    String villagerType = scanner.nextLine().toLowerCase();

                    // Ask user to input villager name
                    System.out.print("Please enter the villager name: ");

                    // Save villager name inputted as a variable
                    String villagerName = scanner.nextLine().toLowerCase();

                    // Switch statement to create a new villager based on building type
                    Villager villager = switch (villagerType) {
                        case "knight" -> new Knight(villagerName, 100, 75, 50, 25);
                        case "blacksmith" -> new Blacksmith(villagerName);
                        case "farmer" -> new Farmer(villagerName);
                        default -> null;
                    };

                    // IF statement to check if user has entered a valid villager type
                    if (villager != null) {
                        // Call addVillager function from the territory class
                        territory.addVillager(villager);
                        // Print message to inform the user that their villager has been added
                        System.out.println(villagerName + " has been added!");                        
                    } else {
                        // Print message to user to explain their error
                        System.out.println("You have entered an invalid villager type.");
                    }
                    break;

                // Code block executed when user selects option 3
                case 3:
                    // Call assignVillagerToBuilding function from the territory class
                    territory.assignVillagerToBuilding(scanner);
                    break;
                
                // Code block executed when user selects option 4
                case 4:
                    // Call printStructure function from the territory class
                    territory.printStructure();
                    break;
                
                // Code block executed when user selects option 5
                case 5:
                    // Return message to user that the game has ended and thank them for playing
                    System.out.println("You have chosen to end the game, thank you for playing!");
                    // Reassign the running boolean to false to end the loop
                    running = false;
                    break;
                
                // Code block executed when user enters an input that is not in the menu list
                default:
                    // Print message to user to explain their error
                    System.out.println("You have entered an invalid option. Please enter a number between 1 and 5.");
            }
        }
    }
}
