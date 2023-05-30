package com.jingdong.common.unification.navigationbar.newbar;

import android.animation.Animator;
import android.graphics.Bitmap;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.AnimationEndListener;
import com.jingdong.common.unification.navigationbar.NavigationParam;

/* loaded from: classes6.dex */
public interface INaviIcon {
    int getLottieType();

    boolean isDefaultIcon();

    boolean isIndex();

    boolean isNewIconState();

    void isShowAnim(boolean z);

    void setBubbleIcon(Bitmap bitmap, String str, String str2, int i2, NavigationParam navigationParam, AnimationEndListener animationEndListener);

    void setClick(boolean z);

    void setDefault(boolean z);

    void setIndex(boolean z);

    void setIsDefaultIcon(boolean z);

    void setNavigationId(int i2);

    void setNavigationInfo(NavigationInfo navigationInfo);

    void setResourceItems(ResourceItems resourceItems);

    void setState(String str, int i2, int i3, String str2);

    void setState(String str, String str2, String str3, boolean z, boolean z2);

    boolean setUpdateState(String str, String str2, String str3);

    void showBubbleLottie(String str, Animator.AnimatorListener animatorListener, String str2, int i2);

    void showEffect(int i2);

    void showMarketingEffect(Animator.AnimatorListener animatorListener);
}
