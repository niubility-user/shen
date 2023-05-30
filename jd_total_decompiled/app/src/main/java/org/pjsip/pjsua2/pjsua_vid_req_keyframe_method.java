package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_vid_req_keyframe_method {
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_RTCP_PLI;
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_SIP_INFO;
    private static int swigNext;
    private static pjsua_vid_req_keyframe_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_vid_req_keyframe_method pjsua_vid_req_keyframe_methodVar = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_SIP_INFO", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_SIP_INFO_get());
        PJSUA_VID_REQ_KEYFRAME_SIP_INFO = pjsua_vid_req_keyframe_methodVar;
        pjsua_vid_req_keyframe_method pjsua_vid_req_keyframe_methodVar2 = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_RTCP_PLI", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_RTCP_PLI_get());
        PJSUA_VID_REQ_KEYFRAME_RTCP_PLI = pjsua_vid_req_keyframe_methodVar2;
        swigValues = new pjsua_vid_req_keyframe_method[]{pjsua_vid_req_keyframe_methodVar, pjsua_vid_req_keyframe_methodVar2};
        swigNext = 0;
    }

    private pjsua_vid_req_keyframe_method(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_vid_req_keyframe_method swigToEnum(int i2) {
        pjsua_vid_req_keyframe_method[] pjsua_vid_req_keyframe_methodVarArr = swigValues;
        if (i2 < pjsua_vid_req_keyframe_methodVarArr.length && i2 >= 0 && pjsua_vid_req_keyframe_methodVarArr[i2].swigValue == i2) {
            return pjsua_vid_req_keyframe_methodVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_vid_req_keyframe_method[] pjsua_vid_req_keyframe_methodVarArr2 = swigValues;
            if (i3 < pjsua_vid_req_keyframe_methodVarArr2.length) {
                if (pjsua_vid_req_keyframe_methodVarArr2[i3].swigValue == i2) {
                    return pjsua_vid_req_keyframe_methodVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_vid_req_keyframe_method.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_vid_req_keyframe_method(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_vid_req_keyframe_method(String str, pjsua_vid_req_keyframe_method pjsua_vid_req_keyframe_methodVar) {
        this.swigName = str;
        int i2 = pjsua_vid_req_keyframe_methodVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
