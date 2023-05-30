package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pj_ssl_cert_verify_flag_t {
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_ECHAIN_TOO_LONG;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_ECRL_FAILURE;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EIDENTITY_NOT_MATCH;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EINVALID_FORMAT;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EINVALID_PURPOSE;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EISSUER_MISMATCH;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EISSUER_NOT_FOUND;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EREVOKED;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_ESUCCESS;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EUNKNOWN;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EUNTRUSTED;
    public static final pj_ssl_cert_verify_flag_t PJ_SSL_CERT_EVALIDITY_PERIOD;
    private static int swigNext;
    private static pj_ssl_cert_verify_flag_t[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_ESUCCESS", pjsua2JNI.PJ_SSL_CERT_ESUCCESS_get());
        PJ_SSL_CERT_ESUCCESS = pj_ssl_cert_verify_flag_tVar;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar2 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EISSUER_NOT_FOUND", pjsua2JNI.PJ_SSL_CERT_EISSUER_NOT_FOUND_get());
        PJ_SSL_CERT_EISSUER_NOT_FOUND = pj_ssl_cert_verify_flag_tVar2;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar3 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EUNTRUSTED", pjsua2JNI.PJ_SSL_CERT_EUNTRUSTED_get());
        PJ_SSL_CERT_EUNTRUSTED = pj_ssl_cert_verify_flag_tVar3;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar4 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EVALIDITY_PERIOD", pjsua2JNI.PJ_SSL_CERT_EVALIDITY_PERIOD_get());
        PJ_SSL_CERT_EVALIDITY_PERIOD = pj_ssl_cert_verify_flag_tVar4;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar5 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EINVALID_FORMAT", pjsua2JNI.PJ_SSL_CERT_EINVALID_FORMAT_get());
        PJ_SSL_CERT_EINVALID_FORMAT = pj_ssl_cert_verify_flag_tVar5;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar6 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EINVALID_PURPOSE", pjsua2JNI.PJ_SSL_CERT_EINVALID_PURPOSE_get());
        PJ_SSL_CERT_EINVALID_PURPOSE = pj_ssl_cert_verify_flag_tVar6;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar7 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EISSUER_MISMATCH", pjsua2JNI.PJ_SSL_CERT_EISSUER_MISMATCH_get());
        PJ_SSL_CERT_EISSUER_MISMATCH = pj_ssl_cert_verify_flag_tVar7;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar8 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_ECRL_FAILURE", pjsua2JNI.PJ_SSL_CERT_ECRL_FAILURE_get());
        PJ_SSL_CERT_ECRL_FAILURE = pj_ssl_cert_verify_flag_tVar8;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar9 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EREVOKED", pjsua2JNI.PJ_SSL_CERT_EREVOKED_get());
        PJ_SSL_CERT_EREVOKED = pj_ssl_cert_verify_flag_tVar9;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar10 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_ECHAIN_TOO_LONG", pjsua2JNI.PJ_SSL_CERT_ECHAIN_TOO_LONG_get());
        PJ_SSL_CERT_ECHAIN_TOO_LONG = pj_ssl_cert_verify_flag_tVar10;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar11 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EIDENTITY_NOT_MATCH", pjsua2JNI.PJ_SSL_CERT_EIDENTITY_NOT_MATCH_get());
        PJ_SSL_CERT_EIDENTITY_NOT_MATCH = pj_ssl_cert_verify_flag_tVar11;
        pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar12 = new pj_ssl_cert_verify_flag_t("PJ_SSL_CERT_EUNKNOWN", pjsua2JNI.PJ_SSL_CERT_EUNKNOWN_get());
        PJ_SSL_CERT_EUNKNOWN = pj_ssl_cert_verify_flag_tVar12;
        swigValues = new pj_ssl_cert_verify_flag_t[]{pj_ssl_cert_verify_flag_tVar, pj_ssl_cert_verify_flag_tVar2, pj_ssl_cert_verify_flag_tVar3, pj_ssl_cert_verify_flag_tVar4, pj_ssl_cert_verify_flag_tVar5, pj_ssl_cert_verify_flag_tVar6, pj_ssl_cert_verify_flag_tVar7, pj_ssl_cert_verify_flag_tVar8, pj_ssl_cert_verify_flag_tVar9, pj_ssl_cert_verify_flag_tVar10, pj_ssl_cert_verify_flag_tVar11, pj_ssl_cert_verify_flag_tVar12};
        swigNext = 0;
    }

    private pj_ssl_cert_verify_flag_t(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pj_ssl_cert_verify_flag_t swigToEnum(int i2) {
        pj_ssl_cert_verify_flag_t[] pj_ssl_cert_verify_flag_tVarArr = swigValues;
        if (i2 < pj_ssl_cert_verify_flag_tVarArr.length && i2 >= 0 && pj_ssl_cert_verify_flag_tVarArr[i2].swigValue == i2) {
            return pj_ssl_cert_verify_flag_tVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pj_ssl_cert_verify_flag_t[] pj_ssl_cert_verify_flag_tVarArr2 = swigValues;
            if (i3 < pj_ssl_cert_verify_flag_tVarArr2.length) {
                if (pj_ssl_cert_verify_flag_tVarArr2[i3].swigValue == i2) {
                    return pj_ssl_cert_verify_flag_tVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pj_ssl_cert_verify_flag_t.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_ssl_cert_verify_flag_t(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pj_ssl_cert_verify_flag_t(String str, pj_ssl_cert_verify_flag_t pj_ssl_cert_verify_flag_tVar) {
        this.swigName = str;
        int i2 = pj_ssl_cert_verify_flag_tVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
