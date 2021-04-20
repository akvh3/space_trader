/**
 * The region class that is used to setup the map.
 */
public class Region {

    //attributes of region
    private int xCoord;
    private int yCoord;
    private TechLevel techLevel;
    private String name;
    private String universe;

    public Region(int xCoord, int yCoord, TechLevel techLevel,
                  String name, String universe) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.techLevel = techLevel;
        this.name = name;
        this.universe = universe;
    }

    public String getName() {
        return this.name;
    }

    public int getXCoord() {
        return this.xCoord;
    }

    public int getyCoord() {
        return this.yCoord;
    }

    public TechLevel getTechLevel() {
        return this.techLevel;
    }

    public double priceMultiplier(int skill) {
        int xCenter = 700;
        int yCenter = 425;
        int xDiff = Math.abs(this.xCoord - xCenter);
        int yDiff = Math.abs(this.yCoord - yCenter);
        double diff = xDiff + yDiff;
        if (diff <= 100) {
            return 0.1 + (skill / (double) 20);
        } else if (diff > 100.0 && diff <= 200) {
            return 0.2 + (skill / (double) 20);
        } else if (diff > 200.0 && diff <= 300) {
            return 0.3 + (skill / (double) 20);
        } else if (diff > 300.0 && diff <= 400) {
            return 0.4 + (skill / (double) 20);
        } else if (diff > 400.0 && diff <= 500) {
            return 0.5 + (skill / (double) 20);
        } else if (diff > 500.0 && diff <= 600) {
            return 0.6 + (skill / (double) 20);
        } else if (diff > 600.0 && diff <= 700) {
            return 0.7 + (skill / (double) 20);
        } else if (diff > 700.0 && diff <= 800) {
            return 0.8 + (skill / (double) 20);
        } else {
            return 0.9 + (skill / (double) 20);
        }
    }

    public String checkEndgame() {
        return this.universe;
    }
}