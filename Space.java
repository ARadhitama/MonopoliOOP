import java.util.Random;
import java.util.Scanner;

public class Space extends Tile {
    Random randomGen = new Random();
    private int cards = 5;

    
    public void startTile(Player P){                                //jika posisi pada startTile 
                                                                   //akan menambahkna 2x uang start
        if (P.getPos() == 0){
            P.addMoney(40000);
        } else {
            P.addMoney(20000);
        }
    }

    public void jailTile(){
        
    }

    public void freeParkingTile(Player P){
        Scanner sc = new Scanner(System.in);
        if (P.getPos()==20) {
            System.out.println("mau mendarat dimana? : ");
            P.setPos(sc.nextInt());
        } 
    }

    public void goToJailTile(Player P){    
        P.setPos(10);
        P.setJail(true);
    }

    public void communityChestCard(Player P){
        int result = randomGen.nextInt(cards);
        switch(result) {
            case 1 :
                System.out.println("Melanggar marka jalan, Anda dilempar ke penjara!");
                P.setPos(10);
                P.setJail(true);
                break;
            case 2 :
                System.out.println("Anda menang togel!! dapat Rp.100.000,-");
                P.addMoney(100000);
                break;
            case 3 :
                System.out.println("Vaksinisasi wajib!! bayar Rp.15.000");
                P.rdcMoney(15000);
                break;
            case 4 :
                System.out.println("Sekolah itu gak gratis!! ga bayar sekolah RP.50.000 PKI!!!!!");
                P.rdcMoney(15000);
                break;
            case 5 :
                System.out.println("Cie menang olimpiade, selamat anda mendapatkan Rp.50.000");
                P.addMoney(50000);
                break;    
        } 
            
    }

    public void chanceCard(Player P){
        int result = randomGen.nextInt(cards);
        switch(result) {
            case 1 : 
                System.out.println("Keciduk polisi, hiya hiya hiya, korupsi ya?? masuk penjara sini!! jangan korupsi dong");
                P.setPos(10);
                P.setJail(true);
                break;
            case 2 :
                System.out.println("Pergi ke go!! lumayan dapet Rp.40.000");
                P.setPos(0);
                this.startTile(P);
                break;
            case 3 :
                System.out.println("Maju 3 langkah dong");
                P.setPos(P.getPos()+3);
                break;
            case 4 :
                System.out.println("Astaghfirullah menang judi.... tapi gaapa uang monopoli kokga haram");
                P.addMoney(100000);
                break;
            case 5 : 
                System.out.println("Mundur 3 langkah");
                P.setPos(P.getPos()-3);
                break;
        }
    }

    public boolean landedMethod(Player P){
        if (P.getPos() == 0){
            this.startTile(P);
        } else if (P.getPos()==30){
            this.goToJailTile(P);
        } else if (P.getPos()==20){
            this.freeParkingTile(P);
        } else if (P.getPos()==2 || P.getPos()==17 || P.getPos()==32){
            this.communityChestCard(P);
        } else if (P.getPos()==7 || P.getPos()==22 || P.getPos()==36){
            this.chanceCard(P);
        }

        return true;
    }
}