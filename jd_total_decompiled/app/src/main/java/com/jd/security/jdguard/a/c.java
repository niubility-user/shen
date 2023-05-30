package com.jd.security.jdguard.a;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
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
    */
    private static URI a(URI uri, boolean z, byte[] bArr, String str, String str2, Map<String, String> map, String str3, Map<String, String> map2) {
        Map<String, String> map3;
        URI uri2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (com.jd.security.jdguard.b.e() != null) {
                if (com.jd.security.jdguard.b.e() != null) {
                }
                a aVar = new a(uri, z, bArr, str, str3, str2);
                if (!TextUtils.isEmpty(str3)) {
                    aVar.g(str3.toUpperCase(Locale.getDefault()));
                }
                e eVar = new e(aVar);
                Map<String, String> map4 = null;
                try {
                    map3 = map2;
                    uri2 = eVar.l();
                } catch (Throwable th) {
                    com.jd.security.jdguard.f.d.e(th);
                    map3 = map2;
                    uri2 = null;
                }
                aVar.f(map3);
                try {
                    map4 = eVar.i();
                } catch (Throwable th2) {
                    com.jd.security.jdguard.f.d.e(th2);
                }
                if (map4 != null) {
                    map.putAll(map4);
                    c(uri, map4.get("jdgs"), currentTimeMillis);
                }
                return uri2;
            }
        } catch (Throwable unused) {
        }
        return uri;
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
