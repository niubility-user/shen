package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import android.os.IBinder;

/* loaded from: classes9.dex */
public class h implements b, g {
    public IVendorCallback a;
    public i d;
    public String b = "";

    /* renamed from: c  reason: collision with root package name */
    public String f18077c = "";

    /* renamed from: e  reason: collision with root package name */
    public boolean f18078e = false;

    /* renamed from: f  reason: collision with root package name */
    public boolean f18079f = false;

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String a() {
        return this.b;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void a(Context context, IVendorCallback iVendorCallback) {
        this.a = iVendorCallback;
        i iVar = new i(context);
        this.d = iVar;
        iVar.a(this);
    }

    @Override // com.tencent.tmsqmsp.oaid2.g
    public void a(f fVar) {
        try {
            String c2 = fVar.c();
            this.b = c2;
            if (c2 == null) {
                this.b = "";
            }
        } catch (Exception unused) {
        }
        try {
            String i2 = fVar.i();
            this.f18077c = i2;
            if (i2 == null) {
                this.f18077c = "";
            }
        } catch (Exception unused2) {
        }
        try {
            this.f18079f = fVar.b();
        } catch (Exception unused3) {
        }
        this.f18078e = true;
        IVendorCallback iVendorCallback = this.a;
        if (iVendorCallback != null) {
            iVendorCallback.onResult(this.f18079f, this.f18077c, this.b);
        }
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    @Override // com.tencent.tmsqmsp.oaid2.g
    public void b() {
        IVendorCallback iVendorCallback = this.a;
        if (iVendorCallback != null) {
            iVendorCallback.onResult(false, null, null);
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String d() {
        return this.f18077c;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean e() {
        return this.f18079f;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void j() {
        this.d.a(this);
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean k() {
        return false;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void l() {
        i iVar;
        if (!this.f18078e || (iVar = this.d) == null) {
            return;
        }
        iVar.a();
    }
}
