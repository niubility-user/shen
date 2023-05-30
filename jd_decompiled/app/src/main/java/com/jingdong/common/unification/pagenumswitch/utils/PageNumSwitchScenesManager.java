package com.jingdong.common.unification.pagenumswitch.utils;

import android.graphics.Canvas;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.jingdong.common.unification.pagenumswitch.animation.Rotate3dAnimation;
import com.jingdong.common.unification.scenes.base.BaseScenes;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes6.dex */
public class PageNumSwitchScenesManager {
    private Set<BaseScenes> baseScenesSet = new HashSet();
    private View hostView;
    private BaseScenes rootBaseScenes;

    /* loaded from: classes6.dex */
    public interface OnScenesAnimListener {
        void onAnimEnd();

        void onAnimStart();
    }

    private PageNumSwitchScenesManager(View view) {
        this.hostView = view;
    }

    public static Animation createBottomToNormalAnim(BaseScenes baseScenes) {
        return createBottomToNormalAnim(baseScenes, 300L);
    }

    public static Animation createNormalToBottomAnim(BaseScenes baseScenes) {
        return createNormalToBottomAnim(baseScenes, 300L);
    }

    public static Animation createNormalToUpAnim(BaseScenes baseScenes) {
        return createNormalToUpAnim(baseScenes, 300L);
    }

    public static PageNumSwitchScenesManager createScenesManager(View view) {
        return new PageNumSwitchScenesManager(view);
    }

    public static Animation createUpToNormalAnim(BaseScenes baseScenes) {
        return createUpToNormalAnim(baseScenes, 300L);
    }

    private boolean isNeedUpdate() {
        return isAnimRunning() && this.hostView != null;
    }

    private void notifyHostViewNeedUpdate() {
        View view = this.hostView;
        if (view == null) {
            return;
        }
        view.invalidate();
    }

    private void registerAnim(final BaseScenes baseScenes, Animation animation, final OnScenesAnimListener onScenesAnimListener) {
        if (animation == null) {
            return;
        }
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesManager.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
                OnScenesAnimListener onScenesAnimListener2 = onScenesAnimListener;
                if (onScenesAnimListener2 != null) {
                    onScenesAnimListener2.onAnimEnd();
                }
                PageNumSwitchScenesManager.this.baseScenesSet.remove(baseScenes);
                baseScenes.clearAnim();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation2) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation2) {
                OnScenesAnimListener onScenesAnimListener2 = onScenesAnimListener;
                if (onScenesAnimListener2 != null) {
                    onScenesAnimListener2.onAnimStart();
                }
                PageNumSwitchScenesManager.this.baseScenesSet.add(baseScenes);
            }
        });
    }

    public void draw(Canvas canvas) {
        BaseScenes baseScenes = this.rootBaseScenes;
        if (baseScenes != null) {
            baseScenes.draw(canvas, null);
        }
        if (isNeedUpdate()) {
            notifyHostViewNeedUpdate();
        }
    }

    public long getAnimRemainningTime() {
        Set<BaseScenes> set = this.baseScenesSet;
        if (set != null && !set.isEmpty()) {
            for (BaseScenes baseScenes : this.baseScenesSet) {
                if (baseScenes.isAnimRunning()) {
                    return baseScenes.getRemainingAnimTime();
                }
            }
        }
        return 0L;
    }

    public boolean isAnimRunning() {
        Set<BaseScenes> set = this.baseScenesSet;
        if (set != null && !set.isEmpty()) {
            Iterator<BaseScenes> it = this.baseScenesSet.iterator();
            while (it.hasNext()) {
                if (it.next().isAnimRunning()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void release() {
        this.hostView = null;
        this.baseScenesSet.clear();
    }

    public void setRootBaseScenes(BaseScenes baseScenes) {
        this.rootBaseScenes = baseScenes;
    }

    public void startAnim(BaseScenes baseScenes, Animation animation, BaseScenes baseScenes2, Animation animation2, OnScenesAnimListener onScenesAnimListener) {
        baseScenes.stopAnim();
        baseScenes.stopChildAnim();
        baseScenes2.stopAnim();
        baseScenes2.stopChildAnim();
        registerAnim(baseScenes, animation, onScenesAnimListener);
        registerAnim(baseScenes2, animation2, null);
        baseScenes.startAnim(animation);
        baseScenes2.startAnim(animation2);
        notifyHostViewNeedUpdate();
    }

    public static Animation createBottomToNormalAnim(BaseScenes baseScenes, long j2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, baseScenes.getWidth() / 2, (baseScenes.getHeight() / 5) * 4, 0.0f, Rotate3dAnimation.ROTATE_X_AXIS, true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (baseScenes.getHeight() / 5) * 4, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(j2);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotate3dAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static Animation createNormalToBottomAnim(BaseScenes baseScenes, long j2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0.0f, -90.0f, baseScenes.getWidth() / 2, (baseScenes.getHeight() / 5) * 4, 0.0f, Rotate3dAnimation.ROTATE_X_AXIS, true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (baseScenes.getHeight() / 5) * 4);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(j2);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotate3dAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static Animation createNormalToUpAnim(BaseScenes baseScenes, long j2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0.0f, 90.0f, baseScenes.getWidth() / 2, (baseScenes.getHeight() / 5) * 4, 0.0f, Rotate3dAnimation.ROTATE_X_AXIS, true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, ((-baseScenes.getHeight()) / 5) * 4);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(j2);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotate3dAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static Animation createUpToNormalAnim(BaseScenes baseScenes, long j2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(90.0f, 0.0f, baseScenes.getWidth() / 2, (baseScenes.getHeight() / 5) * 4, 0.0f, Rotate3dAnimation.ROTATE_X_AXIS, true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, ((-baseScenes.getHeight()) / 5) * 4, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(j2);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotate3dAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }
}
