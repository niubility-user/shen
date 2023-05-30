package com.jdjr.acr;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jdjr.identify.IdentifyListener;
import com.jdjr.identify.IdentifyManager;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jdjr.securehttp.GeneralErrorCode;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.securehttp.SecureHttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.DeviceInfo;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jdjr.tools.TaskCacheThreadPool;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.wangyin.platform.ACMUtil;
import com.wangyin.platform.CryptoUtils;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class IntegrityCheck implements IdentifyListener {
    private static final String TAG = "IntegrityCheck";
    private final Context context;
    private String jdPin;
    private final SecureHttpHandler secureHttpHandler;

    /* loaded from: classes18.dex */
    public class ErrorReportCallback implements IntegrityCheckCallback {
        private static final int UP_ERROR_CODE_FAIL = 101;
        private static final int UP_ERROR_CODE_SIG_ERROR = 103;
        private static final int UP_ERROR_CODE_UNKNOWN = 102;
        private final IntegrityCheckCallback impl;
        private final String signatureInfo;

        private void composeErrorInfo(int i2, String str) {
            DeviceInfo newInstance = DeviceInfo.newInstance(IntegrityCheck.this.context);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            DeviceInfo.composeJson(jSONObject, "sdk_version", newInstance.getSDKVersion(), "app_info", newInstance.getAppPackageName(), ApplicationUpgradeHelper.APP_VERSION, newInstance.getAppVersionName(), PushConstants.DEVICE_ID, newInstance.getCurrentDID(), "device_type", newInstance.getDeviceType(), "os_type", newInstance.getOS(), "os_info", newInstance.getOSVersion());
            DeviceInfo.composeJson(jSONObject2, "code", String.valueOf(i2), "detail", str);
            try {
                jSONObject2.put("function_type", "2");
                jSONArray.put(jSONObject2);
                jSONObject.put("errors", jSONArray);
                jSONObject.put(MobileCertConstants.JDPIN, IntegrityCheck.this.jdPin);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            IntegrityCheck.this.secureHttpHandler.secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", jSONObject.toString()), CommonTools.upCertsErrorInfoAddress(), new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.acr.IntegrityCheck.ErrorReportCallback.1
                {
                    ErrorReportCallback.this = this;
                }

                @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
                public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                    if (jDJRResultMessage.getErrorCode().equals("00000") && jDJRResultMessage.getResultString() != null && jDJRResultMessage.getResultString().length() != 0) {
                        JDJRLog.d(IntegrityCheck.TAG, "up_success");
                    } else {
                        JDJRLog.d(IntegrityCheck.TAG, "up_fail");
                    }
                }
            });
        }

        @Override // com.jdjr.acr.IntegrityCheckCallback
        public void onResult(int i2) {
            this.impl.onResult(i2);
            if (i2 == 0) {
                composeErrorInfo(102, this.signatureInfo);
            } else if (i2 == 2) {
                composeErrorInfo(101, this.signatureInfo);
            } else if (i2 != 3) {
            } else {
                composeErrorInfo(103, this.signatureInfo);
            }
        }

        private ErrorReportCallback(IntegrityCheckCallback integrityCheckCallback, String str) {
            IntegrityCheck.this = r1;
            this.impl = integrityCheckCallback;
            this.signatureInfo = str;
        }
    }

    public IntegrityCheck(Context context) {
        this.jdPin = "";
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.secureHttpHandler = new SecureHttpHandler(applicationContext);
        this.jdPin = IdentifyManager.getInstance().addListener(this);
    }

    public void sendDataToServerByEnvelop(String str, String str2, SecureHttpHandler.secureHttpRetCallback securehttpretcallback) {
        JDJRLog.i(TAG, "secureSendDataToServer content =" + str);
        JDJRResultMessage sendDataByP7Envelop = this.secureHttpHandler.sendDataByP7Envelop(str, str2);
        if (sendDataByP7Envelop != null && sendDataByP7Envelop.getResult() != null && sendDataByP7Envelop.getResult().length > 0 && sendDataByP7Envelop.getErrorCode().equals("00000")) {
            JDJRLog.i(TAG, "secureSendDataToServer result...:" + new String(sendDataByP7Envelop.getResult()));
            securehttpretcallback.getResultMessage(new JDJRResultMessage(sendDataByP7Envelop.getResult(), "00000"));
        } else if (sendDataByP7Envelop != null) {
            securehttpretcallback.getResultMessage(new JDJRResultMessage(sendDataByP7Envelop.getErrorCode().getBytes(), sendDataByP7Envelop.getErrorCode()));
        } else {
            securehttpretcallback.getResultMessage(new JDJRResultMessage(null, GeneralErrorCode.NETWORK_ERROR));
        }
        JDJRLog.i(TAG, "secureSendDataToServer mHandler send...:");
    }

    public String check() {
        StringBuilder sb = new StringBuilder();
        String integrityCheckData = ACMUtil.newInstance(this.context).getIntegrityCheckData(this.context, false);
        if (integrityCheckData != null && integrityCheckData.length() > 5) {
            String substring = integrityCheckData.substring(0, 5);
            if (!"00000".equals(substring)) {
                JDJRLog.i(TAG, "\u9519\u8bef\u7801\uff1a" + substring + "\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
            } else {
                sb.append(integrityCheckData.substring(5));
            }
        } else {
            JDJRLog.i(TAG, "\u7ed3\u679c\u9519\u8bef\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
        }
        sb.append("|");
        String integrityCheckData2 = ACMUtil.newInstance(this.context).getIntegrityCheckData(this.context, true);
        if (integrityCheckData2 != null && integrityCheckData2.length() > 5) {
            String substring2 = integrityCheckData2.substring(0, 5);
            if (!"00000".equals(substring2)) {
                JDJRLog.i(TAG, "\u9519\u8bef\u7801\uff1a" + substring2 + "\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
            } else {
                String[] split = integrityCheckData2.split(DYConstants.DY_REGEX_VERTICAL_LINE);
                if (split.length > 1) {
                    sb.append(split[1]);
                }
            }
        } else {
            JDJRLog.i(TAG, "\u7ed3\u679c\u9519\u8bef\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
        }
        String sb2 = sb.toString();
        String serverCert = CommonTools.getServerCert();
        byte[] wyP7Envelope = CryptoUtils.newInstance(this.context).wyP7Envelope(serverCert, sb2.getBytes());
        byte[] errorCode = JDJRSecureUtils.getErrorCode(wyP7Envelope);
        byte[] retData = JDJRSecureUtils.getRetData(wyP7Envelope);
        JDJRLog.i(TAG, "sendDataByP7Envelop cerData=" + serverCert);
        if (new String(errorCode).equals("00000")) {
            System.arraycopy(wyP7Envelope, 5, retData, 0, wyP7Envelope.length - 5);
            String encodeToString = Base64.encodeToString(retData, 2);
            JDJRLog.i(TAG, "p7Base64:" + encodeToString);
            JDJRLog.i(TAG, "p7Base64 length:" + (wyP7Envelope.length - 5));
            return encodeToString;
        }
        return null;
    }

    @Override // com.jdjr.identify.IdentifyListener
    public void updateJdPIN(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.jdPin = str;
    }

    public void check(String str, IntegrityCheckCallback integrityCheckCallback) {
        check(str, integrityCheckCallback, false, 0L);
        check(str, integrityCheckCallback, true, new Random().nextInt(1000) + 500);
    }

    private void check(String str, final IntegrityCheckCallback integrityCheckCallback, final boolean z, final long j2) {
        if (TextUtils.isEmpty(str)) {
            str = CommonTools.getIntegrityCheckAddress();
        }
        final String str2 = str;
        TaskCacheThreadPool.getInstance().execute(new Runnable() { // from class: com.jdjr.acr.IntegrityCheck.1
            {
                IntegrityCheck.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j3 = j2;
                if (j3 > 0) {
                    try {
                        Thread.sleep(j3);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                String integrityCheckData = ACMUtil.newInstance(IntegrityCheck.this.context).getIntegrityCheckData(IntegrityCheck.this.context, z);
                final ErrorReportCallback errorReportCallback = new ErrorReportCallback(integrityCheckCallback, integrityCheckData);
                JDJRLog.i(IntegrityCheck.TAG, "\u5b8c\u6574\u6027\u68c0\u67e5\u4fe1\u606f\uff1a" + integrityCheckData);
                if (integrityCheckData != null && integrityCheckData.length() > 5) {
                    String substring = integrityCheckData.substring(0, 5);
                    if (!"00000".equals(substring)) {
                        JDJRLog.i(IntegrityCheck.TAG, "\u9519\u8bef\u7801\uff1a" + substring + "\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
                        errorReportCallback.onResult(3);
                        return;
                    }
                    IntegrityCheck.this.sendDataToServerByEnvelop(integrityCheckData.substring(5), str2, new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.acr.IntegrityCheck.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
                        public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                            if ("00000".equals(jDJRResultMessage.getErrorCode())) {
                                if (DYConstants.DY_TRUE.equals(jDJRResultMessage.getResultString())) {
                                    errorReportCallback.onResult(1);
                                    return;
                                } else if (DYConstants.DY_FALSE.equals(jDJRResultMessage.getResultString())) {
                                    errorReportCallback.onResult(2);
                                    return;
                                }
                            }
                            errorReportCallback.onResult(0);
                        }
                    });
                    return;
                }
                JDJRLog.i(IntegrityCheck.TAG, "\u7ed3\u679c\u9519\u8bef\uff0c\u8fd4\u56deRESULT_SIG_ERROR");
                errorReportCallback.onResult(3);
            }
        });
    }
}
