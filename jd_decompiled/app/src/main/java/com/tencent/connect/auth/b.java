package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class b {
    public static b a = null;
    static final /* synthetic */ boolean d = true;

    /* renamed from: e */
    private static int f16124e;
    public HashMap<String, a> b = new HashMap<>();

    /* renamed from: c */
    public final String f16125c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /* loaded from: classes9.dex */
    public static class a {
        public IUiListener a;
        public com.tencent.connect.auth.a b;

        /* renamed from: c */
        public String f16126c;
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public static int b() {
        int i2 = f16124e + 1;
        f16124e = i2;
        return i2;
    }

    public String c() {
        int ceil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < ceil; i2++) {
            double random = Math.random();
            double d2 = length;
            Double.isNaN(d2);
            stringBuffer.append(charArray[(int) (random * d2)]);
        }
        return stringBuffer.toString();
    }

    public String a(a aVar) {
        int b = b();
        try {
            this.b.put("" + b, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "" + b;
    }
}
