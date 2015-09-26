package pl.twoja_domena.rozdzial_7a;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    public void obracaj( View v)
    {
        obracaj_przycisk( v);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.obrot);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.startAnimation(a);
    }
    public void skaluj( View v)
    {
        obracaj_przycisk( v);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.skalowanie);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.startAnimation(a);
    }
    public void zanikaj( View v)
    {
        obracaj_przycisk( v);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.zanik);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.startAnimation(a);
    }
    public void przesuwaj( View v)
    {
        obracaj_przycisk( v);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.przesuw);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.startAnimation(a);
    }
    public void stosuj_wszystko( View v)
    {
        obracaj_przycisk( v);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.wszystko_razem);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.startAnimation(a);
    }
    private void obracaj_przycisk( View v)
    {
        Animation obr = AnimationUtils.loadAnimation(this, R. anim.obrot);
        v.startAnimation(obr);
    }
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
}
