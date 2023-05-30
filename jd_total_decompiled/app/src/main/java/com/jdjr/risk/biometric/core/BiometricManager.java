package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdjr.risk.util.a.f;
import com.jdjr.risk.util.constant.RiskType;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class BiometricManager {

    /* renamed from: c */
    private static BiometricManager f7290c = new BiometricManager();
    private ExecutorService b = null;
    private e a = new e();

    /* loaded from: classes18.dex */
    public class a implements LorasHttpCallback {
        private int b;

        /* renamed from: c */
        private JSCallback f7291c;

        public a(int i2, JSCallback jSCallback) {
            BiometricManager.this = r1;
            this.b = i2;
            this.f7291c = jSCallback;
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInCurentThread(int i2, String str) {
            this.f7291c.onFinish(this.b, com.jdjr.risk.biometric.b.a.a(this.b, 0, "", ""));
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInNetThread(int i2, String str) {
            this.f7291c.onFinish(this.b, com.jdjr.risk.biometric.b.a.a(this.b, 0, "", ""));
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onSuccess(String str) {
            JSONObject jSONObject = new JSONObject();
            int i2 = this.b;
            if (i2 == 1) {
                jSONObject = com.jdjr.risk.biometric.b.a.a(i2, 1, str, "");
            }
            int i3 = this.b;
            if (i3 == 4) {
                jSONObject = com.jdjr.risk.biometric.b.a.a(i3, 1, "", str);
            }
            this.f7291c.onFinish(this.b, jSONObject);
        }
    }

    private BiometricManager() {
        c();
    }

    private void c() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new f(), new ThreadPoolExecutor.DiscardPolicy());
        this.b = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static String getAndroidID(Context context) {
        return BaseInfoService.getAndroidID(context);
    }

    public static String getCommonID(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str)) ? "" : BaseInfoService.getCommonID(context);
    }

    public static String getDeviceID(Context context) {
        return context != null ? BaseInfoService.getInstance(context).getDeviceID(context) : "";
    }

    public static BiometricManager getInstance() {
        return f7290c;
    }

    public static BiometricManager getInstance(String str) {
        com.jdjr.risk.util.httputil.a.a = str;
        return f7290c;
    }

    public static BiometricManager getInstance(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            com.jdjr.risk.util.httputil.a.a = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            com.jdjr.risk.util.b.e.a = str2;
        }
        return f7290c;
    }

    public static void getOAID(Context context, LorasHttpCallback lorasHttpCallback) {
        if (context != null) {
            BaseInfoService.getOAID(context, lorasHttpCallback);
        } else {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        }
    }

    public static boolean setDeviceID(Context context, int i2, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return BaseInfoService.setDeviceID(context, i2, str);
    }

    public e a() {
        return this.a;
    }

    public String a(Context context) {
        return com.jdjr.risk.biometric.core.a.a(context).b(context);
    }

    public ExecutorService b() {
        return this.b;
    }

    public void biometricForJS(Context context, String str, String str2, JSCallback jSCallback) {
        int i2;
        JSONObject jSONObject;
        JSONObject a2;
        try {
            jSONObject = new JSONObject(str);
            i2 = jSONObject.has("operation") ? jSONObject.getInt("operation") : -1;
        } catch (Exception unused) {
            i2 = -1;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("biometricData");
            String string = jSONObject2.has("bizId") ? jSONObject2.getString("bizId") : "oldSDKSignin";
            if (jSONObject2.has("interval")) {
                jSONObject2.getString("interval");
            }
            if (jSONObject2.has("duraTime")) {
                jSONObject2.getString("duraTime");
            }
            if (i2 == 0) {
                startBiometricAndSensor(context, string, str2);
                a2 = com.jdjr.risk.biometric.b.a.a(i2, 1, "", "");
            } else if (i2 == 1) {
                getToken(context, string, str2, new a(i2, jSCallback));
                return;
            } else if (i2 == 2) {
                startBiometric(context, string, str2);
                a2 = com.jdjr.risk.biometric.b.a.a(i2, 1, "", "");
            } else if (i2 == 3 || i2 == 4) {
                getRiskData(context, string, new a(i2, jSCallback));
                return;
            } else if (i2 != 5) {
                return;
            } else {
                a2 = com.jdjr.risk.biometric.b.a.a(i2, 1, getCacheTokenByBizId(context, string, str2), "");
            }
            jSCallback.onFinish(i2, a2);
        } catch (Exception unused2) {
            if (i2 != -1) {
                jSCallback.onFinish(i2, com.jdjr.risk.biometric.b.a.a(i2, 0, "", ""));
            }
        }
    }

    public String getCacheTokenByBizId(Context context, String str, String str2) {
        return (context == null || Build.VERSION.SDK_INT < 16) ? "" : com.jdjr.risk.biometric.core.a.a(context).b(context, str, str2);
    }

    public void getRiskData(Context context, String str, RiskType riskType, String str2, LorasHttpCallback lorasHttpCallback) {
        if (context != null) {
            try {
                if ("com.jd.jrapp".equals(context.getPackageName()) || "com.jdjr.risk.biometric.demo".equals(context.getPackageName())) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("agreedPrivacy", BaseInfo.isAgreedPrivacy());
                    bundle.putBoolean("tokenExist", this.a.d(context));
                    e.a.set(bundle);
                    com.jdjr.risk.biometric.core.a.a(context);
                    if (BaseInfo.isAgreedPrivacy()) {
                        c.a(context, str, riskType, str2, lorasHttpCallback);
                    } else {
                        lorasHttpCallback.onSuccess("0");
                    }
                }
            } catch (Throwable unused) {
                lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
            }
        }
    }

    public void getRiskData(Context context, String str, LorasHttpCallback lorasHttpCallback) {
        int i2;
        String str2;
        if (context == null) {
            i2 = 902;
            str2 = HttpInfoConstants.FAIL_ERROR_PARAM_STR;
        } else if (Build.VERSION.SDK_INT >= 16) {
            c.a(context, str, lorasHttpCallback);
            return;
        } else {
            i2 = 904;
            str2 = HttpInfoConstants.FAIL_SDKVERSION_EXCEPTION_STR;
        }
        lorasHttpCallback.onFailInCurentThread(i2, str2);
    }

    public void getToken(Context context, String str, String str2, LorasHttpCallback lorasHttpCallback) {
        if (context == null || lorasHttpCallback == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        com.jdjr.risk.biometric.core.a.a(context).a(context, str, str2, lorasHttpCallback);
    }

    public void startBiometric(Context context, String str, String str2) {
        if (context == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        com.jdjr.risk.biometric.core.a.a(context).a(context, str, str2);
    }

    public void startBiometricAndSensor(Context context, String str, String str2) {
        if (context == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        com.jdjr.risk.biometric.core.a.a(context).a(context, str, str2);
    }
}
