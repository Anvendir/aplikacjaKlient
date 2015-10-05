package pl.kobak.rafal.osiemnastaaplikacja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private String adres_iss = "http://www2.heavens-above.com/orbitdisplay.aspx?icon=iss&width=300&height=300&satid=25544";
    private String tyt_ISS = "ISS (heavens-above)";

    private String adres_ksi = "http://api.usno.navy.mil/imagery/moon.png";
    private String tyt_ksi = "Księżyc (USNavy)";

    private String adres_slo = "http://sdo.gsfc.nasa.gov/assets/img/latest/latest_512_4500.jpg";
    private String tyt_slo = "Słońce (NASA)";

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
        int id = item.getItemId();
        if(id==R.id.menu_ISS)
        {
            Intent zamiar = new Intent( this, Activity_ISS_Moon_Sun.class);
            zamiar.putExtra( "adres", adres_iss);
            zamiar.putExtra( "tyt", tyt_ISS);
            startActivity( zamiar);
            return true;
        }
        if(id==R.id.menu_Moon)
        {
            Intent zamiar = new Intent( this, Activity_ISS_Moon_Sun.class);
            zamiar.putExtra( "adres", adres_ksi);
            zamiar.putExtra( "tyt", tyt_ksi);
            startActivity( zamiar);
            return true;
        }
        if(id==R.id.menu_Sun)
        {
            Intent zamiar = new Intent( this, Activity_ISS_Moon_Sun.class);
            zamiar.putExtra( "adres", adres_slo);
            zamiar.putExtra( "tyt", tyt_slo);
            startActivity( zamiar);
            return true;
        }
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
