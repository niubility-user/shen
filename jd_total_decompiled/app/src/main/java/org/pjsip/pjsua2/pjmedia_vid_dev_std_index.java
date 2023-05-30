package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_vid_dev_std_index {
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_CAPTURE_DEV;
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_RENDER_DEV;
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_INVALID_DEV;
    private static int swigNext;
    private static pjmedia_vid_dev_std_index[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_vid_dev_std_index pjmedia_vid_dev_std_indexVar = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_CAPTURE_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_CAPTURE_DEV_get());
        PJMEDIA_VID_DEFAULT_CAPTURE_DEV = pjmedia_vid_dev_std_indexVar;
        pjmedia_vid_dev_std_index pjmedia_vid_dev_std_indexVar2 = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_RENDER_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_RENDER_DEV_get());
        PJMEDIA_VID_DEFAULT_RENDER_DEV = pjmedia_vid_dev_std_indexVar2;
        pjmedia_vid_dev_std_index pjmedia_vid_dev_std_indexVar3 = new pjmedia_vid_dev_std_index("PJMEDIA_VID_INVALID_DEV", pjsua2JNI.PJMEDIA_VID_INVALID_DEV_get());
        PJMEDIA_VID_INVALID_DEV = pjmedia_vid_dev_std_indexVar3;
        swigValues = new pjmedia_vid_dev_std_index[]{pjmedia_vid_dev_std_indexVar, pjmedia_vid_dev_std_indexVar2, pjmedia_vid_dev_std_indexVar3};
        swigNext = 0;
    }

    private pjmedia_vid_dev_std_index(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_vid_dev_std_index swigToEnum(int i2) {
        pjmedia_vid_dev_std_index[] pjmedia_vid_dev_std_indexVarArr = swigValues;
        if (i2 < pjmedia_vid_dev_std_indexVarArr.length && i2 >= 0 && pjmedia_vid_dev_std_indexVarArr[i2].swigValue == i2) {
            return pjmedia_vid_dev_std_indexVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_vid_dev_std_index[] pjmedia_vid_dev_std_indexVarArr2 = swigValues;
            if (i3 < pjmedia_vid_dev_std_indexVarArr2.length) {
                if (pjmedia_vid_dev_std_indexVarArr2[i3].swigValue == i2) {
                    return pjmedia_vid_dev_std_indexVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_vid_dev_std_index.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_dev_std_index(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_vid_dev_std_index(String str, pjmedia_vid_dev_std_index pjmedia_vid_dev_std_indexVar) {
        this.swigName = str;
        int i2 = pjmedia_vid_dev_std_indexVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
