import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static synchronized void main(String[] args) {
    	// Local variable
    	Scanner sc = new Scanner(System.in);
    	List<Player> players = new ArrayList<Player>();
    	String playerName, command;
    	int i, totalPlayer;
    	boolean play = true;
    	boolean nextTurn = false;

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

        		// Ngejalanin landed method property / space
        		/*tiles.get(players.get(turn.getPlayer()).getPos()).landedMethod();*/

        		// Command harusnya di dalam landed method kah??
        		System.out.println("Masukkan commandmu");
        		System.out.print(">> ");
        		command = sc.next();


        		// ... jalanin command ...
        		// nextTurn = true;


        		if (dice1.getValue() == dice2.getValue()) {
        			
        		}
        	}

        	if ((timer.getTime() == 30) || (nextTurn)) {
        		nextTurn = false;
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