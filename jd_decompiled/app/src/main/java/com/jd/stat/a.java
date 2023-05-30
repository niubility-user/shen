package com.jd.stat;

/* loaded from: classes18.dex */
public class a {
    public static int a(String str) {
        return a(str, "fix:wifiRouterMac", "fix:wf", "fix:wifiMac", "alter:ssid") ? 1 : 0;
    }

    private static boolean a(String str, String... strArr) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
