package com.jingdong.app.mall.home.o.a;

import com.jd.jdsdk.security.AesCbcCrypto;

/* loaded from: classes4.dex */
public class a {
    public static String a(String str) {
        String encrypt = AesCbcCrypto.encrypt(str, "5yKhoqodQjuHGlKZ", "7WwXmH2TKSCIEJQ3".getBytes());
        return encrypt == null ? "" : encrypt;
    }
}
