package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;
import pl.kobak.rafal.dicommobile.MainActivity;

/**
 * Created by Rafal on 2016-07-28.
 */
public class ServerParseDicomFileHandler extends CommonHandler
{
    public ServerParseDicomFileHandler()
    {
        super();
    }

    @Override
    public void run()
    {
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        l_msgReader.readMessage();

        sendServerParseDicomFileReq();
        receiveServerSendDicomFileResponse();
    }

    private void sendServerParseDicomFileReq()
    {
        Message l_msg = buildServerSendDicomFileReq();
        MessageSender l_msgSender = new MessageSender();
        l_msgSender.send(l_msg);
    }

    private Message buildServerSendDicomFileReq()
    {
        Message l_msg = new Message();
        String l_payload = "./moduleTest/plikiTestyAndroid/" + MainActivity.s_chosenFileName;
        l_msg.msgId = EMessageId.SERVER_PARSE_DICOM_FILE_REQ;
        l_msg.numOfMsgInFileTransfer = 0;
        l_msg.bytesInPayload = l_payload.length();
        l_msg.payloadWrite = l_payload.toCharArray();

        return l_msg;
    }

    private void receiveServerSendDicomFileResponse()
    {
        MessageReader l_msgReader = new MessageReader();
        Message l_msg = l_msgReader.readMessage();

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
}
