package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintWidgetGroup mBelongingGroup;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    boolean mOptimizerMeasurable;
    boolean mOptimizerMeasured;
    ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f2 = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f2;
        this.mVerticalBiasPercent = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    /* JADX WARN: Removed duplicated region for block: B:160:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02e6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02f3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:193:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01c9 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyConstraints(LinearSystem linearSystem, boolean z, SolverVariable solverVariable, SolverVariable solverVariable2, DimensionBehaviour dimensionBehaviour, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, int i3, int i4, int i5, float f2, boolean z3, boolean z4, int i6, int i7, int i8, float f3, boolean z5) {
        boolean z6;
        int i9;
        int i10;
        int i11;
        boolean z7;
        int i12;
        SolverVariable createObjectVariable;
        SolverVariable createObjectVariable2;
        SolverVariable solverVariable3;
        int i13;
        int i14;
        int i15;
        int i16;
        SolverVariable solverVariable4;
        boolean z8;
        boolean z9;
        SolverVariable solverVariable5;
        SolverVariable solverVariable6;
        boolean z10;
        boolean z11;
        int i17;
        SolverVariable solverVariable7;
        SolverVariable solverVariable8;
        SolverVariable solverVariable9;
        boolean z12;
        boolean z13;
        int i18;
        int i19;
        int i20;
        int i21;
        boolean z14;
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(constraintAnchor.getTarget());
        SolverVariable createObjectVariable6 = linearSystem.createObjectVariable(constraintAnchor2.getTarget());
        if (linearSystem.graphOptimizer && constraintAnchor.getResolutionNode().state == 1 && constraintAnchor2.getResolutionNode().state == 1) {
            if (LinearSystem.getMetrics() != null) {
                LinearSystem.getMetrics().resolvedWidgets++;
            }
            constraintAnchor.getResolutionNode().addResolvedValue(linearSystem);
            constraintAnchor2.getResolutionNode().addResolvedValue(linearSystem);
            if (z4 || !z) {
                return;
            }
            linearSystem.addGreaterThan(solverVariable2, createObjectVariable4, 0, 6);
            return;
        }
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isConnected = constraintAnchor.isConnected();
        boolean isConnected2 = constraintAnchor2.isConnected();
        boolean isConnected3 = this.mCenter.isConnected();
        int i22 = isConnected2 ? (isConnected ? 1 : 0) + 1 : isConnected ? 1 : 0;
        if (isConnected3) {
            i22++;
        }
        int i23 = i22;
        int i24 = z3 ? 3 : i6;
        int i25 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour.ordinal()];
        boolean z15 = (i25 == 1 || i25 == 2 || i25 == 3 || i25 != 4 || i24 == 4) ? false : true;
        if (this.mVisibility == 8) {
            i9 = 0;
            z6 = false;
        } else {
            z6 = z15;
            i9 = i3;
        }
        if (z5) {
            if (!isConnected && !isConnected2 && !isConnected3) {
                linearSystem.addEquality(createObjectVariable3, i2);
            } else if (isConnected && !isConnected2) {
                i10 = 6;
                linearSystem.addEquality(createObjectVariable3, createObjectVariable5, constraintAnchor.getMargin(), 6);
                if (z6) {
                    if (z2) {
                        linearSystem.addEquality(createObjectVariable4, createObjectVariable3, 0, 3);
                        if (i4 > 0) {
                            linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, i4, 6);
                        }
                        if (i5 < Integer.MAX_VALUE) {
                            linearSystem.addLowerThan(createObjectVariable4, createObjectVariable3, i5, 6);
                        }
                    } else {
                        linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i9, i10);
                    }
                    i11 = i7;
                    i14 = i24;
                    i15 = i23;
                    solverVariable4 = createObjectVariable6;
                    solverVariable3 = createObjectVariable5;
                    z9 = z6;
                    i16 = i8;
                } else {
                    i11 = i7;
                    int i26 = i8;
                    if (i11 == -2) {
                        i11 = i9;
                    }
                    if (i26 == -2) {
                        i26 = i9;
                    }
                    if (i11 > 0) {
                        linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, i11, 6);
                        i9 = Math.max(i9, i11);
                    }
                    if (i26 > 0) {
                        linearSystem.addLowerThan(createObjectVariable4, createObjectVariable3, i26, 6);
                        i9 = Math.min(i9, i26);
                    }
                    if (i24 != 1) {
                        z7 = z6;
                        if (i24 == 2) {
                            ConstraintAnchor.Type type = constraintAnchor.getType();
                            ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
                            if (type != type2) {
                                i12 = i24;
                                if (constraintAnchor.getType() != ConstraintAnchor.Type.BOTTOM) {
                                    createObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                                    createObjectVariable2 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                                    int i27 = i12;
                                    solverVariable3 = createObjectVariable5;
                                    i13 = i9;
                                    i14 = i27;
                                    i15 = i23;
                                    i16 = i26;
                                    solverVariable4 = createObjectVariable6;
                                    linearSystem.addConstraint(linearSystem.createRow().createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f3));
                                    z8 = false;
                                    if (z8) {
                                    }
                                    z9 = z8;
                                }
                            } else {
                                i12 = i24;
                            }
                            createObjectVariable = linearSystem.createObjectVariable(this.mParent.getAnchor(type2));
                            createObjectVariable2 = linearSystem.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                            int i272 = i12;
                            solverVariable3 = createObjectVariable5;
                            i13 = i9;
                            i14 = i272;
                            i15 = i23;
                            i16 = i26;
                            solverVariable4 = createObjectVariable6;
                            linearSystem.addConstraint(linearSystem.createRow().createRowDimensionRatio(createObjectVariable4, createObjectVariable3, createObjectVariable2, createObjectVariable, f3));
                            z8 = false;
                            if (z8) {
                            }
                            z9 = z8;
                        }
                    } else if (z) {
                        linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i9, 6);
                        i14 = i24;
                        i15 = i23;
                        solverVariable4 = createObjectVariable6;
                        solverVariable3 = createObjectVariable5;
                        z7 = z6;
                        i13 = i9;
                        i16 = i26;
                        z8 = z7;
                        if (z8 || i15 == 2 || z3) {
                            z9 = z8;
                        } else {
                            int max = Math.max(i11, i13);
                            if (i16 > 0) {
                                max = Math.min(i16, max);
                            }
                            linearSystem.addEquality(createObjectVariable4, createObjectVariable3, max, 6);
                            z9 = false;
                        }
                    } else if (z4) {
                        z7 = z6;
                        linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i9, 4);
                    } else {
                        z7 = z6;
                        linearSystem.addEquality(createObjectVariable4, createObjectVariable3, i9, 1);
                    }
                    i14 = i24;
                    i15 = i23;
                    i16 = i26;
                    solverVariable4 = createObjectVariable6;
                    solverVariable3 = createObjectVariable5;
                    i13 = i9;
                    z8 = z7;
                    if (z8) {
                    }
                    z9 = z8;
                }
                if (z5 || z4) {
                    if (i15 < 2 || !z) {
                    }
                    linearSystem.addGreaterThan(createObjectVariable3, solverVariable, 0, 6);
                    linearSystem.addGreaterThan(solverVariable2, createObjectVariable4, 0, 6);
                    return;
                }
                if (isConnected || isConnected2 || isConnected3) {
                    if (!isConnected || isConnected2) {
                        if (!isConnected && isConnected2) {
                            linearSystem.addEquality(createObjectVariable4, solverVariable4, -constraintAnchor2.getMargin(), 6);
                            if (z) {
                                linearSystem.addGreaterThan(createObjectVariable3, solverVariable, 0, 5);
                            }
                        } else if (isConnected && isConnected2) {
                            if (z9) {
                                solverVariable5 = solverVariable4;
                                if (z && i4 == 0) {
                                    linearSystem.addGreaterThan(createObjectVariable4, createObjectVariable3, 0, 6);
                                }
                                if (i14 == 0) {
                                    if (i16 > 0 || i11 > 0) {
                                        i21 = 4;
                                        z14 = true;
                                    } else {
                                        i21 = 6;
                                        z14 = false;
                                    }
                                    solverVariable6 = solverVariable3;
                                    linearSystem.addEquality(createObjectVariable3, solverVariable6, constraintAnchor.getMargin(), i21);
                                    linearSystem.addEquality(createObjectVariable4, solverVariable5, -constraintAnchor2.getMargin(), i21);
                                    z10 = i16 > 0 || i11 > 0;
                                    z11 = z14;
                                    i17 = 5;
                                } else {
                                    int i28 = i14;
                                    solverVariable6 = solverVariable3;
                                    if (i28 == 1) {
                                        z10 = true;
                                        z11 = true;
                                        i17 = 6;
                                    } else if (i28 == 3) {
                                        int i29 = (z3 || this.mResolvedDimensionRatioSide == -1 || i16 > 0) ? 4 : 6;
                                        linearSystem.addEquality(createObjectVariable3, solverVariable6, constraintAnchor.getMargin(), i29);
                                        linearSystem.addEquality(createObjectVariable4, solverVariable5, -constraintAnchor2.getMargin(), i29);
                                        z10 = true;
                                        z11 = true;
                                        i17 = 5;
                                        if (z10) {
                                            solverVariable8 = solverVariable5;
                                            solverVariable7 = solverVariable6;
                                            solverVariable9 = createObjectVariable4;
                                            linearSystem.addCentering(createObjectVariable3, solverVariable6, constraintAnchor.getMargin(), f2, solverVariable5, createObjectVariable4, constraintAnchor2.getMargin(), i17);
                                            boolean z16 = constraintAnchor.mTarget.mOwner instanceof Barrier;
                                            boolean z17 = constraintAnchor2.mTarget.mOwner instanceof Barrier;
                                            if (z16 && !z17) {
                                                z12 = z;
                                                i18 = 6;
                                                i19 = 5;
                                                z13 = true;
                                            } else if (!z16 && z17) {
                                                z13 = z;
                                                i18 = 5;
                                                i19 = 6;
                                                z12 = true;
                                            }
                                            if (z11) {
                                                i18 = 6;
                                                i19 = 6;
                                            }
                                            if ((!z9 && z12) || z11) {
                                                linearSystem.addGreaterThan(createObjectVariable3, solverVariable7, constraintAnchor.getMargin(), i19);
                                            }
                                            if ((!z9 && z13) || z11) {
                                                linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                                            }
                                            i20 = 0;
                                            if (z) {
                                                linearSystem.addGreaterThan(createObjectVariable3, solverVariable, 0, 6);
                                            }
                                            if (z) {
                                                linearSystem.addGreaterThan(solverVariable2, solverVariable9, i20, 6);
                                                return;
                                            }
                                            return;
                                        }
                                        solverVariable7 = solverVariable6;
                                        solverVariable8 = solverVariable5;
                                        solverVariable9 = createObjectVariable4;
                                        z12 = z;
                                        z13 = z12;
                                        i18 = 5;
                                        i19 = 5;
                                        if (z11) {
                                        }
                                        if (!z9) {
                                            linearSystem.addGreaterThan(createObjectVariable3, solverVariable7, constraintAnchor.getMargin(), i19);
                                            if (!z9) {
                                                linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                                                i20 = 0;
                                                if (z) {
                                                }
                                                if (z) {
                                                }
                                            }
                                            linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                                            i20 = 0;
                                            if (z) {
                                            }
                                            if (z) {
                                            }
                                        }
                                        linearSystem.addGreaterThan(createObjectVariable3, solverVariable7, constraintAnchor.getMargin(), i19);
                                        if (!z9) {
                                        }
                                        linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                                        i20 = 0;
                                        if (z) {
                                        }
                                        if (z) {
                                        }
                                    } else {
                                        z10 = false;
                                    }
                                }
                                if (z10) {
                                }
                                z12 = z;
                                z13 = z12;
                                i18 = 5;
                                i19 = 5;
                                if (z11) {
                                }
                                if (!z9) {
                                }
                                linearSystem.addGreaterThan(createObjectVariable3, solverVariable7, constraintAnchor.getMargin(), i19);
                                if (!z9) {
                                }
                                linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                                i20 = 0;
                                if (z) {
                                }
                                if (z) {
                                }
                            } else {
                                solverVariable5 = solverVariable4;
                                solverVariable6 = solverVariable3;
                                z10 = true;
                            }
                            z11 = false;
                            i17 = 5;
                            if (z10) {
                            }
                            z12 = z;
                            z13 = z12;
                            i18 = 5;
                            i19 = 5;
                            if (z11) {
                            }
                            if (!z9) {
                            }
                            linearSystem.addGreaterThan(createObjectVariable3, solverVariable7, constraintAnchor.getMargin(), i19);
                            if (!z9) {
                            }
                            linearSystem.addLowerThan(solverVariable9, solverVariable8, -constraintAnchor2.getMargin(), i18);
                            i20 = 0;
                            if (z) {
                            }
                            if (z) {
                            }
                        }
                    } else if (z) {
                        linearSystem.addGreaterThan(solverVariable2, createObjectVariable4, 0, 5);
                    }
                } else if (z) {
                    linearSystem.addGreaterThan(solverVariable2, createObjectVariable4, 0, 5);
                }
                solverVariable9 = createObjectVariable4;
                i20 = 0;
                if (z) {
                }
            }
        }
        i10 = 6;
        if (z6) {
        }
        if (z5) {
        }
        if (i15 < 2) {
        }
    }

    private boolean isChainHead(int i2) {
        int i3 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        if (constraintAnchorArr[i3].mTarget != null && constraintAnchorArr[i3].mTarget.mTarget != constraintAnchorArr[i3]) {
            int i4 = i3 + 1;
            if (constraintAnchorArr[i4].mTarget != null && constraintAnchorArr[i4].mTarget.mTarget == constraintAnchorArr[i4]) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0196 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0233 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i2;
        char c2;
        int i3;
        int i4;
        int i5;
        boolean z5;
        boolean z6;
        DimensionBehaviour dimensionBehaviour;
        boolean z7;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        SolverVariable solverVariable3;
        LinearSystem linearSystem2;
        boolean z8;
        SolverVariable solverVariable4;
        ConstraintWidget constraintWidget;
        int i6;
        LinearSystem linearSystem3;
        SolverVariable solverVariable5;
        int i7;
        int i8;
        boolean isInHorizontalChain;
        boolean isInVerticalChain;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget2 = this.mParent;
        if (constraintWidget2 != null) {
            boolean z9 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            boolean z10 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                isInHorizontalChain = true;
            } else {
                isInHorizontalChain = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                isInVerticalChain = true;
            } else {
                isInVerticalChain = isInVerticalChain();
            }
            if (z9 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mRight), createObjectVariable2, 0, 1);
            }
            if (z10 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(this.mParent.mBottom), createObjectVariable4, 0, 1);
            }
            z2 = z10;
            z = z9;
            z3 = isInHorizontalChain;
            z4 = isInVerticalChain;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        int i9 = this.mWidth;
        int i10 = this.mMinWidth;
        if (i9 >= i10) {
            i10 = i9;
        }
        int i11 = this.mHeight;
        int i12 = this.mMinHeight;
        if (i11 >= i12) {
            i12 = i11;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour3 = DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z11 = dimensionBehaviour2 != dimensionBehaviour3;
        boolean z12 = dimensionBehaviourArr[1] != dimensionBehaviour3;
        int i13 = this.mDimensionRatioSide;
        this.mResolvedDimensionRatioSide = i13;
        float f2 = this.mDimensionRatio;
        this.mResolvedDimensionRatio = f2;
        int i14 = this.mMatchConstraintDefaultWidth;
        int i15 = i10;
        int i16 = this.mMatchConstraintDefaultHeight;
        if (f2 > 0.0f) {
            i2 = i12;
            if (this.mVisibility != 8) {
                if (dimensionBehaviourArr[0] == dimensionBehaviour3 && i14 == 0) {
                    i14 = 3;
                }
                if (dimensionBehaviourArr[1] == dimensionBehaviour3 && i16 == 0) {
                    i16 = 3;
                }
                if (dimensionBehaviourArr[0] == dimensionBehaviour3 && dimensionBehaviourArr[1] == dimensionBehaviour3) {
                    i8 = 3;
                    if (i14 == 3 && i16 == 3) {
                        setupDimensionRatio(z, z2, z11, z12);
                        c2 = 0;
                        i3 = i14;
                        i4 = i16;
                        i5 = i15;
                        z5 = true;
                        int[] iArr = this.mResolvedMatchConstraintDefault;
                        iArr[c2] = i3;
                        iArr[1] = i4;
                        if (!z5 && ((i7 = this.mResolvedDimensionRatioSide) == 0 || i7 == -1)) {
                            z6 = true;
                            DimensionBehaviour dimensionBehaviour4 = this.mListDimensionBehaviors[0];
                            DimensionBehaviour dimensionBehaviour5 = DimensionBehaviour.WRAP_CONTENT;
                            boolean z13 = dimensionBehaviour4 != dimensionBehaviour5 && (this instanceof ConstraintWidgetContainer);
                            boolean z14 = !this.mCenter.isConnected();
                            if (this.mHorizontalResolution != 2) {
                                ConstraintWidget constraintWidget3 = this.mParent;
                                if (constraintWidget3 != null) {
                                    linearSystem3 = linearSystem;
                                    solverVariable5 = linearSystem3.createObjectVariable(constraintWidget3.mRight);
                                } else {
                                    linearSystem3 = linearSystem;
                                    solverVariable5 = null;
                                }
                                ConstraintWidget constraintWidget4 = this.mParent;
                                z7 = z2;
                                solverVariable = createObjectVariable5;
                                dimensionBehaviour = dimensionBehaviour5;
                                solverVariable2 = createObjectVariable3;
                                solverVariable3 = createObjectVariable2;
                                applyConstraints(linearSystem, z, constraintWidget4 != null ? linearSystem3.createObjectVariable(constraintWidget4.mLeft) : null, solverVariable5, this.mListDimensionBehaviors[0], z13, this.mLeft, this.mRight, this.mX, i5, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, z6, z3, i3, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, z14);
                            } else {
                                dimensionBehaviour = dimensionBehaviour5;
                                z7 = z2;
                                solverVariable = createObjectVariable5;
                                solverVariable2 = createObjectVariable3;
                                solverVariable3 = createObjectVariable2;
                            }
                            if (this.mVerticalResolution == 2) {
                                return;
                            }
                            boolean z15 = this.mListDimensionBehaviors[1] == dimensionBehaviour && (this instanceof ConstraintWidgetContainer);
                            boolean z16 = z5 && ((i6 = this.mResolvedDimensionRatioSide) == 1 || i6 == -1);
                            if (this.mBaselineDistance <= 0) {
                                linearSystem2 = linearSystem;
                            } else if (this.mBaseline.getResolutionNode().state == 1) {
                                linearSystem2 = linearSystem;
                                this.mBaseline.getResolutionNode().addResolvedValue(linearSystem2);
                            } else {
                                linearSystem2 = linearSystem;
                                SolverVariable solverVariable6 = solverVariable;
                                solverVariable4 = solverVariable2;
                                linearSystem2.addEquality(solverVariable6, solverVariable4, getBaselineDistance(), 6);
                                ConstraintAnchor constraintAnchor = this.mBaseline.mTarget;
                                if (constraintAnchor != null) {
                                    linearSystem2.addEquality(solverVariable6, linearSystem2.createObjectVariable(constraintAnchor), 0, 6);
                                    z8 = false;
                                    ConstraintWidget constraintWidget5 = this.mParent;
                                    SolverVariable createObjectVariable6 = constraintWidget5 != null ? linearSystem2.createObjectVariable(constraintWidget5.mBottom) : null;
                                    ConstraintWidget constraintWidget6 = this.mParent;
                                    SolverVariable solverVariable7 = solverVariable4;
                                    applyConstraints(linearSystem, z7, constraintWidget6 != null ? linearSystem2.createObjectVariable(constraintWidget6.mTop) : null, createObjectVariable6, this.mListDimensionBehaviors[1], z15, this.mTop, this.mBottom, this.mY, i2, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z16, z4, i4, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                                    if (z5) {
                                        constraintWidget = this;
                                        if (constraintWidget.mResolvedDimensionRatioSide == 1) {
                                            linearSystem.addRatio(createObjectVariable4, solverVariable7, solverVariable3, createObjectVariable, constraintWidget.mResolvedDimensionRatio, 6);
                                        } else {
                                            linearSystem.addRatio(solverVariable3, createObjectVariable, createObjectVariable4, solverVariable7, constraintWidget.mResolvedDimensionRatio, 6);
                                        }
                                    } else {
                                        constraintWidget = this;
                                    }
                                    if (constraintWidget.mCenter.isConnected()) {
                                        linearSystem.addCenterPoint(constraintWidget, constraintWidget.mCenter.getTarget().getOwner(), (float) Math.toRadians(constraintWidget.mCircleConstraintAngle + 90.0f), constraintWidget.mCenter.getMargin());
                                        return;
                                    }
                                    return;
                                }
                                z8 = z14;
                                ConstraintWidget constraintWidget52 = this.mParent;
                                if (constraintWidget52 != null) {
                                }
                                ConstraintWidget constraintWidget62 = this.mParent;
                                SolverVariable solverVariable72 = solverVariable4;
                                applyConstraints(linearSystem, z7, constraintWidget62 != null ? linearSystem2.createObjectVariable(constraintWidget62.mTop) : null, createObjectVariable6, this.mListDimensionBehaviors[1], z15, this.mTop, this.mBottom, this.mY, i2, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z16, z4, i4, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                                if (z5) {
                                }
                                if (constraintWidget.mCenter.isConnected()) {
                                }
                            }
                            solverVariable4 = solverVariable2;
                            z8 = z14;
                            ConstraintWidget constraintWidget522 = this.mParent;
                            if (constraintWidget522 != null) {
                            }
                            ConstraintWidget constraintWidget622 = this.mParent;
                            SolverVariable solverVariable722 = solverVariable4;
                            applyConstraints(linearSystem, z7, constraintWidget622 != null ? linearSystem2.createObjectVariable(constraintWidget622.mTop) : null, createObjectVariable6, this.mListDimensionBehaviors[1], z15, this.mTop, this.mBottom, this.mY, i2, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z16, z4, i4, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, z8);
                            if (z5) {
                            }
                            if (constraintWidget.mCenter.isConnected()) {
                            }
                        }
                        z6 = false;
                        DimensionBehaviour dimensionBehaviour42 = this.mListDimensionBehaviors[0];
                        DimensionBehaviour dimensionBehaviour52 = DimensionBehaviour.WRAP_CONTENT;
                        if (dimensionBehaviour42 != dimensionBehaviour52) {
                        }
                        boolean z142 = !this.mCenter.isConnected();
                        if (this.mHorizontalResolution != 2) {
                        }
                        if (this.mVerticalResolution == 2) {
                        }
                    }
                } else {
                    i8 = 3;
                }
                if (dimensionBehaviourArr[0] == dimensionBehaviour3 && i14 == i8) {
                    this.mResolvedDimensionRatioSide = 0;
                    i5 = (int) (f2 * i11);
                    if (dimensionBehaviourArr[1] != dimensionBehaviour3) {
                        i4 = i16;
                        c2 = 0;
                        z5 = false;
                        i3 = 4;
                    } else {
                        i3 = i14;
                        i4 = i16;
                        c2 = 0;
                        z5 = true;
                    }
                } else {
                    if (dimensionBehaviourArr[1] == dimensionBehaviour3 && i16 == 3) {
                        this.mResolvedDimensionRatioSide = 1;
                        if (i13 == -1) {
                            this.mResolvedDimensionRatio = 1.0f / f2;
                        }
                        c2 = 0;
                        DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[0];
                        i2 = (int) (this.mResolvedDimensionRatio * i9);
                        i3 = i14;
                        if (dimensionBehaviour6 != dimensionBehaviour3) {
                            i5 = i15;
                            z5 = false;
                            i4 = 4;
                        }
                        i4 = i16;
                        i5 = i15;
                        z5 = true;
                    }
                    c2 = 0;
                    i3 = i14;
                    i4 = i16;
                    i5 = i15;
                    z5 = true;
                }
                int[] iArr2 = this.mResolvedMatchConstraintDefault;
                iArr2[c2] = i3;
                iArr2[1] = i4;
                if (!z5) {
                    z6 = true;
                    DimensionBehaviour dimensionBehaviour422 = this.mListDimensionBehaviors[0];
                    DimensionBehaviour dimensionBehaviour522 = DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour422 != dimensionBehaviour522) {
                    }
                    boolean z1422 = !this.mCenter.isConnected();
                    if (this.mHorizontalResolution != 2) {
                    }
                    if (this.mVerticalResolution == 2) {
                    }
                }
                z6 = false;
                DimensionBehaviour dimensionBehaviour4222 = this.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour5222 = DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour4222 != dimensionBehaviour5222) {
                }
                boolean z14222 = !this.mCenter.isConnected();
                if (this.mHorizontalResolution != 2) {
                }
                if (this.mVerticalResolution == 2) {
                }
            }
        } else {
            i2 = i12;
        }
        c2 = 0;
        i3 = i14;
        i4 = i16;
        i5 = i15;
        z5 = false;
        int[] iArr22 = this.mResolvedMatchConstraintDefault;
        iArr22[c2] = i3;
        iArr22[1] = i4;
        if (!z5) {
        }
        z6 = false;
        DimensionBehaviour dimensionBehaviour42222 = this.mListDimensionBehaviors[0];
        DimensionBehaviour dimensionBehaviour52222 = DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour42222 != dimensionBehaviour52222) {
        }
        boolean z142222 = !this.mCenter.isConnected();
        if (this.mHorizontalResolution != 2) {
        }
        if (this.mVerticalResolution == 2) {
        }
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void analyze(int i2) {
        Optimizer.analyze(i2, this);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, int i3) {
        connect(constraintAnchor, constraintAnchor2, i2, ConstraintAnchor.Strength.STRONG, i3);
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        immediateConnect(type, constraintWidget, type, i2, 0);
        this.mCircleConstraintAngle = f2;
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintAnchor constraintAnchor = anchors.get(i2);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList<ConstraintAnchor> anchors = getAnchors();
        int size = anchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintAnchor constraintAnchor = anchors.get(i2);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void forceUpdateDrawPosition() {
        int i2 = this.mX;
        int i3 = this.mY;
        this.mDrawX = i2;
        this.mDrawY = i3;
        this.mDrawWidth = (this.mWidth + i2) - i2;
        this.mDrawHeight = (this.mHeight + i3) - i3;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public float getBiasPercent(int i2) {
        if (i2 == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i2 == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public DimensionBehaviour getDimensionBehaviour(int i2) {
        if (i2 == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i2 == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (isInHorizontalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    int getInternalDrawX() {
        return this.mDrawX;
    }

    int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getLeft() {
        return getX();
    }

    public int getLength(int i2) {
        if (i2 == 0) {
            return getWidth();
        }
        if (i2 == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizerWrapHeight() {
        int i2;
        int i3 = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i2 = Math.max(this.mMatchConstraintMinHeight, i3);
            } else {
                i2 = this.mMatchConstraintMinHeight;
                if (i2 > 0) {
                    this.mHeight = i2;
                } else {
                    i2 = 0;
                }
            }
            int i4 = this.mMatchConstraintMaxHeight;
            return (i4 <= 0 || i4 >= i2) ? i2 : i4;
        }
        return i3;
    }

    public int getOptimizerWrapWidth() {
        int i2;
        int i3 = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                i2 = Math.max(this.mMatchConstraintMinWidth, i3);
            } else {
                i2 = this.mMatchConstraintMinWidth;
                if (i2 > 0) {
                    this.mWidth = i2;
                } else {
                    i2 = 0;
                }
            }
            int i4 = this.mMatchConstraintMaxWidth;
            return (i4 <= 0 || i4 >= i2) ? i2 : i4;
        }
        return i3;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRelativePositioning(int i2) {
        if (i2 == 0) {
            return this.mRelX;
        }
        if (i2 == 1) {
            return this.mRelY;
        }
        return 0;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        if (constraintWidget instanceof WidgetContainer) {
            return (WidgetContainer) constraintWidget;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getTop() {
        return getY();
    }

    public String getType() {
        return this.mType;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (isInVerticalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i3) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i2, i3, ConstraintAnchor.Strength.STRONG, 0, true);
    }

    public boolean isFullyResolved() {
        return this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
        }
        return true;
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mBottom;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
        }
        return true;
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        ConstraintWidget constraintWidget;
        return (this instanceof ConstraintWidgetContainer) && ((constraintWidget = this.mParent) == null || !(constraintWidget instanceof ConstraintWidgetContainer));
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        float f2 = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f2;
        this.mVerticalBiasPercent = f2;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        ResolutionDimension resolutionDimension = this.mResolutionWidth;
        if (resolutionDimension != null) {
            resolutionDimension.reset();
        }
        ResolutionDimension resolutionDimension2 = this.mResolutionHeight;
        if (resolutionDimension2 != null) {
            resolutionDimension2.reset();
        }
        this.mBelongingGroup = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (this instanceof ConstraintWidgetContainer) {
            return;
        }
        DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.MATCH_CONSTRAINT;
        if (horizontalDimensionBehaviour == dimensionBehaviour) {
            if (getWidth() == getWrapWidth()) {
                setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getWidth() > getMinWidth()) {
                setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        if (getVerticalDimensionBehaviour() == dimensionBehaviour) {
            if (getHeight() == getWrapHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
            } else if (getHeight() > getMinHeight()) {
                setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintAnchor == anchor5) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                anchor.reset();
                anchor2.reset();
            }
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor6) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                anchor.reset();
                anchor2.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor7) {
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor != anchor && constraintAnchor != anchor2) {
            if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
        } else if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
            anchor5.reset();
        }
        constraintAnchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.mAnchors.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mAnchors.get(i2).reset();
        }
    }

    public void resetResolutionNodes() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.mListAnchors[i2].getResolutionNode().reset();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    public void resolve() {
    }

    public void setBaselineDistance(int i2) {
        this.mBaselineDistance = i2;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i2) {
        if (i2 >= 0) {
            this.mContainerItemSkip = i2;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
        }
    }

    public void setDimension(int i2, int i3) {
        this.mWidth = i2;
        int i4 = this.mMinWidth;
        if (i2 < i4) {
            this.mWidth = i4;
        }
        this.mHeight = i3;
        int i5 = this.mMinHeight;
        if (i3 < i5) {
            this.mHeight = i5;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:39:0x0085). Please submit an issue!!! */
    public void setDimensionRatio(String str) {
        float f2;
        int i2 = 0;
        if (str != null && str.length() != 0) {
            int i3 = -1;
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i4 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i3 = 0;
                } else if (substring.equalsIgnoreCase(DYConstants.LETTER_H)) {
                    i3 = 1;
                }
                i4 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                String substring2 = str.substring(i4, indexOf2);
                String substring3 = str.substring(indexOf2 + 1);
                if (substring2.length() > 0 && substring3.length() > 0) {
                    float parseFloat = Float.parseFloat(substring2);
                    float parseFloat2 = Float.parseFloat(substring3);
                    if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                        if (i3 == 1) {
                            f2 = Math.abs(parseFloat2 / parseFloat);
                        } else {
                            f2 = Math.abs(parseFloat / parseFloat2);
                        }
                    }
                }
                f2 = 0.0f;
            } else {
                String substring4 = str.substring(i4);
                if (substring4.length() > 0) {
                    f2 = Float.parseFloat(substring4);
                }
                f2 = 0.0f;
            }
            i2 = (f2 > i2 ? 1 : (f2 == i2 ? 0 : -1));
            if (i2 > 0) {
                this.mDimensionRatio = f2;
                this.mDimensionRatioSide = i3;
                return;
            }
            return;
        }
        this.mDimensionRatio = 0.0f;
    }

    public void setDrawHeight(int i2) {
        this.mDrawHeight = i2;
    }

    public void setDrawOrigin(int i2, int i3) {
        int i4 = i2 - this.mOffsetX;
        this.mDrawX = i4;
        int i5 = i3 - this.mOffsetY;
        this.mDrawY = i5;
        this.mX = i4;
        this.mY = i5;
    }

    public void setDrawWidth(int i2) {
        this.mDrawWidth = i2;
    }

    public void setDrawX(int i2) {
        int i3 = i2 - this.mOffsetX;
        this.mDrawX = i3;
        this.mX = i3;
    }

    public void setDrawY(int i2) {
        int i3 = i2 - this.mOffsetY;
        this.mDrawY = i3;
        this.mY = i3;
    }

    public void setFrame(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        this.mX = i2;
        this.mY = i3;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i8 < (i7 = this.mWidth)) {
            i8 = i7;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i9 < (i6 = this.mHeight)) {
            i9 = i6;
        }
        this.mWidth = i8;
        this.mHeight = i9;
        int i10 = this.mMinHeight;
        if (i9 < i10) {
            this.mHeight = i10;
        }
        int i11 = this.mMinWidth;
        if (i8 < i11) {
            this.mWidth = i11;
        }
        this.mOptimizerMeasured = true;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()];
        if (i3 == 1) {
            this.mLeft.mGoneMargin = i2;
        } else if (i3 == 2) {
            this.mTop.mGoneMargin = i2;
        } else if (i3 == 3) {
            this.mRight.mGoneMargin = i2;
        } else if (i3 != 4) {
        } else {
            this.mBottom.mGoneMargin = i2;
        }
    }

    public void setHeight(int i2) {
        this.mHeight = i2;
        int i3 = this.mMinHeight;
        if (i2 < i3) {
            this.mHeight = i3;
        }
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void setHorizontalBiasPercent(float f2) {
        this.mHorizontalBiasPercent = f2;
    }

    public void setHorizontalChainStyle(int i2) {
        this.mHorizontalChainStyle = i2;
    }

    public void setHorizontalDimension(int i2, int i3) {
        this.mX = i2;
        int i4 = i3 - i2;
        this.mWidth = i4;
        int i5 = this.mMinWidth;
        if (i4 < i5) {
            this.mWidth = i5;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setHorizontalMatchStyle(int i2, int i3, int i4, float f2) {
        this.mMatchConstraintDefaultWidth = i2;
        this.mMatchConstraintMinWidth = i3;
        this.mMatchConstraintMaxWidth = i4;
        this.mMatchConstraintPercentWidth = f2;
        if (f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultWidth = 2;
    }

    public void setHorizontalWeight(float f2) {
        this.mWeight[0] = f2;
    }

    public void setLength(int i2, int i3) {
        if (i3 == 0) {
            setWidth(i2);
        } else if (i3 == 1) {
            setHeight(i2);
        }
    }

    public void setMaxHeight(int i2) {
        this.mMaxDimension[1] = i2;
    }

    public void setMaxWidth(int i2) {
        this.mMaxDimension[0] = i2;
    }

    public void setMinHeight(int i2) {
        if (i2 < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i2;
        }
    }

    public void setMinWidth(int i2) {
        if (i2 < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i2;
        }
    }

    public void setOffset(int i2, int i3) {
        this.mOffsetX = i2;
        this.mOffsetY = i3;
    }

    public void setOrigin(int i2, int i3) {
        this.mX = i2;
        this.mY = i3;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRelativePositioning(int i2, int i3) {
        if (i3 == 0) {
            this.mRelX = i2;
        } else if (i3 == 1) {
            this.mRelY = i2;
        }
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVerticalBiasPercent(float f2) {
        this.mVerticalBiasPercent = f2;
    }

    public void setVerticalChainStyle(int i2) {
        this.mVerticalChainStyle = i2;
    }

    public void setVerticalDimension(int i2, int i3) {
        this.mY = i2;
        int i4 = i3 - i2;
        this.mHeight = i4;
        int i5 = this.mMinHeight;
        if (i4 < i5) {
            this.mHeight = i5;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public void setVerticalMatchStyle(int i2, int i3, int i4, float f2) {
        this.mMatchConstraintDefaultHeight = i2;
        this.mMatchConstraintMinHeight = i3;
        this.mMatchConstraintMaxHeight = i4;
        this.mMatchConstraintPercentHeight = f2;
        if (f2 >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultHeight = 2;
    }

    public void setVerticalWeight(float f2) {
        this.mWeight[1] = f2;
    }

    public void setVisibility(int i2) {
        this.mVisibility = i2;
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
        int i3 = this.mMinWidth;
        if (i2 < i3) {
            this.mWidth = i3;
        }
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setWrapHeight(int i2) {
        this.mWrapHeight = i2;
    }

    public void setWrapWidth(int i2) {
        this.mWrapWidth = i2;
    }

    public void setX(int i2) {
        this.mX = i2;
    }

    public void setY(int i2) {
        this.mY = i2;
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i2 = this.mMatchConstraintMinWidth;
            if (i2 > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i2 == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = "type: " + this.mType + LangUtils.SINGLE_SPACE;
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + LangUtils.SINGLE_SPACE;
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    public void updateDrawPosition() {
        int i2 = this.mX;
        int i3 = this.mY;
        this.mDrawX = i2;
        this.mDrawY = i3;
        this.mDrawWidth = (this.mWidth + i2) - i2;
        this.mDrawHeight = (this.mHeight + i3) - i3;
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        int i2 = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i2 < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void updateResolutionNodes() {
        for (int i2 = 0; i2 < 6; i2++) {
            this.mListAnchors[i2].getResolutionNode().update();
        }
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        connect(constraintAnchor, constraintAnchor2, i2, ConstraintAnchor.Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2, ConstraintAnchor.Strength strength, int i3) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i2, strength, i3);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2) {
        connect(type, constraintWidget, type2, i2, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0, ConstraintAnchor.Strength.STRONG);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, ConstraintAnchor.Strength strength) {
        connect(type, constraintWidget, type2, i2, strength, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, ConstraintAnchor.Strength strength, int i3) {
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        ConstraintAnchor.Type type5;
        boolean z;
        ConstraintAnchor.Type type6 = ConstraintAnchor.Type.CENTER;
        int i4 = 0;
        if (type == type6) {
            if (type2 == type6) {
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.LEFT;
                ConstraintAnchor anchor = getAnchor(type7);
                ConstraintAnchor.Type type8 = ConstraintAnchor.Type.RIGHT;
                ConstraintAnchor anchor2 = getAnchor(type8);
                ConstraintAnchor.Type type9 = ConstraintAnchor.Type.TOP;
                ConstraintAnchor anchor3 = getAnchor(type9);
                ConstraintAnchor.Type type10 = ConstraintAnchor.Type.BOTTOM;
                ConstraintAnchor anchor4 = getAnchor(type10);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    type5 = type10;
                    connect(type7, constraintWidget, type7, 0, strength, i3);
                    connect(type8, constraintWidget, type8, 0, strength, i3);
                    z = true;
                } else {
                    type5 = type10;
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(type9, constraintWidget, type9, 0, strength, i3);
                    connect(type5, constraintWidget, type5, 0, strength, i3);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(type6).connect(constraintWidget.getAnchor(type6), 0, i3);
                    return;
                } else if (z) {
                    ConstraintAnchor.Type type11 = ConstraintAnchor.Type.CENTER_X;
                    getAnchor(type11).connect(constraintWidget.getAnchor(type11), 0, i3);
                    return;
                } else if (z2) {
                    ConstraintAnchor.Type type12 = ConstraintAnchor.Type.CENTER_Y;
                    getAnchor(type12).connect(constraintWidget.getAnchor(type12), 0, i3);
                    return;
                } else {
                    return;
                }
            }
            ConstraintAnchor.Type type13 = ConstraintAnchor.Type.LEFT;
            if (type2 != type13 && type2 != ConstraintAnchor.Type.RIGHT) {
                ConstraintAnchor.Type type14 = ConstraintAnchor.Type.TOP;
                if (type2 == type14 || type2 == ConstraintAnchor.Type.BOTTOM) {
                    connect(type14, constraintWidget, type2, 0, strength, i3);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0, strength, i3);
                    getAnchor(type6).connect(constraintWidget.getAnchor(type2), 0, i3);
                    return;
                }
                return;
            }
            connect(type13, constraintWidget, type2, 0, strength, i3);
            connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0, strength, i3);
            getAnchor(type6).connect(constraintWidget.getAnchor(type2), 0, i3);
            return;
        }
        ConstraintAnchor.Type type15 = ConstraintAnchor.Type.CENTER_X;
        if (type == type15 && (type2 == (type4 = ConstraintAnchor.Type.LEFT) || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(type4);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0, i3);
            anchor7.connect(anchor6, 0, i3);
            getAnchor(type15).connect(anchor6, 0, i3);
            return;
        }
        ConstraintAnchor.Type type16 = ConstraintAnchor.Type.CENTER_Y;
        if (type == type16 && (type2 == (type3 = ConstraintAnchor.Type.TOP) || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(type3).connect(anchor8, 0, i3);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0, i3);
            getAnchor(type16).connect(anchor8, 0, i3);
        } else if (type == type15 && type2 == type15) {
            ConstraintAnchor.Type type17 = ConstraintAnchor.Type.LEFT;
            getAnchor(type17).connect(constraintWidget.getAnchor(type17), 0, i3);
            ConstraintAnchor.Type type18 = ConstraintAnchor.Type.RIGHT;
            getAnchor(type18).connect(constraintWidget.getAnchor(type18), 0, i3);
            getAnchor(type15).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type == type16 && type2 == type16) {
            ConstraintAnchor.Type type19 = ConstraintAnchor.Type.TOP;
            getAnchor(type19).connect(constraintWidget.getAnchor(type19), 0, i3);
            ConstraintAnchor.Type type20 = ConstraintAnchor.Type.BOTTOM;
            getAnchor(type20).connect(constraintWidget.getAnchor(type20), 0, i3);
            getAnchor(type16).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                ConstraintAnchor.Type type21 = ConstraintAnchor.Type.BASELINE;
                if (type == type21) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                } else {
                    if (type != ConstraintAnchor.Type.TOP && type != ConstraintAnchor.Type.BOTTOM) {
                        if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                            ConstraintAnchor anchor13 = getAnchor(type6);
                            if (anchor13.getTarget() != anchor10) {
                                anchor13.reset();
                            }
                            ConstraintAnchor opposite = getAnchor(type).getOpposite();
                            ConstraintAnchor anchor14 = getAnchor(type15);
                            if (anchor14.isConnected()) {
                                opposite.reset();
                                anchor14.reset();
                            }
                        }
                    } else {
                        ConstraintAnchor anchor15 = getAnchor(type21);
                        if (anchor15 != null) {
                            anchor15.reset();
                        }
                        ConstraintAnchor anchor16 = getAnchor(type6);
                        if (anchor16.getTarget() != anchor10) {
                            anchor16.reset();
                        }
                        ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                        ConstraintAnchor anchor17 = getAnchor(type16);
                        if (anchor17.isConnected()) {
                            opposite2.reset();
                            anchor17.reset();
                        }
                    }
                    i4 = i2;
                }
                anchor9.connect(anchor10, i4, strength, i3);
                anchor10.getOwner().connectedTo(anchor9.getOwner());
            }
        }
    }

    public void resetAnchors(int i2) {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.mAnchors.size();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintAnchor constraintAnchor = this.mAnchors.get(i3);
            if (i2 == constraintAnchor.getConnectionCreator()) {
                if (constraintAnchor.isVerticalAnchor()) {
                    setVerticalBiasPercent(DEFAULT_BIAS);
                } else {
                    setHorizontalBiasPercent(DEFAULT_BIAS);
                }
                constraintAnchor.reset();
            }
        }
    }

    public void setFrame(int i2, int i3, int i4) {
        if (i4 == 0) {
            setHorizontalDimension(i2, i3);
        } else if (i4 == 1) {
            setVerticalDimension(i2, i3);
        }
        this.mOptimizerMeasured = true;
    }

    public void setDimensionRatio(float f2, int i2) {
        this.mDimensionRatio = f2;
        this.mDimensionRatioSide = i2;
    }

    public ConstraintWidget(int i2, int i3, int i4, int i5) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mBelongingGroup = null;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f2 = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f2;
        this.mVerticalBiasPercent = f2;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mOptimizerMeasurable = false;
        this.mOptimizerMeasured = false;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i2, int i3) {
        this(0, 0, i2, i3);
    }
}
