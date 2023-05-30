package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_snd_dev_id {
    public static final pjsua_snd_dev_id PJSUA_SND_DEFAULT_CAPTURE_DEV;
    public static final pjsua_snd_dev_id PJSUA_SND_DEFAULT_PLAYBACK_DEV;
    public static final pjsua_snd_dev_id PJSUA_SND_NO_DEV;
    public static final pjsua_snd_dev_id PJSUA_SND_NULL_DEV;
    private static int swigNext;
    private static pjsua_snd_dev_id[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_snd_dev_id pjsua_snd_dev_idVar = new pjsua_snd_dev_id("PJSUA_SND_DEFAULT_CAPTURE_DEV", pjsua2JNI.PJSUA_SND_DEFAULT_CAPTURE_DEV_get());
        PJSUA_SND_DEFAULT_CAPTURE_DEV = pjsua_snd_dev_idVar;
        pjsua_snd_dev_id pjsua_snd_dev_idVar2 = new pjsua_snd_dev_id("PJSUA_SND_DEFAULT_PLAYBACK_DEV", pjsua2JNI.PJSUA_SND_DEFAULT_PLAYBACK_DEV_get());
        PJSUA_SND_DEFAULT_PLAYBACK_DEV = pjsua_snd_dev_idVar2;
        pjsua_snd_dev_id pjsua_snd_dev_idVar3 = new pjsua_snd_dev_id("PJSUA_SND_NO_DEV", pjsua2JNI.PJSUA_SND_NO_DEV_get());
        PJSUA_SND_NO_DEV = pjsua_snd_dev_idVar3;
        pjsua_snd_dev_id pjsua_snd_dev_idVar4 = new pjsua_snd_dev_id("PJSUA_SND_NULL_DEV", pjsua2JNI.PJSUA_SND_NULL_DEV_get());
        PJSUA_SND_NULL_DEV = pjsua_snd_dev_idVar4;
        swigValues = new pjsua_snd_dev_id[]{pjsua_snd_dev_idVar, pjsua_snd_dev_idVar2, pjsua_snd_dev_idVar3, pjsua_snd_dev_idVar4};
        swigNext = 0;
    }

    private pjsua_snd_dev_id(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_snd_dev_id swigToEnum(int i2) {
        pjsua_snd_dev_id[] pjsua_snd_dev_idVarArr = swigValues;
        if (i2 < pjsua_snd_dev_idVarArr.length && i2 >= 0 && pjsua_snd_dev_idVarArr[i2].swigValue == i2) {
            return pjsua_snd_dev_idVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_snd_dev_id[] pjsua_snd_dev_idVarArr2 = swigValues;
            if (i3 < pjsua_snd_dev_idVarArr2.length) {
                if (pjsua_snd_dev_idVarArr2[i3].swigValue == i2) {
                    return pjsua_snd_dev_idVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_snd_dev_id.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_snd_dev_id(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_snd_dev_id(String str, pjsua_snd_dev_id pjsua_snd_dev_idVar) {
        this.swigName = str;
        int i2 = pjsua_snd_dev_idVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
