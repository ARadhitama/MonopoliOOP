public abstract class Tile {
    /*private int pos;    // pake index aja?

    public Tile(int pos){
        setPos(pos);
    }

    public int getPos(){
        return this.pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }*/

    public abstract void setOwner(Player owner);
    public abstract String getKind();
    public abstract String getName();
    public abstract String getOwnerName();
    public abstract int getHarga();
    
    public abstract boolean landedMethod(Player p, Turn t);
}