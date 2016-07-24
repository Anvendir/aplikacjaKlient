package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import pl.kobak.rafal.dicommobile.MainActivity;

/**
 * Created by Rafal on 2016-05-13.
 */
public class CommonHandler implements Runnable
{
    protected String LABEL = this.getClass().getSimpleName();
    protected String m_serverIp;
    public static int m_serverPort;

    public CommonHandler()
    {
        super();
        m_serverIp = MainActivity.s_ipAddress;
        m_serverPort = Integer.parseInt(MainActivity.s_portNumber);
    }

    @Override
    public void run()
    {
    }

    protected void connectToServer()
    {
        try
        {
            InetAddress l_serverAddress = InetAddress.getByName(m_serverIp);
            MainActivity.s_socket = new Socket(l_serverAddress, m_serverPort);

            Log.d(LABEL, "Connection with " + m_serverIp +
                         " in port " + m_serverPort +
                         " successfully established.");
        }
        catch (UnknownHostException e)
        {
            Log.d(LABEL, "Exception occurred: Unknown host: " + m_serverIp + "!");
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred: No I/O!");
        }
        catch (Exception e)
        {
            Log.d(LABEL, "Unknown exception occurred!");
        }
    }
}
