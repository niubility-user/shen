package com.jd.security.jdguard.d.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a implements f {
    protected String a;
    protected String b;

    /* renamed from: c  reason: collision with root package name */
    protected String f6911c;
    protected String d;

    /* renamed from: e  reason: collision with root package name */
    protected String f6912e;

    /* renamed from: f  reason: collision with root package name */
    protected String f6913f;

    /* renamed from: g  reason: collision with root package name */
    protected SharedPreferences f6914g;

    /* renamed from: h  reason: collision with root package name */
    protected SharedPreferences.Editor f6915h;

    /* renamed from: i  reason: collision with root package name */
    private String f6916i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, String str) {
        this.f6915h = editor;
        this.f6914g = sharedPreferences;
        this.f6916i = str;
        this.a = str;
        this.b = this.a + "_plc_";
        this.f6911c = this.a + "_l_ts";
        this.d = this.a + "_s_delay";
        this.f6912e = this.a + "_s_interval";
        this.f6913f = this.a + "_s_sw";
    }

    @Override // com.jd.security.jdguard.d.c.f
    public void a(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            try {
                String str = map.get(this.f6916i);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                h(new JSONObject(str));
            } catch (JSONException unused) {
            }
        }
    }

    @Override // com.jd.security.jdguard.d.c.f
    public long b() {
        return f(this.f6912e, 360L);
    }

    @Override // com.jd.security.jdguard.d.c.f
    public long c() {
        return f(this.d, 3L);
    }

    @Override // com.jd.security.jdguard.d.c.f
    public long d() {
        return f(this.f6911c, 0L);
    }

    @Override // com.jd.security.jdguard.d.c.f
    public void e(long j2) {
        j(this.f6911c, j2);
    }

    @Override // com.jd.security.jdguard.d.c.f
    public boolean enable() {
        return this.f6914g.getBoolean(this.f6913f, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long f(String str, long j2) {
        return this.f6914g.getLong(str, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String g(String str) {
        return this.f6914g.getString(this.b + str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h(JSONObject jSONObject) {
    }

    public void i(boolean z) {
        this.f6915h.putBoolean(this.f6913f, z);
        this.f6915h.apply();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j(String str, long j2) {
        this.f6915h.putLong(str, j2);
        this.f6915h.apply();
    }

    public void k(long j2) {
        j(this.d, j2);
    }

    public void l(long j2) {
        j(this.f6912e, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void m(String str, String str2) {
        this.f6915h.putString(this.b + str, str2);
        this.f6915h.apply();
    }
}
