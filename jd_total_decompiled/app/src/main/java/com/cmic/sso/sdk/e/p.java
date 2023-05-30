package com.cmic.sso.sdk.e;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* loaded from: classes.dex */
public class p {
    public static String[] a(boolean z) {
        String[] strArr = {"", ""};
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!z || !nextElement.getName().toLowerCase().contains("wlan")) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && !nextElement2.isLinkLocalAddress()) {
                            String hostAddress = nextElement2.getHostAddress();
                            if (!TextUtils.isEmpty(hostAddress)) {
                                if (nextElement2 instanceof Inet6Address) {
                                    sb.append(hostAddress);
                                    sb.append(DYConstants.DY_REGEX_COMMA);
                                } else if (nextElement2 instanceof Inet4Address) {
                                    sb2.append(hostAddress);
                                    sb2.append(DYConstants.DY_REGEX_COMMA);
                                }
                            }
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(sb)) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
            if (!TextUtils.isEmpty(sb2)) {
                sb2 = sb2.delete(sb2.length() - 1, sb2.length());
            }
            strArr[0] = sb2.toString();
            strArr[1] = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("onlyMobileDataIp ");
            sb3.append(z);
            sb3.append(" IPV6 ip\uff1a");
            sb3.append(sb.toString());
            c.b("UmcIPUtils", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("onlyMobileDataIp ");
            sb4.append(z);
            sb4.append(" IPV4 ip\uff1a");
            sb4.append(sb2.toString());
            c.b("UmcIPUtils", sb4.toString());
            return strArr;
        } catch (Exception e2) {
            e2.printStackTrace();
            return strArr;
        }
    }
}
