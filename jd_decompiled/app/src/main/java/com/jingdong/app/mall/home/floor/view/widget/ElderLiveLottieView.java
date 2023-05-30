package com.jingdong.app.mall.home.floor.view.widget;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLiveVideoElder;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class ElderLiveLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: j  reason: collision with root package name */
    private static String f10059j;

    /* renamed from: k  reason: collision with root package name */
    private static AtomicBoolean f10060k = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f10061g;

    /* renamed from: h  reason: collision with root package name */
    private MallFloorLiveVideoElder.VideoElderItem f10062h;

    /* renamed from: i  reason: collision with root package name */
    private int f10063i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Animator.AnimatorListener {
        a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ElderLiveLottieView.this.j();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (ElderLiveLottieView.f10060k.get()) {
                if (ElderLiveLottieView.this.h()) {
                    if (ElderLiveLottieView.this.f10061g.get()) {
                        ElderLiveLottieView.this.onResume();
                        return;
                    }
                    ElderLiveLottieView.this.f10061g.set(true);
                    ElderLiveLottieView.this.playAnimation();
                } else if (ElderLiveLottieView.this.f10061g.get()) {
                    ElderLiveLottieView.this.onPause();
                }
            }
        }
    }

    public ElderLiveLottieView(Context context) {
        super(context);
        this.f10061g = new AtomicBoolean(false);
        initLiveLottie();
    }

    private void g() {
        if (f10060k.get()) {
            if (h()) {
                onResume();
            } else {
                onPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        if (getVisibility() != 0) {
            return false;
        }
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 0, true);
    }

    private void initLiveLottie() {
        try {
            setImageAssetsFolder("assets/");
            if (TextUtils.isEmpty(f10059j)) {
                f10059j = k.o("local/homeLiveLottie.json");
            }
            if (isValid(f10059j)) {
                setLottieJson(f10059j, "HOME_LIVE_LOTTIE");
                f10060k.set(true);
            } else {
                setVisibility(8);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setRepeatCount(0);
        addAnimatorListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPause() {
        if (f10060k.get()) {
            pauseAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResume() {
        if (this.f10061g.get()) {
            resumeAnimation();
        } else {
            k();
        }
    }

    public void f(MallFloorLiveVideoElder.VideoElderItem videoElderItem, int i2) {
        if (videoElderItem != null && videoElderItem.hasBubbleTimes(i2)) {
            f.G0(this);
            setVisibility(0);
            this.f10062h = videoElderItem;
            this.f10063i = i2;
            videoElderItem.addBubbleTimes(i2);
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
            MallFloorLiveVideoElder.VideoElderItem videoElderItem = this.f10062h;
            if (videoElderItem != null && videoElderItem.hasBubbleTimes(this.f10063i)) {
                this.f10062h.addBubbleTimes(this.f10063i);
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
