import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private int pos;
    private Boolean inJail;
    private int countJail;
    private ArrayList<Property> ownProp;
    private ArrayList<Lot> ownLot;
    
    public Player(String name) {
        this.name = name;
        this.money = 100000;
        this.inJail = false;
        this.countJail = 0;
        this.pos = 0;
        ownProp = new ArrayList<Property>();
        ownLot = new ArrayList<Lot>();
    }

    public int getMoney() {
        return this.money;
    }

    public Boolean getJail() {
        return this.inJail;
    }
    
    public void setJail(boolean inJail) {
        if (inJail) {
            this.countJail = 3;
        }
        this.inJail = inJail;
    }

    public int getCountJail() {
        return this.countJail;
    }

    public void decrementJail() {
        this.countJail--;
        System.out.println("Di penjara " + this.countJail + " turn lagi yaa~");
    }

    public void addMoney(int money) {
        this.money = this.money + money;
    }

    public void rdcMoney(int money) {
        this.money = this.money - money;
    }

    public int getPos() {
        return this.pos;
    }

    public void setPos(int pos) {
        this.pos = pos % 38;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Property> getProp() {
        return ownProp;
    }

    public ArrayList<Lot> getLot() {
        return ownLot;
    }

    /*public void sellProp () {
        // ngurangin ownProp, nambah money
    }*/

    // Ngitung berapa property yang dipunya, sesuai type masukan
    public int countProp(int type) {
        /* 1 = Lot
        2 = Utilities
        3 = Railroad*/
        if (this.getLot().size() == 0 ) {
            return 0;
        } else {
            int i = 0;
            for (Property prop : ownProp){
                if (prop.getType() == type) {
                    i++;
                }
            }
            return i;
        }
    }

    // Cek komplek 
    public boolean cekKompleks(int komp) {
        if (this.getLot().size() == 0) {
            return false;
        } else {
            int i = 0;
            for (Lot lot : ownLot){
                if (lot.getKompleks() == komp) {
                    i++;
                }
            }
            if ((komp == 1) || (komp == 8)) {
                return i == 2;
            } else {
                return i == 3;
            }
        }  
    }
}