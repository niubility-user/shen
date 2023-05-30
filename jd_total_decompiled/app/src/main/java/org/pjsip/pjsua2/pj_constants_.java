package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_constants_ {
    public static final pj_constants_ PJ_FALSE;
    public static final pj_constants_ PJ_SUCCESS;
    public static final pj_constants_ PJ_TRUE;
    private static int swigNext;
    private static pj_constants_[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_constants_ pj_constants_Var = new pj_constants_("PJ_SUCCESS", pjsua2JNI.PJ_SUCCESS_get());
        PJ_SUCCESS = pj_constants_Var;
        pj_constants_ pj_constants_Var2 = new pj_constants_("PJ_TRUE", pjsua2JNI.PJ_TRUE_get());
        PJ_TRUE = pj_constants_Var2;
        pj_constants_ pj_constants_Var3 = new pj_constants_("PJ_FALSE", pjsua2JNI.PJ_FALSE_get());
        PJ_FALSE = pj_constants_Var3;
        swigValues = new pj_constants_[]{pj_constants_Var, pj_constants_Var2, pj_constants_Var3};
        swigNext = 0;
    }

    private pj_constants_(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_constants_ swigToEnum(int i2) {
        pj_constants_[] pj_constants_VarArr = swigValues;
        if (i2 < pj_constants_VarArr.length && i2 >= 0 && pj_constants_VarArr[i2].swigValue == i2) {
            return pj_constants_VarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_constants_[] pj_constants_VarArr2 = swigValues;
            if (i3 < pj_constants_VarArr2.length) {
                if (pj_constants_VarArr2[i3].swigValue == i2) {
                    return pj_constants_VarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_constants_.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_constants_(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_constants_(String str, pj_constants_ pj_constants_Var) {
        this.swigName = str;
        int i2 = pj_constants_Var.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
