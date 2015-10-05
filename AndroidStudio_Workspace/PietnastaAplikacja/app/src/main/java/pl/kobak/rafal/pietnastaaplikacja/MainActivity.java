package pl.kobak.rafal.pietnastaaplikacja;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = (Button) findViewById(R.id.button);
        obraz = (ImageView) findViewById(R.id.imageView);
        napis = (TextView) findViewById(R.id.textView);
        Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.palmy);
        bmp = tmp.copy(Bitmap.Config.ARGB_8888, true);
        progres = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ImageView obraz;
    private TextView napis;
    private Button przycisk;
    private Bitmap bmp;
    private ProgressBar progres;

    public void kliknieTo_start(View v)
    {
        przycisk.setEnabled(false);
        Proces p = new Proces();
        p.execute();
    }

    class Proces extends AsyncTask<Void, Integer, Void>
    {
        @Override
        protected Void doInBackground(Void... params)
        {
            Integer[] procent = new Integer[1];
            int ile = 600000;
            int szer = bmp.getWidth();
            int wys = bmp.getHeight();
            int x, y, kolor;
            int d = 20;
            Canvas c = new Canvas(bmp);
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            Random rand = new Random();

            for (int i = 0; i < ile; i++)
            {
                x = rand.nextInt(szer);
                y = rand.nextInt(wys);
                kolor = bmp.getPixel(x, y);
                p.setColor(kolor);
                c.drawLine(x - d, y - d, x + d, y + d, p);
                c.drawLine(x - d, y + d, x + d, y - d, p);
                if (i % 100 == 0)
                {
                    procent[0] = i * 100 / ile;
                    publishProgress(procent);
                }
            }
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.STROKE);
            c.drawRect(0, 0, szer - 1, wys - 1, p);
            return null;
        }

        @Override
        protected void onPreExecute()
        {
            progres.setVisibility(ProgressBar.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progres.setVisibility(ProgressBar.INVISIBLE);
            obraz.setImageBitmap(bmp);
            napis.setText("Koniec!");
            przycisk.setEnabled(true);
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            napis.setText("Stan: " + values[0] + "%");
            super.onProgressUpdate(values);
        }
    }
}
