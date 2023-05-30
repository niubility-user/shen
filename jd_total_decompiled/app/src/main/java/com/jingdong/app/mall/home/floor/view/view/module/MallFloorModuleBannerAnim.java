package com.jingdong.app.mall.home.floor.view.view.module;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.ctrl.j;
import com.jingdong.app.mall.home.floor.ctrl.o;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.f.a.b;
import com.jingdong.app.mall.home.r.f.a.q;

/* loaded from: classes4.dex */
public class MallFloorModuleBannerAnim extends MallFloorModuleCommon implements j {
    private static final int ITEM_LEFT = 0;
    private static final int ITEM_MIDDLE = 1;
    private static final int ITEM_RIGHT = 2;
    private String currentText;
    private int index;
    private AnimatorSet mAnimatorSet;
    private boolean mHasImg2;
    private String mId;
    private String mInterest1;
    private String mInterest2;
    private boolean mIsReverseAnimation;
    private View mSkuImg1;
    private View mSkuImg2;
    private View mSkuWhiteBg;
    private TextView mTvInterest;
    private GradientDrawable mWhiteDrawable;

    public MallFloorModuleBannerAnim(Context context, BaseMallFloor<?> baseMallFloor) {
        super(context, baseMallFloor);
    }

    private void addInterestText(f fVar, int i2) {
        this.mInterest1 = fVar.d0();
        String e0 = fVar.e0();
        this.mInterest2 = e0;
        this.currentText = null;
        if (this.mInterest1 == null && e0 == null) {
            return;
        }
        int i3 = m.p(fVar.f0(), -1, true)[0];
        TextView textView = new TextView(getContext());
        this.mTvInterest = textView;
        textView.setGravity(17);
        this.mTvInterest.setText(this.mInterest1);
        this.mTvInterest.setTextColor(i3);
        this.mTvInterest.getPaint().setFakeBoldText(true);
        this.mTvInterest.setTextSize(0, d.d(20));
        this.mTvInterest.setMaxLines(1);
        this.mTvInterest.setEllipsize(TextUtils.TruncateAt.END);
        TextView textView2 = this.mTvInterest;
        Resources resources = getContext().getResources();
        int i4 = R.dimen.home_title_topbottom_padding;
        textView2.setPadding(0, (int) resources.getDimension(i4), 0, (int) getContext().getResources().getDimension(i4));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(d.d(160), d.d(28));
        layoutParams.addRule(12);
        if (i2 == 0) {
            layoutParams.bottomMargin = d.d(21);
            layoutParams.rightMargin = d.d(10);
            layoutParams.addRule(11);
        } else if (i2 == 1) {
            layoutParams.bottomMargin = d.d(21);
            layoutParams.addRule(14);
        } else if (i2 == 2) {
            layoutParams.bottomMargin = d.d(21);
            layoutParams.leftMargin = d.d(10);
            layoutParams.addRule(9);
        }
        addView(this.mTvInterest, layoutParams);
    }

    public void onSkuAnimationEnd() {
        o.g().h();
        this.mAnimatorSet = null;
    }

    public void restoreImageState(View view, int i2, float f2) {
        if (view != null) {
            view.setVisibility(i2);
            view.setScaleX(f2);
            view.setScaleY(f2);
            view.setAlpha(f2);
        }
    }

    private void setBgParams(View view) {
        RelativeLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
        if (this.mWhiteDrawable == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            this.mWhiteDrawable = gradientDrawable;
            gradientDrawable.setColor(-1);
        }
        this.mWhiteDrawable.setCornerRadius(d.d(4));
        if (this.mSkuWhiteBg == null) {
            View view2 = new View(getContext());
            this.mSkuWhiteBg = view2;
            view2.setBackgroundDrawable(this.mWhiteDrawable);
        }
        ViewGroup.LayoutParams layoutParams4 = this.mSkuWhiteBg.getLayoutParams();
        int d = d.d(8);
        int i2 = d >> 1;
        if (layoutParams4 instanceof RelativeLayout.LayoutParams) {
            layoutParams = (RelativeLayout.LayoutParams) layoutParams4;
        } else {
            layoutParams = new RelativeLayout.LayoutParams(layoutParams3.width + d, layoutParams3.height + d);
        }
        layoutParams.width = layoutParams3.width + d;
        layoutParams.height = layoutParams3.height + d;
        int i3 = layoutParams3.leftMargin;
        if (i3 > 0) {
            layoutParams.leftMargin = i3 - i2;
            layoutParams.addRule(9);
        }
        int i4 = layoutParams3.topMargin;
        if (i4 > 0) {
            layoutParams.topMargin = i4 - i2;
            layoutParams.addRule(10);
        }
        int i5 = layoutParams3.rightMargin;
        if (i5 > 0) {
            layoutParams.rightMargin = i5 - i2;
            layoutParams.addRule(11);
        }
        int i6 = layoutParams3.bottomMargin;
        if (i6 > 0) {
            layoutParams.bottomMargin = i6 - i2;
            layoutParams.addRule(12);
        }
        this.mSkuWhiteBg.setLayoutParams(layoutParams);
        m.b(this, this.mSkuWhiteBg, 0);
    }

    private void startSkuAnimation(final View view, final View view2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, "ScaleX", 1.0f, 0.3f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "ScaleY", 1.0f, 0.3f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "ScaleX", 0.3f, 1.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "ScaleY", 0.3f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleBannerAnim.1
            {
                MallFloorModuleBannerAnim.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (MallFloorModuleBannerAnim.this.mInterest2 == null || floatValue <= 0.5f) {
                    return;
                }
                String str = view == MallFloorModuleBannerAnim.this.mSkuImg2 ? MallFloorModuleBannerAnim.this.mInterest2 : MallFloorModuleBannerAnim.this.mInterest1;
                if (str.equals(MallFloorModuleBannerAnim.this.currentText)) {
                    return;
                }
                MallFloorModuleBannerAnim.this.mTvInterest.setText(str);
                MallFloorModuleBannerAnim.this.currentText = str;
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6);
        this.mAnimatorSet.setDuration(1600L);
        this.mAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleBannerAnim.2
            {
                MallFloorModuleBannerAnim.this = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view2.setVisibility(8);
                if (view.getVisibility() != 0) {
                    MallFloorModuleBannerAnim.this.restoreImageState(view, 0, 1.0f);
                }
                MallFloorModuleBannerAnim.this.onSkuAnimationEnd();
            }
        });
        this.mAnimatorSet.start();
    }

    public void addItemTitleAndSubTitle(f fVar, int i2) {
        addInterestText(fVar, i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public int addSeparationItemImgs(p.a aVar, f fVar, int i2, int i3, int i4, int i5) {
        this.mIsReverseAnimation = false;
        removeAllViews();
        this.index = i2;
        String[] y = fVar.y(aVar.Q);
        stopAnimation();
        this.mHasImg2 = y != null && y.length > 1;
        if (this.mPresenter instanceof q) {
            this.mId = fVar.s();
        }
        o.g().b(this);
        int addSeparationItemImgs = super.addSeparationItemImgs(aVar, fVar, i2, i3, i4, i5);
        if (this.mHasImg2) {
            restoreImageState(this.mSkuImg2, 0, this.mIsReverseAnimation ? 1.0f : 0.0f);
            restoreImageState(this.mSkuImg1, 0, this.mIsReverseAnimation ? 0.0f : 1.0f);
        } else {
            restoreImageState(this.mSkuImg2, 8, 0.0f);
            restoreImageState(this.mSkuImg1, 0, 1.0f);
        }
        return addSeparationItemImgs;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean canPlayAnimation() {
        return this.mHasImg2 && hasSkuAnimation() && isVisibleInScreen() && !isAnimationDisplay();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean displayAnimation() {
        if (canPlayAnimation()) {
            this.mSkuImg1.setVisibility(0);
            this.mSkuImg2.setVisibility(0);
            if (!this.mIsReverseAnimation) {
                startSkuAnimation(this.mSkuImg2, this.mSkuImg1);
            } else {
                startSkuAnimation(this.mSkuImg1, this.mSkuImg2);
            }
            this.mIsReverseAnimation = !this.mIsReverseAnimation;
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public int[][] getAlignRule(p.a aVar, boolean z, int i2) {
        return super.getAlignRule(aVar, true, i2);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public String getFloorId() {
        return this.mPresenter.i();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    protected int getImagesAndGapsWidth(p.a aVar, int i2) {
        return aVar.K;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public int getSeparationImgMarginLeft(int i2, p.a aVar, int i3) {
        return super.getSeparationImgMarginLeft(0, aVar, i3);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public String getSkuAnimationId() {
        return this.mId;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean hasSkuAnimation() {
        b bVar = this.mPresenter;
        if (bVar instanceof q) {
            return this.mHasImg2 && ((q) bVar).W(this.index);
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean isAnimationDisplay() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        return animatorSet != null && animatorSet.isRunning();
    }

    public boolean isVisibleInScreen() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof BaseMallFloor) || ((BaseMallFloor) parent).isFloorRecycle()) {
            return false;
        }
        return m.I(this, a.f8560i, a.f8562k, false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public void onAddSeparationImg(int i2, SimpleDraweeView simpleDraweeView) {
        if (i2 == 0) {
            this.mSkuImg1 = simpleDraweeView;
            setBgParams(simpleDraweeView);
        } else if (i2 == 1) {
            this.mSkuImg2 = simpleDraweeView;
        }
    }

    public boolean stopAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return false;
        }
        this.mAnimatorSet.end();
        return true;
    }
}
