package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.animation.Animator;
import com.jingdong.app.mall.home.r.a.a;
import com.jingdong.app.mall.home.r.a.b;
import com.jingdong.app.mall.home.r.a.c;
import com.jingdong.app.mall.home.r.e.h;

/* loaded from: classes4.dex */
public class BannerAnimation implements a, b {

    /* renamed from: g  reason: collision with root package name */
    private DBanner2x4 f8895g;

    /* renamed from: h  reason: collision with root package name */
    private DBanner2x4Model f8896h;

    public BannerAnimation(DBanner2x4 dBanner2x4, DBanner2x4Model dBanner2x4Model) {
        this.f8895g = dBanner2x4;
        this.f8896h = dBanner2x4Model;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public int getFloorPos() {
        h d;
        com.jingdong.app.mall.home.p.b.e.b o = this.f8896h.o();
        if (o == null || (d = o.d()) == null) {
            return 0;
        }
        return d.f10696h;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public String getModelId() {
        return this.f8896h.B0();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public int getPriority() {
        return 4;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public int getSubPriority() {
        return 5;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public c.e getType() {
        return c.e.Other;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isDictator() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isInDisplayArea(int i2, int i3) {
        return this.f8895g.l();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isMatchOtherStartCondition() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public boolean isNeedWait() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public boolean isSplashAnimation() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void pause(int i2) {
        this.f8895g.E(i2);
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void startPlay() {
        this.f8895g.F();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void stopPlay() {
        this.f8895g.E(0);
    }
}
