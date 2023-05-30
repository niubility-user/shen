package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_nat64_opt {
    public static final pjsua_nat64_opt PJSUA_NAT64_DISABLED;
    public static final pjsua_nat64_opt PJSUA_NAT64_ENABLED;
    private static int swigNext;
    private static pjsua_nat64_opt[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_nat64_opt pjsua_nat64_optVar = new pjsua_nat64_opt("PJSUA_NAT64_DISABLED");
        PJSUA_NAT64_DISABLED = pjsua_nat64_optVar;
        pjsua_nat64_opt pjsua_nat64_optVar2 = new pjsua_nat64_opt("PJSUA_NAT64_ENABLED");
        PJSUA_NAT64_ENABLED = pjsua_nat64_optVar2;
        swigValues = new pjsua_nat64_opt[]{pjsua_nat64_optVar, pjsua_nat64_optVar2};
        swigNext = 0;
    }

    private pjsua_nat64_opt(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_nat64_opt swigToEnum(int i2) {
        pjsua_nat64_opt[] pjsua_nat64_optVarArr = swigValues;
        if (i2 < pjsua_nat64_optVarArr.length && i2 >= 0 && pjsua_nat64_optVarArr[i2].swigValue == i2) {
            return pjsua_nat64_optVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_nat64_opt[] pjsua_nat64_optVarArr2 = swigValues;
            if (i3 < pjsua_nat64_optVarArr2.length) {
                if (pjsua_nat64_optVarArr2[i3].swigValue == i2) {
                    return pjsua_nat64_optVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_nat64_opt.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_nat64_opt(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_nat64_opt(String str, pjsua_nat64_opt pjsua_nat64_optVar) {
        this.swigName = str;
        int i2 = pjsua_nat64_optVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
