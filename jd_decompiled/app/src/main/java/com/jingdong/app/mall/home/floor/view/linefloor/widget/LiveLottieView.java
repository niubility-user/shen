package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class LiveLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: j */
    private static String f9914j;

    /* renamed from: k */
    private static AtomicBoolean f9915k = new AtomicBoolean(false);

    /* renamed from: g */
    private AtomicBoolean f9916g;

    /* renamed from: h */
    private com.jingdong.app.mall.home.floor.view.linefloor.base.b f9917h;

    /* renamed from: i */
    private int f9918i;

    /* loaded from: classes4.dex */
    public class a implements Animator.AnimatorListener {
        a() {
            LiveLottieView.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            LiveLottieView.this.j();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
            LiveLottieView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (LiveLottieView.f9915k.get()) {
                if (LiveLottieView.this.h()) {
                    if (LiveLottieView.this.f9916g.get()) {
                        LiveLottieView.this.onResume();
                        return;
                    }
                    LiveLottieView.this.f9916g.set(true);
                    LiveLottieView.this.playAnimation();
                } else if (LiveLottieView.this.f9916g.get()) {
                    LiveLottieView.this.onPause();
                }
            }
        }
    }

    public LiveLottieView(Context context) {
        super(context);
        this.f9916g = new AtomicBoolean(false);
        initLiveLottie();
    }

    private void g() {
        if (f9915k.get()) {
            if (h()) {
                onResume();
            } else {
                onPause();
            }
        }
    }

    public boolean h() {
        if (getVisibility() != 0) {
            return false;
        }
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 0, true);
    }

    private void initLiveLottie() {
        try {
            setImageAssetsFolder("assets/");
            if (TextUtils.isEmpty(f9914j)) {
                f9914j = k.o("local/homeLiveLottie.json");
            }
            if (isValid(f9914j)) {
                setLottieJson(f9914j, "HOME_LIVE_LOTTIE");
                f9915k.set(true);
            } else {
                setVisibility(8);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setRepeatCount(0);
        addAnimatorListener(new a());
    }

    public void onPause() {
        if (f9915k.get()) {
            pauseAnimation();
        }
    }

    public void onResume() {
        if (this.f9916g.get()) {
            resumeAnimation();
        } else {
            k();
        }
    }

    public void f(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        if (bVar != null && bVar.t0(this.f9918i)) {
            f.G0(this);
            setVisibility(0);
            this.f9917h = bVar;
            this.f9918i = i2;
            bVar.N(i2);
            k();
            return;
        }
        setVisibility(8);
        i();
    }

    public void i() {
        pauseAnimation();
        f.H0(this);
    }

    public void j() {
        try {
            com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar = this.f9917h;
            if (bVar != null && bVar.t0(this.f9918i)) {
                this.f9917h.N(this.f9918i);
                setFrame(0);
                k();
                return;
            }
            setVisibility(8);
            i();
        } catch (Exception e2) {
            f.o(e2.getMessage());
        }
    }

    public void k() {
        if (getVisibility() != 0) {
            return;
        }
        f.u0(new b());
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
                g();
                return;
            default:
                return;
        }
    }
}
