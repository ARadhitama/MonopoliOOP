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
		tiles.add(new Space("Start"));								// 0
		tiles.add(new Lot("Monas", 10000, 1));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Taman Mini", 13000, 1));
    	tiles.add(new Property("Bandara Kemayoran", 22500, 3));
    	tiles.add(new Lot("Ragunan Pasar Minggu", 15000, 2));		// 5
    	tiles.add(new Lot("Binaria", 20000, 2));
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Taman Pluit", 20000, 2));
    	tiles.add(new Lot("Kebun Raya Bogor", 25000, 3));
		tiles.add(new Space("Penjara"));							// 10
		tiles.add(new Property("Perusahaan Listrik", 32500, 2));
    	tiles.add(new Lot("Pelabuhan Ratu", 26500, 3));
    	tiles.add(new Lot("Tangkuban Perahu", 26500, 3));
    	tiles.add(new Property("Terminal Bis Semarang", 27500, 3));
    	tiles.add(new Lot("Gedung Batu", 27000, 4));				// 15
    	tiles.add(new Lot("Kopeng", 27000, 4));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Borobudur", 30000, 4));
    	tiles.add(new Lot("Prambanan", 30000, 5));
		tiles.add(new Space("Free Parking"));						// 20
		tiles.add(new Lot("Kraton Yogya", 32000, 5));
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Bengawan Solo", 31000, 5));
    	tiles.add(new Property("Stasiun Pasar Turi", 30000, 3));
    	tiles.add(new Lot("Sarangan", 33000, 6));					// 25
    	tiles.add(new Lot("Selecta", 33500, 6));
    	tiles.add(new Property("Perusahaan Air", 37000, 2));		
    	tiles.add(new Lot("Gunung Kawi", 33000, 6));		
		tiles.add(new Lot("Pantai Sanur", 33000, 7));
		tiles.add(new Space("Go To Jail"));							// 30
		tiles.add(new Lot("Tampak Siring", 34000, 7));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Taman Laut Banda", 36000, 7));
		tiles.add(new Property("Pelabuhan Belawan", 36000, 3));
		tiles.add(new Lot("Danau Toba", 39000, 8));					// 35
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Brastagi", 40000, 8));					// 37

        System.out.println("============ WELCOME TO HELL MONOPOLY ============\n");

    	System.out.println("Berapa orang yang main?");
    	System.out.print(">> ");
    	totalPlayer = sc.nextInt();

    	System.out.println("Masukkan nama pemain");

    	for (i = 1; i <= totalPlayer; i++) {
    		System.out.print("Player " + i + " : ");
    		playerName = sc.next();
    		players.add(new Player(playerName));
    	}

    	System.out.println("");

    	// Timer ga perlu di stop, biarkan mengalir aja~
    	Timer timer = new Timer();
        Turn turn = new Turn(totalPlayer, timer);
	    timer.start();
        turn.start();

        // Dadu
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        System.out.println("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");

        while (play) {
    		// Kelar
    		/*for (Player p : players) {
    			if (p.getMoney() <= 0) {
    				players.remove()		// Hapus player
    			}

    		}*/

        	// New turn (setiap player sudah bermain)
    		if (turn.getPlayer() == 0) {
    			turn.setTurn(turn.getTurn() + 1);
    		}

			command = "diam";

			// Penjara
			if (players.get(turn.getPlayer()).getJail() == true) {
				players.get(turn.getPlayer()).decrementJail();
				if (players.get(turn.getPlayer()).getCountJail() <= 0) {
					players.get(turn.getPlayer()).setJail(false);
					System.out.println("Selamat! " + players.get(turn.getPlayer()).getName() + " keluar dari penjara");
				} else {
					dice1.roll();
					dice2.roll();
					System.out.println("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());
					if (dice1.getValue() == dice2.getValue()) {
						players.get(turn.getPlayer()).setJail(false);
						System.out.println("Selamat! " + players.get(turn.getPlayer()).getName() + " keluar dari penjara");
					} else {
						System.out.println("Sorry, " + players.get(turn.getPlayer()).getName() + " yang betah ya di penjara~");
					}
				}
			}
			// Biasa
			else {
				// Ubah posisi player
				players.get(turn.getPlayer()).setPos(
					players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
				);
				System.out.println("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());

				// Jika dadu sama
				if (dice1.getValue() == dice2.getValue()) {
					System.out.println("Angka dadu sama, roll lagi!");
					players.get(turn.getPlayer()).setPos(
						players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
					);
					System.out.println("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());
				}

				// Info landing
				System.out.println(players.get(turn.getPlayer()).getName() + " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				command = "diam";
				if (!tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Space")) {
					// Property
					if (tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName().equals("Tuhan")) {
						try
						{
							command = (new Input()).getInput();
						}
						catch( Exception e )
						{
							System.out.println( e );
						}
					}
				}
				///////////////////////////////////////////////////////////////
			}

    		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), command);
    	
    		if (command.equals("majuchance") || command.equals("mundurchance") || command.equals("parkirsabeb")) {
        		// Info landing
				System.out.println(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				command = "diam";
				if (!tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Space")) {
					// Property
        			if (tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName().equals("Tuhan")) {
			            try
				        {
				            command = (new Input()).getInput();
				        }
				        catch( Exception e )
				        {
				            System.out.println( e );
				        }
        			}
				}
				///////////////////////////////////////////////////////////////

				// landedMethod
        		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), command);
    		} 

        	// Next turn
        	if (nextPlayer) {
        		nextPlayer = false;
        		System.out.println("");
        		turn.nextPlayer();

        		try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e) {
	                e.printStackTrace();
	            }

        		System.out.println("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");
        	}

        	// Quit
        	if (command.equals("quit")) {
        		play = false;
        	}			
        }

        System.out.println("");
        System.out.println("Selesai");
        System.out.println("Anda bermain selama " + (timer.getTime() / 60) + " menit " + (timer.getTime() % 60) + " detik");

        sc.close();
    }
}