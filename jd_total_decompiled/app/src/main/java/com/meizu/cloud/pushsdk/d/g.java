package com.meizu.cloud.pushsdk.d;

import android.content.Context;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: classes14.dex */
public class g extends h<f> implements f {
    private static g d;

    /* renamed from: c */
    private boolean f15712c;

    private g(f fVar) {
        super(fVar);
        this.f15712c = false;
    }

    public static g e() {
        if (d == null) {
            synchronized (g.class) {
                if (d == null) {
                    d = new g(new b());
                }
            }
        }
        return d;
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str) {
        b().a(str);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str, String str2) {
        b().a(str, str2);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str, String str2, Throwable th) {
        b().a(str, str2, th);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(boolean z) {
        b().a(z);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public boolean a() {
        return b().a();
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void b(String str, String str2) {
        b().b(str, str2);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void b(boolean z) {
        b().b(z);
    }

    public void c(Context context) {
        d(context, (String) null);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void c(String str, String str2) {
        b().c(str, str2);
    }

    public void d(Context context, String str) {
        if (this.f15712c) {
            return;
        }
        this.f15712c = true;
        a((context.getApplicationInfo().flags & 2) != 0);
        if (str == null) {
            str = MzSystemUtils.getDocumentsPath(context) + "/pushSdk/" + context.getPackageName();
        }
        a(str);
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void d(String str, String str2) {
        b().d(str, str2);
    }
}
