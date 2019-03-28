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
    	tiles.add(new Property("Bandung", 5000, 1));	// Lot 1
    	tiles.add(new Property("a", 5000, 1));
    	tiles.add(new Property("b", 5000, 1));
    	tiles.add(new Property("c", 5000, 1));
    	tiles.add(new Property("d", 5000, 1));
    	tiles.add(new Property("e", 5000, 1));
    	tiles.add(new Property("f", 5000, 1));
    	tiles.add(new Property("g", 5000, 1));
    	tiles.add(new Property("h", 5000, 1));
    	tiles.add(new Property("i", 5000, 1));
    	tiles.add(new Property("ax", 5000, 1));
    	tiles.add(new Property("bx", 5000, 1));
    	tiles.add(new Property("cx", 5000, 1));
    	tiles.add(new Property("dx", 5000, 1));
    	tiles.add(new Property("ex", 5000, 1));
    	tiles.add(new Property("fx", 5000, 1));
    	tiles.add(new Property("gx", 5000, 1));
    	tiles.add(new Property("hx", 5000, 1));
    	tiles.add(new Property("ix", 5000, 1));
    	tiles.add(new Property("ay", 5000, 1));
    	tiles.add(new Property("by", 5000, 1));
    	tiles.add(new Property("cy", 5000, 1));
    	tiles.add(new Property("dy", 5000, 1));
    	tiles.add(new Property("ey", 5000, 1));
    	tiles.add(new Property("fy", 5000, 1));
    	tiles.add(new Property("gy", 5000, 1));
    	tiles.add(new Property("hy", 5000, 1));
    	tiles.add(new Property("iy", 5000, 1));
    	tiles.add(new Property("PLN", 3000, 2));		// Utility 1
    	tiles.add(new Property("Gambir", 4000, 3));		// Railroad 1
    	// tiles.add(new Space())







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

        		// Jika dadu sama
        		if (dice1.getValue() == dice2.getValue()) {
        			players.get(turn.getPlayer()).setPos(
	        			players.get(turn.getPlayer()).getPos() + dice1.roll() + dice2.roll()
	        		);
        		}

        		// Ngejalanin landed method property / space
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