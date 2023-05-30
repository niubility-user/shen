package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class LottieLoadingView extends LottieAnimationView {
    private boolean initSuccess;

    public LottieLoadingView(Context context) {
        super(context);
        this.initSuccess = false;
        init();
    }

    public static void freeLottieMemory(View view) {
        if (view == null) {
            return;
        }
        try {
            if (view instanceof LottieLoadingView) {
                ((LottieLoadingView) view).freeResource();
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d("LottieLoadingView", "freeLottieMemory error : " + th.getMessage());
            }
        }
    }

    private void init() {
        try {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.dip2px(45.0f), DPIUtil.dip2px(45.0f));
            layoutParams.addRule(13);
            setAnimation("loading.json");
            setLayoutParams(layoutParams);
            loop(true);
            playAnimation();
            this.initSuccess = true;
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d("LottieLoadingView", "init LottieLoadingView error : " + th.getMessage());
            }
            this.initSuccess = false;
        }
    }

    public void freeResource() {
        clearColorFilter();
        cancelAnimation();
    }

    public boolean initSuccess() {
        return this.initSuccess;
    }

    public LottieLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initSuccess = false;
        init();
    }

    public LottieLoadingView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.initSuccess = false;
        init();
    }
}
