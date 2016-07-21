package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

/**
 * Created by Rafal on 2016-05-13.
 */
public class ServerConnectionHandler extends CommonHandler
{
    public ServerConnectionHandler(String p_ipAddress, String p_portNumber)
    {
        super(p_ipAddress, p_portNumber);
    }

    @Override
    public void run()
    {
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        Message l_msg = l_msgReader.readMessage();

        printWelcomeMessage(l_msg);

        Message l_tmp = new Message();
        MessageSender l_msgSender = new MessageSender();
        l_msgSender.send(l_tmp);
    }

    private void printWelcomeMessage(Message p_msg)
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
