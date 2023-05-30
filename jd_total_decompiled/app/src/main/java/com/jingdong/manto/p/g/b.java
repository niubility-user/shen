package com.jingdong.manto.p.g;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
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

    /* JADX WARN: Removed duplicated region for block: B:59:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a() {
        String defaultUserAgent;
        int length;
        int i2;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                defaultUserAgent = WebSettings.getDefaultUserAgent(com.jingdong.manto.c.a());
            } catch (Exception unused) {
            }
            StringBuilder sb = new StringBuilder();
            length = defaultUserAgent.length();
            for (i2 = 0; i2 < length; i2++) {
                char charAt = defaultUserAgent.charAt(i2);
                if (charAt <= 31 || charAt >= '\u007f') {
                    sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
                } else {
                    sb.append(charAt);
                }
            }
            return sb.toString();
        }
        defaultUserAgent = System.getProperty("http.agent");
        StringBuilder sb2 = new StringBuilder();
        length = defaultUserAgent.length();
        while (i2 < length) {
        }
        return sb2.toString();
    }
}
