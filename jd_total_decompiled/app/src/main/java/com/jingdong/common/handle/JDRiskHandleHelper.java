package com.jingdong.common.handle;

import android.content.Context;
import com.jd.sec.LogoManager;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleJumpHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleLoginHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleMtaHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleVerifyHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.DeviceFingerUtil;
import com.jingdong.common.login.LoginLocationUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.widget.custom.discovery.MyFrameUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import java.util.HashMap;
import jd.wjlogin_sdk.common.WJLoginHelper;

/* loaded from: classes5.dex */
public class JDRiskHandleHelper {
    public static void init() {
        JDRiskHandleManager.getInstance().init(JdSdk.getInstance().getApplicationContext(), new JDRiskHandleInfoHelper() { // from class: com.jingdong.common.handle.JDRiskHandleHelper.5
            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper
            public String getEid() {
                return LogoManager.getInstance(JdSdk.getInstance().getApplicationContext()).getLogo();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper
            public String getUnionwsws() {
                return DeviceFingerUtil.getUnionFingerPrint();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper
            public String getUuid() {
                return StatisticsReportUtil.readDeviceUUID();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper
            public WJLoginHelper getWJLoginHelper() {
                return UserUtil.getWJLoginHelper();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper
            public boolean isAppForeground() {
                return BaseInfo.isAppForeground();
            }
        }).setLoginHelper(new JDRiskHandleLoginHelper() { // from class: com.jingdong.common.handle.JDRiskHandleHelper.4
            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleLoginHelper
            public void exitLogin(Context context) {
                UserUtil.onLogout(MyFrameUtil.getInstance().getCurrentMyActivity());
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleLoginHelper
            public void jumpToLogin(Context context, String str) {
                DeepLinkLoginHelper.startLoginActivity(context, null);
            }
        }).setVerifyHelper(new JDRiskHandleVerifyHelper() { // from class: com.jingdong.common.handle.JDRiskHandleHelper.3
            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleVerifyHelper
            public double getLat() {
                return LoginLocationUtil.getCacheLocation().getLat();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleVerifyHelper
            public double getLng() {
                return LoginLocationUtil.getCacheLocation().getLng();
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleVerifyHelper
            public String getUemp() {
                return !JDElderModeUtils.isElderMode() ? "0" : "1";
            }
        }).setMtaHelper(new JDRiskHandleMtaHelper() { // from class: com.jingdong.common.handle.JDRiskHandleHelper.2
            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleMtaHelper
            public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
                JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, str8, "", "", "", "", hashMap);
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleMtaHelper
            public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
                JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, "", "", "", hashMap);
            }

            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleMtaHelper
            public void sendPVExtend(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
                JDMtaUtils.sendPVExtend(context, obj, str, str2, str3, str4, str5, str6, hashMap, hashMap2);
            }
        }).setJumpHelper(new JDRiskHandleJumpHelper() { // from class: com.jingdong.common.handle.JDRiskHandleHelper.1
            @Override // com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleJumpHelper
            public void jumpToFeedBack(Context context, String str) {
                try {
                    JumpHelper.jump(context, "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"feedback\",\"from\":\"riskhandle\"}", 2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).setDebugHost(isDebugHost()).setDebugLog(OKLog.D);
    }

    private static boolean isDebugHost() {
        return !"api.m.jd.com".equals(Configuration.getNgwHost());
    }
}
