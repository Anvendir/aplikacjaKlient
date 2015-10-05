package pl.kobak.rafal.dziesiataaplikacja;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

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
        int r = 200;

        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
        
        canvas.drawRect(szer/2-r, wys/2-r, szer/2+r, wys/2+r, p);

        p.setColor(Color.RED);
        canvas.drawCircle(szer/2, wys/2, r, p);

        super.onDraw(canvas);
    }
}
