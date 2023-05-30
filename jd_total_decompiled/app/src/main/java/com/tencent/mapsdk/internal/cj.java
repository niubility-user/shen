package com.tencent.mapsdk.internal;

import android.content.Context;

/* loaded from: classes9.dex */
public class cj implements ce {
    private Context a;
    private final lc b;

    /* renamed from: c  reason: collision with root package name */
    private String f16377c;

    public cj(Context context, String str) {
        if (context == null) {
            this.b = null;
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = lc.b(applicationContext);
        this.f16377c = str;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String a() {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String b() {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String c() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.g();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String d() {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String e() {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String f() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.a(this.f16377c);
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String g() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.f();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String h() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.d();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String i() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.d(this.f16377c);
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ce
    public String j() {
        lc lcVar = this.b;
        if (lcVar != null) {
            return lcVar.c(this.f16377c);
        }
        return null;
    }
}
