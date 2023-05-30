package com.jd.security.jdguard.a;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends d {

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ URI f6871c;
        final /* synthetic */ boolean d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ byte[] f6872e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f6873f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6874g;

        a(URI uri, boolean z, byte[] bArr, String str, String str2, String str3) {
            this.f6871c = uri;
            this.d = z;
            this.f6872e = bArr;
            this.f6873f = str2;
            this.f6874g = str3;
        }

        @Override // com.jd.security.jdguard.a.f
        public byte[] a() {
            if (this.f6872e == null) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
                messageDigest.reset();
                messageDigest.update(this.f6872e);
                return com.jd.security.jdguard.f.a.a(messageDigest.digest()).toLowerCase().getBytes();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        @Override // com.jd.security.jdguard.a.d
        protected URI d() {
            return this.f6871c;
        }

        @Override // com.jd.security.jdguard.a.d
        protected boolean e() {
            return this.d;
        }

        @Override // com.jd.security.jdguard.a.f
        public String getContentType() {
            if ("GET".equalsIgnoreCase(this.f6873f)) {
                return null;
            }
            return this.f6874g;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (com.jd.security.jdguard.b.e().i() != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.URI a(java.net.URI r12, boolean r13, byte[] r14, java.lang.String r15, java.lang.String r16, java.util.Map<java.lang.String, java.lang.String> r17, java.lang.String r18, java.util.Map<java.lang.String, java.lang.String> r19) {
        /*
            r8 = r12
            long r9 = java.lang.System.currentTimeMillis()
            com.jd.security.jdguard.c r0 = com.jd.security.jdguard.b.e()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L73
            com.jd.security.jdguard.c r0 = com.jd.security.jdguard.b.e()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L1c
            com.jd.security.jdguard.c r0 = com.jd.security.jdguard.b.e()     // Catch: java.lang.Throwable -> L73
            boolean r0 = r0.i()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L1c
            goto L73
        L1c:
            com.jd.security.jdguard.a.c$a r11 = new com.jd.security.jdguard.a.c$a
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r18
            r7 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7)
            boolean r0 = android.text.TextUtils.isEmpty(r18)
            if (r0 != 0) goto L3d
            java.util.Locale r0 = java.util.Locale.getDefault()
            r1 = r18
            java.lang.String r0 = r1.toUpperCase(r0)
            r11.g(r0)
        L3d:
            com.jd.security.jdguard.a.e r1 = new com.jd.security.jdguard.a.e
            r1.<init>(r11)
            r2 = 0
            java.net.URI r0 = r1.l()     // Catch: java.lang.Throwable -> L4b
            r4 = r19
            r3 = r0
            goto L53
        L4b:
            r0 = move-exception
            r3 = r0
            com.jd.security.jdguard.f.d.e(r3)
            r4 = r19
            r3 = r2
        L53:
            r11.f(r4)
            java.util.Map r2 = r1.i()     // Catch: java.lang.Throwable -> L5b
            goto L60
        L5b:
            r0 = move-exception
            r1 = r0
            com.jd.security.jdguard.f.d.e(r1)
        L60:
            if (r2 == 0) goto L72
            r1 = r17
            r1.putAll(r2)
            java.lang.String r0 = "jdgs"
            java.lang.Object r0 = r2.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            c(r12, r0, r9)
        L72:
            return r3
        L73:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.security.jdguard.a.c.a(java.net.URI, boolean, byte[], java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.util.Map):java.net.URI");
    }

    public static Map<String, String> b(URI uri, byte[] bArr, String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        if ("GET".equalsIgnoreCase(str2)) {
            a(uri, z, bArr, null, null, hashMap, str2, null);
        } else {
            a(uri, z, bArr, null, str, hashMap, str2, null);
        }
        return hashMap;
    }

    private static void c(URI uri, String str, long j2) {
        com.jd.security.jdguard.e.c y;
        try {
            com.jd.security.jdguard.core.e d = com.jd.security.jdguard.b.d();
            if (d == null || (y = d.y()) == null) {
                return;
            }
            if (uri != null) {
                String path = uri.getPath();
                String query = uri.getQuery();
                r1 = TextUtils.isEmpty(path) ? 70 : 70 + path.length();
                if (!TextUtils.isEmpty(query)) {
                    r1 += query.length();
                }
            }
            if (str == null) {
                return;
            }
            if (str.startsWith("-")) {
                y.e(Integer.parseInt(str), r1, System.currentTimeMillis() - j2);
            } else if (str.startsWith("{")) {
                y.e(0, r1, System.currentTimeMillis() - j2);
            } else if (DYConstants.DY_NULL_STR.equals(str)) {
                y.e(-4112, r1, System.currentTimeMillis() - j2);
            }
        } catch (Throwable unused) {
        }
    }
}
