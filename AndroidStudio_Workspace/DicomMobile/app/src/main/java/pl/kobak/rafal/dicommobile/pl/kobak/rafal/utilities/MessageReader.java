package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Rafal on 2016-05-13.
 */
public class MessageReader
{
    private Socket m_socket;
    private Message m_message;
    private String LABEL = this.getClass().getSimpleName();

    public MessageReader(Socket p_socket)
    {
        m_socket = p_socket;
        m_message = new Message();
    }

    public Message readMessage()
    {
        try
        {
            InputStreamReader l_in = new InputStreamReader(m_socket.getInputStream());

            readMessageId(l_in);
            readNumOfMsgInFileTransfer(l_in);
            readBytesInPayload(l_in);
            readPayload(l_in);
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred in read message: No I/O!");
        }

        return m_message;
    }

    private void readMessageId(InputStreamReader p_in)
    {
        try
        {
            char[] rawBuffer = new char[4];
            p_in.read(rawBuffer);

            String l_string = new String(rawBuffer);
            l_string = l_string.replace("\0", "");
            int l_int = Integer.parseInt(l_string);

            m_message.msgId = EMessageId.values()[l_int];

            Log.d(LABEL, "Msg id: " + m_message.msgId.name());
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred during reading msgId: No I/O!");
        }
    }

    private void readNumOfMsgInFileTransfer(InputStreamReader p_in)
    {
        try
        {
            char[] rawBuffer = new char[10];
            p_in.read(rawBuffer);

            String l_string = new String(rawBuffer);
            l_string = l_string.replace("\0", "");
            m_message.numOfMsgInFileTransfer = Integer.parseInt(l_string);

            Log.d(LABEL, "numOfMsgInFileTransfer: " + m_message.numOfMsgInFileTransfer);
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred during reading numOfMsgInFileTransfer: No I/O!");
        }
    }

    private void readBytesInPayload(InputStreamReader p_in)
    {
        try
        {
            char[] rawBuffer = new char[5];
            p_in.read(rawBuffer);

            String l_string = new String(rawBuffer);
            l_string = l_string.replace("\0", "");
            m_message.bytesInPayload = Integer.parseInt(l_string);

            Log.d(LABEL, "bytesInPayload: " + m_message.bytesInPayload);
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred during reading bytesInPayload: No I/O!");
        }
    }

    private void readPayload(InputStreamReader p_in)
    {
        try
        {
            char[] rawBuffer = new char[1024];
            p_in.read(rawBuffer);
            m_message.payload = rawBuffer;
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred during reading payload: No I/O!");
        }
    }
}
