public class Player {
    private String name;
    private int money;
    private int pos;
    private Boolean inJail;
    
    public Player (String name) {
        this.name = name;
        this.money = 0;
        this.inJail = false;
        this.pos = 0;
    }
}