package com.jingdong.app.mall.home.n.g.w;

import android.graphics.drawable.Drawable;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class d {
    public f a;
    public f b;

    /* renamed from: c  reason: collision with root package name */
    public int f10389c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    private Drawable f10390e;

    /* renamed from: f  reason: collision with root package name */
    private int f10391f;

    public Drawable a() {
        if (this.f10390e == null || this.f10391f != com.jingdong.app.mall.home.floor.common.d.f9279g) {
            this.f10390e = com.jingdong.app.mall.home.floor.ctrl.e.w();
            this.f10391f = com.jingdong.app.mall.home.floor.common.d.f9279g;
        }
        return this.f10390e;
    }

    public boolean b() {
        return (this.a == null || this.b == null || this.d <= 0) ? false : true;
    }
}
