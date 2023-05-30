package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r37, androidx.constraintlayout.solver.LinearSystem r38, int r39, int r40, androidx.constraintlayout.solver.widgets.ChainHead r41) {
        /*
            Method dump skipped, instructions count: 1276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
