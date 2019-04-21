import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Input
{
    private String str = "";
    private boolean empty = false;
    private LogPage logPage;
    
    TimerTask task = new TimerTask()
    {
        public void run()
        {
            if (str.equals(""))
            {
                logPage.appendLog( "Time limit! Next player turn" );
                logPage.appendLog( "Press [ENTER] untuk lanjut bermain" );

                empty = true;
            }
        }    
    };

    public String getInput(boolean isOwner) throws Exception
    {
        Timer timer = new Timer();
        timer.schedule(task, 30*1000);

        if (!isOwner) {
            logPage.appendLog("Masukkan commandmu dalam 30 detik (Beli | Diam)");
        } else {
            logPage.appendLog("Mau beli rumah? (Beli | Diam)");
        }
        logPage.appendLog(">> ");
        BufferedReader in = new BufferedReader(
        new InputStreamReader( System.in ) );
        str = in.readLine();

        timer.cancel();

        if (this.empty) {
            logPage.appendLog ("diam");
            return 0;                       // gayakin
        } else {
            return str.toLowerCase();
        }
    }
}