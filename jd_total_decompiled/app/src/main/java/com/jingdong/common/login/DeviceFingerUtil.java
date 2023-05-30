package com.jingdong.common.login;

import android.text.TextUtils;
import com.jd.sec.InitParams;
import com.jd.sec.LogoManager;
import com.jd.security.jdguard.b;
import com.jd.stat.bot.BotDetector;
import com.jd.stat.common.callback.JmaCallback;
import com.jd.stat.security.InfoCollectHelper;
import com.jd.stat.security.PrivacyHelper;
import com.jd.stat.security.SecurityInit;
import com.jd.stat.security.TrackBaseData;
import com.jd.stat.security.WSKeyHelper;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.entity.SceneStatus;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DeviceFingerUtil {
    private static final String TAG = "DeviceFingerUtil.init";

    public static void fpInit(boolean z) {
        InitParams build = new InitParams.InitParamsBuilder().acceptPrivacy(z).env(new LogoManager.IEnv() { // from class: com.jingdong.common.login.DeviceFingerUtil.1
            @Override // com.jd.sec.LogoManager.IEnv
            public String UserAgent() {
                return a.b(JdSdk.getInstance().getApplication().getApplicationContext());
            }

            @Override // com.jd.sec.LogoManager.IEnv
            public String env() {
                return b.c();
            }
        }).pin(LoginUserBase.getUserPin()).deviceUUID(StatisticsReportUtil.readDeviceUUID()).imei(StatisticsReportUtil.readDeviceId()).build();
        LogoManager.getInstance(JdSdk.getInstance().getApplication().getApplicationContext()).setDebugMode(false, Configuration.isBeta());
        LogoManager.getInstance(JdSdk.getInstance().getApplication().getApplicationContext()).initInBackground(LogoManager.ServerLocation.CHA, build);
    }

    public static String getUnionFingerPrint() {
        JSONObject jSONObject = new JSONObject();
        try {
            String logo = LogoManager.getInstance(JdSdk.getInstance().getApplication().getApplicationContext()).getLogo();
            String softFingerprint = JMA.getSoftFingerprint(JdSdk.getInstance().getApplication().getApplicationContext());
            if (OKLog.D) {
                OKLog.d("DeviceFingerUtil", "devicefinger = " + logo);
                OKLog.d("DeviceFingerUtil", "jmafinger = " + softFingerprint);
            }
            if (TextUtils.isEmpty(logo) && TextUtils.isEmpty(softFingerprint)) {
                return "";
            }
            jSONObject.put("devicefinger", logo);
            jSONObject.put("jmafinger", softFingerprint);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void init() {
        fpInit(JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication()));
        String property = Configuration.getProperty(Configuration.UNION_ID);
        String property2 = Configuration.getProperty(Configuration.PARTNER);
        String property3 = Configuration.getProperty(Configuration.SUB_UNION_ID);
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        String userPin = LoginUserBase.getUserPin();
        if (OKLog.I) {
            OKLog.i(TAG, "initJMATrack");
        }
        SecurityInit.init(JdSdk.getInstance().getApplication(), "100", new TrackBaseData.TrackBaseDataBuilder().partner(property2).deviceCode(readDeviceUUID).subunionId(property3).unionId(property).installtionid(StatisticsReportUtil.readInstallationId()).pin(userPin).enableLog(OKLog.I).debug(false).oaid(BaseInfo.getOAID()).useRemoteConfig(true).wsKeyHelper(new WSKeyHelper() { // from class: com.jingdong.common.login.DeviceFingerUtil.4
            @Override // com.jd.stat.security.WSKeyHelper
            public String getPin() {
                try {
                    String pin = UserUtil.getWJLoginHelper().getPin();
                    if (OKLog.D) {
                        OKLog.d(DeviceFingerUtil.TAG, "getPin:" + pin);
                    }
                    return pin;
                } catch (Throwable th) {
                    if (OKLog.D) {
                        OKLog.d(DeviceFingerUtil.TAG, "getPin", th);
                        return "";
                    }
                    return "";
                }
            }

            @Override // com.jd.stat.security.WSKeyHelper
            public String getWsKey() {
                try {
                    String a2 = UserUtil.getWJLoginHelper().getA2();
                    if (OKLog.D) {
                        OKLog.d(DeviceFingerUtil.TAG, "getWsKey:" + a2);
                    }
                    return a2;
                } catch (Throwable th) {
                    if (OKLog.D) {
                        OKLog.d(DeviceFingerUtil.TAG, "getWsKey", th);
                        return "";
                    }
                    return "";
                }
            }
        }).privacyHelper(new PrivacyHelper() { // from class: com.jingdong.common.login.DeviceFingerUtil.3
            @Override // com.jd.stat.security.PrivacyHelper
            public boolean isOpen() {
                return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication());
            }
        }).infoCollectHelper(new InfoCollectHelper() { // from class: com.jingdong.common.login.DeviceFingerUtil.2
            @Override // com.jd.stat.security.InfoCollectHelper
            public boolean canCollect(int i2, String str) {
                if (OKLog.D) {
                    OKLog.d(DeviceFingerUtil.TAG, "InfoCollectHelper call: code:" + i2 + " string:" + str);
                }
                if (i2 == 1) {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            return false;
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        SceneStatus hasLocationPermissionWithSceneV2 = PermissionHelper.hasLocationPermissionWithSceneV2(jSONObject.optString("sceneId", ""), jSONObject.optString("businessId", ""));
                        if (OKLog.D) {
                            OKLog.d(DeviceFingerUtil.TAG, "sceneStatus:" + hasLocationPermissionWithSceneV2);
                        }
                        return hasLocationPermissionWithSceneV2 == SceneStatus.HAS_ALL_PERMISSION;
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            OKLog.d(DeviceFingerUtil.TAG, th);
                        }
                    }
                } else if (i2 == -1 && OKLog.D) {
                    OKLog.d(DeviceFingerUtil.TAG, "new default scene " + str);
                }
                return false;
            }
        }).build(), new JmaCallback() { // from class: com.jingdong.common.login.DeviceFingerUtil.5
            @Override // com.jd.stat.common.callback.JmaCallback
            public void onCheckAppIdResult(String str) {
            }
        });
        BotDetector.init(JdSdk.getInstance().getApplication());
        FireEyeUtils.initFireEyeTrack();
    }
}
