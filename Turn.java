public class Turn extends Thread {
	// private Timer timer;
	private int totalPlayer;
	private int turn = 1;
	private int player = 0;		// Index dimulai dari 0

	public Turn(int totalPlayer) {
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

	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}

	public void nextPlayer() {
		this.player = (this.player + 1) % this.totalPlayer;
	}

/*	public void run() {
        synchronized (timer) {
            while (true) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (timer.getTime() == 30) {
                	timer.setTime(1);
                }
            }
        }
    }*/
}