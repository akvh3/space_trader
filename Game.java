import java.util.concurrent.ThreadLocalRandom;

/**
 * The class that contains the method to actually start the game.
 */
public class Game {

    private String difficulty;
    private String[] namesList = new String[10];
    private Player player;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String r6;
    private String r7;
    private String r8;
    private String r9;
    private String r10;

    public Game(Player player) {
        this.player = player;
        difficulty = player.getLevel();
    }

    public void startGame() {
        if (player.getLevel().equals("Easy")) {
            player.setCredits(1000);
        } else if (player.getLevel().equals("Medium")) {
            player.setCredits(500);
        } else if (player.getLevel().equals("Hard")) {
            player.setCredits(100);
        }
        Universe.getUniverseInstance();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
        Region[] rList = Universe.getRegionList();
        player.setRegion(rList[randomNum]);
        namesList[0] = rList[0].getName();
        namesList[1] = rList[1].getName();
        namesList[2] = rList[2].getName();
        namesList[3] = rList[3].getName();
        namesList[4] = rList[4].getName();
        namesList[5] = rList[5].getName();
        namesList[6] = rList[6].getName();
        namesList[7] = rList[7].getName();
        namesList[8] = rList[8].getName();
        namesList[9] = rList[9].getName();
    }
}
