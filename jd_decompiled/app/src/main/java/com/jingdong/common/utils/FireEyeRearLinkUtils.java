package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FireEyeRearLinkUtils {
    private static final String TAG = "FireEyeRearLinkUtils";
    private static BenefitInfo sBenefitInfo;
    private static final AtomicBoolean isLoading = new AtomicBoolean(false);
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class BenefitInfo {
        private final String channelId;
        private final boolean hideClose;
        private String imgUrl;
        private String loginParam;

        public BenefitInfo(String str, HttpResponse httpResponse, boolean z) {
            JDJSONObject jDJSONObject;
            this.imgUrl = "";
            this.channelId = str;
            this.hideClose = z;
            if (FireEyeRearLinkUtils.canShow(z) && (jDJSONObject = FireEyeRearLinkUtils.getJDJSONObject(httpResponse)) != null) {
                this.imgUrl = jDJSONObject.optString("imgUrl");
                this.loginParam = jDJSONObject.optString("loginParam");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean canShow(boolean z) {
        if (z) {
            return true;
        }
        return !JDElderModeUtils.isElderMode() && JDPrivacyManager.getInstance().isInAppAgree();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doBenefitJump(HttpResponse httpResponse) {
        JDJSONObject jDJSONObject = getJDJSONObject(httpResponse);
        if (jDJSONObject == null) {
            return;
        }
        String optString = jDJSONObject.optString(CartConstant.KEY_APPURL);
        if (TextUtils.isEmpty(optString)) {
            optString = jDJSONObject.optString("h5Url");
        }
        if (TextUtils.isEmpty(optString) || !JDPrivacyManager.getInstance().isInAppAgree()) {
            return;
        }
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity instanceof Context) {
            OpenAppJumpController.dispatchJumpRequestInApp((Context) currentMyActivity, Uri.parse(optString));
        }
    }

    private static HttpSetting getHttpSetting(String str, HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId(str);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setListener(onCommonListener);
        return httpSetting;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static JDJSONObject getJDJSONObject(HttpResponse httpResponse) {
        try {
            return httpResponse.getFastJsonObject().optJSONObject("data").optJSONArray("materialDisplayList").getJSONObject(0).optJSONObject("materialDetail");
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean isSplitScreen() {
        return UnAndroidUtils.mateXEasyClientNew(JdSdk.getInstance().getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void launchLogin(BenefitInfo benefitInfo) {
        if (LoginUserBase.hasLogin()) {
            return;
        }
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity instanceof Activity) {
            Bundle bundle = new Bundle();
            bundle.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
            sBenefitInfo = benefitInfo;
            if (!TextUtils.isEmpty(benefitInfo.imgUrl)) {
                bundle.putString(LoginConstans.RESOURCE_URL_KEY, benefitInfo.imgUrl);
            }
            if (benefitInfo.hideClose) {
                bundle.putString(LoginConstans.FOLD_FLAG, LoginConstans.FOLD_VALUE);
            }
            postLaunchLogin(benefitInfo.channelId, !TextUtils.isEmpty(benefitInfo.imgUrl) ? "1" : "2", UserUtil.getWJLoginHelper().isEnterLogined() ? "0" : "1");
            DeepLinkLoginHelper.startLoginActivity((Context) currentMyActivity, bundle, new ILogin() { // from class: com.jingdong.common.utils.FireEyeRearLinkUtils.3
                @Override // com.jingdong.common.login.ILogin
                public void onSuccess(String str) {
                    if (TextUtils.equals(FireEyeRearLinkUtils.TAG, str)) {
                        if (FireEyeRearLinkUtils.sBenefitInfo != null) {
                            FireEyeRearLinkUtils.postLoginSuccess(FireEyeRearLinkUtils.sBenefitInfo.channelId, !TextUtils.isEmpty(FireEyeRearLinkUtils.sBenefitInfo.imgUrl) ? "1" : "2");
                        }
                        FireEyeRearLinkUtils.provideBenefit();
                        return;
                    }
                    BenefitInfo unused = FireEyeRearLinkUtils.sBenefitInfo = null;
                }
            }, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void launchLoginSafe(final BenefitInfo benefitInfo) {
        sHandler.post(new Runnable() { // from class: com.jingdong.common.utils.FireEyeRearLinkUtils.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FireEyeRearLinkUtils.launchLogin(BenefitInfo.this);
                } catch (Exception e2) {
                    FireEyeRearLinkUtils.launchNormalLogin();
                    e2.printStackTrace();
                }
                FireEyeRearLinkUtils.isLoading.set(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void launchNormalLogin() {
        try {
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (currentMyActivity instanceof Activity) {
                DeepLinkLoginHelper.startLoginActivity((Context) currentMyActivity, null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void postCallSdk(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channel", str);
            jSONObject.put("type", str2);
            jSONObject.put("status", str3);
            sendExpo("MRightsGuideLoginToast_ActivationCallSdk", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void postLaunchLogin(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channel", str);
            jSONObject.put("isprize", str2);
            jSONObject.put("is_show", str3);
            sendExpo("MRightsGuideLoginToast_NativeRightsShow", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void postLoginSuccess(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channel", str);
            jSONObject.put("isprize", str2);
            sendExpo("MRightsGuideLoginToast_LoginSuccess", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void provideBenefit() {
        BenefitInfo benefitInfo = sBenefitInfo;
        if (benefitInfo == null || TextUtils.isEmpty(benefitInfo.loginParam) || !JDPrivacyManager.getInstance().isInAppAgree()) {
            return;
        }
        postCallSdk(sBenefitInfo.channelId, "2", "1");
        final String str = sBenefitInfo.channelId;
        HttpSetting httpSetting = getHttpSetting("normandyLogin", new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.FireEyeRearLinkUtils.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (!TextUtils.isEmpty(str)) {
                    FireEyeRearLinkUtils.postCallSdk(str, "2", "2");
                }
                try {
                    FireEyeRearLinkUtils.doBenefitJump(httpResponse);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        httpSetting.putJsonParam("loginParam", sBenefitInfo.loginParam);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void requestBenefit() {
        requestBenefit(TextUtils.equals(Configuration.getPortalHost(), "api.m.jd.care") ? "11413" : "10235", false);
    }

    private static void sendExpo(String str, String str2) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "MRightsGuideLoginToast_Main", "", "", str2, null);
    }

    public static void requestBenefit(final String str, final boolean z) {
        AtomicBoolean atomicBoolean = isLoading;
        if (atomicBoolean.get()) {
            return;
        }
        atomicBoolean.set(true);
        if (LoginUserBase.hasLogin()) {
            return;
        }
        if (!canShow(z)) {
            launchLoginSafe(new BenefitInfo(str, null, false));
            return;
        }
        postCallSdk(str, "1", "1");
        HttpSetting httpSetting = getHttpSetting("normandyLogin", new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.FireEyeRearLinkUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                FireEyeRearLinkUtils.postCallSdk(str, "1", "2");
                FireEyeRearLinkUtils.launchLoginSafe(new BenefitInfo(str, httpResponse, z));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                FireEyeRearLinkUtils.launchLoginSafe(new BenefitInfo(str, null, z));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        httpSetting.putJsonParam("channelId", str);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
