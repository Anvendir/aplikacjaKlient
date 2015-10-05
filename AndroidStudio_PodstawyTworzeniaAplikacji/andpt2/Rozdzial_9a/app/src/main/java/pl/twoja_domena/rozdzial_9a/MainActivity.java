package pl.twoja_domena.rozdzial_9a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private ImageView obraz;
    private TextView napis;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obraz = (ImageView) findViewById(R.id.imageView);
        napis = (TextView) findViewById(R.id.textView);
        Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.palmy);
        bmp = tmp.copy(Bitmap.Config.ARGB_8888, true);
    }

    public void kliknij( View v)
    {
        rysuj();
//        rysuj_kolka();
//        rysuj_kreski();
        obraz.setImageBitmap(bmp);
        napis.setText( "ImageView: " + obraz.getWidth() + " x " + obraz.getHeight() +
                       ", bitmapa: "+ bmp.getWidth() + " x " + bmp.getHeight());
    }

    private void rysuj()
    {
        int szer = bmp.getWidth(), wys = bmp.getHeight();
        int kolor, r, g, b;
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();
        for( int x = 0; x < szer; x ++)
        {
            for( int y = 0; y < wys; y ++)
            {
                kolor = bmp.getPixel(x, y);
                r = Color.red( kolor);
                g = Color.green(kolor);
                b = Color.blue(kolor);
                bmp.setPixel(x, y, Color.rgb( b, g, r));
            }
        }
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(0, 0, szer-1, wys-1, p);
    }
    private void rysuj_kolka()
    {
        int ile = 1000;
        int szer = bmp.getWidth(), wys = bmp.getHeight();
        int x, y, kolor;
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
            c.drawCircle( x,  y, 10, p);
        }
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(0, 0, szer-1, wys-1, p);
    }
    private void rysuj_kreski()
    {
        int ile = 1000;
        int szer = bmp.getWidth(), wys = bmp.getHeight();
        int x, y, kolor, r, g, b;
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
            r = Color.red( kolor);
            g = Color.green(kolor);
            b = Color.blue(kolor);
            c.drawLine( x-d, y-d, x+d, y+d, p);
            c.drawLine( x-d, y+d, x+d, y-d, p);
        }
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(0, 0, szer-1, wys-1, p);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
