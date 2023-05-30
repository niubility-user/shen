package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class Analyzer {
    private Analyzer() {
    }

    public static void determineGroups(ConstraintWidgetContainer constraintWidgetContainer) {
        if ((constraintWidgetContainer.getOptimizationLevel() & 32) != 32) {
            singleGroup(constraintWidgetContainer);
            return;
        }
        constraintWidgetContainer.mSkipSolver = true;
        constraintWidgetContainer.mGroupsWrapOptimized = false;
        constraintWidgetContainer.mHorizontalWrapOptimized = false;
        constraintWidgetContainer.mVerticalWrapOptimized = false;
        ArrayList<ConstraintWidget> arrayList = constraintWidgetContainer.mChildren;
        List<ConstraintWidgetGroup> list = constraintWidgetContainer.mWidgetGroups;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z = horizontalDimensionBehaviour == dimensionBehaviour;
        boolean z2 = constraintWidgetContainer.getVerticalDimensionBehaviour() == dimensionBehaviour;
        boolean z3 = z || z2;
        list.clear();
        for (ConstraintWidget constraintWidget : arrayList) {
            constraintWidget.mBelongingGroup = null;
            constraintWidget.mGroupsToSolver = false;
            constraintWidget.resetResolutionNodes();
        }
        for (ConstraintWidget constraintWidget2 : arrayList) {
            if (constraintWidget2.mBelongingGroup == null && !determineGroups(constraintWidget2, list, z3)) {
                singleGroup(constraintWidgetContainer);
                constraintWidgetContainer.mSkipSolver = false;
                return;
            }
        }
        int i2 = 0;
        int i3 = 0;
        for (ConstraintWidgetGroup constraintWidgetGroup : list) {
            i2 = Math.max(i2, getMaxDimension(constraintWidgetGroup, 0));
            i3 = Math.max(i3, getMaxDimension(constraintWidgetGroup, 1));
        }
        if (z) {
            constraintWidgetContainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidgetContainer.setWidth(i2);
            constraintWidgetContainer.mGroupsWrapOptimized = true;
            constraintWidgetContainer.mHorizontalWrapOptimized = true;
            constraintWidgetContainer.mWrapFixedWidth = i2;
        }
        if (z2) {
            constraintWidgetContainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidgetContainer.setHeight(i3);
            constraintWidgetContainer.mGroupsWrapOptimized = true;
            constraintWidgetContainer.mVerticalWrapOptimized = true;
            constraintWidgetContainer.mWrapFixedHeight = i3;
        }
        setPosition(list, 0, constraintWidgetContainer.getWidth());
        setPosition(list, 1, constraintWidgetContainer.getHeight());
    }

    private static int getMaxDimension(ConstraintWidgetGroup constraintWidgetGroup, int i2) {
        int i3 = i2 * 2;
        List<ConstraintWidget> startWidgets = constraintWidgetGroup.getStartWidgets(i2);
        int size = startWidgets.size();
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget = startWidgets.get(i5);
            ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
            int i6 = i3 + 1;
            i4 = Math.max(i4, getMaxDimensionTraversal(constraintWidget, i2, constraintAnchorArr[i6].mTarget == null || !(constraintAnchorArr[i3].mTarget == null || constraintAnchorArr[i6].mTarget == null), 0));
        }
        constraintWidgetGroup.mGroupDimensions[i2] = i4;
        return i4;
    }

    private static int getMaxDimensionTraversal(ConstraintWidget constraintWidget, int i2, boolean z, int i3) {
        int height;
        int baselineDistance;
        int i4;
        int i5;
        int i6;
        int width;
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        if (constraintWidget.mOptimizerMeasurable) {
            boolean z2 = constraintWidget.mBaseline.mTarget != null && i2 == 1;
            if (z) {
                height = constraintWidget.getBaselineDistance();
                baselineDistance = constraintWidget.getHeight() - constraintWidget.getBaselineDistance();
                i5 = i2 * 2;
                i4 = i5 + 1;
            } else {
                height = constraintWidget.getHeight() - constraintWidget.getBaselineDistance();
                baselineDistance = constraintWidget.getBaselineDistance();
                i4 = i2 * 2;
                i5 = i4 + 1;
            }
            ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
            if (constraintAnchorArr[i4].mTarget == null || constraintAnchorArr[i5].mTarget != null) {
                i6 = 1;
            } else {
                i6 = -1;
                int i11 = i4;
                i4 = i5;
                i5 = i11;
            }
            int i12 = z2 ? i3 - height : i3;
            int margin = (constraintAnchorArr[i5].getMargin() * i6) + getParentBiasOffset(constraintWidget, i2);
            int i13 = i12 + margin;
            int width2 = (i2 == 0 ? constraintWidget.getWidth() : constraintWidget.getHeight()) * i6;
            Iterator<ResolutionNode> it = constraintWidget.mListAnchors[i5].getResolutionNode().dependents.iterator();
            while (it.hasNext()) {
                i10 = Math.max(i10, getMaxDimensionTraversal(((ResolutionAnchor) it.next()).myAnchor.mOwner, i2, z, i13));
            }
            int i14 = 0;
            for (Iterator<ResolutionNode> it2 = constraintWidget.mListAnchors[i4].getResolutionNode().dependents.iterator(); it2.hasNext(); it2 = it2) {
                i14 = Math.max(i14, getMaxDimensionTraversal(((ResolutionAnchor) it2.next()).myAnchor.mOwner, i2, z, width2 + i13));
            }
            if (z2) {
                i10 -= height;
                width = i14 + baselineDistance;
            } else {
                width = i14 + ((i2 == 0 ? constraintWidget.getWidth() : constraintWidget.getHeight()) * i6);
            }
            int i15 = 1;
            if (i2 == 1) {
                Iterator<ResolutionNode> it3 = constraintWidget.mBaseline.getResolutionNode().dependents.iterator();
                int i16 = 0;
                while (it3.hasNext()) {
                    Iterator<ResolutionNode> it4 = it3;
                    ResolutionAnchor resolutionAnchor = (ResolutionAnchor) it3.next();
                    if (i6 == i15) {
                        i16 = Math.max(i16, getMaxDimensionTraversal(resolutionAnchor.myAnchor.mOwner, i2, z, height + i13));
                        i9 = i4;
                    } else {
                        i9 = i4;
                        i16 = Math.max(i16, getMaxDimensionTraversal(resolutionAnchor.myAnchor.mOwner, i2, z, (baselineDistance * i6) + i13));
                    }
                    it3 = it4;
                    i4 = i9;
                    i15 = 1;
                }
                i7 = i4;
                int i17 = i16;
                i8 = (constraintWidget.mBaseline.getResolutionNode().dependents.size() <= 0 || z2) ? i17 : i6 == 1 ? i17 + height : i17 - baselineDistance;
            } else {
                i7 = i4;
                i8 = 0;
            }
            int max = margin + Math.max(i10, Math.max(width, i8));
            int i18 = width2 + i13;
            if (i6 == -1) {
                i18 = i13;
                i13 = i18;
            }
            if (z) {
                Optimizer.setOptimizedWidget(constraintWidget, i2, i13);
                constraintWidget.setFrame(i13, i18, i2);
            } else {
                constraintWidget.mBelongingGroup.addWidgetsToSet(constraintWidget, i2);
                constraintWidget.setRelativePositioning(i13, i2);
            }
            if (constraintWidget.getDimensionBehaviour(i2) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio != 0.0f) {
                constraintWidget.mBelongingGroup.addWidgetsToSet(constraintWidget, i2);
            }
            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.mListAnchors;
            if (constraintAnchorArr2[i5].mTarget != null && constraintAnchorArr2[i7].mTarget != null) {
                ConstraintWidget parent = constraintWidget.getParent();
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget.mListAnchors;
                if (constraintAnchorArr3[i5].mTarget.mOwner == parent && constraintAnchorArr3[i7].mTarget.mOwner == parent) {
                    constraintWidget.mBelongingGroup.addWidgetsToSet(constraintWidget, i2);
                }
            }
            return max;
        }
        return 0;
    }

    private static int getParentBiasOffset(ConstraintWidget constraintWidget, int i2) {
        ConstraintAnchor constraintAnchor;
        int i3 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i3];
        ConstraintAnchor constraintAnchor3 = constraintAnchorArr[i3 + 1];
        ConstraintAnchor constraintAnchor4 = constraintAnchor2.mTarget;
        if (constraintAnchor4 != null) {
            ConstraintWidget constraintWidget2 = constraintAnchor4.mOwner;
            ConstraintWidget constraintWidget3 = constraintWidget.mParent;
            if (constraintWidget2 == constraintWidget3 && (constraintAnchor = constraintAnchor3.mTarget) != null && constraintAnchor.mOwner == constraintWidget3) {
                return (int) ((((constraintWidget3.getLength(i2) - constraintAnchor2.getMargin()) - constraintAnchor3.getMargin()) - constraintWidget.getLength(i2)) * (i2 == 0 ? constraintWidget.mHorizontalBiasPercent : constraintWidget.mVerticalBiasPercent));
            }
            return 0;
        }
        return 0;
    }

    private static void invalidate(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidget constraintWidget, ConstraintWidgetGroup constraintWidgetGroup) {
        constraintWidgetGroup.mSkipSolver = false;
        constraintWidgetContainer.mSkipSolver = false;
        constraintWidget.mOptimizerMeasurable = false;
    }

    private static int resolveDimensionRatio(ConstraintWidget constraintWidget) {
        float width;
        float height;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (horizontalDimensionBehaviour == dimensionBehaviour) {
            if (constraintWidget.mDimensionRatioSide == 0) {
                height = constraintWidget.getHeight() * constraintWidget.mDimensionRatio;
            } else {
                height = constraintWidget.getHeight() / constraintWidget.mDimensionRatio;
            }
            int i2 = (int) height;
            constraintWidget.setWidth(i2);
            return i2;
        } else if (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) {
            if (constraintWidget.mDimensionRatioSide == 1) {
                width = constraintWidget.getWidth() * constraintWidget.mDimensionRatio;
            } else {
                width = constraintWidget.getWidth() / constraintWidget.mDimensionRatio;
            }
            int i3 = (int) width;
            constraintWidget.setHeight(i3);
            return i3;
        } else {
            return -1;
        }
    }

    private static void setConnection(ConstraintAnchor constraintAnchor) {
        ResolutionAnchor resolutionNode = constraintAnchor.getResolutionNode();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget == constraintAnchor) {
            return;
        }
        constraintAnchor2.getResolutionNode().addDependent(resolutionNode);
    }

    public static void setPosition(List<ConstraintWidgetGroup> list, int i2, int i3) {
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            for (ConstraintWidget constraintWidget : list.get(i4).getWidgetsToSet(i2)) {
                if (constraintWidget.mOptimizerMeasurable) {
                    updateSizeDependentWidgets(constraintWidget, i2, i3);
                }
            }
        }
    }

    private static void singleGroup(ConstraintWidgetContainer constraintWidgetContainer) {
        constraintWidgetContainer.mWidgetGroups.clear();
        constraintWidgetContainer.mWidgetGroups.add(0, new ConstraintWidgetGroup(constraintWidgetContainer.mChildren));
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x015b, code lost:
        if (r4.mOwner == r5) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0112, code lost:
        if (r4.mOwner == r5) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean traverse(ConstraintWidget constraintWidget, ConstraintWidgetGroup constraintWidgetGroup, List<ConstraintWidgetGroup> list, boolean z) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int length;
        int i2;
        if (constraintWidget == null) {
            return true;
        }
        constraintWidget.mOptimizerMeasured = false;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) constraintWidget.getParent();
        ConstraintWidgetGroup constraintWidgetGroup2 = constraintWidget.mBelongingGroup;
        if (constraintWidgetGroup2 != null) {
            if (constraintWidgetGroup2 != constraintWidgetGroup) {
                constraintWidgetGroup.mConstrainedGroup.addAll(constraintWidgetGroup2.mConstrainedGroup);
                constraintWidgetGroup.mStartHorizontalWidgets.addAll(constraintWidget.mBelongingGroup.mStartHorizontalWidgets);
                constraintWidgetGroup.mStartVerticalWidgets.addAll(constraintWidget.mBelongingGroup.mStartVerticalWidgets);
                ConstraintWidgetGroup constraintWidgetGroup3 = constraintWidget.mBelongingGroup;
                if (!constraintWidgetGroup3.mSkipSolver) {
                    constraintWidgetGroup.mSkipSolver = false;
                }
                list.remove(constraintWidgetGroup3);
                Iterator<ConstraintWidget> it = constraintWidget.mBelongingGroup.mConstrainedGroup.iterator();
                while (it.hasNext()) {
                    it.next().mBelongingGroup = constraintWidgetGroup;
                }
            }
            return true;
        }
        constraintWidget.mOptimizerMeasurable = true;
        constraintWidgetGroup.mConstrainedGroup.add(constraintWidget);
        constraintWidget.mBelongingGroup = constraintWidgetGroup;
        if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null && constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null && constraintWidget.mBaseline.mTarget == null && constraintWidget.mCenter.mTarget == null) {
            invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
            if (z) {
                return false;
            }
        }
        if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
            constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z) {
                invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
                return false;
            } else if (constraintWidget.mTop.mTarget.mOwner != constraintWidget.getParent() || constraintWidget.mBottom.mTarget.mOwner != constraintWidget.getParent()) {
                invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
            }
        }
        if (constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) {
            constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z) {
                invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
                return false;
            } else if (constraintWidget.mLeft.mTarget.mOwner != constraintWidget.getParent() || constraintWidget.mRight.mTarget.mOwner != constraintWidget.getParent()) {
                invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
            }
        }
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (((horizontalDimensionBehaviour == dimensionBehaviour3) ^ (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour3)) && constraintWidget.mDimensionRatio != 0.0f) {
            resolveDimensionRatio(constraintWidget);
        } else if (constraintWidget.getHorizontalDimensionBehaviour() == dimensionBehaviour3 || constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour3) {
            invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
            if (z) {
                return false;
            }
        }
        ConstraintAnchor constraintAnchor4 = constraintWidget.mLeft.mTarget;
        if ((constraintAnchor4 != null || constraintWidget.mRight.mTarget != null) && ((constraintAnchor4 == null || constraintAnchor4.mOwner != constraintWidget.mParent || constraintWidget.mRight.mTarget != null) && ((constraintAnchor = constraintWidget.mRight.mTarget) == null || constraintAnchor.mOwner != constraintWidget.mParent || constraintAnchor4 != null))) {
            if (constraintAnchor4 != null) {
                ConstraintWidget constraintWidget2 = constraintAnchor4.mOwner;
                ConstraintWidget constraintWidget3 = constraintWidget.mParent;
                if (constraintWidget2 == constraintWidget3) {
                    if (constraintAnchor != null) {
                    }
                }
            }
            constraintAnchor2 = constraintWidget.mTop.mTarget;
            if ((constraintAnchor2 == null || constraintWidget.mBottom.mTarget != null) && ((constraintAnchor2 == null || constraintAnchor2.mOwner != constraintWidget.mParent || constraintWidget.mBottom.mTarget != null) && ((constraintAnchor3 = constraintWidget.mBottom.mTarget) == null || constraintAnchor3.mOwner != constraintWidget.mParent || constraintAnchor2 != null))) {
                if (constraintAnchor2 != null) {
                    ConstraintWidget constraintWidget4 = constraintAnchor2.mOwner;
                    ConstraintWidget constraintWidget5 = constraintWidget.mParent;
                    if (constraintWidget4 == constraintWidget5) {
                        if (constraintAnchor3 != null) {
                        }
                    }
                }
                if (constraintWidget instanceof Helper) {
                    invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
                    if (z) {
                        return false;
                    }
                    Helper helper = (Helper) constraintWidget;
                    for (int i3 = 0; i3 < helper.mWidgetsCount; i3++) {
                        if (!traverse(helper.mWidgets[i3], constraintWidgetGroup, list, z)) {
                            return false;
                        }
                    }
                }
                length = constraintWidget.mListAnchors.length;
                for (i2 = 0; i2 < length; i2++) {
                    ConstraintAnchor constraintAnchor5 = constraintWidget.mListAnchors[i2];
                    ConstraintAnchor constraintAnchor6 = constraintAnchor5.mTarget;
                    if (constraintAnchor6 != null && constraintAnchor6.mOwner != constraintWidget.getParent()) {
                        if (constraintAnchor5.mType == ConstraintAnchor.Type.CENTER) {
                            invalidate(constraintWidgetContainer, constraintWidget, constraintWidgetGroup);
                            if (z) {
                                return false;
                            }
                        } else {
                            setConnection(constraintAnchor5);
                        }
                        if (!traverse(constraintAnchor5.mTarget.mOwner, constraintWidgetGroup, list, z)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            if (constraintWidget.mCenter.mTarget == null && constraintWidget.mBaseline.mTarget == null && !(constraintWidget instanceof Guideline) && !(constraintWidget instanceof Helper)) {
                constraintWidgetGroup.mStartVerticalWidgets.add(constraintWidget);
            }
            if (constraintWidget instanceof Helper) {
            }
            length = constraintWidget.mListAnchors.length;
            while (i2 < length) {
            }
            return true;
        }
        if (constraintWidget.mCenter.mTarget == null && !(constraintWidget instanceof Guideline) && !(constraintWidget instanceof Helper)) {
            constraintWidgetGroup.mStartHorizontalWidgets.add(constraintWidget);
        }
        constraintAnchor2 = constraintWidget.mTop.mTarget;
        if (constraintAnchor2 == null) {
        }
        if (constraintAnchor2 != null) {
        }
        if (constraintWidget instanceof Helper) {
        }
        length = constraintWidget.mListAnchors.length;
        while (i2 < length) {
        }
        return true;
    }

    private static void updateSizeDependentWidgets(ConstraintWidget constraintWidget, int i2, int i3) {
        int i4 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i4];
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i4 + 1];
        if ((constraintAnchor.mTarget == null || constraintAnchor2.mTarget == null) ? false : true) {
            Optimizer.setOptimizedWidget(constraintWidget, i2, getParentBiasOffset(constraintWidget, i2) + constraintAnchor.getMargin());
        } else if (constraintWidget.mDimensionRatio != 0.0f && constraintWidget.getDimensionBehaviour(i2) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int resolveDimensionRatio = resolveDimensionRatio(constraintWidget);
            int i5 = (int) constraintWidget.mListAnchors[i4].getResolutionNode().resolvedOffset;
            constraintAnchor2.getResolutionNode().resolvedTarget = constraintAnchor.getResolutionNode();
            constraintAnchor2.getResolutionNode().resolvedOffset = resolveDimensionRatio;
            constraintAnchor2.getResolutionNode().state = 1;
            constraintWidget.setFrame(i5, i5 + resolveDimensionRatio, i2);
        } else {
            int relativePositioning = i3 - constraintWidget.getRelativePositioning(i2);
            int length = relativePositioning - constraintWidget.getLength(i2);
            constraintWidget.setFrame(length, relativePositioning, i2);
            Optimizer.setOptimizedWidget(constraintWidget, i2, length);
        }
    }

    private static boolean determineGroups(ConstraintWidget constraintWidget, List<ConstraintWidgetGroup> list, boolean z) {
        ConstraintWidgetGroup constraintWidgetGroup = new ConstraintWidgetGroup(new ArrayList(), true);
        list.add(constraintWidgetGroup);
        return traverse(constraintWidget, constraintWidgetGroup, list, z);
    }
}
