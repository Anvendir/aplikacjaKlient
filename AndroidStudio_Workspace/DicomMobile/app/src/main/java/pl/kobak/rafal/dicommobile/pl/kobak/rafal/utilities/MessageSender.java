package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStreamWriter;
import pl.kobak.rafal.dicommobile.MainActivity;

/**
 * Created by Rafal on 2016-07-21.
 */
public class MessageSender
{
    private String LABEL = this.getClass().getSimpleName();
    private final int MSG_ID_SIZE = 4;
    private final int NUM_OF_MSG_IN_FILE_TRANSFER_SIZE = 10;
    private final int BYTES_IN_PAYLOAD_SIZE = 5;
    private final int PAYLOAD_SIZE = 1024;
    private final int RAW_MESSAGE_SIZE = MSG_ID_SIZE +
                                         NUM_OF_MSG_IN_FILE_TRANSFER_SIZE +
                                         BYTES_IN_PAYLOAD_SIZE +
                                         PAYLOAD_SIZE;
    private char[] m_rawMessage;

    public MessageSender()
    {
        super();
        m_rawMessage = new char[RAW_MESSAGE_SIZE];
    }

    public void send(Message p_message)
    {
        try
        {
            m_rawMessage[0] = '1';
            m_rawMessage[1] = '\0';
            m_rawMessage[4] = '0';
            m_rawMessage[5] = '\0';
            m_rawMessage[14] = '3';
            m_rawMessage[15] = '\0';
            m_rawMessage[19] = 'a';
            m_rawMessage[20] = 'b';
            m_rawMessage[21] = 'c';
            m_rawMessage[22] = '\0';

            OutputStreamWriter l_outputWriter = new OutputStreamWriter(MainActivity.s_socket.getOutputStream());
            l_outputWriter.write(m_rawMessage);
            l_outputWriter.flush();

        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception in message sender!");
        }
    }
}
