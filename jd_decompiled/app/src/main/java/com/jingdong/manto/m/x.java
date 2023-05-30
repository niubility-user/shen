package com.jingdong.manto.m;

import android.app.Activity;
import android.view.WindowManager;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class x extends d0 {
    float a = Float.NaN;
    float b;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13815c;
        final /* synthetic */ String d;

        /* renamed from: com.jingdong.manto.m.x$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0633a extends AppLifeCycle.Listener {
            C0633a() {
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppDestroy() {
                super.onAppDestroy();
                a aVar = a.this;
                x.this.a = Float.NaN;
                AppLifeCycle.remove(aVar.b.a(), this);
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppPause() {
                super.onAppPause();
                a aVar = a.this;
                Activity activity = x.this.getCore(aVar.b).getActivity();
                if (activity == null || activity.isFinishing() || activity.getWindow() == null) {
                    return;
                }
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.screenBrightness = x.this.a;
                activity.getWindow().setAttributes(attributes);
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppResume() {
                super.onAppResume();
                a aVar = a.this;
                Activity activity = x.this.getCore(aVar.b).getActivity();
                if (activity == null || activity.isFinishing() || activity.getWindow() == null) {
                    return;
                }
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.screenBrightness = x.this.b;
                activity.getWindow().setAttributes(attributes);
            }
        }

        a(JSONObject jSONObject, com.jingdong.manto.h hVar, int i2, String str) {
            this.a = jSONObject;
            this.b = hVar;
            this.f13815c = i2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.b = (float) this.a.optDouble("value");
            if (!Float.isNaN(x.this.b)) {
                x xVar = x.this;
                float f2 = xVar.b;
                if (f2 >= 0.0f && f2 <= 1.0f) {
                    Activity activity = xVar.getCore(this.b).getActivity();
                    if (activity == null) {
                        this.b.a(this.f13815c, x.this.putErrMsg("fail", null, this.d));
                        MantoLog.e("setScreenBrightness", "context is null, invoke fail!");
                        return;
                    }
                    WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                    if (Float.isNaN(x.this.a)) {
                        x.this.a = attributes.screenBrightness;
                        AppLifeCycle.add(this.b.a(), new C0633a());
                    }
                    float f3 = x.this.b;
                    if (f3 < 0.01f) {
                        f3 = 0.01f;
                    }
                    attributes.screenBrightness = f3;
                    activity.getWindow().setAttributes(attributes);
                    this.b.a(this.f13815c, x.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
                    return;
                }
            }
            this.b.a(this.f13815c, x.this.putErrMsg("fail:value invalid", null, this.d));
            MantoLog.e("ScreenBrightness", "value invalid");
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        MantoLog.d("SetScreenBrightness", "JsApiSetScreenBrightness!");
        if (jSONObject != null) {
            com.jingdong.manto.sdk.thread.a.a(new a(jSONObject, hVar, i2, str));
            return;
        }
        hVar.a(i2, putErrMsg("fail:data is null", null, str));
        MantoLog.e("SetScreenBrightness", "data is null");
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setScreenBrightness";
    }
}
