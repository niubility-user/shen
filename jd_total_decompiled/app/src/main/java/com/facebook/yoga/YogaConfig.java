package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
/* loaded from: classes12.dex */
public class YogaConfig {
    public static int SPACING_TYPE = 1;
    private YogaLogger mLogger;
    long mNativePointer;
    private YogaNodeCloneFunction mYogaNodeCloneFunction;

    static {
        SoLoader.loadLibrary("yoga");
    }

    public YogaConfig() {
        long jni_YGConfigNew = jni_YGConfigNew();
        this.mNativePointer = jni_YGConfigNew;
        if (jni_YGConfigNew == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    @DoNotStrip
    private final YogaNode cloneNode(YogaNode yogaNode, YogaNode yogaNode2, int i2) {
        return this.mYogaNodeCloneFunction.cloneNode(yogaNode, yogaNode2, i2);
    }

    private native void jni_YGConfigFree(long j2);

    private native long jni_YGConfigNew();

    private native void jni_YGConfigSetExperimentalFeatureEnabled(long j2, int i2, boolean z);

    private native void jni_YGConfigSetHasCloneNodeFunc(long j2, boolean z);

    private native void jni_YGConfigSetLogger(long j2, Object obj);

    private native void jni_YGConfigSetPointScaleFactor(long j2, float f2);

    private native void jni_YGConfigSetPrintTreeFlag(long j2, boolean z);

    private native void jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviour(long j2, boolean z);

    private native void jni_YGConfigSetUseLegacyStretchBehaviour(long j2, boolean z);

    private native void jni_YGConfigSetUseWebDefaults(long j2, boolean z);

    protected void finalize() throws Throwable {
        try {
            jni_YGConfigFree(this.mNativePointer);
        } finally {
            super.finalize();
        }
    }

    public YogaLogger getLogger() {
        return this.mLogger;
    }

    public void setExperimentalFeatureEnabled(YogaExperimentalFeature yogaExperimentalFeature, boolean z) {
        jni_YGConfigSetExperimentalFeatureEnabled(this.mNativePointer, yogaExperimentalFeature.intValue(), z);
    }

    public void setLogger(YogaLogger yogaLogger) {
        this.mLogger = yogaLogger;
        jni_YGConfigSetLogger(this.mNativePointer, yogaLogger);
    }

    public void setOnCloneNode(YogaNodeCloneFunction yogaNodeCloneFunction) {
        this.mYogaNodeCloneFunction = yogaNodeCloneFunction;
        jni_YGConfigSetHasCloneNodeFunc(this.mNativePointer, yogaNodeCloneFunction != null);
    }

    public void setPointScaleFactor(float f2) {
        jni_YGConfigSetPointScaleFactor(this.mNativePointer, f2);
    }

    public void setPrintTreeFlag(boolean z) {
        jni_YGConfigSetPrintTreeFlag(this.mNativePointer, z);
    }

    public void setShouldDiffLayoutWithoutLegacyStretchBehaviour(boolean z) {
        jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviour(this.mNativePointer, z);
    }

    public void setUseLegacyStretchBehaviour(boolean z) {
        jni_YGConfigSetUseLegacyStretchBehaviour(this.mNativePointer, z);
    }

    public void setUseWebDefaults(boolean z) {
        jni_YGConfigSetUseWebDefaults(this.mNativePointer, z);
    }
}
