package jd.wjlogin_sdk.net;

import android.os.Build;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jd.wjlogin_sdk.util.c0;

/* loaded from: classes.dex */
public abstract class AbsHttpService implements c {

    /* renamed from: l */
    static final Map<String, String> f19828l = new HashMap<String, String>() { // from class: jd.wjlogin_sdk.net.AbsHttpService.1
        {
            put("User-Agent", "Android WJLoginSDK 9.9.6");
            put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
    };
    int a;
    int b;

    /* renamed from: c */
    int f19829c;
    boolean d;

    /* renamed from: e */
    String f19830e;

    /* renamed from: f */
    String f19831f;

    /* renamed from: g */
    byte[] f19832g;

    /* renamed from: h */
    int f19833h;

    /* renamed from: i */
    Map<String, String> f19834i;

    /* renamed from: j */
    Map<String, List<String>> f19835j;

    /* renamed from: k */
    boolean f19836k;

    /* loaded from: classes.dex */
    public static abstract class a {
        String a;
        boolean b;

        /* renamed from: e */
        byte[] f19838e;

        /* renamed from: c */
        int f19837c = 0;
        Map<String, String> d = new HashMap();

        /* renamed from: f */
        int f19839f = 1;

        /* renamed from: g */
        int f19840g = 15000;

        /* renamed from: h */
        int f19841h = 1;

        /* renamed from: i */
        boolean f19842i = false;

        public a a(boolean z) {
            this.f19842i = z;
            return this;
        }

        public abstract AbsHttpService a();

        public a b(int i2) {
            this.f19837c = i2;
            return this;
        }

        public a c(int i2) {
            this.f19840g = i2;
            return this;
        }

        public a d(int i2) {
            this.f19841h = i2;
            return this;
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(boolean z) {
            this.b = z;
            return this;
        }

        public a a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public a a(byte[] bArr) {
            this.f19838e = bArr;
            return this;
        }

        public a a(int i2) {
            this.f19839f = i2;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        public static final int a = 0;
        public static final int b = 1;
    }

    AbsHttpService(String str, int i2, Map<String, String> map, byte[] bArr, boolean z, int i3, int i4, int i5) {
        this.b = 1;
        this.f19829c = 15000;
        this.f19833h = 1;
        this.f19836k = false;
        this.f19830e = str;
        this.a = i2;
        this.f19834i = map;
        this.f19832g = bArr;
        this.d = z;
        this.b = i3;
        this.f19829c = i4;
        this.f19833h = i5;
    }

    public boolean a(int i2) {
        return this.f19833h == 2 ? i2 == 200 || i2 == 299 : i2 == 200;
    }

    @Override // jd.wjlogin_sdk.net.c
    public Map<String, List<String>> c() {
        return this.f19835j;
    }

    public void d() {
        if (!this.d || !this.f19830e.startsWith("https://") || this.b < 2 || Build.VERSION.SDK_INT >= 28) {
            return;
        }
        this.f19831f = this.f19830e.replaceFirst("https://", "http://");
        c0.a((short) 257, "Execut_retryWithHttp");
    }

    public AbsHttpService(String str, int i2, Map<String, String> map, byte[] bArr, boolean z, int i3, int i4, int i5, boolean z2) {
        this.b = 1;
        this.f19829c = 15000;
        this.f19833h = 1;
        this.f19836k = false;
        this.f19830e = str;
        this.a = i2;
        this.f19834i = map;
        this.f19832g = bArr;
        this.d = z;
        this.b = i3;
        this.f19829c = i4;
        this.f19833h = i5;
        this.f19836k = z2;
    }
}
