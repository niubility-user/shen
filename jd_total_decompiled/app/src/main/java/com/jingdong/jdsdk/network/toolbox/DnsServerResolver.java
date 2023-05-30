package com.jingdong.jdsdk.network.toolbox;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.RouteInfo;
import android.os.Build;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class DnsServerResolver {
    private static String arrayToJsonStr(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return "";
        }
        try {
            return new JSONArray((Collection) arrayList).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String getServers(Context context) {
        ArrayList arrayList = new ArrayList();
        String[] serversMethodConnectivityManager = getServersMethodConnectivityManager(context);
        if (serversMethodConnectivityManager != null && serversMethodConnectivityManager.length > 0) {
            arrayList.addAll(Arrays.asList(serversMethodConnectivityManager));
            return arrayToJsonStr(arrayList);
        }
        return arrayToJsonStr(arrayList);
    }

    private static String[] getServersMethodConnectivityManager(Context context) {
        if (context != null && Build.VERSION.SDK_INT >= 21) {
            try {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    for (Network network : connectivityManager.getAllNetworks()) {
                        if (connectivityManager.getNetworkInfo(network).isConnected()) {
                            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                            List<InetAddress> dnsServers = linkProperties.getDnsServers();
                            if (linkPropertiesHasDefaultRoute(linkProperties)) {
                                for (InetAddress inetAddress : dnsServers) {
                                    if (inetAddress instanceof Inet6Address) {
                                        arrayList.add(inetAddress.getHostAddress());
                                    }
                                }
                            } else {
                                for (InetAddress inetAddress2 : dnsServers) {
                                    if (inetAddress2 instanceof Inet6Address) {
                                        arrayList.add(inetAddress2.getHostAddress());
                                    }
                                }
                            }
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList.addAll(arrayList2);
                }
                if (arrayList.size() > 0) {
                    return (String[]) arrayList.toArray(new String[0]);
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    @TargetApi(21)
    private static boolean linkPropertiesHasDefaultRoute(LinkProperties linkProperties) {
        Iterator<RouteInfo> it = linkProperties.getRoutes().iterator();
        while (it.hasNext()) {
            if (it.next().isDefaultRoute()) {
                return true;
            }
        }
        return false;
    }
}
