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
    private Socket m_socket;

    public ClientThread(String p_ipAddress, String p_portNumber)
    {
        super();
        m_serverIp = p_ipAddress;
        m_serverPort = Integer.parseInt(p_portNumber);
        m_socket = new Socket();
    }

    @Override
    public void run()
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

        processConnection();
    }

    private void processConnection()
    {
        try
        {
            InputStreamReader l_in = new InputStreamReader(m_socket.getInputStream());
            //char[] buffer = new char[1024];
            //l_in.read(buffer);
            char[] msgIdBuffer = new char[4];
            l_in.read(msgIdBuffer);

            String a = new String(msgIdBuffer);
            a = a.replace("\0", "");
            int foo = Integer.parseInt(a);

            Message m = new Message();
            m.msgId = EMessageId.values()[foo];

            Log.d(LABEL, "rec: " + m.msgId.name());
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred: No I/O!");
        }
    }
}
