package com.nalbam.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AddressUtil {

    public static synchronized String getAddress() {
        try {
            InetAddress ip;
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                for (Enumeration<InetAddress> a = e.nextElement().getInetAddresses(); a.hasMoreElements(); ) {
                    ip = a.nextElement();
                    if (!ip.isLoopbackAddress() && !ip.isLinkLocalAddress() && ip.isSiteLocalAddress()) {
                        return ip.getHostAddress();
                    }
                }
            }
            ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
