package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

/**
 * Created by Rafal on 2016-05-12.
 */
public class Message
{
    private static final int PAYLOAD_SIZE = 1024;
    public EMessageId msgId;
    public int numOfMsgInFileTransfer;
    public int bytesInPayload;
    public char[] payloadWrite;
    public byte[] payloadRead;

    public Message()
    {
        msgId = EMessageId.SERVER_TEST_FIRST_REQ;
        numOfMsgInFileTransfer = 0;
        bytesInPayload = 0;
        payloadWrite = new char[PAYLOAD_SIZE];
        payloadRead = new byte[PAYLOAD_SIZE];
    }

    public String getUsefulPayload()
    {
        byte[] usefulPayload = new byte[bytesInPayload];
        for (int i = 0; i < bytesInPayload; i++)
        {
            usefulPayload[i] = payloadRead[i];
        }

        String l_tempString = new String(usefulPayload);
        return l_tempString.replace("\0", "");
    }
}
