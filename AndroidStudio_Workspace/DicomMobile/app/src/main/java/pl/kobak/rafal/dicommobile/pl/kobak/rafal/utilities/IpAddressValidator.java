package pl.kobak.rafal.dicommobile.pl.kobak.rafal.utilities;

import android.text.TextUtils;

/**
 * Created by Rafal on 2016-05-12.
 */
public class IpAddressValidator
{
    public boolean validateServerAddress(String p_ipAddress, String p_portNumber)
    {
        return isPortNumberValid(p_portNumber) &&
               isIpAddressValid(p_ipAddress);
    }

    private boolean isNumeric(String p_string)
    {
        return TextUtils.isDigitsOnly(p_string);
    }

    private boolean isPortNumberValid(String p_portNumber)
    {
        if(p_portNumber == null || p_portNumber.isEmpty())
        {
            return false;
        }
        return isNumeric(p_portNumber);
    }

    private boolean isIpAddressValid(String p_ip)
    {
        if (p_ip == null || p_ip.isEmpty())
        {
            return false;
        }

        if (p_ip.endsWith("."))
        {
            return false;
        }

        String[] l_parts = p_ip.split( "\\." );
        if (l_parts.length != 4)
        {
            return false;
        }

        for (String l_singlePart : l_parts)
        {
            if(!isNumeric(l_singlePart))
            {
                return false;
            }

            int i = Integer.parseInt(l_singlePart);
            if ((i < 0) || (i > 255))
            {
                return false;
            }
        }

        return true;
    }
}
