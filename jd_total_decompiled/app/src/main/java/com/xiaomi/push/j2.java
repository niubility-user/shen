package com.xiaomi.push;

import com.xiaomi.push.i2;
import java.io.File;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j2 extends i2.b {
    File b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f18767c;
    final /* synthetic */ Date d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Date f18768e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f18769f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f18770g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f18771h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ i2 f18772i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j2(i2 i2Var, int i2, Date date, Date date2, String str, String str2, boolean z) {
        super(i2Var);
        this.f18772i = i2Var;
        this.f18767c = i2;
        this.d = date;
        this.f18768e = date2;
        this.f18769f = str;
        this.f18770g = str2;
        this.f18771h = z;
    }

    @Override // com.xiaomi.push.i2.b, com.xiaomi.push.l.b
    public void b() {
        if (w9.e()) {
            try {
                File file = new File(this.f18772i.b.getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    h2 h2Var = new h2();
                    h2Var.d(this.f18767c);
                    this.b = h2Var.c(this.f18772i.b, this.d, this.f18768e, file);
                }
            } catch (NullPointerException unused) {
            }
        }
    }

    @Override // com.xiaomi.push.l.b
    public void c() {
        File file = this.b;
        if (file != null && file.exists()) {
            this.f18772i.a.add(new i2.c(this.f18769f, this.f18770g, this.b, this.f18771h));
        }
        this.f18772i.e(0L);
    }
}
