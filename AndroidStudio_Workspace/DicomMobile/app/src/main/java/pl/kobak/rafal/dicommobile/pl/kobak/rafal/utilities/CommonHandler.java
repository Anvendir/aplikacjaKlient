package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Rafal on 2016-05-13.
 */
public class CommonHandler implements Runnable
{
    protected String LABEL = this.getClass().getSimpleName();
    protected String m_serverIp;
    protected int m_serverPort;
    protected Socket m_socket;

    public CommonHandler(String p_ipAddress, String p_portNumber)
    {
        super();
        m_serverIp = p_ipAddress;
        m_serverPort = Integer.parseInt(p_portNumber);
        m_socket = new Socket();
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
            m_socket = new Socket(l_serverAddress, m_serverPort);

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
