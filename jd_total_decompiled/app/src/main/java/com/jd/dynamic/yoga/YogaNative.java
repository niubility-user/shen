package com.jd.dynamic.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.yoga.android.YogaInit;

@DoNotStrip
/* loaded from: classes13.dex */
public class YogaNative {
    static {
        try {
            loadSo(true);
        } catch (Throwable th) {
            DynamicSdk.handException("YogaInit", "SDK init Yoga with exception,soloader is init?" + SoLoader.isInitialized(), null, "SDK1", new Exception(th));
            if (SoLoader.isInitialized() || DynamicSdk.getEngine().getContext() == null) {
                return;
            }
            try {
                YogaInit.initYoga(DynamicSdk.getEngine().getContext());
                loadSo(true);
            } catch (Throwable unused) {
                DynamicSdk.handException("YogaInit", "SDK retry init Yoga with exception,soloader is init?" + SoLoader.isInitialized(), null, "SDK2", new Exception(th));
                try {
                    loadSo(false);
                } catch (Throwable unused2) {
                    DynamicSdk.handException("YogaInit", "SDK retry again init Yoga with exception,use System loadLibrary with Exception", null, "SDK3", new Exception(th));
                }
            }
        }
    }

    public static native void jni_YGConfigFreeJNI(long j2);

    public static native long jni_YGConfigNewJNI();

    public static native void jni_YGConfigSetExperimentalFeatureEnabledJNI(long j2, int i2, boolean z);

    public static native void jni_YGConfigSetLoggerJNI(long j2, YogaLogger yogaLogger);

    public static native void jni_YGConfigSetPointScaleFactorJNI(long j2, float f2);

    public static native void jni_YGConfigSetPrintTreeFlagJNI(long j2, boolean z);

    public static native void jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviourJNI(long j2, boolean z);

    public static native void jni_YGConfigSetUseLegacyStretchBehaviourJNI(long j2, boolean z);

    public static native void jni_YGConfigSetUseWebDefaultsJNI(long j2, boolean z);

    public static native void jni_YGNodeCalculateLayoutJNI(long j2, float f2, float f3, long[] jArr, YogaNodeJNIBase[] yogaNodeJNIBaseArr);

    public static native void jni_YGNodeClearChildrenJNI(long j2);

    public static native long jni_YGNodeCloneJNI(long j2);

    public static native void jni_YGNodeCopyStyleJNI(long j2, long j3);

    public static native void jni_YGNodeFreeJNI(long j2);

    public static native void jni_YGNodeInsertChildJNI(long j2, long j3, int i2);

    public static native boolean jni_YGNodeIsDirtyJNI(long j2);

    public static native boolean jni_YGNodeIsReferenceBaselineJNI(long j2);

    public static native void jni_YGNodeMarkDirtyAndPropogateToDescendantsJNI(long j2);

    public static native void jni_YGNodeMarkDirtyJNI(long j2);

    public static native long jni_YGNodeNewJNI();

    public static native long jni_YGNodeNewWithConfigJNI(long j2);

    public static native void jni_YGNodePrintJNI(long j2);

    public static native void jni_YGNodeRemoveChildJNI(long j2, long j3);

    public static native void jni_YGNodeResetJNI(long j2);

    public static native void jni_YGNodeSetHasBaselineFuncJNI(long j2, boolean z);

    public static native void jni_YGNodeSetHasMeasureFuncJNI(long j2, boolean z);

    public static native void jni_YGNodeSetIsReferenceBaselineJNI(long j2, boolean z);

    static native void jni_YGNodeSetStyleInputsJNI(long j2, float[] fArr, int i2);

    public static native int jni_YGNodeStyleGetAlignContentJNI(long j2);

    public static native int jni_YGNodeStyleGetAlignItemsJNI(long j2);

    public static native int jni_YGNodeStyleGetAlignSelfJNI(long j2);

    public static native float jni_YGNodeStyleGetAspectRatioJNI(long j2);

    public static native float jni_YGNodeStyleGetBorderJNI(long j2, int i2);

    public static native int jni_YGNodeStyleGetDirectionJNI(long j2);

    public static native int jni_YGNodeStyleGetDisplayJNI(long j2);

    public static native long jni_YGNodeStyleGetFlexBasisJNI(long j2);

    public static native int jni_YGNodeStyleGetFlexDirectionJNI(long j2);

    public static native float jni_YGNodeStyleGetFlexGrowJNI(long j2);

    public static native float jni_YGNodeStyleGetFlexJNI(long j2);

    public static native float jni_YGNodeStyleGetFlexShrinkJNI(long j2);

    public static native int jni_YGNodeStyleGetFlexWrapJNI(long j2);

    public static native long jni_YGNodeStyleGetHeightJNI(long j2);

    public static native int jni_YGNodeStyleGetJustifyContentJNI(long j2);

    public static native long jni_YGNodeStyleGetMarginJNI(long j2, int i2);

    public static native long jni_YGNodeStyleGetMaxHeightJNI(long j2);

    public static native long jni_YGNodeStyleGetMaxWidthJNI(long j2);

    public static native long jni_YGNodeStyleGetMinHeightJNI(long j2);

    public static native long jni_YGNodeStyleGetMinWidthJNI(long j2);

    public static native int jni_YGNodeStyleGetOverflowJNI(long j2);

    public static native long jni_YGNodeStyleGetPaddingJNI(long j2, int i2);

    public static native long jni_YGNodeStyleGetPositionJNI(long j2, int i2);

    public static native int jni_YGNodeStyleGetPositionTypeJNI(long j2);

    public static native long jni_YGNodeStyleGetWidthJNI(long j2);

    public static native void jni_YGNodeStyleSetAlignContentJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetAlignItemsJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetAlignSelfJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetAspectRatioJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetBorderJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetDirectionJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetDisplayJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetFlexBasisAutoJNI(long j2);

    public static native void jni_YGNodeStyleSetFlexBasisJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetFlexBasisPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetFlexDirectionJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetFlexGrowJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetFlexJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetFlexShrinkJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetFlexWrapJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetHeightAutoJNI(long j2);

    public static native void jni_YGNodeStyleSetHeightJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetHeightPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetJustifyContentJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetMarginAutoJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetMarginJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetMarginPercentJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetMaxHeightJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMaxHeightPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMaxWidthJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMaxWidthPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMinHeightJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMinHeightPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMinWidthJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetMinWidthPercentJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetOverflowJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetPaddingJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetPaddingPercentJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetPositionJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetPositionPercentJNI(long j2, int i2, float f2);

    public static native void jni_YGNodeStyleSetPositionTypeJNI(long j2, int i2);

    public static native void jni_YGNodeStyleSetWidthAutoJNI(long j2);

    public static native void jni_YGNodeStyleSetWidthJNI(long j2, float f2);

    public static native void jni_YGNodeStyleSetWidthPercentJNI(long j2, float f2);

    public static native void jni_YGNodeSwapChildJNI(long j2, long j3, int i2);

    private static void loadSo(boolean z) {
        if (z) {
            t.e("YogaNative", "loadSo useLoader");
            SoLoader.loadLibrary("jdDynamic");
            return;
        }
        t.e("YogaNative", "loadSo use System load");
        System.loadLibrary("c++_shared");
        System.loadLibrary("jdDynamic");
    }
}
