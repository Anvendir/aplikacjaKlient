package pl.kobak.rafal.dicommobile;

import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.IpAddressValidator;
import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.ServerConnectionHandler;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectToServer extends Activity
{
    final String LABEL = getClass().getSimpleName();
    private IpAddressValidator m_addressValidator;

    public ConnectToServer()
    {
        super();
        m_addressValidator = new IpAddressValidator();
    }

    @Override
    protected void onCreate(Bundle p_savedInstanceState)
    {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.activity_connect_to_server);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu p_menu)
    {
        getMenuInflater().inflate(R.menu.menu_connect_to_server, p_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_item)
    {
        return super.onOptionsItemSelected(p_item);
    }

    public void onClick_connect(View p_view)
    {
        EditText l_ipAddressEditText = (EditText) findViewById(R.id.ipAddressTextEdit);
        String l_ipAddress = l_ipAddressEditText.getText().toString();

        EditText l_portNumberEditText = (EditText) findViewById(R.id.portNumberEditText);
        String l_portNumber = l_portNumberEditText.getText().toString();

        logReceivedAddress(l_ipAddress, l_portNumber);
        boolean l_isValid = m_addressValidator.validateServerAddress(l_ipAddress, l_portNumber);
        if (!l_isValid)
        {
            Log.d(LABEL, "Invalid ip address or port number!");

            l_ipAddressEditText.getText().clear();
            l_ipAddressEditText.setHint(getString(R.string.incorrectData));
            l_portNumberEditText.getText().clear();
            l_portNumberEditText.setHint(getString(R.string.incorrectData));

            return;
        }

        MainActivity.s_ipAddress = l_ipAddress;
        MainActivity.s_portNumber = l_portNumber;

        Thread l_connectionThread = new Thread(new ServerConnectionHandler());
        l_connectionThread.start();
        while(l_connectionThread.isAlive()) {}

        TextView l_textView = (TextView) this.findViewById(R.id.welcomeMessageTextView);
        l_textView.setText(MainActivity.s_welcomeMessage);
    }

    public void onClick_clear(View p_view)
    {
        EditText l_ipAddressEditText = (EditText) findViewById(R.id.ipAddressTextEdit);
        l_ipAddressEditText.getText().clear();
        l_ipAddressEditText.setHint(getString(R.string.hint_ipAddress));

        EditText l_portNumberEditText = (EditText) findViewById(R.id.portNumberEditText);
        l_portNumberEditText.getText().clear();
        l_portNumberEditText.setHint(R.string.hint_portNumber);
    }

    private void logReceivedAddress(String p_ipAddress, String p_portNumber)
    {
        Log.d(LABEL, "Ip address: " + p_ipAddress);
        Log.d(LABEL, "Port number: " + p_portNumber);
    }
}
