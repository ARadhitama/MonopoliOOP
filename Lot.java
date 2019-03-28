import java.util.Scanner;

public class Lot extends Property {
    private int rumah;
    private int kompleks;

    public Lot(String name, int harga, int kompleks){
        super(name, harga, 1);
        setRumah(0);
        setKompleks(kompleks);
    }

    public int getRumah(){
        return this.rumah;
    }

    public void setRumah(int rumah){
        this.rumah = rumah;
    }

    public void addRumah(){
        if (getRumah() == 4){
            System.out.println("Gabisa bang, rumahlu udah 4");
        } else {
            setRumah(getRumah() + 1);
        }
    }

    public int getKompleks(){
        return this.kompleks;
    }

    public void setKompleks(int kompleks){
        this.kompleks = kompleks;
    }

    public void buyProp(Player p){
        /*if (p.getMoney() >= this.getHarga()){
            this.setOwner(p);
            this.getOwner().rdcMoney(harga);
            System.out.println(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
            System.out.println("Uang " + p.getName() + " tinggal " + p.getMoney());
        } else {
            System.out.println(p.getName() + "tidak memiliki cukup uang");
        }*/
    }

    public void bayarRent(Player p){
        /*if ( // komplek ) {
            if (//1rumah) {
                p.rdcMoney(getHarga()/2);
                this.getOwner().addMoney(getHarga()/2);  
            } else if (//2rumah) {
                p.rdcMoney(getHarga());
                this.getOwner().addMoney(getHarga());
            } else if (//3rumah) {
                p.rdcMoney(getHarga()*2);
                this.getOwner().addMoney(getHarga()*2);
            } else {
                p.rdcMoney(getHarga()*4);
                this.getOwner().addMoney(getHarga()*4);
            }
        } else { // ga komplek
            p.rdcMoney(getHarga()/8);
            this.getOwner().addMoney(getHarga()/8);
        }*/
    }
}