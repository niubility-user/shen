package com.jingdong.sdk.dialingtest.e.b;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* loaded from: classes7.dex */
public class a {
    public String a(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        return null;
    }

    public String b(InetAddress inetAddress) {
        if (inetAddress instanceof Inet6Address) {
            return inetAddress.getHostAddress();
        }
        return null;
    }

    public boolean c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet6Address) && !nextElement.getHostAddress().toLowerCase().startsWith("fe80")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            com.jingdong.sdk.dialingtest.c.e.a.c("SafeApi", e2.getMessage());
            return false;
        }
    }
}
