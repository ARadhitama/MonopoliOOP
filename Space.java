public class Space extends Tile {
    
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

    }

    public void chanceCard(){

    }
}