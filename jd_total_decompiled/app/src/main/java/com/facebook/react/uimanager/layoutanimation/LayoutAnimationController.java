package com.facebook.react.uimanager.layoutanimation;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* loaded from: classes12.dex */
public class LayoutAnimationController {
    private static final boolean ENABLED = true;
    private boolean mShouldAnimateLayout;
    private final AbstractLayoutAnimation mLayoutCreateAnimation = new LayoutCreateAnimation();
    private final AbstractLayoutAnimation mLayoutUpdateAnimation = new LayoutUpdateAnimation();
    private final AbstractLayoutAnimation mLayoutDeleteAnimation = new LayoutDeleteAnimation();
    private final SparseArray<LayoutHandlingAnimation> mLayoutHandlers = new SparseArray<>(0);

    private void disableUserInteractions(View view) {
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                disableUserInteractions(viewGroup.getChildAt(i2));
            }
        }
    }

    public void applyLayoutUpdate(View view, int i2, int i3, int i4, int i5) {
        AbstractLayoutAnimation abstractLayoutAnimation;
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        LayoutHandlingAnimation layoutHandlingAnimation = this.mLayoutHandlers.get(id);
        if (layoutHandlingAnimation != null) {
            layoutHandlingAnimation.onLayoutUpdate(i2, i3, i4, i5);
            return;
        }
        if (view.getWidth() != 0 && view.getHeight() != 0) {
            abstractLayoutAnimation = this.mLayoutUpdateAnimation;
        } else {
            abstractLayoutAnimation = this.mLayoutCreateAnimation;
        }
        Animation createAnimation = abstractLayoutAnimation.createAnimation(view, i2, i3, i4, i5);
        if (createAnimation instanceof LayoutHandlingAnimation) {
            createAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.remove(id);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.put(id, (LayoutHandlingAnimation) animation);
                }
            });
        } else {
            view.layout(i2, i3, i4 + i2, i5 + i3);
        }
        if (createAnimation != null) {
            view.startAnimation(createAnimation);
        }
    }

    public void deleteView(View view, final LayoutAnimationListener layoutAnimationListener) {
        UiThreadUtil.assertOnUiThread();
        Animation createAnimation = this.mLayoutDeleteAnimation.createAnimation(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (createAnimation != null) {
            disableUserInteractions(view);
            createAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    layoutAnimationListener.onAnimationEnd();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            view.startAnimation(createAnimation);
            return;
        }
        layoutAnimationListener.onAnimationEnd();
    }

    public void initializeFromConfig(@Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            reset();
            return;
        }
        this.mShouldAnimateLayout = false;
        int i2 = readableMap.hasKey("duration") ? readableMap.getInt("duration") : 0;
        LayoutAnimationType layoutAnimationType = LayoutAnimationType.CREATE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType))) {
            this.mLayoutCreateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType)), i2);
            this.mShouldAnimateLayout = true;
        }
        LayoutAnimationType layoutAnimationType2 = LayoutAnimationType.UPDATE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType2))) {
            this.mLayoutUpdateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType2)), i2);
            this.mShouldAnimateLayout = true;
        }
        LayoutAnimationType layoutAnimationType3 = LayoutAnimationType.DELETE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType3))) {
            this.mLayoutDeleteAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType3)), i2);
            this.mShouldAnimateLayout = true;
        }
    }

    public void reset() {
        this.mLayoutCreateAnimation.reset();
        this.mLayoutUpdateAnimation.reset();
        this.mLayoutDeleteAnimation.reset();
        this.mShouldAnimateLayout = false;
    }

    public boolean shouldAnimateLayout(View view) {
        return (this.mShouldAnimateLayout && view.getParent() != null) || this.mLayoutHandlers.get(view.getId()) != null;
    }
}
