package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.Analyzer;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.ResolutionAnchor;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private HashMap<String, Integer> mDesignIds;
    private boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private int mOptimizationLevel;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets;

    public ConstraintLayout(Context context) {
        super(context);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 7;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(null);
    }

    private final ConstraintWidget getTargetWidget(int i2) {
        if (i2 == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(i2);
        if (view == null && (view = findViewById(i2)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void init(AttributeSet attributeSet) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.load(getContext(), resourceId);
                    } catch (Resources.NotFoundException unused) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    private void internalMeasureChildren(int i2, int i3) {
        boolean z;
        boolean z2;
        int baseline;
        int childMeasureSpec;
        int childMeasureSpec2;
        boolean z3;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline && !layoutParams.isHelper) {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                    int i6 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                    boolean z4 = layoutParams.horizontalDimensionFixed;
                    if (z4 || (z3 = layoutParams.verticalDimensionFixed) || (!z4 && layoutParams.matchConstraintDefaultWidth == 1) || i5 == -1 || (!z3 && (layoutParams.matchConstraintDefaultHeight == 1 || i6 == -1))) {
                        if (i5 == 0) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingLeft, -2);
                            z = true;
                        } else if (i5 == -1) {
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingLeft, -1);
                            z = false;
                        } else {
                            z = i5 == -2;
                            childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingLeft, i5);
                        }
                        if (i6 == 0) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
                            z2 = true;
                        } else if (i6 == -1) {
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, paddingTop, -1);
                            z2 = false;
                        } else {
                            z2 = i6 == -2;
                            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, paddingTop, i6);
                        }
                        childAt.measure(childMeasureSpec, childMeasureSpec2);
                        Metrics metrics = this.mMetrics;
                        if (metrics != null) {
                            metrics.measures++;
                        }
                        constraintWidget.setWidthWrapContent(i5 == -2);
                        constraintWidget.setHeightWrapContent(i6 == -2);
                        i5 = childAt.getMeasuredWidth();
                        i6 = childAt.getMeasuredHeight();
                    } else {
                        z = false;
                        z2 = false;
                    }
                    constraintWidget.setWidth(i5);
                    constraintWidget.setHeight(i6);
                    if (z) {
                        constraintWidget.setWrapWidth(i5);
                    }
                    if (z2) {
                        constraintWidget.setWrapHeight(i6);
                    }
                    if (layoutParams.needsBaseline && (baseline = childAt.getBaseline()) != -1) {
                        constraintWidget.setBaselineDistance(baseline);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:472:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x02c3 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void internalMeasureDimensions(int i2, int i3) {
        long j2;
        int i4;
        int i5;
        int i6;
        long j3;
        boolean z;
        int childMeasureSpec;
        boolean z2;
        int i7;
        int i8;
        Metrics metrics;
        int baseline;
        int i9;
        int baseline2;
        ConstraintLayout constraintLayout = this;
        int i10 = i2;
        int i11 = i3;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i12 = 0;
        while (true) {
            j2 = 1;
            i4 = 8;
            if (i12 >= childCount) {
                break;
            }
            View childAt = constraintLayout.getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline && !layoutParams.isHelper) {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i13 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                    int i14 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                    if (i13 != 0 && i14 != 0) {
                        boolean z3 = i13 == -2;
                        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i10, paddingLeft, i13);
                        boolean z4 = i14 == -2;
                        childAt.measure(childMeasureSpec2, ViewGroup.getChildMeasureSpec(i11, paddingTop, i14));
                        Metrics metrics2 = constraintLayout.mMetrics;
                        i9 = paddingTop;
                        if (metrics2 != null) {
                            metrics2.measures++;
                        }
                        constraintWidget.setWidthWrapContent(i13 == -2);
                        constraintWidget.setHeightWrapContent(i14 == -2);
                        int measuredWidth = childAt.getMeasuredWidth();
                        int measuredHeight = childAt.getMeasuredHeight();
                        constraintWidget.setWidth(measuredWidth);
                        constraintWidget.setHeight(measuredHeight);
                        if (z3) {
                            constraintWidget.setWrapWidth(measuredWidth);
                        }
                        if (z4) {
                            constraintWidget.setWrapHeight(measuredHeight);
                        }
                        if (layoutParams.needsBaseline && (baseline2 = childAt.getBaseline()) != -1) {
                            constraintWidget.setBaselineDistance(baseline2);
                        }
                        if (layoutParams.horizontalDimensionFixed && layoutParams.verticalDimensionFixed) {
                            constraintWidget.getResolutionWidth().resolve(measuredWidth);
                            constraintWidget.getResolutionHeight().resolve(measuredHeight);
                        }
                    } else {
                        i9 = paddingTop;
                        constraintWidget.getResolutionWidth().invalidate();
                        constraintWidget.getResolutionHeight().invalidate();
                    }
                    i12++;
                    i11 = i3;
                    paddingTop = i9;
                }
            }
            i9 = paddingTop;
            i12++;
            i11 = i3;
            paddingTop = i9;
        }
        int i15 = paddingTop;
        constraintLayout.mLayoutWidget.solveGraph();
        int i16 = 0;
        while (i16 < childCount) {
            View childAt2 = constraintLayout.getChildAt(i16);
            if (childAt2.getVisibility() != i4) {
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                ConstraintWidget constraintWidget2 = layoutParams2.widget;
                if (!layoutParams2.isGuideline && !layoutParams2.isHelper) {
                    constraintWidget2.setVisibility(childAt2.getVisibility());
                    int i17 = ((ViewGroup.MarginLayoutParams) layoutParams2).width;
                    int i18 = ((ViewGroup.MarginLayoutParams) layoutParams2).height;
                    if (i17 == 0 || i18 == 0) {
                        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                        ResolutionAnchor resolutionNode = constraintWidget2.getAnchor(type).getResolutionNode();
                        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                        ResolutionAnchor resolutionNode2 = constraintWidget2.getAnchor(type2).getResolutionNode();
                        boolean z5 = (constraintWidget2.getAnchor(type).getTarget() == null || constraintWidget2.getAnchor(type2).getTarget() == null) ? false : true;
                        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                        ResolutionAnchor resolutionNode3 = constraintWidget2.getAnchor(type3).getResolutionNode();
                        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                        ResolutionAnchor resolutionNode4 = constraintWidget2.getAnchor(type4).getResolutionNode();
                        boolean z6 = (constraintWidget2.getAnchor(type3).getTarget() == null || constraintWidget2.getAnchor(type4).getTarget() == null) ? false : true;
                        if (i17 == 0 && i18 == 0 && z5 && z6) {
                            i5 = i16;
                            i6 = childCount;
                            j3 = 1;
                        } else {
                            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintLayout.mLayoutWidget.getHorizontalDimensionBehaviour();
                            i6 = childCount;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            i5 = i16;
                            boolean z7 = horizontalDimensionBehaviour != dimensionBehaviour;
                            boolean z8 = constraintLayout.mLayoutWidget.getVerticalDimensionBehaviour() != dimensionBehaviour;
                            if (!z7) {
                                constraintWidget2.getResolutionWidth().invalidate();
                            }
                            if (!z8) {
                                constraintWidget2.getResolutionHeight().invalidate();
                            }
                            if (i17 == 0) {
                                if (z7 && constraintWidget2.isSpreadWidth() && z5 && resolutionNode.isResolved() && resolutionNode2.isResolved()) {
                                    i17 = (int) (resolutionNode2.getResolvedValue() - resolutionNode.getResolvedValue());
                                    constraintWidget2.getResolutionWidth().resolve(i17);
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingLeft, i17);
                                    z = false;
                                    if (i18 != 0) {
                                    }
                                } else {
                                    childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingLeft, -2);
                                    z = true;
                                    z7 = false;
                                    if (i18 != 0) {
                                        if (z8 && constraintWidget2.isSpreadHeight() && z6 && resolutionNode3.isResolved() && resolutionNode4.isResolved()) {
                                            int resolvedValue = (int) (resolutionNode4.getResolvedValue() - resolutionNode3.getResolvedValue());
                                            constraintWidget2.getResolutionHeight().resolve(resolvedValue);
                                            i7 = ViewGroup.getChildMeasureSpec(i3, i15, resolvedValue);
                                            i8 = resolvedValue;
                                            z2 = false;
                                            childAt2.measure(childMeasureSpec, i7);
                                            metrics = constraintLayout.mMetrics;
                                            if (metrics == null) {
                                            }
                                            constraintWidget2.setWidthWrapContent(i17 != -2);
                                            constraintWidget2.setHeightWrapContent(i8 != -2);
                                            int measuredWidth2 = childAt2.getMeasuredWidth();
                                            int measuredHeight2 = childAt2.getMeasuredHeight();
                                            constraintWidget2.setWidth(measuredWidth2);
                                            constraintWidget2.setHeight(measuredHeight2);
                                            if (z) {
                                            }
                                            if (z2) {
                                            }
                                            if (!z7) {
                                            }
                                            if (!z8) {
                                            }
                                            if (layoutParams2.needsBaseline) {
                                                constraintWidget2.setBaselineDistance(baseline);
                                            }
                                        } else {
                                            i7 = ViewGroup.getChildMeasureSpec(i3, i15, -2);
                                            i8 = i18;
                                            z8 = false;
                                            z2 = true;
                                            childAt2.measure(childMeasureSpec, i7);
                                            metrics = constraintLayout.mMetrics;
                                            if (metrics == null) {
                                                j3 = 1;
                                                metrics.measures++;
                                            } else {
                                                j3 = 1;
                                            }
                                            constraintWidget2.setWidthWrapContent(i17 != -2);
                                            constraintWidget2.setHeightWrapContent(i8 != -2);
                                            int measuredWidth22 = childAt2.getMeasuredWidth();
                                            int measuredHeight22 = childAt2.getMeasuredHeight();
                                            constraintWidget2.setWidth(measuredWidth22);
                                            constraintWidget2.setHeight(measuredHeight22);
                                            if (z) {
                                                constraintWidget2.setWrapWidth(measuredWidth22);
                                            }
                                            if (z2) {
                                                constraintWidget2.setWrapHeight(measuredHeight22);
                                            }
                                            if (!z7) {
                                                constraintWidget2.getResolutionWidth().resolve(measuredWidth22);
                                            } else {
                                                constraintWidget2.getResolutionWidth().remove();
                                            }
                                            if (!z8) {
                                                constraintWidget2.getResolutionHeight().resolve(measuredHeight22);
                                            } else {
                                                constraintWidget2.getResolutionHeight().remove();
                                            }
                                            if (layoutParams2.needsBaseline && (baseline = childAt2.getBaseline()) != -1) {
                                                constraintWidget2.setBaselineDistance(baseline);
                                            }
                                        }
                                    } else if (i18 == -1) {
                                        i8 = i18;
                                        i7 = ViewGroup.getChildMeasureSpec(i3, i15, -1);
                                        z2 = false;
                                        childAt2.measure(childMeasureSpec, i7);
                                        metrics = constraintLayout.mMetrics;
                                        if (metrics == null) {
                                        }
                                        constraintWidget2.setWidthWrapContent(i17 != -2);
                                        constraintWidget2.setHeightWrapContent(i8 != -2);
                                        int measuredWidth222 = childAt2.getMeasuredWidth();
                                        int measuredHeight222 = childAt2.getMeasuredHeight();
                                        constraintWidget2.setWidth(measuredWidth222);
                                        constraintWidget2.setHeight(measuredHeight222);
                                        if (z) {
                                        }
                                        if (z2) {
                                        }
                                        if (!z7) {
                                        }
                                        if (!z8) {
                                        }
                                        if (layoutParams2.needsBaseline) {
                                        }
                                    } else {
                                        boolean z9 = i18 == -2;
                                        int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(i3, i15, i18);
                                        z2 = z9;
                                        i7 = childMeasureSpec3;
                                        i8 = i18;
                                        childAt2.measure(childMeasureSpec, i7);
                                        metrics = constraintLayout.mMetrics;
                                        if (metrics == null) {
                                        }
                                        constraintWidget2.setWidthWrapContent(i17 != -2);
                                        constraintWidget2.setHeightWrapContent(i8 != -2);
                                        int measuredWidth2222 = childAt2.getMeasuredWidth();
                                        int measuredHeight2222 = childAt2.getMeasuredHeight();
                                        constraintWidget2.setWidth(measuredWidth2222);
                                        constraintWidget2.setHeight(measuredHeight2222);
                                        if (z) {
                                        }
                                        if (z2) {
                                        }
                                        if (!z7) {
                                        }
                                        if (!z8) {
                                        }
                                        if (layoutParams2.needsBaseline) {
                                        }
                                    }
                                }
                            } else if (i17 == -1) {
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingLeft, -1);
                                z = false;
                                if (i18 != 0) {
                                }
                            } else {
                                z = i17 == -2;
                                childMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingLeft, i17);
                                if (i18 != 0) {
                                }
                            }
                        }
                        constraintLayout = this;
                        i16 = i5 + 1;
                        j2 = j3;
                        childCount = i6;
                        i4 = 8;
                        i10 = i2;
                    }
                }
            }
            i5 = i16;
            i6 = childCount;
            j3 = j2;
            constraintLayout = this;
            i16 = i5 + 1;
            j2 = j3;
            childCount = i6;
            i4 = 8;
            i10 = i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:640:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:643:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:649:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:662:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x02c3  */
    /* JADX WARN: Type inference failed for: r23v0, types: [androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void setChildrenConstraints() {
        int i2;
        int i3;
        int i4;
        int i5;
        ConstraintWidget targetWidget;
        int i6;
        ConstraintWidget targetWidget2;
        int i7;
        ConstraintWidget targetWidget3;
        int i8;
        float f2;
        int i9;
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        ?? r3 = 0;
        int i10 = -1;
        if (isInEditMode) {
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).setDebugName(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        for (int i12 = 0; i12 < childCount; i12++) {
            ConstraintWidget viewWidget = getViewWidget(getChildAt(i12));
            if (viewWidget != null) {
                viewWidget.reset();
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt2 = getChildAt(i13);
                if (childAt2.getId() == this.mConstraintSetId && (childAt2 instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        ConstraintSet constraintSet = this.mConstraintSet;
        if (constraintSet != null) {
            constraintSet.applyToInternal(this);
        }
        this.mLayoutWidget.removeAllChildren();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i14 = 0; i14 < size; i14++) {
                this.mConstraintHelpers.get(i14).updatePreLayout(this);
            }
        }
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt3 = getChildAt(i15);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).updatePreLayout(this);
            }
        }
        int i16 = 0;
        while (i16 < childCount) {
            View childAt4 = getChildAt(i16);
            ConstraintWidget viewWidget2 = getViewWidget(childAt4);
            if (viewWidget2 != null) {
                LayoutParams layoutParams = (LayoutParams) childAt4.getLayoutParams();
                layoutParams.validate();
                if (layoutParams.helped) {
                    layoutParams.helped = r3;
                } else if (isInEditMode) {
                    try {
                        String resourceName2 = getResources().getResourceName(childAt4.getId());
                        setDesignInformation(r3, resourceName2, Integer.valueOf(childAt4.getId()));
                        getTargetWidget(childAt4.getId()).setDebugName(resourceName2.substring(resourceName2.indexOf("id/") + 3));
                    } catch (Resources.NotFoundException unused2) {
                    }
                }
                viewWidget2.setVisibility(childAt4.getVisibility());
                if (layoutParams.isInPlaceholder) {
                    viewWidget2.setVisibility(8);
                }
                viewWidget2.setCompanionWidget(childAt4);
                this.mLayoutWidget.add(viewWidget2);
                if (!layoutParams.verticalDimensionFixed || !layoutParams.horizontalDimensionFixed) {
                    this.mVariableDimensionsWidgets.add(viewWidget2);
                }
                if (layoutParams.isGuideline) {
                    androidx.constraintlayout.solver.widgets.Guideline guideline = (androidx.constraintlayout.solver.widgets.Guideline) viewWidget2;
                    int i17 = layoutParams.resolvedGuideBegin;
                    int i18 = layoutParams.resolvedGuideEnd;
                    float f3 = layoutParams.resolvedGuidePercent;
                    if (Build.VERSION.SDK_INT < 17) {
                        i17 = layoutParams.guideBegin;
                        i18 = layoutParams.guideEnd;
                        f3 = layoutParams.guidePercent;
                    }
                    if (f3 != -1.0f) {
                        guideline.setGuidePercent(f3);
                    } else if (i17 != i10) {
                        guideline.setGuideBegin(i17);
                    } else if (i18 != i10) {
                        guideline.setGuideEnd(i18);
                    }
                } else {
                    int i19 = layoutParams.leftToLeft;
                    if (i19 != i10 || layoutParams.leftToRight != i10 || layoutParams.rightToLeft != i10 || layoutParams.rightToRight != i10 || layoutParams.startToStart != i10 || layoutParams.startToEnd != i10 || layoutParams.endToStart != i10 || layoutParams.endToEnd != i10 || layoutParams.topToTop != i10 || layoutParams.topToBottom != i10 || layoutParams.bottomToTop != i10 || layoutParams.bottomToBottom != i10 || layoutParams.baselineToBaseline != i10 || layoutParams.editorAbsoluteX != i10 || layoutParams.editorAbsoluteY != i10 || layoutParams.circleConstraint != i10 || ((ViewGroup.MarginLayoutParams) layoutParams).width == i10 || ((ViewGroup.MarginLayoutParams) layoutParams).height == i10) {
                        int i20 = layoutParams.resolvedLeftToLeft;
                        int i21 = layoutParams.resolvedLeftToRight;
                        int i22 = layoutParams.resolvedRightToLeft;
                        int i23 = layoutParams.resolvedRightToRight;
                        int i24 = layoutParams.resolveGoneLeftMargin;
                        int i25 = layoutParams.resolveGoneRightMargin;
                        float f4 = layoutParams.resolvedHorizontalBias;
                        if (Build.VERSION.SDK_INT < 17) {
                            int i26 = layoutParams.leftToRight;
                            int i27 = layoutParams.rightToLeft;
                            int i28 = layoutParams.rightToRight;
                            int i29 = layoutParams.goneLeftMargin;
                            int i30 = layoutParams.goneRightMargin;
                            float f5 = layoutParams.horizontalBias;
                            if (i19 == -1 && i26 == -1) {
                                int i31 = layoutParams.startToStart;
                                if (i31 != -1) {
                                    i19 = i31;
                                } else {
                                    int i32 = layoutParams.startToEnd;
                                    if (i32 != -1) {
                                        i26 = i32;
                                    }
                                }
                            }
                            if (i27 == -1 && i28 == -1) {
                                int i33 = layoutParams.endToStart;
                                if (i33 != -1) {
                                    i21 = i26;
                                    i4 = i30;
                                    f4 = f5;
                                    i5 = i28;
                                    i20 = i19;
                                    i3 = i33;
                                    i24 = i29;
                                } else {
                                    int i34 = layoutParams.endToEnd;
                                    if (i34 != -1) {
                                        i21 = i26;
                                        i4 = i30;
                                        f4 = f5;
                                        i5 = i34;
                                        i24 = i29;
                                        i20 = i19;
                                        i3 = i27;
                                    }
                                }
                                i2 = -1;
                            }
                            i21 = i26;
                            i24 = i29;
                            i4 = i30;
                            f4 = f5;
                            i5 = i28;
                            i2 = -1;
                            i20 = i19;
                            i3 = i27;
                        } else {
                            i2 = -1;
                            i3 = i22;
                            i4 = i25;
                            i5 = i23;
                        }
                        int i35 = layoutParams.circleConstraint;
                        if (i35 != i2) {
                            ConstraintWidget targetWidget4 = getTargetWidget(i35);
                            if (targetWidget4 != null) {
                                viewWidget2.connectCircularConstraint(targetWidget4, layoutParams.circleAngle, layoutParams.circleRadius);
                            }
                        } else {
                            if (i20 != i2) {
                                ConstraintWidget targetWidget5 = getTargetWidget(i20);
                                if (targetWidget5 != null) {
                                    ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
                                    viewWidget2.immediateConnect(type, targetWidget5, type, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i24);
                                }
                            } else {
                                if (i21 != i2) {
                                    ConstraintWidget targetWidget6 = getTargetWidget(i21);
                                    if (targetWidget6 != null) {
                                        viewWidget2.immediateConnect(ConstraintAnchor.Type.LEFT, targetWidget6, ConstraintAnchor.Type.RIGHT, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i24);
                                    }
                                }
                                if (i3 == i2) {
                                    ConstraintWidget targetWidget7 = getTargetWidget(i3);
                                    if (targetWidget7 != null) {
                                        viewWidget2.immediateConnect(ConstraintAnchor.Type.RIGHT, targetWidget7, ConstraintAnchor.Type.LEFT, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i4);
                                    }
                                } else if (i5 != i2 && (targetWidget = getTargetWidget(i5)) != null) {
                                    ConstraintAnchor.Type type2 = ConstraintAnchor.Type.RIGHT;
                                    viewWidget2.immediateConnect(type2, targetWidget, type2, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i4);
                                }
                                i6 = layoutParams.topToTop;
                                if (i6 == -1) {
                                    ConstraintWidget targetWidget8 = getTargetWidget(i6);
                                    if (targetWidget8 != null) {
                                        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.TOP;
                                        viewWidget2.immediateConnect(type3, targetWidget8, type3, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.goneTopMargin);
                                    }
                                } else {
                                    int i36 = layoutParams.topToBottom;
                                    if (i36 != -1 && (targetWidget2 = getTargetWidget(i36)) != null) {
                                        viewWidget2.immediateConnect(ConstraintAnchor.Type.TOP, targetWidget2, ConstraintAnchor.Type.BOTTOM, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.goneTopMargin);
                                    }
                                }
                                i7 = layoutParams.bottomToTop;
                                if (i7 == -1) {
                                    ConstraintWidget targetWidget9 = getTargetWidget(i7);
                                    if (targetWidget9 != null) {
                                        viewWidget2.immediateConnect(ConstraintAnchor.Type.BOTTOM, targetWidget9, ConstraintAnchor.Type.TOP, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.goneBottomMargin);
                                    }
                                } else {
                                    int i37 = layoutParams.bottomToBottom;
                                    if (i37 != -1 && (targetWidget3 = getTargetWidget(i37)) != null) {
                                        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
                                        viewWidget2.immediateConnect(type4, targetWidget3, type4, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.goneBottomMargin);
                                    }
                                }
                                i8 = layoutParams.baselineToBaseline;
                                if (i8 != -1) {
                                    View view = this.mChildrenByIds.get(i8);
                                    ConstraintWidget targetWidget10 = getTargetWidget(layoutParams.baselineToBaseline);
                                    if (targetWidget10 != null && view != null && (view.getLayoutParams() instanceof LayoutParams)) {
                                        layoutParams.needsBaseline = true;
                                        ((LayoutParams) view.getLayoutParams()).needsBaseline = true;
                                        ConstraintAnchor.Type type5 = ConstraintAnchor.Type.BASELINE;
                                        viewWidget2.getAnchor(type5).connect(targetWidget10.getAnchor(type5), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                                        viewWidget2.getAnchor(ConstraintAnchor.Type.TOP).reset();
                                        viewWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
                                    }
                                }
                                if (f4 >= 0.0f && f4 != 0.5f) {
                                    viewWidget2.setHorizontalBiasPercent(f4);
                                }
                                f2 = layoutParams.verticalBias;
                                if (f2 >= 0.0f && f2 != 0.5f) {
                                    viewWidget2.setVerticalBiasPercent(f2);
                                }
                            }
                            i2 = -1;
                            if (i3 == i2) {
                            }
                            i6 = layoutParams.topToTop;
                            if (i6 == -1) {
                            }
                            i7 = layoutParams.bottomToTop;
                            if (i7 == -1) {
                            }
                            i8 = layoutParams.baselineToBaseline;
                            if (i8 != -1) {
                            }
                            if (f4 >= 0.0f) {
                                viewWidget2.setHorizontalBiasPercent(f4);
                            }
                            f2 = layoutParams.verticalBias;
                            if (f2 >= 0.0f) {
                                viewWidget2.setVerticalBiasPercent(f2);
                            }
                        }
                        if (isInEditMode) {
                            int i38 = layoutParams.editorAbsoluteX;
                            i9 = -1;
                            if (i38 != -1 || layoutParams.editorAbsoluteY != -1) {
                                viewWidget2.setOrigin(i38, layoutParams.editorAbsoluteY);
                            }
                        } else {
                            i9 = -1;
                        }
                        if (!layoutParams.horizontalDimensionFixed) {
                            if (((ViewGroup.MarginLayoutParams) layoutParams).width == i9) {
                                viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                                viewWidget2.getAnchor(ConstraintAnchor.Type.LEFT).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                                viewWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                            } else {
                                viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                                viewWidget2.setWidth(0);
                            }
                        } else {
                            viewWidget2.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                            viewWidget2.setWidth(((ViewGroup.MarginLayoutParams) layoutParams).width);
                        }
                        if (!layoutParams.verticalDimensionFixed) {
                            i10 = -1;
                            if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
                                viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                                viewWidget2.getAnchor(ConstraintAnchor.Type.TOP).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                                viewWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                                r3 = 0;
                            } else {
                                viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                                r3 = 0;
                                viewWidget2.setHeight(0);
                            }
                        } else {
                            r3 = 0;
                            i10 = -1;
                            viewWidget2.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                            viewWidget2.setHeight(((ViewGroup.MarginLayoutParams) layoutParams).height);
                        }
                        String str = layoutParams.dimensionRatio;
                        if (str != null) {
                            viewWidget2.setDimensionRatio(str);
                        }
                        viewWidget2.setHorizontalWeight(layoutParams.horizontalWeight);
                        viewWidget2.setVerticalWeight(layoutParams.verticalWeight);
                        viewWidget2.setHorizontalChainStyle(layoutParams.horizontalChainStyle);
                        viewWidget2.setVerticalChainStyle(layoutParams.verticalChainStyle);
                        viewWidget2.setHorizontalMatchStyle(layoutParams.matchConstraintDefaultWidth, layoutParams.matchConstraintMinWidth, layoutParams.matchConstraintMaxWidth, layoutParams.matchConstraintPercentWidth);
                        viewWidget2.setVerticalMatchStyle(layoutParams.matchConstraintDefaultHeight, layoutParams.matchConstraintMinHeight, layoutParams.matchConstraintMaxHeight, layoutParams.matchConstraintPercentHeight);
                    }
                }
            }
            i16++;
            r3 = r3;
        }
    }

    private void setSelfDimensionBehaviour(int i2, int i3) {
        int i4;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode != 1073741824) {
                dimensionBehaviour = dimensionBehaviour2;
            } else {
                i4 = Math.min(this.mMaxWidth, size) - paddingLeft;
                dimensionBehaviour = dimensionBehaviour2;
            }
            i4 = 0;
        } else {
            i4 = size;
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else if (mode2 == 1073741824) {
                size2 = Math.min(this.mMaxHeight, size2) - paddingTop;
            }
            size2 = 0;
        } else {
            dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.mLayoutWidget.setWidth(i4);
        this.mLayoutWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
        this.mLayoutWidget.setHeight(size2);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    private void updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            } else if (getChildAt(i2).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void updatePostMeasures() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).updatePostMeasure(this);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i3 = 0; i3 < size; i3++) {
                this.mConstraintHelpers.get(i3).updatePostMeasure(this);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(DYConstants.DY_REGEX_COMMA);
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i3 = (int) ((parseInt / 1080.0f) * width);
                        int i4 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(SupportMenu.CATEGORY_MASK);
                        float f2 = i3;
                        float f3 = i4;
                        float f4 = i3 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float parseInt4 = i4 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, parseInt4, paint);
                        canvas.drawLine(f4, parseInt4, f2, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, parseInt4, paint);
                        canvas.drawLine(f2, parseInt4, f4, f3, paint);
                    }
                }
            }
        }
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    public Object getDesignInformation(int i2, Object obj) {
        if (i2 == 0 && (obj instanceof String)) {
            String str = (String) obj;
            HashMap<String, Integer> hashMap = this.mDesignIds;
            if (hashMap == null || !hashMap.containsKey(str)) {
                return null;
            }
            return this.mDesignIds.get(str);
        }
        return null;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public View getViewById(int i2) {
        return this.mChildrenByIds.get(i2);
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View content;
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || isInEditMode) && !layoutParams.isInPlaceholder) {
                int drawX = constraintWidget.getDrawX();
                int drawY = constraintWidget.getDrawY();
                int width = constraintWidget.getWidth() + drawX;
                int height = constraintWidget.getHeight() + drawY;
                childAt.layout(drawX, drawY, width, height);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(drawX, drawY, width, height);
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.mConstraintHelpers.get(i7).updatePostLayout(this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:460:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:584:0x03ad  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i2, int i3) {
        boolean z;
        int size;
        int i4;
        int i5;
        boolean z2;
        boolean z3;
        int i6;
        int i7;
        boolean z4;
        int makeMeasureSpec;
        int makeMeasureSpec2;
        int i8;
        int i9;
        boolean z5;
        int baseline;
        int i10 = i2;
        System.currentTimeMillis();
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size3 = View.MeasureSpec.getSize(i3);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(i2, i3);
        int width = this.mLayoutWidget.getWidth();
        int height = this.mLayoutWidget.getHeight();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            z = true;
        } else {
            z = false;
        }
        boolean z6 = (this.mOptimizationLevel & 8) == 8;
        if (z6) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(width, height);
            internalMeasureDimensions(i2, i3);
        } else {
            internalMeasureChildren(i2, i3);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && z) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
        if (constraintWidgetContainer.mGroupsWrapOptimized) {
            if (constraintWidgetContainer.mHorizontalWrapOptimized && mode == Integer.MIN_VALUE) {
                int i11 = constraintWidgetContainer.mWrapFixedWidth;
                if (i11 < size2) {
                    constraintWidgetContainer.setWidth(i11);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
            ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutWidget;
            if (constraintWidgetContainer2.mVerticalWrapOptimized && mode2 == Integer.MIN_VALUE) {
                int i12 = constraintWidgetContainer2.mWrapFixedHeight;
                if (i12 < size3) {
                    constraintWidgetContainer2.setHeight(i12);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
        }
        if ((this.mOptimizationLevel & 32) == 32) {
            int width2 = this.mLayoutWidget.getWidth();
            int height2 = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth != width2 && mode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width2);
            }
            if (this.mLastMeasureHeight != height2 && mode2 == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height2);
            }
            ConstraintWidgetContainer constraintWidgetContainer3 = this.mLayoutWidget;
            if (constraintWidgetContainer3.mHorizontalWrapOptimized && constraintWidgetContainer3.mWrapFixedWidth > size2) {
                Analyzer.setPosition(constraintWidgetContainer3.mWidgetGroups, 0, size2);
            }
            ConstraintWidgetContainer constraintWidgetContainer4 = this.mLayoutWidget;
            if (constraintWidgetContainer4.mVerticalWrapOptimized && constraintWidgetContainer4.mWrapFixedHeight > size3) {
                Analyzer.setPosition(constraintWidgetContainer4.mWidgetGroups, 1, size3);
                if (getChildCount() > 0) {
                    solveLinearSystem("First pass");
                }
                size = this.mVariableDimensionsWidgets.size();
                int paddingBottom = paddingTop + getPaddingBottom();
                int paddingRight = paddingLeft + getPaddingRight();
                if (size <= 0) {
                    ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = this.mLayoutWidget.getHorizontalDimensionBehaviour();
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    boolean z7 = horizontalDimensionBehaviour == dimensionBehaviour;
                    boolean z8 = this.mLayoutWidget.getVerticalDimensionBehaviour() == dimensionBehaviour;
                    int max = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
                    int max2 = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
                    int i13 = 0;
                    boolean z9 = false;
                    int i14 = 0;
                    while (i13 < size) {
                        ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(i13);
                        int i15 = size;
                        View view = (View) constraintWidget.getCompanionWidget();
                        if (view == null) {
                            i7 = width;
                            i6 = height;
                        } else {
                            i6 = height;
                            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                            i7 = width;
                            if (!layoutParams.isHelper && !layoutParams.isGuideline) {
                                z4 = z9;
                                if (view.getVisibility() != 8 && (!z6 || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved())) {
                                    int i16 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                                    if (i16 == -2 && layoutParams.horizontalDimensionFixed) {
                                        makeMeasureSpec = ViewGroup.getChildMeasureSpec(i10, paddingRight, i16);
                                    } else {
                                        makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                                    }
                                    int i17 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                                    if (i17 == -2 && layoutParams.verticalDimensionFixed) {
                                        makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, paddingBottom, i17);
                                    } else {
                                        makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                                    }
                                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                                    Metrics metrics = this.mMetrics;
                                    i8 = paddingBottom;
                                    if (metrics != null) {
                                        metrics.additionalMeasures++;
                                    }
                                    int measuredWidth = view.getMeasuredWidth();
                                    int measuredHeight = view.getMeasuredHeight();
                                    if (measuredWidth != constraintWidget.getWidth()) {
                                        constraintWidget.setWidth(measuredWidth);
                                        if (z6) {
                                            constraintWidget.getResolutionWidth().resolve(measuredWidth);
                                        }
                                        if (z7 && constraintWidget.getRight() > max) {
                                            max = Math.max(max, constraintWidget.getRight() + constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                        }
                                        z4 = true;
                                    }
                                    if (measuredHeight != constraintWidget.getHeight()) {
                                        constraintWidget.setHeight(measuredHeight);
                                        if (z6) {
                                            constraintWidget.getResolutionHeight().resolve(measuredHeight);
                                        }
                                        if (z8 && constraintWidget.getBottom() > max2) {
                                            max2 = Math.max(max2, constraintWidget.getBottom() + constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                        }
                                        i9 = max2;
                                        z5 = true;
                                    } else {
                                        i9 = max2;
                                        z5 = z4;
                                    }
                                    if (layoutParams.needsBaseline && (baseline = view.getBaseline()) != -1 && baseline != constraintWidget.getBaselineDistance()) {
                                        constraintWidget.setBaselineDistance(baseline);
                                        z5 = true;
                                    }
                                    if (Build.VERSION.SDK_INT >= 11) {
                                        i14 = ViewGroup.combineMeasuredStates(i14, view.getMeasuredState());
                                    }
                                    z4 = z5;
                                    max2 = i9;
                                    i13++;
                                    i10 = i2;
                                    paddingBottom = i8;
                                    width = i7;
                                    size = i15;
                                    height = i6;
                                    z9 = z4;
                                }
                                i8 = paddingBottom;
                                i14 = i14;
                                i13++;
                                i10 = i2;
                                paddingBottom = i8;
                                width = i7;
                                size = i15;
                                height = i6;
                                z9 = z4;
                            }
                        }
                        z4 = z9;
                        i8 = paddingBottom;
                        i14 = i14;
                        i13++;
                        i10 = i2;
                        paddingBottom = i8;
                        width = i7;
                        size = i15;
                        height = i6;
                        z9 = z4;
                    }
                    int i18 = size;
                    int i19 = width;
                    int i20 = height;
                    boolean z10 = z9;
                    i4 = paddingBottom;
                    int i21 = i14;
                    if (z10) {
                        this.mLayoutWidget.setWidth(i19);
                        this.mLayoutWidget.setHeight(i20);
                        if (z6) {
                            this.mLayoutWidget.solveGraph();
                        }
                        solveLinearSystem("2nd pass");
                        if (this.mLayoutWidget.getWidth() < max) {
                            this.mLayoutWidget.setWidth(max);
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (this.mLayoutWidget.getHeight() < max2) {
                            this.mLayoutWidget.setHeight(max2);
                            z3 = true;
                        } else {
                            z3 = z2;
                        }
                        if (z3) {
                            solveLinearSystem("3rd pass");
                        }
                    }
                    for (int i22 = 0; i22 < i18; i22++) {
                        ConstraintWidget constraintWidget2 = this.mVariableDimensionsWidgets.get(i22);
                        View view2 = (View) constraintWidget2.getCompanionWidget();
                        if (view2 != null && (view2.getMeasuredWidth() != constraintWidget2.getWidth() || view2.getMeasuredHeight() != constraintWidget2.getHeight())) {
                            if (constraintWidget2.getVisibility() != 8) {
                                view2.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824));
                                Metrics metrics2 = this.mMetrics;
                                if (metrics2 != null) {
                                    metrics2.additionalMeasures++;
                                }
                            }
                        }
                    }
                    i5 = i21;
                } else {
                    i4 = paddingBottom;
                    i5 = 0;
                }
                int width3 = this.mLayoutWidget.getWidth() + paddingRight;
                int height3 = this.mLayoutWidget.getHeight() + i4;
                if (Build.VERSION.SDK_INT < 11) {
                    int resolveSizeAndState = ViewGroup.resolveSizeAndState(width3, i2, i5);
                    int resolveSizeAndState2 = ViewGroup.resolveSizeAndState(height3, i3, i5 << 16);
                    int i23 = resolveSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
                    int i24 = resolveSizeAndState2 & ViewCompat.MEASURED_SIZE_MASK;
                    int min = Math.min(this.mMaxWidth, i23);
                    int min2 = Math.min(this.mMaxHeight, i24);
                    if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                        min |= 16777216;
                    }
                    if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                        min2 |= 16777216;
                    }
                    setMeasuredDimension(min, min2);
                    this.mLastMeasureWidth = min;
                    this.mLastMeasureHeight = min2;
                    return;
                }
                setMeasuredDimension(width3, height3);
                this.mLastMeasureWidth = width3;
                this.mLastMeasureHeight = height3;
                return;
            }
        }
        if (getChildCount() > 0) {
        }
        size = this.mVariableDimensionsWidgets.size();
        int paddingBottom2 = paddingTop + getPaddingBottom();
        int paddingRight2 = paddingLeft + getPaddingRight();
        if (size <= 0) {
        }
        int width32 = this.mLayoutWidget.getWidth() + paddingRight2;
        int height32 = this.mLayoutWidget.getHeight() + i4;
        if (Build.VERSION.SDK_INT < 11) {
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof androidx.constraintlayout.solver.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            androidx.constraintlayout.solver.widgets.Guideline guideline = new androidx.constraintlayout.solver.widgets.Guideline();
            layoutParams.widget = guideline;
            layoutParams.isGuideline = true;
            guideline.setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public void setDesignInformation(int i2, Object obj, Object obj2) {
        if (i2 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    @Override // android.view.View
    public void setId(int i2) {
        this.mChildrenByIds.remove(getId());
        super.setId(i2);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        if (i2 == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = i2;
        requestLayout();
    }

    public void setMinHeight(int i2) {
        if (i2 == this.mMinHeight) {
            return;
        }
        this.mMinHeight = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        if (i2 == this.mMinWidth) {
            return;
        }
        this.mMinWidth = i2;
        requestLayout();
    }

    public void setOptimizationLevel(int i2) {
        this.mLayoutWidget.setOptimizationLevel(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected void solveLinearSystem(String str) {
        this.mLayoutWidget.layout();
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.resolutions++;
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 7;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 7;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(attributeSet);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        /* loaded from: classes.dex */
        public static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                map = sparseIntArray;
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }

            private Table() {
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = layoutParams.guideBegin;
            this.guideEnd = layoutParams.guideEnd;
            this.guidePercent = layoutParams.guidePercent;
            this.leftToLeft = layoutParams.leftToLeft;
            this.leftToRight = layoutParams.leftToRight;
            this.rightToLeft = layoutParams.rightToLeft;
            this.rightToRight = layoutParams.rightToRight;
            this.topToTop = layoutParams.topToTop;
            this.topToBottom = layoutParams.topToBottom;
            this.bottomToTop = layoutParams.bottomToTop;
            this.bottomToBottom = layoutParams.bottomToBottom;
            this.baselineToBaseline = layoutParams.baselineToBaseline;
            this.circleConstraint = layoutParams.circleConstraint;
            this.circleRadius = layoutParams.circleRadius;
            this.circleAngle = layoutParams.circleAngle;
            this.startToEnd = layoutParams.startToEnd;
            this.startToStart = layoutParams.startToStart;
            this.endToStart = layoutParams.endToStart;
            this.endToEnd = layoutParams.endToEnd;
            this.goneLeftMargin = layoutParams.goneLeftMargin;
            this.goneTopMargin = layoutParams.goneTopMargin;
            this.goneRightMargin = layoutParams.goneRightMargin;
            this.goneBottomMargin = layoutParams.goneBottomMargin;
            this.goneStartMargin = layoutParams.goneStartMargin;
            this.goneEndMargin = layoutParams.goneEndMargin;
            this.horizontalBias = layoutParams.horizontalBias;
            this.verticalBias = layoutParams.verticalBias;
            this.dimensionRatio = layoutParams.dimensionRatio;
            this.dimensionRatioValue = layoutParams.dimensionRatioValue;
            this.dimensionRatioSide = layoutParams.dimensionRatioSide;
            this.horizontalWeight = layoutParams.horizontalWeight;
            this.verticalWeight = layoutParams.verticalWeight;
            this.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.verticalChainStyle = layoutParams.verticalChainStyle;
            this.constrainedWidth = layoutParams.constrainedWidth;
            this.constrainedHeight = layoutParams.constrainedHeight;
            this.matchConstraintDefaultWidth = layoutParams.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = layoutParams.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = layoutParams.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = layoutParams.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = layoutParams.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = layoutParams.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = layoutParams.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = layoutParams.matchConstraintPercentHeight;
            this.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.orientation = layoutParams.orientation;
            this.horizontalDimensionFixed = layoutParams.horizontalDimensionFixed;
            this.verticalDimensionFixed = layoutParams.verticalDimensionFixed;
            this.needsBaseline = layoutParams.needsBaseline;
            this.isGuideline = layoutParams.isGuideline;
            this.resolvedLeftToLeft = layoutParams.resolvedLeftToLeft;
            this.resolvedLeftToRight = layoutParams.resolvedLeftToRight;
            this.resolvedRightToLeft = layoutParams.resolvedRightToLeft;
            this.resolvedRightToRight = layoutParams.resolvedRightToRight;
            this.resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
            this.resolvedHorizontalBias = layoutParams.resolvedHorizontalBias;
            this.widget = layoutParams.widget;
        }

        public void reset() {
            ConstraintWidget constraintWidget = this.widget;
            if (constraintWidget != null) {
                constraintWidget.reset();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:222:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:225:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:228:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:231:0x0060  */
        /* JADX WARN: Removed duplicated region for block: B:234:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:241:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:242:0x0084  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void resolveLayoutDirection(int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            float f2;
            int i7 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int i8 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(i2);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            boolean z = false;
            if (1 == getLayoutDirection()) {
                int i9 = this.startToEnd;
                if (i9 != -1) {
                    this.resolvedRightToLeft = i9;
                } else {
                    int i10 = this.startToStart;
                    if (i10 != -1) {
                        this.resolvedRightToRight = i10;
                    }
                    i3 = this.endToStart;
                    if (i3 != -1) {
                        this.resolvedLeftToRight = i3;
                        z = true;
                    }
                    i4 = this.endToEnd;
                    if (i4 != -1) {
                        this.resolvedLeftToLeft = i4;
                        z = true;
                    }
                    i5 = this.goneStartMargin;
                    if (i5 != -1) {
                        this.resolveGoneRightMargin = i5;
                    }
                    i6 = this.goneEndMargin;
                    if (i6 != -1) {
                        this.resolveGoneLeftMargin = i6;
                    }
                    if (z) {
                        this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                    }
                    if (this.isGuideline && this.orientation == 1) {
                        f2 = this.guidePercent;
                        if (f2 == -1.0f) {
                            this.resolvedGuidePercent = 1.0f - f2;
                            this.resolvedGuideBegin = -1;
                            this.resolvedGuideEnd = -1;
                        } else {
                            int i11 = this.guideBegin;
                            if (i11 != -1) {
                                this.resolvedGuideEnd = i11;
                                this.resolvedGuideBegin = -1;
                                this.resolvedGuidePercent = -1.0f;
                            } else {
                                int i12 = this.guideEnd;
                                if (i12 != -1) {
                                    this.resolvedGuideBegin = i12;
                                    this.resolvedGuideEnd = -1;
                                    this.resolvedGuidePercent = -1.0f;
                                }
                            }
                        }
                    }
                }
                z = true;
                i3 = this.endToStart;
                if (i3 != -1) {
                }
                i4 = this.endToEnd;
                if (i4 != -1) {
                }
                i5 = this.goneStartMargin;
                if (i5 != -1) {
                }
                i6 = this.goneEndMargin;
                if (i6 != -1) {
                }
                if (z) {
                }
                if (this.isGuideline) {
                    f2 = this.guidePercent;
                    if (f2 == -1.0f) {
                    }
                }
            } else {
                int i13 = this.startToEnd;
                if (i13 != -1) {
                    this.resolvedLeftToRight = i13;
                }
                int i14 = this.startToStart;
                if (i14 != -1) {
                    this.resolvedLeftToLeft = i14;
                }
                int i15 = this.endToStart;
                if (i15 != -1) {
                    this.resolvedRightToLeft = i15;
                }
                int i16 = this.endToEnd;
                if (i16 != -1) {
                    this.resolvedRightToRight = i16;
                }
                int i17 = this.goneStartMargin;
                if (i17 != -1) {
                    this.resolveGoneLeftMargin = i17;
                }
                int i18 = this.goneEndMargin;
                if (i18 != -1) {
                    this.resolveGoneRightMargin = i18;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                int i19 = this.rightToLeft;
                if (i19 != -1) {
                    this.resolvedRightToLeft = i19;
                    if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i8 > 0) {
                        ((ViewGroup.MarginLayoutParams) this).rightMargin = i8;
                    }
                } else {
                    int i20 = this.rightToRight;
                    if (i20 != -1) {
                        this.resolvedRightToRight = i20;
                        if (((ViewGroup.MarginLayoutParams) this).rightMargin <= 0 && i8 > 0) {
                            ((ViewGroup.MarginLayoutParams) this).rightMargin = i8;
                        }
                    }
                }
                int i21 = this.leftToLeft;
                if (i21 != -1) {
                    this.resolvedLeftToLeft = i21;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i7 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i7;
                    return;
                }
                int i22 = this.leftToRight;
                if (i22 != -1) {
                    this.resolvedLeftToRight = i22;
                    if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0 || i7 <= 0) {
                        return;
                    }
                    ((ViewGroup.MarginLayoutParams) this).leftMargin = i7;
                }
            }
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            int i2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i2 == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            int i3 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i3 == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (i2 == 0 || i2 == -1) {
                this.horizontalDimensionFixed = false;
                if (i2 == 0 && this.matchConstraintDefaultWidth == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (i3 == 0 || i3 == -1) {
                this.verticalDimensionFixed = false;
                if (i3 == 0 && this.matchConstraintDefaultHeight == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent == -1.0f && this.guideBegin == -1 && this.guideEnd == -1) {
                return;
            }
            this.isGuideline = true;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (!(this.widget instanceof androidx.constraintlayout.solver.widgets.Guideline)) {
                this.widget = new androidx.constraintlayout.solver.widgets.Guideline();
            }
            ((androidx.constraintlayout.solver.widgets.Guideline) this.widget).setOrientation(this.orientation);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            int i2;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                int i4 = Table.map.get(index);
                switch (i4) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        this.circleConstraint = resourceId;
                        if (resourceId == -1) {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        float f2 = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        this.circleAngle = f2;
                        if (f2 < 0.0f) {
                            this.circleAngle = (360.0f - f2) % 360.0f;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        int resourceId2 = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        this.leftToLeft = resourceId2;
                        if (resourceId2 == -1) {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        int resourceId3 = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        this.leftToRight = resourceId3;
                        if (resourceId3 == -1) {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        int resourceId4 = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        this.rightToLeft = resourceId4;
                        if (resourceId4 == -1) {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        int resourceId5 = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        this.rightToRight = resourceId5;
                        if (resourceId5 == -1) {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        int resourceId6 = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        this.topToTop = resourceId6;
                        if (resourceId6 == -1) {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        int resourceId7 = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        this.topToBottom = resourceId7;
                        if (resourceId7 == -1) {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        int resourceId8 = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        this.bottomToTop = resourceId8;
                        if (resourceId8 == -1) {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        int resourceId9 = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        this.bottomToBottom = resourceId9;
                        if (resourceId9 == -1) {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        int resourceId10 = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        this.baselineToBaseline = resourceId10;
                        if (resourceId10 == -1) {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        int resourceId11 = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        this.startToEnd = resourceId11;
                        if (resourceId11 == -1) {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        int resourceId12 = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        this.startToStart = resourceId12;
                        if (resourceId12 == -1) {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 19:
                        int resourceId13 = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        this.endToStart = resourceId13;
                        if (resourceId13 == -1) {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        int resourceId14 = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        this.endToEnd = resourceId14;
                        if (resourceId14 == -1) {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        } else {
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        this.matchConstraintDefaultWidth = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 32:
                        this.matchConstraintDefaultHeight = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) == -2) {
                                this.matchConstraintMinWidth = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) == -2) {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) == -2) {
                                this.matchConstraintMinHeight = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) == -2) {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            } else {
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        break;
                    default:
                        switch (i4) {
                            case 44:
                                String string = obtainStyledAttributes.getString(index);
                                this.dimensionRatio = string;
                                this.dimensionRatioValue = Float.NaN;
                                this.dimensionRatioSide = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int indexOf = this.dimensionRatio.indexOf(44);
                                    if (indexOf <= 0 || indexOf >= length - 1) {
                                        i2 = 0;
                                    } else {
                                        String substring = this.dimensionRatio.substring(0, indexOf);
                                        if (substring.equalsIgnoreCase("W")) {
                                            this.dimensionRatioSide = 0;
                                        } else if (substring.equalsIgnoreCase(DYConstants.LETTER_H)) {
                                            this.dimensionRatioSide = 1;
                                        }
                                        i2 = indexOf + 1;
                                    }
                                    int indexOf2 = this.dimensionRatio.indexOf(58);
                                    if (indexOf2 >= 0 && indexOf2 < length - 1) {
                                        String substring2 = this.dimensionRatio.substring(i2, indexOf2);
                                        String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                                        if (substring2.length() > 0 && substring3.length() > 0) {
                                            try {
                                                float parseFloat = Float.parseFloat(substring2);
                                                float parseFloat2 = Float.parseFloat(substring3);
                                                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                                    if (this.dimensionRatioSide == 1) {
                                                        this.dimensionRatioValue = Math.abs(parseFloat2 / parseFloat);
                                                        break;
                                                    } else {
                                                        this.dimensionRatioValue = Math.abs(parseFloat / parseFloat2);
                                                        break;
                                                    }
                                                }
                                            } catch (NumberFormatException unused5) {
                                                break;
                                            }
                                        }
                                    } else {
                                        String substring4 = this.dimensionRatio.substring(i2);
                                        if (substring4.length() > 0) {
                                            this.dimensionRatioValue = Float.parseFloat(substring4);
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    continue;
                                }
                                break;
                            case 45:
                                this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                                continue;
                            case 46:
                                this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                                continue;
                            case 47:
                                this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                continue;
                            case 48:
                                this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                                continue;
                            case 49:
                                this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                                continue;
                            case 50:
                                this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                                continue;
                        }
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }
    }
}
