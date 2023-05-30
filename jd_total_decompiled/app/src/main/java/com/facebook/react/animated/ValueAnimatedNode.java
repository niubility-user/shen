package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import javax.annotation.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes.dex */
class ValueAnimatedNode extends AnimatedNode {
    double mOffset;
    double mValue;
    @Nullable
    private AnimatedNodeValueListener mValueListener;

    public ValueAnimatedNode() {
        this.mValue = Double.NaN;
        this.mOffset = 0.0d;
    }

    public void extractOffset() {
        this.mOffset += this.mValue;
        this.mValue = 0.0d;
    }

    public void flattenOffset() {
        this.mValue += this.mOffset;
        this.mOffset = 0.0d;
    }

    public double getValue() {
        return this.mOffset + this.mValue;
    }

    public void onValueUpdate() {
        AnimatedNodeValueListener animatedNodeValueListener = this.mValueListener;
        if (animatedNodeValueListener == null) {
            return;
        }
        animatedNodeValueListener.onValueUpdate(getValue());
    }

    public void setValueListener(@Nullable AnimatedNodeValueListener animatedNodeValueListener) {
        this.mValueListener = animatedNodeValueListener;
    }

    public ValueAnimatedNode(ReadableMap readableMap) {
        this.mValue = Double.NaN;
        this.mOffset = 0.0d;
        this.mValue = readableMap.getDouble("value");
        this.mOffset = readableMap.getDouble(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET);
    }
}
