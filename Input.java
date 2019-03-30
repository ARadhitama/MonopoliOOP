import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Input
{
    private String str = "";

    TimerTask task = new TimerTask()
    {
        public void run()
        {
            if( str.equals("") )
            {
                System.out.println( "you input nothing. exit..." );
                
                // System.exit( 0 );
            }
        }    
    };

    public String getInput() throws Exception
    {
        Timer timer = new Timer();
        timer.schedule( task, 30*1000 );

        // System.out.println( "Input a string within 30 seconds: " );
        System.out.println("Masukkan commandmu dalam 30 detik (Beli | Diam)");
        System.out.print(">> ");
        BufferedReader in = new BufferedReader(
        new InputStreamReader( System.in ) );
        str = in.readLine();
        // command = str;

        timer.cancel();

        return str;
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