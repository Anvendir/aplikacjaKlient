package pl.twoja_domena.rozdzial_11a;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //ISS
    public void kliknieto_1( View v)
    {
        Intent iss = new Intent( this, ISSActivity.class);
        startActivity( iss);
    }
    //Księżyc
    public void kliknieto_2( View v)
    {
        Intent iss = new Intent( this, MoonActivity.class);
        startActivity( iss);
    }
    //Słońce
    public void kliknieto_3( View v)
    {
        Intent iss = new Intent( this, SunActivity.class);
        startActivity( iss);
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
}
