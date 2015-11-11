package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import java.util.Random;

public class Language extends Activity
{
    public Language()
    {
        m_maxRadius = 150;
        m_minRadius = 10;
        m_numberOfCircles = 500;
        m_rand = new Random();
    }

    @Override
    protected void onCreate(Bundle p_savedInstanceState)
    {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.activity_language);

        m_width = getResources().getDisplayMetrics().widthPixels;
        m_height = getResources().getDisplayMetrics().heightPixels;
        m_bmp = Bitmap.createBitmap(m_width, m_height, Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_bmp);
        m_paint = new Paint();
        m_paint.setAntiAlias(true);
        m_paint.setStyle(Paint.Style.FILL);
    }

    public void onClick_english(View p_view)
    {
        prepareEnglishBackground();
        setEnglishLanguage();
    }

    public void onClick_polish(View p_view)
    {
        preparePolishBackground();
        setPolishLanguage();
    }

    public void onClick_german(View p_view)
    {
        prepareGermanBackground();
        setGermanLanguage();
    }

    private void prepareEnglishBackground()
    {
        m_bmp.eraseColor(Color.GRAY);

        for (int i = 0; i < m_numberOfCircles / 3; i++)
        {
            drawEnglishBlueCircle();
            drawEnglishRedCircle();
            drawEnglishWhiteCircle();
        }
        setBackground();
    }

    private void setEnglishLanguage()
    {
        //do implementacji na koncu
    }

    private void preparePolishBackground()
    {
        m_bmp.eraseColor(Color.GRAY);

        for (int i = 0; i < m_numberOfCircles / 2; i++)
        {
            drawPolishRedCircle();
            drawPolishWhiteCircle();
        }
        setBackground();
    }

    private void setPolishLanguage()
    {
        //do implementacji na koncu
    }

    private void prepareGermanBackground()
    {
        m_bmp.eraseColor(Color.GRAY);

        for (int i = 0; i < m_numberOfCircles / 3; i++)
        {
            drawGermanRedCircle();
            drawGermanBlackCircle();
            drawGermanGoldCircle();
        }
        setBackground();
    }

    private void setGermanLanguage()
    {
        //do implementacji
    }

    private void setBackground()
    {
        Drawable l_drawable = new BitmapDrawable(getResources(), m_bmp);
        getWindow().getDecorView().setBackground(l_drawable);
    }

    private void drawCircle()
    {
        m_canvas.drawCircle(m_rand.nextInt(m_width),
                m_rand.nextInt(m_height),
                m_rand.nextInt(m_maxRadius - m_minRadius) + m_minRadius,
                m_paint);
    }

    private void drawPolishRedCircle()
    {
        //Red color from Polish flag: #D4213D
        m_paint.setARGB(255, 212, 33, 61);
        drawCircle();
    }

    private void drawPolishWhiteCircle()
    {
        //White color from Polish flag: #E9E8E7
        m_paint.setARGB(255, 233, 232, 231);
        drawCircle();
    }

    private void drawGermanBlackCircle()
    {
        //Black color from German flag: #000000
        m_paint.setARGB(255, 0, 0, 0);
        drawCircle();
    }

    private void drawGermanRedCircle()
    {
        //Red color from German flag: #FF0000
        m_paint.setARGB(255, 255, 0, 0);
        drawCircle();
    }

    private void drawGermanGoldCircle()
    {
        //Golden color from German flag: #FFCC00
        m_paint.setARGB(255, 255, 204, 0);
        drawCircle();
    }

    private void drawEnglishBlueCircle()
    {
        //blue color from United Kingdom flag: #00247D
        m_paint.setARGB(255, 0, 36, 125);
        drawCircle();
    }

    private void drawEnglishRedCircle()
    {
        //Red color from United Kingdom flag: #CF142B
        m_paint.setARGB(255, 207, 20, 43);
        drawCircle();
    }

    private void drawEnglishWhiteCircle()
    {
        //White color from United Kingdom flag: #FFFFFF
        m_paint.setARGB(255, 255, 255, 255);
        drawCircle();
    }

    private int m_numberOfCircles;
    private int m_maxRadius;
    private int m_minRadius;
    private int m_width;
    private int m_height;
    private Paint m_paint;
    private Bitmap m_bmp;
    private Random m_rand;
    private Canvas m_canvas;
}
