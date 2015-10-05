package pl.twoja_domena.rozdzial_11b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class Activity_ISS_Moon_Sun extends ActionBarActivity {
    private ImageView obraz;
    private ProgressBar stan;
    private TextView napis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iss_moon_sun);

        obraz = (ImageView) findViewById(R.id.imageView);
        stan = (ProgressBar) findViewById(R.id.progressBar);
        napis = (TextView) findViewById(R.id.textView);

        Bundle b = getIntent().getExtras();
        String adres_stronki = b.getString( "adres");
        String tytul = b.getString( "tyt");
        setTitle( tytul);
        OdczytObrazka oo = new OdczytObrazka();
        oo.execute( adres_stronki);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_iss_moon_sun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
