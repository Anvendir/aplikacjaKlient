package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
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
    private final int MSG_SIZE = MSG_ID_SIZE +
                                 NUM_OF_MSG_IN_FILE_TRANSFER_SIZE +
                                 BYTES_IN_PAYLOAD_SIZE +
                                 PAYLOAD_SIZE;

    public MessageReader()
    {
        m_message = new Message();
    }

    public Message readMessage()
    {
        try
        {
            byte[] l_receivedRawMsg = new byte[MSG_SIZE];
            InputStream l_inputStream = MainActivity.s_socket.getInputStream();
            int l_readBytes = l_inputStream.read(l_receivedRawMsg, 0, l_receivedRawMsg.length);

            Log.d(LABEL, "Amount of read bytes: " +  Integer.toString(l_readBytes));

            fillMessageId(l_receivedRawMsg);
            fillNumOfMsgInFileTransfer(l_receivedRawMsg);
            fillBytesInPayload(l_receivedRawMsg);
            fillPayload(l_receivedRawMsg);
        }
        catch (IOException e)
        {
            Log.d(LABEL, "Exception occurred in read message: No I/O!");
        }

        return m_message;
    }

    private void fillMessageId(byte[] p_rawMessage)
    {
        byte[] l_msgId = new byte[MSG_ID_SIZE];
        for(int i = 0; i < MSG_ID_SIZE; i++)
        {
            l_msgId[i] = p_rawMessage[i];
        }

        String l_tempString = new String(l_msgId);
        l_tempString = l_tempString.replace("\0", "");
        int l_tempInt = Integer.parseInt(l_tempString);
        m_message.msgId = EMessageId.values()[l_tempInt];

        Log.d(LABEL, "Msg id: " + m_message.msgId.name());
    }

    private void fillNumOfMsgInFileTransfer(byte[] p_rawMessage)
    {
        byte[] l_numOfMsgInFileTransfer = new byte[NUM_OF_MSG_IN_FILE_TRANSFER_SIZE];
        for(int i = 0; i < NUM_OF_MSG_IN_FILE_TRANSFER_SIZE; i++)
        {
            l_numOfMsgInFileTransfer[i] = p_rawMessage[i + MSG_ID_SIZE];
        }

        String l_tempString = new String(l_numOfMsgInFileTransfer);
        l_tempString = l_tempString.replace("\0", "");
        m_message.numOfMsgInFileTransfer = Integer.parseInt(l_tempString);

        Log.d(LABEL, "NumOfMsgInFileTransfer: " + m_message.numOfMsgInFileTransfer);
    }

    private void fillBytesInPayload(byte[] p_rawMessage)
    {
        byte[] l_bytesInPayload = new byte[BYTES_IN_PAYLOAD_SIZE];
        for(int i = 0; i < BYTES_IN_PAYLOAD_SIZE; i++)
        {
            l_bytesInPayload[i] = p_rawMessage[i + MSG_ID_SIZE + NUM_OF_MSG_IN_FILE_TRANSFER_SIZE];
        }

        String l_tempString = new String(l_bytesInPayload);
        l_tempString = l_tempString.replace("\0", "");
        m_message.bytesInPayload = Integer.parseInt(l_tempString);

        Log.d(LABEL, "BytesInPayload: " + m_message.bytesInPayload);
    }

    private void fillPayload(byte[] p_rawMessage)
    {
        for (int i = 0; i < PAYLOAD_SIZE; i++)
        {
            m_message.payloadRead[i] = p_rawMessage[i +
                                                    MSG_ID_SIZE +
                                                    NUM_OF_MSG_IN_FILE_TRANSFER_SIZE +
                                                    BYTES_IN_PAYLOAD_SIZE];
        }
    }
}
