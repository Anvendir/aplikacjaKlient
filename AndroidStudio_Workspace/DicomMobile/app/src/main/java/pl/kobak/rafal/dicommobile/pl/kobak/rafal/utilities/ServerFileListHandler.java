package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

import pl.kobak.rafal.dicommobile.MainActivity;

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
        Log.d(LABEL, "Adress:" + MainActivity.s_ipAddress);
        Log.d(LABEL, "PORT:" + MainActivity.s_portNumber);
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        l_msgReader.readMessage();

        sendServerSendFileListRequest();
        receiveServerSendFileListResponse();
        Log.d(LABEL, "wszystko");
    }

    private void sendServerSendFileListRequest()
    {
        Message l_msg = buildServerSendFileListReq();
        MessageSender l_msgSender = new MessageSender();
        l_msgSender.send(l_msg);
    }

    private Message buildServerSendFileListReq()
    {
        Message l_msg = new Message();
        String l_payload = "File list request";
        l_msg.msgId = EMessageId.SERVER_SEND_FILE_LIST_REQ;
        l_msg.numOfMsgInFileTransfer = 0;
        l_msg.bytesInPayload = l_payload.length();
        l_msg.payloadWrite = l_payload.toCharArray();

        return l_msg;
    }

    private void receiveServerSendFileListResponse()
    {
        MessageReader l_msgReader = new MessageReader();
        Message l_msg = l_msgReader.readMessage();
        MainActivity.s_fileList = l_msg.getUsefulPayload();

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
