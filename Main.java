import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static synchronized void main(String[] args) {
    	// Variables
    	Scanner sc = new Scanner(System.in);
    	List<Player> players = new ArrayList<Player>();
    	List<Tile> tiles = new ArrayList<Tile>();
    	String playerName, command;
    	int i, totalPlayer;
    	boolean play = true;
    	boolean nextPlayer = false;



    	// Adding map of the game
    	//tiles.add(new Property("Bandung", 5000, 1));	
    	tiles.add(new Lot("Monas", 5000, 1));
    	//tiles.add(new //Property("b", 5000, 1));
    	tiles.add(new Lot("Taman Mini", 6000, 1));
    	//tiles.add(new //Property("d", 5000, 1));
    	tiles.add(new Property("Bandara Kemayoran", 20000, 3));
    	tiles.add(new Lot("Ragunan Pasar Minggu", 7000, 2));
    	//tiles.add(new //Property("g", 5000, 1));
    	tiles.add(new Lot("Binaria", 7500, 2));
    	tiles.add(new Lot("Taman Pluit", 8000, 2));
    	//tiles.add(new //Property("ax", 5000, 1));
    	tiles.add(new Lot("Kebun Raya Bogor", 9000, 3));
    	tiles.add(new Property("Perusahaan Listrik", 10000, 2));
    	tiles.add(new Lot("Pelabuhan Ratu", 9500, 3));
    	tiles.add(new Lot("Tangkuban Perahu", 10000, 3));
    	tiles.add(new Property("Terminal Bis Semarang", 20000, 3));
    	tiles.add(new Lot("Gedung Batu", 11000, 4));
    	//tiles.add(new //Property("hx", 5000, 1));
    	tiles.add(new Lot("Kopeng", 11500, 4));
    	tiles.add(new Lot("Borobudur", 12000, 4));
    	//tiles.add(new //Property("by", 5000, 1));
    	tiles.add(new Lot("Prambanan", 13000, 5));
    	//tiles.add(new //Property("dy", 5000, 1));
    	tiles.add(new Lot("Kraton Yogya", 13500, 5));
    	tiles.add(new Lot("Bengawan Solo", 14000, 5));
    	tiles.add(new Property("Stasiun Pasar Turi", 20000, 3));
    	tiles.add(new Lot("Sarangan", 15000, 6));
    	tiles.add(new Lot("Selecta", 15500, 6));
    	tiles.add(new Property("Perusahaan Air", 10000, 2));		
    	tiles.add(new Lot("Gunung Kawi", 16000, 6));		
    	//tiles.add(new //Property("Perusahaan Air", 10000, 2));
		tiles.add(new Lot("Pantai Sanur", 17000, 7));
		tiles.add(new Lot("Tampak Siring", 17500, 7));
		//tiles.add(new //Lot("Gunung Kawi", 16000, 6));
		tiles.add(new Lot("Taman Laut Banda", 18000, 7));
		tiles.add(new Property("Pelabuhan Belawan", 20000, 3));
		//tiles.add(new //Lot("Gunung Kawi", 16000, 6));
		tiles.add(new Lot("Danau Toba", 19000, 8));
		//tiles.add(new //Lot("Gunung Kawi", 16000, 6));
		tiles.add(new Lot("Brastagi", 20000, 8));

    	System.out.println("Berapa orang yang main?");
    	System.out.print(">> ");
    	totalPlayer = sc.nextInt();

    	System.out.println("Masukkan nama pemain");

    	for (i = 1; i <= totalPlayer; i++) {
    		System.out.print("Player " + i + " : ");
    		playerName = sc.next();
    		players.add(new Player(playerName));
    	}

    	// Timer ga perlu di stop, biarkan mengalir aja~
    	Timer timer = new Timer();
        Turn turn = new Turn(timer, totalPlayer);
	    timer.start();
        turn.start();

        // Dadu
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        while (play) {
	        // Next player turn
        	if (timer.getTime() == 1) {
        		// New turn (setiap player sudah bermain)
        		if (turn.getPlayer() == 1) {
        			turn.setTurn(turn.getTurn() + 1);
        		}

				turn.nextPlayer();

        		// Ubah posisi player
        		players.get(turn.getPlayer()).setPos(
        			players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
				);
				System.out.println("Angka dadu : " + dice1.getValue() + " , " + dice2.getValue());

        		// Jika dadu sama
        		if (dice1.getValue() == dice2.getValue()) {
					System.out.println("Angka dadu sama, roll lagi!");
        			players.get(turn.getPlayer()).setPos(
	        			players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
					);
					System.out.println("Angka dadu : " + dice1.getValue() + " , " + dice2.getValue());
        		}

				// Ngejalanin landed method property / space
				System.out.println("Uang " + players.get(turn.getPlayer()).getName() + " saat ini adalah : " + players.get(turn.getPlayer()).getName());
				System.out.println("Anda mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() + " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() + " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()));
        	}

        	if ((timer.getTime() == 30) || (nextPlayer)) {
        		nextPlayer = false;
        		timer.setTime(1);
        		System.out.println("Giliran player " + (turn.getPlayer() + 1) + "!");
        	}

        	/*if (timer.getTime() == totalTime) {
        		timer.setTime(1);
        	}*/

        }

        System.out.println("Yee menang");
        sc.close();
    }
}