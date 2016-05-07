package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Rafal on 2016-05-05.
 */
public class NetworkWrapper
{
    public NetworkWrapper()
    {
        Log.d(LABEL, "Object of "
                     + this.getClass().getSimpleName()
                     + " class has been created");
    }

    public void connect()
    {
        Log.d(LABEL, "Function connect has been called");
        m_connectionThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    InetAddress l_serverAddr = InetAddress.getByName(SERVER_IP);
                    Socket l_socket = new Socket(l_serverAddr, SERVER_PORT);
                    PrintWriter l_out = new PrintWriter(l_socket.getOutputStream(), true);
                    BufferedReader l_in = new BufferedReader(new InputStreamReader(l_socket.getInputStream()));
                }
                catch (UnknownHostException e)
                {
                    Log.d(LABEL, "Exception occurred: Unknown host: 192.168.1.6");
                }
                catch  (IOException e)
                {
                    Log.d(LABEL, "Exception occurred: No I/O");
                }
            }
        });
        m_connectionThread.start();
    }

    private static final String LABEL = "NetworkWrapper";
    private static final String SERVER_IP = "192.168.1.6";
    private static final int SERVER_PORT = 9878;
    private Thread m_connectionThread;
}
