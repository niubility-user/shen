package com.jingdong.app.mall.safemode;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

/* loaded from: classes4.dex */
public abstract class d {
    protected TextView a;
    protected TextView b;

    /* renamed from: c  reason: collision with root package name */
    protected TextView f11612c;
    protected Button d;

    /* renamed from: e  reason: collision with root package name */
    protected Activity f11613e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f11614f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f11615g;

    public void a() {
        i.g().c();
        Activity activity = this.f11613e;
        if (activity != null && !activity.isFinishing()) {
            this.f11613e.finish();
        }
        h.a();
    }

    public abstract void b(String str);
}
