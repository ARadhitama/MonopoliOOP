import java.util.Random;

public class Space extends Tile {
    Random randomGen = new Random;
    private int sides;

    
    public void startTile(int pos){                                //jika posisi pada startTile 
                                                                   //akan menambahkna 2x uang start
        if (pos == 0){
            money += 400;
        } else {
            money += 200;
        }
    }

    public void jailTile(){
        
    }

    public void freeParkingTile(int pos){
        if (pos == 20) {
            System.out.println("mau mendarat dimana? : "+ )
        } 
    }

    public void goToJailTile(Player P){    
        P.setPos(9);
        P.setJail(true);
    }

    public void communityChestCard(){
        int result = randomGen.nextInt(sides);
        switch(sides) {
            case 
        } 
            
    }

    public void chanceCard(){

    }
}