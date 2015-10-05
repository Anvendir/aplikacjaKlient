package pl.kobak.rafal.czternastaaplikacja;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obraz = (ImageView) findViewById(R.id.imageView);
        napis = (TextView) findViewById(R.id.textView);
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

    public void kliknieTo_kreuj(View v)
    {
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();

        bmp = Bitmap.createBitmap(szer, wys, Bitmap.Config.ARGB_8888);
        napis.setText("Kreacja mapy " + bmp.getWidth() + " x " + bmp.getHeight());

        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(R.id.button4).setEnabled(true);
        findViewById(R.id.button5).setEnabled(true);
    }

    public void kliknieTo_czysc(View v)
    {
        int marg = 4;
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        bmp.eraseColor(Color.BLACK);
        p.setColor(Color.WHITE);
        c.drawRect( marg, marg, szer - marg, wys - marg, p);
        obraz.setImageBitmap(bmp);
        napis.setText("Czyszczenie mapy " + bmp.getWidth() +
                " x " + bmp.getHeight());
    }

    public void kliknieTo_linie(View v)
    {
        int ile = 200;
        int marg = 4;
        int x1, y1, x2, y2;
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();

        Random r = new Random();
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();

        for (int i = 0; i < ile; i++)
        {
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            x1 = marg + r.nextInt(szer - 2 * marg);
            y1 = marg + r.nextInt(wys - 2 * marg);
            x2 = marg + r.nextInt(szer - 2 * marg);
            y2 = marg + r.nextInt(wys - 2 * marg);
            c.drawLine(x1, y1, x2, y2, p);
        }
        obraz.setImageBitmap(bmp);
        napis.setText("Linie");
    }

    public void kliknieTo_elipsy(View v)
    {
        int ile = 200;
        int marg = 4;
        int x1, y1, x2, y2;
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();

        Random r = new Random();
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);

        for( int i = 0; i < ile; i ++)
        {
            p.setARGB( 255, r.nextInt( 256), r.nextInt( 256), r.nextInt( 256));
            x1 = marg + r.nextInt( szer-2*marg);
            y1 = marg + r.nextInt( wys-2*marg);
            x2 = marg + r.nextInt( szer-2*marg);
            y2 = marg + r.nextInt( wys-2*marg);
            RectF rect = new RectF( x1, y1, x2, y2);
            c.drawOval(rect, p);
        }
        obraz.setImageBitmap(bmp);
        napis.setText("Kółka");
    }
    public void kliknieTo_prostokaty(View v) {
        int ile = 200;
        int marg = 4;
        int x1, y1, x2, y2;
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();

        Random r = new Random();
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < ile; i++) {
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            x1 = marg + r.nextInt(szer - 2 * marg);
            y1 = marg + r.nextInt(wys - 2 * marg);
            x2 = marg + r.nextInt(szer - 2 * marg);
            y2 = marg + r.nextInt(wys - 2 * marg);
            c.drawRect(x1, y1, x2, y2, p);
        }
        obraz.setImageBitmap(bmp);
        napis.setText("Prostokąty");
    }

    private ImageView obraz;
    private TextView napis;
    private Bitmap bmp;
}
