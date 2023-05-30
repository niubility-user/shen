package com.jingdong.manto.p.g;

import android.text.TextUtils;
import com.jingdong.manto.p.g.c;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/* loaded from: classes16.dex */
public class b {
    private byte[] a;
    private String b;

    /* renamed from: c */
    c.b f13935c;
    private Map<String, String> d;

    /* renamed from: e */
    ArrayList<String> f13936e;

    /* renamed from: f */
    private String f13937f;

    /* renamed from: g */
    Call f13938g;

    /* renamed from: h */
    String f13939h;

    /* renamed from: i */
    String f13940i;

    /* renamed from: j */
    public Request f13941j;

    /* renamed from: com.jingdong.manto.p.g.b$b */
    /* loaded from: classes16.dex */
    public static class C0651b {
        private String a;
        private String b;

        /* renamed from: c */
        private Map<String, String> f13942c;
        private ArrayList<String> d;

        /* renamed from: e */
        private String f13943e;

        /* renamed from: f */
        private String f13944f;

        /* renamed from: g */
        private String f13945g;

        /* renamed from: h */
        private String f13946h;

        /* renamed from: i */
        private byte[] f13947i;

        /* renamed from: j */
        private int f13948j;

        /* renamed from: k */
        private c.b f13949k;

        public C0651b a(int i2) {
            this.f13948j = i2;
            return this;
        }

        public C0651b a(c.b bVar) {
            this.f13949k = bVar;
            return this;
        }

        public C0651b a(String str) {
            if (TextUtils.isEmpty(str)) {
                str = "GET";
            }
            this.b = str;
            return this;
        }

        public C0651b a(ArrayList<String> arrayList) {
            this.d = arrayList;
            return this;
        }

        public C0651b a(Map<String, String> map) {
            this.f13942c = map;
            return this;
        }

        public C0651b a(byte[] bArr) {
            if (bArr == null) {
                bArr = new byte[0];
            }
            this.f13947i = bArr;
            return this;
        }

        public b a() {
            return new b(this);
        }

        public C0651b b(String str) {
            this.f13943e = str;
            return this;
        }

        public C0651b c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f13944f = str;
            }
            return this;
        }

        public C0651b d(String str) {
            this.f13945g = str;
            return this;
        }

        public C0651b e(String str) {
            this.a = str;
            return this;
        }

        public C0651b f(String str) {
            this.f13946h = str;
            return this;
        }
    }

    private b(C0651b c0651b) {
        String str;
        RequestBody requestBody;
        this.f13940i = c0651b.f13946h;
        this.f13939h = c0651b.a;
        String unused = c0651b.f13945g;
        this.f13937f = c0651b.f13944f;
        String unused2 = c0651b.f13943e;
        this.f13936e = c0651b.d;
        this.a = c0651b.f13947i;
        this.d = c0651b.f13942c;
        this.f13935c = c0651b.f13949k;
        int unused3 = c0651b.f13948j;
        this.b = c0651b.b;
        System.currentTimeMillis();
        Request.Builder url = new Request.Builder().url(this.f13940i);
        if (HttpMethod.permitsRequestBody(this.b)) {
            str = this.b;
            requestBody = RequestBody.create(MediaType.parse(this.f13937f), this.a);
        } else {
            str = this.b;
            requestBody = null;
        }
        url.method(str, requestBody);
        com.jingdong.manto.p.c.a(url, this.d);
        url.addHeader("charset", "utf-8");
        String a2 = a();
        url.removeHeader("User-Agent").addHeader("User-Agent", TextUtils.isEmpty(a2) ? "" : a2);
        this.f13941j = url.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 < r1) goto Lf
            android.content.Context r0 = com.jingdong.manto.c.a()     // Catch: java.lang.Exception -> Lf
            java.lang.String r0 = android.webkit.WebSettings.getDefaultUserAgent(r0)     // Catch: java.lang.Exception -> Lf
            goto L15
        Lf:
            java.lang.String r0 = "http.agent"
            java.lang.String r0 = java.lang.System.getProperty(r0)
        L15:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r2 = r0.length()
            r3 = 0
            r4 = 0
        L20:
            if (r4 >= r2) goto L48
            char r5 = r0.charAt(r4)
            r6 = 31
            if (r5 <= r6) goto L33
            r6 = 127(0x7f, float:1.78E-43)
            if (r5 < r6) goto L2f
            goto L33
        L2f:
            r1.append(r5)
            goto L45
        L33:
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6[r3] = r5
            java.lang.String r5 = "\\u%04x"
            java.lang.String r5 = java.lang.String.format(r5, r6)
            r1.append(r5)
        L45:
            int r4 = r4 + 1
            goto L20
        L48:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.p.g.b.a():java.lang.String");
    }
}
