package com.jingdong.app.mall.home.r.a;

import android.animation.Animator;
import com.jingdong.app.mall.home.r.a.c;

/* loaded from: classes4.dex */
public interface a {
    void addAnimatorListener(Animator.AnimatorListener animatorListener);

    int getFloorPos();

    String getModelId();

    int getPriority();

    c.e getType();

    boolean isDictator();

    boolean isInDisplayArea(int i2, int i3);

    boolean isMatchOtherStartCondition();

    void pause(int i2);

    void startPlay();

    void stopPlay();
}
