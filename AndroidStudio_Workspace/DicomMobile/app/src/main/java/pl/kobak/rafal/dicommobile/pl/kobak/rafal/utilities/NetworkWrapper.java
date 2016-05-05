package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

import java.net.Socket;

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
        Socket l_socket = new Socket();
    }

    private static final String LABEL = "NetworkWrapper";
}
