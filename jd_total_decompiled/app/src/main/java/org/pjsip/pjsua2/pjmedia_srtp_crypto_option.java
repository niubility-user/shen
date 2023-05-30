package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public final class pjmedia_srtp_crypto_option {
    public static final pjmedia_srtp_crypto_option PJMEDIA_SRTP_NO_AUTHENTICATION;
    public static final pjmedia_srtp_crypto_option PJMEDIA_SRTP_NO_ENCRYPTION;
    private static int swigNext;
    private static pjmedia_srtp_crypto_option[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        pjmedia_srtp_crypto_option pjmedia_srtp_crypto_optionVar = new pjmedia_srtp_crypto_option("PJMEDIA_SRTP_NO_ENCRYPTION", pjsua2JNI.PJMEDIA_SRTP_NO_ENCRYPTION_get());
        PJMEDIA_SRTP_NO_ENCRYPTION = pjmedia_srtp_crypto_optionVar;
        pjmedia_srtp_crypto_option pjmedia_srtp_crypto_optionVar2 = new pjmedia_srtp_crypto_option("PJMEDIA_SRTP_NO_AUTHENTICATION", pjsua2JNI.PJMEDIA_SRTP_NO_AUTHENTICATION_get());
        PJMEDIA_SRTP_NO_AUTHENTICATION = pjmedia_srtp_crypto_optionVar2;
        swigValues = new pjmedia_srtp_crypto_option[]{pjmedia_srtp_crypto_optionVar, pjmedia_srtp_crypto_optionVar2};
        swigNext = 0;
    }

    private pjmedia_srtp_crypto_option(String str) {
        this.swigName = str;
        int i2 = swigNext;
        swigNext = i2 + 1;
        this.swigValue = i2;
    }

    public static pjmedia_srtp_crypto_option swigToEnum(int i2) {
        pjmedia_srtp_crypto_option[] pjmedia_srtp_crypto_optionVarArr = swigValues;
        if (i2 < pjmedia_srtp_crypto_optionVarArr.length && i2 >= 0 && pjmedia_srtp_crypto_optionVarArr[i2].swigValue == i2) {
            return pjmedia_srtp_crypto_optionVarArr[i2];
        }
        int i3 = 0;
        while (true) {
            pjmedia_srtp_crypto_option[] pjmedia_srtp_crypto_optionVarArr2 = swigValues;
            if (i3 < pjmedia_srtp_crypto_optionVarArr2.length) {
                if (pjmedia_srtp_crypto_optionVarArr2[i3].swigValue == i2) {
                    return pjmedia_srtp_crypto_optionVarArr2[i3];
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + pjmedia_srtp_crypto_option.class + " with value " + i2);
            }
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_srtp_crypto_option(String str, int i2) {
        this.swigName = str;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }

    private pjmedia_srtp_crypto_option(String str, pjmedia_srtp_crypto_option pjmedia_srtp_crypto_optionVar) {
        this.swigName = str;
        int i2 = pjmedia_srtp_crypto_optionVar.swigValue;
        this.swigValue = i2;
        swigNext = i2 + 1;
    }
}
