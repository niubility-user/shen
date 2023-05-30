package tv.danmaku.ijk.media.ext.tarns;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* loaded from: classes11.dex */
public class JDTransController {
    private View animView;
    private long duration;
    private int targetHeight;
    private int targetWidth;
    private TimeInterpolator timeInterpolator;
    private JDTransParam transitionParam;
    private ViewPropertyAnimator viewAnimator;

    /* loaded from: classes11.dex */
    public static class Builder {
        private View animView;
        private long duration;
        private int targetHeight;
        private int targetWidth;
        private TimeInterpolator timeInterpolator;

        public JDTransController build() {
            return new JDTransController(this.animView, this.duration, this.targetWidth, this.targetHeight, this.timeInterpolator);
        }

        public Builder duration(long j2) {
            this.duration = j2;
            return this;
        }

        public Builder setInterpolator(TimeInterpolator timeInterpolator) {
            this.timeInterpolator = timeInterpolator;
            return this;
        }

        public Builder targetWH(int i2, int i3) {
            this.targetWidth = i2;
            this.targetHeight = i3;
            return this;
        }

        public Builder with(View view) {
            this.animView = view;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    private static class TransitionAnimation implements Animator.AnimatorListener {
        private WeakReference<JDTransCallback> weakCallback;

        public TransitionAnimation(JDTransCallback jDTransCallback) {
            this.weakCallback = new WeakReference<>(jDTransCallback);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            JDTransCallback jDTransCallback = this.weakCallback.get();
            if (jDTransCallback != null) {
                jDTransCallback.onTransitionStop();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionStart(boolean z, Animator.AnimatorListener animatorListener) {
        int i2;
        JDTransParam jDTransParam = this.transitionParam;
        int i3 = jDTransParam.bottom - jDTransParam.top;
        float f2 = jDTransParam.width / this.targetWidth;
        float f3 = jDTransParam.height / this.targetHeight;
        this.animView.setPivotX(0.0f);
        this.animView.setPivotY(0.0f);
        JDTransParam jDTransParam2 = this.transitionParam;
        int i4 = jDTransParam2.left;
        int i5 = jDTransParam2.bottom;
        if (i5 == this.targetHeight) {
            i2 = jDTransParam2.top;
        } else {
            int i6 = jDTransParam2.height;
            i2 = i3 < i6 ? i5 - i6 : jDTransParam2.top;
        }
        if (z) {
            this.animView.setTranslationX(i4);
            this.animView.setTranslationY(i2);
        }
        this.animView.setScaleX(z ? f2 : 1.0f);
        this.animView.setScaleY(z ? f3 : 1.0f);
        ViewPropertyAnimator animate = this.animView.animate();
        this.viewAnimator = animate;
        animate.setInterpolator(this.timeInterpolator);
        this.animView.setVisibility(0);
        ViewPropertyAnimator listener = this.viewAnimator.setDuration(this.duration).setListener(animatorListener);
        if (z) {
            f2 = 1.0f;
        }
        ViewPropertyAnimator scaleX = listener.scaleX(f2);
        if (z) {
            f3 = 1.0f;
        }
        scaleX.scaleY(f3).translationX(z ? 0.0f : i4).translationY(z ? 0.0f : i2).start();
    }

    public void transitionEnter(JDTransParam jDTransParam, final JDTransCallback jDTransCallback) {
        this.transitionParam = jDTransParam;
        this.animView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: tv.danmaku.ijk.media.ext.tarns.JDTransController.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    JDTransController.this.animView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    JDTransController.this.animView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                JDTransController jDTransController = JDTransController.this;
                jDTransController.targetWidth = jDTransController.animView.getMeasuredWidth();
                JDTransController jDTransController2 = JDTransController.this;
                jDTransController2.targetHeight = jDTransController2.animView.getMeasuredHeight();
                JDTransController.this.transitionStart(true, new TransitionAnimation(jDTransCallback));
            }
        });
    }

    public void transitionExit(JDTransCallback jDTransCallback) {
        transitionStart(false, new TransitionAnimation(jDTransCallback));
    }

    public void transitionRelease() {
        ViewPropertyAnimator viewPropertyAnimator = this.viewAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setListener(null);
            this.viewAnimator.cancel();
            this.viewAnimator = null;
        }
    }

    private JDTransController(View view, long j2, int i2, int i3, TimeInterpolator timeInterpolator) {
        this.animView = view;
        this.duration = j2;
        this.targetWidth = i2;
        this.targetHeight = i3;
        this.timeInterpolator = timeInterpolator;
    }
}
