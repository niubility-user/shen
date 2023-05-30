package com.jd.viewkit.tool.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

/* loaded from: classes18.dex */
public class LinerObjectAnimator {
    private ObjectAnimator animator;
    private LinerObjectAnimatorListener animatorListener;
    private boolean isEnd = false;
    private int lineDistance;
    private int lineDistanceCut;
    public int lineDistanceState;
    private int pp;

    /* loaded from: classes18.dex */
    public interface LinerObjectAnimatorListener {
        void end(int i2);

        void updata(int i2);
    }

    public LinerObjectAnimator(long j2, int i2) {
        this.lineDistance = i2;
        this.lineDistanceCut = (int) Math.ceil(i2 / r2);
        ObjectAnimator duration = ObjectAnimator.ofInt(this, "pp", 0, (int) (j2 / 20)).setDuration(j2);
        this.animator = duration;
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.jd.viewkit.tool.animator.LinerObjectAnimator.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LinerObjectAnimator.this.end();
            }
        });
    }

    public void cancel() {
        this.isEnd = true;
        this.animator.cancel();
    }

    public void end() {
        if (!this.isEnd) {
            this.animatorListener.end(this.lineDistance - this.lineDistanceState);
        }
        this.lineDistanceState = 0;
        this.isEnd = true;
    }

    public LinerObjectAnimatorListener getAnimatorListener() {
        return this.animatorListener;
    }

    public int getPp() {
        return this.pp;
    }

    public void setAnimatorListener(LinerObjectAnimatorListener linerObjectAnimatorListener) {
        this.animatorListener = linerObjectAnimatorListener;
    }

    public void setPp(int i2) {
        this.pp = i2;
        if (this.animatorListener == null) {
            cancel();
        }
        int i3 = this.lineDistanceState;
        int i4 = this.lineDistanceCut;
        int i5 = i3 + i4;
        this.lineDistanceState = i5;
        int i6 = this.lineDistance;
        if (i5 < i6) {
            this.animatorListener.updata(i4);
        } else if (i5 >= i6) {
            this.animatorListener.end((i6 + i4) - i5);
            this.isEnd = true;
            this.lineDistanceState = 0;
            cancel();
        }
    }

    public void start() {
        this.lineDistanceState = 0;
        this.isEnd = false;
        this.animator.start();
    }
}
