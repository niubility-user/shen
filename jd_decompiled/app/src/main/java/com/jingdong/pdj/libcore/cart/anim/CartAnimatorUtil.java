package com.jingdong.pdj.libcore.cart.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/anim/CartAnimatorUtil;", "", "Landroid/view/View;", "view", "", "form", RemoteMessageConst.TO, "", "translationAnim", "(Landroid/view/View;Ljava/lang/Float;Ljava/lang/Float;)V", "", "ANIMATION_TIME", "J", "Landroid/animation/Animator;", "animator", "Landroid/animation/Animator;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class CartAnimatorUtil {
    private static Animator animator;
    public static final CartAnimatorUtil INSTANCE = new CartAnimatorUtil();
    private static long ANIMATION_TIME = 400;

    private CartAnimatorUtil() {
    }

    public final void translationAnim(@Nullable final View view, @Nullable Float form, @Nullable final Float to) {
        Animator animator2;
        if (view == null || form == null || to == null) {
            return;
        }
        Animator animator3 = animator;
        if (animator3 != null && animator3.isRunning() && (animator2 = animator) != null) {
            animator2.cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", form.floatValue(), to.floatValue());
        animator = ofFloat;
        if (ofFloat != null) {
            ofFloat.setDuration(ANIMATION_TIME / 2);
        }
        Animator animator4 = animator;
        if (animator4 != null) {
            animator4.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.pdj.libcore.cart.anim.CartAnimatorUtil$translationAnim$1
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(@NotNull Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(@NotNull Animator animation) {
                    if (to.floatValue() > 0.0f) {
                        view.setVisibility(8);
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationRepeat(@NotNull Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(@NotNull Animator animation) {
                    view.setVisibility(0);
                }
            });
        }
        Animator animator5 = animator;
        if (animator5 != null) {
            animator5.start();
        }
    }
}
