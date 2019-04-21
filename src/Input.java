import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Input
{
    private String str = "";
    private boolean empty = false;
    private MonopoliOOPUI mainPage;
    private LogPage logPage;

    TimerTask task = new TimerTask()
    {
        int seconds = 30;
        int i = 0;

        public void run()
        {
            i++;

            logPage.appendLog(String.valueOf(seconds - i));

            /*if (str.equals(""))
            {
                logPage.appendLog( "Time limit! Next player turn" );
                logPage.appendLog( "Press [ENTER] untuk lanjut bermain" );

                empty = true;
            }*/

            if (i % seconds != 0) {
                logPage.appendLog("Time limit! Next player turn");

                empty = true;
                // edit timer in main page
                // this.mainPage.editTimer(seconds - i -> toString()); atau biarin aja int
            } else {
                logPage.appendLog(String.valueOf(seconds - i));
                // edit timer in main page
                // this.mainPage.editTimer("Time limit!");
            }

        }    
    };

    public String getInput(boolean isOwner, MonopoliOOPUI mainPage, LogPage logPage, String kind, String ownerName, String playerName, String tileName) throws Exception
    {
        this.mainPage = mainPage;
        this.logPage = logPage;

        Timer timer = new Timer();
        timer.schedule(task, 30 * 1000);

        if (!kind.equals("Space")) {
            if (!isOwner) {
                this.logPage.appendLog("Masukkan commandmu dalam 30 detik (Beli | Diam)");
                this.mainPage.setRollButtonEnabled(true);
                this.mainPage.setBuyButtonEnabled(true);
            } else {
                this.logPage.appendLog("Mau beli rumah? (Beli | Diam)");
                this.mainPage.setRollButtonEnabled(true);
                this.mainPage.setUpgradeButtonEnabled(true);
            }
        } else {
            if (tileName.equals("Free Parking")) {
                this.logPage.appendLog("Mendarat di Free Parking");
                // Bikin textfield untuk input mau mendarat di berapa
            } else {
                this.logPage.appendLog("Mendarat di Space, hanya bisa roll");
                this.mainPage.setRollButtonEnabled(true);
            }
        }

        while (!this.mainPage.getIsButtonClicked() && !this.empty) {
            System.out.println("waiting for command");
        }

        // kalo misal ada actionperformed dari button, maka ... while (!isButtonClicked) {Sys.out("wait for command")}
        // get command dari attribute command di dalem Main page

        this.logPage.appendLog(">> ");
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = this.mainPage.getCommand();
        this.logPage.appendLog(str);

        timer.cancel();

        this.mainPage.setIsButtonClicked(false);
        this.mainPage.setRollButtonEnabled(false);
        this.mainPage.setBuyButtonEnabled(false);
        this.mainPage.setUpgradeButtonEnabled(false);

        if (this.empty) {
            return "diam";                       // gayakin
        } else {
            return str.toLowerCase();
        }
    }
}