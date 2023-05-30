package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_dialog_cap_status {
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_SUPPORTED;
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNKNOWN;
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNSUPPORTED;
    private static int swigNext;
    private static pjsip_dialog_cap_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_dialog_cap_status pjsip_dialog_cap_statusVar = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNSUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_UNSUPPORTED_get());
        PJSIP_DIALOG_CAP_UNSUPPORTED = pjsip_dialog_cap_statusVar;
        pjsip_dialog_cap_status pjsip_dialog_cap_statusVar2 = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_SUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_SUPPORTED_get());
        PJSIP_DIALOG_CAP_SUPPORTED = pjsip_dialog_cap_statusVar2;
        pjsip_dialog_cap_status pjsip_dialog_cap_statusVar3 = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNKNOWN", pjsua2JNI.PJSIP_DIALOG_CAP_UNKNOWN_get());
        PJSIP_DIALOG_CAP_UNKNOWN = pjsip_dialog_cap_statusVar3;
        swigValues = new pjsip_dialog_cap_status[]{pjsip_dialog_cap_statusVar, pjsip_dialog_cap_statusVar2, pjsip_dialog_cap_statusVar3};
        swigNext = 0;
    }

    private pjsip_dialog_cap_status(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_dialog_cap_status swigToEnum(int i2) {
        pjsip_dialog_cap_status[] pjsip_dialog_cap_statusVarArr = swigValues;
        if (i2 < pjsip_dialog_cap_statusVarArr.length && i2 >= 0 && pjsip_dialog_cap_statusVarArr[i2].swigValue == i2) {
            return pjsip_dialog_cap_statusVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_dialog_cap_status[] pjsip_dialog_cap_statusVarArr2 = swigValues;
            if (i3 < pjsip_dialog_cap_statusVarArr2.length) {
                if (pjsip_dialog_cap_statusVarArr2[i3].swigValue == i2) {
                    return pjsip_dialog_cap_statusVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_dialog_cap_status.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_dialog_cap_status(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_dialog_cap_status(String str, pjsip_dialog_cap_status pjsip_dialog_cap_statusVar) {
        this.swigName = str;
        int i2 = pjsip_dialog_cap_statusVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
