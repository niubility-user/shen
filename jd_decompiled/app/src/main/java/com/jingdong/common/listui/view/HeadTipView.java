package com.jingdong.common.listui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.Interpolator.AccelerateDecelerateInterpolator;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes5.dex */
public class HeadTipView {
    private View bgView;
    private TextView headTipView;
    private int height;
    private volatile boolean isShow;
    private Runnable mRunnable;
    private RelativeLayout rlContainer;
    private final HeadTipStyle style;
    private ViewStub viewStub;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.listui.view.HeadTipView$5  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$listui$view$HeadTipStyle;

        static {
            int[] iArr = new int[HeadTipStyle.values().length];
            $SwitchMap$com$jingdong$common$listui$view$HeadTipStyle = iArr;
            try {
                iArr[HeadTipStyle.RED_WHITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$listui$view$HeadTipStyle[HeadTipStyle.NO_BG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public HeadTipView(ViewStub viewStub) {
        this(viewStub, HeadTipStyle.DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateClose(final View view) {
        if (view == null) {
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(this.height, 0);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.listui.view.HeadTipView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (HeadTipView.this.isShow) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                view.setLayoutParams(layoutParams);
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.listui.view.HeadTipView.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (HeadTipView.this.isShow) {
                    return;
                }
                view.setVisibility(8);
            }
        });
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(180L).start();
    }

    private void animateOPen(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(200L);
        animationSet.addAnimation(alphaAnimation);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2f, 1.0f, 1.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(200L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        animationSet.setFillBefore(false);
        view.startAnimation(animationSet);
    }

    private void viewStub() {
        if (this.viewStub == null) {
            return;
        }
        int i2 = AnonymousClass5.$SwitchMap$com$jingdong$common$listui$view$HeadTipStyle[this.style.ordinal()];
        if (i2 == 1) {
            this.viewStub.setLayoutResource(R.layout.faxian_head_tip);
        } else if (i2 != 2) {
            this.viewStub.setLayoutResource(R.layout.listui_head_tip);
        } else {
            this.viewStub.setLayoutResource(R.layout.listui_nobg_head_tip);
        }
        this.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() { // from class: com.jingdong.common.listui.view.HeadTipView.1
            @Override // android.view.ViewStub.OnInflateListener
            public void onInflate(ViewStub viewStub, View view) {
                HeadTipView.this.rlContainer = (RelativeLayout) view;
                HeadTipView.this.headTipView = (TextView) view.findViewById(R.id.desc);
                HeadTipView.this.bgView = view.findViewById(R.id.bg);
            }
        });
        this.viewStub.inflate();
        this.height = DPIUtil.dip2px(30.0f);
    }

    public void hideHeadViewTip() {
        if (this.isShow) {
            this.isShow = false;
            this.rlContainer.removeCallbacks(this.mRunnable);
            animateClose(this.rlContainer);
        }
    }

    public void showHeadViewTip(String str) {
        showHeadViewTip(str, 0);
    }

    public HeadTipView(ViewStub viewStub, HeadTipStyle headTipStyle) {
        this.viewStub = viewStub;
        this.style = headTipStyle;
    }

    public void showHeadViewTip(String str, int i2) {
        if (this.headTipView == null || this.rlContainer == null) {
            viewStub();
        }
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            this.mRunnable = new Runnable() { // from class: com.jingdong.common.listui.view.HeadTipView.2
                @Override // java.lang.Runnable
                public void run() {
                    HeadTipView.this.isShow = false;
                    HeadTipView headTipView = HeadTipView.this;
                    headTipView.animateClose(headTipView.rlContainer);
                }
            };
        } else {
            this.rlContainer.removeCallbacks(runnable);
        }
        this.isShow = true;
        if (i2 == 4097 || i2 == 4098 || i2 == 4099) {
            FontsUtil.changeTextFont(this.headTipView, i2);
        }
        this.rlContainer.getLayoutParams().height = this.height;
        this.rlContainer.setVisibility(0);
        this.headTipView.setText(str);
        animateOPen(this.bgView);
        this.rlContainer.postDelayed(this.mRunnable, 2000L);
    }
}
