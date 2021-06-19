package util;

import java.net.InetAddress;

/**
 * description: net <br>
 * date: 2021/6/18 22:38 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class net {
    public static boolean isReservedAddr(InetAddress inetAddr) {
        if(inetAddr.isAnyLocalAddress()||inetAddr.isLinkLocalAddress()||inetAddr.isLoopbackAddress())
        {
            return true;
        }
        return false;
    }
}
