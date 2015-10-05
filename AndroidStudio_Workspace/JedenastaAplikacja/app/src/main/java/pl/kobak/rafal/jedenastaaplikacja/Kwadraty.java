package pl.kobak.rafal.jedenastaaplikacja;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Rafal on 2015-09-27.
 */
public class Kwadraty extends View
{
    public Kwadraty(Context context)
    {
        super(context);
    }

    public Kwadraty(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public Kwadraty(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int szer = getWidth();
        int wys = getHeight();

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        Random r = new Random();

        for (int i = 0; i < 10; i++)
        {
            p.setARGB(255,
                      r.nextInt(256),
                      r.nextInt(256),
                      r.nextInt(256));

            canvas.drawRect(r.nextInt(szer)-r.nextInt(100),
                            r.nextInt(wys)-r.nextInt(100),
                            r.nextInt(szer)+r.nextInt(100),
                            r.nextInt(wys)+r.nextInt(100),
                            p);
        }

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setColor(Color.YELLOW);
        canvas.drawRect(2, 2, szer - 3, wys - 3, p);
        super.onDraw(canvas);
    }
}
