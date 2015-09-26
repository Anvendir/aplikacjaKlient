package pl.kobak.rafal.szostaaplikacja;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends Activity {
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

    public void kliknieTo(View v) {
        finish();
    }

    public void data_i_godzina(View v)
    {
        int year, month, day, hour, minute, sekunde;
        Calendar Cal = Calendar.getInstance();

        year = Cal.get(Calendar.YEAR);
        month = Cal.get(Calendar.MONTH);
        day = Cal.get(Calendar.DAY_OF_MONTH);
        hour = Cal.get(Calendar.HOUR);
        minute = Cal.get(Calendar.MINUTE);
        sekunde = Cal.get(Calendar.SECOND);

        TextView data_text = (TextView) findViewById(R.id.textView);
        data_text.setText(year + "-" + month + "-" + day);

        TextView hour_text = (TextView) findViewById(R.id.textView2);
        hour_text.setText(hour + "-" + minute + "-" + sekunde);
    }
}
