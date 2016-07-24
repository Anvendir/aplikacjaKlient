package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

/**
 * Created by Rafal on 2016-07-24.
 */
public class ServerFileListHandler extends CommonHandler
{
    public ServerFileListHandler()
    {
        super();
    }

    @Override
    public void run()
    {
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        l_msgReader.readMessage();

        sendServerSendFileRequest();
        receiveServerSendFileResponse();
    }

    private void sendServerSendFileRequest()
    {
        Message l_msg = buildServerSendFileListReq();
        MessageSender l_msgSender = new MessageSender();
        l_msgSender.send(l_msg);
    }

    private Message buildServerSendFileListReq()
    {
        Message l_msg = new Message();
        String l_payload = "File lest request";
        l_msg.msgId = EMessageId.SERVER_SEND_FILE_LIST_REQ;
        l_msg.numOfMsgInFileTransfer = 0;
        l_msg.bytesInPayload = l_payload.length();
        l_msg.payload = l_payload.toCharArray();

        return l_msg;
    }

    private void receiveServerSendFileResponse()
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

        String l_string = new String(p_msg.payload);
        l_string = l_string.substring(0, p_msg.bytesInPayload - 1);
        Log.d(LABEL, "payload: " + l_string);
    }
}
