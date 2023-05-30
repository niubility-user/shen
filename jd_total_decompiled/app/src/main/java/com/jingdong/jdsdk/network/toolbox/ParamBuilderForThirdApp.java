package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes14.dex */
public class ParamBuilderForThirdApp extends HttpSettingTool {
    private static String TAG = "ParamBuilderForThirdApp";
    private static AtomicLong sServerTimeOffset = new AtomicLong(0);

    private static Map<String, String> getCustomBodyParam(HttpSetting httpSetting) {
        HashMap hashMap = new HashMap();
        if (httpSetting.getCustomMapParam() != null) {
            hashMap.putAll(httpSetting.getCustomMapParam());
        }
        if (httpSetting.getCustomEncryptMapParam() != null) {
            hashMap.putAll(httpSetting.getCustomEncryptMapParam());
        }
        return hashMap;
    }

    public static void setServerTimeOffset(long j2) {
        sServerTimeOffset.set(j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x045f A[Catch: all -> 0x04bc, TryCatch #5 {all -> 0x04bc, blocks: (B:146:0x0454, B:148:0x045f, B:149:0x0466, B:151:0x046c, B:153:0x0472, B:154:0x0479, B:156:0x047f, B:157:0x0487, B:159:0x048d, B:161:0x0499, B:163:0x04a5), top: B:190:0x0454 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x047f A[Catch: all -> 0x04bc, TryCatch #5 {all -> 0x04bc, blocks: (B:146:0x0454, B:148:0x045f, B:149:0x0466, B:151:0x046c, B:153:0x0472, B:154:0x0479, B:156:0x047f, B:157:0x0487, B:159:0x048d, B:161:0x0499, B:163:0x04a5), top: B:190:0x0454 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0284 A[Catch: all -> 0x02bb, TryCatch #4 {all -> 0x02bb, blocks: (B:73:0x0262, B:75:0x0268, B:77:0x0272, B:78:0x027e, B:80:0x0284, B:82:0x0290, B:84:0x02a0), top: B:188:0x0262 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void setupParams(HttpRequest httpRequest) {
        String str;
        String str2;
        HashMap hashMap;
        String str3;
        String str4;
        Iterator<String> it;
        HttpSetting httpSetting = HttpSettingTool.setupBaseParams(httpRequest);
        String appId = httpSetting.getAppId();
        if (TextUtils.isEmpty(appId)) {
            appId = JDHttpTookit.getEngine().getAppId();
        }
        httpSetting.putMapParams("appid", appId);
        httpSetting.putMapParams("t", String.valueOf(System.currentTimeMillis() + sServerTimeOffset.get()));
        String str5 = "";
        if (httpSetting.getCustomMapParam() == null || !httpSetting.getCustomMapParam().containsKey("uuid")) {
            str = "";
        } else {
            str = httpSetting.getCustomMapParam().get("uuid");
            httpSetting.getCustomMapParam().remove("uuid");
        }
        if (TextUtils.isEmpty(str) && httpSetting.getCustomEncryptMapParam() != null && httpSetting.getCustomEncryptMapParam().containsKey("uuid")) {
            str = httpSetting.getCustomEncryptMapParam().get("uuid");
            httpSetting.getCustomEncryptMapParam().remove("uuid");
        }
        if (TextUtils.isEmpty(str)) {
            str = JDHttpTookit.getEngine().getStatInfoConfigImpl().getDeviceUUID(httpSetting.getFunctionId(), httpSetting.isEnableEncryptTransmission());
        }
        if (TextUtils.isEmpty(str)) {
            str = "unknow";
        }
        String str6 = str;
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- uuid -->> " + str6);
        }
        HttpSettingTool.addGuardVerifyLmtCode(httpSetting);
        String bodyParam = HttpSettingTool.getBodyParam(httpSetting);
        if (httpSetting.isNeedExtraStatisticParam()) {
            Map<String, String> colorStatParamStr = JDHttpTookit.getEngine().getStatInfoConfigImpl().getColorStatParamStr(httpSetting.isNeedGlobalInitialization(), httpSetting.isNeedLoal(), httpSetting.isEnableEncryptTransmission(), httpSetting.getCustomEncryptMapParam(), str6);
            if (colorStatParamStr != null && !colorStatParamStr.isEmpty()) {
                String str7 = colorStatParamStr.get(IStatInfoConfig.KEY_CLEARTEXT);
                String str8 = colorStatParamStr.get(IStatInfoConfig.KEY_ENCRYPT);
                if (!TextUtils.isEmpty(str7)) {
                    if (httpSetting.isPost()) {
                        if (httpSetting.getMapParams() != null) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(httpSetting.getUrl());
                            if (!httpSetting.getMapParams().isEmpty()) {
                                stringBuffer.append("?");
                                Iterator<String> it2 = httpSetting.getMapParams().keySet().iterator();
                                while (it2.hasNext()) {
                                    String next = it2.next();
                                    String str9 = str5;
                                    String str10 = httpSetting.getMapParams().get(next);
                                    if ("body".equalsIgnoreCase(next)) {
                                        it = it2;
                                    } else {
                                        it = it2;
                                        stringBuffer.append(next + ContainerUtils.KEY_VALUE_DELIMITER + str10 + ContainerUtils.FIELD_DELIMITER);
                                    }
                                    str5 = str9;
                                    it2 = it;
                                }
                            }
                            str3 = str5;
                            if (stringBuffer.charAt(stringBuffer.length() - 1) == '&') {
                                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                            }
                            httpSetting.setUrl(stringBuffer.toString());
                        } else {
                            str3 = "";
                        }
                    } else {
                        str3 = "";
                        httpSetting.setUrl(HttpGroup.mergerUrlAndParams(httpSetting.getUrl(), httpSetting.getMapParams()));
                    }
                    String url = httpSetting.getUrl();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(url);
                    stringBuffer2.append(str7);
                    httpSetting.setUrl(stringBuffer2.toString());
                    HttpSettingTool.addCustomQueryParam(httpSetting);
                    if (JDHttpTookit.getEngine().isNeedVerifySignature() && httpSetting.needSignature()) {
                        httpSetting.getMapParams().remove("appid");
                        httpSetting.getMapParams().remove("t");
                        str4 = signatureFromJava(httpSetting, bodyParam);
                    } else {
                        str4 = str3;
                    }
                    if (httpSetting.isEnableEncryptTransmission() && !TextUtils.isEmpty(str8)) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append(url);
                        stringBuffer3.append(str8);
                        httpSetting.setUrl(stringBuffer3.toString());
                        HttpSettingTool.addCustomQueryParam(httpSetting);
                        StringBuffer stringBuffer4 = new StringBuffer();
                        stringBuffer4.append(httpSetting.getUrl());
                        if (!httpSetting.isPost()) {
                            try {
                                if (httpSetting.isEncryptBody()) {
                                    String encryptBody = JDHttpTookit.getEngine().getStatInfoConfigImpl().encryptBody(bodyParam);
                                    if (!TextUtils.isEmpty(encryptBody)) {
                                        String encode = URLEncoder.encode(encryptBody, "UTF-8");
                                        stringBuffer4.append("&bef=1");
                                        stringBuffer4.append("&body=");
                                        stringBuffer4.append(encode);
                                    }
                                    if (httpSetting.getCustomMapParam() != null && !httpSetting.getCustomMapParam().isEmpty()) {
                                        for (String str11 : httpSetting.getCustomMapParam().keySet()) {
                                            if (!TextUtils.isEmpty(str11) && !TextUtils.isEmpty(httpSetting.getCustomMapParam().get(str11))) {
                                                stringBuffer4.append(ContainerUtils.FIELD_DELIMITER);
                                                stringBuffer4.append(str11);
                                                stringBuffer4.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                                stringBuffer4.append(URLEncoder.encode(httpSetting.getCustomMapParam().get(str11), "UTF-8"));
                                            }
                                        }
                                    }
                                } else {
                                    String encode2 = URLEncoder.encode(bodyParam, "UTF-8");
                                    stringBuffer4.append("&body=");
                                    stringBuffer4.append(encode2);
                                }
                                if (httpSetting.getCustomMapParam() != null) {
                                    while (r4.hasNext()) {
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        } else if (httpSetting.isEncryptBody()) {
                            stringBuffer4.append("&bef=1");
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            stringBuffer4.append(str4);
                        }
                        httpSetting.setUrl(stringBuffer4.toString());
                    } else {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append(httpSetting.getUrl());
                        if (!httpSetting.isPost()) {
                            try {
                                String encode3 = URLEncoder.encode(bodyParam, "UTF-8");
                                stringBuffer5.append("&body=");
                                stringBuffer5.append(encode3);
                            } catch (Throwable unused2) {
                            }
                            try {
                                Map<String, String> customBodyParam = getCustomBodyParam(httpSetting);
                                if (!customBodyParam.isEmpty()) {
                                    for (String str12 : customBodyParam.keySet()) {
                                        if (!TextUtils.isEmpty(str12) && !TextUtils.isEmpty(customBodyParam.get(str12))) {
                                            stringBuffer5.append(ContainerUtils.FIELD_DELIMITER);
                                            stringBuffer5.append(str12);
                                            stringBuffer5.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                            stringBuffer5.append(URLEncoder.encode(customBodyParam.get(str12), "UTF-8"));
                                        }
                                    }
                                }
                            } catch (Throwable unused3) {
                            }
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            stringBuffer5.append(str4);
                        }
                        httpSetting.setUrl(stringBuffer5.toString());
                    }
                }
            } else {
                if (httpSetting.isPost()) {
                    if (httpSetting.getMapParams() != null) {
                        StringBuffer stringBuffer6 = new StringBuffer();
                        stringBuffer6.append(httpSetting.getUrl());
                        if (!httpSetting.getMapParams().isEmpty()) {
                            stringBuffer6.append("?");
                            for (String str13 : httpSetting.getMapParams().keySet()) {
                                String str14 = httpSetting.getMapParams().get(str13);
                                if (!"body".equalsIgnoreCase(str13)) {
                                    stringBuffer6.append(str13 + ContainerUtils.KEY_VALUE_DELIMITER + str14 + ContainerUtils.FIELD_DELIMITER);
                                }
                            }
                        }
                        if (stringBuffer6.charAt(stringBuffer6.length() - 1) == '&') {
                            stringBuffer6.deleteCharAt(stringBuffer6.length() - 1);
                        }
                        httpSetting.setUrl(stringBuffer6.toString());
                    }
                } else {
                    httpSetting.setUrl(HttpGroup.mergerUrlAndParams(httpSetting.getUrl(), httpSetting.getMapParams()));
                }
                HttpSettingTool.addCustomQueryParam(httpSetting);
                if (JDHttpTookit.getEngine().isNeedVerifySignature() && httpSetting.needSignature()) {
                    httpSetting.getMapParams().remove("appid");
                    httpSetting.getMapParams().remove("t");
                    str2 = signatureFromJava(httpSetting, bodyParam);
                } else {
                    str2 = "";
                }
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append(httpSetting.getUrl());
                if (!httpSetting.isPost()) {
                    try {
                        if (httpSetting.isEncryptBody() && httpSetting.isEnableEncryptTransmission()) {
                            String encryptBody2 = JDHttpTookit.getEngine().getStatInfoConfigImpl().encryptBody(bodyParam);
                            if (!TextUtils.isEmpty(encryptBody2)) {
                                String encode4 = URLEncoder.encode(encryptBody2, "UTF-8");
                                stringBuffer7.append("&bef=1");
                                stringBuffer7.append("&body=");
                                stringBuffer7.append(encode4);
                            }
                            hashMap = new HashMap();
                            if (httpSetting.getCustomMapParam() != null) {
                                hashMap.putAll(httpSetting.getCustomMapParam());
                            }
                            if (!httpSetting.isEnableEncryptTransmission() && httpSetting.getCustomEncryptMapParam() != null) {
                                hashMap.putAll(httpSetting.getCustomEncryptMapParam());
                            }
                            if (!hashMap.isEmpty()) {
                                for (String str15 : hashMap.keySet()) {
                                    if (!TextUtils.isEmpty(str15) && !TextUtils.isEmpty((CharSequence) hashMap.get(str15))) {
                                        stringBuffer7.append(ContainerUtils.FIELD_DELIMITER);
                                        stringBuffer7.append(str15);
                                        stringBuffer7.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                        stringBuffer7.append(URLEncoder.encode((String) hashMap.get(str15), "UTF-8"));
                                    }
                                }
                            }
                        } else {
                            String encode5 = URLEncoder.encode(bodyParam, "UTF-8");
                            stringBuffer7.append("&body=");
                            stringBuffer7.append(encode5);
                        }
                        hashMap = new HashMap();
                        if (httpSetting.getCustomMapParam() != null) {
                        }
                        if (!httpSetting.isEnableEncryptTransmission()) {
                            hashMap.putAll(httpSetting.getCustomEncryptMapParam());
                        }
                        if (!hashMap.isEmpty()) {
                        }
                    } catch (Throwable unused4) {
                    }
                } else if (httpSetting.isEncryptBody()) {
                    stringBuffer7.append("&bef=1");
                }
                if (!TextUtils.isEmpty(str2)) {
                    stringBuffer7.append(str2);
                }
                httpSetting.setUrl(stringBuffer7.toString());
            }
        }
        HttpSettingTool.doSignUsingJdGuard(httpSetting, bodyParam);
        JDHttpTookit.getEngine().getExternalDebugConfigImpl().addMockerIdName(httpSetting);
    }

    private static String signatureFromJava(HttpSetting httpSetting, String str) {
        String functionId = httpSetting.getFunctionId();
        String secretKey = httpSetting.getSecretKey();
        if (TextUtils.isEmpty(secretKey)) {
            secretKey = JDHttpTookit.getEngine().getSecretKey();
        }
        if (!TextUtils.isEmpty(functionId) && !TextUtils.isEmpty(secretKey)) {
            if (OKLog.D) {
                OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..functionId -->> " + functionId);
            }
            try {
                String url = httpSetting.getUrl();
                String signature2 = GatewaySignatureHelper.signature2(url, str, secretKey, getCustomBodyParam(httpSetting));
                if (OKLog.D) {
                    OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..url str -->> " + url);
                    OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..sign str -->> " + signature2);
                }
                return signature2;
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
