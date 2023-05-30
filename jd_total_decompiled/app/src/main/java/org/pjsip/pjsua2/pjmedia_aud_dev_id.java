package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_aud_dev_id {
    public static final pjmedia_aud_dev_id PJMEDIA_AUD_DEFAULT_CAPTURE_DEV;
    public static final pjmedia_aud_dev_id PJMEDIA_AUD_DEFAULT_PLAYBACK_DEV;
    public static final pjmedia_aud_dev_id PJMEDIA_AUD_INVALID_DEV;
    private static int swigNext;
    private static pjmedia_aud_dev_id[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_aud_dev_id pjmedia_aud_dev_idVar = new pjmedia_aud_dev_id("PJMEDIA_AUD_DEFAULT_CAPTURE_DEV", pjsua2JNI.PJMEDIA_AUD_DEFAULT_CAPTURE_DEV_get());
        PJMEDIA_AUD_DEFAULT_CAPTURE_DEV = pjmedia_aud_dev_idVar;
        pjmedia_aud_dev_id pjmedia_aud_dev_idVar2 = new pjmedia_aud_dev_id("PJMEDIA_AUD_DEFAULT_PLAYBACK_DEV", pjsua2JNI.PJMEDIA_AUD_DEFAULT_PLAYBACK_DEV_get());
        PJMEDIA_AUD_DEFAULT_PLAYBACK_DEV = pjmedia_aud_dev_idVar2;
        pjmedia_aud_dev_id pjmedia_aud_dev_idVar3 = new pjmedia_aud_dev_id("PJMEDIA_AUD_INVALID_DEV", pjsua2JNI.PJMEDIA_AUD_INVALID_DEV_get());
        PJMEDIA_AUD_INVALID_DEV = pjmedia_aud_dev_idVar3;
        swigValues = new pjmedia_aud_dev_id[]{pjmedia_aud_dev_idVar, pjmedia_aud_dev_idVar2, pjmedia_aud_dev_idVar3};
        swigNext = 0;
    }

    private pjmedia_aud_dev_id(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_aud_dev_id swigToEnum(int i2) {
        pjmedia_aud_dev_id[] pjmedia_aud_dev_idVarArr = swigValues;
        if (i2 < pjmedia_aud_dev_idVarArr.length && i2 >= 0 && pjmedia_aud_dev_idVarArr[i2].swigValue == i2) {
            return pjmedia_aud_dev_idVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_aud_dev_id[] pjmedia_aud_dev_idVarArr2 = swigValues;
            if (i3 < pjmedia_aud_dev_idVarArr2.length) {
                if (pjmedia_aud_dev_idVarArr2[i3].swigValue == i2) {
                    return pjmedia_aud_dev_idVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_aud_dev_id.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_aud_dev_id(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_aud_dev_id(String str, pjmedia_aud_dev_id pjmedia_aud_dev_idVar) {
        this.swigName = str;
        int i2 = pjmedia_aud_dev_idVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
