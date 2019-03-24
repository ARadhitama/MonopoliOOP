public abstract class Tile {
    private int pos;
    private Player landedPlayer;

    public int getPos(){
        return this.pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public Player getLandedPlayer(){
        return this.landedPlayer;
    }

    public void setLandedPlayerNull(){
        this.landedPlayer = null;
    }

    public void setLandedP(Player p){
        this.landedPlayer = p;
    }

    //public abstract void landedMethod(); masih bingung

    public Tile(int pos){
        setPos(pos);
    }
}