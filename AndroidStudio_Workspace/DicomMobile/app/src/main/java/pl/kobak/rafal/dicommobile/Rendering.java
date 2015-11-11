package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;

public class Rendering extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendering);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
