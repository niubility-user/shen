package com.jingdong.app.mall.home.n.g.w;

import android.graphics.Rect;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class b {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private f f10385c = new f(-1, -1);
    public boolean d = true;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public f c() {
        return this.f10385c;
    }

    public int d() {
        return this.f10385c.n();
    }

    public void e(String str) {
        this.b = str;
    }

    public void f(String str) {
        this.a = str;
    }

    public void g(Rect rect) {
        this.f10385c.F(rect);
    }
}
