package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.InternalConfiguration;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class ParamBuilderForJDMall extends HttpSettingTool {
    private static String TAG = "ParamBuilderForJDMall";

    public static void setupParams(HttpRequest httpRequest) {
        String str;
        HttpSetting httpSetting = HttpSettingTool.setupBaseParams(httpRequest);
        HttpSettingTool.addGuardVerifyLmtCode(httpSetting);
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
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- uuid -->> " + str);
        }
        String bodyParam = HttpSettingTool.getBodyParam(httpSetting);
        if (httpSetting.getType() == 6000) {
            if (RuntimeConfigHelper.advertiseStatDataEnable()) {
                HttpSettingTool.addStatQueryParam(httpSetting, bodyParam, str);
            }
        } else {
            HttpSettingTool.addStatQueryParam(httpSetting, bodyParam, str);
        }
        HttpSettingTool.addCustomQueryParam(httpSetting);
        if (JDHttpTookit.getEngine().isNeedVerifySignature()) {
            JDHttpTookit.getEngine().getSignatureHandlerImpl().networkSettingsPreSignature();
            signature(httpSetting, bodyParam, str);
        }
        HttpSettingTool.doSignUsingJdGuard(httpSetting, bodyParam);
        JDHttpTookit.getEngine().getExternalDebugConfigImpl().addMockerIdName(httpSetting);
    }

    private static void signature(HttpSetting httpSetting, String str, String str2) {
        String functionId = httpSetting.getFunctionId();
        String property = InternalConfiguration.getProperty("client", "");
        String versionName = JDHttpTookit.getEngine().getStatInfoConfigImpl().getVersionName();
        if (functionId == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..functionId -->> " + functionId);
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..body -->> " + str);
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..uuid -->> " + str2);
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..client -->> " + property);
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..clientVersion -->> " + versionName);
        }
        try {
            String signature = JDHttpTookit.getEngine().getSignatureHandlerImpl().signature(JDHttpTookit.getEngine().getApplicationContext(), functionId, str, str2, property, versionName);
            if (OKLog.D) {
                OKLog.d("Signature", "native  load  sucess " + signature);
            }
            httpSetting.setSignature(ContainerUtils.FIELD_DELIMITER + signature);
            httpSetting.setUrl(httpSetting.getUrl() + httpSetting.getSignature());
        } catch (Exception unused) {
        }
    }
}
