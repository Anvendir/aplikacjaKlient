package pl.kobak.rafal.dicommobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.net.Socket;

public class MainActivity extends Activity
{
    public MainActivity()
    {
        super();
        s_socket = new Socket();
        s_ipAddress = new String();
        s_portNumber = new String();
        s_fileList = new String();
        s_chosenFileName = new String();
        s_welcomeMessage = new String();
    }

    @Override
    protected void onCreate(Bundle p_savedInstanceState)
    {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static Socket s_socket;
    public static String s_ipAddress;
    public static String s_portNumber;
    public static String s_fileList;
    public static String s_chosenFileName;
    public static String s_welcomeMessage;

    private boolean m_isMovement = false;
    public void onClickMainAnimation(View p_view)
    {
        ImageView l_img = (ImageView) findViewById(R.id.animationViewMain);
        AnimationDrawable l_animation = (AnimationDrawable) l_img.getBackground();

        if (m_isMovement)
        {
            l_animation.stop();
            m_isMovement = false;
        }
        else
        {
            l_animation.start();
            m_isMovement = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu p_menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, p_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_item)
    {
        int l_id = p_item.getItemId();

        if(l_id == R.id.action_opsenFile)
        {
            Intent l_openFile = new Intent(this, OpenFile.class);
            startActivity(l_openFile);
            return true;
        }
        if(l_id == R.id.action_connectToServer)
        {
            Intent l_connectToServer = new Intent(this, ConnectToServer.class);
            startActivity(l_connectToServer);
            return true;
        }
        if(l_id == R.id.action_aboutDicom)
        {
            Intent l_aboutDicomView = new Intent(this, AboutDicom.class);
            startActivity(l_aboutDicomView);
            return true;
        }
        if(l_id == R.id.action_language)
        {
            Intent l_language = new Intent(this, Language.class);
            startActivity(l_language);
            return true;
        }
        if(l_id == R.id.action_aboutApp)
        {
            Intent l_aboutApp = new Intent(this, AboutApplication.class);
            startActivity(l_aboutApp);
            return true;
        }

        if(l_id == R.id.action_quit)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(p_item);
    }

    public void onClick_quit(View p_view)
    {
        finish();
    }
}
