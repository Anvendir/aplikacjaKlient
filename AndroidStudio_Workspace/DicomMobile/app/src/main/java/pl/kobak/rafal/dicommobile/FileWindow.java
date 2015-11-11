package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FileWindow extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_window);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_file_window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_item)
    {
        int l_id = p_item.getItemId();
        if (l_id == R.id.action_quit)
        {
            onClick_quit();
            return true;
        }
        else if (l_id == R.id.action_metaData)
        {
            onClick_metadata();
            return true;
        }
        else if (l_id == R.id.action_aboutFile)
        {
            onClick_aboutFile();
            return true;
        }
        else if (l_id == R.id.action_fullMetaData)
        {
            onClick_fullMetadata();
            return true;
        }
        else if (l_id == R.id.action_render)
        {
            onClick_rendering();
            return true;
        }
        return super.onOptionsItemSelected(p_item);
    }

    public void onClick_metadata()
    {
        Intent l_intent = new Intent(this, Metadata.class);
        startActivity(l_intent);
    }

    public void onClick_aboutFile()
    {
        Intent l_intent = new Intent(this, AboutFile.class);
        startActivity(l_intent);
    }

    public void onClick_fullMetadata()
    {
        Intent l_intent = new Intent(this, FullMetadata.class);
        startActivity(l_intent);
    }

    public void onClick_rendering()
    {
        Intent l_intent = new Intent(this, Rendering.class);
        startActivity(l_intent);
    }

    public void onClick_quit()
    {
        finish();
    }
}
