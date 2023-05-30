package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes9.dex */
public class u implements b, s {
    public r a;
    public Context b;

    /* renamed from: c  reason: collision with root package name */
    public IVendorCallback f18096c;

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String a() {
        String b;
        return (!e() || (b = this.a.b()) == null) ? "" : b;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void a(Context context, IVendorCallback iVendorCallback) {
        if (r.a(context)) {
            String a = e.a(context);
            if (!TextUtils.isEmpty(a)) {
                r.a(context, a);
            }
            this.a = new r(context, this);
            this.f18096c = iVendorCallback;
            this.b = context;
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.s
    public void a(boolean z) {
        IVendorCallback iVendorCallback = this.f18096c;
        if (iVendorCallback != null) {
            iVendorCallback.onResult(e(), d(), a());
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.s
    public void b() {
        IVendorCallback iVendorCallback = this.f18096c;
        if (iVendorCallback != null) {
            iVendorCallback.onResult(false, "", "");
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String d() {
        String a;
        return (!e() || (a = this.a.a()) == null) ? "" : a;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean e() {
        r rVar = this.a;
        if (rVar != null) {
            return rVar.c();
        }
        return false;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void j() {
        r rVar = this.a;
        if (rVar != null) {
            rVar.a(e.a(this.b));
        } else {
            b();
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean k() {
        return false;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void l() {
        r rVar = this.a;
        if (rVar != null) {
            rVar.d();
        }
    }
}
