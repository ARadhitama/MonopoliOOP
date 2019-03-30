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

    public int getKompleks(){
        return this.kompleks;
    }

    public void setKompleks(int kompleks){
        this.kompleks = kompleks;
    }

    public void addRumah(){
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            if (getRumah() == 4){
                System.out.println("Gabisa bang, rumahlu udah 4");
            } else {
                this.getOwner().rdcMoney(this.getHarga());
                setRumah(getRumah() + 1);
            }
        } else {
            System.out.println("ni lot bukan punya lu, pergi sana");
        }
    }

    public void buyProp(Player p){
        if (this.getOwnerName().equals(p.getName())){
            System.out.println("Udah lu beli");
        } else if (this.getOwner() == null) {
            if (p.getMoney() >= this.getHarga()){
                this.setOwner(p);
                this.getOwner().rdcMoney(this.getHarga());
                System.out.println(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
                System.out.println("Uang " + p.getName() + " tinggal " + p.getMoney());
            } else {
                System.out.println(p.getName() + "tidak memiliki cukup uang");
            }
        } else {
           System.out.println("Udah punya orang lain");
        }
    }

    public void bayarRent(Player p){
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            switch (this.getRumah()) {
                case 1:
                    p.rdcMoney(getHarga()/2);
                    this.getOwner().addMoney(getHarga()/2);
                    break;
                case 2:
                    p.rdcMoney(getHarga());
                    this.getOwner().addMoney(getHarga());
                    break;
                case 3:
                    p.rdcMoney(getHarga()*2);
                    this.getOwner().addMoney(getHarga()*2);
                    break;
                case 4:
                    p.rdcMoney(getHarga()*4);
                    this.getOwner().addMoney(getHarga()*4);
                    break;
            }
        } else { // ga komplek
            p.rdcMoney(getHarga()/8);
            this.getOwner().addMoney(getHarga()/8);
        }
    }
}