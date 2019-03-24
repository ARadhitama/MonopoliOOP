import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Timer extends Thread {
    private int time = 1;
    private boolean setting = false;
    private int playerTurn = 0;

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

    public synchronized int getPlayerTurn() {
        while (setting) {
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }

        return this.playerTurn;
    }

    public synchronized void setTime (int t) {
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }

    public synchronized void nextPlayerTurn (int jumlahPemain) {
        setting = true;
        this.playerTurn = (this.playerTurn + 1) % jumlahPemain;
        setting = false;
        notifyAll();
    }
}

class Timestamp extends Thread {
    Timer timer;

    public Timestamp (Timer t) {
        this.timer = t;
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
                    System.out.println("Time : " + timer.getTime() + " | Time limit, next player turn");
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
    	boolean play = true;
    	boolean nextTurn = false;

    	System.out.println("Berapa orang yang main?");
    	System.out.print(">> ");
    	int jumlahPemain = sc.nextInt();
    	int totalTime = jumlahPemain * 30;

    	System.out.println("Masukkan nama pemain");

    	for (int i = 1; i <= jumlahPemain; i++) {
    		System.out.print("Player " + i + " : ");
    		playerName = sc.next();
    		players.add(new Player(playerName));
    	}

    	// Timer ga perlu di stop, biarkan mengalir aja~
    	Timer timer = new Timer();
        Timestamp timestamp = new Timestamp(timer);
	    timer.start();
        timestamp.start();

        while (play) {
	        
        	if (timer.getTime() == 1) {
        		System.out.println("Masukkan commandmu");
        		System.out.print(">> ");
        		command = sc.next();
        	}

        	if ((timer.getTime() == 30) || (nextTurn)) {
        		nextTurn = false;
        		timer.setTime(1);
        		timer.nextPlayerTurn(jumlahPemain);
        		System.out.println("Giliran player " + (timer.getPlayerTurn() + 1) + "!");
        	}



        	/*if (timer.getTime() == totalTime) {
        		timer.setTime(1);
        	}*/

        }

        /*timer.setIsTerminating(true); // tell the thread to stop
		timer.join(); // wait for the thread to stop
		timestamp.setIsTerminating(true); // tell the thread to stop
		timestamp.join(); // wait for the thread to stop*/

    }
}