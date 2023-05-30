package com.jingdong.common.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class ProductDetailAddCartAnimUtils {

    /* loaded from: classes6.dex */
    public interface OnAnimFinishListener {
        void onFinish();
    }

    private static ViewGroup createAnimLayout(Activity activity) {
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setId(Integer.MAX_VALUE);
        linearLayout.setBackgroundResource(17170445);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(linearLayout);
        return linearLayout;
    }

    private static AnimatorSet getInitAnimationSet(View view) {
        if (view == null) {
            return null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(250L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.2f);
        ofFloat2.setDuration(200L);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.2f);
        ofFloat3.setDuration(200L);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleX", 0.2f, 0.25f);
        ofFloat4.setDuration(50L);
        ofFloat4.setStartDelay(200L);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "scaleY", 0.2f, 0.25f);
        ofFloat5.setDuration(50L);
        ofFloat5.setStartDelay(200L);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, "scaleX", 0.25f, 0.05f);
        ofFloat6.setDuration(400L);
        ofFloat6.setStartDelay(250L);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view, "scaleY", 0.25f, 0.05f);
        ofFloat7.setDuration(400L);
        ofFloat7.setStartDelay(250L);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.1f);
        ofFloat8.setDuration(250L);
        ofFloat8.setStartDelay(400L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4).with(ofFloat5).with(ofFloat6).with(ofFloat7).with(ofFloat8);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private static PointF getPointF(int[] iArr) {
        PointF pointF = new PointF();
        pointF.x = iArr[0];
        pointF.y = iArr[1];
        return pointF;
    }

    private static AnimatorSet getRunAnimatorSet(final View view, View view2, int i2) {
        if (view == null || view2 == null) {
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        view2.getLocationInWindow(new int[2]);
        int i3 = (i2 / 20) * 19;
        float f2 = i2;
        ValueAnimator ofObject = ValueAnimator.ofObject(new BezierEvaluator(getPointF(new int[]{(int) (view.getX() - f2), (int) (view.getY() + f2)})), new PointF(view.getX(), view.getY()), new PointF((r2[0] - i3) + (view2.getMeasuredWidth() / 2), r2[1] - i3));
        ofObject.setInterpolator(new AccelerateInterpolator());
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.utils.ProductDetailAddCartAnimUtils.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                view.setX(pointF.x);
                view.setY(pointF.y);
            }
        });
        animatorSet.play(ofObject);
        animatorSet.setDuration(400L);
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private static View setAnimViewLayoutParams(View view, int[] iArr, int i2) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i2;
            layoutParams.leftMargin = iArr[0];
            layoutParams.topMargin = iArr[1];
            view.setLayoutParams(layoutParams);
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void shoppingCartAnim(View view, final OnAnimFinishListener onAnimFinishListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 30.0f, -30.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setInterpolator(new LinearInterpolator());
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        ofFloat.start();
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.utils.ProductDetailAddCartAnimUtils.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                OnAnimFinishListener onAnimFinishListener2 = OnAnimFinishListener.this;
                if (onAnimFinishListener2 != null) {
                    onAnimFinishListener2.onFinish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startAnim(final View view, int[] iArr, final View view2, final OnAnimFinishListener onAnimFinishListener) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet initAnimationSet = getInitAnimationSet(view);
        AnimatorSet runAnimatorSet = getRunAnimatorSet(view, view2, (DPIUtil.getWidth() - (iArr[0] * 2)) / 2);
        if (initAnimationSet == null || runAnimatorSet == null) {
            return;
        }
        animatorSet.play(initAnimationSet);
        animatorSet.play(runAnimatorSet).after(250L);
        animatorSet.setTarget(view);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.utils.ProductDetailAddCartAnimUtils.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                ProductDetailAddCartAnimUtils.shoppingCartAnim(view2, onAnimFinishListener);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
            }
        });
        animatorSet.start();
    }

    public static void startShopCartAnim(Activity activity, final int[] iArr, final View view, String str, final OnAnimFinishListener onAnimFinishListener) {
        if (activity == null || iArr == null || view == null) {
            return;
        }
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(activity);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.displayer(new JDRoundedBitmapDisplayer(R2.attr.internalMinHeight));
        JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions);
        createAnimLayout(activity).addView(simpleDraweeView);
        simpleDraweeView.setVisibility(4);
        final View animViewLayoutParams = setAnimViewLayoutParams(simpleDraweeView, iArr, DPIUtil.getWidth() - (iArr[0] * 2));
        animViewLayoutParams.post(new Runnable() { // from class: com.jingdong.common.utils.ProductDetailAddCartAnimUtils.1
            @Override // java.lang.Runnable
            public void run() {
                View view2;
                int[] iArr2;
                View view3 = animViewLayoutParams;
                if (view3 == null || (view2 = view) == null || (iArr2 = iArr) == null) {
                    return;
                }
                ProductDetailAddCartAnimUtils.startAnim(view3, iArr2, view2, onAnimFinishListener);
            }
        });
    }
}
