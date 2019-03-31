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
        //cek punya komplek atau belum
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            if (getRumah() == 4){
                System.out.println("Gabisa bang, rumahlu udah 4");
            } else {
                this.getOwner().rdcMoney(this.getHarga());
                setRumah(getRumah() + 1);
                System.out.println("Rumah " + this.getOwnerName() + " di " + this.getName() + " ada " + this.getRumah());
            }
        } else {
            System.out.println("blom punya satu komplek, beli dulu sana");
        }
    }

    public void buyProp(Player p){
        if (this.getOwnerName().equals(p.getName())){
            System.out.println("Udah lu beli");
        } else if (this.getOwner() == null) {
            if (p.getMoney() >= this.getHarga()){
                this.setOwner(p);
                p.getProp().add(this);
                p.getLot().add(this);
                this.getOwner().rdcMoney(this.getHarga());
                System.out.println(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
                System.out.println("Uang " + p.getName() + " tinggal " + p.getMoney());
            } else {
                System.out.println(p.getName() + " tidak memiliki cukup uang");
            }
        } else {
           System.out.println("Udah punya orang lain");
        }
    }

    public void bayarRent(Player p){
        int hargaRent = 0;
        //System.out.println(p.getLot().get(0).getName());
        System.out.println(this.getOwner().cekKompleks(this.getKompleks()));
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            switch (this.getRumah()) {
                case 0:
                    hargaRent = this.getHarga()/4;
                    break;
                case 1:
                    hargaRent = this.getHarga()/2;
                    break;
                case 2:
                    hargaRent = this.getHarga();
                    break;
                case 3:
                    hargaRent = this.getHarga()*2;
                    break;
                case 4:
                    hargaRent = this.getHarga()*4;
                    break;
            }
        } else { // ga komplek
            hargaRent = this.getHarga()/8;
        }
        p.rdcMoney(hargaRent);
        this.getOwner().addMoney(hargaRent);
        System.out.println(p.getName() + " telah membayar sewa " + this.getName() + " kepada " + this.getOwnerName() + " sebanyak " + hargaRent);
    }

    public boolean landedMethod(Player p, String command) {
        if (command.equals("beli")) {
            if (this.getOwnerName().equals("Tuhan")) {
                this.buyProp(p);
            } else if (this.getOwnerName().equals(p.getName())){
                this.addRumah();
            }
        } else {
            this.bayarRent(p);
        }
        return true;
    }
}