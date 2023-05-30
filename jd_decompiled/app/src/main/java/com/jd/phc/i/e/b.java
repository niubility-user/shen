package com.jd.phc.i.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class b {
    private static final boolean a = com.jd.phc.a.a;

    /* loaded from: classes17.dex */
    public static class a {

        /* renamed from: e */
        public HashMap<String, String> f6860e;

        /* renamed from: f */
        public String f6861f;
        public int a = 20000;
        public int b = 20000;

        /* renamed from: c */
        public String f6859c = "UTF-8";
        public boolean d = false;

        /* renamed from: g */
        public int f6862g = -1;
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (!str.contains("?")) {
            sb.append('?');
        } else if (!str.endsWith("?")) {
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        sb.append(str2);
        return sb.toString();
    }

    public static String b(String str, String str2) throws IOException {
        return c(str, str2.getBytes("UTF-8"), new a());
    }

    public static String c(String str, byte[] bArr, a aVar) throws IOException {
        com.jd.phc.i.e.a c2 = com.jd.phc.i.e.a.c();
        String a2 = a(str, aVar.f6861f);
        if (a) {
            com.jd.phc.i.b.a("NetUtil", "request: " + a2 + ", content size: " + bArr.length + ", content: " + bArr);
        }
        return c2.b(a2, bArr, aVar);
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Exception e2) {
            if (com.jd.phc.a.a) {
                e2.printStackTrace();
            }
        }
    }
}
