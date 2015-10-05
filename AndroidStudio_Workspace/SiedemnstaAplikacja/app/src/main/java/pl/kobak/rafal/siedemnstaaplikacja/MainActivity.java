package pl.kobak.rafal.siedemnstaaplikacja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void kliknieTo_1(View v)
    {
        Intent iss = new Intent(this, ISSActivity.class);
        startActivity(iss);
    }

    public void kliknieTo_2(View v)
    {
        Intent moon = new Intent(this, MoonActivity.class);
        startActivity(moon);
    }

    public void kliknieTo_3(View v)
    {
        Intent sun = new Intent(this, SunActivity.class);
        startActivity(sun);
    }
}
