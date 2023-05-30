package com.jd.aips.widget.spinkit.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.jd.aips.widget.spinkit.sprite.Sprite;

/* loaded from: classes12.dex */
public class AnimationUtils {
    public static boolean isRunning(Sprite... spriteArr) {
        for (Sprite sprite : spriteArr) {
            if (sprite.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStarted(ValueAnimator valueAnimator) {
        return valueAnimator != null && valueAnimator.isStarted();
    }

    public static void start(Animator animator) {
        if (animator == null || animator.isStarted()) {
            return;
        }
        animator.start();
    }

    public static void stop(Animator animator) {
        if (animator == null || animator.isRunning()) {
            return;
        }
        animator.end();
    }

    public static boolean isRunning(ValueAnimator valueAnimator) {
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public static void start(Sprite... spriteArr) {
        for (Sprite sprite : spriteArr) {
            sprite.start();
        }
    }

    public static void stop(Sprite... spriteArr) {
        for (Sprite sprite : spriteArr) {
            sprite.stop();
        }
    }
}
