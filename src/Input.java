import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Input
{
    private String str = "";
    private boolean empty = false;
    // private MonopoliOOPUI mainPage;

    TimerTask task = new TimerTask()
    {
        int seconds = 30;
        int i = 0;

        public void run()
        {
            i++;

            if (str.equals(""))
            {
                System.out.println( "Time limit! Next player turn" );
                System.out.println( "Press [ENTER] untuk lanjut bermain" );

                empty = true;
            }

            if (i % seconds != 0) {
                // edit timer in main page
                // this.mainPage.editTimer(seconds - i -> toString()); atau biarin aja int
            } else {
                // edit timer in main page
                // this.mainPage.editTimer("Time limit!");
            }

        }    
    };

    public String getInput(boolean isOwner) throws Exception
    {
        Timer timer = new Timer();
        timer.schedule(task, 30 * 1000);

        // this.mainPage = mainPage;
        // sambungin main page ke sini (sebagai parameter?)

        if (!isOwner) {
            System.out.println("Masukkan commandmu dalam 30 detik (Beli | Diam)");
            // set visible
            // invisible upgrade button
        } else {
            System.out.println("Mau beli rumah? (Beli | Diam)");
            // all button visible
        }

        // kalo misal ada actionperformed dari button, maka ... while (!isButtonClicked) {Sys.out("wait for command")}
        // get command dari attribute command di dalem Main page

        System.out.print(">> ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        timer.cancel();

        if (this.empty) {
            return "diam";
        } else {
            return str.toLowerCase();
        }
    }
}