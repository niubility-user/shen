package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_tsx_state_e {
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CALLING;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_COMPLETED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CONFIRMED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_DESTROYED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_MAX;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_NULL;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_PROCEEDING;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TERMINATED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TRYING;
    private static int swigNext;
    private static pjsip_tsx_state_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_tsx_state_e pjsip_tsx_state_eVar = new pjsip_tsx_state_e("PJSIP_TSX_STATE_NULL");
        PJSIP_TSX_STATE_NULL = pjsip_tsx_state_eVar;
        pjsip_tsx_state_e pjsip_tsx_state_eVar2 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CALLING");
        PJSIP_TSX_STATE_CALLING = pjsip_tsx_state_eVar2;
        pjsip_tsx_state_e pjsip_tsx_state_eVar3 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TRYING");
        PJSIP_TSX_STATE_TRYING = pjsip_tsx_state_eVar3;
        pjsip_tsx_state_e pjsip_tsx_state_eVar4 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_PROCEEDING");
        PJSIP_TSX_STATE_PROCEEDING = pjsip_tsx_state_eVar4;
        pjsip_tsx_state_e pjsip_tsx_state_eVar5 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_COMPLETED");
        PJSIP_TSX_STATE_COMPLETED = pjsip_tsx_state_eVar5;
        pjsip_tsx_state_e pjsip_tsx_state_eVar6 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CONFIRMED");
        PJSIP_TSX_STATE_CONFIRMED = pjsip_tsx_state_eVar6;
        pjsip_tsx_state_e pjsip_tsx_state_eVar7 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TERMINATED");
        PJSIP_TSX_STATE_TERMINATED = pjsip_tsx_state_eVar7;
        pjsip_tsx_state_e pjsip_tsx_state_eVar8 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_DESTROYED");
        PJSIP_TSX_STATE_DESTROYED = pjsip_tsx_state_eVar8;
        pjsip_tsx_state_e pjsip_tsx_state_eVar9 = new pjsip_tsx_state_e("PJSIP_TSX_STATE_MAX");
        PJSIP_TSX_STATE_MAX = pjsip_tsx_state_eVar9;
        swigValues = new pjsip_tsx_state_e[]{pjsip_tsx_state_eVar, pjsip_tsx_state_eVar2, pjsip_tsx_state_eVar3, pjsip_tsx_state_eVar4, pjsip_tsx_state_eVar5, pjsip_tsx_state_eVar6, pjsip_tsx_state_eVar7, pjsip_tsx_state_eVar8, pjsip_tsx_state_eVar9};
        swigNext = 0;
    }

    private pjsip_tsx_state_e(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_tsx_state_e swigToEnum(int i2) {
        pjsip_tsx_state_e[] pjsip_tsx_state_eVarArr = swigValues;
        if (i2 < pjsip_tsx_state_eVarArr.length && i2 >= 0 && pjsip_tsx_state_eVarArr[i2].swigValue == i2) {
            return pjsip_tsx_state_eVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_tsx_state_e[] pjsip_tsx_state_eVarArr2 = swigValues;
            if (i3 < pjsip_tsx_state_eVarArr2.length) {
                if (pjsip_tsx_state_eVarArr2[i3].swigValue == i2) {
                    return pjsip_tsx_state_eVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_tsx_state_e.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_tsx_state_e(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_tsx_state_e(String str, pjsip_tsx_state_e pjsip_tsx_state_eVar) {
        this.swigName = str;
        int i2 = pjsip_tsx_state_eVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
