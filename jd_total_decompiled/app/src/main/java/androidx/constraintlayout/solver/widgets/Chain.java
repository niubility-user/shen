package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i2) {
        int i3;
        int i4;
        ChainHead[] chainHeadArr;
        if (i2 == 0) {
            int i5 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i4 = i5;
            i3 = 0;
        } else {
            i3 = 2;
            i4 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i2, i3, chainHead)) {
                    applyChainConstraints(constraintWidgetContainer, linearSystem, i2, i3, chainHead);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i2, i3, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r8 == 2) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0040, code lost:
        if (r8 == 2) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0042, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
        r5 = false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0366 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i2, int i3, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ArrayList<ConstraintWidget> arrayList;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int i4;
        ConstraintWidget constraintWidget2;
        int i5;
        SolverVariable solverVariable;
        ConstraintAnchor constraintAnchor4;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget3;
        ConstraintAnchor constraintAnchor5;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        ConstraintWidget constraintWidget4;
        SolverVariable solverVariable5;
        float f2;
        int size;
        int i6;
        ArrayList<ConstraintWidget> arrayList2;
        int i7;
        boolean z4;
        int i8;
        boolean z5;
        ConstraintWidget constraintWidget5;
        boolean z6;
        int i9;
        ConstraintWidget constraintWidget6 = chainHead.mFirst;
        ConstraintWidget constraintWidget7 = chainHead.mLast;
        ConstraintWidget constraintWidget8 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget9 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget10 = chainHead.mHead;
        float f3 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget11 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget12 = chainHead.mLastMatchConstraintWidget;
        boolean z7 = constraintWidgetContainer.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i2 == 0) {
            int i10 = constraintWidget10.mHorizontalChainStyle;
            z = i10 == 0;
            z2 = i10 == 1;
        } else {
            int i11 = constraintWidget10.mVerticalChainStyle;
            z = i11 == 0;
            z2 = i11 == 1;
        }
        ConstraintWidget constraintWidget13 = constraintWidget6;
        boolean z8 = false;
        while (true) {
            if (z8) {
                break;
            }
            ConstraintAnchor constraintAnchor6 = constraintWidget13.mListAnchors[i3];
            int i12 = (z7 || z3) ? 1 : 4;
            int margin = constraintAnchor6.getMargin();
            float f4 = f3;
            ConstraintAnchor constraintAnchor7 = constraintAnchor6.mTarget;
            if (constraintAnchor7 != null && constraintWidget13 != constraintWidget6) {
                margin += constraintAnchor7.getMargin();
            }
            int i13 = margin;
            if (z3 && constraintWidget13 != constraintWidget6 && constraintWidget13 != constraintWidget8) {
                z4 = z8;
                z5 = z2;
                i8 = 6;
            } else if (z && z7) {
                z4 = z8;
                z5 = z2;
                i8 = 4;
            } else {
                z4 = z8;
                i8 = i12;
                z5 = z2;
            }
            ConstraintAnchor constraintAnchor8 = constraintAnchor6.mTarget;
            if (constraintAnchor8 != null) {
                if (constraintWidget13 == constraintWidget8) {
                    z6 = z;
                    constraintWidget5 = constraintWidget10;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor8.mSolverVariable, i13, 5);
                } else {
                    constraintWidget5 = constraintWidget10;
                    z6 = z;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor8.mSolverVariable, i13, 6);
                }
                linearSystem.addEquality(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i13, i8);
            } else {
                constraintWidget5 = constraintWidget10;
                z6 = z;
            }
            if (z7) {
                if (constraintWidget13.getVisibility() == 8 || constraintWidget13.mListDimensionBehaviors[i2] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i9 = 0;
                } else {
                    ConstraintAnchor[] constraintAnchorArr = constraintWidget13.mListAnchors;
                    i9 = 0;
                    linearSystem.addGreaterThan(constraintAnchorArr[i3 + 1].mSolverVariable, constraintAnchorArr[i3].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(constraintWidget13.mListAnchors[i3].mSolverVariable, constraintWidgetContainer.mListAnchors[i3].mSolverVariable, i9, 6);
            }
            ConstraintAnchor constraintAnchor9 = constraintWidget13.mListAnchors[i3 + 1].mTarget;
            if (constraintAnchor9 != null) {
                ConstraintWidget constraintWidget14 = constraintAnchor9.mOwner;
                ConstraintAnchor[] constraintAnchorArr2 = constraintWidget14.mListAnchors;
                if (constraintAnchorArr2[i3].mTarget != null && constraintAnchorArr2[i3].mTarget.mOwner == constraintWidget13) {
                    r21 = constraintWidget14;
                }
            }
            if (r21 != null) {
                constraintWidget13 = r21;
                z8 = z4;
            } else {
                z8 = true;
            }
            z2 = z5;
            f3 = f4;
            z = z6;
            constraintWidget10 = constraintWidget5;
        }
        ConstraintWidget constraintWidget15 = constraintWidget10;
        float f5 = f3;
        boolean z9 = z;
        boolean z10 = z2;
        if (constraintWidget9 != null) {
            ConstraintAnchor[] constraintAnchorArr3 = constraintWidget7.mListAnchors;
            int i14 = i3 + 1;
            if (constraintAnchorArr3[i14].mTarget != null) {
                ConstraintAnchor constraintAnchor10 = constraintWidget9.mListAnchors[i14];
                linearSystem.addLowerThan(constraintAnchor10.mSolverVariable, constraintAnchorArr3[i14].mTarget.mSolverVariable, -constraintAnchor10.getMargin(), 5);
                if (z7) {
                    int i15 = i3 + 1;
                    SolverVariable solverVariable6 = constraintWidgetContainer.mListAnchors[i15].mSolverVariable;
                    ConstraintAnchor[] constraintAnchorArr4 = constraintWidget7.mListAnchors;
                    linearSystem.addGreaterThan(solverVariable6, constraintAnchorArr4[i15].mSolverVariable, constraintAnchorArr4[i15].getMargin(), 6);
                }
                arrayList = chainHead.mWeightedMatchConstraintsWidgets;
                if (arrayList != null && (size = arrayList.size()) > 1) {
                    float f6 = (chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f5 : chainHead.mWidgetsMatchCount;
                    float f7 = 0.0f;
                    ConstraintWidget constraintWidget16 = null;
                    i6 = 0;
                    float f8 = 0.0f;
                    while (i6 < size) {
                        ConstraintWidget constraintWidget17 = arrayList.get(i6);
                        float f9 = constraintWidget17.mWeight[i2];
                        if (f9 < f7) {
                            if (chainHead.mHasComplexMatchWeights) {
                                ConstraintAnchor[] constraintAnchorArr5 = constraintWidget17.mListAnchors;
                                linearSystem.addEquality(constraintAnchorArr5[i3 + 1].mSolverVariable, constraintAnchorArr5[i3].mSolverVariable, 0, 4);
                                arrayList2 = arrayList;
                                i7 = size;
                                i6++;
                                size = i7;
                                arrayList = arrayList2;
                                f7 = 0.0f;
                            } else {
                                f9 = 1.0f;
                                f7 = 0.0f;
                            }
                        }
                        if (f9 == f7) {
                            ConstraintAnchor[] constraintAnchorArr6 = constraintWidget17.mListAnchors;
                            linearSystem.addEquality(constraintAnchorArr6[i3 + 1].mSolverVariable, constraintAnchorArr6[i3].mSolverVariable, 0, 6);
                            arrayList2 = arrayList;
                            i7 = size;
                            i6++;
                            size = i7;
                            arrayList = arrayList2;
                            f7 = 0.0f;
                        } else {
                            if (constraintWidget16 != null) {
                                ConstraintAnchor[] constraintAnchorArr7 = constraintWidget16.mListAnchors;
                                SolverVariable solverVariable7 = constraintAnchorArr7[i3].mSolverVariable;
                                int i16 = i3 + 1;
                                SolverVariable solverVariable8 = constraintAnchorArr7[i16].mSolverVariable;
                                ConstraintAnchor[] constraintAnchorArr8 = constraintWidget17.mListAnchors;
                                arrayList2 = arrayList;
                                SolverVariable solverVariable9 = constraintAnchorArr8[i3].mSolverVariable;
                                SolverVariable solverVariable10 = constraintAnchorArr8[i16].mSolverVariable;
                                i7 = size;
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualMatchDimensions(f8, f6, f9, solverVariable7, solverVariable8, solverVariable9, solverVariable10);
                                linearSystem.addConstraint(createRow);
                            } else {
                                arrayList2 = arrayList;
                                i7 = size;
                            }
                            f8 = f9;
                            constraintWidget16 = constraintWidget17;
                            i6++;
                            size = i7;
                            arrayList = arrayList2;
                            f7 = 0.0f;
                        }
                    }
                }
                if (constraintWidget8 == null && (constraintWidget8 == constraintWidget9 || z3)) {
                    ConstraintAnchor[] constraintAnchorArr9 = constraintWidget6.mListAnchors;
                    ConstraintAnchor constraintAnchor11 = constraintAnchorArr9[i3];
                    ConstraintAnchor[] constraintAnchorArr10 = constraintWidget7.mListAnchors;
                    int i17 = i3 + 1;
                    ConstraintAnchor constraintAnchor12 = constraintAnchorArr10[i17];
                    SolverVariable solverVariable11 = constraintAnchorArr9[i3].mTarget != null ? constraintAnchorArr9[i3].mTarget.mSolverVariable : null;
                    SolverVariable solverVariable12 = constraintAnchorArr10[i17].mTarget != null ? constraintAnchorArr10[i17].mTarget.mSolverVariable : null;
                    if (constraintWidget8 == constraintWidget9) {
                        ConstraintAnchor[] constraintAnchorArr11 = constraintWidget8.mListAnchors;
                        constraintAnchor11 = constraintAnchorArr11[i3];
                        constraintAnchor12 = constraintAnchorArr11[i17];
                    }
                    if (solverVariable11 != null && solverVariable12 != null) {
                        if (i2 == 0) {
                            f2 = constraintWidget15.mHorizontalBiasPercent;
                        } else {
                            f2 = constraintWidget15.mVerticalBiasPercent;
                        }
                        linearSystem.addCentering(constraintAnchor11.mSolverVariable, solverVariable11, constraintAnchor11.getMargin(), f2, solverVariable12, constraintAnchor12.mSolverVariable, constraintAnchor12.getMargin(), 5);
                    }
                } else if (z9 || constraintWidget8 == null) {
                    int i18 = 8;
                    if (z10 && constraintWidget8 != null) {
                        int i19 = chainHead.mWidgetsMatchCount;
                        boolean z11 = i19 <= 0 && chainHead.mWidgetsCount == i19;
                        constraintWidget = constraintWidget8;
                        ConstraintWidget constraintWidget18 = constraintWidget;
                        while (constraintWidget != null) {
                            ConstraintWidget constraintWidget19 = constraintWidget.mNextChainWidget[i2];
                            while (constraintWidget19 != null && constraintWidget19.getVisibility() == i18) {
                                constraintWidget19 = constraintWidget19.mNextChainWidget[i2];
                            }
                            if (constraintWidget == constraintWidget8 || constraintWidget == constraintWidget9 || constraintWidget19 == null) {
                                constraintWidget2 = constraintWidget18;
                                i5 = 8;
                            } else {
                                ConstraintWidget constraintWidget20 = constraintWidget19 == constraintWidget9 ? null : constraintWidget19;
                                ConstraintAnchor constraintAnchor13 = constraintWidget.mListAnchors[i3];
                                SolverVariable solverVariable13 = constraintAnchor13.mSolverVariable;
                                ConstraintAnchor constraintAnchor14 = constraintAnchor13.mTarget;
                                if (constraintAnchor14 != null) {
                                    SolverVariable solverVariable14 = constraintAnchor14.mSolverVariable;
                                }
                                int i20 = i3 + 1;
                                SolverVariable solverVariable15 = constraintWidget18.mListAnchors[i20].mSolverVariable;
                                int margin2 = constraintAnchor13.getMargin();
                                int margin3 = constraintWidget.mListAnchors[i20].getMargin();
                                if (constraintWidget20 != null) {
                                    constraintAnchor4 = constraintWidget20.mListAnchors[i3];
                                    solverVariable2 = constraintAnchor4.mSolverVariable;
                                    ConstraintAnchor constraintAnchor15 = constraintAnchor4.mTarget;
                                    solverVariable = constraintAnchor15 != null ? constraintAnchor15.mSolverVariable : null;
                                } else {
                                    ConstraintAnchor[] constraintAnchorArr12 = constraintWidget.mListAnchors;
                                    ConstraintAnchor constraintAnchor16 = constraintAnchorArr12[i20].mTarget;
                                    SolverVariable solverVariable16 = constraintAnchor16 != null ? constraintAnchor16.mSolverVariable : null;
                                    solverVariable = constraintAnchorArr12[i20].mSolverVariable;
                                    constraintAnchor4 = constraintAnchor16;
                                    solverVariable2 = solverVariable16;
                                }
                                if (constraintAnchor4 != null) {
                                    margin3 += constraintAnchor4.getMargin();
                                }
                                int i21 = margin3;
                                if (constraintWidget18 != null) {
                                    margin2 += constraintWidget18.mListAnchors[i20].getMargin();
                                }
                                int i22 = margin2;
                                int i23 = z11 ? 6 : 4;
                                if (solverVariable13 == null || solverVariable15 == null || solverVariable2 == null || solverVariable == null) {
                                    constraintWidget3 = constraintWidget20;
                                    constraintWidget2 = constraintWidget18;
                                    i5 = 8;
                                } else {
                                    constraintWidget3 = constraintWidget20;
                                    constraintWidget2 = constraintWidget18;
                                    i5 = 8;
                                    linearSystem.addCentering(solverVariable13, solverVariable15, i22, 0.5f, solverVariable2, solverVariable, i21, i23);
                                }
                                constraintWidget19 = constraintWidget3;
                            }
                            if (constraintWidget.getVisibility() == i5) {
                                constraintWidget = constraintWidget2;
                            }
                            constraintWidget18 = constraintWidget;
                            i18 = 8;
                            constraintWidget = constraintWidget19;
                        }
                        ConstraintAnchor constraintAnchor17 = constraintWidget8.mListAnchors[i3];
                        constraintAnchor = constraintWidget6.mListAnchors[i3].mTarget;
                        int i24 = i3 + 1;
                        constraintAnchor2 = constraintWidget9.mListAnchors[i24];
                        constraintAnchor3 = constraintWidget7.mListAnchors[i24].mTarget;
                        if (constraintAnchor != null) {
                            i4 = 5;
                        } else if (constraintWidget8 != constraintWidget9) {
                            i4 = 5;
                            linearSystem.addEquality(constraintAnchor17.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor17.getMargin(), 5);
                        } else {
                            i4 = 5;
                            if (constraintAnchor3 != null) {
                                linearSystem.addCentering(constraintAnchor17.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor17.getMargin(), 0.5f, constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, constraintAnchor2.getMargin(), 5);
                            }
                        }
                        if (constraintAnchor3 != null && constraintWidget8 != constraintWidget9) {
                            linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i4);
                        }
                    }
                } else {
                    int i25 = chainHead.mWidgetsMatchCount;
                    boolean z12 = i25 > 0 && chainHead.mWidgetsCount == i25;
                    ConstraintWidget constraintWidget21 = constraintWidget8;
                    ConstraintWidget constraintWidget22 = constraintWidget21;
                    while (constraintWidget21 != null) {
                        ConstraintWidget constraintWidget23 = constraintWidget21.mNextChainWidget[i2];
                        while (constraintWidget23 != null && constraintWidget23.getVisibility() == 8) {
                            constraintWidget23 = constraintWidget23.mNextChainWidget[i2];
                        }
                        if (constraintWidget23 != null || constraintWidget21 == constraintWidget9) {
                            ConstraintAnchor constraintAnchor18 = constraintWidget21.mListAnchors[i3];
                            SolverVariable solverVariable17 = constraintAnchor18.mSolverVariable;
                            ConstraintAnchor constraintAnchor19 = constraintAnchor18.mTarget;
                            SolverVariable solverVariable18 = constraintAnchor19 != null ? constraintAnchor19.mSolverVariable : null;
                            if (constraintWidget22 != constraintWidget21) {
                                solverVariable18 = constraintWidget22.mListAnchors[i3 + 1].mSolverVariable;
                            } else if (constraintWidget21 == constraintWidget8 && constraintWidget22 == constraintWidget21) {
                                ConstraintAnchor[] constraintAnchorArr13 = constraintWidget6.mListAnchors;
                                solverVariable18 = constraintAnchorArr13[i3].mTarget != null ? constraintAnchorArr13[i3].mTarget.mSolverVariable : null;
                            }
                            int margin4 = constraintAnchor18.getMargin();
                            int i26 = i3 + 1;
                            int margin5 = constraintWidget21.mListAnchors[i26].getMargin();
                            if (constraintWidget23 != null) {
                                constraintAnchor5 = constraintWidget23.mListAnchors[i3];
                                SolverVariable solverVariable19 = constraintAnchor5.mSolverVariable;
                                solverVariable4 = constraintWidget21.mListAnchors[i26].mSolverVariable;
                                solverVariable3 = solverVariable19;
                            } else {
                                constraintAnchor5 = constraintWidget7.mListAnchors[i26].mTarget;
                                solverVariable3 = constraintAnchor5 != null ? constraintAnchor5.mSolverVariable : null;
                                solverVariable4 = constraintWidget21.mListAnchors[i26].mSolverVariable;
                            }
                            if (constraintAnchor5 != null) {
                                margin5 += constraintAnchor5.getMargin();
                            }
                            if (constraintWidget22 != null) {
                                margin4 += constraintWidget22.mListAnchors[i26].getMargin();
                            }
                            if (solverVariable17 != null && solverVariable18 != null && solverVariable3 != null && solverVariable4 != null) {
                                if (constraintWidget21 == constraintWidget8) {
                                    margin4 = constraintWidget8.mListAnchors[i3].getMargin();
                                }
                                int i27 = margin4;
                                constraintWidget4 = constraintWidget23;
                                linearSystem.addCentering(solverVariable17, solverVariable18, i27, 0.5f, solverVariable3, solverVariable4, constraintWidget21 == constraintWidget9 ? constraintWidget9.mListAnchors[i26].getMargin() : margin5, z12 ? 6 : 4);
                                if (constraintWidget21.getVisibility() == 8) {
                                    constraintWidget22 = constraintWidget21;
                                }
                                constraintWidget21 = constraintWidget4;
                            }
                        }
                        constraintWidget4 = constraintWidget23;
                        if (constraintWidget21.getVisibility() == 8) {
                        }
                        constraintWidget21 = constraintWidget4;
                    }
                }
                if ((!z9 || z10) && constraintWidget8 != null) {
                    ConstraintAnchor[] constraintAnchorArr14 = constraintWidget8.mListAnchors;
                    ConstraintAnchor constraintAnchor20 = constraintAnchorArr14[i3];
                    int i28 = i3 + 1;
                    ConstraintAnchor constraintAnchor21 = constraintWidget9.mListAnchors[i28];
                    ConstraintAnchor constraintAnchor22 = constraintAnchor20.mTarget;
                    solverVariable5 = constraintAnchor22 == null ? constraintAnchor22.mSolverVariable : null;
                    ConstraintAnchor constraintAnchor23 = constraintAnchor21.mTarget;
                    SolverVariable solverVariable20 = constraintAnchor23 == null ? constraintAnchor23.mSolverVariable : null;
                    if (constraintWidget7 != constraintWidget9) {
                        ConstraintAnchor constraintAnchor24 = constraintWidget7.mListAnchors[i28].mTarget;
                        solverVariable20 = constraintAnchor24 != null ? constraintAnchor24.mSolverVariable : null;
                    }
                    if (constraintWidget8 == constraintWidget9) {
                        constraintAnchor20 = constraintAnchorArr14[i3];
                        constraintAnchor21 = constraintAnchorArr14[i28];
                    }
                    if (solverVariable5 != null || solverVariable20 == null) {
                    }
                    int margin6 = constraintAnchor20.getMargin();
                    if (constraintWidget9 != null) {
                        constraintWidget7 = constraintWidget9;
                    }
                    linearSystem.addCentering(constraintAnchor20.mSolverVariable, solverVariable5, margin6, 0.5f, solverVariable20, constraintAnchor21.mSolverVariable, constraintWidget7.mListAnchors[i28].getMargin(), 5);
                    return;
                }
                return;
            }
        }
        if (z7) {
        }
        arrayList = chainHead.mWeightedMatchConstraintsWidgets;
        if (arrayList != null) {
            if (chainHead.mHasUndefinedWeights) {
            }
            float f72 = 0.0f;
            ConstraintWidget constraintWidget162 = null;
            i6 = 0;
            float f82 = 0.0f;
            while (i6 < size) {
            }
        }
        if (constraintWidget8 == null) {
        }
        if (z9) {
        }
        int i182 = 8;
        if (z10) {
            int i192 = chainHead.mWidgetsMatchCount;
            if (i192 <= 0) {
            }
            constraintWidget = constraintWidget8;
            ConstraintWidget constraintWidget182 = constraintWidget;
            while (constraintWidget != null) {
            }
            ConstraintAnchor constraintAnchor172 = constraintWidget8.mListAnchors[i3];
            constraintAnchor = constraintWidget6.mListAnchors[i3].mTarget;
            int i242 = i3 + 1;
            constraintAnchor2 = constraintWidget9.mListAnchors[i242];
            constraintAnchor3 = constraintWidget7.mListAnchors[i242].mTarget;
            if (constraintAnchor != null) {
            }
            if (constraintAnchor3 != null) {
                linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i4);
            }
        }
        if (z9) {
        }
        ConstraintAnchor[] constraintAnchorArr142 = constraintWidget8.mListAnchors;
        ConstraintAnchor constraintAnchor202 = constraintAnchorArr142[i3];
        int i282 = i3 + 1;
        ConstraintAnchor constraintAnchor212 = constraintWidget9.mListAnchors[i282];
        ConstraintAnchor constraintAnchor222 = constraintAnchor202.mTarget;
        if (constraintAnchor222 == null) {
        }
        ConstraintAnchor constraintAnchor232 = constraintAnchor212.mTarget;
        if (constraintAnchor232 == null) {
        }
        if (constraintWidget7 != constraintWidget9) {
        }
        if (constraintWidget8 == constraintWidget9) {
        }
        if (solverVariable5 != null) {
        }
    }
}
