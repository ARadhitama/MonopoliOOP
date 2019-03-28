import java.util.List;
import java.util.ArrayList;
// import java.util.arrays;

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
        ownProp = new ArrayList<Property>();
    }

    public int getMoney() {
        return this.money;
    }

    public Boolean getJail () {
        return this.inJail;
    }
    
    public void setJail (boolean inJail) {
        this.inJail = inJail;
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

    public void setPos (int pos) {
        this.pos = pos;
    }

    public String getName () {
        return this.name;
    }

    /*public Property getProp () {
        // return
    }*/

    public void buyProp () {
        // ngurangin money, nambah ownProp
    }

    public void sellProp () {
        // ngurangin ownProp, nambah money
    }

    //ngitung berapa property yang dipunya, sesuai type masukan
    public int countProp(int type){
        /* 1 = Lot
        2 = Utilities
        3 = Railroad*/
        int i = 0;
        for (Property prop : ownProp){
            if (prop.getType() == type) {
                i++;
            }
        }
        return i;
    }
}