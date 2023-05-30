package jd.wjlogin_sdk.common;

import android.text.TextUtils;
import java.util.Date;
import jd.wjlogin_sdk.util.i;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f19740c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f19741e;

    /* renamed from: f  reason: collision with root package name */
    private Date f19742f;

    /* renamed from: g  reason: collision with root package name */
    private String f19743g;

    /* renamed from: h  reason: collision with root package name */
    private String f19744h;

    public void a(Date date) {
        this.f19742f = date;
    }

    public String b() {
        return this.f19740c;
    }

    public Date c() {
        return this.f19742f;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.f19741e;
    }

    public String f() {
        return this.a;
    }

    public String g() {
        return this.f19743g;
    }

    public String h() {
        return this.f19744h;
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a4", this.f19740c);
            jSONObject.put("pin", this.a);
            jSONObject.put("dwChangeTime", this.d);
            jSONObject.put("dwExpireTime", this.f19741e);
            Date date = this.f19742f;
            if (date != null) {
                jSONObject.put("a4CreateTime", i.b(date));
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String a() {
        return this.b;
    }

    public void b(String str) {
        this.f19740c = str;
    }

    public void c(String str) {
        this.a = str;
    }

    public void d(String str) {
        this.f19743g = str;
    }

    public void e(String str) {
        this.f19744h = str;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(int i2) {
        this.f19741e = i2;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public void a(JSONObject jSONObject) {
        this.f19740c = jSONObject.optString("a4", "");
        this.a = jSONObject.optString("pin", "");
        String optString = jSONObject.optString("a4CreateTime", "");
        if (!TextUtils.isEmpty(optString)) {
            this.f19742f = i.a(optString);
        }
        this.d = jSONObject.optInt("dwChangeTime");
        this.f19741e = jSONObject.optInt("dwExpireTime");
    }
}
