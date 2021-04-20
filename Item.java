/**
 * The item class where each item in the market
 * gets created.
 */
public class Item {

    //Item attributes
    private String name;
    private double cost;
    private ItemType itemType;
    private double weight;
    private Player player;

    public Item(String name, double cost, ItemType itemType, double weight) {
        this.name = name;
        this.cost = cost;
        this.itemType = itemType;
        this.weight = weight;
    }

    @SuppressWarnings("checkstyle:LineLength")
    public Item(ItemType itemType, double weight, Player player) {
        this.itemType = itemType;
        this.weight = weight;
        this.player = player;
        switch (itemType) {
        case COMPUTERS: cost = 100 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                - (10 * player.getKarma());
            name = "Computers";
            break;
        case FOOD:
            cost = 30 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (5 * player.getKarma());
            name = "Food";
            break;
        case FUEL:
            cost = 150 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (15 * player.getKarma());
            name = "Fuel";
            break;
        case GOLD:
            cost = 200 * player.getRegion().priceMultiplier(player.getMerchantSkill()
                    - (20 * player.getKarma()));
            name = "Gold";
            break;
        case WOOD:
            cost = 40 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (5 * player.getKarma());
            name = "Wood";
            break;
        case OXYGEN:
            cost = 60 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (5 * player.getKarma());
            name = "Oxygen";
            break;
        case CLOTHES:
            cost = 30 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (5 * player.getKarma());
            name = "Clothes";
            break;
        case WEAPONS:
            cost = 175 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (17 * player.getKarma());
            name = "Weapons";
            break;
        case MEDICINE:
            cost = 120 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (14 * player.getKarma());
            name = "Medicine";
            break;
        case THRUSTERS:
            cost = 190 * player.getRegion().priceMultiplier(player.getMerchantSkill())
                    - (19 * player.getKarma());
            name = "Thrusters";
            break;
        default:
            break;
        }
    }

    //getters
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public double getWeight() {
        return weight;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}