package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_ssl_cert_name_type {
    public static final pj_ssl_cert_name_type PJ_SSL_CERT_NAME_DNS;
    public static final pj_ssl_cert_name_type PJ_SSL_CERT_NAME_IP;
    public static final pj_ssl_cert_name_type PJ_SSL_CERT_NAME_RFC822;
    public static final pj_ssl_cert_name_type PJ_SSL_CERT_NAME_UNKNOWN;
    public static final pj_ssl_cert_name_type PJ_SSL_CERT_NAME_URI;
    private static int swigNext;
    private static pj_ssl_cert_name_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_ssl_cert_name_type pj_ssl_cert_name_typeVar = new pj_ssl_cert_name_type("PJ_SSL_CERT_NAME_UNKNOWN", pjsua2JNI.PJ_SSL_CERT_NAME_UNKNOWN_get());
        PJ_SSL_CERT_NAME_UNKNOWN = pj_ssl_cert_name_typeVar;
        pj_ssl_cert_name_type pj_ssl_cert_name_typeVar2 = new pj_ssl_cert_name_type("PJ_SSL_CERT_NAME_RFC822");
        PJ_SSL_CERT_NAME_RFC822 = pj_ssl_cert_name_typeVar2;
        pj_ssl_cert_name_type pj_ssl_cert_name_typeVar3 = new pj_ssl_cert_name_type("PJ_SSL_CERT_NAME_DNS");
        PJ_SSL_CERT_NAME_DNS = pj_ssl_cert_name_typeVar3;
        pj_ssl_cert_name_type pj_ssl_cert_name_typeVar4 = new pj_ssl_cert_name_type("PJ_SSL_CERT_NAME_URI");
        PJ_SSL_CERT_NAME_URI = pj_ssl_cert_name_typeVar4;
        pj_ssl_cert_name_type pj_ssl_cert_name_typeVar5 = new pj_ssl_cert_name_type("PJ_SSL_CERT_NAME_IP");
        PJ_SSL_CERT_NAME_IP = pj_ssl_cert_name_typeVar5;
        swigValues = new pj_ssl_cert_name_type[]{pj_ssl_cert_name_typeVar, pj_ssl_cert_name_typeVar2, pj_ssl_cert_name_typeVar3, pj_ssl_cert_name_typeVar4, pj_ssl_cert_name_typeVar5};
        swigNext = 0;
    }

    private pj_ssl_cert_name_type(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_ssl_cert_name_type swigToEnum(int i2) {
        pj_ssl_cert_name_type[] pj_ssl_cert_name_typeVarArr = swigValues;
        if (i2 < pj_ssl_cert_name_typeVarArr.length && i2 >= 0 && pj_ssl_cert_name_typeVarArr[i2].swigValue == i2) {
            return pj_ssl_cert_name_typeVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_ssl_cert_name_type[] pj_ssl_cert_name_typeVarArr2 = swigValues;
            if (i3 < pj_ssl_cert_name_typeVarArr2.length) {
                if (pj_ssl_cert_name_typeVarArr2[i3].swigValue == i2) {
                    return pj_ssl_cert_name_typeVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_ssl_cert_name_type.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_ssl_cert_name_type(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_ssl_cert_name_type(String str, pj_ssl_cert_name_type pj_ssl_cert_name_typeVar) {
        this.swigName = str;
        int i2 = pj_ssl_cert_name_typeVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
