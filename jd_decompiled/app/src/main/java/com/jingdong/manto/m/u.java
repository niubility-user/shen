package com.jingdong.manto.m;

import android.app.Activity;
import android.os.PowerManager;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class u extends d0 {
    private static boolean d;
    com.jingdong.manto.h a;
    PowerManager.WakeLock b;

    /* renamed from: c  reason: collision with root package name */
    AppLifeCycle.Listener f13718c = new a();

    /* loaded from: classes15.dex */
    class a extends AppLifeCycle.Listener {
        a() {
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public final void onAppDestroy() {
            MantoLog.i("JsApiSetKeepScreenOn", "onDestroy");
            if (u.this.d()) {
                u.this.e();
            }
            AppLifeCycle.remove(u.this.a.a(), this);
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppPause() {
            MantoLog.i("JsApiSetKeepScreenOn", "onPause");
            if (u.this.d()) {
                u.this.e();
            }
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public final void onAppResume() {
            MantoLog.i("JsApiSetKeepScreenOn", "onResume");
            if (u.d) {
                u.this.c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean c() {
        String str;
        String str2;
        boolean z;
        if (this.a.p() == null) {
            MantoLog.e("JsApiSetKeepScreenOn", "acquire fail, server context is nul");
            z = false;
        } else {
            MantoLog.e("JsApiSetKeepScreenOn", "acquire ok");
            Activity p = this.a.p();
            if (this.b == null) {
                this.b = ((PowerManager) p.getSystemService("power")).newWakeLock(536870922, "Manto:JsApiSetKeepScreenOn");
            }
            if (this.b.isHeld()) {
                str = "JsApiSetKeepScreenOn";
                str2 = "wakeLock has held ";
            } else {
                this.b.acquire();
                str = "JsApiSetKeepScreenOn";
                str2 = "wakeLock acquire";
            }
            MantoLog.i(str, str2);
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean d() {
        boolean z;
        PowerManager.WakeLock wakeLock = this.b;
        if (wakeLock != null) {
            z = wakeLock.isHeld();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean e() {
        boolean z;
        MantoLog.e("JsApiSetKeepScreenOn", "release");
        PowerManager.WakeLock wakeLock = this.b;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.b.release();
            this.b = null;
            z = true;
        }
        MantoLog.e("JsApiSetKeepScreenOn", "wakeLock is  null");
        z = false;
        return z;
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        boolean e2;
        String str3;
        if (jSONObject == null) {
            MantoLog.e("JsApiSetKeepScreenOn", "setKeepScreenOn data is null");
            str3 = "fail:data is null";
        } else if (hVar.p() == null) {
            MantoLog.e("JsApiSetKeepScreenOn", "setKeepScreenOn, server context is nul");
            str3 = "fail:context is null";
        } else {
            boolean optBoolean = jSONObject.optBoolean("keepScreenOn", false);
            d = optBoolean;
            this.a = hVar;
            if (optBoolean) {
                AppLifeCycle.add(hVar.a(), this.f13718c);
                e2 = c();
            } else if (!d()) {
                MantoLog.e("JsApiSetKeepScreenOn", "fail, has not set screen");
                str2 = "fail:has not set screen";
                hVar.a(i2, putErrMsg(str2, null, str));
                return;
            } else {
                MantoLog.i("JsApiSetKeepScreenOn", "reset screen off");
                e2 = e();
            }
            if (e2) {
                MantoLog.i("JsApiSetKeepScreenOn", "setKeepScreenOn ok");
                str2 = IMantoBaseModule.SUCCESS;
                hVar.a(i2, putErrMsg(str2, null, str));
                return;
            }
            MantoLog.e("JsApiSetKeepScreenOn", "setKeepScreenOn fail");
            str3 = "fail";
        }
        hVar.a(i2, putErrMsg(str3, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setKeepScreenOn";
    }
}
