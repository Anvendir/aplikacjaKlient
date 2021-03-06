package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Rafal on 2016-07-27.
 */
public class ServerFileSentHandler extends CommonHandler
{
    private int m_numOfMsgInFileTransfer;
    private String m_fileName;
    public ServerFileSentHandler(String p_fileName)
    {
        super();
        m_numOfMsgInFileTransfer = 0;
        m_fileName = p_fileName;
    }

    @Override
    public void run()
    {
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        l_msgReader.readMessage();

        sendServerSendFileReq();
        receiveServerSendFileResponse();

        receiveSendFileInd();
    }

    private void sendServerSendFileReq()
    {
        Message l_msg = buildServerSendFileReq();
        MessageSender l_msgSender = new MessageSender();
        l_msgSender.send(l_msg);
    }

    private Message buildServerSendFileReq()
    {
        Message l_msg = new Message();
        String l_payload = "./moduleTest/plikiTestyAndroid/" + m_fileName;
        l_msg.msgId = EMessageId.SERVER_SEND_FILE_REQ;
        l_msg.numOfMsgInFileTransfer = 0;
        l_msg.bytesInPayload = l_payload.length();
        l_msg.payloadWrite = l_payload.toCharArray();

        return l_msg;
    }

    private void receiveServerSendFileResponse()
    {
        MessageReader l_msgReader = new MessageReader();
        Message l_msg = l_msgReader.readMessage();
        m_numOfMsgInFileTransfer = l_msg.numOfMsgInFileTransfer;

        printReceivedMessage(l_msg);
    }

    private void printReceivedMessage(Message p_msg)
    {
        Log.d(LABEL, "Received message: ");
        Log.d(LABEL, "msgId: " + p_msg.msgId.name());
        Log.d(LABEL, "numOfMsgInFileTransfer: " + p_msg.numOfMsgInFileTransfer);
        Log.d(LABEL, "bytesInPayload: " + p_msg.bytesInPayload);
        Log.d(LABEL, "payload: " + p_msg.getUsefulPayload());
    }

    private void receiveSendFileInd()
    {
        BufferedOutputStream l_bufferedOutputStream = getBufferedOutputStream(m_fileName);

        try
        {
            receiveFile(l_bufferedOutputStream);
            l_bufferedOutputStream.flush();
            l_bufferedOutputStream.close();
        }
        catch (IOException e)
        {
            Log.d(LABEL + "_exception", e.getMessage());
            e.printStackTrace();
        }
    }

    private void receiveFile(BufferedOutputStream p_bufferedOutputStream)
            throws IOException
    {
        for (int j = 0; j < m_numOfMsgInFileTransfer; j++)
        {
            MessageReader l_msgReader = new MessageReader();
            Message l_msg = l_msgReader.readMessage();

            for (int i = 0; i < l_msg.bytesInPayload; i++)
            {
                p_bufferedOutputStream.write(l_msg.payloadRead[i]);
            }
        }
    }

    private BufferedOutputStream getBufferedOutputStream(String p_outputFileName)
    {
        File l_externalStorageDir = Environment.getExternalStorageDirectory();
        File l_directory = new File(l_externalStorageDir.getAbsolutePath() + "/mojePliki");
        l_directory.mkdirs();

        File l_file = new File(l_directory, p_outputFileName);
        FileOutputStream l_outputStream = null;

        try
        {
            l_outputStream = new FileOutputStream(l_file);
        }
        catch (FileNotFoundException e)
        {
            Log.d(LABEL + "_exception", e.getMessage());
            e.printStackTrace();
        }
        return new BufferedOutputStream(l_outputStream);
    }
}
