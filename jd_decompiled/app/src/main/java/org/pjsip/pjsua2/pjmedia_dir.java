package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_dir {
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE;
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_PLAYBACK;
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_RENDER;
    public static final pjmedia_dir PJMEDIA_DIR_DECODING;
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING;
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING_DECODING;
    public static final pjmedia_dir PJMEDIA_DIR_NONE;
    public static final pjmedia_dir PJMEDIA_DIR_PLAYBACK;
    public static final pjmedia_dir PJMEDIA_DIR_RENDER;
    private static int swigNext;
    private static pjmedia_dir[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_dir pjmedia_dirVar = new pjmedia_dir("PJMEDIA_DIR_NONE", pjsua2JNI.PJMEDIA_DIR_NONE_get());
        PJMEDIA_DIR_NONE = pjmedia_dirVar;
        pjmedia_dir pjmedia_dirVar2 = new pjmedia_dir("PJMEDIA_DIR_ENCODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_get());
        PJMEDIA_DIR_ENCODING = pjmedia_dirVar2;
        pjmedia_dir pjmedia_dirVar3 = new pjmedia_dir("PJMEDIA_DIR_CAPTURE", pjsua2JNI.PJMEDIA_DIR_CAPTURE_get());
        PJMEDIA_DIR_CAPTURE = pjmedia_dirVar3;
        pjmedia_dir pjmedia_dirVar4 = new pjmedia_dir("PJMEDIA_DIR_DECODING", pjsua2JNI.PJMEDIA_DIR_DECODING_get());
        PJMEDIA_DIR_DECODING = pjmedia_dirVar4;
        pjmedia_dir pjmedia_dirVar5 = new pjmedia_dir("PJMEDIA_DIR_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_PLAYBACK_get());
        PJMEDIA_DIR_PLAYBACK = pjmedia_dirVar5;
        pjmedia_dir pjmedia_dirVar6 = new pjmedia_dir("PJMEDIA_DIR_RENDER", pjsua2JNI.PJMEDIA_DIR_RENDER_get());
        PJMEDIA_DIR_RENDER = pjmedia_dirVar6;
        pjmedia_dir pjmedia_dirVar7 = new pjmedia_dir("PJMEDIA_DIR_ENCODING_DECODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_DECODING_get());
        PJMEDIA_DIR_ENCODING_DECODING = pjmedia_dirVar7;
        pjmedia_dir pjmedia_dirVar8 = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_CAPTURE_PLAYBACK_get());
        PJMEDIA_DIR_CAPTURE_PLAYBACK = pjmedia_dirVar8;
        pjmedia_dir pjmedia_dirVar9 = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_RENDER", pjsua2JNI.PJMEDIA_DIR_CAPTURE_RENDER_get());
        PJMEDIA_DIR_CAPTURE_RENDER = pjmedia_dirVar9;
        swigValues = new pjmedia_dir[]{pjmedia_dirVar, pjmedia_dirVar2, pjmedia_dirVar3, pjmedia_dirVar4, pjmedia_dirVar5, pjmedia_dirVar6, pjmedia_dirVar7, pjmedia_dirVar8, pjmedia_dirVar9};
        swigNext = 0;
    }

    private pjmedia_dir(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_dir swigToEnum(int i2) {
        pjmedia_dir[] pjmedia_dirVarArr = swigValues;
        if (i2 < pjmedia_dirVarArr.length && i2 >= 0 && pjmedia_dirVarArr[i2].swigValue == i2) {
            return pjmedia_dirVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_dir[] pjmedia_dirVarArr2 = swigValues;
            if (i3 < pjmedia_dirVarArr2.length) {
                if (pjmedia_dirVarArr2[i3].swigValue == i2) {
                    return pjmedia_dirVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_dir.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_dir(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_dir(String str, pjmedia_dir pjmedia_dirVar) {
        this.swigName = str;
        int i2 = pjmedia_dirVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
