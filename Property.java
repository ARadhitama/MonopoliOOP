public abstract class Property extends Tile {
    private String name;
    private Player owner;
    private int harga;

    public String getName(){
        return this.name;
    }

    public void setName(String owner){
        this.owner = owner;
    }

    public String getOwner(){
        return this.owner;
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

    public Property(String name, Player owner, int harga){
        setName(name);
        setOwner(owner);
        setHarga(harga);
    }

    public abstract void beli();


}