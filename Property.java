public abstract class Property extends Tile {
    private String name;
    private Player owner;

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

    public Property(String name){
        setName(name);

    }

    public abstract void beli();


}