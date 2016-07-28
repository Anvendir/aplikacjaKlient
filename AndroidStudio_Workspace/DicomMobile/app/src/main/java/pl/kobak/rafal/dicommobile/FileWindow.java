package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;

public class FileWindow extends Activity
{
    protected String LABEL = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_window);

        ImageView l_imageView = (ImageView) this.findViewById(R.id.ImageView);
        String l_rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String l_path = l_rootPath + File.separator + "mojePliki" + File.separator + MainActivity.s_chosenFileName + ".png";
        Log.d(LABEL, l_path);

        Bitmap l_bmp = BitmapFactory.decodeFile(l_path);
        if(l_bmp == null)
        {
            Log.d(LABEL, "Nie udalo sie wczytac pliku: " + MainActivity.s_chosenFileName + ".png !");
        }
        l_imageView.setImageBitmap(l_bmp);
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
