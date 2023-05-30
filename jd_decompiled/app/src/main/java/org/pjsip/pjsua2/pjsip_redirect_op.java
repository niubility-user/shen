package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_redirect_op {
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT;
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT_REPLACE;
    public static final pjsip_redirect_op PJSIP_REDIRECT_PENDING;
    public static final pjsip_redirect_op PJSIP_REDIRECT_REJECT;
    public static final pjsip_redirect_op PJSIP_REDIRECT_STOP;
    private static int swigNext;
    private static pjsip_redirect_op[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_redirect_op pjsip_redirect_opVar = new pjsip_redirect_op("PJSIP_REDIRECT_REJECT");
        PJSIP_REDIRECT_REJECT = pjsip_redirect_opVar;
        pjsip_redirect_op pjsip_redirect_opVar2 = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT");
        PJSIP_REDIRECT_ACCEPT = pjsip_redirect_opVar2;
        pjsip_redirect_op pjsip_redirect_opVar3 = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT_REPLACE");
        PJSIP_REDIRECT_ACCEPT_REPLACE = pjsip_redirect_opVar3;
        pjsip_redirect_op pjsip_redirect_opVar4 = new pjsip_redirect_op("PJSIP_REDIRECT_PENDING");
        PJSIP_REDIRECT_PENDING = pjsip_redirect_opVar4;
        pjsip_redirect_op pjsip_redirect_opVar5 = new pjsip_redirect_op("PJSIP_REDIRECT_STOP");
        PJSIP_REDIRECT_STOP = pjsip_redirect_opVar5;
        swigValues = new pjsip_redirect_op[]{pjsip_redirect_opVar, pjsip_redirect_opVar2, pjsip_redirect_opVar3, pjsip_redirect_opVar4, pjsip_redirect_opVar5};
        swigNext = 0;
    }

    private pjsip_redirect_op(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_redirect_op swigToEnum(int i2) {
        pjsip_redirect_op[] pjsip_redirect_opVarArr = swigValues;
        if (i2 < pjsip_redirect_opVarArr.length && i2 >= 0 && pjsip_redirect_opVarArr[i2].swigValue == i2) {
            return pjsip_redirect_opVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_redirect_op[] pjsip_redirect_opVarArr2 = swigValues;
            if (i3 < pjsip_redirect_opVarArr2.length) {
                if (pjsip_redirect_opVarArr2[i3].swigValue == i2) {
                    return pjsip_redirect_opVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_redirect_op.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_redirect_op(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_redirect_op(String str, pjsip_redirect_op pjsip_redirect_opVar) {
        this.swigName = str;
        int i2 = pjsip_redirect_opVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
