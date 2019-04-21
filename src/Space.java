import java.util.Random;
import java.util.Scanner;

public class Space extends Tile {
    private Random randomGen = new Random();
    private int cards = 5;
    private String spacetile;
    private LogPage logPage;


    public Space(String spacetile, LogPage log) {
        this.spacetile = spacetile;
        this.logPage = log;
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
            logPage.appendLog("Selamat anda beruntung tiba di start dapat Rp.40.000!!!");
        } else {
            p.addMoney(20000);
            logPage.appendLog("Selamat anda mendapatkan Rp.20.000");
        }
        System.out.println("Uang anda sekarang :" + p.getMoney());
    }

    public void jailTile(Player p, Turn t){
       if (t.getCommand().equals("Bayar")) {
        p.rdcMoney(50000);
       } else {
        logPage.appendLog("Roll Dice");
       }
    }

    public void freeParkingTile(Player p, Turn t) {     //Tile freeparking
        Scanner sc = new Scanner(System.in);
        int pos = p.getPos();

        if (p.getPos() == 20) {                         // Dapat berjalan ke lot mana saja
            logPage.appendLog("Mau mendarat di mana? (Sebut posisi 0-37)");
            logPage.appendLog(">> ");
            pos = sc.nextInt();                         // Scan daerah yang ingin dituju
            while (pos > 37 || pos < 0) {
                logPage.appendLog("Input salah, yang bener dong mau mendarat di mana!? (Sebut posisi 0-37)");
                logPage.appendLog(">> ");
                pos = sc.nextInt();
            }
            p.setPos(pos);
            t.setCommand("parkirsabeb");                                 
        }
    }

    public void goToJailTile(Player p) {                                // Tile Go TO Jail
        p.setPos(10);                                                   // Set player ke jail
        p.setJail(true);                                                // Set jail true
        logPage.appendLog("Anda mendarat penjara");
    }

    public void taxTile(Player p) {
        logPage.appendLog("Bayar pajak 10% dulu, biar negara maju");
        p.rdcMoney(p.getMoney()/10);                                              
    }

    public void communityChestCard(Player p) {                          // Community chest card tile
        int result = randomGen.nextInt(cards);
        switch (result) {
            case 0 :
                logPage.appendLog("Melanggar marka jalan, Anda dilempar ke penjara!");
                p.setPos(10);
                p.setJail(true);
                break;
            case 1 :
                logPage.appendLog("Anda menang togel!! dapat Rp.100.000,-");
                p.addMoney(100000);
                System.out.println("Uang anda sekarang :" + p.getMoney());
                break;
            case 2 :
                logPage.appendLog("Vaksinisasi wajib!! bayar Rp.15.000");
                p.rdcMoney(15000);
                System.out.println("Uang anda sekarang :" + p.getMoney());
                break;
            case 3 :
                logPage.appendLog("Sekolah itu gak gratis!! ga bayar sekolah RP.50.000 PKI!!!!!");
                p.rdcMoney(15000);
                System.out.println("Uang anda sekarang :" + p.getMoney());
                break;
            case 4 :
                logPage.appendLog("Cie menang olimpiade, selamat anda mendapatkan Rp.50.000");
                p.addMoney(50000);
                System.out.println("Uang anda sekarang :" + p.getMoney());
                break;    
        } 
            
    }

    public void chanceCard(Player p, Turn t) {                          // Chance Card
        int result = randomGen.nextInt(cards);
        switch (result) {
            case 0 : 
                logPage.appendLog("Keciduk polisi, hiya hiya hiya, korupsi ya?? masuk penjara sini!! jangan korupsi dong");
                p.setPos(10);
                p.setJail(true);
                break;
            case 1 :
                logPage.appendLog("Pergi ke go!! lumayan dapet Rp.40.000");
                p.setPos(0);
                this.startTile(p);
                break;
            case 2 :
                logPage.appendLog("Maju 3 langkah dong");
                p.setPos(p.getPos() + 3);
                t.setCommand("majuchance");
                break;
            case 3 :
                logPage.appendLog("Astaghfirullah menang judi 100k.... tapi gaapa uang monopoli kok, ga haram");
                p.addMoney(100000);
                System.out.println("Uang anda sekarang :" + p.getMoney());
                break;
            case 4 : 
                logPage.appendLog("Mundur 3 langkah");
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
        } else if (p.getPos() == 2 || p.getPos() == 17 || p.getPos() == 33) {
            this.communityChestCard(p);
        } else if (p.getPos() == 7 || p.getPos() == 22 || p.getPos() == 36) {
            this.chanceCard(p, t);
        } else if (p.getPos() == 4 || p.getPos() == 38) {
            this.taxTile(p);
        }
        return true;
    }
}