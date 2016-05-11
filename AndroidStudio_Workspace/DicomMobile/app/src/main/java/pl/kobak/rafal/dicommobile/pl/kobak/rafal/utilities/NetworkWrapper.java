package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * Created by Rafal on 2016-05-05.
 */
public class NetworkWrapper
{
    static final String LABEL = "NetworkWrapper";

    public NetworkWrapper()
    {
        Log.d(LABEL, "Object of "
                     + this.getClass().getSimpleName()
                     + " class has been created");
    }

    public void connect()
    {
        Log.d(LABEL, "Function connect has been called");
        Thread l_connectionThread = new Thread(new ClientThread());
        l_connectionThread.start();
    }
}

class ClientThread implements Runnable
{
    private static final String SERVER_IP = "192.168.1.6";
    private static final int SERVER_PORT = 9878;
    static final String LABEL = "NetworkWrapper";

    @Override
    public void run()
    {
        try
        {
            InetAddress l_serverAddr = InetAddress.getByName(SERVER_IP);
            Socket l_socket = new Socket(l_serverAddr, SERVER_PORT);
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
            Log.d(LABEL, "Exception occurred: Unknown host: 192.168.1.6");
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred: No I/O");
        }
    }
}
