package com.eclipsesource.v8.debug;

/* loaded from: classes.dex */
public enum StepAction {
    STEP_OUT(0),
    STEP_NEXT(1),
    STEP_IN(2),
    STEP_FRAME(3);
    
    int index;

    StepAction(int i2) {
        this.index = i2;
    }
}
