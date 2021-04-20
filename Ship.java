public class Ship {
    private String name;
    private final int maxCargo;
    private final int maxFuel;
    private final int maxHealth;
    private int currCargo;
    private int currFuel;
    private int currHealth;
    private Item[] cargo;
    private int cargoCount = 0;

    public Ship(String name, int maxCargo, int maxFuel, int maxHealth,
                int currFuel, int currHealth) {
        this.name = name;
        this.maxCargo = maxCargo;
        this.maxFuel = maxFuel;
        this.maxHealth = maxHealth;
        this.currCargo = 0;
        this.currFuel = currFuel;
        this.currHealth = currHealth;
        cargo = new Item[maxCargo];
    }
    public String getName() {
        return name;
    }
    public int getmaxCargo() {
        return maxCargo;
    }
    public int getmaxFuel() {
        return maxFuel;
    }
    public int getmaxHealth() {
        return maxHealth;
    }
    public int getCurrCargo() {
        return currCargo;
    }
    public int getCurrFuel() {
        return currFuel;
    }
    public int getCurrHealth() {
        return currHealth;
    }
    public Item[] getCargoList() {
        return cargo;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCurrCargo(int currCargo) {
        this.currCargo = currCargo;
    }
    public void setCurrFuel(int currFuel) {
        this.currFuel = currFuel;
    }
    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
    }
    public void setCargoList(Item[] cargo) {
        this.cargo = cargo;
    }
    public Item[] getCargo() {
        return this.cargo;
    }



    public void addCargo(Item item) {
        cargo[cargoCount] = item;
        cargoCount++;
        setCurrCargo(getCurrCargo() + 1);
    }

    public void removeCargo(Item item) {
        Item[] newArray = new Item[cargo.length];
        int count = 0;
        for (int i = 0; i < cargo.length; i++) {
            if (!(cargo[i].equals(item))) {
                newArray[count] = cargo[i];
                count++;
            }
        }
        cargo = newArray;
    }

}