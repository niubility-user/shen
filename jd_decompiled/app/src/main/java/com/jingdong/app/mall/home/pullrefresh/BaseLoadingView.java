package com.jingdong.app.mall.home.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;

/* loaded from: classes4.dex */
public abstract class BaseLoadingView extends FrameLayout {

    /* renamed from: g */
    protected BaseVerticalRefresh.g f10543g;

    public BaseLoadingView(Context context) {
        super(context);
        this.f10543g = BaseVerticalRefresh.g.a();
    }

    public abstract int a();

    public abstract void b(float f2);

    public abstract void c(float f2);

    public abstract void d(float f2, boolean z, boolean z2);

    public abstract void e(int i2);

    public abstract void f(int i2, int i3);

    public abstract void g();

    public abstract void h();

    public abstract boolean i(boolean z);

    public abstract void j();

    public abstract void k();

    public abstract void l(int i2);

    public void m(BaseVerticalRefresh.g gVar) {
        this.f10543g = gVar;
    }

    public abstract void n(BaseVerticalRefresh.l lVar);

    public BaseLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10543g = BaseVerticalRefresh.g.a();
    }

    public BaseLoadingView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f10543g = BaseVerticalRefresh.g.a();
    }
}
