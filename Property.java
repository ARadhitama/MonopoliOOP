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

    public String getKind() {
        return "Property";
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
            p.getProp().add(this);
            this.getOwner().rdcMoney(harga);
            System.out.println(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
            System.out.println("Uang " + p.getName() + " tinggal " + p.getMoney());
        } else {
            System.out.println(p.getName() + "tidak memiliki cukup uang");
        }
    }

    public void bayarRent(Player p){
        int hargaRent = 0;
        if (getType() == 2) {
            if (this.getOwner().countProp(2) == 1) {
                hargaRent = getHarga()/8;
            } else { // 2
                hargaRent = getHarga()/2;
            }
        } else {
            switch (this.getOwner().countProp(3)) {
                case 1:
                    hargaRent = getHarga()/8; 
                    break;
                case 2:
                    hargaRent = getHarga()/4;
                    break;
                case 3:
                    hargaRent = getHarga()/2;
                    break;
                default: //4
                    hargaRent = getHarga();
                    break;
           }
        }
        p.rdcMoney(hargaRent);
        this.getOwner().addMoney(hargaRent);
        System.out.println(p.getName() + " telah membayar sewa " + this.getName() + " kepada " + this.getOwnerName() + " sebanyak " + hargaRent);
    }

    public boolean landedMethod(Player p, String command) {
        if (this.getOwnerName().equals("Tuhan")) {
            if (command.equals("beli")) {
                this.buyProp(p);
            }
        } else {
            this.bayarRent(p);
        }
        return true;
    }
}