package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_invalid_id_const_ {
    public static final pjsua_invalid_id_const_ PJSUA_INVALID_ID;
    private static int swigNext;
    private static pjsua_invalid_id_const_[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_invalid_id_const_ pjsua_invalid_id_const_Var = new pjsua_invalid_id_const_("PJSUA_INVALID_ID", pjsua2JNI.PJSUA_INVALID_ID_get());
        PJSUA_INVALID_ID = pjsua_invalid_id_const_Var;
        swigValues = new pjsua_invalid_id_const_[]{pjsua_invalid_id_const_Var};
        swigNext = 0;
    }

    private pjsua_invalid_id_const_(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_invalid_id_const_ swigToEnum(int i2) {
        pjsua_invalid_id_const_[] pjsua_invalid_id_const_VarArr = swigValues;
        if (i2 < pjsua_invalid_id_const_VarArr.length && i2 >= 0 && pjsua_invalid_id_const_VarArr[i2].swigValue == i2) {
            return pjsua_invalid_id_const_VarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_invalid_id_const_[] pjsua_invalid_id_const_VarArr2 = swigValues;
            if (i3 < pjsua_invalid_id_const_VarArr2.length) {
                if (pjsua_invalid_id_const_VarArr2[i3].swigValue == i2) {
                    return pjsua_invalid_id_const_VarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_invalid_id_const_.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_invalid_id_const_(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_invalid_id_const_(String str, pjsua_invalid_id_const_ pjsua_invalid_id_const_Var) {
        this.swigName = str;
        int i2 = pjsua_invalid_id_const_Var.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
