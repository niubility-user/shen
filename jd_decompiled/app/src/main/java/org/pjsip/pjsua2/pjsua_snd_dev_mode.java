package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_snd_dev_mode {
    public static final pjsua_snd_dev_mode PJSUA_SND_DEV_NO_IMMEDIATE_OPEN;
    public static final pjsua_snd_dev_mode PJSUA_SND_DEV_SPEAKER_ONLY;
    private static int swigNext;
    private static pjsua_snd_dev_mode[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_snd_dev_mode pjsua_snd_dev_modeVar = new pjsua_snd_dev_mode("PJSUA_SND_DEV_SPEAKER_ONLY", pjsua2JNI.PJSUA_SND_DEV_SPEAKER_ONLY_get());
        PJSUA_SND_DEV_SPEAKER_ONLY = pjsua_snd_dev_modeVar;
        pjsua_snd_dev_mode pjsua_snd_dev_modeVar2 = new pjsua_snd_dev_mode("PJSUA_SND_DEV_NO_IMMEDIATE_OPEN", pjsua2JNI.PJSUA_SND_DEV_NO_IMMEDIATE_OPEN_get());
        PJSUA_SND_DEV_NO_IMMEDIATE_OPEN = pjsua_snd_dev_modeVar2;
        swigValues = new pjsua_snd_dev_mode[]{pjsua_snd_dev_modeVar, pjsua_snd_dev_modeVar2};
        swigNext = 0;
    }

    private pjsua_snd_dev_mode(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_snd_dev_mode swigToEnum(int i2) {
        pjsua_snd_dev_mode[] pjsua_snd_dev_modeVarArr = swigValues;
        if (i2 < pjsua_snd_dev_modeVarArr.length && i2 >= 0 && pjsua_snd_dev_modeVarArr[i2].swigValue == i2) {
            return pjsua_snd_dev_modeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_snd_dev_mode[] pjsua_snd_dev_modeVarArr2 = swigValues;
            if (i3 < pjsua_snd_dev_modeVarArr2.length) {
                if (pjsua_snd_dev_modeVarArr2[i3].swigValue == i2) {
                    return pjsua_snd_dev_modeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_snd_dev_mode.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_snd_dev_mode(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_snd_dev_mode(String str, pjsua_snd_dev_mode pjsua_snd_dev_modeVar) {
        this.swigName = str;
        int i2 = pjsua_snd_dev_modeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
