package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_call_media_status {
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ACTIVE;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ERROR;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_LOCAL_HOLD;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_NONE;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_REMOTE_HOLD;
    private static int swigNext;
    private static pjsua_call_media_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_call_media_status pjsua_call_media_statusVar = new pjsua_call_media_status("PJSUA_CALL_MEDIA_NONE");
        PJSUA_CALL_MEDIA_NONE = pjsua_call_media_statusVar;
        pjsua_call_media_status pjsua_call_media_statusVar2 = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ACTIVE");
        PJSUA_CALL_MEDIA_ACTIVE = pjsua_call_media_statusVar2;
        pjsua_call_media_status pjsua_call_media_statusVar3 = new pjsua_call_media_status("PJSUA_CALL_MEDIA_LOCAL_HOLD");
        PJSUA_CALL_MEDIA_LOCAL_HOLD = pjsua_call_media_statusVar3;
        pjsua_call_media_status pjsua_call_media_statusVar4 = new pjsua_call_media_status("PJSUA_CALL_MEDIA_REMOTE_HOLD");
        PJSUA_CALL_MEDIA_REMOTE_HOLD = pjsua_call_media_statusVar4;
        pjsua_call_media_status pjsua_call_media_statusVar5 = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ERROR");
        PJSUA_CALL_MEDIA_ERROR = pjsua_call_media_statusVar5;
        swigValues = new pjsua_call_media_status[]{pjsua_call_media_statusVar, pjsua_call_media_statusVar2, pjsua_call_media_statusVar3, pjsua_call_media_statusVar4, pjsua_call_media_statusVar5};
        swigNext = 0;
    }

    private pjsua_call_media_status(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_call_media_status swigToEnum(int i2) {
        pjsua_call_media_status[] pjsua_call_media_statusVarArr = swigValues;
        if (i2 < pjsua_call_media_statusVarArr.length && i2 >= 0 && pjsua_call_media_statusVarArr[i2].swigValue == i2) {
            return pjsua_call_media_statusVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_call_media_status[] pjsua_call_media_statusVarArr2 = swigValues;
            if (i3 < pjsua_call_media_statusVarArr2.length) {
                if (pjsua_call_media_statusVarArr2[i3].swigValue == i2) {
                    return pjsua_call_media_statusVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_call_media_status.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_media_status(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_call_media_status(String str, pjsua_call_media_status pjsua_call_media_statusVar) {
        this.swigName = str;
        int i2 = pjsua_call_media_statusVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
