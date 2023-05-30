package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_tp_proto {
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_NONE;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_AVP;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_SAVP;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_UNKNOWN;
    private static int swigNext;
    private static pjmedia_tp_proto[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_tp_proto pjmedia_tp_protoVar = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_NONE", pjsua2JNI.PJMEDIA_TP_PROTO_NONE_get());
        PJMEDIA_TP_PROTO_NONE = pjmedia_tp_protoVar;
        pjmedia_tp_proto pjmedia_tp_protoVar2 = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_AVP");
        PJMEDIA_TP_PROTO_RTP_AVP = pjmedia_tp_protoVar2;
        pjmedia_tp_proto pjmedia_tp_protoVar3 = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_SAVP");
        PJMEDIA_TP_PROTO_RTP_SAVP = pjmedia_tp_protoVar3;
        pjmedia_tp_proto pjmedia_tp_protoVar4 = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_UNKNOWN");
        PJMEDIA_TP_PROTO_UNKNOWN = pjmedia_tp_protoVar4;
        swigValues = new pjmedia_tp_proto[]{pjmedia_tp_protoVar, pjmedia_tp_protoVar2, pjmedia_tp_protoVar3, pjmedia_tp_protoVar4};
        swigNext = 0;
    }

    private pjmedia_tp_proto(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_tp_proto swigToEnum(int i2) {
        pjmedia_tp_proto[] pjmedia_tp_protoVarArr = swigValues;
        if (i2 < pjmedia_tp_protoVarArr.length && i2 >= 0 && pjmedia_tp_protoVarArr[i2].swigValue == i2) {
            return pjmedia_tp_protoVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_tp_proto[] pjmedia_tp_protoVarArr2 = swigValues;
            if (i3 < pjmedia_tp_protoVarArr2.length) {
                if (pjmedia_tp_protoVarArr2[i3].swigValue == i2) {
                    return pjmedia_tp_protoVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_tp_proto.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_tp_proto(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_tp_proto(String str, pjmedia_tp_proto pjmedia_tp_protoVar) {
        this.swigName = str;
        int i2 = pjmedia_tp_protoVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
