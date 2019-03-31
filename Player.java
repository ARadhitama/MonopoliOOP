import java.util.List;
import java.util.ArrayList;
// import java.util.arrays;

public class Player {
    private String name;
    private int money;
    private int pos;
    private Boolean inJail;
    private ArrayList<Property> ownProp;
    private ArrayList<Lot> ownLot;
    
    public Player (String name) {
        this.name = name;
        this.money = 200000;
        this.inJail = false;
        this.pos = 0;
        ownProp = new ArrayList<Property>();
        ownLot = new ArrayList<Lot>();
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
        this.pos = pos % 38;
    }

    public String getName () {
        return this.name;
    }

    public ArrayList<Property> getProp () {
        return ownProp;
    }

    public ArrayList<Lot> getLot () {
        return ownLot;
    }

    /*public void sellProp () {
        // ngurangin ownProp, nambah money
    }*/

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

    // cek komplek 
    public boolean cekKompleks (int komp) {
        int i = 0;
        for (Lot lot : ownLot){
            if (lot.getKompleks() == komp) {
                i++;
            }
        }
        if ((komp == 1) || (komp == 8)) {
            if (i==2) {
                return true;
            } else {
                return false;
            }
        } else {
            if (i==3) {
                return true;
            }
            else {
                return false;
            }
        }  
    }
}