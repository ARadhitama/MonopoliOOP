public class Timer extends Thread {
    private int time = 1;
    private boolean setting = false;

    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.print(time + " ");
            setTime(time + 1);
            // Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
        
            // Sambungin Main page ke sini
            // Ntar setiap setTime() akak nge-trigger setTimer() di UI
        }
    }

    public synchronized int getTime() {
        while (setting) {
            try {
                wait(); // This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }

        return time;
    }

    public synchronized void setTime(int t) {
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }
}