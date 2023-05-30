package com.vivo.push.b;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class c extends com.vivo.push.o {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private long f18246c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f18247e;

    /* renamed from: f  reason: collision with root package name */
    private String f18248f;

    public c(int i2, String str) {
        super(i2);
        this.f18246c = -1L;
        this.d = -1;
        this.a = null;
        this.b = str;
    }

    public final void a(int i2) {
        this.f18247e = i2;
    }

    public final void b(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.o
    public void c(com.vivo.push.a aVar) {
        aVar.a("req_id", this.a);
        aVar.a("package_name", this.b);
        aVar.a("sdk_version", 323L);
        aVar.a("PUSH_APP_STATUS", this.d);
        if (TextUtils.isEmpty(this.f18248f)) {
            return;
        }
        aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION", this.f18248f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.o
    public void d(com.vivo.push.a aVar) {
        this.a = aVar.a("req_id");
        this.b = aVar.a("package_name");
        this.f18246c = aVar.b("sdk_version", 0L);
        this.d = aVar.b("PUSH_APP_STATUS", 0);
        this.f18248f = aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION");
    }

    public final int f() {
        return this.f18247e;
    }

    public final void g() {
        this.f18248f = null;
    }

    public final String h() {
        return this.a;
    }

    @Override // com.vivo.push.o
    public String toString() {
        return "BaseAppCommand";
    }

    public final int a(Context context) {
        if (this.d == -1) {
            String str = this.b;
            if (TextUtils.isEmpty(str)) {
                com.vivo.push.util.p.a("BaseAppCommand", "pkg name is null");
                String a = a();
                if (TextUtils.isEmpty(a)) {
                    com.vivo.push.util.p.a("BaseAppCommand", "src is null");
                    return -1;
                }
                str = a;
            }
            this.d = com.vivo.push.util.t.b(context, str);
            if (!TextUtils.isEmpty(this.f18248f)) {
                this.d = 2;
            }
        }
        return this.d;
    }
}
