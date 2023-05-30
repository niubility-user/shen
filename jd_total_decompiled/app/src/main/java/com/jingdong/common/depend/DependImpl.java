package com.jingdong.common.depend;

import android.app.Activity;
import android.content.Context;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.login.SafetyManager;
import com.jingdong.common.network.NetworkSetting;
import com.jingdong.common.network.SignatureAlertController;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.BitmapkitUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.depend.Idepend;
import com.jingdong.jdsdk.network.toolbox.HttpSettingTool;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.lib.netdiagnosis.NetDiagnosisController;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;

@Deprecated
/* loaded from: classes5.dex */
public class DependImpl implements Idepend {
    @Override // com.jingdong.jdsdk.depend.Idepend
    public void autoNetDiagnose() {
        NetDiagnosisController.getController().autoNetDiagnose();
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getCookies() {
        return SafetyManager.getCookies();
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getReportParamNetworkType() {
        return "&networkType=";
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getSignFromJni(Context context, String str, String str2, String str3, String str4, String str5) throws Exception {
        return BitmapkitUtils.getSignFromJni(JdSdk.getInstance().getApplication(), str, str2, str3, str4, str5);
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getSoftwareVersionName() {
        return PackageInfoUtil.getVersionName();
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getStringFromPreference(String str) {
        return ConfigUtil.getStringFromPreference(str);
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public String getUUID() {
        return StatisticsReportUtil.readDeviceUUID();
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public JSONObjectProxy handlerEncrypt(JSONObjectProxy jSONObjectProxy) throws UnsupportedEncodingException, JSONException {
        return SignatureAlertController.handlerEncrypt(jSONObjectProxy);
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public boolean isAllowNetworkConnection() {
        return !Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getBooleanFromPreference(Configuration.HAS_INIT_TIP, Boolean.FALSE).booleanValue() || HttpSettingTool.isNetPromptAgree;
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public boolean isUseOkhttp() {
        return ConfigUtil.getKeySwitchState("okhttpFlag");
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public void networkSettingsPreSignature() {
        NetworkSetting.networkSetting();
        BitmapkitUtils.loadBMP();
        if (BitmapkitUtils.isFuncAvailable()) {
            return;
        }
        SignatureAlertController.alertSignatureFailedDialog((Activity) BaseFrameUtil.getInstance().getCurrentMyActivity());
    }

    @Override // com.jingdong.jdsdk.depend.Idepend
    @Deprecated
    public void saveCookies(String str) {
        SafetyManager.saveCookies(str);
    }
}
