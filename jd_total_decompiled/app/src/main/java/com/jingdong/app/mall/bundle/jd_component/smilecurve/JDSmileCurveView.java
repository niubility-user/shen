package com.jingdong.app.mall.bundle.jd_component.smilecurve;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.jd.dynamic.DYConstants;

/* loaded from: classes2.dex */
public class JDSmileCurveView extends FrameLayout {
    private LottieAnimationView fadeInView;
    private LottieAnimationView fadeOutView;
    boolean isAppeared;
    boolean isDisappeared;

    public JDSmileCurveView(Context context) {
        super(context);
        this.isAppeared = false;
        this.isDisappeared = true;
        initView();
    }

    private void initView() {
        LottieAnimationView lottieAnimationView = new LottieAnimationView(getContext());
        this.fadeInView = lottieAnimationView;
        lottieAnimationView.setImageAssetsFolder(DYConstants.DY_ASSETS);
        this.fadeInView.setAnimation("jd_smilecurve_appear.json");
        this.fadeInView.loop(false);
        LottieAnimationView lottieAnimationView2 = new LottieAnimationView(getContext());
        this.fadeOutView = lottieAnimationView2;
        lottieAnimationView2.setImageAssetsFolder(DYConstants.DY_ASSETS);
        this.fadeOutView.setAnimation("jd_smilecurve_disappear.json");
        this.fadeOutView.loop(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        addView(this.fadeInView, layoutParams);
        addView(this.fadeOutView, layoutParams);
        this.fadeInView.setVisibility(8);
        this.fadeOutView.setVisibility(8);
    }

    public void appear() {
        if (!this.isAppeared) {
            this.fadeInView.setVisibility(0);
            this.fadeOutView.setVisibility(8);
            this.fadeInView.playAnimation();
        }
        this.isAppeared = true;
        this.isDisappeared = false;
    }

    public void disappear() {
        if (!this.isDisappeared) {
            this.fadeOutView.setVisibility(0);
            this.fadeInView.setVisibility(8);
            this.fadeOutView.playAnimation();
        }
        this.isDisappeared = true;
        this.isAppeared = false;
    }

    public void initSmileCurveColor(boolean z) {
        if (z) {
            this.fadeInView.setImageAssetsFolder(DYConstants.DY_ASSETS);
            this.fadeInView.setAnimation("jd_smilecurve_appear.json");
            this.fadeOutView.setImageAssetsFolder(DYConstants.DY_ASSETS);
            this.fadeOutView.setAnimation("jd_smilecurve_disappear.json");
            return;
        }
        this.fadeInView.setImageAssetsFolder(DYConstants.DY_ASSETS);
        this.fadeInView.setAnimation("jd_whitesmilecurve_appear.json");
        this.fadeOutView.setImageAssetsFolder(DYConstants.DY_ASSETS);
        this.fadeOutView.setAnimation("jd_whitesmilecurve_disappear.json");
    }

    public JDSmileCurveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAppeared = false;
        this.isDisappeared = true;
        initView();
    }

    public JDSmileCurveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isAppeared = false;
        this.isDisappeared = true;
        initView();
    }
}
