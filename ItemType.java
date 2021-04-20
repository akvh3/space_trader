import java.util.Random;

/**
 * ItemType enum that decided many different attributes for
 * each item.
 */
public enum ItemType {
    FOOD, MEDICINE, WEAPONS, GOLD, WOOD, COMPUTERS, FUEL, THRUSTERS, OXYGEN, CLOTHES;

    public static ItemType getRandomItem() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
