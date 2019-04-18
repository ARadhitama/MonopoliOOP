import java.util.Random;
import java.util.Scanner;

public class Space extends Tile {
    private Random randomGen = new Random();
    private int cards = 5;
    private String spacetile;


    public Space(String spacetile) {
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
    
    public void setOwner(Player owner) {

    }

    public void startTile(Player p) {                   // Jika posisi pada startTile akan menambahkan 2x uang start
        if (p.getPos() == 0) {
            p.addMoney(40000);
            System.out.println("Selamat anda beruntung tiba di start dapat Rp.40.000!!!");
        } else {
            p.addMoney(20000);
            System.out.println("Selamat anda mendapatkan Rp.20.000");
        }
    }

    public void freeParkingTile(Player p, Turn t) {     //Tile freeparking
        Scanner sc = new Scanner(System.in);
        int pos = p.getPos();

        if (p.getPos() == 20) {                         // Dapat berjalan ke lot mana saja
            System.out.println("Mau mendarat di mana? (Sebut posisi 0-37)");
            System.out.print(">> ");
            pos = sc.nextInt();                         // Scan daerah yang ingin dituju
            while (pos > 37 || pos < 0) {
                System.out.println("Input salah, yang bener dong mau mendarat di mana!? (Sebut posisi 0-37)");
                System.out.print(">> ");
                pos = sc.nextInt();
            }
            p.setPos(pos);
            t.setCommand("parkirsabeb");                                 
        }
    }

    public void goToJailTile(Player p) {                                // Tile Go TO Jail
        p.setPos(10);                                                   // Set player ke jail
        p.setJail(true);                                                // Set jail true
        System.out.println("Anda mendarat penjara");
    }

    public void taxTile(Player p) {
        System.out.println("Bayar pajak dulu");
        p.rdcMoney(p.getMoney()/10);                                              //Maunya 10% tapi money gak double
    }

    public void communityChestCard(Player p) {                          // Community chest card tile
        int result = randomGen.nextInt(cards);
        switch (result) {
            case 0 :
                System.out.println("Melanggar marka jalan, Anda dilempar ke penjara!");
                p.setPos(10);
                p.setJail(true);
                break;
            case 1 :
                System.out.println("Anda menang togel!! dapat Rp.100.000,-");
                p.addMoney(100000);
                break;
            case 2 :
                System.out.println("Vaksinisasi wajib!! bayar Rp.15.000");
                p.rdcMoney(15000);
                break;
            case 3 :
                System.out.println("Sekolah itu gak gratis!! ga bayar sekolah RP.50.000 PKI!!!!!");
                p.rdcMoney(15000);
                break;
            case 4 :
                System.out.println("Cie menang olimpiade, selamat anda mendapatkan Rp.50.000");
                p.addMoney(50000);
                break;    
        } 
            
    }

    public void chanceCard(Player p, Turn t) {                          // Chance Card
        int result = randomGen.nextInt(cards);
        switch (result) {
            case 0 : 
                System.out.println("Keciduk polisi, hiya hiya hiya, korupsi ya?? masuk penjara sini!! jangan korupsi dong");
                p.setPos(10);
                p.setJail(true);
                break;
            case 1 :
                System.out.println("Pergi ke go!! lumayan dapet Rp.40.000");
                p.setPos(0);
                this.startTile(p);
                break;
            case 2 :
                System.out.println("Maju 3 langkah dong");
                p.setPos(p.getPos() + 3);
                t.setCommand("majuchance");
                break;
            case 3 :
                System.out.println("Astaghfirullah menang judi 100k.... tapi gaapa uang monopoli kok, ga haram");
                p.addMoney(100000);
                break;
            case 4 : 
                System.out.println("Mundur 3 langkah");
                p.setPos(p.getPos() - 3);
                t.setCommand("mundurchance");
                break;
        }
    }

    public boolean landedMethod(Player p, Turn t) {
        if (p.getPos() == 0) {
            this.startTile(p);
        } else if (p.getPos() == 30) {
            this.goToJailTile(p);
        } else if (p.getPos() == 20) {
            this.freeParkingTile(p, t);
        } else if (p.getPos() == 2 || p.getPos() == 17 || p.getPos() == 32) {
            this.communityChestCard(p);
        } else if (p.getPos() == 7 || p.getPos() == 22 || p.getPos() == 36) {
            this.chanceCard(p, t);
        }
        return true;
    }
}