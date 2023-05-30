package com.jingdong.common.unification.navigationbar.newbar;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.AnimationEndListener;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationParam;
import com.jingdong.common.unification.navigationbar.RadioStateDrawable;
import com.jingdong.common.unification.navigationbar.utils.NavigationUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class NaviIconNoLottieView extends AppCompatImageView implements INaviIcon {
    private static final String TAG = "NavigationIconView";
    private RadioStateDrawable clickDrawable;
    private Context context;
    private RadioStateDrawable defaultDrawable;
    public boolean isDefaultIcon;
    private boolean isIndex;
    private boolean isNewIcon;
    private int navigationId;
    private ObjectAnimator objectAnimator;
    private RadioStateDrawable updateDrawable;

    public NaviIconNoLottieView(Context context) {
        super(context);
        this.isIndex = false;
        this.isNewIcon = false;
        this.isDefaultIcon = true;
        this.context = context;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public int getLottieType() {
        return 0;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isDefaultIcon() {
        return this.isDefaultIcon;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isIndex() {
        return this.isIndex;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean isNewIconState() {
        return this.isNewIcon;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void isShowAnim(boolean z) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setBubbleIcon(final Bitmap bitmap, final String str, String str2, final int i2, final NavigationParam navigationParam, final AnimationEndListener animationEndListener) {
        int i3;
        if (bitmap != null) {
            try {
                this.isNewIcon = true;
                if ((navigationParam == null ? 0 : navigationParam.bucketType) != 1) {
                    if (navigationParam != null && ((i3 = navigationParam.dynamicType) == 1 || i3 == 3)) {
                        this.objectAnimator = NavigationUtils.startRotationAnimation(this, new AnimationEndListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NaviIconNoLottieView.1
                            @Override // com.jingdong.common.unification.navigationbar.AnimationEndListener
                            public void onAnimationEnd() {
                                AnimationEndListener animationEndListener2 = animationEndListener;
                                if (animationEndListener2 != null) {
                                    animationEndListener2.onAnimationEnd();
                                }
                            }
                        }, new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.unification.navigationbar.newbar.NaviIconNoLottieView.2
                            boolean isSet = false;

                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (OKLog.D) {
                                    OKLog.d("TEST", "  rotationY onAnimationUpdate getAnimatedValue : " + valueAnimator.getAnimatedValue());
                                }
                                if (!(valueAnimator.getAnimatedValue() instanceof Float) || ((Float) valueAnimator.getAnimatedValue()).floatValue() < -90.0f || this.isSet) {
                                    return;
                                }
                                this.isSet = true;
                                NaviIconNoLottieView.this.setImageDrawable(new RadioStateDrawable(NaviIconNoLottieView.this.context, bitmap, str, false, true, i2, navigationParam));
                            }
                        });
                    } else {
                        setImageDrawable(new RadioStateDrawable(this.context, bitmap, str, false, true, i2, navigationParam));
                        if (navigationParam != null && navigationParam.dynamicType == 2 && animationEndListener != null) {
                            animationEndListener.onAnimationEnd();
                        }
                    }
                }
                NavigationBase.getInstance().buttonIconMarketExp(this.context, str2);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, "setBubbleIcon=" + e2.toString());
                }
            }
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setClick(boolean z) {
        if (Log.D) {
            Log.d("navigation-n-click", "  " + this.navigationId);
        }
        if (this.clickDrawable != null) {
            if (Log.D) {
                Log.d(TAG, "clickDrawable");
            }
            this.isNewIcon = false;
            setImageDrawable(this.clickDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setDefault(boolean z) {
        if (Log.D) {
            Log.d("navigation-n-default", "  " + this.navigationId);
        }
        RadioStateDrawable radioStateDrawable = this.defaultDrawable;
        if (radioStateDrawable != null) {
            this.isNewIcon = false;
            setImageDrawable(radioStateDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setIndex(boolean z) {
        this.isIndex = z;
        if (z) {
            setBackgroundDrawable(this.clickDrawable);
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setIsDefaultIcon(boolean z) {
        this.isDefaultIcon = z;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setNavigationId(int i2) {
        this.navigationId = i2;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setNavigationInfo(NavigationInfo navigationInfo) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setResourceItems(ResourceItems resourceItems) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setState(String str, String str2, String str3, boolean z, boolean z2) {
        this.isDefaultIcon = z;
        if (z) {
            setState(str, Integer.parseInt(str2), Integer.parseInt(str3), "");
            return;
        }
        this.defaultDrawable = new RadioStateDrawable(this.context, str2, str, false, z2);
        this.clickDrawable = new RadioStateDrawable(this.context, str3, str, true, z2);
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public boolean setUpdateState(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str2);
        if (Log.D) {
            Log.d(TAG, "setUpdateState-bitmap=" + decodeFile);
        }
        if (decodeFile == null) {
            return false;
        }
        RadioStateDrawable radioStateDrawable = new RadioStateDrawable(this.context, decodeFile, str, false);
        this.updateDrawable = radioStateDrawable;
        if (radioStateDrawable != null) {
            setImageDrawable(radioStateDrawable);
            return true;
        }
        return true;
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showBubbleLottie(String str, Animator.AnimatorListener animatorListener, String str2, int i2) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showEffect(int i2) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void showMarketingEffect(Animator.AnimatorListener animatorListener) {
    }

    @Override // com.jingdong.common.unification.navigationbar.newbar.INaviIcon
    public void setState(String str, int i2, int i3, String str2) {
        this.isDefaultIcon = true;
        this.defaultDrawable = new RadioStateDrawable(this.context, i2, str);
        this.clickDrawable = new RadioStateDrawable(this.context, i3, str);
    }

    public NaviIconNoLottieView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isIndex = false;
        this.isNewIcon = false;
        this.isDefaultIcon = true;
        this.context = context;
    }

    public NaviIconNoLottieView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isIndex = false;
        this.isNewIcon = false;
        this.isDefaultIcon = true;
        this.context = context;
    }
}
