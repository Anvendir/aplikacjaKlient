package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OpenFileLocally extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_locally);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_open_file_locally, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("Kurwa", "Directory not created");
        }
        return file;
    }

    public void onClick_openFile(View p_view)
    {
        /*String FILENAME = "hello_file";
        String string = "hello world!";

        Log.d("Kurwa", "Bla");
        File l = getFilesDir();
        Log.d("Kurwa", "Dir " + l);

        Log.d("Kurwa", "Dir " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        Log.d("Kurwa", "Dir " + getAlbumStorageDir("razDwaTrzy"));*/

        //This will get the SD Card directory and create a folder named MyFiles in it.




        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/mojePliki");
        directory.mkdirs();

        //Now create the file in the above directory and write the contents into it
        File file = new File(directory, "bla.txt");
        FileOutputStream fOut = null;
        try
        {
            fOut = new FileOutputStream(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        OutputStreamWriter osw = new OutputStreamWriter(fOut);

        try
        {
            osw.write("jakas tam zawartosc sidfsaidhf ashdf kalsfha lhsfalsdf halsf");
            osw.flush();
            osw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


       /* boolean x = isExternalStorageWritable();
        if(x)
        {
            Log.d("Kurwa", "true");
        }
        else
        {
            Log.d("Kurwa", "false");
        }

        FileOutputStream fos = null;
        try
        {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            fos.write(string.getBytes());
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }*/

        //Intent l_openFile = new Intent(this, FileWindow.class);
        //startActivity(l_openFile);
    }

    public void onClick_cancel(View p_view)
    {
        finish();
    }
}
