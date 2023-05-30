package com.jdjr.mobilecertsm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jdjr.dns.R;
import com.jdjr.dns.RealTimeThreadPool;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jdjr.mobilecert.MobileCertRetCallback;
import com.jdjr.securehttp.GeneralErrorCode;
import com.jdjr.securehttp.HttpHandler;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.securehttp.SecureHttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.DeviceInfo;
import com.jdjr.tools.EnvConfig;
import com.jdjr.tools.EnvSwitchUtils;
import com.jdjr.tools.JDJRSecureUtils;
import com.jdjr.tools.StringTools;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.wangyin.platform.CryptoUtils;
import com.wangyin.platform.NativeCryptoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class SmCertUtils {

    /* loaded from: classes18.dex */
    public interface SecureHttpRetCallback {
        void getResultMessage(JDJRResultMessage jDJRResultMessage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkSmCertExist(Context context, String str) {
        byte[] isCertExistsSm = CryptoUtils.newInstance(context).isCertExistsSm(StringTools.getMD5(str), 5);
        byte[] bArr = new byte[5];
        System.arraycopy(isCertExistsSm, 0, bArr, 0, 5);
        return new String(bArr).equals("00000");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void composeErrorInfo(Context context, String str, String str2, String str3) {
        try {
            DeviceInfo newInstance = DeviceInfo.newInstance(context);
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
            new SecureHttpHandler(context).secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", jSONObject.toString()), CommonTools.upCertsErrorInfoAddress(), new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.mobilecertsm.SmCertUtils.2
                @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
                public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean deleteSmCert(Context context, String str) {
        byte[] bArr = new byte[5];
        System.arraycopy(CryptoUtils.newInstance(context).deleteCertificateSm(StringTools.getMD5(str)), 0, bArr, 0, 5);
        return new String(bArr).equals("00000");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getSmP7SignMsg(Context context, String str, byte[] bArr) {
        return CryptoUtils.newInstance(context).signP7AndEnvelopMsgSm(StringTools.getMD5(str), StringTools.getMD5(str), null, bArr);
    }

    static JDJRResultMessage importSmCert(Context context, JDJRResultMessage jDJRResultMessage, String str) {
        if (jDJRResultMessage == null || !"00000".equals(jDJRResultMessage.getErrorCode())) {
            return jDJRResultMessage;
        }
        byte[] bArr = null;
        try {
            JSONObject jSONObject = new JSONObject(jDJRResultMessage.getResultString());
            if (jSONObject.getBoolean(context.getString(R.string.security_sm_server_json_key_issuccess))) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString(context.getString(R.string.security_sm_server_json_key_respdata)));
                String string = jSONObject2.getString(context.getString(R.string.security_sm_server_json_key_respcode));
                String string2 = jSONObject2.getString(context.getString(R.string.security_sm_server_json_key_certbuf));
                String string3 = jSONObject2.getString(context.getString(R.string.security_sm_server_json_key_servercert));
                if (TextUtils.isEmpty(string3)) {
                    string3 = EnvConfig.getImportServerCertGm(context);
                }
                if ("0".equals(string) && !TextUtils.isEmpty(string2)) {
                    bArr = CryptoUtils.newInstance(context).importSmCert(Base64.decode(string2, 2), string3.getBytes());
                }
                return new JDJRResultMessage(JDJRSecureUtils.getRetData(bArr), new String(JDJRSecureUtils.getErrorCode(bArr)));
            }
            composeErrorInfo(context, GeneralErrorCode.MOBILECERT_FAIL, jDJRResultMessage.getResultString(), str);
            return new JDJRResultMessage(jDJRResultMessage.getResultString().getBytes(), GeneralErrorCode.MOBILECERT_FAIL);
        } catch (JSONException e2) {
            composeErrorInfo(context, GeneralErrorCode.JSON_ERROR, e2.getMessage(), str);
            return new JDJRResultMessage(e2.getMessage().getBytes(), GeneralErrorCode.JSON_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean initWbxPath(Context context) {
        FileOutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            String str = context.getFilesDir() + "/wbxstatic";
            if (new File(str).length() <= 269184) {
                inputStream = context.getResources().getAssets().open("wbx");
                try {
                    fileOutputStream = new FileOutputStream(str);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        inputStream2 = inputStream;
                    } catch (Exception unused) {
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException unused2) {
                                return false;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused3) {
                                return false;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception unused4) {
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    fileOutputStream = null;
                    th = th3;
                }
            } else {
                fileOutputStream = null;
            }
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException unused5) {
                    return false;
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
                return true;
            }
            return true;
        } catch (Exception unused6) {
            fileOutputStream = null;
        } catch (Throwable th4) {
            fileOutputStream = null;
            th = th4;
            inputStream = null;
        }
    }

    static void secureSendDataToServer(final Context context, final String str, final String str2, final String str3, final SecureHttpRetCallback secureHttpRetCallback) {
        RealTimeThreadPool.getInstance().execute(new Runnable() { // from class: com.jdjr.mobilecertsm.SmCertUtils.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String p7SMEnvelope = CryptoUtils.newInstance(context).p7SMEnvelope(EnvConfig.getP7P1CertGm(context), str);
                    if (secureHttpRetCallback != null) {
                        secureHttpRetCallback.getResultMessage(SmCertUtils.importSmCert(context, SmCertUtils.verifyServerP1DataSm(context, new HttpHandler().sendHttpRequest(p7SMEnvelope, str2, 2)), str3));
                    } else {
                        new HttpHandler().sendHttpRequest(p7SMEnvelope, str2, -1);
                    }
                } catch (NativeCryptoException e2) {
                    e2.printStackTrace();
                    if (secureHttpRetCallback != null) {
                        SmCertUtils.composeErrorInfo(context, "", e2.getMessage(), str3);
                        secureHttpRetCallback.getResultMessage(new JDJRResultMessage("".getBytes(), e2.getMessage()));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendSmCertApply(Context context, String str, String str2, int i2, String str3, String str4, String str5, String str6, String str7, String str8, int i3, final MobileCertRetCallback mobileCertRetCallback) {
        byte[] createP10RequestSm = CryptoUtils.newInstance(context).createP10RequestSm(StringTools.getMD5(str7), StringTools.getMD5(str7), str8, i3);
        byte[] errorCode = JDJRSecureUtils.getErrorCode(createP10RequestSm);
        byte[] retData = JDJRSecureUtils.getRetData(createP10RequestSm);
        String str9 = str7 + "(mobile" + str2 + StringTools.getMD5(str7) + ")";
        if (new String(errorCode).equals("00000")) {
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
            secureSendDataToServer(context, jSONObject.toString(), EnvConfig.getApplyDigitalCertUrl(context), str7, new SecureHttpRetCallback() { // from class: com.jdjr.mobilecertsm.SmCertUtils.1
                @Override // com.jdjr.mobilecertsm.SmCertUtils.SecureHttpRetCallback
                public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                    MobileCertRetCallback.this.getMobileCertResultMessage(jDJRResultMessage);
                }
            });
            return;
        }
        mobileCertRetCallback.getMobileCertResultMessage(new JDJRResultMessage("".getBytes(), new String(errorCode)));
        composeErrorInfo(context, GeneralErrorCode.INSTALL_CERT_ERROR, context.getString(R.string.security_sm_p10_error) + new String(errorCode), str7);
    }

    static JDJRResultMessage verifyServerP1DataSm(Context context, JDJRResultMessage jDJRResultMessage) {
        if (jDJRResultMessage == null || !"00000".equals(jDJRResultMessage.getErrorCode())) {
            return jDJRResultMessage;
        }
        String resultString = jDJRResultMessage.getResultString();
        String substring = resultString.substring(0, resultString.indexOf(123));
        String substring2 = resultString.substring(resultString.indexOf(123));
        return new JDJRResultMessage(substring2.getBytes(), new String(CryptoUtils.newInstance(context).verifyP1SignMsgSm(EnvConfig.getP7P1CertGm(context).getBytes(), R2.attr.constraintSet, substring2.getBytes(), substring.getBytes())));
    }
}
