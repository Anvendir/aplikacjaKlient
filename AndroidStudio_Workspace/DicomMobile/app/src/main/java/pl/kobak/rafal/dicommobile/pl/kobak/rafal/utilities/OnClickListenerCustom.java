package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import pl.kobak.rafal.dicommobile.FileWindow;
import pl.kobak.rafal.dicommobile.MainActivity;

/**
 * Created by Rafal on 2016-07-26.
 */
public class OnClickListenerCustom implements View.OnClickListener
{
    @Override
    public void onClick(View p_view)
    {
        TextView l_textView = (TextView) p_view;
        MainActivity.s_chosenFileName = l_textView.getText().toString();

        Intent l_openFile = new Intent(p_view.getContext(), FileWindow.class);
        p_view.getContext().startActivity(l_openFile);

        Thread l_parseDicomFile_connectionThread = new Thread(new ServerParseDicomFileHandler());
        l_parseDicomFile_connectionThread.start();

        while(l_parseDicomFile_connectionThread.isAlive()) {}

        Thread l_requestParsedFiles_connectionThread
                = new Thread(new ServerFileSentHandler(MainActivity.s_chosenFileName + ".png"));
        l_requestParsedFiles_connectionThread.start();
        while(l_requestParsedFiles_connectionThread.isAlive()) {}
    }
}
