package com.jingdong.app.mall.home.floor.view.view.module;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewParent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.ctrl.j;
import com.jingdong.app.mall.home.floor.ctrl.n;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.f.a.b;
import com.jingdong.app.mall.home.r.f.a.q;

/* loaded from: classes4.dex */
public class MallFloorModuleAnimalSku extends MallFloorModuleMixed implements j {
    private static Paint sBgPaint = new Paint(1);
    private AnimatorSet mAnimatorSet;
    private boolean mHasImg2;
    private String mId;
    private boolean mIsReverseAnimation;
    private View mSkuImg1;
    private View mSkuImg2;

    public MallFloorModuleAnimalSku(Context context, BaseMallFloor<?> baseMallFloor) {
        super(context, baseMallFloor);
        sBgPaint.setColor(-1);
    }

    private void drawSkuWhiteBg(Canvas canvas) {
        canvas.drawRect(d.d(18), d.d(12), getWidth() - d.d(18), getHeight() - d.d(56), sBgPaint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSkuAnimationEnd() {
        n.i().j();
        this.mAnimatorSet = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreImageState(View view, int i2, float f2) {
        if (view != null) {
            view.setVisibility(i2);
            view.setScaleX(f2);
            view.setScaleY(f2);
            view.setAlpha(f2);
        }
    }

    private void startSkuAnimation(final View view, final View view2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, "ScaleX", 1.0f, 0.3f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "ScaleY", 1.0f, 0.3f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "ScaleX", 0.3f, 1.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "ScaleY", 0.3f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6);
        this.mAnimatorSet.setDuration(1600L);
        this.mAnimatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleAnimalSku.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view2.setVisibility(8);
                if (view.getVisibility() != 0) {
                    MallFloorModuleAnimalSku.this.restoreImageState(view, 0, 1.0f);
                }
                MallFloorModuleAnimalSku.this.onSkuAnimationEnd();
            }
        });
        this.mAnimatorSet.start();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public int addSeparationItemImgs(p.a aVar, f fVar, int i2, int i3, int i4, int i5) {
        String[] y = fVar.y(aVar.Q);
        stopAnimation();
        this.mHasImg2 = y != null && y.length > 1;
        if (this.mPresenter instanceof q) {
            this.mId = fVar.s();
        }
        n.i().d(this);
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
        return this.mHasImg2 & hasSkuAnimation() & isVisibleInScreen();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        drawSkuWhiteBg(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean displayAnimation() {
        if (canPlayAnimation()) {
            if (!this.mIsReverseAnimation) {
                this.mSkuImg1.setVisibility(0);
                this.mSkuImg2.setVisibility(0);
                startSkuAnimation(this.mSkuImg2, this.mSkuImg1);
            } else {
                this.mSkuImg1.setVisibility(0);
                this.mSkuImg2.setVisibility(0);
                startSkuAnimation(this.mSkuImg1, this.mSkuImg2);
            }
            this.mIsReverseAnimation = !this.mIsReverseAnimation;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
        return getSeparationItemImgSize(aVar, 0).x;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
            return this.mHasImg2 && ((q) bVar).S();
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean isAnimationDisplay() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        return animatorSet != null && animatorSet.isRunning();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public boolean isSkuWithAnimation() {
        if (this.mHasImg2 && hasSkuAnimation()) {
            return true;
        }
        return super.isSkuWithAnimation();
    }

    public boolean isVisibleInScreen() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof BaseMallFloor)) {
            return false;
        }
        return ((BaseMallFloor) parent).getIsDisplayInScreen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon
    public void onAddSeparationImg(int i2, SimpleDraweeView simpleDraweeView) {
        super.onAddSeparationImg(i2, simpleDraweeView);
        if (i2 == 0) {
            this.mSkuImg1 = simpleDraweeView;
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
