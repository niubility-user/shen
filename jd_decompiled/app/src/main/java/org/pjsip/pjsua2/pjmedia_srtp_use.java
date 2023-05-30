package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_srtp_use {
    public static final pjmedia_srtp_use PJMEDIA_SRTP_DISABLED;
    public static final pjmedia_srtp_use PJMEDIA_SRTP_MANDATORY;
    public static final pjmedia_srtp_use PJMEDIA_SRTP_OPTIONAL;
    private static int swigNext;
    private static pjmedia_srtp_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_srtp_use pjmedia_srtp_useVar = new pjmedia_srtp_use("PJMEDIA_SRTP_DISABLED");
        PJMEDIA_SRTP_DISABLED = pjmedia_srtp_useVar;
        pjmedia_srtp_use pjmedia_srtp_useVar2 = new pjmedia_srtp_use("PJMEDIA_SRTP_OPTIONAL");
        PJMEDIA_SRTP_OPTIONAL = pjmedia_srtp_useVar2;
        pjmedia_srtp_use pjmedia_srtp_useVar3 = new pjmedia_srtp_use("PJMEDIA_SRTP_MANDATORY");
        PJMEDIA_SRTP_MANDATORY = pjmedia_srtp_useVar3;
        swigValues = new pjmedia_srtp_use[]{pjmedia_srtp_useVar, pjmedia_srtp_useVar2, pjmedia_srtp_useVar3};
        swigNext = 0;
    }

    private pjmedia_srtp_use(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_srtp_use swigToEnum(int i2) {
        pjmedia_srtp_use[] pjmedia_srtp_useVarArr = swigValues;
        if (i2 < pjmedia_srtp_useVarArr.length && i2 >= 0 && pjmedia_srtp_useVarArr[i2].swigValue == i2) {
            return pjmedia_srtp_useVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_srtp_use[] pjmedia_srtp_useVarArr2 = swigValues;
            if (i3 < pjmedia_srtp_useVarArr2.length) {
                if (pjmedia_srtp_useVarArr2[i3].swigValue == i2) {
                    return pjmedia_srtp_useVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_srtp_use.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_srtp_use(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_srtp_use(String str, pjmedia_srtp_use pjmedia_srtp_useVar) {
        this.swigName = str;
        int i2 = pjmedia_srtp_useVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
