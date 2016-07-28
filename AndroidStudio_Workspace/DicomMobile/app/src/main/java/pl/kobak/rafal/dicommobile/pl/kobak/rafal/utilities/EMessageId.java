package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

/**
 * Created by Rafal on 2016-05-12.
 */
public enum EMessageId
{
    CLIENT_WELCOME_MSG_IND(0),
    SERVER_TEST_FIRST_REQ(1),
    SERVER_TEST_FIRST_RESP(2),
    SERVER_TEST_SECOND_REQ(3),
    SERVER_TEST_SECOND_RESP(4),
    SERVER_SEND_FILE_REQ(5),
    SERVER_SEND_FILE_RESP(6),
    CLIENT_SEND_FILE_IND(7),
    SERVER_SEND_FILE_LIST_REQ(8),
    SERVER_SEND_FILE_LIST_RESP(9),
    SERVER_PARSE_DICOM_FILE_REQ(10),
    SERVER_PARSE_DICOM_FILE_RESP(11);

    private final int m_value;
    private EMessageId(int p_value)
    {
        this.m_value = p_value;
    }

    public int getValue()
    {
        return m_value;
    }
}
