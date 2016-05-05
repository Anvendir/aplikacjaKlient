package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.NetworkWrapper;

public class ConnectToServer extends Activity
{

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
        int id = p_item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(p_item);
    }

    public void onClick_connect(View p_view)
    {
        NetworkWrapper l_networkWrapper = new NetworkWrapper();
        l_networkWrapper.connect();
    }
}
