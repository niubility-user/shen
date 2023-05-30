package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_cred_data_type {
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_DIGEST;
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_EXT_AKA;
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_PLAIN_PASSWD;
    private static int swigNext;
    private static pjsip_cred_data_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_cred_data_type pjsip_cred_data_typeVar = new pjsip_cred_data_type("PJSIP_CRED_DATA_PLAIN_PASSWD", pjsua2JNI.PJSIP_CRED_DATA_PLAIN_PASSWD_get());
        PJSIP_CRED_DATA_PLAIN_PASSWD = pjsip_cred_data_typeVar;
        pjsip_cred_data_type pjsip_cred_data_typeVar2 = new pjsip_cred_data_type("PJSIP_CRED_DATA_DIGEST", pjsua2JNI.PJSIP_CRED_DATA_DIGEST_get());
        PJSIP_CRED_DATA_DIGEST = pjsip_cred_data_typeVar2;
        pjsip_cred_data_type pjsip_cred_data_typeVar3 = new pjsip_cred_data_type("PJSIP_CRED_DATA_EXT_AKA", pjsua2JNI.PJSIP_CRED_DATA_EXT_AKA_get());
        PJSIP_CRED_DATA_EXT_AKA = pjsip_cred_data_typeVar3;
        swigValues = new pjsip_cred_data_type[]{pjsip_cred_data_typeVar, pjsip_cred_data_typeVar2, pjsip_cred_data_typeVar3};
        swigNext = 0;
    }

    private pjsip_cred_data_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_cred_data_type swigToEnum(int i2) {
        pjsip_cred_data_type[] pjsip_cred_data_typeVarArr = swigValues;
        if (i2 < pjsip_cred_data_typeVarArr.length && i2 >= 0 && pjsip_cred_data_typeVarArr[i2].swigValue == i2) {
            return pjsip_cred_data_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_cred_data_type[] pjsip_cred_data_typeVarArr2 = swigValues;
            if (i3 < pjsip_cred_data_typeVarArr2.length) {
                if (pjsip_cred_data_typeVarArr2[i3].swigValue == i2) {
                    return pjsip_cred_data_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_cred_data_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_cred_data_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_cred_data_type(String str, pjsip_cred_data_type pjsip_cred_data_typeVar) {
        this.swigName = str;
        int i2 = pjsip_cred_data_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
