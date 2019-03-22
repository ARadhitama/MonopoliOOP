import java.util.arrays;

public class Player {
    private String name;
    private int money;
    private int pos;
    private Boolean inJail;
    private ArrayList<Property> ownProp;
    
    public Player (String name) {
        this.name = name;
        this.money = 0;
        this.inJail = false;
        this.pos = 0;
    }

    public int getMoney() {
        return this.money;
    }

    public Boolean getJail () {
        return this.inJail;
    }

    public void addMoney (int money) {
            this.money = this.money + money;
    }

    public void rdcMoney (int money) {
        this.money = this.money - money;
    }

    public int getPos () {
        return this.pos;
    }

    public int setPos (int pos) {
        this.pos = pos;
    }
}