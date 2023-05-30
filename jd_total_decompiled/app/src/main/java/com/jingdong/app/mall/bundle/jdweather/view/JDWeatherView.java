package com.jingdong.app.mall.bundle.jdweather.view;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.jdweather.config.JDWeatherAnimConfig;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class JDWeatherView extends LinearLayout implements View.OnAttachStateChangeListener, LottieOnCompositionLoadedListener {
    private static final String TAG = "JDWeatherView";
    private View weatherLottieView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements LottieListener<Throwable> {
        a(JDWeatherView jDWeatherView) {
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onResult(Throwable th) {
        }
    }

    public JDWeatherView(Context context) {
        super(context);
    }

    private void hookExceptionListener() {
        try {
            Field declaredField = Class.forName("com.airbnb.lottie.LottieAnimationView").getDeclaredField("failureListener");
            a aVar = new a(this);
            declaredField.setAccessible(true);
            declaredField.set(this.weatherLottieView, aVar);
            com.jingdong.app.mall.bundle.jdweather.c.a.c(TAG, "on replacing the lottie exception listener");
        } catch (Exception e2) {
            com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
        }
    }

    private void initLottieWeatherView(Context context) {
        removeAllViews();
        this.weatherLottieView = new LottieAnimationView(context);
        this.weatherLottieView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        View view = this.weatherLottieView;
        if (view instanceof LottieAnimationView) {
            ((LottieAnimationView) view).addLottieOnCompositionLoadedListener(this);
            ((LottieAnimationView) this.weatherLottieView).setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        addView(this.weatherLottieView);
    }

    private void initView() {
        setVisibility(8);
    }

    private void playLottieAnimation(String str, int i2) {
        try {
            if (this.weatherLottieView == null || TextUtils.isEmpty(str)) {
                return;
            }
            if (getVisibility() == 8) {
                setVisibility(0);
            }
            ((LottieAnimationView) this.weatherLottieView).setAnimationFromUrl(str);
            ((LottieAnimationView) this.weatherLottieView).setRepeatCount(i2);
            View view = this.weatherLottieView;
            if ((view instanceof LottieAnimationView) && !((LottieAnimationView) view).isAnimating()) {
                ((LottieAnimationView) this.weatherLottieView).playAnimation();
            }
            this.weatherLottieView.addOnAttachStateChangeListener(this);
        } catch (Exception e2) {
            setVisibility(8);
            com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
        }
    }

    public void initAndShowLottieAnimation(Context context, JDWeatherAnimConfig jDWeatherAnimConfig) {
        if (context == null) {
            setVisibility(8);
        } else if (jDWeatherAnimConfig == null) {
            setVisibility(8);
        } else if (TextUtils.isEmpty(jDWeatherAnimConfig.lottieJson)) {
            setVisibility(8);
        } else if (Build.VERSION.SDK_INT < 16) {
            setVisibility(8);
        } else {
            showLottieAnimation(context, jDWeatherAnimConfig.lottieJson, jDWeatherAnimConfig.animationRepeatCount);
        }
    }

    @Override // com.airbnb.lottie.LottieOnCompositionLoadedListener
    public void onCompositionLoaded(LottieComposition lottieComposition) {
        StringBuilder sb = new StringBuilder();
        sb.append("onCompositionLoaded:");
        sb.append(lottieComposition != null ? lottieComposition.toString() : DYConstants.DY_NULL_STR);
        com.jingdong.app.mall.bundle.jdweather.c.a.a(TAG, sb.toString());
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        try {
            if (this.weatherLottieView != null && getVisibility() == 0) {
                View view2 = this.weatherLottieView;
                if ((view2 instanceof LottieAnimationView) && !((LottieAnimationView) view2).isAnimating()) {
                    ((LottieAnimationView) this.weatherLottieView).playAnimation();
                }
                this.weatherLottieView.removeOnAttachStateChangeListener(this);
                this.weatherLottieView.addOnAttachStateChangeListener(this);
                com.jingdong.app.mall.bundle.jdweather.c.a.a(TAG, "onViewAttachedToWindow");
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        try {
            View view2 = this.weatherLottieView;
            if (view2 instanceof LottieAnimationView) {
                ((LottieAnimationView) view2).cancelAnimation();
                this.weatherLottieView.clearAnimation();
            }
            View view3 = this.weatherLottieView;
            if (view3 != null) {
                view3.removeOnAttachStateChangeListener(this);
            }
            com.jingdong.app.mall.bundle.jdweather.c.a.a(TAG, "onViewDetachedFromWindow");
        } catch (Exception e2) {
            com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
        }
    }

    protected void showLottieAnimation(Context context, String str, int i2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (this.weatherLottieView == null) {
                initLottieWeatherView(context);
                hookExceptionListener();
            }
            playLottieAnimation(str, i2);
            return;
        }
        setVisibility(8);
    }

    public JDWeatherView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public JDWeatherView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView();
    }
}
