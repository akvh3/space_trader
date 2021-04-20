import java.util.ArrayList;

/**
 * The player class, essential class where player gets created with
 * the chosen assigned skills.
 */
public class Player {

    private int skillPoints;
    private int fighterSkill;
    private int merchantSkill;
    private int engineerSkill;
    private int pilotSkill;
    private int credits;
    private String level;
    private Region curRegion = null;
    private Region nextRegion = null;
    private String name;
    private ArrayList<Item> itemList;
    private int karma;

    public Player(int skillPoints, int fSkill,
                  int mSkill, int eSkill, int pSkill, String level, String name) {
        this.skillPoints = skillPoints;
        this.fighterSkill = fSkill;
        this.merchantSkill = mSkill;
        this.engineerSkill = eSkill;
        this.pilotSkill = pSkill;
        this.level = level;
        this.name = name;
        itemList = new ArrayList(10);
        karma = 0;
    }

    //getter methods for instance data
    public String getName() {
        return name;
    }
    public int getFighterSkill() {
        return fighterSkill;
    }
    public int getMerchantSkill() {
        return merchantSkill;
    }
    public int getEngineerSkill() {
        return engineerSkill;
    }
    public int getPilotSkill() {
        return pilotSkill;
    }
    public int getCredits() {
        return credits;
    }
    public int getSkillPoints() {
        return skillPoints;
    }
    public String getLevel() {
        return level;
    }
    public Region getRegion() {
        return curRegion;
    }
    public Region getNextRegion() {
        return nextRegion;
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
    public String getKarmaTitle() {
        if (karma == 0) {
            return "Neutral Karma";
        } else if (karma > 0) {
            return "Good Karma";
        } else {
            return "Bad Karma";
        }
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    //setters for skills
    public void setFighterSkill(int points) {
        fighterSkill = points;
    }
    public void setMerchantSkill(int points) {
        merchantSkill = points;
    }
    public void setEngineerSkill(int points) {
        engineerSkill = points;
    }
    public void setPilotSkill(int points) {
        pilotSkill = points;
    }
    public void setSkillPoints(int points) {
        skillPoints = points;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setRegion(Region region) {
        this.curRegion = region;
    }
    public void setNextRegion(Region region) {
        this.nextRegion = region;
    }
}