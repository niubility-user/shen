package com.facebook.react.animation;

import android.view.View;
import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class Animation {
    @Nullable
    private View mAnimatedView;
    private final int mAnimationID;
    @Nullable
    private AnimationListener mAnimationListener;
    private volatile boolean mCancelled = false;
    private volatile boolean mIsFinished = false;
    private final AnimationPropertyUpdater mPropertyUpdater;

    public Animation(int i2, AnimationPropertyUpdater animationPropertyUpdater) {
        this.mAnimationID = i2;
        this.mPropertyUpdater = animationPropertyUpdater;
    }

    public final void cancel() {
        if (this.mIsFinished || this.mCancelled) {
            return;
        }
        this.mCancelled = true;
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void finish() {
        Assertions.assertCondition(!this.mIsFinished, "Animation must not already be finished!");
        this.mIsFinished = true;
        if (this.mCancelled) {
            return;
        }
        View view = this.mAnimatedView;
        if (view != null) {
            this.mPropertyUpdater.onFinish(view);
        }
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onFinished();
        }
    }

    public int getAnimationID() {
        return this.mAnimationID;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean onUpdate(float f2) {
        Assertions.assertCondition(!this.mIsFinished, "Animation must not already be finished!");
        if (!this.mCancelled) {
            this.mPropertyUpdater.onUpdate((View) Assertions.assertNotNull(this.mAnimatedView), f2);
        }
        return !this.mCancelled;
    }

    public abstract void run();

    public void setAnimationListener(AnimationListener animationListener) {
        this.mAnimationListener = animationListener;
    }

    public final void start(View view) {
        this.mAnimatedView = view;
        this.mPropertyUpdater.prepare(view);
        run();
    }
}
