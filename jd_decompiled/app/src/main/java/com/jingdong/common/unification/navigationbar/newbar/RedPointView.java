package com.jingdong.common.unification.navigationbar.newbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.common.unification.navigationbar.RadioStateDrawable;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class RedPointView extends ImageView {
    private Context context;
    private RadioStateDrawable defaultDrawable;
    private boolean isIndex;
    private StateController stateController;
    private TabShowNew tabShowNew;

    public RedPointView(Context context) {
        super(context);
        this.isIndex = false;
        this.context = context;
        this.tabShowNew = new TabShowNew(this);
        this.stateController = new StateController(this);
    }

    public void startRotationAnimation() {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        RotateAnimation rotateAnimation = new RotateAnimation(-2.0f, 8.0f, 0, radioStateDrawable.drawCenterX, 0, radioStateDrawable.drawCenterY);
        RadioStateDrawable radioStateDrawable2 = this.defaultDrawable;
        final RotateAnimation rotateAnimation2 = new RotateAnimation(8.0f, -8.0f, 0, radioStateDrawable2.drawCenterX, 0, radioStateDrawable2.drawCenterY);
        rotateAnimation.setDuration(200L);
        rotateAnimation2.setDuration(200L);
        rotateAnimation.setStartOffset(NavigationConstants.ANIMATION_TIME_ICON_SCALE_DELAY);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.RedPointView.3
            {
                RedPointView.this = this;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (RedPointView.this.getStateController() != null) {
                    RedPointView.this.getStateController().setNavigationParam(null);
                }
                RotateAnimation rotateAnimation3 = rotateAnimation2;
                if (rotateAnimation3 != null) {
                    RedPointView.this.startAnimation(rotateAnimation3);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        startAnimation(rotateAnimation);
    }

    private void startScaleAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "scaleX", 0.5f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "scaleY", 0.5f, 1.0f);
        animatorSet.setDuration(NavigationConstants.ANIMATION_TIME_ICON_SCALE);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.RedPointView.1
            {
                RedPointView.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
            }
        });
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.RedPointView.2
            {
                RedPointView.this = this;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                RedPointView.this.startRotationAnimation();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (OKLog.D) {
                    OKLog.i("TEST", "  addListener onAnimationStart VISIBLE : ");
                }
                RedPointView redPointView = RedPointView.this;
                redPointView.setImageDrawable(redPointView.defaultDrawable);
            }
        });
        animatorSet.start();
    }

    public void drawLabelEnable(boolean z) {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        if (radioStateDrawable != null) {
            radioStateDrawable.drawLabel = z;
        }
    }

    public void drawNumAble(boolean z) {
        this.defaultDrawable.drawNum = z;
    }

    public StateController getStateController() {
        return this.stateController;
    }

    public TabShowNew getTabShowNew() {
        return this.tabShowNew;
    }

    public void refreshNum(int i2) {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        radioStateDrawable.drawNum = true;
        radioStateDrawable.drawLabel = false;
        this.stateController.setNum(Integer.valueOf(i2));
    }

    public void setClick() {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        if (radioStateDrawable != null) {
            setImageDrawable(radioStateDrawable);
        }
    }

    public void setDefault() {
        if (OKLog.D && this.stateController != null) {
            OKLog.d("RedPointView", "setDefault()-navigationParam=" + this.stateController.getNavigationParam());
        }
        if (this.defaultDrawable != null) {
            StateController stateController = this.stateController;
            if (stateController != null && stateController.getNavigationParam() != null && (this.stateController.getNavigationParam().dynamicType == 2 || this.stateController.getNavigationParam().dynamicType == 3)) {
                startScaleAnimation();
            } else {
                setImageDrawable(this.defaultDrawable);
            }
        }
    }

    public void setNavigationParam(NavigationParam navigationParam) {
        StateController stateController = this.stateController;
        if (stateController != null) {
            stateController.setNavigationParam(navigationParam);
        }
    }

    public void setState(int i2, boolean z, boolean z2) {
        this.defaultDrawable = new RadioStateDrawable(this.context, true, z);
        StateController stateController = this.stateController;
        if (stateController != null) {
            stateController.setNavigationId(i2);
        }
        this.defaultDrawable.setAngleSwitch(z2);
        this.defaultDrawable.setStateController(this.stateController);
        this.defaultDrawable.setTabShowNew(this.tabShowNew);
    }

    public void showRedPoint(boolean z) {
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        radioStateDrawable.drawNum = false;
        radioStateDrawable.drawLabel = false;
        this.tabShowNew.setIsShowRedPoint(Boolean.valueOf(z));
    }

    public RedPointView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isIndex = false;
        this.context = context;
        this.tabShowNew = new TabShowNew(this);
        this.stateController = new StateController(this);
    }

    @RequiresApi(api = 21)
    public RedPointView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isIndex = false;
        this.context = context;
        this.tabShowNew = new TabShowNew(this);
        this.stateController = new StateController(this);
    }
}
