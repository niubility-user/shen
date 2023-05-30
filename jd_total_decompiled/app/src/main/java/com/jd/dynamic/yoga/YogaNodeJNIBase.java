package com.jd.dynamic.yoga;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.List;

@DoNotStrip
/* loaded from: classes13.dex */
public abstract class YogaNodeJNIBase extends YogaNode implements Cloneable {
    private static final byte BORDER = 4;
    private static final byte DOES_LEGACY_STRETCH_BEHAVIOUR = 8;
    private static final byte HAS_NEW_LAYOUT = 16;
    private static final byte LAYOUT_BORDER_START_INDEX = 14;
    private static final byte LAYOUT_DIRECTION_INDEX = 5;
    private static final byte LAYOUT_EDGE_SET_FLAG_INDEX = 0;
    private static final byte LAYOUT_HEIGHT_INDEX = 2;
    private static final byte LAYOUT_LEFT_INDEX = 3;
    private static final byte LAYOUT_MARGIN_START_INDEX = 6;
    private static final byte LAYOUT_PADDING_START_INDEX = 10;
    private static final byte LAYOUT_TOP_INDEX = 4;
    private static final byte LAYOUT_WIDTH_INDEX = 1;
    private static final byte MARGIN = 1;
    private static final byte PADDING = 2;
    @Nullable
    @DoNotStrip
    private float[] arr;
    @Nullable
    private YogaBaselineFunction mBaselineFunction;
    @Nullable
    private List<YogaNodeJNIBase> mChildren;
    @Nullable
    private Object mData;
    private boolean mHasNewLayout;
    @DoNotStrip
    private int mLayoutDirection;
    @Nullable
    private YogaMeasureFunction mMeasureFunction;
    protected long mNativePointer;
    @Nullable
    private YogaNodeJNIBase mOwner;

    /* renamed from: com.jd.dynamic.yoga.YogaNodeJNIBase$1 */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jd$dynamic$yoga$YogaEdge;

        static {
            int[] iArr = new int[YogaEdge.values().length];
            $SwitchMap$com$jd$dynamic$yoga$YogaEdge = iArr;
            try {
                iArr[YogaEdge.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaEdge[YogaEdge.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaEdge[YogaEdge.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaEdge[YogaEdge.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaEdge[YogaEdge.START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jd$dynamic$yoga$YogaEdge[YogaEdge.END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public YogaNodeJNIBase() {
        this(YogaNative.jni_YGNodeNewJNI());
    }

    private YogaNodeJNIBase(long j2) {
        this.arr = null;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        if (j2 == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
        this.mNativePointer = j2;
    }

    public YogaNodeJNIBase(YogaConfig yogaConfig) {
        this(YogaNative.jni_YGNodeNewWithConfigJNI(((YogaConfigJNIBase) yogaConfig).mNativePointer));
    }

    @DoNotStrip
    private final long replaceChild(YogaNodeJNIBase yogaNodeJNIBase, int i2) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list != null) {
            list.remove(i2);
            this.mChildren.add(i2, yogaNodeJNIBase);
            yogaNodeJNIBase.mOwner = this;
            return yogaNodeJNIBase.mNativePointer;
        }
        throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
    }

    private static YogaValue valueFromLong(long j2) {
        return new YogaValue(Float.intBitsToFloat((int) j2), (int) (j2 >> 32));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void addChildAt(YogaNode yogaNode, int i2) {
        YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) yogaNode;
        if (yogaNodeJNIBase.mOwner != null) {
            throw new IllegalStateException("Child already has a parent, it must be removed first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(4);
        }
        this.mChildren.add(i2, yogaNodeJNIBase);
        yogaNodeJNIBase.mOwner = this;
        YogaNative.jni_YGNodeInsertChildJNI(this.mNativePointer, yogaNodeJNIBase.mNativePointer, i2);
    }

    @DoNotStrip
    public final float baseline(float f2, float f3) {
        return this.mBaselineFunction.baseline(this, f2, f3);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void calculateLayout(float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            List<YogaNodeJNIBase> list = ((YogaNodeJNIBase) arrayList.get(i2)).mChildren;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        YogaNodeJNIBase[] yogaNodeJNIBaseArr = (YogaNodeJNIBase[]) arrayList.toArray(new YogaNodeJNIBase[arrayList.size()]);
        long[] jArr = new long[yogaNodeJNIBaseArr.length];
        for (int i3 = 0; i3 < yogaNodeJNIBaseArr.length; i3++) {
            jArr[i3] = yogaNodeJNIBaseArr[i3].mNativePointer;
        }
        YogaNative.jni_YGNodeCalculateLayoutJNI(this.mNativePointer, f2, f3, jArr, yogaNodeJNIBaseArr);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void clearChildren() {
        this.mChildren = null;
        YogaNative.jni_YGNodeClearChildrenJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaNodeJNIBase cloneWithChildren() {
        try {
            YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) super.clone();
            long jni_YGNodeCloneJNI = YogaNative.jni_YGNodeCloneJNI(this.mNativePointer);
            yogaNodeJNIBase.mOwner = null;
            yogaNodeJNIBase.mNativePointer = jni_YGNodeCloneJNI;
            for (int i2 = 0; i2 < yogaNodeJNIBase.getChildCount(); i2++) {
                yogaNodeJNIBase.swapChildAt(yogaNodeJNIBase.getChildAt(i2).cloneWithChildren(), i2);
            }
            return yogaNodeJNIBase;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaNodeJNIBase cloneWithoutChildren() {
        try {
            YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) super.clone();
            long jni_YGNodeCloneJNI = YogaNative.jni_YGNodeCloneJNI(this.mNativePointer);
            yogaNodeJNIBase.mOwner = null;
            yogaNodeJNIBase.mNativePointer = jni_YGNodeCloneJNI;
            yogaNodeJNIBase.clearChildren();
            return yogaNodeJNIBase;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void copyStyle(YogaNode yogaNode) {
        YogaNative.jni_YGNodeCopyStyleJNI(this.mNativePointer, ((YogaNodeJNIBase) yogaNode).mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void dirty() {
        YogaNative.jni_YGNodeMarkDirtyJNI(this.mNativePointer);
    }

    public void dirtyAllDescendants() {
        YogaNative.jni_YGNodeMarkDirtyAndPropogateToDescendantsJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaAlign getAlignContent() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignContentJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaAlign getAlignItems() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignItemsJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaAlign getAlignSelf() {
        return YogaAlign.fromInt(YogaNative.jni_YGNodeStyleGetAlignSelfJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getAspectRatio() {
        return YogaNative.jni_YGNodeStyleGetAspectRatioJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getBorder(YogaEdge yogaEdge) {
        return YogaNative.jni_YGNodeStyleGetBorderJNI(this.mNativePointer, yogaEdge.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaNodeJNIBase getChildAt(int i2) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list != null) {
            return list.get(i2);
        }
        throw new IllegalStateException("YogaNode does not have children");
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public int getChildCount() {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    @Nullable
    public Object getData() {
        return this.mData;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaDisplay getDisplay() {
        return YogaDisplay.fromInt(YogaNative.jni_YGNodeStyleGetDisplayJNI(this.mNativePointer));
    }

    public boolean getDoesLegacyStretchFlagAffectsLayout() {
        float[] fArr = this.arr;
        return fArr != null && (((int) fArr[0]) & 8) == 8;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getFlex() {
        return YogaNative.jni_YGNodeStyleGetFlexJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getFlexBasis() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetFlexBasisJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaFlexDirection getFlexDirection() {
        return YogaFlexDirection.fromInt(YogaNative.jni_YGNodeStyleGetFlexDirectionJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getFlexGrow() {
        return YogaNative.jni_YGNodeStyleGetFlexGrowJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getFlexShrink() {
        return YogaNative.jni_YGNodeStyleGetFlexShrinkJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetHeightJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaJustify getJustifyContent() {
        return YogaJustify.fromInt(YogaNative.jni_YGNodeStyleGetJustifyContentJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutBorder(YogaEdge yogaEdge) {
        float[] fArr = this.arr;
        if (fArr != null) {
            if ((((int) fArr[0]) & 4) == 4) {
                int i2 = (14 - ((((int) fArr[0]) & 1) == 1 ? 0 : 4)) - ((((int) fArr[0]) & 2) != 2 ? 4 : 0);
                switch (AnonymousClass1.$SwitchMap$com$jd$dynamic$yoga$YogaEdge[yogaEdge.ordinal()]) {
                    case 1:
                        return this.arr[i2];
                    case 2:
                        return this.arr[i2 + 1];
                    case 3:
                        return this.arr[i2 + 2];
                    case 4:
                        return this.arr[i2 + 3];
                    case 5:
                        return getLayoutDirection() == YogaDirection.RTL ? this.arr[i2 + 2] : this.arr[i2];
                    case 6:
                        return getLayoutDirection() == YogaDirection.RTL ? this.arr[i2] : this.arr[i2 + 2];
                    default:
                        throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
                }
            }
            return 0.0f;
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaDirection getLayoutDirection() {
        float[] fArr = this.arr;
        return YogaDirection.fromInt(fArr != null ? (int) fArr[5] : this.mLayoutDirection);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutHeight() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[2];
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutMargin(YogaEdge yogaEdge) {
        float[] fArr = this.arr;
        if (fArr == null || (((int) fArr[0]) & 1) != 1) {
            return 0.0f;
        }
        switch (AnonymousClass1.$SwitchMap$com$jd$dynamic$yoga$YogaEdge[yogaEdge.ordinal()]) {
            case 1:
                return this.arr[6];
            case 2:
                return this.arr[7];
            case 3:
                return this.arr[8];
            case 4:
                return this.arr[9];
            case 5:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[8] : this.arr[6];
            case 6:
                return getLayoutDirection() == YogaDirection.RTL ? this.arr[6] : this.arr[8];
            default:
                throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
        }
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutPadding(YogaEdge yogaEdge) {
        float[] fArr = this.arr;
        if (fArr != null) {
            if ((((int) fArr[0]) & 2) == 2) {
                int i2 = 10 - ((((int) fArr[0]) & 1) != 1 ? 4 : 0);
                switch (AnonymousClass1.$SwitchMap$com$jd$dynamic$yoga$YogaEdge[yogaEdge.ordinal()]) {
                    case 1:
                        return this.arr[i2];
                    case 2:
                        return this.arr[i2 + 1];
                    case 3:
                        return this.arr[i2 + 2];
                    case 4:
                        return this.arr[i2 + 3];
                    case 5:
                        return getLayoutDirection() == YogaDirection.RTL ? this.arr[i2 + 2] : this.arr[i2];
                    case 6:
                        return getLayoutDirection() == YogaDirection.RTL ? this.arr[i2] : this.arr[i2 + 2];
                    default:
                        throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
                }
            }
            return 0.0f;
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutWidth() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[1];
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutX() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[3];
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public float getLayoutY() {
        float[] fArr = this.arr;
        if (fArr != null) {
            return fArr[4];
        }
        return 0.0f;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getMargin(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMarginJNI(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getMaxHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxHeightJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getMaxWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMaxWidthJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getMinHeight() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinHeightJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getMinWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetMinWidthJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaOverflow getOverflow() {
        return YogaOverflow.fromInt(YogaNative.jni_YGNodeStyleGetOverflowJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    @Nullable
    public YogaNodeJNIBase getOwner() {
        return this.mOwner;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getPadding(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPaddingJNI(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    @Nullable
    @Deprecated
    public YogaNodeJNIBase getParent() {
        return getOwner();
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getPosition(YogaEdge yogaEdge) {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetPositionJNI(this.mNativePointer, yogaEdge.intValue()));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaPositionType getPositionType() {
        return YogaPositionType.fromInt(YogaNative.jni_YGNodeStyleGetPositionTypeJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaDirection getStyleDirection() {
        return YogaDirection.fromInt(YogaNative.jni_YGNodeStyleGetDirectionJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaValue getWidth() {
        return valueFromLong(YogaNative.jni_YGNodeStyleGetWidthJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaWrap getWrap() {
        return YogaWrap.fromInt(YogaNative.jni_YGNodeStyleGetFlexWrapJNI(this.mNativePointer));
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public boolean hasNewLayout() {
        float[] fArr = this.arr;
        return fArr != null ? (((int) fArr[0]) & 16) == 16 : this.mHasNewLayout;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public int indexOf(YogaNode yogaNode) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list == null) {
            return -1;
        }
        return list.indexOf(yogaNode);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public boolean isBaselineDefined() {
        return this.mBaselineFunction != null;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public boolean isDirty() {
        return YogaNative.jni_YGNodeIsDirtyJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public boolean isReferenceBaseline() {
        return YogaNative.jni_YGNodeIsReferenceBaselineJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void markLayoutSeen() {
        float[] fArr = this.arr;
        if (fArr != null) {
            fArr[0] = ((int) fArr[0]) & (-17);
        }
        this.mHasNewLayout = false;
    }

    @DoNotStrip
    public final long measure(float f2, int i2, float f3, int i3) {
        if (isMeasureDefined()) {
            return this.mMeasureFunction.measure(this, f2, YogaMeasureMode.fromInt(i2), f3, YogaMeasureMode.fromInt(i3));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void print() {
        YogaNative.jni_YGNodePrintJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public YogaNodeJNIBase removeChildAt(int i2) {
        List<YogaNodeJNIBase> list = this.mChildren;
        if (list != null) {
            YogaNodeJNIBase remove = list.remove(i2);
            remove.mOwner = null;
            YogaNative.jni_YGNodeRemoveChildJNI(this.mNativePointer, remove.mNativePointer);
            return remove;
        }
        throw new IllegalStateException("Trying to remove a child of a YogaNode that does not have children");
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void reset() {
        this.mMeasureFunction = null;
        this.mBaselineFunction = null;
        this.mData = null;
        this.arr = null;
        this.mHasNewLayout = true;
        this.mLayoutDirection = 0;
        YogaNative.jni_YGNodeResetJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setAlignContent(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignContentJNI(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setAlignItems(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignItemsJNI(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setAlignSelf(YogaAlign yogaAlign) {
        YogaNative.jni_YGNodeStyleSetAlignSelfJNI(this.mNativePointer, yogaAlign.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setAspectRatio(float f2) {
        YogaNative.jni_YGNodeStyleSetAspectRatioJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction) {
        this.mBaselineFunction = yogaBaselineFunction;
        YogaNative.jni_YGNodeSetHasBaselineFuncJNI(this.mNativePointer, yogaBaselineFunction != null);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setBorder(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetBorderJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setData(Object obj) {
        this.mData = obj;
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setDirection(YogaDirection yogaDirection) {
        YogaNative.jni_YGNodeStyleSetDirectionJNI(this.mNativePointer, yogaDirection.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setDisplay(YogaDisplay yogaDisplay) {
        YogaNative.jni_YGNodeStyleSetDisplayJNI(this.mNativePointer, yogaDisplay.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlex(float f2) {
        YogaNative.jni_YGNodeStyleSetFlexJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexBasis(float f2) {
        YogaNative.jni_YGNodeStyleSetFlexBasisJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexBasisAuto() {
        YogaNative.jni_YGNodeStyleSetFlexBasisAutoJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexBasisPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetFlexBasisPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexDirection(YogaFlexDirection yogaFlexDirection) {
        YogaNative.jni_YGNodeStyleSetFlexDirectionJNI(this.mNativePointer, yogaFlexDirection.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexGrow(float f2) {
        YogaNative.jni_YGNodeStyleSetFlexGrowJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setFlexShrink(float f2) {
        YogaNative.jni_YGNodeStyleSetFlexShrinkJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setHeight(float f2) {
        YogaNative.jni_YGNodeStyleSetHeightJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setHeightAuto() {
        YogaNative.jni_YGNodeStyleSetHeightAutoJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setHeightPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetHeightPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setIsReferenceBaseline(boolean z) {
        YogaNative.jni_YGNodeSetIsReferenceBaselineJNI(this.mNativePointer, z);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setJustifyContent(YogaJustify yogaJustify) {
        YogaNative.jni_YGNodeStyleSetJustifyContentJNI(this.mNativePointer, yogaJustify.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMargin(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetMarginJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMarginAuto(YogaEdge yogaEdge) {
        YogaNative.jni_YGNodeStyleSetMarginAutoJNI(this.mNativePointer, yogaEdge.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMarginPercent(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetMarginPercentJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMaxHeight(float f2) {
        YogaNative.jni_YGNodeStyleSetMaxHeightJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMaxHeightPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetMaxHeightPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMaxWidth(float f2) {
        YogaNative.jni_YGNodeStyleSetMaxWidthJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMaxWidthPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetMaxWidthPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction) {
        this.mMeasureFunction = yogaMeasureFunction;
        YogaNative.jni_YGNodeSetHasMeasureFuncJNI(this.mNativePointer, yogaMeasureFunction != null);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMinHeight(float f2) {
        YogaNative.jni_YGNodeStyleSetMinHeightJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMinHeightPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetMinHeightPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMinWidth(float f2) {
        YogaNative.jni_YGNodeStyleSetMinWidthJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setMinWidthPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetMinWidthPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setOverflow(YogaOverflow yogaOverflow) {
        YogaNative.jni_YGNodeStyleSetOverflowJNI(this.mNativePointer, yogaOverflow.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setPadding(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetPaddingJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setPaddingPercent(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetPaddingPercentJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setPosition(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetPositionJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setPositionPercent(YogaEdge yogaEdge, float f2) {
        YogaNative.jni_YGNodeStyleSetPositionPercentJNI(this.mNativePointer, yogaEdge.intValue(), f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setPositionType(YogaPositionType yogaPositionType) {
        YogaNative.jni_YGNodeStyleSetPositionTypeJNI(this.mNativePointer, yogaPositionType.intValue());
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setWidth(float f2) {
        YogaNative.jni_YGNodeStyleSetWidthJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setWidthAuto() {
        YogaNative.jni_YGNodeStyleSetWidthAutoJNI(this.mNativePointer);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setWidthPercent(float f2) {
        YogaNative.jni_YGNodeStyleSetWidthPercentJNI(this.mNativePointer, f2);
    }

    @Override // com.jd.dynamic.yoga.YogaNode
    public void setWrap(YogaWrap yogaWrap) {
        YogaNative.jni_YGNodeStyleSetFlexWrapJNI(this.mNativePointer, yogaWrap.intValue());
    }

    public void swapChildAt(YogaNode yogaNode, int i2) {
        YogaNodeJNIBase yogaNodeJNIBase = (YogaNodeJNIBase) yogaNode;
        this.mChildren.remove(i2).mOwner = null;
        this.mChildren.add(i2, yogaNodeJNIBase);
        yogaNodeJNIBase.mOwner = this;
        YogaNative.jni_YGNodeSwapChildJNI(this.mNativePointer, yogaNodeJNIBase.mNativePointer, i2);
    }
}
