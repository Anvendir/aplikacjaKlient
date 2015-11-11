package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;

public class AboutFile extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_file);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
