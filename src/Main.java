import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static synchronized void main(String[] args) {
    	// Variables
    	Scanner sc = new Scanner(System.in);
    	List<Player> players = new ArrayList<Player>();
    	List<Tile> tiles = new ArrayList<Tile>();
    	String playerName;
    	int i, totalPlayer;
    	boolean play = false;
    	boolean nextPlayer = false;
    	// UI pages
		LogPage logPage = new LogPage();
		NewGameUI welcomePage = new NewGameUI();
		MonopoliOOPUI mainPage = new MonopoliOOPUI();
		WinnerPage endPage = new WinnerPage();

    	// Adding map of the game (Total: 40 tiles)
		tiles.add(new Space("Start", logPage));								// 0
		tiles.add(new Lot("Aceh", 10000, 1, logPage));
		tiles.add(new Space("Community Chest", logPage));
		tiles.add(new Lot("Ambon", 13000, 1, logPage));
		tiles.add(new Space("Pajak", logPage));
    	tiles.add(new Property("Soekarno-Hatta", 22500, 3, logPage));		// 5
    	tiles.add(new Lot("Bali", 15000, 2, logPage));
		tiles.add(new Space("Chance", logPage));
		tiles.add(new Lot("Bandung", 20000, 2, logPage));
		tiles.add(new Lot("Banjarbaru", 20000, 2, logPage));					// 9
			// end of first row
		tiles.add(new Space("Penjara", logPage));
		tiles.add(new Lot("Banten", 25000, 3, logPage));
		tiles.add(new Property("PLTU", 32500, 2, logPage));
    	tiles.add(new Lot("Bekasi", 26500, 3, logPage));
    	tiles.add(new Lot("Bima", 26500, 3, logPage));
    	tiles.add(new Property("Cicaheum", 27500, 3, logPage));				// 15
    	tiles.add(new Lot("Bogor", 27000, 4, logPage));
		tiles.add(new Space("Community Chest", logPage));
		tiles.add(new Lot("Tangerang", 27000, 4, logPage));
		tiles.add(new Lot("Bontang", 30000, 4, logPage));					// 19
			// end of second row
		tiles.add(new Space("Free Parking", logPage));
		tiles.add(new Lot("Depok", 30000, 5, logPage));
		tiles.add(new Space("Chance", logPage));
		tiles.add(new Lot("Gorontalo", 32000, 5, logPage));
		tiles.add(new Lot("Jakarta", 31000, 5, logPage));
    	tiles.add(new Property("Gambir", 30000, 3, logPage));				// 25
    	tiles.add(new Lot("Jogja", 33000, 6, logPage));
    	tiles.add(new Lot("Lampung", 33500, 6, logPage));
    	tiles.add(new Property("PLTA", 37000, 2, logPage));		
    	tiles.add(new Lot("Lombok", 33000, 6, logPage));						// 29
    		// end of third row
		tiles.add(new Space("Go To Jail", logPage));
		tiles.add(new Lot("Makassar", 33000, 7, logPage));
		tiles.add(new Lot("Malang", 34000, 7, logPage));
		tiles.add(new Space("Community Chest", logPage));
		tiles.add(new Lot("Manokwari", 36000, 7, logPage));
		tiles.add(new Property("Tanjung Priok", 36000, 3, logPage));			// 35
		tiles.add(new Space("Chance", logPage));
		tiles.add(new Lot("Medan", 39000, 8, logPage));
		tiles.add(new Space("Pajak", logPage));
		tiles.add(new Lot("Semarang", 40000, 8, logPage));					// 39
			// end of fourth row

		// Log Page
		// logPage.setVisible(true);

		// Welcome Page
		welcomePage.setVisible(true);
		int markCounter = 0;
		while (welcomePage.getPanelOpen()) {
			// while visible
			if (markCounter == 0) {
				logPage.appendLog("Welcome page");
				markCounter++;
			}
			System.out.println("inside");
		}
    	for (i = 1; i <= welcomePage.getNumP(); i++) {
    		players.add(new Player(welcomePage.getPlayersName().get(i - 1), logPage));
    	}
    	welcomePage.setVisible(false);

    	// Main Page
		mainPage.setVisible(true);
		play = true;

		/////////////////////
		// UI (deprecated) //
		logPage.appendLog("============ WELCOME TO HELL MONOPOLY ============\n");

    	logPage.appendLog("Berapa orang yang main? (2-4 orang)");
    	totalPlayer = welcomePage.getNumP();
    	logPage.appendLog(">> " + totalPlayer);

    	for (i = 1; i <= totalPlayer; i++) {
    		logPage.appendLog("Player " + i + " : ");
    		logPage.appendLog(players.get(i - 1).getName());
    	}

    	// Timer ga perlu di stop, biarkan mengalir aja~
    	Timer timer = new Timer();
        Turn<Timer> turn = new Turn<Timer>(totalPlayer, timer);
	    timer.start();
        turn.start();

        // Dadu
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        logPage.appendLog("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");

        while (play) {
        	// New turn (setiap player sudah bermain)
    		if (turn.getPlayer() == 0) {
    			turn.setTurn(turn.getTurn() + 1);
    		}

    		// Dah kelar, tinggal 1 player
    		if (turn.getTotalPlayer() == 1) {
    			play = false;
    			break;
    		}

			turn.setCommand("diam");

			// Penjara
			if (players.get(turn.getPlayer()).getJail() == true) {
				players.get(turn.getPlayer()).decrementJail();
				if (players.get(turn.getPlayer()).getCountJail() <= 0) {
					players.get(turn.getPlayer()).setJail(false);
					logPage.appendLog("Selamat! " + players.get(turn.getPlayer()).getName() + " keluar dari penjara");
				} else {
					// Roll dice
					try
					{
						turn.setCommand((new Input()).getInput(
							tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
							.equals(
								players.get(turn.getPlayer()).getName()
							),
							mainPage,
							logPage,
							"roll",
							tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName(),
							players.get(turn.getPlayer()).getName(),
							tiles.get(players.get(turn.getPlayer()).getPos()).getName()
						));
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
					dice1.roll();
					dice2.roll();
					logPage.appendLog("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());
					if (dice1.getValue() == dice2.getValue()) {
						players.get(turn.getPlayer()).setJail(false);
						logPage.appendLog("Selamat! " + players.get(turn.getPlayer()).getName() + " keluar dari penjara");
					} else {
						logPage.appendLog("Sorry, " + players.get(turn.getPlayer()).getName() + " yang betah ya di penjara~");
					}
				}
			}
			// Biasa
			else {
				// Roll dice
				try
				{
					turn.setCommand((new Input()).getInput(
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
						.equals(
							players.get(turn.getPlayer()).getName()
						),
						mainPage,
						logPage,
						"roll",
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName(),
						players.get(turn.getPlayer()).getName(),
						tiles.get(players.get(turn.getPlayer()).getPos()).getName()
					));
				}
				catch (Exception e)
				{
					System.out.println(e);
				}

				// Ubah posisi player
				players.get(turn.getPlayer()).setPos(
					players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
				);
				logPage.appendLog("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());

				// Jika dadu sama
				if (dice1.getValue() == dice2.getValue()) {
					logPage.appendLog("Angka dadu sama, roll lagi!");
					// Roll dice
					try
					{
						turn.setCommand((new Input()).getInput(
							tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
							.equals(
								players.get(turn.getPlayer()).getName()
							),
							mainPage,
							logPage,
							"roll",
							tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName(),
							players.get(turn.getPlayer()).getName(),
							tiles.get(players.get(turn.getPlayer()).getPos()).getName()
						));
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
					players.get(turn.getPlayer()).setPos(
						players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
					);
					logPage.appendLog("Angka dadu : " + dice1.getValue() + " & " + dice2.getValue());
				}

				// Info landing
				if (tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Property")) {
					logPage.appendLog(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				} else {
					logPage.appendLog(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName());
				}

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				turn.setCommand("diam");

				try
				{
					turn.setCommand((new Input()).getInput(
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
						.equals(
							players.get(turn.getPlayer()).getName()
						),
						mainPage,
						logPage,
						tiles.get(players.get(turn.getPlayer()).getPos()).getKind(),
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName(),
						players.get(turn.getPlayer()).getName(),
						tiles.get(players.get(turn.getPlayer()).getPos()).getName()
					));
				}
				catch (Exception e)
				{
					System.out.println(e);
				}

				///////////////////////////////////////////////////////////////
			}

			// landedMethod
    		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), turn);
    	
    		// For chances, free parking, and cheats
    		if (turn.getCommand().equals("majuchance") || turn.getCommand().equals("mundurchance") || turn.getCommand().equals("parkirsabeb")) {
        		// Info landing
				if (tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Property")) {
					logPage.appendLog(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				} else {
					logPage.appendLog(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName());
				}
				

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				turn.setCommand("diam");

				try
				{
					turn.setCommand((new Input()).getInput(
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
						.equals(
							players.get(turn.getPlayer()).getName()
						),
						mainPage,
						logPage,
						tiles.get(players.get(turn.getPlayer()).getPos()).getKind(),
						tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName(),
						players.get(turn.getPlayer()).getName(),
						tiles.get(players.get(turn.getPlayer()).getPos()).getName()
					));
				}
				catch (Exception e)
				{
					System.out.println(e);
				}

				///////////////////////////////////////////////////////////////

				// landedMethod
        		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), turn);
    		} 

        	// Next turn
        	if (nextPlayer) {
        		if (players.get(turn.getPlayer()).getMoney() <= 0) {
		            logPage.appendLog("");
        			for (Tile tile : tiles) {
        				if (tile.getKind().equals("Property")) {
			                if (tile.getOwnerName().equals(players.get(turn.getPlayer()).getName())) {
			                    tile.setOwner(null);
			                    logPage.appendLog(tile.getName() + " telah dikembalikan kepada Tuhan");
			                }
			            }
		            }
        			logPage.appendLog(players.get(turn.getPlayer()).getName() + " kalah! Sukurinn hahahah");
        			players.remove(turn.getPlayer());
        			turn.setTotalPlayer(players.size());
        		}

        		nextPlayer = false;
        		logPage.appendLog("");
        		turn.nextPlayer();

        		try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            if (!turn.getCommand().equals("quit")) {
	        		logPage.appendLog("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");
	            }
        	}

        	// Quit
        	if (turn.getCommand().equals("quit")) {
        		play = false;
        	}			
        }

        logPage.appendLog("");
        logPage.appendLog("==================== Selesai ====================");
        logPage.appendLog("========== Terima kasih telah bermain! ==========");
        logPage.appendLog("");
        logPage.appendLog("Selamat! " + players.get(0).getName() + " menang");
        logPage.appendLog("Anda bermain sebanyak " + turn.getTurn() + " turn");
        logPage.appendLog("Anda bermain selama " + (turn.getT().getTime() / 60) + " menit " + (turn.getT().getTime() % 60) + " detik");

        sc.close();
    }
}