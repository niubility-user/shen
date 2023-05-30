package com.facebook.react.animation;

import android.util.SparseArray;
import com.facebook.react.bridge.UiThreadUtil;

/* loaded from: classes.dex */
public class AnimationRegistry {
    private final SparseArray<Animation> mRegistry = new SparseArray<>();

    public Animation getAnimation(int i2) {
        UiThreadUtil.assertOnUiThread();
        return this.mRegistry.get(i2);
    }

    public void registerAnimation(Animation animation) {
        UiThreadUtil.assertOnUiThread();
        this.mRegistry.put(animation.getAnimationID(), animation);
    }

    public Animation removeAnimation(int i2) {
        UiThreadUtil.assertOnUiThread();
        Animation animation = this.mRegistry.get(i2);
        if (animation != null) {
            this.mRegistry.delete(i2);
        }
        return animation;
    }
}
