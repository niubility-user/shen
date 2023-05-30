package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.utils.JDAvSdkUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes6.dex */
public class RtcSdkConfig {
    private static final String JD_VIDEOGUIDE_APP_ID = "jd.videoguide";
    private static final String JD_VIDEOGUIDE_INSTANCE = "instance.jd.videoguide";
    private static final String KEY_CURDATE_PREFIX = "rtcsdk_config_curdate";
    private static final String KEY_SUBSCRIBED_PREFIX = "rtcsdk_config_subscribed";
    private static final String KEY_WORKTIME_PREFIX = "rtcsdk_config_worktime";
    private static final String TAG = "RtcSdkConfig";
    private static RtcSdkConfig mInstance;

    private RtcSdkConfig() {
    }

    public static RtcSdkConfig getInstance() {
        if (mInstance == null) {
            synchronized (RtcSdkConfig.class) {
                if (mInstance == null) {
                    mInstance = new RtcSdkConfig();
                }
            }
        }
        return mInstance;
    }

    private String getUid() {
        if (TextUtils.isEmpty(LoginUserHelper.getInstance().getLoginUser().getLoginUserName())) {
            return null;
        }
        try {
            return Md5Encrypt.md5(LoginUserHelper.getInstance().getLoginUser().getLoginUserName() + "rtcsdk_config_uid");
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConfig(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        String optString = jSONObjectProxy.optString("subscribed");
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String optString2 = jSONObjectProxy.optString("workTime");
        String uid = getUid();
        if (!TextUtils.isEmpty(uid)) {
            try {
                SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                edit.putString(KEY_SUBSCRIBED_PREFIX + uid, optString);
                edit.putString(KEY_CURDATE_PREFIX + uid, format);
                edit.putString(KEY_WORKTIME_PREFIX + uid, optString2);
                edit.apply();
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        int matchTime = matchTime(format, optString2);
        if (matchTime != -1 && DYConstants.DY_TRUE.equals(optString) && matchTime == 1) {
            initSdk();
        }
    }

    private synchronized void initSdk() {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName("JDRtcSdkModule").setMethodName("initVideoInstance").putStringParam("instanceId", JD_VIDEOGUIDE_INSTANCE).putStringParam("user", UserUtil.getWJLoginHelper().getPin()).putStringParam("appid", JD_VIDEOGUIDE_APP_ID);
            if (Configuration.isBeta()) {
                jDRouterUrlBuilder.putStringParam("token", "webtoken").putStringParam("ServerAddr", "ap8-mt.jd.com").putStringParam("ServerPort", "2000");
            }
            JDRouter.build(JdSdk.getInstance().getApplication(), jDRouterUrlBuilder.build()).callBackListener(new CallBackWithReturnListener() { // from class: com.jingdong.common.utils.RtcSdkConfig.1
                @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
                public void onComplete(Object obj) {
                    if (OKLog.D) {
                        OKLog.d(RtcSdkConfig.TAG, "testinitVideoInstance onComplete(): >>><<<:");
                    }
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    if (OKLog.D) {
                        OKLog.d(RtcSdkConfig.TAG, "testinitVideoInstance onError(): >>><<<:");
                    }
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                    if (OKLog.D) {
                        OKLog.d(RtcSdkConfig.TAG, "testinitVideoInstance onComplete(): >>><<<:");
                    }
                }
            }).open();
        }
    }

    private boolean isConfigOpen() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "ddRegister", JshopConst.JSKEY_CATE_OPEN);
            if (TextUtils.isEmpty(config)) {
                return false;
            }
            return TextUtils.equals("1", config);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    private int matchTime(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            Date date = new Date(System.currentTimeMillis());
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            calendar.setTime(date);
            calendar2.setTime(parse);
            boolean z = false;
            if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1)) {
                if (calendar.get(6) == calendar2.get(6)) {
                    z = true;
                }
            }
            return z ? 1 : -1;
        } catch (Throwable th) {
            if (OKLog.D) {
                th.printStackTrace();
                return -1;
            }
            return -1;
        }
    }

    private void request() {
        OKLog.d(TAG, "request");
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("ddGuideApi");
        httpSetting.setHost(Configuration.isBeta() ? "beta-api.m.jd.com" : "api.m.jd.com");
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("m", "has_video_subscribe");
        httpSetting.putJsonParam("okCode", 0);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.utils.RtcSdkConfig.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObjectOrNull;
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject != null) {
                    if (OKLog.V) {
                        OKLog.v(RtcSdkConfig.TAG, jSONObject.toString());
                    }
                    if (!"0".equals(jSONObject.optString("code")) || (jSONObjectOrNull = jSONObject.getJSONObjectOrNull("body")) == null) {
                        return;
                    }
                    synchronized (RtcSdkConfig.class) {
                        RtcSdkConfig.this.handleConfig(jSONObjectOrNull);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.V) {
                    OKLog.v(RtcSdkConfig.TAG, "onError = " + httpError.toString());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private synchronized void unInitSdk() {
        JDAvSdkUtil.JDRtcUtils.unregisterByInstance(JD_VIDEOGUIDE_INSTANCE);
    }

    public void init() {
        try {
            if (ProcessUtil.isMainProcess() && isConfigOpen()) {
                String uid = getUid();
                if (TextUtils.isEmpty(uid)) {
                    return;
                }
                OKLog.d(TAG, XView2Constants.XVIEW2_ACTION_INIT);
                String stringFromPreference = CommonBase.getStringFromPreference(KEY_SUBSCRIBED_PREFIX + uid, "");
                String stringFromPreference2 = CommonBase.getStringFromPreference(KEY_CURDATE_PREFIX + uid, "");
                String stringFromPreference3 = CommonBase.getStringFromPreference(KEY_WORKTIME_PREFIX + uid, "");
                if (LoginUserHelper.getInstance().getLoginUser().hasLogin()) {
                    int matchTime = matchTime(stringFromPreference2, stringFromPreference3);
                    if (matchTime != -1) {
                        if (DYConstants.DY_TRUE.equals(stringFromPreference) && matchTime == 1) {
                            initSdk();
                        }
                    } else {
                        request();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void unInit() {
        try {
            if (ProcessUtil.isMainProcess() && isConfigOpen()) {
                OKLog.d(TAG, "unInit");
                unInitSdk();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void update() {
        try {
            if (ProcessUtil.isMainProcess() && isConfigOpen()) {
                OKLog.d(TAG, "update");
                init();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
