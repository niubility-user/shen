package com.jingdong.app.mall.home.floor.animation.lottie;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeSimpleLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: g */
    protected final AtomicBoolean f9133g;

    /* renamed from: h */
    protected final AtomicBoolean f9134h;

    /* renamed from: i */
    protected String f9135i;

    /* loaded from: classes4.dex */
    public class a extends b {
        a() {
            HomeSimpleLottieView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (HomeSimpleLottieView.this.f9133g.get()) {
                if (HomeSimpleLottieView.this.f()) {
                    if (HomeSimpleLottieView.this.f9134h.get()) {
                        HomeSimpleLottieView.this.onResume();
                        return;
                    }
                    HomeSimpleLottieView.this.f9134h.set(true);
                    HomeSimpleLottieView.this.playAnimation();
                } else if (HomeSimpleLottieView.this.f9134h.get()) {
                    HomeSimpleLottieView.this.onPause();
                }
            }
        }
    }

    public HomeSimpleLottieView(Context context) {
        super(context);
        this.f9133g = new AtomicBoolean(false);
        this.f9134h = new AtomicBoolean(false);
    }

    private void d() {
        if (this.f9133g.get()) {
            if (f()) {
                onResume();
            } else {
                onPause();
            }
        }
    }

    public boolean f() {
        if (getVisibility() != 0) {
            return false;
        }
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 0, true);
    }

    public void onPause() {
        if (this.f9133g.get()) {
            pauseAnimation();
        }
    }

    public void onResume() {
        if (this.f9134h.get()) {
            resumeAnimation();
        } else {
            j();
        }
    }

    public void e(String str, String str2) {
        try {
            setImageAssetsFolder("assets/");
            if (TextUtils.isEmpty(this.f9135i)) {
                this.f9135i = k.o(str);
            }
            if (isValid(this.f9135i)) {
                setLottieJson(this.f9135i, str2);
                this.f9133g.set(true);
                return;
            }
            setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean g() {
        return this.f9133g.get();
    }

    public void h() {
        pauseAnimation();
        f.H0(this);
        setVisibility(8);
    }

    public void i() {
        try {
            setFrame(0);
            j();
        } catch (Exception e2) {
            f.o(e2.getMessage());
        }
    }

    public void j() {
        if (getVisibility() != 0) {
            return;
        }
        f.F0(new a(), 800L);
    }

    public void k() {
        if (!this.f9133g.get()) {
            setVisibility(8);
            return;
        }
        f.G0(this);
        setVisibility(0);
        j();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1158331917:
                if (type.equals("homePageXViewDisplay")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 815832937:
                if (type.equals("homePageXViewClose")) {
                    c2 = 2;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1236015766:
                if (type.equals("home_pause")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 4:
                onPause();
                return;
            case 1:
            case 2:
            case 3:
                d();
                return;
            default:
                return;
        }
    }
}
