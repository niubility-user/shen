package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_call_hold_type {
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC2543;
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC3264;
    private static int swigNext;
    private static pjsua_call_hold_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_call_hold_type pjsua_call_hold_typeVar = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC3264");
        PJSUA_CALL_HOLD_TYPE_RFC3264 = pjsua_call_hold_typeVar;
        pjsua_call_hold_type pjsua_call_hold_typeVar2 = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC2543");
        PJSUA_CALL_HOLD_TYPE_RFC2543 = pjsua_call_hold_typeVar2;
        swigValues = new pjsua_call_hold_type[]{pjsua_call_hold_typeVar, pjsua_call_hold_typeVar2};
        swigNext = 0;
    }

    private pjsua_call_hold_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_call_hold_type swigToEnum(int i2) {
        pjsua_call_hold_type[] pjsua_call_hold_typeVarArr = swigValues;
        if (i2 < pjsua_call_hold_typeVarArr.length && i2 >= 0 && pjsua_call_hold_typeVarArr[i2].swigValue == i2) {
            return pjsua_call_hold_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_call_hold_type[] pjsua_call_hold_typeVarArr2 = swigValues;
            if (i3 < pjsua_call_hold_typeVarArr2.length) {
                if (pjsua_call_hold_typeVarArr2[i3].swigValue == i2) {
                    return pjsua_call_hold_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_call_hold_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_hold_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_call_hold_type(String str, pjsua_call_hold_type pjsua_call_hold_typeVar) {
        this.swigName = str;
        int i2 = pjsua_call_hold_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
