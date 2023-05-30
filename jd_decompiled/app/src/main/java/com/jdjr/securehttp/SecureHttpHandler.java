package com.jdjr.securehttp;

import android.content.Context;
import android.util.Base64;
import com.jdjr.dns.RealTimeThreadPool;
import com.jdjr.tools.EnvConfig;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.wangyin.platform.CryptoUtils;

/* loaded from: classes18.dex */
public class SecureHttpHandler {
    private static final String TAG = "SecureHttpHandler";
    private static Boolean isHandshakeSuccess = Boolean.FALSE;
    private Context mContext;
    private CryptoUtils mCryptoUtils;
    private HttpHandler mHttpHandler;
    private Thread mSendDataThread = null;

    /* loaded from: classes18.dex */
    public interface secureHttpRetCallback {
        void getResultMessage(JDJRResultMessage jDJRResultMessage);
    }

    public SecureHttpHandler(Context context) {
        this.mCryptoUtils = null;
        this.mHttpHandler = null;
        this.mCryptoUtils = CryptoUtils.newInstance(context);
        this.mHttpHandler = new HttpHandler();
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JDJRResultMessage sendSecureHttpRequest(String str, String str2) {
        if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
            JDJRLog.i(TAG, "sendSercureHttpRequest p7 envelop ");
            return sendDataByP7Envelop(str, str2);
        }
        JDJRLog.e(TAG, "sendSercureHttpRequest parameter error");
        return new JDJRResultMessage(null, GeneralErrorCode.PARAMETER_ERROR);
    }

    private JDJRResultMessage verifyServerP1Data(String str) {
        JDJRLog.i(TAG, "verifyServerP1Data: data=" + str);
        String p7P1Cert = EnvConfig.getP7P1Cert(this.mContext);
        if (str != null && str.length() > 344) {
            String substring = str.substring(0, R2.attr.actionModeWebSearchDrawable);
            String substring2 = str.substring(R2.attr.actionModeWebSearchDrawable);
            JDJRLog.i(TAG, "verifyServerP1Data source =" + substring2 + ",,,,p1=" + substring);
            byte[] verifyP1SignMsg = this.mCryptoUtils.verifyP1SignMsg(p7P1Cert.getBytes(), R2.attr.constraintSet, substring2.getBytes(), substring.getBytes());
            if ("00000".equals(new String(verifyP1SignMsg))) {
                return new JDJRResultMessage(substring2.getBytes(), "00000");
            }
            JDJRLog.e(TAG, "verifyServerP1Data: err");
            return new JDJRResultMessage("".getBytes(), new String(verifyP1SignMsg));
        }
        return new JDJRResultMessage(null, GeneralErrorCode.NETWORK_ERROR);
    }

    public void secureSendDataToServer(final String str, final String str2, final secureHttpRetCallback securehttpretcallback) {
        JDJRLog.i(TAG, "secureSendDataToServer source =" + str);
        this.mSendDataThread = new Thread() { // from class: com.jdjr.securehttp.SecureHttpHandler.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                JDJRResultMessage sendSecureHttpRequest = SecureHttpHandler.this.sendSecureHttpRequest(str, str2);
                if (sendSecureHttpRequest != null && sendSecureHttpRequest.getResult() != null && sendSecureHttpRequest.getResult().length > 0 && sendSecureHttpRequest.getErrorCode().equals("00000")) {
                    JDJRLog.i(SecureHttpHandler.TAG, "secureSendDataToServer result...:" + new String(sendSecureHttpRequest.getResult()));
                    securehttpretcallback.getResultMessage(new JDJRResultMessage(sendSecureHttpRequest.getResult(), "00000"));
                } else {
                    JDJRLog.e(SecureHttpHandler.TAG, "sendSecureHttpRequest result failed");
                    if (sendSecureHttpRequest != null) {
                        securehttpretcallback.getResultMessage(new JDJRResultMessage(sendSecureHttpRequest.getErrorCode().getBytes(), sendSecureHttpRequest.getErrorCode()));
                    } else {
                        securehttpretcallback.getResultMessage(new JDJRResultMessage(null, GeneralErrorCode.NETWORK_ERROR));
                    }
                }
                JDJRLog.i(SecureHttpHandler.TAG, "secureSendDataToServer mHandler send...:");
            }
        };
        RealTimeThreadPool.getInstance().execute(this.mSendDataThread);
    }

    public JDJRResultMessage sendDataByP7Envelop(String str, String str2) {
        String p7P1Cert = EnvConfig.getP7P1Cert(this.mContext);
        JDJRLog.i(TAG, "sendDataByP7Envelop source=" + str);
        byte[] p7Envelope = this.mCryptoUtils.p7Envelope(p7P1Cert, str.getBytes());
        byte[] errorCode = JDJRSecureUtils.getErrorCode(p7Envelope);
        byte[] retData = JDJRSecureUtils.getRetData(p7Envelope);
        JDJRLog.i(TAG, "sendDataByP7Envelop cerData=" + p7P1Cert);
        if (new String(errorCode).equals("00000")) {
            System.arraycopy(p7Envelope, 5, retData, 0, p7Envelope.length - 5);
            String encodeToString = Base64.encodeToString(retData, 2);
            JDJRLog.i(TAG, "p7Base641111:" + encodeToString);
            JDJRLog.i(TAG, "p7Base64 length:" + (p7Envelope.length - 5));
            JDJRResultMessage sendHttpRequest = this.mHttpHandler.sendHttpRequest(encodeToString, str2, 0);
            if (sendHttpRequest != null && sendHttpRequest.getResult() != null && sendHttpRequest.getResult().length != 0 && sendHttpRequest.getErrorCode().equals("00000")) {
                JDJRLog.i(TAG, "serverResp=" + sendHttpRequest);
                return verifyServerP1Data(sendHttpRequest.getResultString());
            }
            JDJRLog.e(TAG, "sendHttpRequest : serverResp is null");
            return new JDJRResultMessage(null, sendHttpRequest.getErrorCode());
        }
        return new JDJRResultMessage("".getBytes(), new String(errorCode));
    }
}
