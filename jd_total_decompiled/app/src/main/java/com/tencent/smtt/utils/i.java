package com.tencent.smtt.utils;

import com.huawei.hms.framework.common.ExceptionCode;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;

/* loaded from: classes9.dex */
public class i {
    private static final char[] a = "0123456789abcdef".toCharArray();
    private static i b;

    /* renamed from: c  reason: collision with root package name */
    private String f17964c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f17965e;

    private i() {
        int nextInt = new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION;
        int nextInt2 = new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION;
        this.f17965e = String.valueOf(nextInt);
        this.f17964c = this.f17965e + String.valueOf(nextInt2);
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    private String b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = a;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
        }
        return new String(cArr);
    }

    public byte[] a(byte[] bArr) throws Exception {
        return com.tencent.smtt.sdk.stat.a.a(this.f17965e.getBytes(), bArr, 1);
    }

    public void b() throws Exception {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    public String c() throws Exception {
        if (this.d == null) {
            byte[] bytes = this.f17964c.getBytes();
            Cipher cipher = null;
            try {
                try {
                    cipher = Cipher.getInstance("RSA/ECB/NoPadding");
                } catch (Exception unused) {
                    b();
                    cipher = Cipher.getInstance("RSA/ECB/NoPadding");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            cipher.init(1, KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(android.util.Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
            this.d = b(cipher.doFinal(bytes));
        }
        return this.d;
    }
}
