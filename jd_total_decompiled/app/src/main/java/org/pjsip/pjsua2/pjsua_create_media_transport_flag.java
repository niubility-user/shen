package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_create_media_transport_flag {
    public static final pjsua_create_media_transport_flag PJSUA_MED_TP_CLOSE_MEMBER;
    private static int swigNext;
    private static pjsua_create_media_transport_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_create_media_transport_flag pjsua_create_media_transport_flagVar = new pjsua_create_media_transport_flag("PJSUA_MED_TP_CLOSE_MEMBER", pjsua2JNI.PJSUA_MED_TP_CLOSE_MEMBER_get());
        PJSUA_MED_TP_CLOSE_MEMBER = pjsua_create_media_transport_flagVar;
        swigValues = new pjsua_create_media_transport_flag[]{pjsua_create_media_transport_flagVar};
        swigNext = 0;
    }

    private pjsua_create_media_transport_flag(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_create_media_transport_flag swigToEnum(int i2) {
        pjsua_create_media_transport_flag[] pjsua_create_media_transport_flagVarArr = swigValues;
        if (i2 < pjsua_create_media_transport_flagVarArr.length && i2 >= 0 && pjsua_create_media_transport_flagVarArr[i2].swigValue == i2) {
            return pjsua_create_media_transport_flagVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_create_media_transport_flag[] pjsua_create_media_transport_flagVarArr2 = swigValues;
            if (i3 < pjsua_create_media_transport_flagVarArr2.length) {
                if (pjsua_create_media_transport_flagVarArr2[i3].swigValue == i2) {
                    return pjsua_create_media_transport_flagVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_create_media_transport_flag.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_create_media_transport_flag(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_create_media_transport_flag(String str, pjsua_create_media_transport_flag pjsua_create_media_transport_flagVar) {
        this.swigName = str;
        int i2 = pjsua_create_media_transport_flagVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
