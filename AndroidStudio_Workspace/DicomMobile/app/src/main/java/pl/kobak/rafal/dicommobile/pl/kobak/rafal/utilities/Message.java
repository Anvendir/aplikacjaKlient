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
    public byte[] payload;

    public Message()
    {
        msgId = EMessageId.SERVER_TEST_FIRST_REQ;
        numOfMsgInFileTransfer = 0;
        bytesInPayload = 0;
        payload = new byte[PAYLOAD_SIZE];
    }
}
