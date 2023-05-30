package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class zh extends JsonComposer {

    /* renamed from: j  reason: collision with root package name */
    private static final String f17578j = "default";

    /* renamed from: k  reason: collision with root package name */
    private static final int f17579k = 0;

    /* renamed from: l  reason: collision with root package name */
    private static final int f17580l = 1;
    @Json(name = "rule")
    private int a = 0;
    @Json(name = RemoteMessageConst.Notification.PRIORITY)
    private int b = 0;
    @Json(name = "frontier")

    /* renamed from: c  reason: collision with root package name */
    private String f17581c = "default";
    @Json(name = "logo_name")
    private String d = "";
    @Json(name = "logo")

    /* renamed from: e  reason: collision with root package name */
    private String f17582e = "";
    @Json(name = "logo_night")

    /* renamed from: f  reason: collision with root package name */
    private String f17583f;
    @Json(ignore = true)

    /* renamed from: g  reason: collision with root package name */
    private Bitmap f17584g;
    @Json(ignore = true)

    /* renamed from: h  reason: collision with root package name */
    private Bitmap f17585h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f17586i;

    private boolean a(v5 v5Var) {
        if ("default".equals(this.f17581c)) {
            return true;
        }
        if (v5Var == null) {
            return false;
        }
        return sh.a(v5Var.b(), sh.b().c(this.f17581c));
    }

    private boolean b(v5 v5Var) {
        if ("default".equals(this.f17581c)) {
            return true;
        }
        if (v5Var == null) {
            return false;
        }
        w5[] c2 = sh.b().c(this.f17581c);
        w5[] c3 = v5Var.c();
        if (c3 == null || c2 == null) {
            return true;
        }
        return sh.a(c3, c2);
    }

    public Bitmap a(boolean z) {
        return z ? this.f17585h : this.f17584g;
    }

    public String a() {
        return this.d;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public void a(Bitmap bitmap) {
        this.f17584g = bitmap;
    }

    public void a(String str) {
        this.f17581c = str;
    }

    public String b() {
        return this.f17583f;
    }

    public void b(int i2) {
        this.a = i2;
    }

    public void b(Bitmap bitmap) {
        this.f17585h = bitmap;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(boolean z) {
        this.f17586i = z;
    }

    public String c() {
        return this.f17582e;
    }

    public void c(String str) {
        this.f17583f = str;
    }

    public boolean c(v5 v5Var) {
        int i2 = this.a;
        boolean b = i2 != 0 ? i2 != 1 ? false : b(v5Var) : a(v5Var);
        return e() ? !b : b;
    }

    public int d() {
        return (this.a * 10) + this.b;
    }

    public void d(String str) {
        this.f17582e = str;
    }

    public boolean e() {
        return this.f17586i;
    }
}
