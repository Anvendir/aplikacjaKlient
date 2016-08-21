package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.OnClickListenerCustom;
import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.ServerFileListHandler;

public class OpenFileRemotely extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_remotely);

        Thread l_connectionThread = new Thread(new ServerFileListHandler());
        l_connectionThread.start();

        while(l_connectionThread.isAlive()) {}

        String [] l_receivedFileList = MainActivity.s_fileList.split("\\r?\\n");
        LinearLayout l_linearLayout = new LinearLayout(this);
        setContentView(l_linearLayout);
        l_linearLayout.setOrientation(LinearLayout.VERTICAL);

        for( int i = 0; i < l_receivedFileList.length; i++ )
        {
            if(l_receivedFileList[i].endsWith(".txt") || l_receivedFileList[i].endsWith(".png"))
                continue;

            TextView l_textView = new TextView(this);
            l_textView.setText(l_receivedFileList[i]);
            l_textView.setClickable(true);
            l_linearLayout.addView(l_textView);
            l_textView.setOnClickListener(new OnClickListenerCustom());
            l_textView.setTextSize(getResources().getDimension(R.dimen.text_on_list));
            l_textView.setGravity(Gravity.CENTER);
            l_textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        getWindow().getDecorView().setBackgroundResource(R.drawable.ogolny2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_open_file_remotely, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    public void onClick_openFile(View p_view)
    {
        //Intent l_openFile = new Intent(this, FileWindow.class);
        //startActivity(l_openFile);
    }

    public void onClick_cancel(View p_view)
    {
        finish();
    }
}
