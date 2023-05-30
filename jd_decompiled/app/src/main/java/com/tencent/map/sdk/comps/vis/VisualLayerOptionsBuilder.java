package com.tencent.map.sdk.comps.vis;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Builder;

/* loaded from: classes9.dex */
public class VisualLayerOptionsBuilder implements Builder<VisualLayerOptions> {
    public final VisualLayerOptions mLayerOptions;

    public VisualLayerOptionsBuilder(VisualLayerOptions visualLayerOptions) {
        this.mLayerOptions = visualLayerOptions;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Builder
    public VisualLayerOptions build() {
        return this.mLayerOptions;
    }

    public VisualLayerOptionsBuilder setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.mLayerOptions.mAlpha = f2;
        return this;
    }

    public VisualLayerOptionsBuilder setClickEnable(boolean z) {
        this.mLayerOptions.mClickEnabled = z;
        return this;
    }

    public VisualLayerOptionsBuilder setLevel(int i2) {
        this.mLayerOptions.mLevel = i2;
        return this;
    }

    public VisualLayerOptionsBuilder setTimeInterval(@IntRange(from = 15) int i2) {
        this.mLayerOptions.mTimeInternal = i2;
        return this;
    }

    public VisualLayerOptionsBuilder setZIndex(int i2) {
        this.mLayerOptions.mZIndex = i2;
        return this;
    }
}
