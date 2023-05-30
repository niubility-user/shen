package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_ssl_sock_proto {
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_DEFAULT;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_DTLS1;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL2;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL23;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL3;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1_1;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1_2;
    private static int swigNext;
    private static pj_ssl_sock_proto[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_ssl_sock_proto pj_ssl_sock_protoVar = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_DEFAULT", pjsua2JNI.PJ_SSL_SOCK_PROTO_DEFAULT_get());
        PJ_SSL_SOCK_PROTO_DEFAULT = pj_ssl_sock_protoVar;
        pj_ssl_sock_proto pj_ssl_sock_protoVar2 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL2", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL2_get());
        PJ_SSL_SOCK_PROTO_SSL2 = pj_ssl_sock_protoVar2;
        pj_ssl_sock_proto pj_ssl_sock_protoVar3 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL3", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL3_get());
        PJ_SSL_SOCK_PROTO_SSL3 = pj_ssl_sock_protoVar3;
        pj_ssl_sock_proto pj_ssl_sock_protoVar4 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_get());
        PJ_SSL_SOCK_PROTO_TLS1 = pj_ssl_sock_protoVar4;
        pj_ssl_sock_proto pj_ssl_sock_protoVar5 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1_1", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_1_get());
        PJ_SSL_SOCK_PROTO_TLS1_1 = pj_ssl_sock_protoVar5;
        pj_ssl_sock_proto pj_ssl_sock_protoVar6 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1_2", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_2_get());
        PJ_SSL_SOCK_PROTO_TLS1_2 = pj_ssl_sock_protoVar6;
        pj_ssl_sock_proto pj_ssl_sock_protoVar7 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL23", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL23_get());
        PJ_SSL_SOCK_PROTO_SSL23 = pj_ssl_sock_protoVar7;
        pj_ssl_sock_proto pj_ssl_sock_protoVar8 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_DTLS1", pjsua2JNI.PJ_SSL_SOCK_PROTO_DTLS1_get());
        PJ_SSL_SOCK_PROTO_DTLS1 = pj_ssl_sock_protoVar8;
        swigValues = new pj_ssl_sock_proto[]{pj_ssl_sock_protoVar, pj_ssl_sock_protoVar2, pj_ssl_sock_protoVar3, pj_ssl_sock_protoVar4, pj_ssl_sock_protoVar5, pj_ssl_sock_protoVar6, pj_ssl_sock_protoVar7, pj_ssl_sock_protoVar8};
        swigNext = 0;
    }

    private pj_ssl_sock_proto(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_ssl_sock_proto swigToEnum(int i2) {
        pj_ssl_sock_proto[] pj_ssl_sock_protoVarArr = swigValues;
        if (i2 < pj_ssl_sock_protoVarArr.length && i2 >= 0 && pj_ssl_sock_protoVarArr[i2].swigValue == i2) {
            return pj_ssl_sock_protoVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_ssl_sock_proto[] pj_ssl_sock_protoVarArr2 = swigValues;
            if (i3 < pj_ssl_sock_protoVarArr2.length) {
                if (pj_ssl_sock_protoVarArr2[i3].swigValue == i2) {
                    return pj_ssl_sock_protoVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_ssl_sock_proto.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_ssl_sock_proto(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_ssl_sock_proto(String str, pj_ssl_sock_proto pj_ssl_sock_protoVar) {
        this.swigName = str;
        int i2 = pj_ssl_sock_protoVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
