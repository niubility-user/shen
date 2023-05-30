package com.jingdong.common.unification.uniutil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class AddToCartAnimUtils {
    public static final byte DEFAULT_STYLE = 0;
    public static final byte SELECT_STYLE = 1;

    /* loaded from: classes6.dex */
    public interface IAddToCartAnimListener {
        void onAddToCartAnimFinish();
    }

    private static Animator createAnim(View view, View view2, View view3, TextView textView) {
        Activity findActivityByView = findActivityByView(view);
        if (findActivityByView == null) {
            return null;
        }
        int dip2px = DPIUtil.dip2px(findActivityByView, 160.0f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr);
        view2.getLocationOnScreen(iArr2);
        int width = view2.getWidth();
        int height = view2.getHeight();
        int width2 = view.getWidth();
        int height2 = view.getHeight();
        int i2 = iArr2[0] - iArr[0];
        int i3 = width / 2;
        int i4 = (iArr2[1] - dip2px) - iArr[1];
        int i5 = iArr2[1] - iArr[1];
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, "alpha", 1.0f, 0.0f);
        long j2 = (long) R2.attr.behavior_hideable;
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, "translationX", 0.0f, i2 + i3);
        ofFloat2.setDuration(j2);
        ofFloat2.setInterpolator(new LinearInterpolator());
        float f2 = i4;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(textView, "translationY", 0.0f, f2);
        long j3 = 225;
        ofFloat3.setDuration(j3);
        ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(textView, "translationY", f2, i5);
        ofFloat4.setStartDelay(j3);
        ofFloat4.setDuration(j3);
        ofFloat4.setInterpolator(new AccelerateInterpolator());
        Animator createWidthAnim = createWidthAnim(textView, width2, height2, 0L, j3);
        Animator createWidthAnim2 = createWidthAnim(textView, height2, 0, j3, j3);
        Animator createHeightAnim = createHeightAnim(textView, height2, 0, j3, j3);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(textView, DYConstants.DY_TEXT_SIZE, 14.0f, 0.0f);
        ofFloat5.setDuration(j3);
        ofFloat5.setStartDelay(j3);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view2, "rotation", 0.0f, 30.0f, -30.0f, 0.0f);
        long j4 = 150;
        ofFloat6.setDuration(j4);
        ofFloat6.setStartDelay(j2);
        ofFloat6.setInterpolator(new LinearInterpolator());
        view2.setPivotX(i3);
        view2.setPivotY(height / 2);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view3, "scaleX", 1.0f, 0.6f, 1.0f);
        ofFloat7.setDuration(j4);
        ofFloat7.setStartDelay(j2);
        ofFloat7.setInterpolator(new LinearInterpolator());
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view3, "scaleY", 1.0f, 0.6f, 1.0f);
        ofFloat8.setDuration(j4);
        ofFloat8.setStartDelay(j2);
        ofFloat8.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createWidthAnim, ofFloat5, createWidthAnim2, createHeightAnim, ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat6, ofFloat7, ofFloat8);
        return animatorSet;
    }

    private static TextView createAnimView(View view, byte b) {
        Activity findActivityByView = findActivityByView(view);
        if (findActivityByView == null) {
            return null;
        }
        TextView textView = new TextView(findActivityByView);
        textView.setText("+1");
        textView.setGravity(17);
        textView.setTextColor(ContextCompat.getColor(findActivityByView, 17170443));
        textView.setTextSize(2, 14.0f);
        textView.setBackgroundResource(createAnimViewBackground(b));
        textView.setTypeface(FontsUtil.getTypeFace(findActivityByView, 4099));
        return textView;
    }

    private static int createAnimViewBackground(byte b) {
        if (b != 0) {
            if (b != 1) {
                return R.drawable.anim_add_to_cart_num;
            }
            return R.drawable.anim_add_to_cart_num_01;
        }
        return R.drawable.anim_add_to_cart_num;
    }

    private static FrameLayout.LayoutParams createAnimViewLayoutParams(View view) {
        if (view == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        return layoutParams;
    }

    private static Animator createHeightAnim(final View view, int i2, int i3, long j2, long j3) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        ofInt.setDuration(j3);
        ofInt.setStartDelay(j2);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.uniutil.AddToCartAnimUtils.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = intValue;
                view.setLayoutParams(layoutParams);
            }
        });
        return ofInt;
    }

    private static Animator createWidthAnim(final View view, int i2, int i3, long j2, long j3) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        ofInt.setDuration(j3);
        ofInt.setStartDelay(j2);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.uniutil.AddToCartAnimUtils.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = intValue;
                view.setLayoutParams(layoutParams);
            }
        });
        return ofInt;
    }

    private static Activity findActivityByView(View view) {
        if (view == null) {
            return null;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    private static ViewGroup findDecorViewByActivity(Activity activity) {
        if (activity == null) {
            return null;
        }
        View decorView = activity.getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            return (ViewGroup) decorView;
        }
        return null;
    }

    public static void startAddToCartAnim(View view, View view2, View view3, IAddToCartAnimListener iAddToCartAnimListener) {
        startAddToCartAnim(view, view2, view3, (byte) 0, iAddToCartAnimListener);
    }

    public static void startAddToCartAnim(View view, View view2, View view3, byte b, final IAddToCartAnimListener iAddToCartAnimListener) {
        final ViewGroup findDecorViewByActivity;
        if (view == null || view2 == null || view3 == null || view.getWidth() <= 0 || view.getHeight() <= 0 || (findDecorViewByActivity = findDecorViewByActivity(findActivityByView(view))) == null) {
            return;
        }
        final TextView createAnimView = createAnimView(view, b);
        findDecorViewByActivity.addView(createAnimView, createAnimViewLayoutParams(view));
        Animator createAnim = createAnim(view, view2, view3, createAnimView);
        if (createAnim == null) {
            return;
        }
        createAnim.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.unification.uniutil.AddToCartAnimUtils.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                IAddToCartAnimListener iAddToCartAnimListener2 = IAddToCartAnimListener.this;
                if (iAddToCartAnimListener2 != null) {
                    iAddToCartAnimListener2.onAddToCartAnimFinish();
                }
                findDecorViewByActivity.removeView(createAnimView);
            }
        });
        createAnim.start();
    }
}
