public abstract class Property extends Tile {
    private String name;
    private Player owner = null;
    private int harga;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Player getOwner(){
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

    public Property(int pos, String name, int harga){
        super(pos);
        setName(name);
        setHarga(harga);
    }

    public abstract void beli();
}