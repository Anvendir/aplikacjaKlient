package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle p_savedInstanceState)
    {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu p_menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, p_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_item)
    {
        int l_id = p_item.getItemId();

        if(l_id == R.id.action_opsenFile)
        {
            Intent l_openFile = new Intent(this, OpenFile.class);
            startActivity(l_openFile);
            return true;
        }
        if(l_id == R.id.action_connectToServer)
        {
            Intent l_connectToServer = new Intent(this, ConnectToServer.class);
            startActivity(l_connectToServer);
            return true;
        }
        if(l_id == R.id.action_aboutDicom)
        {
            Intent l_aboutDicomView = new Intent(this, AboutDicom.class);
            startActivity(l_aboutDicomView);
            return true;
        }
        if(l_id == R.id.action_language)
        {
            Intent l_language = new Intent(this, Language.class);
            startActivity(l_language);
            return true;
        }
        if(l_id == R.id.action_aboutApp)
        {
            Intent l_aboutApp = new Intent(this, AboutApplication.class);
            startActivity(l_aboutApp);
            return true;
        }

        if(l_id == R.id.action_settings)
        {
            Intent l_settings = new Intent(this, Settings.class);
            startActivity(l_settings);
            return true;
        }
        if(l_id == R.id.action_quit)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(p_item);
    }

    public void onClick_quit(View p_view)
    {
        finish();
    }
}
