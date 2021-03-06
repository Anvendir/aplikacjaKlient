package pl.twoja_domena.rozdzial_11a;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class SunActivity extends ActionBarActivity {
    private String adres = "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_512_4500.jpg";
    private Button przycisk;
    private ImageView obraz;
    private ProgressBar stan;
    private TextView napis;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        przycisk = (Button) findViewById(R.id.button6);
        obraz = (ImageView) findViewById(R.id.imageView2);
        napis = (TextView) findViewById(R.id.textView6);
        stan = (ProgressBar) findViewById(R.id.progressBar2);

        OdczytObrazka oo = new OdczytObrazka();
        oo.execute(adres);
    }

    public void kliknieto_powrot( View v)
    {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    class OdczytObrazka extends AsyncTask<String, Void, Boolean>
    {
        private Bitmap bmp;
        @Override
        protected Boolean doInBackground(String... params) {
            URL u;
            InputStream is;
            boolean ret = true;
            try
            {
                u = new URL( params[0]);
                is = u.openStream();
                Bitmap temp = BitmapFactory.decodeStream(is);
                bmp=temp.copy(Bitmap.Config.ARGB_8888, true);
            }
            catch( Exception e)
            {
                ret = false;
            }
            return ret;
        }

        @Override
        protected void onPreExecute() {
            stan.setVisibility( ProgressBar.VISIBLE);
            przycisk.setEnabled(false);
            napis.setText( "Pobieranie danych ...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if( result)
            {
                Canvas c = new Canvas( bmp);
                Paint p = new Paint();
                int szer = bmp.getWidth(), wys = bmp.getHeight();
                p.setColor(Color.BLUE);
                p.setStyle(Paint.Style.STROKE);
                c.drawRect(0, 0, szer-1, wys-1, p);
                obraz.setImageBitmap( bmp);
                napis.setText( "Pobieranie zakończone. Obraz " +
                        szer + " x " + wys);
            }
            else
            {
                napis.setText( "Błąd podczas pobierania danych.");
            }
            przycisk.setEnabled(true);
            stan.setVisibility( ProgressBar.INVISIBLE);
            super.onPostExecute(result);
        }
    }
}
