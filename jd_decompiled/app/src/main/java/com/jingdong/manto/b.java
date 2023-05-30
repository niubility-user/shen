package com.jingdong.manto;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;
import com.jingdong.a;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.jsapi.refact.JsApiOpenDocument;
import com.jingdong.manto.jsapi.refact.JsApiShowPickerView;
import com.jingdong.manto.jsapi.refact.media.JsApiChooseImage;
import com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia;
import com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo;
import com.jingdong.manto.jsapi.refact.media.JsApiPreviewImage;
import com.jingdong.manto.m.b0;
import com.jingdong.manto.message.MantoAcrossMessage;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.message.ProcessMessageManager;
import com.jingdong.manto.pkg.AppExecutors;
import com.jingdong.manto.preload.MantoMPReceiver0;
import com.jingdong.manto.preload.MantoMPReceiverSingleProcess;
import com.jingdong.manto.sdk.IMantoSdkBase;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.u.b;
import com.jingdong.manto.ui.MantoActivity;
import com.jingdong.manto.utils.MantoCryptoUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoMd5Utils;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.m;
import com.jingdong.sdk.jweb.JWebFactory;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Random;

/* loaded from: classes15.dex */
public class b {
    public static boolean a;
    private static String b;

    /* renamed from: c */
    private static a.C0232a f12995c;
    private static com.jingdong.manto.pkg.c.a d;

    /* renamed from: e */
    private static AppExecutors f12996e;

    /* renamed from: f */
    private static com.jingdong.manto.p.f.a f12997f;

    /* renamed from: g */
    private static String f12998g = MantoMd5Utils.md5OfString("").trim();

    /* renamed from: h */
    private static String f12999h = "";

    /* renamed from: i */
    private static SoftReference<Activity> f13000i = null;

    /* loaded from: classes15.dex */
    public class a implements JWebFactory.InitCallback {
        a() {
        }

        @Override // com.jingdong.sdk.jweb.JWebFactory.InitCallback
        public void onFinish(boolean z) {
            MantoLog.d("onFinish", "canUse:" + z);
            if (com.jingdong.manto.jsengine.d.a() != a.c.j2v8) {
                com.jingdong.manto.preload.b.a(null);
            }
        }
    }

    /* renamed from: com.jingdong.manto.b$b */
    /* loaded from: classes15.dex */
    public class C0504b implements MantoAcrossMessage.Listener {
        C0504b() {
        }

        @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
        public void onCalled(Object obj) {
            if (obj != null && (obj instanceof com.jingdong.manto.message.d) && ((com.jingdong.manto.message.d) obj).a() == com.jingdong.manto.message.d.f13863c) {
                MantoLog.e("MantoAcrossMessage", "network change in miniprogram");
                com.jingdong.manto.p.d.d.b().c();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements MantoAcrossMessage.Listener {
        c() {
        }

        @Override // com.jingdong.manto.message.MantoAcrossMessage.Listener
        public void onCalled(Object obj) {
            if (obj != null && (obj instanceof com.jingdong.manto.message.d) && ((com.jingdong.manto.message.d) obj).a() == com.jingdong.manto.message.d.d) {
                MantoLog.e("MantoAcrossMessage", "kill");
                System.exit(0);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b();
        }
    }

    /* loaded from: classes15.dex */
    public class e implements b.c {
        e() {
        }

        @Override // com.jingdong.manto.u.b.c
        public void onFail() {
            MantoLog.d("onFail", new Object[0]);
        }

        @Override // com.jingdong.manto.u.b.c
        public void onSuccess() {
            if (MantoProcessUtil.isMantoProcess()) {
                com.jingdong.manto.preload.b.g();
            }
            MantoLog.d("onSuccess", new Object[0]);
        }
    }

    public static void a(long j2) {
        com.jingdong.manto.preload.c.a().a(j2);
    }

    public static void a(Activity activity) {
        f latestRuntime;
        if (!(activity instanceof MantoActivity) || (latestRuntime = ((MantoActivity) activity).getLatestRuntime()) == null) {
            return;
        }
        latestRuntime.I();
    }

    public static void a(a.C0232a c0232a) {
        String processName;
        ILogin iLogin;
        f12995c = c0232a;
        b = com.jingdong.a.b;
        a = com.jingdong.a.a;
        com.jingdong.manto.c.a(c0232a);
        MantoProcessUtil.setConfig(c0232a);
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.j1.b());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.j1.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.y0.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.j1.c());
        OpenJsApiManager.addServiceJsApi(new JsApiOpenDocument());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.q0.b());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.q0.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.q0.c());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.p1.a());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.g1.b());
        OpenJsApiManager.addPageJsApi(new JsApiShowPickerView());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.jsapi.webview.d());
        OpenJsApiManager.addWebViewJsApi(new com.jingdong.manto.jsapi.webview.f());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.jsapi.webview.e());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.j1.d.a());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.j1.d.c());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.j1.d.b());
        OpenJsApiManager.addPageJsApi(new JsApiChooseImage());
        OpenJsApiManager.addWebViewJsApi(new JsApiChooseImage());
        OpenJsApiManager.addServiceJsApi(new JsApiChooseImage());
        OpenJsApiManager.addPageJsApi(new JsApiPreviewImage());
        OpenJsApiManager.addWebViewJsApi(new JsApiPreviewImage());
        OpenJsApiManager.addServiceJsApi(new JsApiPreviewImage());
        OpenJsApiManager.addServiceJsApi(new JsApiChooseMedia());
        OpenJsApiManager.addPageJsApi(new JsApiChooseMedia());
        OpenJsApiManager.addServiceJsApi(new JsApiChooseVideo());
        OpenJsApiManager.addPageJsApi(new JsApiChooseVideo());
        OpenJsApiManager.addWebViewJsApi(new JsApiChooseVideo());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.a1.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.a1.b());
        OpenJsApiManager.addWebViewJsApi(new JsApiPreviewImage());
        OpenJsApiManager.addServiceJsApi(new JsApiPreviewImage());
        OpenJsApiManager.addWebViewJsApi(new com.jingdong.manto.m.q0.b());
        OpenJsApiManager.addWebViewJsApi(new com.jingdong.manto.m.j1.a());
        OpenJsApiManager.addServiceJsApi(new b0());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.u1.c());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.u1.b());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.k1.b());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.k1.b());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.v0.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.o0.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.c1.b());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.i1.a());
        OpenJsApiManager.addServiceJsApi(new com.jingdong.manto.m.k1.a());
        OpenJsApiManager.addPageJsApi(new com.jingdong.manto.m.z0.e());
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                if (MantoProcessUtil.isMantoProcess()) {
                    if (TextUtils.isEmpty(MantoProcessUtil.getProcessName())) {
                        processName = "process" + new Random().nextInt(1000);
                    } else {
                        processName = MantoProcessUtil.getProcessName();
                    }
                    WebView.setDataDirectorySuffix(processName);
                }
            } catch (Throwable th) {
                MantoLog.e("webview", th);
            }
        }
        if (MantoProcessUtil.isMantoProcess()) {
            try {
                JWebFactory.initJSContextCore(c0232a.a(), JWebFactory.JSContextType.CT_TYPE_X5, new a());
            } catch (Exception e2) {
                MantoLog.e("x5init", e2);
            }
        }
        if (MantoProcessUtil.isMainProcess()) {
            try {
                com.jingdong.manto.p.f.a aVar = new com.jingdong.manto.p.f.a();
                f12997f = aVar;
                aVar.b(c0232a.a());
            } catch (Throwable th2) {
                MantoLog.e("netState", th2);
            }
        }
        if (MantoProcessUtil.isMainProcess() || MantoProcessUtil.isMantoProcess()) {
            d = com.jingdong.manto.pkg.c.a.b(c0232a.a());
        }
        t();
        n();
        try {
            if (MantoProcessUtil.isMantoProcess() && (iLogin = (ILogin) com.jingdong.a.n(ILogin.class)) != null) {
                iLogin.asyncWebCookies();
            }
        } catch (Throwable th3) {
            MantoLog.e("asyncWebCookies", th3);
        }
        if (MantoProcessUtil.isMainProcess()) {
            com.jingdong.manto.n.c.a();
        }
    }

    public static void a(a.c cVar) {
        com.jingdong.manto.jsengine.d.a(cVar);
    }

    public static <T extends IMantoSdkBase> void a(Class<T> cls, Class<? extends T> cls2) {
        MantoSdkManager.register(cls, cls2);
    }

    public static void a(String str) {
        if (MantoProcessUtil.isMainProcess()) {
            MantoLog.d("better", "updateSandBox:" + str);
            com.jingdong.manto.pkg.c.a.c(MantoMd5Utils.md5OfString(str).trim());
        }
    }

    public static void a(Map<String, String> map) {
        m.a(map);
    }

    public static void a(boolean z) {
        if (MantoProcessUtil.isMainProcess()) {
            f12995c.c(z);
            if (z) {
                Intent intent = new Intent();
                intent.setClass(com.jingdong.manto.c.a(), MantoMPReceiver0.class);
                Intent intent2 = new Intent();
                intent2.setClass(com.jingdong.manto.c.a(), MantoMPReceiverSingleProcess.class);
                try {
                    com.jingdong.manto.c.a().sendBroadcast(intent);
                    com.jingdong.manto.c.a().sendBroadcast(intent2);
                } catch (Exception e2) {
                    MantoLog.e("setX5InitFlag", e2);
                }
            }
        }
    }

    public static void a(String[] strArr) {
        com.jingdong.manto.preload.a.a().a(strArr);
    }

    public static void b() {
        com.jingdong.manto.u.b.c().b(new e());
    }

    public static void b(Activity activity) {
        f13000i = new SoftReference<>(activity);
    }

    public static void c() {
        if (MantoProcessUtil.isMainProcess()) {
            MantoAcrossMessageCenter.notifyCommonData(new com.jingdong.manto.message.d().a(com.jingdong.manto.message.d.d));
        }
    }

    public static AppExecutors d() {
        if (f12996e == null) {
            f12996e = new AppExecutors();
        }
        return f12996e;
    }

    public static String e() {
        return b;
    }

    public static Context f() {
        return f12995c.a();
    }

    public static a.C0232a g() {
        return f12995c;
    }

    public static Activity h() {
        SoftReference<Activity> softReference = f13000i;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public static com.jingdong.manto.pkg.c.a i() {
        return d;
    }

    public static String j() {
        return MantoCryptoUtils.a(l() + "7D6D16CC3D2BE89108F9DCFC9A855253", "616E746F");
    }

    public static com.jingdong.manto.pkg.a k() {
        return com.jingdong.manto.pkg.a.a(d);
    }

    public static String l() {
        return "2AA64BD44C4381F31D9DA68EFE377874";
    }

    public static String m() {
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null) {
            String pin = iLogin.getPin(f());
            if (MantoStringUtils.isEmpty(pin) && MantoProcessUtil.isMantoProcess()) {
                pin = com.jingdong.manto.utils.f.a(f());
            }
            if (!MantoStringUtils.isEmpty(pin)) {
                f12999h = MantoMd5Utils.md5OfString(pin).trim();
                MantoLog.d("better", "getUserFlag, pin: " + pin);
            }
        }
        MantoLog.d("better", "getUserFlag: " + f12999h);
        return TextUtils.isEmpty(f12999h) ? f12998g : f12999h;
    }

    private static void n() {
        if (com.jingdong.manto.jsengine.d.a() == a.c.j2v8) {
            String a2 = m.a("threadInitV8", "1");
            if (MantoProcessUtil.isMainProcess() && TextUtils.equals(a2, "1")) {
                ThreadManager.heavy().post(new d());
            } else {
                b();
            }
        }
    }

    public static boolean o() {
        return false;
    }

    public static void p() {
        if (MantoProcessUtil.isMainProcess()) {
            com.jingdong.manto.pkg.c.a.c(f12998g);
            com.jingdong.manto.n.c.a(1);
        }
    }

    public static void q() {
        com.jingdong.manto.preload.c.a().d();
    }

    public static void r() {
        if (MantoProcessUtil.isMainProcess()) {
            Intent intent = new Intent();
            intent.setClass(com.jingdong.manto.c.a(), MantoMPReceiverSingleProcess.class);
            try {
                com.jingdong.manto.c.a().sendBroadcast(intent);
            } catch (Exception e2) {
                MantoLog.e("preInitMainTask", e2);
            }
        }
    }

    public static void s() {
        if (MantoProcessUtil.isMainProcess()) {
            Intent intent = new Intent();
            intent.setClass(com.jingdong.manto.c.a(), MantoMPReceiver0.class);
            try {
                com.jingdong.manto.c.a().sendBroadcast(intent);
            } catch (Exception e2) {
                MantoLog.e("preInitMantoTask", e2);
            }
        }
    }

    private static void t() {
        if (MantoProcessUtil.isMantoProcess()) {
            com.jingdong.manto.utils.a.a().a((Application) f12995c.a());
        }
        ProcessMessageManager.getInstance().init();
        com.jingdong.manto.k.a.b().c();
        MantoLog.d("MantoAcrossMessage", "Network registListener");
        ProcessMessageManager.getInstance().registListener(new C0504b());
        if (MantoProcessUtil.isMantoProcess()) {
            ProcessMessageManager.getInstance().registListener(new c());
        }
    }
}
