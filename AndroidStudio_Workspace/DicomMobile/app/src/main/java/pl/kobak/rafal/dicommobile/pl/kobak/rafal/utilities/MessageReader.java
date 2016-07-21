package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.io.InputStreamReader;
import pl.kobak.rafal.dicommobile.MainActivity;

/**
 * Created by Rafal on 2016-05-13.
 */
public class MessageReader
{
    private Message m_message;
    private String LABEL = this.getClass().getSimpleName();
    private final int MSG_ID_SIZE = 4;
    private final int NUM_OF_MSG_IN_FILE_TRANSFER_SIZE = 10;
    private final int BYTES_IN_PAYLOAD_SIZE = 5;
    private final int PAYLOAD_SIZE = 1024;

    public MessageReader()
    {
        m_message = new Message();
    }

    public Message readMessage()
    {
        try
        {
            InputStreamReader l_in = new InputStreamReader(MainActivity.s_socket.getInputStream());

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
            char[] l_rawBuffer = new char[MSG_ID_SIZE];
            p_in.read(l_rawBuffer);

            String l_string = new String(l_rawBuffer);
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
            char[] rawBuffer = new char[NUM_OF_MSG_IN_FILE_TRANSFER_SIZE];
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
            char[] rawBuffer = new char[BYTES_IN_PAYLOAD_SIZE];
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
            char[] rawBuffer = new char[PAYLOAD_SIZE];
            p_in.read(rawBuffer);
            m_message.payload = rawBuffer;
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred during reading payload: No I/O!");
        }
    }
}
