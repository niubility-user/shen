package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_vid_stream_rc_method {
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_NONE;
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING;
    private static int swigNext;
    private static pjmedia_vid_stream_rc_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_vid_stream_rc_method pjmedia_vid_stream_rc_methodVar = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_NONE", pjsua2JNI.PJMEDIA_VID_STREAM_RC_NONE_get());
        PJMEDIA_VID_STREAM_RC_NONE = pjmedia_vid_stream_rc_methodVar;
        pjmedia_vid_stream_rc_method pjmedia_vid_stream_rc_methodVar2 = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING", pjsua2JNI.PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING_get());
        PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING = pjmedia_vid_stream_rc_methodVar2;
        swigValues = new pjmedia_vid_stream_rc_method[]{pjmedia_vid_stream_rc_methodVar, pjmedia_vid_stream_rc_methodVar2};
        swigNext = 0;
    }

    private pjmedia_vid_stream_rc_method(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_vid_stream_rc_method swigToEnum(int i2) {
        pjmedia_vid_stream_rc_method[] pjmedia_vid_stream_rc_methodVarArr = swigValues;
        if (i2 < pjmedia_vid_stream_rc_methodVarArr.length && i2 >= 0 && pjmedia_vid_stream_rc_methodVarArr[i2].swigValue == i2) {
            return pjmedia_vid_stream_rc_methodVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_vid_stream_rc_method[] pjmedia_vid_stream_rc_methodVarArr2 = swigValues;
            if (i3 < pjmedia_vid_stream_rc_methodVarArr2.length) {
                if (pjmedia_vid_stream_rc_methodVarArr2[i3].swigValue == i2) {
                    return pjmedia_vid_stream_rc_methodVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_vid_stream_rc_method.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_vid_stream_rc_method(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_vid_stream_rc_method(String str, pjmedia_vid_stream_rc_method pjmedia_vid_stream_rc_methodVar) {
        this.swigName = str;
        int i2 = pjmedia_vid_stream_rc_methodVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
