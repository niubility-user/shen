package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_inv_state {
    public static final pjsip_inv_state PJSIP_INV_STATE_CALLING;
    public static final pjsip_inv_state PJSIP_INV_STATE_CONFIRMED;
    public static final pjsip_inv_state PJSIP_INV_STATE_CONNECTING;
    public static final pjsip_inv_state PJSIP_INV_STATE_DISCONNECTED;
    public static final pjsip_inv_state PJSIP_INV_STATE_EARLY;
    public static final pjsip_inv_state PJSIP_INV_STATE_INCOMING;
    public static final pjsip_inv_state PJSIP_INV_STATE_NULL;
    private static int swigNext;
    private static pjsip_inv_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_inv_state pjsip_inv_stateVar = new pjsip_inv_state("PJSIP_INV_STATE_NULL");
        PJSIP_INV_STATE_NULL = pjsip_inv_stateVar;
        pjsip_inv_state pjsip_inv_stateVar2 = new pjsip_inv_state("PJSIP_INV_STATE_CALLING");
        PJSIP_INV_STATE_CALLING = pjsip_inv_stateVar2;
        pjsip_inv_state pjsip_inv_stateVar3 = new pjsip_inv_state("PJSIP_INV_STATE_INCOMING");
        PJSIP_INV_STATE_INCOMING = pjsip_inv_stateVar3;
        pjsip_inv_state pjsip_inv_stateVar4 = new pjsip_inv_state("PJSIP_INV_STATE_EARLY");
        PJSIP_INV_STATE_EARLY = pjsip_inv_stateVar4;
        pjsip_inv_state pjsip_inv_stateVar5 = new pjsip_inv_state("PJSIP_INV_STATE_CONNECTING");
        PJSIP_INV_STATE_CONNECTING = pjsip_inv_stateVar5;
        pjsip_inv_state pjsip_inv_stateVar6 = new pjsip_inv_state("PJSIP_INV_STATE_CONFIRMED");
        PJSIP_INV_STATE_CONFIRMED = pjsip_inv_stateVar6;
        pjsip_inv_state pjsip_inv_stateVar7 = new pjsip_inv_state("PJSIP_INV_STATE_DISCONNECTED");
        PJSIP_INV_STATE_DISCONNECTED = pjsip_inv_stateVar7;
        swigValues = new pjsip_inv_state[]{pjsip_inv_stateVar, pjsip_inv_stateVar2, pjsip_inv_stateVar3, pjsip_inv_stateVar4, pjsip_inv_stateVar5, pjsip_inv_stateVar6, pjsip_inv_stateVar7};
        swigNext = 0;
    }

    private pjsip_inv_state(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_inv_state swigToEnum(int i2) {
        pjsip_inv_state[] pjsip_inv_stateVarArr = swigValues;
        if (i2 < pjsip_inv_stateVarArr.length && i2 >= 0 && pjsip_inv_stateVarArr[i2].swigValue == i2) {
            return pjsip_inv_stateVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_inv_state[] pjsip_inv_stateVarArr2 = swigValues;
            if (i3 < pjsip_inv_stateVarArr2.length) {
                if (pjsip_inv_stateVarArr2[i3].swigValue == i2) {
                    return pjsip_inv_stateVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_inv_state.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_inv_state(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_inv_state(String str, pjsip_inv_state pjsip_inv_stateVar) {
        this.swigName = str;
        int i2 = pjsip_inv_stateVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
