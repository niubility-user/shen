package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.jingdong.sdk.platform.business.personal.R2;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class j1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f18390g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j1(Context context) {
        this.f18390g = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            PackageInfo packageInfo = this.f18390g.getPackageManager().getPackageInfo(this.f18390g.getPackageName(), R2.color.pd_color_FF000000);
            i1.j(this.f18390g);
            i1.l(this.f18390g, packageInfo);
            i1.k(this.f18390g, packageInfo);
        } catch (Throwable unused) {
        }
    }
}
