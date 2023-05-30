package com.jingdong.app.mall.h.c;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.hybrid.downloader.j;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.utils.PackageInfoUtil;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class b {

    /* renamed from: f  reason: collision with root package name */
    private static volatile b f8448f;

    /* renamed from: g  reason: collision with root package name */
    private static final Application.ActivityLifecycleCallbacks f8449g = new a();
    private boolean a = false;
    private Application b;

    /* renamed from: c  reason: collision with root package name */
    private String f8450c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private com.jingdong.app.mall.h.c.a f8451e;

    /* loaded from: classes3.dex */
    class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
            if (com.jingdong.app.mall.h.c.c.a > 0) {
                b.e().j(com.jingdong.app.mall.h.c.c.a + "");
                com.jingdong.app.mall.h.c.c.a = 0L;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
            if (b.e().a) {
                return;
            }
            b.e().a = true;
            b.e().i();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.h.c.b$b  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class C0267b implements HttpGroup.OnCommonListener {
        C0267b() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (TextUtils.equals(fastJsonObject.optString("code"), "0")) {
                b.this.g(fastJsonObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            b.this.a = false;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes3.dex */
    public static final class c {
        private String a;
        private Application b;

        /* renamed from: c  reason: collision with root package name */
        private com.jingdong.app.mall.h.c.a f8453c;

        public Application a() {
            return this.b;
        }

        public String b() {
            return this.a;
        }

        public com.jingdong.app.mall.h.c.a c() {
            return this.f8453c;
        }

        public c d(Application application) {
            this.b = application;
            return this;
        }

        public c e(String str) {
            this.a = str;
            return this;
        }

        public c f(com.jingdong.app.mall.h.c.a aVar) {
            this.f8453c = aVar;
            return this;
        }
    }

    public static b e() {
        if (f8448f == null) {
            synchronized (b.class) {
                if (f8448f == null) {
                    f8448f = new b();
                }
            }
        }
        return f8448f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        if (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray("data")) == null || optJSONArray.isEmpty()) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("nameSpace", optJSONObject.optString("nameSpace", "ximage"));
                    JSONArray jSONArray2 = new JSONArray();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("url", optJSONObject.optString("url"));
                    jSONObject2.put("fileId", optJSONObject.optString(PushConstants.SUB_ALIAS_STATUS_NAME));
                    jSONObject2.put("version_code", optJSONObject.optInt("version"));
                    jSONObject2.put("file_type", optJSONObject.optString("file_type", "zip"));
                    jSONObject2.put("md5", optJSONObject.optString("md5"));
                    jSONObject2.put("project_priority", 2);
                    jSONArray2.put(jSONObject2);
                    jSONObject.put("files", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
            j.l().p("ximage", jSONArray);
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static c h() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str) {
        com.jingdong.app.mall.h.c.a aVar = this.f8451e;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public void f(@NonNull c cVar) {
        this.b = cVar.a();
        this.f8450c = cVar.b();
        this.d = PackageInfoUtil.getVersionName(this.b);
        this.f8451e = cVar.c();
        Application application = this.b;
        if (application != null) {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = f8449g;
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            this.b.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    public void i() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(this.f8450c);
        httpSetting.setFunctionId("clientXimage");
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putQueryParam("client", com.jingdong.sdk.utils.b.a.a());
        httpSetting.putQueryParam(HybridSDK.APP_VERSION, this.d);
        httpSetting.setListener(new C0267b());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
