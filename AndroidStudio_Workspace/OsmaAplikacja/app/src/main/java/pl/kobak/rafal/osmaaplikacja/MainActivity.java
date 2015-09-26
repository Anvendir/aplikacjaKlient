package pl.kobak.rafal.osmaaplikacja;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void obroc(View v)
    {
        obrocPrzycisk(v);
        Animation animacja = AnimationUtils.loadAnimation(this, R.anim.obrot);
        ImageView obrazek = (ImageView) findViewById(R.id.imageView);
        obrazek.startAnimation(animacja);
    }

    public void skaluj(View v)
    {
        obrocPrzycisk(v);
        Animation animacja = AnimationUtils.loadAnimation(this, R.anim.skalowanie);
        ImageView obrazek = (ImageView) findViewById(R.id.imageView);
        obrazek.startAnimation(animacja);
    }

    public void przesun(View v)
    {
        obrocPrzycisk(v);
        Animation animacja = AnimationUtils.loadAnimation(this, R.anim.przesuw);
        ImageView obrazek = (ImageView) findViewById(R.id.imageView);
        obrazek.startAnimation(animacja);
    }

    public void zanik(View v)
    {
        obrocPrzycisk(v);
        Animation animacja = AnimationUtils.loadAnimation(this, R.anim.zanik);
        ImageView obrazek = (ImageView) findViewById(R.id.imageView);
        obrazek.startAnimation(animacja);
    }

    public void stosujWszystko(View v)
    {
        Animation animacja = AnimationUtils.loadAnimation(this, R.anim.stosuj_wszystko);
        ImageView obrazek = (ImageView) findViewById(R.id.imageView);
        obrazek.startAnimation(animacja);
    }

    private void obrocPrzycisk(View p)
    {
        Animation obrot = AnimationUtils.loadAnimation(this, R.anim.obrot);
        p.startAnimation(obrot);
    }
}
