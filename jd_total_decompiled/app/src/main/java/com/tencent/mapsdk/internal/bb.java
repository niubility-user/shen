package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.ya;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class bb {

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f16321c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final ua<String, String> a = new ua<>(1000);
    private final ya.k<ya.m<MessageDigest>> b = ya.b(10, new a());

    /* loaded from: classes9.dex */
    public class a implements ya.i<ya.m<MessageDigest>> {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.ya.i
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public ya.m<MessageDigest> a() {
            try {
                return new ya.m<>(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256));
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            int i3 = i2 * 2;
            char[] cArr2 = f16321c;
            cArr[i3 + 1] = cArr2[b & 15];
            cArr[i3 + 0] = cArr2[((byte) (b >>> 4)) & 15];
        }
        return new String(cArr);
    }

    public String a(String str) {
        String b;
        synchronized (this.a) {
            b = this.a.b((ua<String, String>) str);
        }
        if (b == null) {
            ya.m<MessageDigest> a2 = this.b.a();
            try {
                a2.b().update(str.getBytes());
                b = a(a2.b().digest());
            } finally {
                this.b.a(a2);
            }
        }
        synchronized (this.a) {
            this.a.a(str, b);
        }
        return b;
    }
}
