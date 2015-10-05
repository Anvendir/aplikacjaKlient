package pl.twoja_domena.rozdzial_10b;

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


public class MainActivity extends ActionBarActivity {
    private String adres = "http://www2.heavens-above.com/orbitdisplay.aspx?icon=iss&width=300&height=300&satid=25544";
    private Button przycisk;
    private ImageView obraz;
    private ProgressBar stan;
    private TextView napis;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = (Button) findViewById(R.id.button1);
        obraz = (ImageView) findViewById(R.id.imageView1);
        stan = (ProgressBar) findViewById(R.id.progressBar1);
        napis = (TextView) findViewById(R.id.textView1);
    }
    public void kliknieto_sciagaj( View v)
    {
        OdczytObrazka oo = new OdczytObrazka();
        oo.execute( adres);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                p.setColor(Color.WHITE);
                p.setStyle(Paint.Style.STROKE);
                c.drawRect(0, 0, szer-1, wys-1, p);

                obraz.setImageBitmap( bmp);
                napis.setText( "Pobieranie zakończone. Obraz " + szer + " x " + wys);
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
