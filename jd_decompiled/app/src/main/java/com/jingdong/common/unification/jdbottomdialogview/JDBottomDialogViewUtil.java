package com.jingdong.common.unification.jdbottomdialogview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class JDBottomDialogViewUtil {
    public static long getDuration(float f2, float f3, View view) {
        return Math.abs(f3 - f2) * (380.0f / DpiUtil.getHeight(view.getContext()));
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean listIsAtTop(AbsListView absListView) {
        return absListView.getChildCount() == 0 || absListView.getChildAt(0).getTop() == absListView.getPaddingTop();
    }

    public static void startTransAnim(View view, float f2, float f3, Animator.AnimatorListener animatorListener) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, "translationY", f2, f3).setDuration(getDuration(f2, f3, view));
        if (animatorListener != null) {
            duration.addListener(animatorListener);
        }
        duration.start();
    }
}
