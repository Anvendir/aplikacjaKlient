package pl.kobak.rafal.dicommobile;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities.ServerFileSentHandler;

public class Metadata extends Activity
{
    protected String LABEL = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metadata);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        startRequestParsedFilesConnectionThread();

        TextView l_textView = (TextView) this.findViewById(R.id.textView);
        String l_pathToFile = getPathToFile();
        Log.d(LABEL, l_pathToFile);

        String l_textFromFile = getTextFromFile(l_pathToFile);
        l_textView.setText(l_textFromFile);
    }

    private void startRequestParsedFilesConnectionThread()
    {
        Thread l_requestParsedFiles_connectionThread
                = new Thread(new ServerFileSentHandler(MainActivity.s_chosenFileName + ".txt"));

        l_requestParsedFiles_connectionThread.start();
        while(l_requestParsedFiles_connectionThread.isAlive()) {}
    }

    private String getPathToFile()
    {
        String l_rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String l_path =  l_rootPath + File.separator
                                    + "mojePliki"
                                    + File.separator
                                    + MainActivity.s_chosenFileName
                                    + ".txt";
        return l_path;
    }

    private String getTextFromFile(String p_filePath)
    {
        BufferedReader l_bufferReader = null;
        try
        {
            l_bufferReader = new BufferedReader(new FileReader(p_filePath));
        }
        catch (FileNotFoundException e)
        {
            Log.d(LABEL + "_exception", "Blad przy otwieraniu pliku!");
            Log.d(LABEL + "_exception", e.getMessage());
            e.printStackTrace();
        }

        StringBuilder l_total = new StringBuilder();
        String l_line = new String();
        try
        {
            while((l_line = l_bufferReader.readLine()) != null)
            {
                l_total.append("\n");
                l_total.append(l_line);
            }
        }
        catch (IOException e)
        {
            Log.d(LABEL + "_exception", "Blad przy odczycie");
            Log.d(LABEL + "_exception", e.getMessage());
            e.printStackTrace();
        }
        return l_total.toString();
    }
}
