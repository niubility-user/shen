package com.vivo.push.util;

import android.content.Context;
import android.util.Base64;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static volatile a f18296c;
    private byte[] a;
    private byte[] b;

    private a(Context context) {
        w.b().a(ContextDelegate.getContext(context));
        w b = w.b();
        this.a = b.c();
        this.b = b.d();
    }

    public static a a(Context context) {
        if (f18296c == null) {
            synchronized (a.class) {
                if (f18296c == null) {
                    f18296c = new a(context.getApplicationContext());
                }
            }
        }
        return f18296c;
    }

    public final String b(String str) throws Exception {
        return new String(f.a(f.a(a()), f.a(b()), Base64.decode(str, 2)), "utf-8");
    }

    private byte[] b() {
        byte[] bArr = this.b;
        return (bArr == null || bArr.length <= 0) ? w.b().d() : bArr;
    }

    public final String a(String str) throws Exception {
        String a = f.a(a());
        String a2 = f.a(b());
        byte[] bytes = str.getBytes("utf-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(a2.getBytes("utf-8"), JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(1, secretKeySpec, new IvParameterSpec(a.getBytes("utf-8")));
        return Base64.encodeToString(cipher.doFinal(bytes), 2);
    }

    private byte[] a() {
        byte[] bArr = this.a;
        return (bArr == null || bArr.length <= 0) ? w.b().c() : bArr;
    }
}
