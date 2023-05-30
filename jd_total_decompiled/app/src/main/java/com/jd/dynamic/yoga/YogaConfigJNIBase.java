package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public abstract class YogaConfigJNIBase extends YogaConfig {
    private YogaLogger mLogger;
    long mNativePointer;

    public YogaConfigJNIBase() {
        this(YogaNative.jni_YGConfigNewJNI());
    }

    private YogaConfigJNIBase(long j2) {
        if (j2 == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
        this.mNativePointer = j2;
    }

    YogaConfigJNIBase(boolean z) {
        this(YogaNative.jni_YGConfigNewJNI());
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public YogaLogger getLogger() {
        return this.mLogger;
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    long getNativePointer() {
        return this.mNativePointer;
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setExperimentalFeatureEnabled(YogaExperimentalFeature yogaExperimentalFeature, boolean z) {
        YogaNative.jni_YGConfigSetExperimentalFeatureEnabledJNI(this.mNativePointer, yogaExperimentalFeature.intValue(), z);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setLogger(YogaLogger yogaLogger) {
        this.mLogger = yogaLogger;
        YogaNative.jni_YGConfigSetLoggerJNI(this.mNativePointer, yogaLogger);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setPointScaleFactor(float f2) {
        YogaNative.jni_YGConfigSetPointScaleFactorJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setPrintTreeFlag(boolean z) {
        YogaNative.jni_YGConfigSetPrintTreeFlagJNI(this.mNativePointer, z);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setShouldDiffLayoutWithoutLegacyStretchBehaviour(boolean z) {
        YogaNative.jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviourJNI(this.mNativePointer, z);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setUseLegacyStretchBehaviour(boolean z) {
        YogaNative.jni_YGConfigSetUseLegacyStretchBehaviourJNI(this.mNativePointer, z);
    }

    @Override // com.jd.dynamic.yoga.YogaConfig
    public void setUseWebDefaults(boolean z) {
        YogaNative.jni_YGConfigSetUseWebDefaultsJNI(this.mNativePointer, z);
    }
}
