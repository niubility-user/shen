package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_stun_use {
    public static final pjsua_stun_use PJSUA_STUN_RETRY_ON_FAILURE;
    public static final pjsua_stun_use PJSUA_STUN_USE_DEFAULT;
    public static final pjsua_stun_use PJSUA_STUN_USE_DISABLED;
    private static int swigNext;
    private static pjsua_stun_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_stun_use pjsua_stun_useVar = new pjsua_stun_use("PJSUA_STUN_USE_DEFAULT");
        PJSUA_STUN_USE_DEFAULT = pjsua_stun_useVar;
        pjsua_stun_use pjsua_stun_useVar2 = new pjsua_stun_use("PJSUA_STUN_USE_DISABLED");
        PJSUA_STUN_USE_DISABLED = pjsua_stun_useVar2;
        pjsua_stun_use pjsua_stun_useVar3 = new pjsua_stun_use("PJSUA_STUN_RETRY_ON_FAILURE");
        PJSUA_STUN_RETRY_ON_FAILURE = pjsua_stun_useVar3;
        swigValues = new pjsua_stun_use[]{pjsua_stun_useVar, pjsua_stun_useVar2, pjsua_stun_useVar3};
        swigNext = 0;
    }

    private pjsua_stun_use(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_stun_use swigToEnum(int i2) {
        pjsua_stun_use[] pjsua_stun_useVarArr = swigValues;
        if (i2 < pjsua_stun_useVarArr.length && i2 >= 0 && pjsua_stun_useVarArr[i2].swigValue == i2) {
            return pjsua_stun_useVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_stun_use[] pjsua_stun_useVarArr2 = swigValues;
            if (i3 < pjsua_stun_useVarArr2.length) {
                if (pjsua_stun_useVarArr2[i3].swigValue == i2) {
                    return pjsua_stun_useVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_stun_use.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_stun_use(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_stun_use(String str, pjsua_stun_use pjsua_stun_useVar) {
        this.swigName = str;
        int i2 = pjsua_stun_useVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
