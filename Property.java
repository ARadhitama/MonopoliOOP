import java.util.Scanner;

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

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }

    public boolean landedMethod(Player p) {
        String command;
        Scanner sc = new Scanner(System.in);

        System.out.println("Masukkan commandmu (Beli | Diam)");
        System.out.print(">> ");
        command = sc.next();

        if (command.equals("Beli")) {
            p.buyProp();
            this.setOwner(p);
        } else if (command.equals("Diam")) {

        }

        return true;
    }
}