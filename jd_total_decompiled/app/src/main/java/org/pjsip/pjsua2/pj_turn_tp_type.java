package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_turn_tp_type {
    public static final pj_turn_tp_type PJ_TURN_TP_TCP;
    public static final pj_turn_tp_type PJ_TURN_TP_TLS;
    public static final pj_turn_tp_type PJ_TURN_TP_UDP;
    private static int swigNext;
    private static pj_turn_tp_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_turn_tp_type pj_turn_tp_typeVar = new pj_turn_tp_type("PJ_TURN_TP_UDP", pjsua2JNI.PJ_TURN_TP_UDP_get());
        PJ_TURN_TP_UDP = pj_turn_tp_typeVar;
        pj_turn_tp_type pj_turn_tp_typeVar2 = new pj_turn_tp_type("PJ_TURN_TP_TCP", pjsua2JNI.PJ_TURN_TP_TCP_get());
        PJ_TURN_TP_TCP = pj_turn_tp_typeVar2;
        pj_turn_tp_type pj_turn_tp_typeVar3 = new pj_turn_tp_type("PJ_TURN_TP_TLS", pjsua2JNI.PJ_TURN_TP_TLS_get());
        PJ_TURN_TP_TLS = pj_turn_tp_typeVar3;
        swigValues = new pj_turn_tp_type[]{pj_turn_tp_typeVar, pj_turn_tp_typeVar2, pj_turn_tp_typeVar3};
        swigNext = 0;
    }

    private pj_turn_tp_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_turn_tp_type swigToEnum(int i2) {
        pj_turn_tp_type[] pj_turn_tp_typeVarArr = swigValues;
        if (i2 < pj_turn_tp_typeVarArr.length && i2 >= 0 && pj_turn_tp_typeVarArr[i2].swigValue == i2) {
            return pj_turn_tp_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_turn_tp_type[] pj_turn_tp_typeVarArr2 = swigValues;
            if (i3 < pj_turn_tp_typeVarArr2.length) {
                if (pj_turn_tp_typeVarArr2[i3].swigValue == i2) {
                    return pj_turn_tp_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_turn_tp_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_turn_tp_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_turn_tp_type(String str, pj_turn_tp_type pj_turn_tp_typeVar) {
        this.swigName = str;
        int i2 = pj_turn_tp_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
