package com.jd.security.jdguard.f;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes17.dex */
public class e {

    /* loaded from: classes17.dex */
    public static class a {
        public String d;
        public int a = 10000;
        public int b = 10000;

        /* renamed from: c */
        public String f6937c = "UTF-8";

        /* renamed from: e */
        public int f6938e = -1;
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

    public static String b(String str, String str2) throws Throwable {
        return c(str, str2.getBytes("UTF-8"), new a());
    }

    public static String c(String str, byte[] bArr, a aVar) throws Throwable {
        return b.b().a(a(str, aVar.d), bArr, aVar);
    }
}
