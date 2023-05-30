package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_ipv6_use {
    public static final pjsua_ipv6_use PJSUA_IPV6_DISABLED;
    public static final pjsua_ipv6_use PJSUA_IPV6_ENABLED;
    private static int swigNext;
    private static pjsua_ipv6_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_ipv6_use pjsua_ipv6_useVar = new pjsua_ipv6_use("PJSUA_IPV6_DISABLED");
        PJSUA_IPV6_DISABLED = pjsua_ipv6_useVar;
        pjsua_ipv6_use pjsua_ipv6_useVar2 = new pjsua_ipv6_use("PJSUA_IPV6_ENABLED");
        PJSUA_IPV6_ENABLED = pjsua_ipv6_useVar2;
        swigValues = new pjsua_ipv6_use[]{pjsua_ipv6_useVar, pjsua_ipv6_useVar2};
        swigNext = 0;
    }

    private pjsua_ipv6_use(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_ipv6_use swigToEnum(int i2) {
        pjsua_ipv6_use[] pjsua_ipv6_useVarArr = swigValues;
        if (i2 < pjsua_ipv6_useVarArr.length && i2 >= 0 && pjsua_ipv6_useVarArr[i2].swigValue == i2) {
            return pjsua_ipv6_useVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_ipv6_use[] pjsua_ipv6_useVarArr2 = swigValues;
            if (i3 < pjsua_ipv6_useVarArr2.length) {
                if (pjsua_ipv6_useVarArr2[i3].swigValue == i2) {
                    return pjsua_ipv6_useVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_ipv6_use.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_ipv6_use(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_ipv6_use(String str, pjsua_ipv6_use pjsua_ipv6_useVar) {
        this.swigName = str;
        int i2 = pjsua_ipv6_useVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
