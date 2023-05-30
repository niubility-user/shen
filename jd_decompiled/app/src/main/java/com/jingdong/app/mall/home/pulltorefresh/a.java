package com.jingdong.app.mall.home.pulltorefresh;

import android.content.Context;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.pullrefresh.BaseLoadingView;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;

/* loaded from: classes4.dex */
public class a implements com.jingdong.app.mall.home.pullrefresh.a {

    /* renamed from: e */
    private static int f10580e = 130;

    /* renamed from: f */
    private static int f10581f = 320;
    protected BaseLoadingView a;
    protected BaseLoadingView b;

    /* renamed from: c */
    private JDHomeLoadingView f10582c;
    private int d;

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public final BaseLoadingView a() {
        return this.b;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public float b() {
        return d.d(f10580e);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public int d() {
        return (int) (b() - d.d(50));
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public void e(BaseLoadingView baseLoadingView) {
        this.a = baseLoadingView;
        baseLoadingView.m(BaseVerticalRefresh.g.PULL_FROM_START);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public final BaseLoadingView f() {
        return this.a;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public int g() {
        return 200;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public int getHeaderSize() {
        BaseLoadingView baseLoadingView = this.a;
        if (baseLoadingView instanceof JDHomeLoadingView) {
            return (int) b();
        }
        if (baseLoadingView == null) {
            return 0;
        }
        return baseLoadingView.a();
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public float h() {
        return 2.0f;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public float i() {
        return 1.0f;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public float j() {
        return d.d(f10581f);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    public int k() {
        BaseLoadingView baseLoadingView = this.b;
        if (baseLoadingView == null) {
            return 0;
        }
        return baseLoadingView.a();
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.a
    /* renamed from: l */
    public JDHomeLoadingView c(Context context, BaseVerticalRefresh.g gVar) {
        JDHomeLoadingView jDHomeLoadingView = new JDHomeLoadingView(context, gVar);
        this.f10582c = jDHomeLoadingView;
        jDHomeLoadingView.setVisibility(4);
        this.f10582c.K(this.d);
        return this.f10582c;
    }

    public int m() {
        return d.d(50);
    }

    public BaseLoadingView n() {
        return this.f10582c;
    }

    public void o(int i2) {
        if (this.d == i2) {
            return;
        }
        this.d = i2;
        JDHomeLoadingView jDHomeLoadingView = this.f10582c;
        if (jDHomeLoadingView != null) {
            jDHomeLoadingView.K(i2);
        }
    }

    public BaseLoadingView p(Context context) {
        if (this.f10582c == null) {
            this.f10582c = c(context, BaseVerticalRefresh.g.PULL_FROM_START);
        }
        this.f10582c.k();
        return this.f10582c;
    }

    public void q(int i2, int i3) {
        if (i2 > 50) {
            f10580e = Math.min(i2, 130);
        }
        int i4 = f10580e;
        if (i3 - i4 >= 110) {
            f10581f = Math.min(i4 + 200, i3);
        } else {
            f10581f = i4 + 180;
        }
    }
}
