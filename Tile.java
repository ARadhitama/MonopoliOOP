public abstract class Tile {
    private int pos;
    private Player landedP;

    public int getPos(){
        return this.pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public Player getLandedP(){
        return this.landedP;
    }

    public void setLandedP(Player p){
        this.landedP = p;
    }
}