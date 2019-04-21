public abstract class Tile {
    public abstract void setOwner(Player owner);

    public abstract String getKind();
    
    public abstract String getName();
    
    public abstract String getOwnerName();
    
    public abstract int getHarga();
    
    public abstract boolean landedMethod(Player p, Turn t);
}