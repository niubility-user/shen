package com.jingdong.pdj.libcore.cart.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.utils.BezierEvaluator;
import com.jingdong.sdk.utils.DPIUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0015J!\u0010\u0006\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\t\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\t\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u000e\u0010\nJ\u0017\u0010\u000e\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0018R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0017\u00a8\u0006\u001e"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator;", "", "Landroid/view/View;", "animView", "Landroid/view/ViewGroup;", "animParent", "setAnimView", "(Landroid/view/View;Landroid/view/ViewGroup;)Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator;", "v", "setBeginPoint", "(Landroid/view/View;)Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator;", "Landroid/graphics/PointF;", "pointF", "(Landroid/graphics/PointF;)Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator;", "setEndPoint", "", "duration", "setDuration", "(I)Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator;", "", "start", "()V", "beginPoint", "Landroid/graphics/PointF;", "I", "Landroid/view/ViewGroup;", "Landroid/view/View;", "endPoint", "<init>", "Companion", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class BezierAnimator {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ViewGroup animParent;
    private View animView;
    private PointF beginPoint;
    private int duration = 500;
    private PointF endPoint;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/anim/BezierAnimator$Companion;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/view/View;", "defaultDot", "(Landroid/content/Context;)Landroid/view/View;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final View defaultDot(@Nullable Context context) {
            View view = new View(context);
            view.setLayoutParams(new FrameLayout.LayoutParams(DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f)));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
            gradientDrawable.setCornerRadius(DPIUtil.dip2px(12.0f));
            view.setBackground(gradientDrawable);
            return view;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public final BezierAnimator setAnimView(@Nullable View animView, @Nullable ViewGroup animParent) {
        if (animView != null && animView.getParent() == null && animParent != null) {
            animParent.addView(animView);
        }
        this.animView = animView;
        this.animParent = animParent;
        return this;
    }

    @NotNull
    public final BezierAnimator setBeginPoint(@Nullable View v) {
        if (v != null) {
            try {
                v.getLocationOnScreen(new int[2]);
                this.beginPoint = new PointF(r1[0], r1[1] - (v.getHeight() / 2));
            } catch (Exception unused) {
            }
        }
        return this;
    }

    @NotNull
    public final BezierAnimator setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @NotNull
    public final BezierAnimator setEndPoint(@Nullable View v) {
        if (v != null) {
            try {
                v.getLocationOnScreen(new int[2]);
                this.endPoint = new PointF(r1[0] + (v.getWidth() / 2), r1[1]);
            } catch (Exception unused) {
            }
        }
        return this;
    }

    public final void start() {
        try {
            final View view = this.animView;
            if (view == null || this.animParent == null) {
                return;
            }
            if (view == null) {
                Intrinsics.throwNpe();
            }
            final ViewGroup viewGroup = this.animParent;
            if (viewGroup == null) {
                Intrinsics.throwNpe();
            }
            PointF pointF = this.beginPoint;
            if (pointF == null) {
                Intrinsics.throwNpe();
            }
            float f2 = pointF.x;
            PointF pointF2 = this.endPoint;
            if (pointF2 == null) {
                Intrinsics.throwNpe();
            }
            float f3 = pointF2.x;
            PointF pointF3 = this.beginPoint;
            if (pointF3 == null) {
                Intrinsics.throwNpe();
            }
            float f4 = f2 + ((f3 - pointF3.x) / 3.0f);
            PointF pointF4 = this.beginPoint;
            if (pointF4 == null) {
                Intrinsics.throwNpe();
            }
            ValueAnimator animator = ValueAnimator.ofObject(new BezierEvaluator(new PointF(f4, pointF4.y - DPIUtil.dip2px(150.0f))), this.beginPoint, this.endPoint);
            Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
            animator.setDuration(this.duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.pdj.libcore.cart.anim.BezierAnimator$start$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator animation) {
                    Intrinsics.checkExpressionValueIsNotNull(animation, "animation");
                    Object animatedValue = animation.getAnimatedValue();
                    if (animatedValue != null) {
                        PointF pointF5 = (PointF) animatedValue;
                        view.setX(pointF5.x);
                        view.setY(pointF5.y);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.PointF");
                }
            });
            animator.setInterpolator(new LinearInterpolator());
            animator.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.pdj.libcore.cart.anim.BezierAnimator$start$2
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(@NotNull Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(@NotNull Animator animation) {
                    viewGroup.removeView(view);
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationRepeat(@NotNull Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(@NotNull Animator animation) {
                    view.setVisibility(0);
                }
            });
            animator.start();
        } catch (Exception unused) {
        }
    }

    @NotNull
    public final BezierAnimator setBeginPoint(@Nullable PointF pointF) {
        this.beginPoint = pointF;
        return this;
    }

    @NotNull
    public final BezierAnimator setEndPoint(@Nullable PointF pointF) {
        this.endPoint = pointF;
        return this;
    }
}
