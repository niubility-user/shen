package com.jdjr.mobilecert;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Base64;
import com.jdjr.securehttp.GeneralErrorCode;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.securehttp.SecureHttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.DeviceInfo;
import com.jdjr.tools.EnvConfig;
import com.jdjr.tools.EnvSwitchUtils;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jdjr.tools.StringTools;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.wangyin.platform.CryptoUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class MobileCertProcessor {
    private static final String TAG = "MobileCertHandler";
    private CryptoUtils cryptoUtils;
    private SecureHttpHandler httpHandler;
    private MobileCertRetCallback mApplyCertRet;
    private Context mContext;
    private String mJDPin;
    private boolean hadCallback = false;
    private ApplyCertTimer applyCertTimer = new ApplyCertTimer(3000, 1000);
    SecureHttpHandler.secureHttpRetCallback callback = new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.mobilecert.MobileCertProcessor.1
        @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
        public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
            JDJRLog.i(MobileCertProcessor.TAG, "MobileCertProcessor getResultMessage=" + jDJRResultMessage.getResult());
            JDJRLog.i(MobileCertProcessor.TAG, "MobileCertProcessor getResultCode=" + jDJRResultMessage.getErrorCode());
            if (jDJRResultMessage.getErrorCode().equals("00000")) {
                String str = new String(jDJRResultMessage.getResult());
                JDJRLog.i(MobileCertProcessor.TAG, "handleImportCert begin");
                String handleImportCert = MobileCertProcessor.this.handleImportCert(str);
                JDJRLog.i(MobileCertProcessor.TAG, "handleImportCert finished");
                if (handleImportCert != null && handleImportCert.equals("00000")) {
                    if (MobileCertProcessor.this.hadCallback) {
                        MobileCertProcessor.this.cryptoUtils.deleteCertificate(StringTools.getMD5(MobileCertProcessor.this.mJDPin));
                        return;
                    }
                    MobileCertProcessor mobileCertProcessor = MobileCertProcessor.this;
                    mobileCertProcessor.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_SUCCESS, "result ok", mobileCertProcessor.mJDPin);
                    MobileCertProcessor.this.mApplyCertRet.getMobileCertResultMessage(new JDJRResultMessage(handleImportCert.getBytes(), "00000"));
                    if (MobileCertProcessor.this.applyCertTimer != null) {
                        MobileCertProcessor.this.applyCertTimer.cancel();
                        return;
                    }
                    return;
                }
                JDJRLog.i(MobileCertProcessor.TAG, "handleImportCert FAILED");
                MobileCertProcessor.this.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "import error:" + handleImportCert + "|" + str, MobileCertProcessor.this.mJDPin);
                if (MobileCertProcessor.this.hadCallback) {
                    return;
                }
                MobileCertProcessor.this.mApplyCertRet.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), handleImportCert));
                if (MobileCertProcessor.this.applyCertTimer != null) {
                    MobileCertProcessor.this.applyCertTimer.cancel();
                    return;
                }
                return;
            }
            JDJRLog.e(MobileCertProcessor.TAG, "SecureHttpHandler failed:" + jDJRResultMessage.getErrorCode());
            MobileCertProcessor.this.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "http_error:" + jDJRResultMessage.getErrorCode() + "|" + jDJRResultMessage.getResultString(), MobileCertProcessor.this.mJDPin);
            if (jDJRResultMessage.getResult() != null && jDJRResultMessage.getErrorCode() != null && jDJRResultMessage.getErrorCode().length() != 0) {
                if (MobileCertProcessor.this.hadCallback) {
                    return;
                }
                MobileCertProcessor.this.mApplyCertRet.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), new String(jDJRResultMessage.getErrorCode())));
                if (MobileCertProcessor.this.applyCertTimer != null) {
                    MobileCertProcessor.this.applyCertTimer.cancel();
                    return;
                }
                return;
            }
            MobileCertProcessor mobileCertProcessor2 = MobileCertProcessor.this;
            mobileCertProcessor2.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "ret null", mobileCertProcessor2.mJDPin);
            if (MobileCertProcessor.this.hadCallback) {
                return;
            }
            MobileCertProcessor.this.mApplyCertRet.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), ""));
            if (MobileCertProcessor.this.applyCertTimer != null) {
                MobileCertProcessor.this.applyCertTimer.cancel();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class ApplyCertTimer extends CountDownTimer {
        MobileCertRetCallback mobileCertRetCallback;

        public ApplyCertTimer(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MobileCertRetCallback mobileCertRetCallback = this.mobileCertRetCallback;
            if (mobileCertRetCallback != null) {
                mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), GeneralErrorCode.INSTALL_CERT_TIMEOUT));
            }
            MobileCertProcessor.this.setHadCallback(true);
            MobileCertProcessor mobileCertProcessor = MobileCertProcessor.this;
            mobileCertProcessor.composeErrorInfo(GeneralErrorCode.INSTALL_CERT_TIMEOUT, "cert is timeout", mobileCertProcessor.mJDPin);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
        }

        public void setMobileCertRetCallback(MobileCertRetCallback mobileCertRetCallback) {
            this.mobileCertRetCallback = mobileCertRetCallback;
        }
    }

    public MobileCertProcessor(Context context) {
        this.httpHandler = new SecureHttpHandler(context);
        CryptoUtils newInstance = CryptoUtils.newInstance(context.getApplicationContext());
        this.cryptoUtils = newInstance;
        newInstance.mobileCertInit(context);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String handleImportCert(String str) {
        byte[] bArr;
        JDJRLog.i(TAG, "handleImportCert" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean z = jSONObject.getBoolean("issuccess");
            JDJRLog.i(TAG, "handleImportCert issuccess:" + z);
            if (z) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("respdata"));
                String string = jSONObject2.getString("respcode");
                String string2 = jSONObject2.getString("respmsg");
                String string3 = jSONObject2.getString("certbuf");
                String string4 = jSONObject2.getString("servercert");
                JDJRLog.i(TAG, "respcode:" + string + "respmsg:" + string2);
                StringBuilder sb = new StringBuilder();
                sb.append("cert:");
                sb.append(string3);
                JDJRLog.i(TAG, sb.toString());
                JDJRLog.i(TAG, "serverCert:" + string4);
                byte[] bArr2 = null;
                if (string == null || !string.equals("0")) {
                    bArr = null;
                } else {
                    bArr2 = Base64.decode(string3, 2);
                    bArr = Base64.decode(string4, 2);
                }
                if (bArr2 != null && this.cryptoUtils != null) {
                    if (bArr == null || bArr.length == 0) {
                        bArr = Base64.decode(EnvConfig.getImportServerCert(this.mContext), 2);
                    }
                    byte[] importCert = this.cryptoUtils.importCert(bArr2, bArr);
                    JDJRLog.i(TAG, "importCert finished");
                    byte[] bArr3 = new byte[5];
                    System.arraycopy(importCert, 0, bArr3, 0, 5);
                    String str2 = new String(bArr3);
                    if (str2.equals("00000")) {
                        JDJRLog.i(TAG, "handleImportCert success");
                        return "00000";
                    }
                    JDJRLog.e(TAG, "handleImportCert failed:" + str2);
                    return str2 + ":" + string2;
                }
            }
            return GeneralErrorCode.MOBILECERT_OTHER_ERROR;
        } catch (JSONException e2) {
            JDJRLog.e(TAG, "JSONException :" + e2.getMessage());
            e2.printStackTrace();
            return GeneralErrorCode.MOBILECERT_OTHER_ERROR;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHadCallback(boolean z) {
        this.hadCallback = z;
    }

    public void composeErrorInfo(String str, String str2, String str3) {
        try {
            DeviceInfo newInstance = DeviceInfo.newInstance(this.mContext);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            String[] strArr = new String[16];
            strArr[0] = "sdk_version";
            strArr[1] = newInstance.getSDKVersion();
            strArr[2] = "app_info";
            strArr[3] = newInstance.getAppPackageName();
            strArr[4] = ApplicationUpgradeHelper.APP_VERSION;
            strArr[5] = newInstance.getAppVersionName();
            strArr[6] = PushConstants.DEVICE_ID;
            strArr[7] = newInstance.getCurrentDID();
            strArr[8] = "device_type";
            strArr[9] = newInstance.getDeviceType();
            strArr[10] = "os_type";
            strArr[11] = newInstance.getOS();
            strArr[12] = "os_info";
            strArr[13] = newInstance.getOSVersion();
            strArr[14] = MobileCertConstants.JDPIN;
            strArr[15] = str3 == null ? "" : StringTools.getMD5(str3);
            DeviceInfo.composeJson(jSONObject, strArr);
            DeviceInfo.composeJson(jSONObject2, "code", str, "detail", str2);
            jSONObject2.put("function_type", "1");
            jSONArray.put(jSONObject2);
            jSONObject.put("errors", jSONArray);
            this.httpHandler.secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", jSONObject.toString()), CommonTools.upCertsErrorInfoAddress(), new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.mobilecert.MobileCertProcessor.3
                @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
                public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                    if (jDJRResultMessage.getErrorCode().equals("00000") && jDJRResultMessage.getResultString() != null && jDJRResultMessage.getResultString().length() != 0) {
                        JDJRLog.d(MobileCertProcessor.TAG, "up_success");
                    } else {
                        JDJRLog.d(MobileCertProcessor.TAG, "up_fail");
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void mobileCertDataToServer(final String str, final String str2, final SecureHttpHandler.secureHttpRetCallback securehttpretcallback) {
        JDJRLog.i(TAG, "mobileCertDataToServer source =" + str);
        new Thread() { // from class: com.jdjr.mobilecert.MobileCertProcessor.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                JDJRResultMessage sendDataByP7Envelop = MobileCertProcessor.this.httpHandler.sendDataByP7Envelop(str, str2);
                if (sendDataByP7Envelop != null && sendDataByP7Envelop.getResult() != null && sendDataByP7Envelop.getResult().length > 0 && sendDataByP7Envelop.getErrorCode().equals("00000")) {
                    JDJRLog.i(MobileCertProcessor.TAG, "secureSendDataToServer result...:" + new String(sendDataByP7Envelop.getResult()));
                    securehttpretcallback.getResultMessage(new JDJRResultMessage(sendDataByP7Envelop.getResult(), "00000"));
                } else {
                    JDJRLog.e(MobileCertProcessor.TAG, "mobileCertDataToServer result failed");
                    if (sendDataByP7Envelop != null) {
                        securehttpretcallback.getResultMessage(new JDJRResultMessage(sendDataByP7Envelop.getErrorCode().getBytes(), sendDataByP7Envelop.getErrorCode()));
                    } else {
                        securehttpretcallback.getResultMessage(new JDJRResultMessage(null, GeneralErrorCode.NETWORK_ERROR));
                    }
                }
                JDJRLog.i(MobileCertProcessor.TAG, "mobileCertDataToServer mHandler send...:");
            }
        }.start();
    }

    public String sendCertApply(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, MobileCertRetCallback mobileCertRetCallback) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.mApplyCertRet = mobileCertRetCallback;
        this.applyCertTimer.setMobileCertRetCallback(mobileCertRetCallback);
        this.applyCertTimer.start();
        setHadCallback(false);
        byte[] createP10Request = this.cryptoUtils.createP10Request(str7, StringTools.getMD5(str7), str8, i2);
        byte[] errorCode = JDJRSecureUtils.getErrorCode(createP10Request);
        byte[] retData = JDJRSecureUtils.getRetData(createP10Request);
        JDJRLog.e(TAG, "jdPin sendCertApply ==" + str7);
        String str9 = str7 + "(mobile" + str2 + StringTools.getMD5(str7) + ")";
        if (new String(errorCode).equals("00000")) {
            JDJRLog.i(TAG, "createP10Request SUCCESS");
            JDJRLog.i(TAG, "p10Content:" + new String(retData));
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", MobileCertConstants.OTPTYPE);
                jSONObject.put(MobileCertConstants.USERNAME, str9);
                jSONObject.put(MobileCertConstants.P10, new String(retData));
                jSONObject.put(MobileCertConstants.TEMPLATE, str5);
                jSONObject.put("appname", str);
                jSONObject.put(MobileCertConstants.VCODE, str6);
                jSONObject.put(MobileCertConstants.JDPIN, str7);
                jSONObject.put(MobileCertConstants.WSKEY, str4);
                jSONObject.put("appid", str3);
                jSONObject.put("client", "android");
                if (EnvSwitchUtils.isTestEnvironment()) {
                    jSONObject.put(MobileCertConstants.JDPIN, str7);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            JDJRLog.e(TAG, "secureSendDataToServer jo.toString():" + jSONObject.toString());
            mobileCertDataToServer(jSONObject.toString(), EnvConfig.getApplyDigitalCertUrl(this.mContext), this.callback);
            return null;
        }
        if (!this.hadCallback) {
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), new String(errorCode)));
            ApplyCertTimer applyCertTimer = this.applyCertTimer;
            if (applyCertTimer != null) {
                applyCertTimer.cancel();
            }
        }
        composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "p10 error:" + new String(errorCode), str7);
        JDJRLog.e(TAG, "createP10Request failed");
        return null;
    }

    public String sendCertApplyWithWskey(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, MobileCertRetCallback mobileCertRetCallback) {
        return sendCertApplyWithWskey(str, str2, -1, str3, str4, str5, str6, str7, str8, i2, mobileCertRetCallback);
    }

    public String sendCertApplyWithWskey(String str, String str2, int i2, String str3, String str4, String str5, String str6, String str7, String str8, int i3, MobileCertRetCallback mobileCertRetCallback) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.applyCertTimer.setMobileCertRetCallback(mobileCertRetCallback);
        this.applyCertTimer.start();
        setHadCallback(false);
        this.mApplyCertRet = mobileCertRetCallback;
        this.mJDPin = str7;
        byte[] createP10Request = this.cryptoUtils.createP10Request(StringTools.getMD5(str7), StringTools.getMD5(str7), str8, i3);
        byte[] errorCode = JDJRSecureUtils.getErrorCode(createP10Request);
        byte[] retData = JDJRSecureUtils.getRetData(createP10Request);
        JDJRLog.e(TAG, "jdPin sendCertApply ==" + str4);
        String str9 = str7 + "(mobile" + str2 + StringTools.getMD5(str7) + ")";
        if (new String(errorCode).equals("00000")) {
            JDJRLog.i(TAG, "createP10Request SUCCESS");
            JDJRLog.i(TAG, "p10Content:" + new String(retData));
            JSONObject jSONObject = new JSONObject();
            if (i2 != -1) {
                try {
                    jSONObject.put(MobileCertConstants.LOGINKEYTYPE, i2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            jSONObject.put("type", MobileCertConstants.OTPTYPE);
            jSONObject.put(MobileCertConstants.USERNAME, str9);
            jSONObject.put(MobileCertConstants.P10, new String(retData));
            jSONObject.put(MobileCertConstants.TEMPLATE, str5);
            jSONObject.put("appname", str);
            jSONObject.put(MobileCertConstants.VCODE, str6);
            jSONObject.put(MobileCertConstants.WSKEY, str4);
            jSONObject.put(MobileCertConstants.APPCHANNEL, str3);
            jSONObject.put("client", "android");
            if (EnvSwitchUtils.isTestEnvironment()) {
                jSONObject.put(MobileCertConstants.JDPIN, str7);
            }
            JDJRLog.e(TAG, "secureSendDataToServer jo.toString():" + jSONObject.toString());
            mobileCertDataToServer(jSONObject.toString(), EnvConfig.getApplyDigitalCertUrl(this.mContext), this.callback);
            return null;
        }
        if (!this.hadCallback) {
            mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), new String(errorCode)));
            ApplyCertTimer applyCertTimer = this.applyCertTimer;
            if (applyCertTimer != null) {
                applyCertTimer.cancel();
            }
        }
        composeErrorInfo(GeneralErrorCode.INSTALL_CERT_ERROR, "p10 error:" + new String(errorCode), str7);
        JDJRLog.e(TAG, "createP10Request failed");
        return null;
    }
}
