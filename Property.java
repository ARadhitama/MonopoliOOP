import java.util.Scanner;

public class Property extends Tile {
    private String name;
    private Player owner = null;
    private int harga;
    private int type;
        /* 1 = Lot
        2 = Utilities
        3 = Railroad*/
    public Property(String name, int harga, int type){
        // super(pos);
        setName(name);
        setHarga(harga);
        setType(type);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Player getOwner(){
        return this.owner;
    }

    public String getOwnerName() {
        if (this.owner != null) {
            return this.owner.getName();
        } else {
            return "Tuhan";
        }
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public int getHarga(){
        return this.harga;
    }

    public void setHarga(int harga){
        this.harga = harga;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }

    public void buyProp(Player p){
        if (p.getMoney() >= this.getHarga()){
            this.setOwner(p);
            this.getOwner().rdcMoney(harga);
            System.out.println(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
            System.out.println("Uang " + p.getName() + " tinggal " + p.getMoney());
        } else {
            System.out.println(p.getName() + "tidak memiliki cukup uang");
        }
    }

    public void bayarRent(Player p){
        if (getType() == 2) {
            if (this.getOwner().countProp(2) == 1) {
                p.rdcMoney(getHarga()/8);
                this.getOwner().addMoney(getHarga()/8);
            } else { // 2
                p.rdcMoney(getHarga()/2);
                this.getOwner().addMoney(getHarga()/2);
            }
       } else {
            if (this.getOwner().countProp(3) == 1) {
                p.rdcMoney(getHarga()/8);
                this.getOwner().addMoney(getHarga()/8);
            } else if (this.getOwner().countProp(3) == 2) {
                p.rdcMoney(getHarga()/4);
                this.getOwner().addMoney(getHarga()/4);
            } else if (this.getOwner().countProp(3) == 3) {
                p.rdcMoney(getHarga()/2);
                this.getOwner().addMoney(getHarga()/2);
            } else { //4
                p.rdcMoney(getHarga());
                this.getOwner().addMoney(getHarga());
            }
       }
    }

    public boolean landedMethod(Player p, String command) {
        if (this.getOwnerName().equals("Tuhan")) {
            if (command.equals("Beli")) {
                this.buyProp(p);
            } else if (command.equals("Diam")) {
                // auto keluar
            } else if (command.isEmpty()) {
            
            }
        } else {
            this.bayarRent(p);
        }
        return true;
    }
}