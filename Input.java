import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Input
{
    private String str = "";
    private boolean empty = false;

    TimerTask task = new TimerTask()
    {
        public void run()
        {
            if( str.equals("") )
            {
                System.out.println( "Time limit! Next player turn" );
                System.out.println( "Press [ENTER] untuk lanjut bermain" );

                empty = true;
                // System.exit( 0 );
            }
        }    
    };

    public String getInput(boolean isOwner) throws Exception
    {
        Timer timer = new Timer();
        timer.schedule( task, 30*1000 );

        if (!isOwner) {
            System.out.println("Masukkan commandmu dalam 30 detik (Beli | Diam)");
        } else {
            System.out.println("Mau beli rumah? (Beli | Diam)");
        }
        System.out.print(">> ");
        BufferedReader in = new BufferedReader(
        new InputStreamReader( System.in ) );
        str = in.readLine();
        // command = str;

        timer.cancel();

        if (this.empty) {
            return "diam";
        } else {
            return str.toLowerCase();
        }
        // System.out.println( "you have entered: "+ str ); 
    }

    /*public static void main( String[] args )
    {
        try
        {
            (new test()).getInput();
        }
        catch( Exception e )
        {
            System.out.println( e );
        }
        System.out.println( "main exit..." );
    }*/
}