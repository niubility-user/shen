package com.jingdong.common.kepler;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class DragView extends LinearLayout {
    private static final String TAG = "DragView";
    private DragViewCallBack callBack;
    private boolean isMove;
    private AnimatorSet mAnimatorSet;
    private ValueAnimator mAnimatorX;
    private ValueAnimator mAnimatorY;
    private View.OnClickListener mClickListener;
    private float mInitX;
    private float mInitY;
    private ImageView mKeplerFlowBackIv;
    private SimpleDraweeView mKeplerFlowBgIv;
    private SimpleDraweeView mKeplerFlowLogoIv;
    private TextView mKeplerFlowText;
    private RelativeLayout parentView;
    private ViewGroup rootView;

    public DragView(Context context) {
        super(context);
        this.isMove = false;
        init(context);
    }

    private void endMove() {
        float f2;
        int left = this.rootView.getLeft();
        int top = this.rootView.getTop();
        int bottom = this.rootView.getBottom();
        if (getY() + getHeight() > bottom) {
            f2 = bottom - getHeight();
        } else {
            f2 = top;
            if (getY() >= f2) {
                f2 = getY();
            }
        }
        float f3 = left;
        ValueAnimator valueAnimator = this.mAnimatorX;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mAnimatorX.setFloatValues(getX(), f3);
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(getX(), f3);
            this.mAnimatorX = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.kepler.DragView.3
                {
                    DragView.this = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    DragView.this.setX(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
        }
        ValueAnimator valueAnimator2 = this.mAnimatorY;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
            this.mAnimatorY.setFloatValues(getY(), f2);
        } else {
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(getY(), f2);
            this.mAnimatorY = ofFloat2;
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.kepler.DragView.4
                {
                    DragView.this = this;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    DragView.this.setY(((Float) valueAnimator3.getAnimatedValue()).floatValue());
                }
            });
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        } else {
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mAnimatorSet = animatorSet2;
            animatorSet2.play(this.mAnimatorX).with(this.mAnimatorY);
            this.mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        this.mAnimatorSet.start();
    }

    private void init(Context context) {
        View inflate = ImageUtil.inflate(R.layout.kepler_flow_back_layout, this);
        if (inflate == null) {
            return;
        }
        this.parentView = (RelativeLayout) inflate.findViewById(R.id.parentLayout);
        this.mKeplerFlowText = (TextView) inflate.findViewById(R.id.keplerFlowText);
        this.mKeplerFlowLogoIv = (SimpleDraweeView) inflate.findViewById(R.id.logoIv);
        this.mKeplerFlowBgIv = (SimpleDraweeView) inflate.findViewById(R.id.bgIv);
        this.mKeplerFlowBackIv = (ImageView) inflate.findViewById(R.id.backIv);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mInitX = motionEvent.getX();
            this.mInitY = motionEvent.getY();
            this.isMove = false;
        } else if (action != 1) {
            if (action == 2) {
                if (!this.isMove) {
                    float f2 = x - this.mInitX;
                    float f3 = y - this.mInitY;
                    if ((f2 * f2) + (f3 * f3) >= 64.0f) {
                        this.isMove = true;
                        this.mInitX = x;
                        this.mInitY = y;
                    }
                }
                if (this.isMove) {
                    setX(getX() + (x - this.mInitX));
                    setY(getY() + (y - this.mInitY));
                }
            }
        } else if (this.isMove) {
            endMove();
            this.isMove = false;
        } else {
            View.OnClickListener onClickListener = this.mClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(this);
            }
        }
        return true;
    }

    public void setDragViewCallBack(DragViewCallBack dragViewCallBack) {
        this.callBack = dragViewCallBack;
    }

    public void setFlowText(String str) {
        if (this.mKeplerFlowText == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mKeplerFlowText.setText(str);
    }

    public void setInfo(Context context, KeplerJumpInfo keplerJumpInfo) {
        if (!TextUtils.isEmpty(keplerJumpInfo.appName)) {
            setFlowText(keplerJumpInfo.appName);
        } else {
            setFlowText(context.getResources().getText(R.string.kepler_flow_text_default).toString());
        }
        if (!TextUtils.isEmpty(keplerJumpInfo.backPic)) {
            this.mKeplerFlowBgIv.setVisibility(0);
            setFlowText(context.getResources().getText(R.string.kepler_flow_text_default).toString());
            JDImageUtils.displayImage(keplerJumpInfo.backPic, this.mKeplerFlowBgIv, null, false, new JDImageLoadingListener() { // from class: com.jingdong.common.kepler.DragView.1
                {
                    DragView.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                    DragView.this.mKeplerFlowBgIv.setVisibility(8);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    DragView.this.parentView.setBackgroundDrawable(null);
                    DragView.this.mKeplerFlowText.setVisibility(8);
                    DragView.this.mKeplerFlowBackIv.setVisibility(8);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    DragView.this.mKeplerFlowBgIv.setVisibility(8);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            }, null);
        } else if (TextUtils.isEmpty(keplerJumpInfo.picLogo)) {
        } else {
            this.mKeplerFlowLogoIv.setVisibility(0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mKeplerFlowText.getLayoutParams();
            layoutParams.rightMargin = DPIUtil.dip2px(5.0f);
            this.mKeplerFlowText.setLayoutParams(layoutParams);
            JDImageUtils.displayImage(keplerJumpInfo.picLogo, this.mKeplerFlowLogoIv, new JDDisplayImageOptions().showImageOnLoading(R.drawable.kepler_flow_logo_default), true, new JDImageLoadingListener() { // from class: com.jingdong.common.kepler.DragView.2
                {
                    DragView.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                    DragView.this.mKeplerFlowLogoIv.setVisibility(8);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    DragView.this.mKeplerFlowLogoIv.setVisibility(8);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) DragView.this.mKeplerFlowText.getLayoutParams();
                    layoutParams2.rightMargin = DPIUtil.dip2px(10.0f);
                    DragView.this.mKeplerFlowText.setLayoutParams(layoutParams2);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            }, null);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public void setRootView(ViewGroup viewGroup) {
        this.rootView = viewGroup;
    }

    public DragView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isMove = false;
        init(context);
    }

    public DragView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isMove = false;
        init(context);
    }
}
