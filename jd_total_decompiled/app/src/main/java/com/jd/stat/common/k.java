package com.jd.stat.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.ProxyInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Method;
import java.util.HashSet;

/* loaded from: classes18.dex */
public class k {
    public static String a(Context context) {
        NetworkInfo activeNetworkInfo;
        String str = null;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    str = "WIFI";
                } else if (type == 0) {
                    str = activeNetworkInfo.getSubtype() + "";
                } else {
                    str = "unknow";
                }
            }
            return str == null ? "unknow" : str;
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String b() {
        ProxyInfo defaultProxy;
        Uri pacFileUrl;
        return (Build.VERSION.SDK_INT < 23 || (defaultProxy = ((ConnectivityManager) com.jd.stat.security.c.a.getSystemService("connectivity")).getDefaultProxy()) == null || (pacFileUrl = defaultProxy.getPacFileUrl()) == null) ? "" : pacFileUrl.toString();
    }

    @SuppressLint({"MissingPermission"})
    public static String c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) com.jd.stat.security.c.a.getSystemService("connectivity");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return com.jd.stat.common.b.g.h(networkCapabilities != null ? com.jd.stat.common.b.g.a(networkCapabilities.hasTransport(0), com.jd.stat.common.b.g.a(networkCapabilities.hasTransport(1), com.jd.stat.common.b.g.a(networkCapabilities.hasTransport(3), com.jd.stat.common.b.g.a(networkCapabilities.hasTransport(2), com.jd.stat.common.b.g.a(networkCapabilities.hasTransport(4), "", "VPN,"), "\u84dd\u7259,"), "\u4ee5\u592a\u7f51,"), "WIFI,"), "\u8702\u7a9d,") : "");
        } else if (i2 >= 21) {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            String str = "Network count: " + allNetworks.length;
            HashSet hashSet = new HashSet();
            for (Network network : allNetworks) {
                NetworkCapabilities networkCapabilities2 = connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities2.hasTransport(4)) {
                    hashSet.add("VPN");
                }
                if (networkCapabilities2.hasTransport(2)) {
                    hashSet.add("\u84dd\u7259");
                }
                if (networkCapabilities2.hasTransport(3)) {
                    hashSet.add("\u4ee5\u592a\u7f51");
                }
                if (networkCapabilities2.hasTransport(1)) {
                    hashSet.add("WIFI");
                }
                if (networkCapabilities2.hasTransport(0)) {
                    hashSet.add("\u8702\u7a9d");
                }
            }
            return com.jd.stat.common.b.g.a(DYConstants.DY_REGEX_COMMA, hashSet);
        } else {
            return "";
        }
    }

    private static boolean d(Context context) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke((ConnectivityManager) context.getSystemService("connectivity"), new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static String a() {
        ProxyInfo defaultProxy;
        if (Build.VERSION.SDK_INT < 23 || (defaultProxy = ((ConnectivityManager) com.jd.stat.security.c.a.getSystemService("connectivity")).getDefaultProxy()) == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String str = "proxy :" + defaultProxy.getHost() + LangUtils.SINGLE_SPACE + defaultProxy.getPort() + LangUtils.SINGLE_SPACE;
        for (String str2 : defaultProxy.getExclusionList()) {
            sb.append(str2);
            sb.append(DYConstants.DY_REGEX_COMMA);
        }
        return com.jd.stat.common.b.g.h(sb.toString());
    }

    public static String b(Context context) {
        try {
            String str = "1";
            String str2 = c(context) ? "1" : "0";
            if (!d(context)) {
                str = "0";
            }
            return str2 + str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    private static boolean c(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        return wifiManager != null && wifiManager.isWifiEnabled();
    }
}
