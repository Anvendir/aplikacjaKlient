package pl.twoja_domena.rozdzial_10a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
    private Button przycisk;
    private ImageView obraz;
    private TextView napis;
    private ProgressBar progres;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar ab = getSupportActionBar();     //ikona aplikacji
//        ab.setDisplayShowHomeEnabled(true);
//        ab.setIcon(R.mipmap.ic_launcher);

        przycisk = (Button) findViewById(R.id.button);
        obraz = (ImageView) findViewById(R.id.imageView);
        napis = (TextView) findViewById(R.id.textView);
        progres = (ProgressBar) findViewById(R.id.progressBar);

        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.palmy);
        bmp = temp.copy(Bitmap.Config.ARGB_8888, true);
    }

    public void kliknieto_start(View v) {
        przycisk.setEnabled(false);
        Proces p = new Proces();
        p.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    class Proces extends AsyncTask<Void, Integer, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            Integer[] procent = new Integer[1];
            int ile = 5000;
            int szer = bmp.getWidth(), wys = bmp.getHeight();
            int x, y, kolor;
            int d = 20;
            Canvas c = new Canvas(bmp);
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            Random rand = new Random();
            for( int i = 0; i < ile; i ++)
            {
                x = rand.nextInt( szer);
                y = rand.nextInt( wys);
                kolor = bmp.getPixel(x, y);
                p.setColor( kolor);
                c.drawLine( x-d, y-d, x+d, y+d, p);
                c.drawLine( x-d, y+d, x+d, y-d, p);
                if( i % 100 == 0) {
                    procent[0]=i * 100 / ile;
                    publishProgress( procent);
                }
            }
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.STROKE);
            c.drawRect(0, 0, szer - 1, wys - 1, p);
            return null;
        }

        @Override
        protected void onPostExecute( Void aVoid) {
            progres.setVisibility(ProgressBar.INVISIBLE);
            obraz.setImageBitmap(bmp);
            napis.setText( "Koniec!");
            przycisk.setEnabled( true);
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate( Integer... values) {
            int procent = values[0];
            napis.setText( "Stan: " + procent + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            progres.setVisibility( ProgressBar.VISIBLE);
            super.onPreExecute();
        }
    }
}



















/*        @Override
        protected void onPreExecute() {
            stan.setVisibility( ProgressBar.VISIBLE);
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            int i, ile = 5000;
            Integer[] procent = new Integer[1];
            int szer = bmp.getWidth(), wys = bmp.getHeight();
            int x, y, kolor;
            int d = 20;
            Canvas c = new Canvas(bmp);
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            Random rand = new Random();
            for( i = 0; i < ile; i ++)
            {
                x = rand.nextInt( szer);
                y = rand.nextInt( wys);
                kolor = bmp.getPixel(x, y);
                p.setColor( kolor);
                c.drawLine( x-d, y-d, x+d, y+d, p);
                c.drawLine( x-d, y+d, x+d, y-d, p);
                if( i % 100 == 0) {
                    procent[0]=i * 100 / ile;
                    publishProgress( procent);
                }
            }
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.STROKE);
            c.drawRect(0, 0, szer - 1, wys - 1, p);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            stan.setVisibility(ProgressBar.INVISIBLE);
            obraz.setImageBitmap(bmp);
            napis.setText("Koniec!");
            przycisk.setEnabled(true);
            super.onPostExecute(aVoid);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            napis.setText( "Stan: " + values[0] + "%");
            super.onProgressUpdate(values);
        }
    }
}
*/
