import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import my.MonopoliOOP.*;

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

    	// Adding map of the game (Total: 40 tiles)
		tiles.add(new Space("Start"));								// 0
		tiles.add(new Lot("Aceh", 10000, 1));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Ambon", 13000, 1));
		tiles.add(new Space("Pajak"));
    	tiles.add(new Property("Soekarno-Hatta", 22500, 3));		// 5
    	tiles.add(new Lot("Bali", 15000, 2));
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Bandung", 20000, 2));
		tiles.add(new Lot("Banjarbaru", 20000, 2));					// 9
			// end of first row
		tiles.add(new Space("Penjara"));
		tiles.add(new Lot("Banten", 25000, 3));
		tiles.add(new Property("PLTU", 32500, 2));
    	tiles.add(new Lot("Bekasi", 26500, 3));
    	tiles.add(new Lot("Bima", 26500, 3));
    	tiles.add(new Property("Cicaheum", 27500, 3));				// 15
    	tiles.add(new Lot("Bogor", 27000, 4));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Tangerang", 27000, 4));
		tiles.add(new Lot("Bontang", 30000, 4));					// 19
			// end of second row
		tiles.add(new Space("Free Parking"));
		tiles.add(new Lot("Depok", 30000, 5));
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Gorontalo", 32000, 5));
		tiles.add(new Lot("Jakarta", 31000, 5));
    	tiles.add(new Property("Gambir", 30000, 3));				// 25
    	tiles.add(new Lot("Jogja", 33000, 6));
    	tiles.add(new Lot("Lampung", 33500, 6));
    	tiles.add(new Property("PLTA", 37000, 2));		
    	tiles.add(new Lot("Lombok", 33000, 6));						// 29
    		// end of third row
		tiles.add(new Space("Go To Jail"));
		tiles.add(new Lot("Makassar", 33000, 7));
		tiles.add(new Lot("Malang", 34000, 7));
		tiles.add(new Space("Community Chest"));
		tiles.add(new Lot("Manokwari", 36000, 7));
		tiles.add(new Property("Tanjung Priok", 36000, 3));			// 35
		tiles.add(new Space("Chance"));
		tiles.add(new Lot("Medan", 39000, 8));
		tiles.add(new Space("Pajak"));
		tiles.add(new Lot("Semarang", 40000, 8));					// 39
			// end of fourth row

		// Welcome Page
		NewGameUI welcomePage = new NewGameUI();
		welcomePage.setVisible(true);
		while (welcomePage.getPanelOpen()) {
			System.out.println("inside");
			// while visible
		}
    	for (i = 1; i <= welcomePage.getNumP(); i++) {
    		System.out.print("Player " + i + " : ");
    		players.add(new Player(welcomePage.getPlayersName().get(i - 1)));
    		System.out.println(players.get(i - 1).getName());
    	}
    	welcomePage.setVisible(false);

    	// Main Page
		MonopoliOOPUI mainPage = new MonopoliOOPUI();
		mainPage.setVisible(true);

		/////////////////////
		// UI (deprecated) //
        System.out.println("============ WELCOME TO HELL MONOPOLY ============\n");

    	System.out.println("Berapa orang yang main? (2-4 orang)");
    	System.out.print(">> ");
    	totalPlayer = sc.nextInt();

    	while (totalPlayer < 2 || totalPlayer > 4) {
			System.out.println("Yang bener dong, yang main berapa ni? (2-4 orang)");
	    	System.out.print(">> ");
	    	totalPlayer = sc.nextInt();    		
    	}

    	System.out.println("Masukkan nama pemain");


    	System.out.println("");

    	// Timer ga perlu di stop, biarkan mengalir aja~
    	Timer timer = new Timer();
        Turn<Timer> turn = new Turn<Timer>(totalPlayer, timer);
	    timer.start();
        turn.start();

        // Dadu
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        System.out.println("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");

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
				if (tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Property")) {
					System.out.println(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				} else {
					System.out.println(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName());
				}

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				turn.setCommand("diam");
				if (!tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Space")) {
					// Property
					if (tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName().equals("Tuhan")
        				|| tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
        					.equals(players.get(turn.getPlayer()).getName())) {
						try
						{
							turn.setCommand((new Input()).getInput(
								tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
								.equals(
									players.get(turn.getPlayer()).getName()
								)
							));
						}
						catch (Exception e)
						{
							System.out.println(e);
						}
					}
				}
				///////////////////////////////////////////////////////////////
			}

			// landedMethod
    		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), turn);
    	
    		// For chances, free parking, and cheats
    		if (turn.getCommand().equals("majuchance") || turn.getCommand().equals("mundurchance") || turn.getCommand().equals("parkirsabeb")) {
        		// Info landing
				if (tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Property")) {
					System.out.println(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName() 
								+ " milik " + tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName() 
								+ " dengan harga " + tiles.get(players.get(turn.getPlayer()).getPos()).getHarga() );
				} else {
					System.out.println(players.get(turn.getPlayer()).getName() 
								+ " mendarat di " + tiles.get(players.get(turn.getPlayer()).getPos()).getName());
				}
				

				////////////////////////////////////////////////////////////////
				// Syarat untuk landedMethod property
				turn.setCommand("diam");
				if (!tiles.get(players.get(turn.getPlayer()).getPos()).getKind().equals("Space")) {
					// Property
        			if (tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName().equals("Tuhan")
        				|| tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
        					.equals(players.get(turn.getPlayer()).getName())) {
			            try
				        {
				            turn.setCommand((new Input()).getInput(
								tiles.get(players.get(turn.getPlayer()).getPos()).getOwnerName()
								.equals(
									players.get(turn.getPlayer()).getName()
								)
							));
				        }
				        catch (Exception e)
				        {
				            System.out.println(e);
				        }
        			}
				}
				///////////////////////////////////////////////////////////////

				// landedMethod
        		nextPlayer = tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod(players.get(turn.getPlayer()), turn);
    		} 

        	// Next turn
        	if (nextPlayer) {
        		if (players.get(turn.getPlayer()).getMoney() <= 0) {
		            System.out.println("");
        			for (Tile tile : tiles) {
        				if (tile.getKind().equals("Property")) {
			                if (tile.getOwnerName().equals(players.get(turn.getPlayer()).getName())) {
			                    tile.setOwner(null);
			                    System.out.println(tile.getName() + " telah dikembalikan kepada Tuhan");
			                }
			            }
		            }
        			System.out.println(players.get(turn.getPlayer()).getName() + " kalah! Sukurinn hahahah");
        			players.remove(turn.getPlayer());
        			turn.setTotalPlayer(players.size());
        		}

        		nextPlayer = false;
        		System.out.println("");
        		turn.nextPlayer();

        		try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            if (!turn.getCommand().equals("quit")) {
	        		System.out.println("Giliran " + players.get(turn.getPlayer()).getName() + " bermain!");
	            }
        	}

        	// Quit
        	if (turn.getCommand().equals("quit")) {
        		play = false;
        	}			
        }

        System.out.println("");
        System.out.println("==================== Selesai ====================");
        System.out.println("========== Terima kasih telah bermain! ==========");
        System.out.println("");
        System.out.println("Selamat! " + players.get(0).getName() + " menang");
        System.out.println("Anda bermain sebanyak " + turn.getTurn() + " turn");
        System.out.println("Anda bermain selama " + (turn.getT().getTime() / 60) + " menit " + (turn.getT().getTime() % 60) + " detik");

        sc.close();
    }
}