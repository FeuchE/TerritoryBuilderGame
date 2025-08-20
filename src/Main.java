import models.*;
import java.util.*;

// Creating Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my territory builder game!");

        // Declare territory and resource manager here so it's visible everywhere in main()
        Territory territory = null;
        ResourceManager resourceManager = null;

        // Initially set running boolean to true so the create a territory loop runs
        boolean running = true;

        // Creating the territory loop
        while (running) {
            // Asking user to input territory name
            System.out.println("Please enter the name of your territory: ");

            // Save the user input as territory name as a variable
            String territoryName = scanner.nextLine().toLowerCase();
            String capitalisedTerritoryName = territoryName.substring(0, 1).toUpperCase() + territoryName.substring(1);

            // Asking user to input territory type
            System.out.println("Please enter the type of your territory you want to create (kingdom/dynasty): ");

            // Save the user input as territory name as a variable
            String territoryType = scanner.nextLine();
            String capitalisedTerritoryType = territoryType.substring(0, 1).toUpperCase() + territoryType.substring(1);

            // Switch statement to create a new territory based on territory type
            territory = switch (territoryType) {
                case "kingdom" -> new Kingdom(territoryName);
                case "dynasty" -> new Dynasty(territoryName);
                default -> null;
            };

            // IF/ELSE statement to check if user has entered a valid territory type
            if (territory != null) {
                // Reassign resource manager to the new territory
                resourceManager = new ResourceManager();
                // Print message to inform the user that their building has been added
                System.out.println(String.format("%s (%s) has been created!",
                    capitalisedTerritoryName,
                    capitalisedTerritoryType
                ));
                // Reassign the running boolean to false to end the loop
                running = false;                        
            } else {
                System.out.println("You have entered an invalid territory type.");
            }
        }   

        // Initially set running boolean to true so the menu loop runs
        boolean runningMenu = true;

        // Menu loop
        while (runningMenu) {
            // Print menu options
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Building");
            System.out.println("2. Add Villager");
            System.out.println("3. Assign Villager to Building");
            System.out.println("4. Show Territory Structure");
            System.out.println("5. Produce Resources");
            System.out.println("6. Show Resources");
            System.out.println("7. Exit");

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
                    String capitalisedBuildingType = buildingType.substring(0, 1).toUpperCase() + buildingType.substring(1);

                    // Ask user to input building name
                    System.out.print("Please enter the building name: ");

                    // Save building name inputted as a variable
                    String buildingName = scanner.nextLine().toLowerCase();
                    String capitalisedBuildingName = buildingName.substring(0, 1).toUpperCase() + buildingName.substring(1);

                    // Switch statement to create a new building based on building type
                    Building building = switch (buildingType) {
                        case "barracks" -> new Barracks(buildingName);
                        case "forge" -> new Forge(buildingName);
                        case "farm" -> new Farm(buildingName);
                        default -> null;
                    };

                    // IF/ELSE statement to check if user has entered a valid building type
                    if (building != null) {
                        // Call addBuilding function from the territory class
                        territory.addBuilding(building);
                        // Print message to inform the user that their building has been added
                        System.out.println(String.format("%s (%s) has been added!",
                            capitalisedBuildingName,
                            capitalisedBuildingType
                        ));                        
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
                    String capitalisedVillagerType = villagerType.substring(0, 1).toUpperCase() + villagerType.substring(1);

                    // Ask user to input villager name
                    System.out.print("Please enter the villager name: ");

                    // Save villager name inputted as a variable
                    String villagerName = scanner.nextLine().toLowerCase();
                    String capitalisedVillagerName = villagerName.substring(0, 1).toUpperCase() + villagerName.substring(1);

                    // Switch statement to create a new villager based on building type
                    Villager villager = switch (villagerType) {
                        case "knight" -> new Knight(villagerName, 100, 75, 50, 25);
                        case "blacksmith" -> new Blacksmith(villagerName);
                        case "farmer" -> new Farmer(villagerName);
                        default -> null;
                    };

                    // IF/ELSE statement to check if user has entered a valid villager type
                    if (villager != null) {
                        // Call addVillager function from the territory class
                        territory.addVillager(villager);
                        // Print message to inform the user that their villager has been added
                        // IF/ELSE statement to print a different message depending on the type of villager
                        if (villagerType.equals("knight")) {
                            System.out.println(String.format("%s (Knight) [HP: 100, STA: 75, STR: 50, DEF: 25] has been added!",
                                capitalisedVillagerName
                            ));
                        } else {
                            System.out.println(String.format("%s (%s) has been added!",
                                capitalisedVillagerName,
                                capitalisedVillagerType
                            )); 
                        }                                               
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
                    // IF statement to check if the user has created buildings yet
                    if (!territory.noBuildings()) {
                        // Call produceResources function from all building classes in the territory
                        territory.produceResources(resourceManager);
                        // Print message to user to inform them the resources have been produced
                        System.out.println("Resources have been produced!");
                    } else {
                        System.out.println("You have to create a building to produce resources.");
                    }
                    break;

                // Code block executed when user selects option 6
                case 6:
                    // Call printResources function from all building classes in the territory
                    resourceManager.printResources();
                    break;

                // Code block executed when user selects option 7
                case 7:
                    // Return message to user that the game has ended and thank them for playing
                    System.out.println("You have chosen to end the game, thank you for playing, see you next time!");
                    // Reassign the running boolean to false to end the loop
                    runningMenu = false;
                    break;
                
                // Code block executed when user enters an input that is not in the menu list
                default:
                    // Print message to user to explain their error
                    System.out.println("You have entered an invalid option. Please enter a number between 1 and 5.");
            }
        }
    }
}
