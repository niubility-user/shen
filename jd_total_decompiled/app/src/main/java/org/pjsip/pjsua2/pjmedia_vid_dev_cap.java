package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_vid_dev_cap {
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_FORMAT;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_INPUT_SCALE;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_MAX;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_ORIENTATION;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS;
    public static final pjmedia_vid_dev_cap PJMEDIA_VID_DEV_CAP_SWITCH;
    private static int swigNext;
    private static pjmedia_vid_dev_cap[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_FORMAT", pjsua2JNI.PJMEDIA_VID_DEV_CAP_FORMAT_get());
        PJMEDIA_VID_DEV_CAP_FORMAT = pjmedia_vid_dev_capVar;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar2 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_INPUT_SCALE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_INPUT_SCALE_get());
        PJMEDIA_VID_DEV_CAP_INPUT_SCALE = pjmedia_vid_dev_capVar2;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar3 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_get());
        PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW = pjmedia_vid_dev_capVar3;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar4 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE_get());
        PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE = pjmedia_vid_dev_capVar4;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar5 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION_get());
        PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION = pjmedia_vid_dev_capVar5;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar6 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE_get());
        PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE = pjmedia_vid_dev_capVar6;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar7 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW", pjsua2JNI.PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW_get());
        PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW = pjmedia_vid_dev_capVar7;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar8 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_ORIENTATION", pjsua2JNI.PJMEDIA_VID_DEV_CAP_ORIENTATION_get());
        PJMEDIA_VID_DEV_CAP_ORIENTATION = pjmedia_vid_dev_capVar8;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar9 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_SWITCH", pjsua2JNI.PJMEDIA_VID_DEV_CAP_SWITCH_get());
        PJMEDIA_VID_DEV_CAP_SWITCH = pjmedia_vid_dev_capVar9;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar10 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS", pjsua2JNI.PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS_get());
        PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS = pjmedia_vid_dev_capVar10;
        pjmedia_vid_dev_cap pjmedia_vid_dev_capVar11 = new pjmedia_vid_dev_cap("PJMEDIA_VID_DEV_CAP_MAX", pjsua2JNI.PJMEDIA_VID_DEV_CAP_MAX_get());
        PJMEDIA_VID_DEV_CAP_MAX = pjmedia_vid_dev_capVar11;
        swigValues = new pjmedia_vid_dev_cap[]{pjmedia_vid_dev_capVar, pjmedia_vid_dev_capVar2, pjmedia_vid_dev_capVar3, pjmedia_vid_dev_capVar4, pjmedia_vid_dev_capVar5, pjmedia_vid_dev_capVar6, pjmedia_vid_dev_capVar7, pjmedia_vid_dev_capVar8, pjmedia_vid_dev_capVar9, pjmedia_vid_dev_capVar10, pjmedia_vid_dev_capVar11};
        swigNext = 0;
    }

    private pjmedia_vid_dev_cap(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_vid_dev_cap swigToEnum(int i2) {
        pjmedia_vid_dev_cap[] pjmedia_vid_dev_capVarArr = swigValues;
        if (i2 < pjmedia_vid_dev_capVarArr.length && i2 >= 0 && pjmedia_vid_dev_capVarArr[i2].swigValue == i2) {
            return pjmedia_vid_dev_capVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_vid_dev_cap[] pjmedia_vid_dev_capVarArr2 = swigValues;
            if (i3 < pjmedia_vid_dev_capVarArr2.length) {
                if (pjmedia_vid_dev_capVarArr2[i3].swigValue == i2) {
                    return pjmedia_vid_dev_capVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_vid_dev_cap.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_dev_cap(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_vid_dev_cap(String str, pjmedia_vid_dev_cap pjmedia_vid_dev_capVar) {
        this.swigName = str;
        int i2 = pjmedia_vid_dev_capVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
