import java.util.Random;
import java.util.Scanner;

public class Space extends Tile {
    private Random randomGen = new Random();
    private int cards = 4;
    private String spacetile;

    public Space(String spacetile){
        this.spacetile = spacetile;
    }
    
    public String getKind() {
        return "Space";
    }

    public String getName() {
        return this.spacetile;
    }

    public String getOwnerName() {
        return "Tuhan";
    }

    public int getHarga() {
        return 0;
    }
    
    public void startTile(Player P){                                //jika posisi pada startTile 
                                                                   //akan menambahkan 2x uang start
        if (P.getPos() == 0){
            P.addMoney(40000);
        } else {
            P.addMoney(20000);
        }
    }

    public void jailTile(){                                       //Tile pada jail
        
    }

    public void freeParkingTile(Player P, String command){                        //Tile freeparking
        Scanner sc = new Scanner(System.in);
        if (P.getPos()==20) {                                       // dapat berjalan ke lot mana saja
            System.out.println("Mau mendarat di mana? (Sebut posisi 0-37) : ");
            P.setPos(sc.nextInt());
            command = "parkirsabeb";                                 //scan daerah yang ingin dituju
        }
        sc.close();
    }

    public void goToJailTile(Player P){                             //Tile Go TO Jail
        P.setPos(10);                                               //set player ke jail
        P.setJail(true);                                            //set jail true
        System.out.println("Anda mendarat penjara");
    }

    public void communityChestCard(Player P){                       //Community chest card tile
        int result = randomGen.nextInt(cards);
        switch(result) {
            case 0 :
                System.out.println("Melanggar marka jalan, Anda dilempar ke penjara!");
                P.setPos(10);
                P.setJail(true);
                break;
            case 1 :
                System.out.println("Anda menang togel!! dapat Rp.100.000,-");
                P.addMoney(100000);
                break;
            case 2 :
                System.out.println("Vaksinisasi wajib!! bayar Rp.15.000");
                P.rdcMoney(15000);
                break;
            case 3 :
                System.out.println("Sekolah itu gak gratis!! ga bayar sekolah RP.50.000 PKI!!!!!");
                P.rdcMoney(15000);
                break;
            case 4 :
                System.out.println("Cie menang olimpiade, selamat anda mendapatkan Rp.50.000");
                P.addMoney(50000);
                break;    
        } 
            
    }

    public void chanceCard(Player P, String command){                                       //Chance Card
        int result = randomGen.nextInt(cards);
        switch(result) {
            case 0 : 
                System.out.println("Keciduk polisi, hiya hiya hiya, korupsi ya?? masuk penjara sini!! jangan korupsi dong");
                P.setPos(10);
                P.setJail(true);
                break;
            case 1 :
                System.out.println("Pergi ke go!! lumayan dapet Rp.40.000");
                P.setPos(0);
                this.startTile(P);
                break;
            case 2 :
                System.out.println("Maju 3 langkah dong");
                P.setPos(P.getPos()+3);
                command = "majuchance";
                break;
            case 3 :
                System.out.println("Astaghfirullah menang judi.... tapi gaapa uang monopoli kokga haram");
                P.addMoney(100000);
                break;
            case 4 : 
                System.out.println("Mundur 3 langkah");
                P.setPos(P.getPos()-3);
                command = "mundurchance";
                break;
        }
    }

    public boolean landedMethod(Player P, String command){
        if (P.getPos() == 0){
            this.startTile(P);
        } else if (P.getPos()==30){
            this.goToJailTile(P);
        } else if (P.getPos()==20){
            this.freeParkingTile(P, command);
        } else if (P.getPos()==2 || P.getPos()==17 || P.getPos()==32){
            this.communityChestCard(P);
        } else if (P.getPos()==7 || P.getPos()==22 || P.getPos()==36){
            this.chanceCard(P, command);
        }

        return true;
    }
}