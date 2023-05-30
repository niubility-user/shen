package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsua_call_vid_strm_op {
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_ADD;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_DIR;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_NO_OP;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_REMOVE;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_SEND_KEYFRAME;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_START_TRANSMIT;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_STOP_TRANSMIT;
    private static int swigNext;
    private static pjsua_call_vid_strm_op[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_NO_OP");
        PJSUA_CALL_VID_STRM_NO_OP = pjsua_call_vid_strm_opVar;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar2 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_ADD");
        PJSUA_CALL_VID_STRM_ADD = pjsua_call_vid_strm_opVar2;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar3 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_REMOVE");
        PJSUA_CALL_VID_STRM_REMOVE = pjsua_call_vid_strm_opVar3;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar4 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_DIR");
        PJSUA_CALL_VID_STRM_CHANGE_DIR = pjsua_call_vid_strm_opVar4;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar5 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV");
        PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV = pjsua_call_vid_strm_opVar5;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar6 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_START_TRANSMIT");
        PJSUA_CALL_VID_STRM_START_TRANSMIT = pjsua_call_vid_strm_opVar6;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar7 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_STOP_TRANSMIT");
        PJSUA_CALL_VID_STRM_STOP_TRANSMIT = pjsua_call_vid_strm_opVar7;
        pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar8 = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_SEND_KEYFRAME");
        PJSUA_CALL_VID_STRM_SEND_KEYFRAME = pjsua_call_vid_strm_opVar8;
        swigValues = new pjsua_call_vid_strm_op[]{pjsua_call_vid_strm_opVar, pjsua_call_vid_strm_opVar2, pjsua_call_vid_strm_opVar3, pjsua_call_vid_strm_opVar4, pjsua_call_vid_strm_opVar5, pjsua_call_vid_strm_opVar6, pjsua_call_vid_strm_opVar7, pjsua_call_vid_strm_opVar8};
        swigNext = 0;
    }

    private pjsua_call_vid_strm_op(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsua_call_vid_strm_op swigToEnum(int i2) {
        pjsua_call_vid_strm_op[] pjsua_call_vid_strm_opVarArr = swigValues;
        if (i2 < pjsua_call_vid_strm_opVarArr.length && i2 >= 0 && pjsua_call_vid_strm_opVarArr[i2].swigValue == i2) {
            return pjsua_call_vid_strm_opVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsua_call_vid_strm_op[] pjsua_call_vid_strm_opVarArr2 = swigValues;
            if (i3 < pjsua_call_vid_strm_opVarArr2.length) {
                if (pjsua_call_vid_strm_opVarArr2[i3].swigValue == i2) {
                    return pjsua_call_vid_strm_opVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsua_call_vid_strm_op.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_vid_strm_op(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsua_call_vid_strm_op(String str, pjsua_call_vid_strm_op pjsua_call_vid_strm_opVar) {
        this.swigName = str;
        int i2 = pjsua_call_vid_strm_opVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
