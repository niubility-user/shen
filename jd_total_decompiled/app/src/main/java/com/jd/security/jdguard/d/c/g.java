package com.jd.security.jdguard.d.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.security.jdguard.d.a;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class g {

    /* renamed from: e */
    private static g f6917e;

    /* renamed from: f */
    private static Map<String, String> f6918f;
    private Context a;
    private SharedPreferences b;

    /* renamed from: c */
    private SharedPreferences.Editor f6919c;
    private e d;

    /* loaded from: classes17.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.d.values().length];
            a = iArr;
            try {
                iArr[a.d.ENV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.d.STATIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static g c() {
        if (f6917e == null) {
            synchronized (g.class) {
                if (f6917e == null) {
                    f6917e = new g();
                }
            }
        }
        return f6917e;
    }

    private long d(String str, long j2) {
        return this.b.getLong(str, j2);
    }

    private long g() {
        return d("eva_plc_update_l_ts", 0L);
    }

    private void h(Map<String, String> map) {
        try {
            if (TextUtils.isEmpty(map.get("plc"))) {
                return;
            }
            k(new JSONObject(r0).optInt("uv"));
            for (a.d dVar : a.d.values()) {
                e(dVar).a(map);
            }
        } catch (Throwable unused) {
        }
    }

    private void i(long j2) {
        j("eva_plc_update_l_ts", j2);
    }

    private void j(String str, long j2) {
        this.f6919c.putLong(str, j2);
        this.f6919c.apply();
    }

    private void k(long j2) {
        j("eva_plc_update_interval", j2);
    }

    private long m() {
        return d("eva_plc_update_interval", 0L);
    }

    public g a(Context context) {
        this.a = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("jdgeva", 0);
        this.b = sharedPreferences;
        this.f6919c = sharedPreferences.edit();
        return this;
    }

    public g b(e eVar) {
        this.d = eVar;
        return this;
    }

    public f e(a.d dVar) {
        int i2 = a.a[dVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return new com.jd.security.jdguard.d.c.a(this.a, this.b, this.f6919c, dVar.key);
            }
            return new h(this.a, this.b, this.f6919c, dVar.key);
        }
        return new c(this.a, this.b, this.f6919c, dVar.key);
    }

    public void f() {
        if (this.f6919c == null || this.d == null || this.a == null || this.b == null) {
            return;
        }
        l();
    }

    public void l() {
        if (this.d == null) {
            return;
        }
        if (g() == 0 || m() == 0 || System.currentTimeMillis() - g() >= m() * 60 * 1000) {
            Map<String, String> evaConfigs = this.d.getEvaConfigs();
            f6918f = evaConfigs;
            if (evaConfigs == null || evaConfigs.isEmpty()) {
                return;
            }
            h(f6918f);
            i(System.currentTimeMillis());
        }
    }
}
