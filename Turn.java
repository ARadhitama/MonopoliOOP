public class Turn<T> extends Thread {
	private T t;				// Timer
	private int totalPlayer;
	private int turn = 1;
	private int player = 0;		// Index dimulai dari 0
	private String command;

	public Turn(int totalPlayer, T t) {
		this.totalPlayer = totalPlayer;
		this.t = t;
		this.command = "diam";
	}

	public int getTurn() {
		return this.turn;
	}

	public int getPlayer() {
		return this.player;
	}

	public int getTotalPlayer() {
		return this.totalPlayer;
	}

	public T getT() {
		return this.t;
	}

	/*public int getTime() {
		return this.t.getTime();
	}*/

	public String getCommand() {
		return this.command;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}

	public void nextPlayer() {
		this.player = (this.player + 1) % this.totalPlayer;
	}

	public void run() {
        synchronized (t) {
            while (true) {
                try {
                    t.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}