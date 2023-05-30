package com.jdjr.mobilecertsm;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.dns.R;
import com.jdjr.mobilecert.MobileCertRetCallback;
import com.jdjr.securehttp.GeneralErrorCode;
import com.jdjr.securehttp.JDJRResultMessage;

/* loaded from: classes18.dex */
public class MobileCertSMManager {
    public static final int CERT_DELETE_FAIL = 0;
    public static final int CERT_DELETE_SUCCESS = 1;
    public static final int CERT_SM_EXIST = 1;
    public static final int CERT_SM_NOT_EXIST = 0;
    private static MobileCertSMManager instance;
    private static final Object lock = new Object();
    private final Context context;
    private String mJDPin;
    private boolean wbxStatus;

    private MobileCertSMManager(Context context) {
        this.context = context.getApplicationContext();
        this.wbxStatus = SmCertUtils.initWbxPath(context);
    }

    public static MobileCertSMManager newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MobileCertSMManager(context);
                }
            }
        }
        return instance;
    }

    public synchronized void checkApplyCertWithWskey(String str, String str2, String str3, String str4, String str5, MobileCertRetCallback mobileCertRetCallback) {
        checkApplyCertWithWskey(str, str2, -1, str3, str4, str5, mobileCertRetCallback);
    }

    public int deleteCert(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return SmCertUtils.deleteSmCert(this.context, str) ? 1 : 0;
    }

    public int isCertExists(String str) {
        if (TextUtils.isEmpty(str)) {
            Context context = this.context;
            String string = context.getString(R.string.security_sm_search_cert_msg);
            String str2 = this.mJDPin;
            if (str2 == null) {
                str2 = "";
            }
            SmCertUtils.composeErrorInfo(context, GeneralErrorCode.SEARCH_CERT, string, str2);
            return 0;
        }
        return SmCertUtils.checkSmCertExist(this.context, str) ? 1 : 0;
    }

    public byte[] signP7AndEnvelopMsg(String str, byte[] bArr) {
        if (bArr != null && !TextUtils.isEmpty(str)) {
            return SmCertUtils.getSmP7SignMsg(this.context, str, bArr);
        }
        return GeneralErrorCode.PARAMETER_ERROR.getBytes();
    }

    public synchronized void checkApplyCertWithWskey(String str, String str2, int i2, String str3, String str4, String str5, final MobileCertRetCallback mobileCertRetCallback) {
        if (mobileCertRetCallback == null) {
            return;
        }
        if (!this.wbxStatus) {
            this.wbxStatus = SmCertUtils.initWbxPath(this.context);
        }
        if (TextUtils.isEmpty(str)) {
            String string = this.context.getString(R.string.security_sm_param_error_bizType);
            SmCertUtils.composeErrorInfo(this.context, GeneralErrorCode.INSTALL_CERT_ERROR, string, str4);
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage(string.getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str2)) {
            String string2 = this.context.getString(R.string.security_sm_param_error_appId);
            SmCertUtils.composeErrorInfo(this.context, GeneralErrorCode.INSTALL_CERT_ERROR, string2, str4);
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage(string2.getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str3)) {
            String string3 = this.context.getString(R.string.security_sm_param_error_template);
            SmCertUtils.composeErrorInfo(this.context, GeneralErrorCode.INSTALL_CERT_ERROR, string3, str4);
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage(string3.getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str4)) {
            String string4 = this.context.getString(R.string.security_sm_param_error_jdpin);
            SmCertUtils.composeErrorInfo(this.context, GeneralErrorCode.INSTALL_CERT_ERROR, string4, str4);
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage(string4.getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str5)) {
            String string5 = this.context.getString(R.string.security_sm_param_error_wsKey);
            SmCertUtils.composeErrorInfo(this.context, GeneralErrorCode.INSTALL_CERT_ERROR, string5, str4);
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage(string5.getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else {
            this.mJDPin = str4;
            if (SmCertUtils.checkSmCertExist(this.context, str4)) {
                mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), "00000"));
            } else {
                SmCertUtils.sendSmCertApply(this.context, "mobile", str, i2, str2, str5, str3, "111111", this.mJDPin, null, 5, new MobileCertRetCallback() { // from class: com.jdjr.mobilecertsm.MobileCertSMManager.1
                    @Override // com.jdjr.mobilecert.MobileCertRetCallback
                    public String getMobileCertResultMessage(JDJRResultMessage jDJRResultMessage) {
                        return mobileCertRetCallback.getMobileCertResultMessage(jDJRResultMessage);
                    }
                });
            }
        }
    }
}
