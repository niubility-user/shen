package com.jingdong.common.XView2.layer.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.utils.XView2Utils;

/* loaded from: classes5.dex */
public class LottieLayer extends BaseLayer {
    private LottieAnimationView mAnimationView;

    public LottieLayer(Activity activity, Object obj, XView2 xView2) {
        super(activity, obj, xView2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        super.closeLayer(str, i2);
        LottieAnimationView lottieAnimationView = this.mAnimationView;
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public View createLayer(XView2Container xView2Container, Object obj, final ILayerCallBack iLayerCallBack) {
        if (this.mContext == null || this.layersEntity == null) {
            return null;
        }
        this.mCallBack = iLayerCallBack;
        this.mLayerState = BaseLayer.LAYERESTATE.CREATE;
        LottieAnimationView lottieAnimationView = new LottieAnimationView(this.mContext);
        this.mAnimationView = lottieAnimationView;
        XViewConfigEntity.RenderDataEntity renderDataEntity = this.layersEntity.renderData;
        lottieAnimationView.setAnimationFromUrl(renderDataEntity != null ? renderDataEntity.url : "");
        this.mAnimationView.loop(XView2Utils.isConvertBool(this.layersEntity.renderData.loop));
        this.mAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.XView2.layer.lottie.LottieLayer.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
            }
        });
        this.mAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.XView2.layer.lottie.LottieLayer.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                XViewConfigEntity.LayersEntity layersEntity;
                ILayerCallBack iLayerCallBack2 = iLayerCallBack;
                if (iLayerCallBack2 == null || (layersEntity = LottieLayer.this.layersEntity) == null) {
                    return;
                }
                iLayerCallBack2.onLayerClosed(layersEntity.layerId, 5);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                XViewConfigEntity.LayersEntity layersEntity;
                ILayerCallBack iLayerCallBack2 = iLayerCallBack;
                if (iLayerCallBack2 == null || (layersEntity = LottieLayer.this.layersEntity) == null) {
                    return;
                }
                iLayerCallBack2.onLayerDisplayed(layersEntity.layerId);
            }
        });
        if ("1".equals(this.layersEntity.renderData.contentMode)) {
            this.mAnimationView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if ("2".equals(this.layersEntity.renderData.contentMode)) {
            this.mAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            this.mAnimationView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        this.mAnimationView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return this.mAnimationView;
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        super.destroyLayer();
        LottieAnimationView lottieAnimationView = this.mAnimationView;
        if (lottieAnimationView != null) {
            lottieAnimationView.destroyDrawingCache();
            this.mAnimationView = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        super.onPause();
        LottieAnimationView lottieAnimationView = this.mAnimationView;
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
            this.mHasResume.set(false);
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        super.onResume();
        if (this.mAnimationView == null || this.mHasResume.get()) {
            return;
        }
        this.mAnimationView.playAnimation();
        this.mHasResume.set(true);
    }
}
