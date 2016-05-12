package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Rafal on 2016-05-05.
 */
public class NetworkWrapper
{
    final String LABEL = getClass().getSimpleName();

    public NetworkWrapper()
    {
    }

    public void connect(String p_ipAddress,
                        String p_portNumber)
    {
        Log.d(LABEL, "Function connect has been called");
        Thread l_connectionThread = new Thread(new ClientThread(p_ipAddress, p_portNumber));
        l_connectionThread.start();
    }
}

class ClientThread implements Runnable
{
    final String LABEL = this.getClass().getSimpleName();
    private String m_serverIp;
    private int m_serverPort;

    public ClientThread(String p_ipAddress, String p_portNumber)
    {
        super();
        m_serverIp = p_ipAddress;
        m_serverPort = Integer.parseInt(p_portNumber);
    }

    @Override
    public void run()
    {
        try
        {
            InetAddress l_serverAddr = InetAddress.getByName(m_serverIp);
            Socket l_socket = new Socket(l_serverAddr, m_serverPort);
            Log.d(LABEL, "Bla");

            InputStreamReader l_in = new InputStreamReader(l_socket.getInputStream());
            Log.d(LABEL, "Bla");
            char[] buffer = new char[1024];
            int bla = l_in.read(buffer);
            Log.d(LABEL, "X");
            for (int i = 0; i < 40; i++)
            {
               Log.d(LABEL, "rec: " + buffer[i]);
            }
        }
        catch (UnknownHostException e)
        {
            Log.d(LABEL, "Exception occurred: Unknown host: " + m_serverIp);
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred: No I/O");
        }
    }
}
