package pl.kobak.rafal.osiemnastaaplikacja;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class Activity_ISS_Moon_Sun extends Activity
{
    private ImageView obraz;
    private ProgressBar stan;
    private TextView napis;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__iss__moon__sun);

        obraz = (ImageView) findViewById(R.id.imageView);
        stan = (ProgressBar) findViewById(R.id.progressBar);
        napis = (TextView) findViewById(R.id.textView);

        Bundle b = getIntent().getExtras();
        String adres_stronki = b.getString( "adres");
        String tytul = b.getString( "tyt");
        setTitle(tytul);
        OdczytObrazka oo = new OdczytObrazka();
        oo.execute( adres_stronki);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__iss__moon__sun, menu);
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

    class OdczytObrazka extends AsyncTask<String, Void, Boolean> {
        private Bitmap bmp;

        @Override
        protected Boolean doInBackground(String... params) {
            URL u;
            InputStream is;
            boolean ret = true;
            try {
                u = new URL(params[0]);
                is = u.openStream();
                Bitmap temp = BitmapFactory.decodeStream(is);
                bmp = temp.copy(Bitmap.Config.ARGB_8888, true);
            } catch (Exception e) {
                ret = false;
            }
            return ret;
        }

        @Override
        protected void onPreExecute() {
            stan.setVisibility(ProgressBar.VISIBLE);
            napis.setText("Pobieranie danych ...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Canvas c = new Canvas(bmp);
                Paint p = new Paint();
                int szer = bmp.getWidth(), wys = bmp.getHeight();
                p.setColor(Color.WHITE);
                p.setStyle(Paint.Style.STROKE);
                c.drawRect(0, 0, szer - 1, wys - 1, p);
                obraz.setImageBitmap(bmp);
                napis.setText("Pobieranie zakończone. Obraz " +
                        szer + " x " + wys);
            } else {
                napis.setText("Błąd podczas pobierania danych.");
            }
            stan.setVisibility(ProgressBar.INVISIBLE);
            super.onPostExecute(result);
        }
    }
}
