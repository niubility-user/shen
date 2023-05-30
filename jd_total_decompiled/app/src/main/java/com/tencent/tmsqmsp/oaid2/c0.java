package com.tencent.tmsqmsp.oaid2;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class c0 {
    public static final Uri a = Uri.parse("content://cn.nubia.identity/identity");

    public static String a(Context context, String str) {
        Bundle call;
        String str2;
        String str3 = null;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(a);
                call = acquireUnstableContentProviderClient.call("getAAID", str, null);
                if (i2 >= 24) {
                    acquireUnstableContentProviderClient.close();
                } else {
                    acquireUnstableContentProviderClient.release();
                }
            } else {
                call = context.getContentResolver().call(a, "getAAID", str, (Bundle) null);
            }
            if (call.getInt("code", -1) == 0) {
                str3 = call.getString("id");
                str2 = "NubiaLog succeed:" + str3;
            } else {
                str3 = call.getString("message");
                str2 = "NubiaLog failed:" + str3;
            }
            c.c(str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str3;
    }

    public static boolean a(Context context) {
        Bundle call;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(a);
                call = acquireUnstableContentProviderClient.call("isSupport", null, null);
                if (i2 >= 24) {
                    acquireUnstableContentProviderClient.close();
                } else {
                    acquireUnstableContentProviderClient.release();
                }
            } else {
                call = context.getContentResolver().call(a, "isSupport", (String) null, (Bundle) null);
            }
            if (call.getInt("code", -1) == 0) {
                c.c("NubiaLog succeed");
                return call.getBoolean("issupport", true);
            }
            c.c("NubiaLog failed:" + call.getString("message"));
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String b(Context context) {
        Bundle call;
        String str;
        String str2 = null;
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                ContentProviderClient acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(a);
                call = acquireUnstableContentProviderClient.call("getOAID", null, null);
                if (i2 >= 24) {
                    acquireUnstableContentProviderClient.close();
                } else {
                    acquireUnstableContentProviderClient.release();
                }
            } else {
                call = context.getContentResolver().call(a, "getOAID", (String) null, (Bundle) null);
            }
            if (call.getInt("code", -1) == 0) {
                str2 = call.getString("id");
                str = "NubiaLog succeed:" + str2;
            } else {
                str2 = call.getString("message");
                str = "NubiaLog failed:" + str2;
            }
            c.c(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str2;
    }
}
