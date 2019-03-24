import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Timer extends Thread {
    private int time = 1;
    private boolean setting = false;

    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(time + " ");
            setTime(time + 1);
            //Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
        }
    }

    public synchronized int getTime() {
        while (setting) {
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }

        return time;
    }

    public synchronized void setTime (int t) {
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }
}

class Turn extends Thread {
	private Timer timer;
	private int totalPlayer;
	private int turn = 1;
	private int player = 0;		// Index dimulai dari 0

	public Turn(Timer t, int totalPlayer) {
		this.timer = t;
		this.totalPlayer = totalPlayer;
	}

	public int getTurn() {
		return this.turn;
	}

	public int getPlayer() {
		return this.player;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void nextPlayer() {
		this.player = (this.player + 1) % this.totalPlayer;
	}

	public void run() {
        synchronized (timer) {
            while (true) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (timer.getTime() % 30 == 0) {
                    System.out.println("Time limit, next player turn!");
                }
            }
        }
    }
}

public class Main {
    public static synchronized void main(String[] args) {
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

    }
}