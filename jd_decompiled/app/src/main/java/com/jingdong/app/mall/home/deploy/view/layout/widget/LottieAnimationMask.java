package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.airbnb.lottie.TextDelegate;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.view.b.f.d;
import com.jingdong.app.mall.home.floor.view.b.f.e;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.p.b.d.c;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class LottieAnimationMask extends LottieAnimationViewCatchDraw {

    /* renamed from: k  reason: collision with root package name */
    private static String f9054k;

    /* renamed from: l  reason: collision with root package name */
    private static boolean f9055l;

    /* renamed from: g  reason: collision with root package name */
    private boolean f9056g;

    /* renamed from: h  reason: collision with root package name */
    private final String f9057h;

    /* renamed from: i  reason: collision with root package name */
    private TextDelegate f9058i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f9059j;

    public LottieAnimationMask(Context context, String str) {
        super(context);
        this.f9059j = new AtomicBoolean(false);
        this.f9057h = str;
        c();
    }

    private void c() {
        try {
            setImageAssetsFolder("assets/");
            if (TextUtils.isEmpty(f9054k)) {
                f9054k = k.o("local/homeCoreLottie.json");
            }
            if (!TextUtils.isEmpty(f9054k) && isValid(f9054k)) {
                setLottieJson(f9054k, this.f9057h);
                this.f9056g = true;
            } else {
                setVisibility(8);
            }
            setRepeatCount(0);
            TextDelegate textDelegate = new TextDelegate(this);
            this.f9058i = textDelegate;
            setTextDelegate(textDelegate);
            addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.LottieAnimationMask.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    LottieAnimationMask.this.d();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    LottieAnimationMask.this.d();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        e.j().i();
        c.g().k(false);
        setVisibility(8);
        f.H0(this);
    }

    public static void e(boolean z) {
        f9055l = z;
    }

    public boolean b(int i2) {
        return this.f9056g && !i.i() && !f9055l && d.f(this.f9057h, 1) && d.g(this.f9057h, i2);
    }

    @Override // com.airbnb.lottie.LottieAnimationView
    public void cancelAnimation() {
        try {
            super.cancelAnimation();
        } catch (Exception unused) {
        }
    }

    public void f(String str) {
        TextDelegate textDelegate = this.f9058i;
        if (textDelegate != null) {
            textDelegate.setText("NAME", str);
        }
    }

    public void g() {
        f.G0(this);
        d.a(this.f9057h, Integer.MAX_VALUE);
        d.c(this.f9057h, Integer.MAX_VALUE);
        e.j().d();
        c.g().k(true);
        setVisibility(0);
        this.f9059j.set(false);
        playAnimation();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (!"home_pause".equals(baseEvent.getType()) || this.f9059j.getAndSet(true)) {
            return;
        }
        f.H0(this);
        cancelAnimation();
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = -1;
        layoutParams.height = -1;
        super.setLayoutParams(layoutParams);
    }
}
