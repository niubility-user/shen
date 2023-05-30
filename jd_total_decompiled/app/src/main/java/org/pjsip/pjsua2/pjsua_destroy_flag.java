package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_destroy_flag {
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_NETWORK;
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_RX_MSG;
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_TX_MSG;
    private static int swigNext;
    private static pjsua_destroy_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_destroy_flag pjsua_destroy_flagVar = new pjsua_destroy_flag("PJSUA_DESTROY_NO_RX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_RX_MSG_get());
        PJSUA_DESTROY_NO_RX_MSG = pjsua_destroy_flagVar;
        pjsua_destroy_flag pjsua_destroy_flagVar2 = new pjsua_destroy_flag("PJSUA_DESTROY_NO_TX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_TX_MSG_get());
        PJSUA_DESTROY_NO_TX_MSG = pjsua_destroy_flagVar2;
        pjsua_destroy_flag pjsua_destroy_flagVar3 = new pjsua_destroy_flag("PJSUA_DESTROY_NO_NETWORK", pjsua2JNI.PJSUA_DESTROY_NO_NETWORK_get());
        PJSUA_DESTROY_NO_NETWORK = pjsua_destroy_flagVar3;
        swigValues = new pjsua_destroy_flag[]{pjsua_destroy_flagVar, pjsua_destroy_flagVar2, pjsua_destroy_flagVar3};
        swigNext = 0;
    }

    private pjsua_destroy_flag(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_destroy_flag swigToEnum(int i2) {
        pjsua_destroy_flag[] pjsua_destroy_flagVarArr = swigValues;
        if (i2 < pjsua_destroy_flagVarArr.length && i2 >= 0 && pjsua_destroy_flagVarArr[i2].swigValue == i2) {
            return pjsua_destroy_flagVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_destroy_flag[] pjsua_destroy_flagVarArr2 = swigValues;
            if (i3 < pjsua_destroy_flagVarArr2.length) {
                if (pjsua_destroy_flagVarArr2[i3].swigValue == i2) {
                    return pjsua_destroy_flagVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_destroy_flag.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_destroy_flag(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_destroy_flag(String str, pjsua_destroy_flag pjsua_destroy_flagVar) {
        this.swigName = str;
        int i2 = pjsua_destroy_flagVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
