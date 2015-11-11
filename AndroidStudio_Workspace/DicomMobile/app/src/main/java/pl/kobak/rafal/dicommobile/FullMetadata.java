package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;

public class FullMetadata extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_metadata);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
