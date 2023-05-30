package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_role_e {
    public static final pjsip_role_e PJSIP_ROLE_UAC;
    public static final pjsip_role_e PJSIP_ROLE_UAS;
    public static final pjsip_role_e PJSIP_UAC_ROLE;
    public static final pjsip_role_e PJSIP_UAS_ROLE;
    private static int swigNext;
    private static pjsip_role_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_role_e pjsip_role_eVar = new pjsip_role_e("PJSIP_ROLE_UAC");
        PJSIP_ROLE_UAC = pjsip_role_eVar;
        pjsip_role_e pjsip_role_eVar2 = new pjsip_role_e("PJSIP_ROLE_UAS");
        PJSIP_ROLE_UAS = pjsip_role_eVar2;
        pjsip_role_e pjsip_role_eVar3 = new pjsip_role_e("PJSIP_UAC_ROLE", pjsua2JNI.PJSIP_UAC_ROLE_get());
        PJSIP_UAC_ROLE = pjsip_role_eVar3;
        pjsip_role_e pjsip_role_eVar4 = new pjsip_role_e("PJSIP_UAS_ROLE", pjsua2JNI.PJSIP_UAS_ROLE_get());
        PJSIP_UAS_ROLE = pjsip_role_eVar4;
        swigValues = new pjsip_role_e[]{pjsip_role_eVar, pjsip_role_eVar2, pjsip_role_eVar3, pjsip_role_eVar4};
        swigNext = 0;
    }

    private pjsip_role_e(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_role_e swigToEnum(int i2) {
        pjsip_role_e[] pjsip_role_eVarArr = swigValues;
        if (i2 < pjsip_role_eVarArr.length && i2 >= 0 && pjsip_role_eVarArr[i2].swigValue == i2) {
            return pjsip_role_eVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_role_e[] pjsip_role_eVarArr2 = swigValues;
            if (i3 < pjsip_role_eVarArr2.length) {
                if (pjsip_role_eVarArr2[i3].swigValue == i2) {
                    return pjsip_role_eVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_role_e.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_role_e(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_role_e(String str, pjsip_role_e pjsip_role_eVar) {
        this.swigName = str;
        int i2 = pjsip_role_eVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
