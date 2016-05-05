package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class History extends Activity
{
    @Override
    protected void onCreate(Bundle p_savedInstanceState)
    {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu p_menu)
    {
        getMenuInflater().inflate(R.menu.menu_history, p_menu);
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

    public void onClick_openFile(View p_view)
    {
    }

    public void onClick_cancel(View p_view)
    {
        finish();
    }
}
