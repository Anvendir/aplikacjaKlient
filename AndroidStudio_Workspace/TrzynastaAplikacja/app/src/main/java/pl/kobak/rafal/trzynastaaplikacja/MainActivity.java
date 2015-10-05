package pl.kobak.rafal.trzynastaaplikacja;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obraz = (ImageView) findViewById(R.id.imageView);
        napis = (TextView) findViewById(R.id.textView);
        Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.palmy);
        bmp = tmp.copy(Bitmap.Config.ARGB_8888, true);
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

    private ImageView obraz;
    private TextView napis;
    private Bitmap bmp;

    public void kliknij(View v)
    {
        int szerIV = obraz.getWidth();
        int wysIV = obraz.getHeight();
        int szerBmp = bmp.getWidth();
        int wysBmp = bmp.getHeight();

        rysuj();
        obraz.setImageBitmap(bmp);

        napis.setText("ImageView: " +
                      szerIV + "x" + wysIV +
                      "Rozmiar bitmapy: " +
                      szerBmp + "x" + wysBmp);
    }

    private void rysuj()
    {
        int szer = bmp.getWidth();
        int wys = bmp.getHeight();
        int r, g, b, kolor;
        Canvas c = new Canvas(bmp);
        Paint p = new Paint();

        for (int x = 0; x < szer; x++)
        {
            for (int y = 0; y < wys; y++)
            {
                kolor = bmp.getPixel(x, y);
                r = Color.red(kolor);
                g = Color.green(kolor);
                b = Color.blue(kolor);

                int prog = 120;
                if (r < prog || g < prog || b < prog)
                {
                    r = g= b = 0;
                }
                else
                    r = g = b = 255;
                bmp.setPixel(x, y, Color.rgb(r, g, b));
            }
        }

        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(0, 0, szer-1, wys-1, p);
    }
}
