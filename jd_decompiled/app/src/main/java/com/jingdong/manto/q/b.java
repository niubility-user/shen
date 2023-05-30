package com.jingdong.manto.q;

import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: classes16.dex */
public class b {
    WeakReference<View> a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    boolean f14008c;
    c d;

    /* renamed from: e  reason: collision with root package name */
    int f14009e;

    /* renamed from: f  reason: collision with root package name */
    int f14010f;

    public b(View view, int i2, int i3, int i4, boolean z) {
        this.a = new WeakReference<>(view);
        this.f14009e = i2;
        this.b = i3;
        this.f14010f = i4;
        this.f14008c = z;
    }
}
