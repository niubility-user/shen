package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes12.dex */
public class YogaNode implements Cloneable {
    private static final int BORDER = 4;
    private static final int MARGIN = 1;
    private static final int PADDING = 2;
    private YogaBaselineFunction mBaselineFunction;
    @Nullable
    private List<YogaNode> mChildren;
    private Object mData;
    private YogaMeasureFunction mMeasureFunction;
    private long mNativePointer;
    private YogaNode mOwner;
    @DoNotStrip
    private int mEdgeSetFlag = 0;
    private boolean mHasSetPosition = false;
    @DoNotStrip
    private float mWidth = Float.NaN;
    @DoNotStrip
    private float mHeight = Float.NaN;
    @DoNotStrip
    private float mTop = Float.NaN;
    @DoNotStrip
    private float mLeft = Float.NaN;
    @DoNotStrip
    private float mMarginLeft = 0.0f;
    @DoNotStrip
    private float mMarginTop = 0.0f;
    @DoNotStrip
    private float mMarginRight = 0.0f;
    @DoNotStrip
    private float mMarginBottom = 0.0f;
    @DoNotStrip
    private float mPaddingLeft = 0.0f;
    @DoNotStrip
    private float mPaddingTop = 0.0f;
    @DoNotStrip
    private float mPaddingRight = 0.0f;
    @DoNotStrip
    private float mPaddingBottom = 0.0f;
    @DoNotStrip
    private float mBorderLeft = 0.0f;
    @DoNotStrip
    private float mBorderTop = 0.0f;
    @DoNotStrip
    private float mBorderRight = 0.0f;
    @DoNotStrip
    private float mBorderBottom = 0.0f;
    @DoNotStrip
    private int mLayoutDirection = 0;
    @DoNotStrip
    private boolean mHasNewLayout = true;
    @DoNotStrip
    private boolean mDoesLegacyStretchFlagAffectsLayout = false;

    /* renamed from: com.facebook.yoga.YogaNode$1 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaEdge;

        static {
            int[] iArr = new int[YogaEdge.values().length];
            $SwitchMap$com$facebook$yoga$YogaEdge = iArr;
            try {
                iArr[YogaEdge.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaEdge[YogaEdge.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaEdge[YogaEdge.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaEdge[YogaEdge.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaEdge[YogaEdge.START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaEdge[YogaEdge.END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static {
        SoLoader.loadLibrary("yoga");
    }

    public YogaNode() {
        long jni_YGNodeNew = jni_YGNodeNew();
        this.mNativePointer = jni_YGNodeNew;
        if (jni_YGNodeNew == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    private void clearChildren() {
        this.mChildren = null;
        jni_YGNodeClearChildren(this.mNativePointer);
    }

    private static native void jni_YGNodeCalculateLayout(long j2, float f2, float f3);

    private static native void jni_YGNodeClearChildren(long j2);

    private native long jni_YGNodeClone(long j2, Object obj);

    private static native void jni_YGNodeCopyStyle(long j2, long j3);

    private static native void jni_YGNodeFree(long j2);

    static native int jni_YGNodeGetInstanceCount();

    private static native void jni_YGNodeInsertChild(long j2, long j3, int i2);

    private static native boolean jni_YGNodeIsDirty(long j2);

    private static native boolean jni_YGNodeIsReferenceBaseline(long j2);

    private static native void jni_YGNodeMarkDirty(long j2);

    private static native void jni_YGNodeMarkDirtyAndPropogateToDescendants(long j2);

    private native long jni_YGNodeNew();

    private native long jni_YGNodeNewWithConfig(long j2);

    private static native void jni_YGNodePrint(long j2);

    private static native void jni_YGNodeRemoveChild(long j2, long j3);

    private static native void jni_YGNodeReset(long j2);

    private static native void jni_YGNodeSetHasBaselineFunc(long j2, boolean z);

    private static native void jni_YGNodeSetHasMeasureFunc(long j2, boolean z);

    private static native void jni_YGNodeSetIsReferenceBaseline(long j2, boolean z);

    private static native void jni_YGNodeSetOwner(long j2, long j3);

    private static native int jni_YGNodeStyleGetAlignContent(long j2);

    private static native int jni_YGNodeStyleGetAlignItems(long j2);

    private static native int jni_YGNodeStyleGetAlignSelf(long j2);

    private static native float jni_YGNodeStyleGetAspectRatio(long j2);

    private static native float jni_YGNodeStyleGetBorder(long j2, int i2);

    private static native int jni_YGNodeStyleGetDirection(long j2);

    private static native int jni_YGNodeStyleGetDisplay(long j2);

    private static native Object jni_YGNodeStyleGetFlexBasis(long j2);

    private static native int jni_YGNodeStyleGetFlexDirection(long j2);

    private static native float jni_YGNodeStyleGetFlexGrow(long j2);

    private static native float jni_YGNodeStyleGetFlexShrink(long j2);

    private static native Object jni_YGNodeStyleGetHeight(long j2);

    private static native int jni_YGNodeStyleGetJustifyContent(long j2);

    private static native Object jni_YGNodeStyleGetMargin(long j2, int i2);

    private static native Object jni_YGNodeStyleGetMaxHeight(long j2);

    private static native Object jni_YGNodeStyleGetMaxWidth(long j2);

    private static native Object jni_YGNodeStyleGetMinHeight(long j2);

    private static native Object jni_YGNodeStyleGetMinWidth(long j2);

    private static native int jni_YGNodeStyleGetOverflow(long j2);

    private static native Object jni_YGNodeStyleGetPadding(long j2, int i2);

    private static native Object jni_YGNodeStyleGetPosition(long j2, int i2);

    private static native int jni_YGNodeStyleGetPositionType(long j2);

    private static native Object jni_YGNodeStyleGetWidth(long j2);

    private static native void jni_YGNodeStyleSetAlignContent(long j2, int i2);

    private static native void jni_YGNodeStyleSetAlignItems(long j2, int i2);

    private static native void jni_YGNodeStyleSetAlignSelf(long j2, int i2);

    private static native void jni_YGNodeStyleSetAspectRatio(long j2, float f2);

    private static native void jni_YGNodeStyleSetBorder(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetDirection(long j2, int i2);

    private static native void jni_YGNodeStyleSetDisplay(long j2, int i2);

    private static native void jni_YGNodeStyleSetFlex(long j2, float f2);

    private static native void jni_YGNodeStyleSetFlexBasis(long j2, float f2);

    private static native void jni_YGNodeStyleSetFlexBasisAuto(long j2);

    private static native void jni_YGNodeStyleSetFlexBasisPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetFlexDirection(long j2, int i2);

    private static native void jni_YGNodeStyleSetFlexGrow(long j2, float f2);

    private static native void jni_YGNodeStyleSetFlexShrink(long j2, float f2);

    private static native void jni_YGNodeStyleSetFlexWrap(long j2, int i2);

    private static native void jni_YGNodeStyleSetHeight(long j2, float f2);

    private static native void jni_YGNodeStyleSetHeightAuto(long j2);

    private static native void jni_YGNodeStyleSetHeightPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetJustifyContent(long j2, int i2);

    private static native void jni_YGNodeStyleSetMargin(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetMarginAuto(long j2, int i2);

    private static native void jni_YGNodeStyleSetMarginPercent(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetMaxHeight(long j2, float f2);

    private static native void jni_YGNodeStyleSetMaxHeightPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetMaxWidth(long j2, float f2);

    private static native void jni_YGNodeStyleSetMaxWidthPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetMinHeight(long j2, float f2);

    private static native void jni_YGNodeStyleSetMinHeightPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetMinWidth(long j2, float f2);

    private static native void jni_YGNodeStyleSetMinWidthPercent(long j2, float f2);

    private static native void jni_YGNodeStyleSetOverflow(long j2, int i2);

    private static native void jni_YGNodeStyleSetPadding(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetPaddingPercent(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetPosition(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetPositionPercent(long j2, int i2, float f2);

    private static native void jni_YGNodeStyleSetPositionType(long j2, int i2);

    private static native void jni_YGNodeStyleSetWidth(long j2, float f2);

    private static native void jni_YGNodeStyleSetWidthAuto(long j2);

    private static native void jni_YGNodeStyleSetWidthPercent(long j2, float f2);

    @DoNotStrip
    private final long replaceChild(YogaNode yogaNode, int i2) {
        List<YogaNode> list = this.mChildren;
        if (list != null) {
            list.remove(i2);
            this.mChildren.add(i2, yogaNode);
            yogaNode.mOwner = this;
            return yogaNode.mNativePointer;
        }
        throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
    }

    public void addChildAt(YogaNode yogaNode, int i2) {
        if (yogaNode.mOwner == null) {
            if (this.mChildren == null) {
                this.mChildren = new ArrayList(4);
            }
            this.mChildren.add(i2, yogaNode);
            yogaNode.mOwner = this;
            jni_YGNodeInsertChild(this.mNativePointer, yogaNode.mNativePointer, i2);
            return;
        }
        throw new IllegalStateException("Child already has a parent, it must be removed first.");
    }

    @DoNotStrip
    public final float baseline(float f2, float f3) {
        return this.mBaselineFunction.baseline(this, f2, f3);
    }

    public void calculateLayout(float f2, float f3) {
        jni_YGNodeCalculateLayout(this.mNativePointer, f2, f3);
    }

    public YogaNode cloneWithNewChildren() {
        try {
            YogaNode yogaNode = (YogaNode) super.clone();
            long jni_YGNodeClone = jni_YGNodeClone(this.mNativePointer, yogaNode);
            yogaNode.mOwner = null;
            yogaNode.mNativePointer = jni_YGNodeClone;
            yogaNode.clearChildren();
            return yogaNode;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void copyStyle(YogaNode yogaNode) {
        jni_YGNodeCopyStyle(this.mNativePointer, yogaNode.mNativePointer);
    }

    public void dirty() {
        jni_YGNodeMarkDirty(this.mNativePointer);
    }

    public void dirtyAllDescendants() {
        jni_YGNodeMarkDirtyAndPropogateToDescendants(this.mNativePointer);
    }

    protected void finalize() throws Throwable {
        try {
            freeNatives();
        } finally {
            super.finalize();
        }
    }

    public void freeNatives() {
        long j2 = this.mNativePointer;
        if (j2 > 0) {
            this.mNativePointer = 0L;
            jni_YGNodeFree(j2);
        }
    }

    public YogaAlign getAlignContent() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignContent(this.mNativePointer));
    }

    public YogaAlign getAlignItems() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignItems(this.mNativePointer));
    }

    public YogaAlign getAlignSelf() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignSelf(this.mNativePointer));
    }

    public float getAspectRatio() {
        return jni_YGNodeStyleGetAspectRatio(this.mNativePointer);
    }

    public float getBorder(YogaEdge yogaEdge) {
        if ((this.mEdgeSetFlag & 4) != 4) {
            return Float.NaN;
        }
        return jni_YGNodeStyleGetBorder(this.mNativePointer, yogaEdge.intValue());
    }

    public YogaNode getChildAt(int i2) {
        List<YogaNode> list = this.mChildren;
        if (list != null) {
            return list.get(i2);
        }
        throw new IllegalStateException("YogaNode does not have children");
    }

    public int getChildCount() {
        List<YogaNode> list = this.mChildren;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Object getData() {
        return this.mData;
    }

    public YogaDisplay getDisplay() {
        return YogaDisplay.fromInt(jni_YGNodeStyleGetDisplay(this.mNativePointer));
    }

    public boolean getDoesLegacyStretchFlagAffectsLayout() {
        return this.mDoesLegacyStretchFlagAffectsLayout;
    }

    public YogaValue getFlexBasis() {
        return (YogaValue) jni_YGNodeStyleGetFlexBasis(this.mNativePointer);
    }

    public YogaFlexDirection getFlexDirection() {
        return YogaFlexDirection.fromInt(jni_YGNodeStyleGetFlexDirection(this.mNativePointer));
    }

    public float getFlexGrow() {
        return jni_YGNodeStyleGetFlexGrow(this.mNativePointer);
    }

    public float getFlexShrink() {
        return jni_YGNodeStyleGetFlexShrink(this.mNativePointer);
    }

    public YogaValue getHeight() {
        return (YogaValue) jni_YGNodeStyleGetHeight(this.mNativePointer);
    }

    public YogaJustify getJustifyContent() {
        return YogaJustify.fromInt(jni_YGNodeStyleGetJustifyContent(this.mNativePointer));
    }

    public float getLayoutBorder(YogaEdge yogaEdge) {
        switch (AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaEdge[yogaEdge.ordinal()]) {
            case 1:
                return this.mBorderLeft;
            case 2:
                return this.mBorderTop;
            case 3:
                return this.mBorderRight;
            case 4:
                return this.mBorderBottom;
            case 5:
                return getLayoutDirection() == YogaDirection.RTL ? this.mBorderRight : this.mBorderLeft;
            case 6:
                return getLayoutDirection() == YogaDirection.RTL ? this.mBorderLeft : this.mBorderRight;
            default:
                throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
        }
    }

    public YogaDirection getLayoutDirection() {
        return YogaDirection.fromInt(this.mLayoutDirection);
    }

    public float getLayoutHeight() {
        return this.mHeight;
    }

    public float getLayoutMargin(YogaEdge yogaEdge) {
        switch (AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaEdge[yogaEdge.ordinal()]) {
            case 1:
                return this.mMarginLeft;
            case 2:
                return this.mMarginTop;
            case 3:
                return this.mMarginRight;
            case 4:
                return this.mMarginBottom;
            case 5:
                return getLayoutDirection() == YogaDirection.RTL ? this.mMarginRight : this.mMarginLeft;
            case 6:
                return getLayoutDirection() == YogaDirection.RTL ? this.mMarginLeft : this.mMarginRight;
            default:
                throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
        }
    }

    public float getLayoutPadding(YogaEdge yogaEdge) {
        switch (AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaEdge[yogaEdge.ordinal()]) {
            case 1:
                return this.mPaddingLeft;
            case 2:
                return this.mPaddingTop;
            case 3:
                return this.mPaddingRight;
            case 4:
                return this.mPaddingBottom;
            case 5:
                return getLayoutDirection() == YogaDirection.RTL ? this.mPaddingRight : this.mPaddingLeft;
            case 6:
                return getLayoutDirection() == YogaDirection.RTL ? this.mPaddingLeft : this.mPaddingRight;
            default:
                throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
        }
    }

    public float getLayoutWidth() {
        return this.mWidth;
    }

    public float getLayoutX() {
        return this.mLeft;
    }

    public float getLayoutY() {
        return this.mTop;
    }

    public YogaValue getMargin(YogaEdge yogaEdge) {
        if ((this.mEdgeSetFlag & 1) != 1) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetMargin(this.mNativePointer, yogaEdge.intValue());
    }

    public YogaValue getMaxHeight() {
        return (YogaValue) jni_YGNodeStyleGetMaxHeight(this.mNativePointer);
    }

    public YogaValue getMaxWidth() {
        return (YogaValue) jni_YGNodeStyleGetMaxWidth(this.mNativePointer);
    }

    public YogaValue getMinHeight() {
        return (YogaValue) jni_YGNodeStyleGetMinHeight(this.mNativePointer);
    }

    public YogaValue getMinWidth() {
        return (YogaValue) jni_YGNodeStyleGetMinWidth(this.mNativePointer);
    }

    public YogaOverflow getOverflow() {
        return YogaOverflow.fromInt(jni_YGNodeStyleGetOverflow(this.mNativePointer));
    }

    @Nullable
    public YogaNode getOwner() {
        return this.mOwner;
    }

    public YogaValue getPadding(YogaEdge yogaEdge) {
        if ((this.mEdgeSetFlag & 2) != 2) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetPadding(this.mNativePointer, yogaEdge.intValue());
    }

    @Nullable
    @Deprecated
    public YogaNode getParent() {
        return getOwner();
    }

    public YogaValue getPosition(YogaEdge yogaEdge) {
        if (!this.mHasSetPosition) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetPosition(this.mNativePointer, yogaEdge.intValue());
    }

    public YogaPositionType getPositionType() {
        return YogaPositionType.fromInt(jni_YGNodeStyleGetPositionType(this.mNativePointer));
    }

    public YogaDirection getStyleDirection() {
        return YogaDirection.fromInt(jni_YGNodeStyleGetDirection(this.mNativePointer));
    }

    public YogaValue getWidth() {
        return (YogaValue) jni_YGNodeStyleGetWidth(this.mNativePointer);
    }

    public boolean hasNewLayout() {
        return this.mHasNewLayout;
    }

    public int indexOf(YogaNode yogaNode) {
        List<YogaNode> list = this.mChildren;
        if (list == null) {
            return -1;
        }
        return list.indexOf(yogaNode);
    }

    public boolean isDirty() {
        return jni_YGNodeIsDirty(this.mNativePointer);
    }

    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    public boolean isReferenceBaseline() {
        return jni_YGNodeIsReferenceBaseline(this.mNativePointer);
    }

    public void markLayoutSeen() {
        this.mHasNewLayout = false;
    }

    @DoNotStrip
    public final long measure(float f2, int i2, float f3, int i3) {
        if (isMeasureDefined()) {
            return this.mMeasureFunction.measure(this, f2, YogaMeasureMode.fromInt(i2), f3, YogaMeasureMode.fromInt(i3));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    public void print() {
        jni_YGNodePrint(this.mNativePointer);
    }

    public YogaNode removeChildAt(int i2) {
        List<YogaNode> list = this.mChildren;
        if (list != null) {
            YogaNode remove = list.remove(i2);
            remove.mOwner = null;
            jni_YGNodeRemoveChild(this.mNativePointer, remove.mNativePointer);
            return remove;
        }
        throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
    }

    public void reset() {
        this.mEdgeSetFlag = 0;
        this.mHasSetPosition = false;
        this.mHasNewLayout = true;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = 0.0f;
        this.mMarginTop = 0.0f;
        this.mMarginRight = 0.0f;
        this.mMarginBottom = 0.0f;
        this.mPaddingLeft = 0.0f;
        this.mPaddingTop = 0.0f;
        this.mPaddingRight = 0.0f;
        this.mPaddingBottom = 0.0f;
        this.mBorderLeft = 0.0f;
        this.mBorderTop = 0.0f;
        this.mBorderRight = 0.0f;
        this.mBorderBottom = 0.0f;
        this.mLayoutDirection = 0;
        this.mMeasureFunction = null;
        this.mBaselineFunction = null;
        this.mData = null;
        this.mDoesLegacyStretchFlagAffectsLayout = false;
        jni_YGNodeReset(this.mNativePointer);
    }

    public void setAlignContent(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignContent(this.mNativePointer, yogaAlign.intValue());
    }

    public void setAlignItems(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignItems(this.mNativePointer, yogaAlign.intValue());
    }

    public void setAlignSelf(YogaAlign yogaAlign) {
        jni_YGNodeStyleSetAlignSelf(this.mNativePointer, yogaAlign.intValue());
    }

    public void setAspectRatio(float f2) {
        jni_YGNodeStyleSetAspectRatio(this.mNativePointer, f2);
    }

    public void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction) {
        this.mBaselineFunction = yogaBaselineFunction;
        jni_YGNodeSetHasBaselineFunc(this.mNativePointer, yogaBaselineFunction != null);
    }

    public void setBorder(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 4;
        jni_YGNodeStyleSetBorder(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setData(Object obj) {
        this.mData = obj;
    }

    public void setDirection(YogaDirection yogaDirection) {
        jni_YGNodeStyleSetDirection(this.mNativePointer, yogaDirection.intValue());
    }

    public void setDisplay(YogaDisplay yogaDisplay) {
        jni_YGNodeStyleSetDisplay(this.mNativePointer, yogaDisplay.intValue());
    }

    public void setFlex(float f2) {
        jni_YGNodeStyleSetFlex(this.mNativePointer, f2);
    }

    public void setFlexBasis(float f2) {
        jni_YGNodeStyleSetFlexBasis(this.mNativePointer, f2);
    }

    public void setFlexBasisAuto() {
        jni_YGNodeStyleSetFlexBasisAuto(this.mNativePointer);
    }

    public void setFlexBasisPercent(float f2) {
        jni_YGNodeStyleSetFlexBasisPercent(this.mNativePointer, f2);
    }

    public void setFlexDirection(YogaFlexDirection yogaFlexDirection) {
        jni_YGNodeStyleSetFlexDirection(this.mNativePointer, yogaFlexDirection.intValue());
    }

    public void setFlexGrow(float f2) {
        jni_YGNodeStyleSetFlexGrow(this.mNativePointer, f2);
    }

    public void setFlexShrink(float f2) {
        jni_YGNodeStyleSetFlexShrink(this.mNativePointer, f2);
    }

    public void setHeight(float f2) {
        jni_YGNodeStyleSetHeight(this.mNativePointer, f2);
    }

    public void setHeightAuto() {
        jni_YGNodeStyleSetHeightAuto(this.mNativePointer);
    }

    public void setHeightPercent(float f2) {
        jni_YGNodeStyleSetHeightPercent(this.mNativePointer, f2);
    }

    public void setIsReferenceBaseline(boolean z) {
        jni_YGNodeSetIsReferenceBaseline(this.mNativePointer, z);
    }

    public void setJustifyContent(YogaJustify yogaJustify) {
        jni_YGNodeStyleSetJustifyContent(this.mNativePointer, yogaJustify.intValue());
    }

    public void setMargin(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMargin(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setMarginAuto(YogaEdge yogaEdge) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginAuto(this.mNativePointer, yogaEdge.intValue());
    }

    public void setMarginPercent(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginPercent(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setMaxHeight(float f2) {
        jni_YGNodeStyleSetMaxHeight(this.mNativePointer, f2);
    }

    public void setMaxHeightPercent(float f2) {
        jni_YGNodeStyleSetMaxHeightPercent(this.mNativePointer, f2);
    }

    public void setMaxWidth(float f2) {
        jni_YGNodeStyleSetMaxWidth(this.mNativePointer, f2);
    }

    public void setMaxWidthPercent(float f2) {
        jni_YGNodeStyleSetMaxWidthPercent(this.mNativePointer, f2);
    }

    public void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction) {
        this.mMeasureFunction = yogaMeasureFunction;
        jni_YGNodeSetHasMeasureFunc(this.mNativePointer, yogaMeasureFunction != null);
    }

    public void setMinHeight(float f2) {
        jni_YGNodeStyleSetMinHeight(this.mNativePointer, f2);
    }

    public void setMinHeightPercent(float f2) {
        jni_YGNodeStyleSetMinHeightPercent(this.mNativePointer, f2);
    }

    public void setMinWidth(float f2) {
        jni_YGNodeStyleSetMinWidth(this.mNativePointer, f2);
    }

    public void setMinWidthPercent(float f2) {
        jni_YGNodeStyleSetMinWidthPercent(this.mNativePointer, f2);
    }

    public void setOverflow(YogaOverflow yogaOverflow) {
        jni_YGNodeStyleSetOverflow(this.mNativePointer, yogaOverflow.intValue());
    }

    public void setPadding(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPadding(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setPaddingPercent(YogaEdge yogaEdge, float f2) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPaddingPercent(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setPosition(YogaEdge yogaEdge, float f2) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPosition(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setPositionPercent(YogaEdge yogaEdge, float f2) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPositionPercent(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    public void setPositionType(YogaPositionType yogaPositionType) {
        jni_YGNodeStyleSetPositionType(this.mNativePointer, yogaPositionType.intValue());
    }

    public void setWidth(float f2) {
        jni_YGNodeStyleSetWidth(this.mNativePointer, f2);
    }

    public void setWidthAuto() {
        jni_YGNodeStyleSetWidthAuto(this.mNativePointer);
    }

    public void setWidthPercent(float f2) {
        jni_YGNodeStyleSetWidthPercent(this.mNativePointer, f2);
    }

    public void setWrap(YogaWrap yogaWrap) {
        jni_YGNodeStyleSetFlexWrap(this.mNativePointer, yogaWrap.intValue());
    }

    /* renamed from: clone */
    public YogaNode m10clone() {
        try {
            YogaNode yogaNode = (YogaNode) super.clone();
            long jni_YGNodeClone = jni_YGNodeClone(this.mNativePointer, yogaNode);
            List<YogaNode> list = this.mChildren;
            if (list != null) {
                for (YogaNode yogaNode2 : list) {
                    jni_YGNodeSetOwner(yogaNode2.mNativePointer, 0L);
                    yogaNode2.mOwner = null;
                }
            }
            yogaNode.mNativePointer = jni_YGNodeClone;
            yogaNode.mOwner = null;
            List<YogaNode> list2 = this.mChildren;
            List<YogaNode> list3 = list2 != null ? (List) ((ArrayList) list2).clone() : null;
            yogaNode.mChildren = list3;
            if (list3 != null) {
                Iterator<YogaNode> it = list3.iterator();
                while (it.hasNext()) {
                    it.next().mOwner = null;
                }
            }
            return yogaNode;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public YogaNode(YogaConfig yogaConfig) {
        long jni_YGNodeNewWithConfig = jni_YGNodeNewWithConfig(yogaConfig.mNativePointer);
        this.mNativePointer = jni_YGNodeNewWithConfig;
        if (jni_YGNodeNewWithConfig == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }
}
