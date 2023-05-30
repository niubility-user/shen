package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_call_flag {
    public static final pjsua_call_flag PJSUA_CALL_INCLUDE_DISABLED_MEDIA;
    public static final pjsua_call_flag PJSUA_CALL_NO_SDP_OFFER;
    public static final pjsua_call_flag PJSUA_CALL_REINIT_MEDIA;
    public static final pjsua_call_flag PJSUA_CALL_UNHOLD;
    public static final pjsua_call_flag PJSUA_CALL_UPDATE_CONTACT;
    public static final pjsua_call_flag PJSUA_CALL_UPDATE_VIA;
    private static int swigNext;
    private static pjsua_call_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_call_flag pjsua_call_flagVar = new pjsua_call_flag("PJSUA_CALL_UNHOLD", pjsua2JNI.PJSUA_CALL_UNHOLD_get());
        PJSUA_CALL_UNHOLD = pjsua_call_flagVar;
        pjsua_call_flag pjsua_call_flagVar2 = new pjsua_call_flag("PJSUA_CALL_UPDATE_CONTACT", pjsua2JNI.PJSUA_CALL_UPDATE_CONTACT_get());
        PJSUA_CALL_UPDATE_CONTACT = pjsua_call_flagVar2;
        pjsua_call_flag pjsua_call_flagVar3 = new pjsua_call_flag("PJSUA_CALL_INCLUDE_DISABLED_MEDIA", pjsua2JNI.PJSUA_CALL_INCLUDE_DISABLED_MEDIA_get());
        PJSUA_CALL_INCLUDE_DISABLED_MEDIA = pjsua_call_flagVar3;
        pjsua_call_flag pjsua_call_flagVar4 = new pjsua_call_flag("PJSUA_CALL_NO_SDP_OFFER", pjsua2JNI.PJSUA_CALL_NO_SDP_OFFER_get());
        PJSUA_CALL_NO_SDP_OFFER = pjsua_call_flagVar4;
        pjsua_call_flag pjsua_call_flagVar5 = new pjsua_call_flag("PJSUA_CALL_REINIT_MEDIA", pjsua2JNI.PJSUA_CALL_REINIT_MEDIA_get());
        PJSUA_CALL_REINIT_MEDIA = pjsua_call_flagVar5;
        pjsua_call_flag pjsua_call_flagVar6 = new pjsua_call_flag("PJSUA_CALL_UPDATE_VIA", pjsua2JNI.PJSUA_CALL_UPDATE_VIA_get());
        PJSUA_CALL_UPDATE_VIA = pjsua_call_flagVar6;
        swigValues = new pjsua_call_flag[]{pjsua_call_flagVar, pjsua_call_flagVar2, pjsua_call_flagVar3, pjsua_call_flagVar4, pjsua_call_flagVar5, pjsua_call_flagVar6};
        swigNext = 0;
    }

    private pjsua_call_flag(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_call_flag swigToEnum(int i2) {
        pjsua_call_flag[] pjsua_call_flagVarArr = swigValues;
        if (i2 < pjsua_call_flagVarArr.length && i2 >= 0 && pjsua_call_flagVarArr[i2].swigValue == i2) {
            return pjsua_call_flagVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_call_flag[] pjsua_call_flagVarArr2 = swigValues;
            if (i3 < pjsua_call_flagVarArr2.length) {
                if (pjsua_call_flagVarArr2[i3].swigValue == i2) {
                    return pjsua_call_flagVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_call_flag.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_flag(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_call_flag(String str, pjsua_call_flag pjsua_call_flagVar) {
        this.swigName = str;
        int i2 = pjsua_call_flagVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
