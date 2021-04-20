import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An enum for tech levels for each region.
 */
public enum TechLevel {
    PREAG,
    MEDIEVAL,
    AGRICULTURE,
    RENAISSANCE,
    INDUSTRIAL,
    MODERN,
    FUTURISTIC;

    public static TechLevel randomTechLevel() {
        Random techLevel = new Random();
        return values()[techLevel.nextInt(values().length)];
    }

    public static int getIntegerInRangeMultipleOf(int minInclusive,
                                                  int maxInclusive, int multiplier) {
        int minMultiplier = minInclusive
                <= multiplier ? 1 : (int) Math.ceil((double) minInclusive / multiplier);
        int maxMultiplier = maxInclusive / multiplier;
        return multiplier * ThreadLocalRandom.current().nextInt(minMultiplier, maxMultiplier + 1);
    }
}