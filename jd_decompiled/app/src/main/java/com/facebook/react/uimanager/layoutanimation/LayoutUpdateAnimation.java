package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    @Nullable
    Animation createAnimationImpl(View view, int i2, int i3, int i4, int i5) {
        boolean z = (view.getX() == ((float) i2) && view.getY() == ((float) i3)) ? false : true;
        boolean z2 = (view.getWidth() == i4 && view.getHeight() == i5) ? false : true;
        if (z || z2) {
            return new PositionAndSizeAnimation(view, i2, i3, i4, i5);
        }
        return null;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0;
    }
}
