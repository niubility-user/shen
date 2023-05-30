package com.jdjr.risk.identity.verify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.bean.DeviceInfo;
import com.jd.aips.common.utils.BiometricTokenUtil;
import com.jd.aips.common.utils.DeviceInfoUtil;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.common.utils.SecurityChannelUtils;
import com.jd.aips.common.utils.ThreadUtils;
import com.jd.aips.verify.BaseEngineLauncher;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.VerifyEngine;
import com.jd.aips.verify.VerifyParams;
import com.jd.aips.verify.VerifyResult;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.tracker.TrackerCallback;
import com.jdjr.risk.identity.verify.activity.LauncherActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class IdentityVerityEngine implements VerifyEngine {
    public static final String ID_CARD_NFC_VERIFY_ENGINE = "com.jd.aips.verify.idcardnfc.IdCardNfcVerifyEngine";
    public static final String ID_CARD_VERIFY_ENGINE = "com.jd.aips.verify.idcard.IdCardVerifyEngine";
    public static final String SDK_NAME = "verification_sdk";
    public static final String SDK_VERSION = "3.1.00";

    /* renamed from: f  reason: collision with root package name */
    private static volatile IdentityVerityEngine f7455f;
    private final Handler a;
    private final SdkEngineLauncher b;

    /* renamed from: c  reason: collision with root package name */
    private volatile Context f7456c;
    private volatile IdentityVerifySession d;

    /* renamed from: e  reason: collision with root package name */
    private volatile DeviceInfo f7457e;

    private IdentityVerityEngine() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.a = handler;
        this.b = new SdkEngineLauncher(handler, new BaseEngineLauncher.LauncherCallback() { // from class: com.jdjr.risk.identity.verify.IdentityVerityEngine.1
            @Override // com.jd.aips.verify.BaseEngineLauncher.LauncherCallback
            public void onFailure() {
                IdentityVerityEngine.this.callbackFinishSDK(1, "launch sdk failed");
            }
        });
    }

    private void a(String str) {
        Bundle a = a();
        if (!TextUtils.isEmpty(str)) {
            a.putString(VerifyParams.ID_CARD_TOKEN, str);
        }
        this.b.toFaceVerify(this.f7456c, a, new VerifyCallback() { // from class: com.jdjr.risk.identity.verify.IdentityVerityEngine.2
            @Override // com.jd.aips.verify.VerifyCallback
            public void onResult(int i2, String str2, String str3, Bundle bundle) {
                IdentityVerityEngine.this.callbackFinishSDK(i2, str2, bundle);
            }
        }, this.d.trackerCallback);
    }

    private void b() {
        Bundle a = a();
        if (((IdentityVerifyConfig) ((IdentityVerifyParams) this.d.verifyParams).verifyConfig).verificationSdk.config.sdk_idcard_nfc_flag) {
            this.b.toIdCardNfcVerify(this.f7456c, a, new VerifyCallback() { // from class: com.jdjr.risk.identity.verify.IdentityVerityEngine.3
                @Override // com.jd.aips.verify.VerifyCallback
                public void onResult(int i2, String str, String str2, Bundle bundle) {
                    IdentityVerityEngine.a(IdentityVerityEngine.this, i2, str, str2, bundle);
                }
            }, this.d.trackerCallback);
            return;
        }
        a.putBoolean(VerifyParams.IS_SHOW_GUIDE_PAGE, false);
        a.putBoolean(VerifyParams.IS_SHOW_RESULT_PAGE, false);
        this.b.toIdCardVerify(this.f7456c, a, new VerifyCallback() { // from class: com.jdjr.risk.identity.verify.IdentityVerityEngine.4
            @Override // com.jd.aips.verify.VerifyCallback
            public void onResult(int i2, String str, String str2, Bundle bundle) {
                IdentityVerityEngine.a(IdentityVerityEngine.this, i2, str, str2, bundle);
            }
        }, this.d.trackerCallback);
    }

    @NonNull
    public static IdentityVerityEngine getInstance() {
        if (f7455f == null) {
            synchronized (IdentityVerityEngine.class) {
                if (f7455f == null) {
                    f7455f = new IdentityVerityEngine();
                }
            }
        }
        return f7455f;
    }

    public synchronized void callbackFinishSDK(int i2, String str) {
        callbackFinishSDK(i2, str, null);
    }

    public synchronized void checkIdentityVerity(@NonNull Context context, @Nullable Bundle bundle, @NonNull String str, @NonNull IdentityVerityCallback identityVerityCallback) {
        launch(context, str, bundle, identityVerityCallback, null);
    }

    public Context getApplicationContext() {
        return this.f7456c;
    }

    public IdentityVerifySession getSession() {
        return this.d;
    }

    public synchronized void launch(@NonNull Context context, @NonNull String str, @Nullable Bundle bundle, @NonNull IdentityVerityCallback identityVerityCallback, @Nullable TrackerCallback trackerCallback) {
        IdentityVerifyRequest identityVerifyRequest;
        IdentityVerifyParams identityVerifyParams;
        if (context == null) {
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1acontext \u4e3a\u7a7a\uff01");
        }
        if (identityVerityCallback != null) {
            if (TextUtils.isEmpty(str)) {
                synchronized (this) {
                    a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aidentityVerifyParamJson \u4e3a\u7a7a\uff01", null);
                }
                return;
            } else if (this.d != null) {
                synchronized (this) {
                    a(identityVerityCallback, 1, "\u91cd\u590d\u8c03\u7528\uff01", null);
                }
                return;
            } else {
                this.f7456c = context.getApplicationContext();
                try {
                    if (this.f7457e == null) {
                        this.f7457e = DeviceInfoUtil.buildDeviceInfo(this.f7456c);
                    }
                    identityVerifyRequest = (IdentityVerifyRequest) FsGsonUtil.fromJson(str, IdentityVerifyRequest.class);
                } catch (Exception unused) {
                    synchronized (this) {
                        a(identityVerityCallback, 1, "\u4eba\u8bc1\u6838\u9a8c Sdk \u542f\u52a8\u5931\u8d25", null);
                    }
                }
                if (identityVerifyRequest != null && (identityVerifyParams = identityVerifyRequest.identityVerifyParams) != null) {
                    if (TextUtils.isEmpty(identityVerifyParams.businessId)) {
                        synchronized (this) {
                            a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1abusinessId \u4e3a\u7a7a\uff01", null);
                        }
                        return;
                    } else if (TextUtils.isEmpty(identityVerifyRequest.identityVerifyParams.appName)) {
                        synchronized (this) {
                            a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aappName \u4e3a\u7a7a\uff01", null);
                        }
                        return;
                    } else if (TextUtils.isEmpty(identityVerifyRequest.identityVerifyParams.appAuthorityKey)) {
                        synchronized (this) {
                            a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aappAuthorityKey \u4e3a\u7a7a\uff01", null);
                        }
                        return;
                    } else if (TextUtils.isEmpty(identityVerifyRequest.identityVerifyParams.verifyToken)) {
                        synchronized (this) {
                            a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1averifyToken \u4e3a\u7a7a\uff01", null);
                        }
                        return;
                    } else {
                        HashMap<String, Object> extension = identityVerifyRequest.identityVerifyParams.getExtension();
                        if (extension != null && extension.containsValue(VerifyParams.CONFIG_REQUEST_TIMEOUT)) {
                            int i2 = 0;
                            try {
                                i2 = Integer.parseInt((String) extension.get(VerifyParams.CONFIG_REQUEST_TIMEOUT));
                            } catch (Exception unused2) {
                            }
                            if (i2 <= 0 || i2 > 60) {
                                synchronized (this) {
                                    a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aconfigRequestTimeout \u65f6\u95f4\u4e0d\u5408\u89c4\uff01", null);
                                }
                                return;
                            }
                        }
                        SecurityChannelUtils.startSecurityChannel(this.f7456c);
                        String buildBiometricToken = BiometricTokenUtil.buildBiometricToken(context, identityVerifyRequest.identityVerifyParams.userId);
                        IdentityVerifyParams identityVerifyParams2 = identityVerifyRequest.identityVerifyParams;
                        identityVerifyParams2.sdkToken = buildBiometricToken;
                        identityVerifyParams2.verifySdkName = SDK_NAME;
                        identityVerifyParams2.verifySdkVersion = "3.1.00";
                        identityVerifyParams2.setDeviceInfo(this.f7457e);
                        this.d = new IdentityVerifySession(this.f7456c, identityVerifyRequest.identityVerifyParams, identityVerityCallback, trackerCallback);
                        ((IdentityVerifyTracker) this.d.verifyTracker).trackEnter();
                        Intent intent = new Intent(this.f7456c, LauncherActivity.class);
                        intent.setFlags(335544320);
                        this.f7456c.startActivity(intent);
                        return;
                    }
                }
                synchronized (this) {
                    a(identityVerityCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u53c2\u6570\u4e3a\u7a7a\uff01", null);
                }
                return;
            }
        }
        throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1averifyCallback \u4e3a\u7a7a\uff01");
    }

    public synchronized void launchVerifySdks() {
        if (this.d != null) {
            if (this.d.verifyParams != 0 && ((IdentityVerifyParams) this.d.verifyParams).verifyConfig != 0) {
                VerificationSdk.Config config = ((IdentityVerifyConfig) ((IdentityVerifyParams) this.d.verifyParams).verifyConfig).verificationSdk.config;
                int i2 = config.sdk_verification_strategy;
                if (i2 == 1) {
                    a(null);
                } else if (i2 == 2) {
                    b();
                } else if (i2 == 3) {
                    b();
                } else {
                    String str = "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u65e0\u6548\u7684 sdk_verification_strategy = %d" + config.sdk_verification_strategy;
                    callbackFinishSDK(5, String.format("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u65e0\u6548\u7684 sdk_verification_strategy = %d", Integer.valueOf(config.sdk_verification_strategy)));
                }
            } else {
                callbackFinishSDK(5, String.format("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1a\u65e0\u6548\u7684 verify config", new Object[0]));
            }
        }
    }

    public synchronized void release() {
        if (this.f7456c != null) {
            this.f7456c = null;
        }
        if (this.d != null) {
            this.d.destroy();
            this.d = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void setConfig(@NonNull IdentityVerifyConfig identityVerifyConfig) {
        if (this.d != null && this.d.verifyParams != 0) {
            ((IdentityVerifyParams) this.d.verifyParams).verifyConfig = identityVerifyConfig;
            this.d.a = FsGsonUtil.toJson(identityVerifyConfig);
            Map<String, String> map = identityVerifyConfig.extra;
            if (map != null) {
                ((IdentityVerifyParams) this.d.verifyParams).userId = map.get("userId");
                ((IdentityVerifyTracker) this.d.verifyTracker).refreshSdkInfo();
            }
            this.d.totalCount = identityVerifyConfig.verificationSdk.config.sdk_verification_retry_count;
            int i2 = this.d.totalCount;
            if (this.d.totalCount <= 0) {
                int i3 = this.d.totalCount;
                this.d.totalCount = 3;
            }
        }
    }

    public synchronized void callbackFinishSDK(int i2, String str, Bundle bundle) {
        if (this.d != null && this.d.verifyCallback != 0) {
            a((IdentityVerityCallback) this.d.verifyCallback, i2, str, bundle);
        }
        release();
    }

    static void a(IdentityVerityEngine identityVerityEngine, int i2, String str, String str2, Bundle bundle) {
        if (i2 == 0) {
            int i3 = ((IdentityVerifyConfig) ((IdentityVerifyParams) identityVerityEngine.d.verifyParams).verifyConfig).verificationSdk.config.sdk_verification_strategy;
            if (i3 == 2) {
                identityVerityEngine.callbackFinishSDK(i2, str);
                return;
            } else if (i3 == 3) {
                identityVerityEngine.d.count = bundle.getInt("retry_count");
                if (identityVerityEngine.d.validateRetry()) {
                    identityVerityEngine.a(str2);
                    return;
                } else {
                    identityVerityEngine.callbackFinishSDK(1, VerifyResult.MSG_NO_RETRY_COUNT);
                    return;
                }
            } else {
                return;
            }
        }
        identityVerityEngine.callbackFinishSDK(i2, str);
    }

    @NonNull
    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("businessId", ((IdentityVerifyParams) this.d.verifyParams).businessId);
        bundle.putString("appName", ((IdentityVerifyParams) this.d.verifyParams).appName);
        bundle.putString("appAuthorityKey", ((IdentityVerifyParams) this.d.verifyParams).appAuthorityKey);
        bundle.putString("verifyToken", ((IdentityVerifyParams) this.d.verifyParams).verifyToken);
        bundle.putString("userId", ((IdentityVerifyParams) this.d.verifyParams).userId);
        bundle.putString("sdkToken", ((IdentityVerifyParams) this.d.verifyParams).sdkToken);
        bundle.putString(VerifyParams.CONFIG_JSON, this.d.a);
        bundle.putBoolean(VerifyParams.SECURITY_CHANNEL_READY, true);
        return bundle;
    }

    private void a(final IdentityVerityCallback identityVerityCallback, final int i2, final String str, Bundle bundle) {
        final String str2 = (this.d == null || this.d.verifyParams == 0) ? "" : ((IdentityVerifyParams) this.d.verifyParams).verifyToken;
        if (this.d != null) {
            ((IdentityVerifyTracker) this.d.verifyTracker).trackComplete(i2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", VerifyEngine.JDJR_WEB_JS_TYPE);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", "" + i2);
            jSONObject2.put("msg", str);
            jSONObject2.put("token", str2);
            jSONObject2.put("extendMap", new JSONObject());
            if (bundle != null) {
                jSONObject2.put("serverCode", bundle.getString("serverCode", ""));
                jSONObject2.put("faceImgBase64", bundle.getString("faceImgBase64", ""));
                ArrayList<String> stringArrayList = bundle.getStringArrayList("faceImgList");
                if (stringArrayList != null) {
                    jSONObject2.put("faceImgList", new JSONArray((Collection) stringArrayList));
                }
                Serializable serializable = bundle.getSerializable("faceFrame");
                if (serializable != null) {
                    jSONObject2.put("faceFrame", new JSONObject(FsGsonUtil.toJson(serializable)));
                }
            } else {
                jSONObject2.put("serverCode", "");
                jSONObject2.put("faceImgBase64", "");
            }
            jSONObject.put("IdentityCallBackResult", jSONObject2);
        } catch (Exception unused) {
        }
        final String jSONObject3 = jSONObject.toString();
        try {
            if (ThreadUtils.isMainThread()) {
                identityVerityCallback.onVerifyResult(i2, str, str2, Bundle.EMPTY, jSONObject3);
            } else {
                this.a.post(new Runnable(this) { // from class: com.jdjr.risk.identity.verify.IdentityVerityEngine.5
                    @Override // java.lang.Runnable
                    public void run() {
                        identityVerityCallback.onVerifyResult(i2, str, str2, Bundle.EMPTY, jSONObject3);
                    }
                });
            }
        } catch (Throwable unused2) {
        }
    }
}
