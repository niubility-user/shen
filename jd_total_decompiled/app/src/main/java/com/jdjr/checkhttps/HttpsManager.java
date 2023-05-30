package com.jdjr.checkhttps;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.securehttp.SecureHttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.DeviceInfo;
import com.jdjr.tools.JDJRLog;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.tencent.smtt.sdk.ProxyConfig;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class HttpsManager {
    public static final int CERT_TIME_ERROR = 3;
    public static final int HOST_ERROR = 2;
    public static final int OTHER_ERROR = 4;
    public static final int ROOT_CERT_ERROR = 1;
    private static final String TAG = "HttpsManager";
    private static HttpsManager instance;
    private static final Object lock = new Object();
    private String certsData;
    private LocalCertsManager localCertsManager;
    private Context mContext;
    private SecureHttpHandler mHttpHandler;

    private HttpsManager(Context context) {
        this.mHttpHandler = new SecureHttpHandler(context);
        LocalCertsManager newInstance = LocalCertsManager.newInstance(context);
        this.localCertsManager = newInstance;
        this.mContext = context;
        this.certsData = newInstance.queryAllCerts();
    }

    private void composeErrorInfo(int i2, String str, String str2) {
        DeviceInfo newInstance = DeviceInfo.newInstance(this.mContext);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        DeviceInfo.composeJson(jSONObject, "sdk_version", newInstance.getSDKVersion(), "app_info", newInstance.getAppPackageName(), ApplicationUpgradeHelper.APP_VERSION, newInstance.getAppVersionName(), PushConstants.DEVICE_ID, newInstance.getCurrentDID(), "device_type", newInstance.getDeviceType(), "os_type", newInstance.getOS(), "os_info", newInstance.getOSVersion());
        DeviceInfo.composeJson(jSONObject2, "code", String.valueOf(i2), "detail", str);
        try {
            if (!TextUtils.isEmpty(str2)) {
                jSONObject2.put("host", str2);
            }
            jSONArray.put(jSONObject2);
            jSONObject.put("errors", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.mHttpHandler.secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", jSONObject.toString()), CommonTools.upCertsErrorInfoAddress(), new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.checkhttps.HttpsManager.2
            @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
            public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                if (jDJRResultMessage.getErrorCode().equals("00000") && jDJRResultMessage.getResultString() != null && jDJRResultMessage.getResultString().length() != 0) {
                    JDJRLog.d(HttpsManager.TAG, "up_success");
                } else {
                    JDJRLog.d(HttpsManager.TAG, "up_fail");
                }
            }
        });
    }

    private String generateCertSN(String str) {
        String bigInteger = CertsUtils.getCertByContent(str).getSerialNumber().toString(16);
        if (bigInteger.length() % 2 != 0) {
            return String.format("%s" + bigInteger, "0");
        }
        return bigInteger;
    }

    private String getBase64Cert(X509Certificate x509Certificate) {
        try {
            return Base64.encodeToString(x509Certificate.getEncoded(), 2);
        } catch (CertificateEncodingException e2) {
            e2.printStackTrace();
            return "CertificateEncodingException";
        }
    }

    private String getCertsSN() {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(this.certsData)) {
            String certsContent = CertsUtils.getCertsContent(this.mContext);
            this.certsData = certsContent;
            if (!TextUtils.isEmpty(certsContent)) {
                String[] split = this.certsData.split(DYConstants.DY_REGEX_VERTICAL_LINE);
                getCertsSN(sb, split);
                this.localCertsManager.addCerts(split);
            }
        }
        if (!TextUtils.isEmpty(this.certsData)) {
            getCertsSN(sb, this.certsData.split(DYConstants.DY_REGEX_VERTICAL_LINE));
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    private List<String> getChainSubjectNames(X509Certificate x509Certificate) {
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && ((Integer) list.get(0)) != null && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    public static HttpsManager newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HttpsManager(context);
                }
            }
        }
        return instance;
    }

    private void printCerts(String[] strArr, String str) {
        for (String str2 : strArr) {
            JDJRLog.d(TAG, str + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCerts(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.substring(17).split(DYConstants.DY_REGEX_VERTICAL_LINE);
        this.certsData = this.localCertsManager.addCerts(split);
        JDJRLog.d(TAG, "current_certs:" + this.certsData);
        printCerts(split, "server_certs:");
    }

    public void checkHttpsCert(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        isServerCertsMatch(x509CertificateArr);
        isHostMatch(x509CertificateArr[0], str);
    }

    public boolean deleteCert(String str) {
        return false;
    }

    public void isHostMatch(X509Certificate x509Certificate, String str) throws CertificateException {
        if (!TextUtils.isEmpty(str)) {
            List<String> chainSubjectNames = getChainSubjectNames(x509Certificate);
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= chainSubjectNames.size()) {
                    break;
                }
                String str2 = chainSubjectNames.get(i2);
                if (str2.contains(ProxyConfig.MATCH_ALL_SCHEMES)) {
                    str2 = str2.substring(str2.lastIndexOf(ProxyConfig.MATCH_ALL_SCHEMES) + 1).trim();
                }
                if (str.contains(str2)) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (z) {
                return;
            }
            throw new CertificateException("[" + str + "]hostname is not correct");
        }
        composeErrorInfo(2, "hostname is empty", "");
        throw new CertificateException("hostname is empty");
    }

    public void isServerCertsMatch(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        if (x509CertificateArr != null) {
            if (x509CertificateArr.length >= 1) {
                X509Certificate x509Certificate = x509CertificateArr[x509CertificateArr.length - 1];
                if (TextUtils.isEmpty(this.certsData)) {
                    this.certsData = CertsUtils.getCertsContent(this.mContext);
                }
                String[] split = this.certsData.split(DYConstants.DY_REGEX_VERTICAL_LINE);
                int i2 = 0;
                while (true) {
                    if (i2 >= split.length) {
                        z = true;
                        break;
                    } else if (x509Certificate.equals(CertsUtils.getCertByContent(split[i2]))) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= split.length) {
                            break;
                        }
                        try {
                            x509Certificate.verify(CertsUtils.getCertByContent(split[i3]).getPublicKey());
                            z = false;
                            break;
                        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException unused) {
                            i3++;
                        }
                    }
                    if (z) {
                        composeErrorInfo(1, getBase64Cert(x509Certificate), "");
                        throw new CertificateException("root certificate is not correct");
                    }
                }
                try {
                    x509CertificateArr[0].checkValidity();
                    return;
                } catch (CertificateException unused2) {
                    composeErrorInfo(3, getBase64Cert(x509CertificateArr[0]), "");
                    throw new CertificateException("validity is error");
                }
            }
            composeErrorInfo(4, "X509Certificate is empty", "");
            throw new CertificateException("X509Certificate is empty");
        }
        composeErrorInfo(4, "X509Certificate array is null", "");
        throw new CertificateException("X509Certificate array is null");
    }

    public void requestCertInfo() {
        String serverCertsAddress = CommonTools.getServerCertsAddress();
        this.mHttpHandler.secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", getCertsSN()), serverCertsAddress, new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.checkhttps.HttpsManager.1
            @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
            public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                if (jDJRResultMessage.getErrorCode().equals("00000") && jDJRResultMessage.getResultString() != null && jDJRResultMessage.getResultString().length() != 0) {
                    if (jDJRResultMessage.getResultString().length() <= 17 || CommonTools.isOvertime20min(jDJRResultMessage.getResultString().substring(0, 17))) {
                        return;
                    }
                    JDJRLog.i(HttpsManager.TAG, "TIME CHECK SUCCESS");
                    if (jDJRResultMessage.getResultString() != null) {
                        JDJRLog.d(HttpsManager.TAG, "data:" + jDJRResultMessage.getResultString());
                        HttpsManager.this.saveCerts(jDJRResultMessage.getResultString());
                        return;
                    }
                    return;
                }
                JDJRLog.i(HttpsManager.TAG, "NO NEED UPDATE");
            }
        });
    }

    public boolean searchCert(String str) {
        return false;
    }

    private void getCertsSN(StringBuilder sb, String[] strArr) {
        for (String str : strArr) {
            String generateCertSN = generateCertSN(str);
            if (!TextUtils.isEmpty(generateCertSN)) {
                sb.append(generateCertSN);
                sb.append("|");
            }
        }
    }
}
