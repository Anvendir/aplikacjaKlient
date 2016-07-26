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
    private int MSG_ID_INITIAL_INDEX = 0;
    private int NUM_OF_MSG_IN_FILE_TRANSFER_INDEX = 4;
    private int BYTES_IN_PAYLOAD_INDEX = 14;
    private int PAYLOAD_INDEX = 19;
    private char[] m_rawMessage;

    public MessageSender()
    {
        super();
        m_rawMessage = new char[RAW_MESSAGE_SIZE];
        for(int i = 0; i < RAW_MESSAGE_SIZE; i++)
        {
            m_rawMessage[i] = '\0';
        }
    }

    public void send(Message p_message)
    {
        try
        {
            convertMsgId(p_message.msgId);
            convertNumOfMsgInFileTransfer(p_message.numOfMsgInFileTransfer);
            convertBytesInPayload(p_message.bytesInPayload);
            convertPayload(p_message);

            OutputStreamWriter l_outputWriter = new OutputStreamWriter(MainActivity.s_socket.getOutputStream());
            l_outputWriter.write(m_rawMessage);
            l_outputWriter.flush();
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception in message sender!" + e.getMessage());
        }
    }

    public void convertMsgId(EMessageId p_msgId)
    {
        if (p_msgId.getValue() > 999)
        {
            Log.d(LABEL, "msgId is greater than 999! Execution finished with error.");
            return;
        }

        String l_tempStr = Integer.toString(p_msgId.getValue());
        char l_temp[] = l_tempStr.toCharArray();

        for (int i = 0; i < l_temp.length; i++)
        {
            m_rawMessage[MSG_ID_INITIAL_INDEX + i] = l_temp[i];
        }
    }

    public void convertNumOfMsgInFileTransfer(int p_numOfMsgInFileTransfer)
    {
        if (p_numOfMsgInFileTransfer > 999999999)
        {
            Log.d(LABEL, "numOfMsgInFileTransfer is greater than 999999999! Execution finished with error.");
            return;
        }

        String l_tempStr = Integer.toString(p_numOfMsgInFileTransfer);
        char l_temp[] = l_tempStr.toCharArray();
        for (int i = 0; i < l_temp.length; i++)
        {
            m_rawMessage[NUM_OF_MSG_IN_FILE_TRANSFER_INDEX + i] = l_temp[i];
        }
    }

    public void convertBytesInPayload(int p_bytesInPayload)
    {
        if (p_bytesInPayload > 9999)
        {
            Log.d(LABEL, "bytesInPayload is greater than 999999999! Execution finished with error.");
            return;
        }

        String l_tempStr = Integer.toString(p_bytesInPayload);
        char[] l_temp = l_tempStr.toCharArray();
        for (int i = 0; i < l_temp.length; i++)
        {
            m_rawMessage[BYTES_IN_PAYLOAD_INDEX + i] = l_temp[i];
        }
    }

    public void convertPayload(Message p_message)
    {
        for (int i = 0; i < p_message.bytesInPayload; i++)
        {
            m_rawMessage[PAYLOAD_INDEX + i] = p_message.payloadWrite[i];
        }
    }
}
