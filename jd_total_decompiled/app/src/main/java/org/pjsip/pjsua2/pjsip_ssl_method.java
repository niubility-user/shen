package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjsip_ssl_method {
    public static final pjsip_ssl_method PJSIP_SSLV23_METHOD;
    public static final pjsip_ssl_method PJSIP_SSLV2_METHOD;
    public static final pjsip_ssl_method PJSIP_SSLV3_METHOD;
    public static final pjsip_ssl_method PJSIP_SSL_UNSPECIFIED_METHOD;
    public static final pjsip_ssl_method PJSIP_TLSV1_1_METHOD;
    public static final pjsip_ssl_method PJSIP_TLSV1_2_METHOD;
    public static final pjsip_ssl_method PJSIP_TLSV1_METHOD;
    private static int swigNext;
    private static pjsip_ssl_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjsip_ssl_method pjsip_ssl_methodVar = new pjsip_ssl_method("PJSIP_SSL_UNSPECIFIED_METHOD", pjsua2JNI.PJSIP_SSL_UNSPECIFIED_METHOD_get());
        PJSIP_SSL_UNSPECIFIED_METHOD = pjsip_ssl_methodVar;
        pjsip_ssl_method pjsip_ssl_methodVar2 = new pjsip_ssl_method("PJSIP_SSLV2_METHOD", pjsua2JNI.PJSIP_SSLV2_METHOD_get());
        PJSIP_SSLV2_METHOD = pjsip_ssl_methodVar2;
        pjsip_ssl_method pjsip_ssl_methodVar3 = new pjsip_ssl_method("PJSIP_SSLV3_METHOD", pjsua2JNI.PJSIP_SSLV3_METHOD_get());
        PJSIP_SSLV3_METHOD = pjsip_ssl_methodVar3;
        pjsip_ssl_method pjsip_ssl_methodVar4 = new pjsip_ssl_method("PJSIP_TLSV1_METHOD", pjsua2JNI.PJSIP_TLSV1_METHOD_get());
        PJSIP_TLSV1_METHOD = pjsip_ssl_methodVar4;
        pjsip_ssl_method pjsip_ssl_methodVar5 = new pjsip_ssl_method("PJSIP_TLSV1_1_METHOD", pjsua2JNI.PJSIP_TLSV1_1_METHOD_get());
        PJSIP_TLSV1_1_METHOD = pjsip_ssl_methodVar5;
        pjsip_ssl_method pjsip_ssl_methodVar6 = new pjsip_ssl_method("PJSIP_TLSV1_2_METHOD", pjsua2JNI.PJSIP_TLSV1_2_METHOD_get());
        PJSIP_TLSV1_2_METHOD = pjsip_ssl_methodVar6;
        pjsip_ssl_method pjsip_ssl_methodVar7 = new pjsip_ssl_method("PJSIP_SSLV23_METHOD", pjsua2JNI.PJSIP_SSLV23_METHOD_get());
        PJSIP_SSLV23_METHOD = pjsip_ssl_methodVar7;
        swigValues = new pjsip_ssl_method[]{pjsip_ssl_methodVar, pjsip_ssl_methodVar2, pjsip_ssl_methodVar3, pjsip_ssl_methodVar4, pjsip_ssl_methodVar5, pjsip_ssl_methodVar6, pjsip_ssl_methodVar7};
        swigNext = 0;
    }

    private pjsip_ssl_method(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjsip_ssl_method swigToEnum(int i2) {
        pjsip_ssl_method[] pjsip_ssl_methodVarArr = swigValues;
        if (i2 < pjsip_ssl_methodVarArr.length && i2 >= 0 && pjsip_ssl_methodVarArr[i2].swigValue == i2) {
            return pjsip_ssl_methodVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjsip_ssl_method[] pjsip_ssl_methodVarArr2 = swigValues;
            if (i3 < pjsip_ssl_methodVarArr2.length) {
                if (pjsip_ssl_methodVarArr2[i3].swigValue == i2) {
                    return pjsip_ssl_methodVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjsip_ssl_method.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_ssl_method(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjsip_ssl_method(String str, pjsip_ssl_method pjsip_ssl_methodVar) {
        this.swigName = str;
        int i2 = pjsip_ssl_methodVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
