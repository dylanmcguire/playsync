package netscan.playerConnectionUI;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionsUtil {

    public static InetAddress getLocalAddress() throws Exception {
        List<NetworkInterface> netInts = Collections.list(NetworkInterface.getNetworkInterfaces());

        // there is a simple method, but it works sometimes
        // incorrectly when there are several network interfaces
        if (netInts.size() == 1) {
            return InetAddress.getLocalHost();
        }

        for (NetworkInterface net : netInts) {
            if (!net.isLoopback() && !net.isVirtual() && net.isUp()) {
                Enumeration<InetAddress> addrEnum = net.getInetAddresses();
                while (addrEnum.hasMoreElements()) {
                    InetAddress addr = addrEnum.nextElement();
                    // filter out addresses, which cannot be considered as the main address
                    // and return the first suitable address
                    if ( !addr.isLoopbackAddress() && !addr.isAnyLocalAddress()
                            && !addr.isLinkLocalAddress() && !addr.isMulticastAddress()
                            ) {
                        return addr;
                    }
                }
            }
        }
        // we can fall here if there are no suitable addresses/interfaces
        // or we don't have enough permissions
        return null;
    }
}
