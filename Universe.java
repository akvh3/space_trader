import java.util.Random;

/**
 * Where the entire game is stored and created.
 */
public class Universe {
    private static Universe universeInstance = null;
    private static Region[] regionList = new Region[10];

    public static Universe getUniverseInstance() {
        if (universeInstance == null) {
            universeInstance = new Universe();
        }
        return universeInstance;
    }

    public static Region[] getRegionList() {
        return regionList;
    }

    public static void restart() {
        universeInstance = new Universe();
    }

    private Universe() {
        Random rand = new Random();
        int uni = rand.nextInt(9);
        String[] checkList = new String[10];
        for (int i = 0; i < checkList.length; i++) {
            if (i == uni) {
                checkList[i] = "y";
            } else {
                checkList[i] = "n";
            }
        }
        regionList[0] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Tundra", checkList[0]);
        regionList[1] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Federation", checkList[1]);
        regionList[2] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Citadel", checkList[2]);
        regionList[3] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Hub", checkList[3]);
        regionList[4] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Reach", checkList[4]);
        regionList[5] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Boneyard", checkList[5]);
        regionList[6] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Aurora", checkList[6]);
        regionList[7] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Rift", checkList[7]);
        regionList[8] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Outskirts", checkList[8]);
        regionList[9] = new Region(TechLevel.getIntegerInRangeMultipleOf(5, 1400, 55),
                TechLevel.getIntegerInRangeMultipleOf(5, 850, 55),
                TechLevel.randomTechLevel(), "The Dark Zone", checkList[9]);

    }
}