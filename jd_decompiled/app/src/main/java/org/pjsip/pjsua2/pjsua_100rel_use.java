package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_100rel_use {
    public static final pjsua_100rel_use PJSUA_100REL_MANDATORY;
    public static final pjsua_100rel_use PJSUA_100REL_NOT_USED;
    public static final pjsua_100rel_use PJSUA_100REL_OPTIONAL;
    private static int swigNext;
    private static pjsua_100rel_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_100rel_use pjsua_100rel_useVar = new pjsua_100rel_use("PJSUA_100REL_NOT_USED");
        PJSUA_100REL_NOT_USED = pjsua_100rel_useVar;
        pjsua_100rel_use pjsua_100rel_useVar2 = new pjsua_100rel_use("PJSUA_100REL_MANDATORY");
        PJSUA_100REL_MANDATORY = pjsua_100rel_useVar2;
        pjsua_100rel_use pjsua_100rel_useVar3 = new pjsua_100rel_use("PJSUA_100REL_OPTIONAL");
        PJSUA_100REL_OPTIONAL = pjsua_100rel_useVar3;
        swigValues = new pjsua_100rel_use[]{pjsua_100rel_useVar, pjsua_100rel_useVar2, pjsua_100rel_useVar3};
        swigNext = 0;
    }

    private pjsua_100rel_use(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_100rel_use swigToEnum(int i2) {
        pjsua_100rel_use[] pjsua_100rel_useVarArr = swigValues;
        if (i2 < pjsua_100rel_useVarArr.length && i2 >= 0 && pjsua_100rel_useVarArr[i2].swigValue == i2) {
            return pjsua_100rel_useVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_100rel_use[] pjsua_100rel_useVarArr2 = swigValues;
            if (i3 < pjsua_100rel_useVarArr2.length) {
                if (pjsua_100rel_useVarArr2[i3].swigValue == i2) {
                    return pjsua_100rel_useVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_100rel_use.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_100rel_use(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_100rel_use(String str, pjsua_100rel_use pjsua_100rel_useVar) {
        this.swigName = str;
        int i2 = pjsua_100rel_useVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
