package com.jingdong.common.recommend.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.RequiresApi;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.utils.ABTestUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class RecommendMaterialFeedBackAniView extends RelativeLayout {
    private ArrayList<Animator> animators;
    private ImageView guideView;

    public RecommendMaterialFeedBackAniView(Context context) {
        super(context);
        this.animators = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goneAni() {
        if (getParent() != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
            this.animators.add(ofFloat);
            if (!(this.guideView instanceof LottieAnimationView)) {
                ofFloat.setStartDelay(4000L);
            }
            ofFloat.setDuration(500L);
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendMaterialFeedBackAniView.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (RecommendMaterialFeedBackAniView.this.getParent() instanceof ViewGroup) {
                        ((ViewGroup) RecommendMaterialFeedBackAniView.this.getParent()).removeView(RecommendMaterialFeedBackAniView.this);
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            ofFloat.start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ArrayList<Animator> arrayList = this.animators;
        if (arrayList != null) {
            Iterator<Animator> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void showGuide() {
        View view = new View(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        view.setBackgroundResource(R.drawable.recommend_feedback_ani);
        addView(view);
        if (this.guideView == null) {
            if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable()) {
                LottieAnimationView lottieAnimationView = new LottieAnimationView(getContext());
                this.guideView = lottieAnimationView;
                if (lottieAnimationView != null) {
                    lottieAnimationView.setAnimation("recommend_material_feedback_ani.json");
                    ((LottieAnimationView) this.guideView).playAnimation();
                    ((LottieAnimationView) this.guideView).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendMaterialFeedBackAniView.1
                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            RecommendMaterialFeedBackAniView.this.goneAni();
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                        }
                    });
                }
            } else {
                ImageView imageView = new ImageView(getContext());
                this.guideView = imageView;
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                RecommendViewUtil.setImageResource(this.guideView, R.drawable.recommend_materiaal_feddback_ani);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(13, -1);
            addView(this.guideView, layoutParams);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendMaterialFeedBackAniView.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (RecommendMaterialFeedBackAniView.this.guideView instanceof LottieAnimationView) {
                        return;
                    }
                    RecommendMaterialFeedBackAniView.this.goneAni();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            ofFloat.setDuration(500L);
            ofFloat.start();
            this.animators.add(ofFloat);
        }
    }

    public RecommendMaterialFeedBackAniView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.animators = new ArrayList<>();
    }

    public RecommendMaterialFeedBackAniView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.animators = new ArrayList<>();
    }

    @RequiresApi(api = 21)
    public RecommendMaterialFeedBackAniView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.animators = new ArrayList<>();
    }
}
