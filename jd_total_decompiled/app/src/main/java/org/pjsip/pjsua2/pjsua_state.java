package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_state {
    public static final pjsua_state PJSUA_STATE_CLOSING;
    public static final pjsua_state PJSUA_STATE_CREATED;
    public static final pjsua_state PJSUA_STATE_INIT;
    public static final pjsua_state PJSUA_STATE_NULL;
    public static final pjsua_state PJSUA_STATE_RUNNING;
    public static final pjsua_state PJSUA_STATE_STARTING;
    private static int swigNext;
    private static pjsua_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_state pjsua_stateVar = new pjsua_state("PJSUA_STATE_NULL");
        PJSUA_STATE_NULL = pjsua_stateVar;
        pjsua_state pjsua_stateVar2 = new pjsua_state("PJSUA_STATE_CREATED");
        PJSUA_STATE_CREATED = pjsua_stateVar2;
        pjsua_state pjsua_stateVar3 = new pjsua_state("PJSUA_STATE_INIT");
        PJSUA_STATE_INIT = pjsua_stateVar3;
        pjsua_state pjsua_stateVar4 = new pjsua_state("PJSUA_STATE_STARTING");
        PJSUA_STATE_STARTING = pjsua_stateVar4;
        pjsua_state pjsua_stateVar5 = new pjsua_state("PJSUA_STATE_RUNNING");
        PJSUA_STATE_RUNNING = pjsua_stateVar5;
        pjsua_state pjsua_stateVar6 = new pjsua_state("PJSUA_STATE_CLOSING");
        PJSUA_STATE_CLOSING = pjsua_stateVar6;
        swigValues = new pjsua_state[]{pjsua_stateVar, pjsua_stateVar2, pjsua_stateVar3, pjsua_stateVar4, pjsua_stateVar5, pjsua_stateVar6};
        swigNext = 0;
    }

    private pjsua_state(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_state swigToEnum(int i2) {
        pjsua_state[] pjsua_stateVarArr = swigValues;
        if (i2 < pjsua_stateVarArr.length && i2 >= 0 && pjsua_stateVarArr[i2].swigValue == i2) {
            return pjsua_stateVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_state[] pjsua_stateVarArr2 = swigValues;
            if (i3 < pjsua_stateVarArr2.length) {
                if (pjsua_stateVarArr2[i3].swigValue == i2) {
                    return pjsua_stateVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_state.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_state(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_state(String str, pjsua_state pjsua_stateVar) {
        this.swigName = str;
        int i2 = pjsua_stateVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
