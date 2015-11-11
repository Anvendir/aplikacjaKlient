package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;

public class Metadata extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metadata);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
