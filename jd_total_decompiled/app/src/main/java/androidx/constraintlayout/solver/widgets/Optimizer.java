package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GROUPS = 32;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 7;
    static boolean[] flags = new boolean[3];

    public static void analyze(int i2, ConstraintWidget constraintWidget) {
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget.mBottom.getResolutionNode();
        boolean z = (i2 & 8) == 8;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2 && optimizableMatchConstraint(constraintWidget, 0);
        if (resolutionNode.type != 4 && resolutionNode3.type != 4) {
            if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || (z2 && constraintWidget.getVisibility() == 8)) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft.mTarget;
                if (constraintAnchor == null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintAnchor != null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintAnchor == null && constraintWidget.mRight.mTarget != null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    }
                } else if (constraintAnchor != null && constraintWidget.mRight.mTarget != null) {
                    resolutionNode.setType(2);
                    resolutionNode3.setType(2);
                    if (z) {
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                        resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                        resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.setOpposite(resolutionNode3, -constraintWidget.getWidth());
                        resolutionNode3.setOpposite(resolutionNode, constraintWidget.getWidth());
                    }
                }
            } else if (z2) {
                int width = constraintWidget.getWidth();
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                ConstraintAnchor constraintAnchor2 = constraintWidget.mLeft.mTarget;
                if (constraintAnchor2 == null && constraintWidget.mRight.mTarget == null) {
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, width);
                    }
                } else if (constraintAnchor2 == null || constraintWidget.mRight.mTarget != null) {
                    if (constraintAnchor2 != null || constraintWidget.mRight.mTarget == null) {
                        if (constraintAnchor2 != null && constraintWidget.mRight.mTarget != null) {
                            if (z) {
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                resolutionNode.setType(3);
                                resolutionNode3.setType(3);
                                resolutionNode.setOpposite(resolutionNode3, 0.0f);
                                resolutionNode3.setOpposite(resolutionNode, 0.0f);
                            } else {
                                resolutionNode.setType(2);
                                resolutionNode3.setType(2);
                                resolutionNode.setOpposite(resolutionNode3, -width);
                                resolutionNode3.setOpposite(resolutionNode, width);
                                constraintWidget.setWidth(width);
                            }
                        }
                    } else if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -width);
                    }
                } else if (z) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, width);
                }
            }
        }
        boolean z3 = constraintWidget.mListDimensionBehaviors[1] == dimensionBehaviour2 && optimizableMatchConstraint(constraintWidget, 1);
        if (resolutionNode2.type == 4 || resolutionNode4.type == 4) {
            return;
        }
        if (constraintWidget.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.FIXED && (!z3 || constraintWidget.getVisibility() != 8)) {
            if (z3) {
                int height = constraintWidget.getHeight();
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop.mTarget;
                if (constraintAnchor3 == null && constraintWidget.mBottom.mTarget == null) {
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        return;
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                        return;
                    }
                } else if (constraintAnchor3 != null && constraintWidget.mBottom.mTarget == null) {
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        return;
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                        return;
                    }
                } else if (constraintAnchor3 == null && constraintWidget.mBottom.mTarget != null) {
                    if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                        return;
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -height);
                        return;
                    }
                } else if (constraintAnchor3 == null || constraintWidget.mBottom.mTarget == null) {
                    return;
                } else {
                    if (z) {
                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                    }
                    if (constraintWidget.mDimensionRatio == 0.0f) {
                        resolutionNode2.setType(3);
                        resolutionNode4.setType(3);
                        resolutionNode2.setOpposite(resolutionNode4, 0.0f);
                        resolutionNode4.setOpposite(resolutionNode2, 0.0f);
                        return;
                    }
                    resolutionNode2.setType(2);
                    resolutionNode4.setType(2);
                    resolutionNode2.setOpposite(resolutionNode4, -height);
                    resolutionNode4.setOpposite(resolutionNode2, height);
                    constraintWidget.setHeight(height);
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        ConstraintAnchor constraintAnchor4 = constraintWidget.mTop.mTarget;
        if (constraintAnchor4 == null && constraintWidget.mBottom.mTarget == null) {
            resolutionNode2.setType(1);
            resolutionNode4.setType(1);
            if (z) {
                resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
            } else {
                resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
            }
            ConstraintAnchor constraintAnchor5 = constraintWidget.mBaseline;
            if (constraintAnchor5.mTarget != null) {
                constraintAnchor5.getResolutionNode().setType(1);
                resolutionNode2.dependsOn(1, constraintWidget.mBaseline.getResolutionNode(), -constraintWidget.mBaselineDistance);
            }
        } else if (constraintAnchor4 != null && constraintWidget.mBottom.mTarget == null) {
            resolutionNode2.setType(1);
            resolutionNode4.setType(1);
            if (z) {
                resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
            } else {
                resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
            }
            if (constraintWidget.mBaselineDistance > 0) {
                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
            }
        } else if (constraintAnchor4 == null && constraintWidget.mBottom.mTarget != null) {
            resolutionNode2.setType(1);
            resolutionNode4.setType(1);
            if (z) {
                resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
            } else {
                resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
            }
            if (constraintWidget.mBaselineDistance > 0) {
                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
            }
        } else if (constraintAnchor4 == null || constraintWidget.mBottom.mTarget == null) {
        } else {
            resolutionNode2.setType(2);
            resolutionNode4.setType(2);
            if (z) {
                resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
            } else {
                resolutionNode2.setOpposite(resolutionNode4, -constraintWidget.getHeight());
                resolutionNode4.setOpposite(resolutionNode2, constraintWidget.getHeight());
            }
            if (constraintWidget.mBaselineDistance > 0) {
                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:497:0x002e, code lost:
        if (r7 == 2) goto L498;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x0030, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x0032, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0040, code lost:
        if (r7 == 2) goto L498;
     */
    /* JADX WARN: Removed duplicated region for block: B:618:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:702:0x00fc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean applyChainOptimized(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i2, int i3, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ResolutionAnchor resolutionAnchor;
        float margin;
        int height;
        int height2;
        float f2;
        ConstraintWidget constraintWidget;
        boolean z4;
        int height3;
        ConstraintWidget constraintWidget2 = chainHead.mFirst;
        ConstraintWidget constraintWidget3 = chainHead.mLast;
        ConstraintWidget constraintWidget4 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget5 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget6 = chainHead.mHead;
        float f3 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget7 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget8 = chainHead.mLastMatchConstraintWidget;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidgetContainer.mListDimensionBehaviors[i2];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i2 == 0) {
            int i4 = constraintWidget6.mHorizontalChainStyle;
            z = i4 == 0;
            z2 = i4 == 1;
        } else {
            int i5 = constraintWidget6.mVerticalChainStyle;
            z = i5 == 0;
            z2 = i5 == 1;
        }
        ConstraintWidget constraintWidget9 = constraintWidget2;
        int i6 = 0;
        boolean z5 = false;
        int i7 = 0;
        float f4 = 0.0f;
        float f5 = 0.0f;
        while (!z5) {
            if (constraintWidget9.getVisibility() != 8) {
                i7++;
                if (i2 == 0) {
                    height3 = constraintWidget9.getWidth();
                } else {
                    height3 = constraintWidget9.getHeight();
                }
                f4 += height3;
                if (constraintWidget9 != constraintWidget4) {
                    f4 += constraintWidget9.mListAnchors[i3].getMargin();
                }
                if (constraintWidget9 != constraintWidget5) {
                    f4 += constraintWidget9.mListAnchors[i3 + 1].getMargin();
                }
                f5 = f5 + constraintWidget9.mListAnchors[i3].getMargin() + constraintWidget9.mListAnchors[i3 + 1].getMargin();
            }
            ConstraintAnchor constraintAnchor = constraintWidget9.mListAnchors[i3];
            if (constraintWidget9.getVisibility() != 8 && constraintWidget9.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                i6++;
                if (i2 == 0) {
                    if (constraintWidget9.mMatchConstraintDefaultWidth != 0) {
                        return false;
                    }
                    z4 = false;
                    if (constraintWidget9.mMatchConstraintMinWidth != 0 || constraintWidget9.mMatchConstraintMaxWidth != 0) {
                        return false;
                    }
                } else {
                    z4 = false;
                    if (constraintWidget9.mMatchConstraintDefaultHeight != 0) {
                        return false;
                    }
                    if (constraintWidget9.mMatchConstraintMinHeight == 0) {
                        if (constraintWidget9.mMatchConstraintMaxHeight != 0) {
                        }
                    }
                    return z4;
                }
                if (constraintWidget9.mDimensionRatio != 0.0f) {
                    return z4;
                }
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget9.mListAnchors[i3 + 1].mTarget;
            if (constraintAnchor2 != null) {
                ConstraintWidget constraintWidget10 = constraintAnchor2.mOwner;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget10.mListAnchors;
                if (constraintAnchorArr[i3].mTarget != null && constraintAnchorArr[i3].mTarget.mOwner == constraintWidget9) {
                    constraintWidget = constraintWidget10;
                    if (constraintWidget == null) {
                        constraintWidget9 = constraintWidget;
                    } else {
                        z5 = true;
                    }
                }
            }
            constraintWidget = null;
            if (constraintWidget == null) {
            }
        }
        ResolutionAnchor resolutionNode = constraintWidget2.mListAnchors[i3].getResolutionNode();
        int i8 = i3 + 1;
        ResolutionAnchor resolutionNode2 = constraintWidget3.mListAnchors[i8].getResolutionNode();
        ResolutionAnchor resolutionAnchor2 = resolutionNode.target;
        if (resolutionAnchor2 == null || (resolutionAnchor = resolutionNode2.target) == null || resolutionAnchor2.state != 1 || resolutionAnchor.state != 1) {
            return false;
        }
        if (i6 > 0 && i6 != i7) {
            return false;
        }
        if (z3 || z || z2) {
            margin = constraintWidget4 != null ? constraintWidget4.mListAnchors[i3].getMargin() : 0.0f;
            if (constraintWidget5 != null) {
                margin += constraintWidget5.mListAnchors[i8].getMargin();
            }
        } else {
            margin = 0.0f;
        }
        float f6 = resolutionNode.target.resolvedOffset;
        float f7 = resolutionNode2.target.resolvedOffset;
        float f8 = (f6 < f7 ? f7 - f6 : f6 - f7) - f4;
        if (i6 > 0 && i6 == i7) {
            if (constraintWidget9.getParent() == null || constraintWidget9.getParent().mListDimensionBehaviors[i2] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                float f9 = (f8 + f4) - f5;
                float f10 = f6;
                ConstraintWidget constraintWidget11 = constraintWidget2;
                while (constraintWidget11 != null) {
                    Metrics metrics = LinearSystem.sMetrics;
                    if (metrics != null) {
                        metrics.nonresolvedWidgets--;
                        metrics.resolvedWidgets++;
                        metrics.chainConnectionResolved++;
                    }
                    ConstraintWidget constraintWidget12 = constraintWidget11.mNextChainWidget[i2];
                    if (constraintWidget12 != null || constraintWidget11 == constraintWidget3) {
                        float f11 = f9 / i6;
                        if (f3 > 0.0f) {
                            float[] fArr = constraintWidget11.mWeight;
                            if (fArr[i2] != -1.0f) {
                                f11 = (fArr[i2] * f9) / f3;
                            } else {
                                f2 = 0.0f;
                                if (constraintWidget11.getVisibility() == 8) {
                                    f2 = 0.0f;
                                }
                                float margin2 = f10 + constraintWidget11.mListAnchors[i3].getMargin();
                                constraintWidget11.mListAnchors[i3].getResolutionNode().resolve(resolutionNode.resolvedTarget, margin2);
                                float f12 = margin2 + f2;
                                constraintWidget11.mListAnchors[i8].getResolutionNode().resolve(resolutionNode.resolvedTarget, f12);
                                constraintWidget11.mListAnchors[i3].getResolutionNode().addResolvedValue(linearSystem);
                                constraintWidget11.mListAnchors[i8].getResolutionNode().addResolvedValue(linearSystem);
                                f10 = f12 + constraintWidget11.mListAnchors[i8].getMargin();
                            }
                        }
                        f2 = f11;
                        if (constraintWidget11.getVisibility() == 8) {
                        }
                        float margin22 = f10 + constraintWidget11.mListAnchors[i3].getMargin();
                        constraintWidget11.mListAnchors[i3].getResolutionNode().resolve(resolutionNode.resolvedTarget, margin22);
                        float f122 = margin22 + f2;
                        constraintWidget11.mListAnchors[i8].getResolutionNode().resolve(resolutionNode.resolvedTarget, f122);
                        constraintWidget11.mListAnchors[i3].getResolutionNode().addResolvedValue(linearSystem);
                        constraintWidget11.mListAnchors[i8].getResolutionNode().addResolvedValue(linearSystem);
                        f10 = f122 + constraintWidget11.mListAnchors[i8].getMargin();
                    }
                    constraintWidget11 = constraintWidget12;
                }
                return true;
            }
            return false;
        }
        if (f8 < 0.0f) {
            z3 = true;
            z = false;
            z2 = false;
        }
        if (z3) {
            ConstraintWidget constraintWidget13 = constraintWidget2;
            float biasPercent = f6 + ((f8 - margin) * constraintWidget13.getBiasPercent(i2));
            while (true) {
                ConstraintWidget constraintWidget14 = constraintWidget13;
                if (constraintWidget14 == null) {
                    return true;
                }
                Metrics metrics2 = LinearSystem.sMetrics;
                if (metrics2 != null) {
                    metrics2.nonresolvedWidgets--;
                    metrics2.resolvedWidgets++;
                    metrics2.chainConnectionResolved++;
                }
                constraintWidget13 = constraintWidget14.mNextChainWidget[i2];
                if (constraintWidget13 != null || constraintWidget14 == constraintWidget3) {
                    if (i2 == 0) {
                        height2 = constraintWidget14.getWidth();
                    } else {
                        height2 = constraintWidget14.getHeight();
                    }
                    float margin3 = biasPercent + constraintWidget14.mListAnchors[i3].getMargin();
                    constraintWidget14.mListAnchors[i3].getResolutionNode().resolve(resolutionNode.resolvedTarget, margin3);
                    float f13 = margin3 + height2;
                    constraintWidget14.mListAnchors[i8].getResolutionNode().resolve(resolutionNode.resolvedTarget, f13);
                    constraintWidget14.mListAnchors[i3].getResolutionNode().addResolvedValue(linearSystem);
                    constraintWidget14.mListAnchors[i8].getResolutionNode().addResolvedValue(linearSystem);
                    biasPercent = f13 + constraintWidget14.mListAnchors[i8].getMargin();
                }
            }
        } else {
            ConstraintWidget constraintWidget15 = constraintWidget2;
            if (!z && !z2) {
                return true;
            }
            if (z || z2) {
                f8 -= margin;
            }
            float f14 = f8 / (i7 + 1);
            if (z2) {
                f14 = f8 / (i7 > 1 ? (float) (i7 - 1) : 2.0f);
            }
            float f15 = constraintWidget15.getVisibility() != 8 ? f6 + f14 : f6;
            if (z2 && i7 > 1) {
                f15 = constraintWidget4.mListAnchors[i3].getMargin() + f6;
            }
            if (z && constraintWidget4 != null) {
                f15 += constraintWidget4.mListAnchors[i3].getMargin();
            }
            while (true) {
                ConstraintWidget constraintWidget16 = constraintWidget15;
                if (constraintWidget16 == null) {
                    return true;
                }
                Metrics metrics3 = LinearSystem.sMetrics;
                if (metrics3 != null) {
                    metrics3.nonresolvedWidgets--;
                    metrics3.resolvedWidgets++;
                    metrics3.chainConnectionResolved++;
                }
                constraintWidget15 = constraintWidget16.mNextChainWidget[i2];
                if (constraintWidget15 != null || constraintWidget16 == constraintWidget3) {
                    if (i2 == 0) {
                        height = constraintWidget16.getWidth();
                    } else {
                        height = constraintWidget16.getHeight();
                    }
                    float f16 = height;
                    if (constraintWidget16 != constraintWidget4) {
                        f15 += constraintWidget16.mListAnchors[i3].getMargin();
                    }
                    constraintWidget16.mListAnchors[i3].getResolutionNode().resolve(resolutionNode.resolvedTarget, f15);
                    constraintWidget16.mListAnchors[i8].getResolutionNode().resolve(resolutionNode.resolvedTarget, f15 + f16);
                    constraintWidget16.mListAnchors[i3].getResolutionNode().addResolvedValue(linearSystem);
                    constraintWidget16.mListAnchors[i8].getResolutionNode().addResolvedValue(linearSystem);
                    f15 += f16 + constraintWidget16.mListAnchors[i8].getMargin();
                    if (constraintWidget15 != null) {
                        if (constraintWidget15.getVisibility() != 8) {
                            f15 += f14;
                        }
                    }
                }
            }
        }
    }

    public static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidgetContainer.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour != dimensionBehaviour2 && constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            constraintAnchor.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.mRight;
            constraintAnchor2.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor2);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i2);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i2, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] == dimensionBehaviour2 || constraintWidget.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            return;
        }
        int i3 = constraintWidget.mTop.mMargin;
        int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
        ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
        constraintAnchor3.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor3);
        ConstraintAnchor constraintAnchor4 = constraintWidget.mBottom;
        constraintAnchor4.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor4);
        linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i3);
        linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
        if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
            ConstraintAnchor constraintAnchor5 = constraintWidget.mBaseline;
            constraintAnchor5.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor5);
            linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i3);
        }
        constraintWidget.mVerticalResolution = 2;
        constraintWidget.setVerticalDimension(i3, height);
    }

    private static boolean optimizableMatchConstraint(ConstraintWidget constraintWidget, int i2) {
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[i2];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour != dimensionBehaviour2) {
            return false;
        }
        if (constraintWidget.mDimensionRatio != 0.0f) {
            if (dimensionBehaviourArr[i2 != 0 ? (char) 0 : (char) 1] == dimensionBehaviour2) {
            }
            return false;
        }
        if (i2 == 0) {
            if (constraintWidget.mMatchConstraintDefaultWidth != 0 || constraintWidget.mMatchConstraintMinWidth != 0 || constraintWidget.mMatchConstraintMaxWidth != 0) {
                return false;
            }
        } else if (constraintWidget.mMatchConstraintDefaultHeight != 0 || constraintWidget.mMatchConstraintMinHeight != 0 || constraintWidget.mMatchConstraintMaxHeight != 0) {
            return false;
        }
        return true;
    }

    public static void setOptimizedWidget(ConstraintWidget constraintWidget, int i2, int i3) {
        int i4 = i2 * 2;
        int i5 = i4 + 1;
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedTarget = constraintWidget.getParent().mLeft.getResolutionNode();
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedOffset = i3;
        constraintWidget.mListAnchors[i4].getResolutionNode().state = 1;
        constraintWidget.mListAnchors[i5].getResolutionNode().resolvedTarget = constraintWidget.mListAnchors[i4].getResolutionNode();
        constraintWidget.mListAnchors[i5].getResolutionNode().resolvedOffset = constraintWidget.getLength(i2);
        constraintWidget.mListAnchors[i5].getResolutionNode().state = 1;
    }
}
