package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import pl.kobak.rafal.dicommobile.FileWindow;

/**
 * Created by Rafal on 2016-07-26.
 */
public class OnClickListenerCustom implements View.OnClickListener
{
    @Override
    public void onClick(View p_view)
    {
        TextView l_textView = (TextView) p_view;
        l_textView.getText();
        Log.d("Kurwa", "text view" +  l_textView.getText());
        Intent l_openFile = new Intent(p_view.getContext(), FileWindow.class);
        p_view.getContext().startActivity(l_openFile);


        Thread l_connectionThread = new Thread(new ServerFileSentHandler());
        l_connectionThread.start();
    }
}
