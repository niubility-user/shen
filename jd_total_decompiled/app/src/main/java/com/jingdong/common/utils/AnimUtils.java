package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class AnimUtils {

    /* loaded from: classes6.dex */
    public interface OnAnimFinishListener {
        void onFinish();
    }

    private static View addViewToAnimLayout(View view, int[] iArr, int i2) {
        int i3 = iArr[0];
        int i4 = iArr[1];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        float f2 = i2 / 2;
        layoutParams.leftMargin = i3 - DpiUtil.dip2px(view.getContext(), f2);
        layoutParams.topMargin = i4 - DpiUtil.dip2px(view.getContext(), f2);
        float f3 = i2;
        layoutParams.width = DpiUtil.dip2px(view.getContext(), f3);
        layoutParams.height = DpiUtil.dip2px(view.getContext(), f3);
        view.setLayoutParams(layoutParams);
        return view;
    }

    private static ViewGroup createAnimLayout(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setId(Integer.MAX_VALUE);
        linearLayout.setBackgroundResource(17170445);
        ((ViewGroup) ((Activity) context).getWindow().getDecorView()).addView(linearLayout);
        return linearLayout;
    }

    public static void startAddCarAnim(final Context context, final View view, int[] iArr, int[] iArr2, final View view2, final OnAnimFinishListener onAnimFinishListener, int i2) {
        createAnimLayout(context).addView(view);
        View addViewToAnimLayout = addViewToAnimLayout(view, iArr, 50);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, iArr2[0] - iArr[0], 0.0f, iArr2[1] - iArr[1]);
        translateAnimation.setDuration(200L);
        translateAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200L);
        alphaAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.2f, 0.0f, 1.2f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(200L);
        scaleAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.2f, 0.8f, 1.2f, 0.8f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setStartOffset(200L);
        scaleAnimation2.setDuration(100L);
        scaleAnimation2.setFillAfter(true);
        ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.8f, 1.1f, 0.8f, 1.1f, 1, 0.5f, 1, 0.5f);
        scaleAnimation3.setStartOffset(300L);
        scaleAnimation3.setDuration(100L);
        scaleAnimation3.setFillAfter(false);
        int[] iArr3 = new int[2];
        view2.getLocationInWindow(iArr3);
        int i3 = iArr3[0] - iArr2[0];
        int i4 = iArr3[1] - iArr2[1];
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, i3, 0.0f, 0.0f);
        translateAnimation2.setInterpolator(new LinearInterpolator());
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setStartOffset(600L);
        translateAnimation2.setDuration(500L);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(0.0f, 0.0f, 0.0f, i4);
        translateAnimation3.setInterpolator(new AccelerateInterpolator());
        translateAnimation3.setFillAfter(true);
        translateAnimation3.setStartOffset(600L);
        translateAnimation3.setDuration(500L);
        ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f, 1, 0.5f, 1, 0.5f);
        scaleAnimation4.setStartOffset(600L);
        scaleAnimation4.setDuration(500L);
        translateAnimation3.setFillAfter(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.2f);
        alphaAnimation2.setStartOffset(600L);
        alphaAnimation2.setDuration(500L);
        translateAnimation3.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation2);
        animationSet.addAnimation(scaleAnimation3);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation4);
        animationSet.addAnimation(alphaAnimation2);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(translateAnimation3);
        animationSet.addAnimation(translateAnimation2);
        addViewToAnimLayout.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.utils.AnimUtils.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
                view2.startAnimation(AnimationUtils.loadAnimation(context, R.anim.add_shoppingcar_anim));
                OnAnimFinishListener onAnimFinishListener2 = onAnimFinishListener;
                if (onAnimFinishListener2 != null) {
                    onAnimFinishListener2.onFinish();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
    }
}
