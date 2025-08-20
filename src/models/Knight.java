package models;

// Creating Knight class within Villager class
public class Knight extends Villager {
    // Attributes
    private int health;
    private int stamina;
    private int strength;
    private int defense;

    // Function to assign the name and attributes of a knight
    public Knight(String name, int health, int stamina, int strength, int defense) {
        super(name, "knight");
        this.health = health;
        this.stamina = stamina;
        this.strength = strength;
        this.defense = defense;
    }

    // Functions to return attribute values
    public int getHealth() { return health; }
    public int getStamina() { return stamina; }
    public int getStrength() { return strength; }
    public int getDefense() { return defense; }

    @Override
    // Function to print a knights attributes as a string
    public String toString() {
        return String.format("%s (Knight) [HP: %d, STA: %d, STR: %d, DEF: %d]",
            super.getName(),
            health,
            stamina,
            strength,
            defense
        );
    }
}