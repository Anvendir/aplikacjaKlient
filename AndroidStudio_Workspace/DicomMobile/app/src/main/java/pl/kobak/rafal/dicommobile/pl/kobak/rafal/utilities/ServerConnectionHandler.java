package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.util.Log;

/**
 * Created by Rafal on 2016-05-13.
 */
public class ServerConnectionHandler extends CommonHandler
{
    public ServerConnectionHandler()
    {
        super();
    }

    @Override
    public void run()
    {
        super.connectToServer();
        MessageReader l_msgReader = new MessageReader();
        Message l_msg = l_msgReader.readMessage();

        printWelcomeMessage(l_msg);
    }

    private void printWelcomeMessage(Message p_msg)
    {
        Log.d(LABEL, "Received message: ");
        Log.d(LABEL, "msgId: " + p_msg.msgId.name());
        Log.d(LABEL, "numOfMsgInFileTransfer: " + p_msg.numOfMsgInFileTransfer);
        Log.d(LABEL, "bytesInPayload: " + p_msg.bytesInPayload);
        Log.d(LABEL, "payload: " + p_msg.getUsefulPayload());
    }
}
