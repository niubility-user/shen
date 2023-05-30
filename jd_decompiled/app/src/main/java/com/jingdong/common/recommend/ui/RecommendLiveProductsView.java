package com.jingdong.common.recommend.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieAnimationView;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendLiveProduct;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class RecommendLiveProductsView extends RelativeLayout implements View.OnClickListener {
    public static final String TAG = RecommendLiveProductsView.class.getSimpleName();
    private ArrayList<Animator> animators;
    private View guideView;
    private boolean isAttachToWindow;
    private int mFrom;
    private RecommendLiveProductViewHolder productOneViewHolder;
    private RecommendLiveProductViewHolder productThreeViewHolder;
    private RecommendLiveProductViewHolder productTwoViewHolder;
    private View recomLiveMore;
    private View recomLiveProductClose;
    private View recomLiveProductLayout;
    private RecommendUtil.OnRecommendClickedListener recommendClickedListener;
    private View rootView;
    private RecommendVideo videoData;
    private ArrayList<RecommendLiveProductViewHolder> viewHolders;

    public RecommendLiveProductsView(@NonNull Context context) {
        super(context);
        this.viewHolders = new ArrayList<>();
        this.isAttachToWindow = false;
    }

    private void onProductItemClick(RecommendLiveProduct recommendLiveProduct) {
        if (recommendLiveProduct == null || this.recommendClickedListener == null) {
            return;
        }
        RecommendMtaUtils.liveProductMaskProductClick(getContext(), recommendLiveProduct.sourceValue, this.mFrom, "");
        this.recommendClickedListener.onRecommendJump(recommendLiveProduct.jumpUrl, recommendLiveProduct.isOpenApp);
    }

    private void showGuidCover() {
        setAlpha(0.0f);
        if (this.guideView == null) {
            if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable()) {
                LottieAnimationView lottieAnimationView = new LottieAnimationView(getContext());
                this.guideView = lottieAnimationView;
                if (lottieAnimationView != null) {
                    lottieAnimationView.setAnimation("recommend_live_guide_ani.json");
                    ((LottieAnimationView) this.guideView).playAnimation();
                    ((LottieAnimationView) this.guideView).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.1
                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            RecommendLiveProductsView.this.startTransLationAni();
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
                RecommendViewUtil.setImageResource((ImageView) this.guideView, R.drawable.recommend_live_guide);
            }
            this.guideView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.2
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                    OKLog.d(RecommendLiveProductsView.TAG, "onViewAttachToWindow---->guideView");
                    if (RecommendLiveProductsView.this.guideView != null) {
                        RecommendLiveProductsView.this.guideView.removeOnAttachStateChangeListener(this);
                    }
                    if (RecommendLiveProductsView.this.videoData.playGuidAni) {
                        RecommendLiveProductsView.this.videoData.playGuidAni = false;
                        if (RecommendLiveProductsView.this.guideView instanceof LottieAnimationView) {
                            ((LottieAnimationView) RecommendLiveProductsView.this.guideView).playAnimation();
                        }
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(RecommendLiveProductsView.this.rootView, "alpha", 0.0f, 1.0f);
                        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.2.1
                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationCancel(Animator animator) {
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                if (RecommendLiveProductsView.this.guideView instanceof LottieAnimationView) {
                                    return;
                                }
                                RecommendLiveProductsView.this.startTransLationAni();
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationRepeat(Animator animator) {
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationStart(Animator animator) {
                            }
                        });
                        ofFloat.setDuration(500L);
                        ofFloat.setStartDelay(500L);
                        ofFloat.start();
                        if (RecommendLiveProductsView.this.animators == null) {
                            RecommendLiveProductsView.this.animators = new ArrayList();
                        }
                        RecommendLiveProductsView.this.animators.add(ofFloat);
                    }
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                }
            });
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(13, -1);
            addView(this.guideView, 0, layoutParams);
        }
        RecommendViewUtil.visible(this.guideView);
        RecommendViewUtil.invisible(this.recomLiveProductClose);
        RecommendViewUtil.invisible(this.recomLiveProductLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGoneAni() {
        if (this.rootView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
            if (this.animators == null) {
                this.animators = new ArrayList<>();
            }
            this.animators.add(ofFloat);
            ofFloat.setDuration(500L);
            ofFloat.setStartDelay(1500L);
            ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.5
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (RecommendLiveProductsView.this.isAttachToWindow) {
                        RecommendViewUtil.gone(RecommendLiveProductsView.this);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void startTransLationAni() {
        if (!this.isAttachToWindow || this.recomLiveProductLayout == null || this.recomLiveProductClose == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.animators == null) {
            this.animators = new ArrayList<>();
        }
        this.animators.add(animatorSet);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.recomLiveProductLayout, "translationY", getHeight(), 0.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                RecommendViewUtil.visible(RecommendLiveProductsView.this.recomLiveProductLayout, RecommendLiveProductsView.this.recomLiveProductClose);
                if (!RecommendLiveProductsView.this.isAttachToWindow || RecommendLiveProductsView.this.guideView == null) {
                    return;
                }
                RecommendLiveProductsView.this.guideView.setTranslationY((-(RecommendLiveProductsView.this.getHeight() - ((Float) valueAnimator.getAnimatedValue()).floatValue())) / 1.5f);
            }
        });
        animatorSet.playTogether(ofFloat, ObjectAnimator.ofFloat(this.recomLiveProductLayout, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(this.recomLiveProductClose, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(this.guideView, "alpha", 1.0f, 0.0f));
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.recommend.ui.RecommendLiveProductsView.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (RecommendLiveProductsView.this.isAttachToWindow) {
                    RecommendViewUtil.gone(RecommendLiveProductsView.this.guideView);
                    RecommendLiveProductsView.this.startGoneAni();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        if (!(this.guideView instanceof LottieAnimationView)) {
            animatorSet.setStartDelay(500L);
        }
        animatorSet.setDuration(600L);
        animatorSet.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        OKLog.d(TAG, "onViewAttachToWindow---->view");
        this.isAttachToWindow = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.recom_live_product_close) {
            if (this.mFrom == 9) {
                JDMtaUtils.sendCommonData(getContext(), RecommendMtaUtils.Home_Mask_LiveProductClose, "", "", "JDHomeFragment", "", "", "", RecommendMtaUtils.Home_PageId);
            }
            RecommendViewUtil.gone(this);
        } else if (view.getId() == R.id.recom_live_product_one) {
            RecommendLiveProductViewHolder recommendLiveProductViewHolder = this.productOneViewHolder;
            if (recommendLiveProductViewHolder != null) {
                onProductItemClick(recommendLiveProductViewHolder.getProduct());
            }
        } else if (view.getId() == R.id.recom_live_product_two) {
            RecommendLiveProductViewHolder recommendLiveProductViewHolder2 = this.productTwoViewHolder;
            if (recommendLiveProductViewHolder2 != null) {
                onProductItemClick(recommendLiveProductViewHolder2.getProduct());
            }
        } else if (view.getId() == R.id.recom_live_product_three) {
            RecommendLiveProductViewHolder recommendLiveProductViewHolder3 = this.productThreeViewHolder;
            if (recommendLiveProductViewHolder3 != null) {
                onProductItemClick(recommendLiveProductViewHolder3.getProduct());
            }
        } else if (view.getId() != R.id.recom_live_more || this.recommendClickedListener == null || this.videoData == null) {
        } else {
            if (this.mFrom == 9) {
                JDMtaUtils.sendCommonData(getContext(), RecommendMtaUtils.Home_Mask_LiveProductMore, "", "", "JDHomeFragment", "", "", "", RecommendMtaUtils.Home_PageId);
            }
            RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.recommendClickedListener;
            RecommendVideo recommendVideo = this.videoData;
            onRecommendClickedListener.onRecommendJump(recommendVideo.channelJumpUrl, recommendVideo.isOpenApp);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachToWindow = false;
        if (this.videoData != null) {
            setVisibility(8);
            ArrayList<Animator> arrayList = this.animators;
            if (arrayList != null) {
                Iterator<Animator> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().cancel();
                }
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.rootView = this;
        View findViewById = findViewById(R.id.recom_live_product_close);
        this.recomLiveProductClose = findViewById;
        findViewById.setOnClickListener(this);
        this.recomLiveProductLayout = findViewById(R.id.recom_live_product_layout);
        RecommendLiveProductViewHolder recommendLiveProductViewHolder = new RecommendLiveProductViewHolder(findViewById(R.id.recom_live_product_one));
        this.productOneViewHolder = recommendLiveProductViewHolder;
        RecommendViewUtil.setOnClickListener(recommendLiveProductViewHolder.rootView, this);
        RecommendLiveProductViewHolder recommendLiveProductViewHolder2 = new RecommendLiveProductViewHolder(findViewById(R.id.recom_live_product_two));
        this.productTwoViewHolder = recommendLiveProductViewHolder2;
        RecommendViewUtil.setOnClickListener(recommendLiveProductViewHolder2.rootView, this);
        RecommendLiveProductViewHolder recommendLiveProductViewHolder3 = new RecommendLiveProductViewHolder(findViewById(R.id.recom_live_product_three));
        this.productThreeViewHolder = recommendLiveProductViewHolder3;
        RecommendViewUtil.setOnClickListener(recommendLiveProductViewHolder3.rootView, this);
        this.viewHolders.add(this.productOneViewHolder);
        this.viewHolders.add(this.productTwoViewHolder);
        this.viewHolders.add(this.productThreeViewHolder);
        View findViewById2 = findViewById(R.id.recom_live_more);
        this.recomLiveMore = findViewById2;
        findViewById2.setOnClickListener(this);
    }

    public void setOnRecommendClickListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        this.recommendClickedListener = onRecommendClickedListener;
    }

    public void setRecommendLiveProductData(RecommendVideo recommendVideo, int i2) {
        ArrayList<RecommendLiveProduct> arrayList;
        this.mFrom = i2;
        this.videoData = recommendVideo;
        if (recommendVideo != null && (arrayList = recommendVideo.liveSkus) != null && arrayList.size() >= 1) {
            float height = getParent() instanceof View ? ((View) getParent()).getHeight() : 0.0f;
            if (height <= 0.0f) {
                height = (RecommendViewUtil.getLineTwoItemWidth(getContext()) * 104) / 69.0f;
            }
            int size = this.viewHolders.size();
            if (DPIUtil.dip2px(241.0f) > height) {
                size = 2;
            }
            int min = Math.min(recommendVideo.liveSkus.size(), size);
            StringBuilder sb = null;
            for (int i3 = 0; i3 < min; i3++) {
                RecommendLiveProductViewHolder recommendLiveProductViewHolder = this.viewHolders.get(i3);
                RecommendLiveProduct recommendLiveProduct = recommendVideo.liveSkus.get(i3);
                if (recommendLiveProductViewHolder != null) {
                    if (recommendLiveProduct != null) {
                        recommendLiveProductViewHolder.onBindData(recommendVideo.liveStatus, recommendLiveProduct, i3);
                        if (sb == null) {
                            sb = new StringBuilder();
                            sb.append("[");
                        }
                        sb.append(recommendLiveProduct.exposureJsonSourceValue);
                        if (i3 != min - 1) {
                            sb.append(DYConstants.DY_REGEX_COMMA);
                        }
                        recommendLiveProductViewHolder.setVisible();
                    } else {
                        recommendLiveProductViewHolder.setGone();
                    }
                }
            }
            if (sb != null) {
                sb.append("]");
            }
            try {
                int size2 = this.viewHolders.size() - min;
                if (size2 > 0) {
                    for (int i4 = 0; i4 < size2; i4++) {
                        RecommendLiveProductViewHolder recommendLiveProductViewHolder2 = this.viewHolders.get(2 - i4);
                        if (recommendLiveProductViewHolder2 != null) {
                            recommendLiveProductViewHolder2.setGone();
                        }
                    }
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            setAlpha(1.0f);
            RecommendViewUtil.gone(this.guideView);
            RecommendViewUtil.visible(this.recomLiveProductLayout, this.recomLiveProductClose);
            View view = this.recomLiveProductLayout;
            if (view != null) {
                view.setTranslationY(0.0f);
            }
            if (recommendVideo.playGuidAni) {
                showGuidCover();
            }
            RecommendMtaUtils.liveProductMaskShow(getContext(), recommendVideo.sourceValue, i2, recommendVideo.extension_id);
            JDMtaUtils.sendExposureDataWithExt(getContext(), RecommendMtaUtils.Home_Mask_LiveProductExpo, "", RecommendMtaUtils.Home_PageId, "JDHomeFragment", "", sb == null ? "" : sb.toString(), "", "", "", null);
            return;
        }
        RecommendViewUtil.gone(this);
    }

    public RecommendLiveProductsView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.viewHolders = new ArrayList<>();
        this.isAttachToWindow = false;
    }

    public RecommendLiveProductsView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.viewHolders = new ArrayList<>();
        this.isAttachToWindow = false;
    }

    public RecommendLiveProductsView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.viewHolders = new ArrayList<>();
        this.isAttachToWindow = false;
    }
}
