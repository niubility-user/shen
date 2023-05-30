package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public abstract class YogaConfig {
    public static int SPACING_TYPE = 1;

    public abstract YogaLogger getLogger();

    abstract long getNativePointer();

    public abstract void setExperimentalFeatureEnabled(YogaExperimentalFeature yogaExperimentalFeature, boolean z);

    public abstract void setLogger(YogaLogger yogaLogger);

    public abstract void setPointScaleFactor(float f2);

    public abstract void setPrintTreeFlag(boolean z);

    public abstract void setShouldDiffLayoutWithoutLegacyStretchBehaviour(boolean z);

    public abstract void setUseLegacyStretchBehaviour(boolean z);

    public abstract void setUseWebDefaults(boolean z);
}
