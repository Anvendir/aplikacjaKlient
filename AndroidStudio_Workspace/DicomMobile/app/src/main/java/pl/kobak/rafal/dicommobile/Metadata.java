package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;

import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.ServerFileSentHandler;

public class Metadata extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metadata);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Thread l_requestParsedFiles_connectionThread
                = new Thread(new ServerFileSentHandler(MainActivity.s_chosenFileName + ".txt"));
        l_requestParsedFiles_connectionThread.start();
    }

}
