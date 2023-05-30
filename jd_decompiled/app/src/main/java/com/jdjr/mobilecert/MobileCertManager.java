package com.jdjr.mobilecert;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.jdjr.securehttp.GeneralErrorCode;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.StringTools;
import com.wangyin.platform.CryptoUtils;

/* loaded from: classes18.dex */
public class MobileCertManager {
    private static final String TAG = "MobileCertManager";
    private static MobileCertManager instance;
    private static final Object lock = new Object();
    private final Context context;
    private CryptoUtils cryptoUtils;
    private MobileCertRetCallback globalCallback;
    private String mAppId;
    private String mBizType;
    private MobileCertProcessor mCertHandler;
    private String mJDPin;
    private String mTemplate;
    private String mWsKey;

    private MobileCertManager(Context context) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.cryptoUtils = CryptoUtils.newInstance(applicationContext);
        this.mCertHandler = new MobileCertProcessor(applicationContext);
    }

    private void generateCertWithA2(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, MobileCertRetCallback mobileCertRetCallback) {
        this.mCertHandler.sendCertApply(str, str2, str3, str7, str4, "111111", str5, str6, i2, mobileCertRetCallback);
    }

    private void generateCertWithWskey(String str, String str2, int i2, String str3, String str4, String str5, String str6, int i3, String str7, MobileCertRetCallback mobileCertRetCallback) {
        this.mCertHandler.sendCertApplyWithWskey(str, str2, i2, str3, str7, str4, "111111", str5, str6, i3, mobileCertRetCallback);
    }

    public static MobileCertManager newInstance(Context context) {
        JDJRLog.i(TAG, "MobileCertManager newInstance");
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MobileCertManager(context);
                }
            }
        }
        return instance;
    }

    public synchronized void checkApplyCertWithA2(String str, String str2, String str3, String str4, String str5, MobileCertRetCallback mobileCertRetCallback) {
        MobileCertProcessor mobileCertProcessor;
        MobileCertProcessor mobileCertProcessor2;
        MobileCertProcessor mobileCertProcessor3;
        MobileCertProcessor mobileCertProcessor4;
        MobileCertProcessor mobileCertProcessor5;
        if (mobileCertRetCallback == null) {
            MobileCertProcessor mobileCertProcessor6 = this.mCertHandler;
            if (mobileCertProcessor6 != null) {
                mobileCertProcessor6.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "CertCallback is null", str4);
                return;
            }
        }
        this.globalCallback = mobileCertRetCallback;
        if (TextUtils.isEmpty(str4) && (mobileCertProcessor5 = this.mCertHandler) != null) {
            mobileCertProcessor5.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "jdPin is empty", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("jdPin is empty".getBytes(), "00000"));
        } else if (TextUtils.isEmpty(str) && (mobileCertProcessor4 = this.mCertHandler) != null) {
            mobileCertProcessor4.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "bizType is empty", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("bizType is empty".getBytes(), "00000"));
        } else if (TextUtils.isEmpty(str2) && (mobileCertProcessor3 = this.mCertHandler) != null) {
            mobileCertProcessor3.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "appId is empty", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("appId is empty".getBytes(), "00000"));
        } else if (TextUtils.isEmpty(str3) && (mobileCertProcessor2 = this.mCertHandler) != null) {
            mobileCertProcessor2.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "template is empty", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("template is empty".getBytes(), "00000"));
        } else if (TextUtils.isEmpty(str5) && (mobileCertProcessor = this.mCertHandler) != null) {
            mobileCertProcessor.composeErrorInfo(GeneralErrorCode.PARAMETER_ERROR, "wsKey is empty", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("wsKey is empty".getBytes(), "00000"));
        } else {
            this.mCertHandler.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_BEGIN, "begin call", str4);
            this.mBizType = str;
            this.mTemplate = str3;
            this.mJDPin = str4;
            this.mWsKey = str5;
            this.mAppId = str2;
            byte[] bArr = new byte[5];
            System.arraycopy(this.cryptoUtils.isCertExists(str4, 5), 0, bArr, 0, 5);
            if (new String(bArr).equals("00000")) {
                JDJRLog.i(TAG, "checkApplyCert EXITST");
                this.mCertHandler.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_EXITS, "cert exist", str4);
                this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), "00000"));
            } else {
                JDJRLog.i(TAG, "checkApplyCert generateCertWithA2 jdpin=" + str4);
                generateCertWithA2("mobile", str, this.mAppId, str3, str4, null, 5, this.mWsKey, mobileCertRetCallback);
            }
        }
    }

    public synchronized void checkApplyCertWithWskey(String str, String str2, String str3, String str4, String str5, MobileCertRetCallback mobileCertRetCallback) {
        checkApplyCertWithWskey(str, str2, -1, str3, str4, str5, mobileCertRetCallback);
    }

    public int deleteCert(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        byte[] bArr = new byte[5];
        System.arraycopy(this.cryptoUtils.deleteCertificate(StringTools.getMD5(str)), 0, bArr, 0, 5);
        if (new String(bArr).equals("00000")) {
            JDJRLog.i(TAG, "deleteCert success");
            return 1;
        }
        JDJRLog.i(TAG, "deleteCert failed");
        return 0;
    }

    public int isCertExists(String str) {
        MobileCertProcessor mobileCertProcessor;
        if (TextUtils.isEmpty(str) && (mobileCertProcessor = this.mCertHandler) != null) {
            String str2 = this.mJDPin;
            if (str2 == null) {
                str2 = "";
            }
            mobileCertProcessor.composeErrorInfo(GeneralErrorCode.SEARCH_CERT, "search:jdpin is null", str2);
            return 0;
        }
        byte[] bArr = new byte[5];
        System.arraycopy(this.cryptoUtils.isCertExists(StringTools.getMD5(str), 5), 0, bArr, 0, 5);
        if (new String(bArr).equals("00000")) {
            MobileCertProcessor mobileCertProcessor2 = this.mCertHandler;
            if (mobileCertProcessor2 != null) {
                mobileCertProcessor2.composeErrorInfo(GeneralErrorCode.SEARCH_CERT, "search:cert Exist", str);
                return 1;
            }
            return 1;
        }
        MobileCertProcessor mobileCertProcessor3 = this.mCertHandler;
        if (mobileCertProcessor3 != null) {
            mobileCertProcessor3.composeErrorInfo(GeneralErrorCode.SEARCH_CERT, "search:cert not Exist:" + new String(bArr), str);
        }
        return 0;
    }

    public byte[] signP7AndEnvelopMsg(String str, byte[] bArr) {
        if (bArr != null && !TextUtils.isEmpty(str)) {
            return this.cryptoUtils.signP7AndEnvelopMsg(StringTools.getMD5(str), StringTools.getMD5(str), null, bArr);
        }
        return GeneralErrorCode.PARAMETER_ERROR.getBytes();
    }

    public synchronized void checkApplyCertWithWskey(String str, String str2, int i2, String str3, String str4, String str5, MobileCertRetCallback mobileCertRetCallback) {
        MobileCertProcessor mobileCertProcessor;
        MobileCertProcessor mobileCertProcessor2;
        MobileCertProcessor mobileCertProcessor3;
        MobileCertProcessor mobileCertProcessor4;
        MobileCertProcessor mobileCertProcessor5;
        if (mobileCertRetCallback == null) {
            MobileCertProcessor mobileCertProcessor6 = this.mCertHandler;
            if (mobileCertProcessor6 != null) {
                mobileCertProcessor6.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "CertCallback is null", str4);
                return;
            }
        }
        this.globalCallback = mobileCertRetCallback;
        if (TextUtils.isEmpty(str) && (mobileCertProcessor5 = this.mCertHandler) != null) {
            mobileCertProcessor5.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "bizType is null", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("bizType is null".getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str2) && (mobileCertProcessor4 = this.mCertHandler) != null) {
            mobileCertProcessor4.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "appId is null", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("appId is null".getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str3) && (mobileCertProcessor3 = this.mCertHandler) != null) {
            mobileCertProcessor3.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "template is null", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("template is null".getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str4) && (mobileCertProcessor2 = this.mCertHandler) != null) {
            mobileCertProcessor2.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "jdPin is null", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("jdPin is null".getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else if (TextUtils.isEmpty(str5) && (mobileCertProcessor = this.mCertHandler) != null) {
            mobileCertProcessor.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "wsKey is null", str4);
            this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("wsKey is null".getBytes(), GeneralErrorCode.PARAMETER_ERROR));
        } else {
            this.mCertHandler.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_BEGIN, "begin call", str4);
            this.mBizType = str;
            this.mTemplate = str3;
            this.mWsKey = str5;
            this.mJDPin = str4;
            this.mAppId = str2;
            byte[] bArr = new byte[5];
            System.arraycopy(this.cryptoUtils.isCertExists(StringTools.getMD5(str4), 5), 0, bArr, 0, 5);
            if (new String(bArr).equals("00000")) {
                JDJRLog.i(TAG, "checkApplyCert EXITST");
                this.mCertHandler.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_EXITS, "install:cert exist", str4);
                this.globalCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), "00000"));
            } else {
                JDJRLog.i(TAG, "checkApplyCert generateCertWithA2 jdpin=" + str5);
                generateCertWithWskey("mobile", this.mBizType, i2, this.mAppId, this.mTemplate, this.mJDPin, null, 5, this.mWsKey, mobileCertRetCallback);
            }
        }
    }
}
