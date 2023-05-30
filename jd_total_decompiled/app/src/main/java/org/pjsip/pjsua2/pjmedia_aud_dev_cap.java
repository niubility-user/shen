package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_aud_dev_cap {
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_CNG;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EC;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EC_TAIL;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EXT_FORMAT;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_SOURCE;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_MAX;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_PLC;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_VAD;
    private static int swigNext;
    private static pjmedia_aud_dev_cap[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EXT_FORMAT", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EXT_FORMAT_get());
        PJMEDIA_AUD_DEV_CAP_EXT_FORMAT = pjmedia_aud_dev_capVar;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar2 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY = pjmedia_aud_dev_capVar2;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar3 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY = pjmedia_aud_dev_capVar3;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar4 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING = pjmedia_aud_dev_capVar4;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar5 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING = pjmedia_aud_dev_capVar5;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar6 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER = pjmedia_aud_dev_capVar6;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar7 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER = pjmedia_aud_dev_capVar7;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar8 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE = pjmedia_aud_dev_capVar8;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar9 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_SOURCE", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_SOURCE_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_SOURCE = pjmedia_aud_dev_capVar9;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar10 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE = pjmedia_aud_dev_capVar10;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar11 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EC", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EC_get());
        PJMEDIA_AUD_DEV_CAP_EC = pjmedia_aud_dev_capVar11;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar12 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EC_TAIL", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EC_TAIL_get());
        PJMEDIA_AUD_DEV_CAP_EC_TAIL = pjmedia_aud_dev_capVar12;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar13 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_VAD", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_VAD_get());
        PJMEDIA_AUD_DEV_CAP_VAD = pjmedia_aud_dev_capVar13;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar14 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_CNG", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_CNG_get());
        PJMEDIA_AUD_DEV_CAP_CNG = pjmedia_aud_dev_capVar14;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar15 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_PLC", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_PLC_get());
        PJMEDIA_AUD_DEV_CAP_PLC = pjmedia_aud_dev_capVar15;
        pjmedia_aud_dev_cap pjmedia_aud_dev_capVar16 = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_MAX", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_MAX_get());
        PJMEDIA_AUD_DEV_CAP_MAX = pjmedia_aud_dev_capVar16;
        swigValues = new pjmedia_aud_dev_cap[]{pjmedia_aud_dev_capVar, pjmedia_aud_dev_capVar2, pjmedia_aud_dev_capVar3, pjmedia_aud_dev_capVar4, pjmedia_aud_dev_capVar5, pjmedia_aud_dev_capVar6, pjmedia_aud_dev_capVar7, pjmedia_aud_dev_capVar8, pjmedia_aud_dev_capVar9, pjmedia_aud_dev_capVar10, pjmedia_aud_dev_capVar11, pjmedia_aud_dev_capVar12, pjmedia_aud_dev_capVar13, pjmedia_aud_dev_capVar14, pjmedia_aud_dev_capVar15, pjmedia_aud_dev_capVar16};
        swigNext = 0;
    }

    private pjmedia_aud_dev_cap(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_aud_dev_cap swigToEnum(int i2) {
        pjmedia_aud_dev_cap[] pjmedia_aud_dev_capVarArr = swigValues;
        if (i2 < pjmedia_aud_dev_capVarArr.length && i2 >= 0 && pjmedia_aud_dev_capVarArr[i2].swigValue == i2) {
            return pjmedia_aud_dev_capVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_aud_dev_cap[] pjmedia_aud_dev_capVarArr2 = swigValues;
            if (i3 < pjmedia_aud_dev_capVarArr2.length) {
                if (pjmedia_aud_dev_capVarArr2[i3].swigValue == i2) {
                    return pjmedia_aud_dev_capVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_aud_dev_cap.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_aud_dev_cap(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_aud_dev_cap(String str, pjmedia_aud_dev_cap pjmedia_aud_dev_capVar) {
        this.swigName = str;
        int i2 = pjmedia_aud_dev_capVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
