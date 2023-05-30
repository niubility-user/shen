package com.jd.manto;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.manto.d.a0;
import com.jd.manto.d.c;
import com.jd.manto.d.d;
import com.jd.manto.d.e;
import com.jd.manto.d.f;
import com.jd.manto.d.g;
import com.jd.manto.d.h;
import com.jd.manto.d.i;
import com.jd.manto.d.j;
import com.jd.manto.d.k;
import com.jd.manto.d.l;
import com.jd.manto.d.m;
import com.jd.manto.d.n;
import com.jd.manto.d.o;
import com.jd.manto.d.p;
import com.jd.manto.d.q;
import com.jd.manto.d.r;
import com.jd.manto.d.s;
import com.jd.manto.d.t;
import com.jd.manto.d.u;
import com.jd.manto.d.v;
import com.jd.manto.d.w;
import com.jd.manto.d.y;
import com.jd.manto.d.z;
import com.jd.manto.hd.bluetooth.peripheral.JsApiPeripheral;
import com.jd.manto.hd.tcp.JsApiTcpServerNew;
import com.jd.manto.hd.tcp.JsApiTcpSocketNew;
import com.jd.manto.hd.udp.JsApiUDPSocketNew;
import com.jd.manto.hd.wifi.JsApiWifiNew;
import com.jd.manto.jdext.address.JsApiChooseAddress;
import com.jd.manto.jdext.cart.JsApiCart;
import com.jd.manto.jdext.code.JsApiGetJosAuthCode;
import com.jd.manto.jdext.pay.JsApiRequestPayment;
import com.jd.manto.jdext.phone.JsApiGetPhoneNumber;
import com.jd.manto.jdext.plus.JsApiGetPlusAuth;
import com.jd.manto.jdext.router.JsApiRouterToNative;
import com.jd.manto.jdext.uuid.JsApiGetUUIdSync;
import com.jd.manto.jdext.zhaixing.JsApiZhaixing;
import com.jd.manto.lbs.JsApiLocationNew;
import com.jd.manto.map.JsApiMapView;
import com.jingdong.a;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.manto.card.MantoCardHelper;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.sdk.api.IBizDaojia;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import com.jingdong.manto.sdk.api.IDeepDarkManager;
import com.jingdong.manto.sdk.api.IFaceDetect;
import com.jingdong.manto.sdk.api.IFeedback;
import com.jingdong.manto.sdk.api.IGlobalParam;
import com.jingdong.manto.sdk.api.IHostBaseInfo;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IMPDownGrade;
import com.jingdong.manto.sdk.api.IMantoLightMode;
import com.jingdong.manto.sdk.api.IMantoLog;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.manto.sdk.api.IModalDialog;
import com.jingdong.manto.sdk.api.INavigate;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.manto.sdk.api.IRouter;
import com.jingdong.manto.sdk.api.IShareManager;
import com.jingdong.manto.sdk.api.ITrackReport;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class a {
    private static boolean a;

    /* renamed from: com.jd.manto.a$a */
    /* loaded from: classes17.dex */
    public class C0174a extends a.b {

        /* renamed from: g */
        final /* synthetic */ Context f6245g;

        /* renamed from: h */
        final /* synthetic */ Map f6246h;

        C0174a(Context context, Map map) {
            this.f6245g = context;
            this.f6246h = map;
        }

        @Override // com.jingdong.a.d
        public Context getContext() {
            return this.f6245g;
        }

        @Override // com.jingdong.a.d
        public String getValue(String str) {
            return (String) this.f6246h.get(str);
        }
    }

    /* loaded from: classes17.dex */
    public class b implements X5InitUtil.WebCoreLoadListener {
        b() {
        }

        @Override // com.jingdong.common.utils.X5InitUtil.WebCoreLoadListener
        public void onCoreLoaded(boolean z) {
            String str = "X5InitUtil onCoreLoaded:" + z;
            if (TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", HttpDnsConfig.PREDOWNLOAD_PARAMS, "initMode", "1"))) {
                com.jingdong.a.A(z);
                return;
            }
            a.g();
            a.h();
        }
    }

    public static void a(Context context, Boolean bool) {
        com.jingdong.a.u(IGlobalParam.class, d.class);
        com.jingdong.a.u(ILogin.class, r.class);
        com.jingdong.a.u(IPermission.class, u.class);
        com.jingdong.a.u(IImageLoader.class, f.class);
        com.jingdong.a.u(ITrackReport.class, w.class);
        com.jingdong.a.u(IMantoLog.class, q.class);
        com.jingdong.a.u(IRequestPayment.class, o.class);
        com.jingdong.a.u(INavigate.class, z.class);
        com.jingdong.a.u(IRouter.class, a0.class);
        com.jingdong.a.u(IShareManager.class, v.class);
        com.jingdong.a.u(ICustomMenuInterface.class, c.class);
        com.jingdong.a.u(IWebview.class, y.class);
        com.jingdong.a.u(IBizDaojia.class, com.jd.manto.d.a.class);
        com.jingdong.a.u(IFeedback.class, l.class);
        com.jingdong.a.u(IDeepDarkManager.class, j.class);
        com.jingdong.a.u(IModalDialog.class, t.class);
        com.jingdong.a.u(AbsChooseMedia.class, com.jd.manto.d.b.class);
        com.jingdong.a.u(IHostBaseInfo.class, m.class);
        com.jingdong.a.u(IHostMenuInterface.class, n.class);
        com.jingdong.a.u(IMantoLightMode.class, p.class);
        com.jingdong.a.u(IMPDownGrade.class, s.class);
        com.jingdong.a.u(IFaceDetect.class, k.class);
        com.jingdong.moutaibuy.lib.a.b(new com.jd.manto.c.b());
        m();
        l();
        HashMap hashMap = new HashMap();
        hashMap.put(Configuration.PARTNER, CashierSdkGlobalConfig.CASHIER_SDK_SOURCE);
        hashMap.put("wjId", "100");
        hashMap.put("versionName", PackageInfoUtil.getVersionName());
        hashMap.put("versionCode", String.valueOf(PackageInfoUtil.getVersionCode()));
        hashMap.put("loginType", "4");
        hashMap.put("client", "android");
        hashMap.put("share_h5", bool.booleanValue() ? "https://beta-mini-app-static.jd.com/apps/share/index.html" : "https://mini-app-static.jd.com/apps/share/index.html");
        com.jingdong.a.l(new C0174a(context, hashMap), "jd00e7c795ae0f4648", bool.booleanValue(), true);
        String config = JDMobileConfig.getInstance().getConfig("manto", "net", "httpsetting", "1");
        String str = "useHttpSettingConfig: " + config + ", process:" + MantoProcessUtil.getProcessName();
        if (bool.booleanValue()) {
            k(CommonBase.getJdSharedPreferences().getBoolean("manto_dev_switch", false));
            if (CommonBase.getJdSharedPreferences().getBoolean("manto_httpsetting_switch", true)) {
                com.jingdong.a.u(IMantoServerRequester.class, e.class);
            }
        } else if (TextUtils.equals("1", config)) {
            com.jingdong.a.u(IMantoServerRequester.class, e.class);
        }
        d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.out");
        intentFilter.addAction("com.jingdong.action.user.login.in");
        try {
            context.registerReceiver(new MantoRipper(), intentFilter);
        } catch (Exception unused) {
        }
        i();
        f(context);
        b();
        c();
    }

    private static void b() {
        if (MantoProcessUtil.isMainProcess()) {
            MantoCardHelper.setUseCachedCardJdaInfo(TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", "card", "useCachedCardJdaInfo", "0")));
            try {
                MantoCardHelper.setAllowCardsCount(Integer.parseInt(JDMobileConfig.getInstance().getConfig("manto", "card", "allowCount", "2147483647")));
            } catch (Throwable unused) {
            }
        }
    }

    private static void c() {
        if (MantoProcessUtil.isMainProcess() || MantoProcessUtil.isMantoProcess()) {
            String config = JDMobileConfig.getInstance().getConfig("manto", "jsUpdate", "enable", "0");
            com.jingdong.a.m(TextUtils.equals("1", config), JDMobileConfig.getInstance().getConfig("manto", "jsUpdate", "json", ""));
        }
    }

    private static void d() {
        com.jingdong.a.c(new g());
        com.jingdong.a.c(new i());
        com.jingdong.a.c(new com.jd.manto.b.c());
        com.jingdong.a.c(new JsApiRouterToNative());
        com.jingdong.a.c(new JsApiGetUUIdSync());
        com.jingdong.a.b(new JsApiMapView());
        com.jingdong.a.c(new JsApiMapView());
        com.jingdong.a.c(new JsApiLocationNew());
        com.jingdong.a.c(new JsApiRequestPayment());
        com.jingdong.a.c(new JsApiGetJosAuthCode());
        com.jingdong.a.c(new JsApiGetPhoneNumber());
        com.jingdong.a.b(new JsApiGetPhoneNumber());
        com.jingdong.a.c(new JsApiGetPlusAuth());
        com.jingdong.a.c(new JsApiWifiNew());
        com.jingdong.a.c(new JsApiUDPSocketNew());
        com.jingdong.a.c(new JsApiTcpSocketNew());
        com.jingdong.a.b(new com.jd.manto.d.c0.b());
        com.jingdong.a.c(new com.jd.manto.d.c0.b());
        com.jingdong.a.b(new com.jd.manto.d.b0.b());
        com.jingdong.a.c(new com.jd.manto.d.b0.b());
        com.jingdong.a.c(new com.jd.manto.sdkimpl.face.a());
        com.jingdong.a.d(new com.jd.manto.sdkimpl.face.a());
        com.jingdong.a.c(new h());
        com.jingdong.a.c(new com.jd.manto.navigate.a());
        com.jingdong.a.b(new com.jd.manto.navigate.a());
        com.jingdong.a.c(new com.jd.manto.c.a());
        com.jingdong.a.c(new com.jd.manto.membercode.a());
        com.jingdong.a.b(new JsApiChooseAddress());
        com.jingdong.a.c(new JsApiChooseAddress());
        if (Build.VERSION.SDK_INT >= 21) {
            com.jingdong.a.c(new JsApiPeripheral());
        }
        com.jingdong.a.c(new JsApiCart());
        com.jingdong.a.c(new com.jd.manto.d.e0.a());
        com.jingdong.a.c(new com.jd.manto.d.d0.a());
        com.jingdong.a.b(new com.jd.manto.d.d0.b());
        com.jingdong.a.c(new JsApiTcpServerNew());
        com.jingdong.a.c(new JsApiZhaixing());
        com.jingdong.a.c(new com.jd.manto.router.a());
        com.jingdong.a.c(new com.jd.manto.navigate.b());
    }

    public static void e(boolean z) {
        a = z;
    }

    public static void f(Context context) {
        if (MantoProcessUtil.isMainProcess()) {
            String config = JDMobileConfig.getInstance().getConfig("manto", "preDownload", IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "1");
            String config2 = JDMobileConfig.getInstance().getConfig("manto", "preDownload", "interval", "480");
            if (JDPrivacyHelper.isAcceptPrivacy(context)) {
                long j2 = 480;
                try {
                    j2 = Long.parseLong(config2);
                } catch (Throwable unused) {
                }
                if ("1".equals(config)) {
                    com.jingdong.a.y(j2);
                    com.jingdong.a.r();
                }
            }
        }
    }

    public static void g() {
        if (TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", HttpDnsConfig.PREDOWNLOAD_PARAMS, "initMainTask", "0"))) {
            com.jingdong.a.s();
        }
    }

    public static void h() {
        if (TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", HttpDnsConfig.PREDOWNLOAD_PARAMS, "initMantoTask", "0"))) {
            com.jingdong.a.t();
        }
    }

    private static void i() {
        if (MantoProcessUtil.isMainProcess() && TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", HttpDnsConfig.PREDOWNLOAD_PARAMS, "useX5InitFlag", "0"))) {
            X5InitUtil.registerWebCoreLoadListener(new b());
        }
    }

    public static void j() {
        if (MantoProcessUtil.isMainProcess() && TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("manto", "card", "preLoadInMain", "0"))) {
            MantoCardHelper.preloadInMain();
        }
    }

    public static void k(boolean z) {
        com.jingdong.a.a = z;
    }

    private static void l() {
        com.jingdong.a.x(JDMobileConfig.getInstance().getConfigs("manto", NavigatorHolder.NaviEntity.TYPE_CUSTOM));
    }

    private static void m() {
        String config;
        if (ProcessUtil.isMainProcess()) {
            config = JDMobileConfig.getInstance().getConfig("manto", "card", "runtimeLevel", "v8");
        } else {
            config = JDMobileConfig.getInstance().getConfig("manto", "setRuntimeLevel", "runtimeLevel", "v8");
        }
        if (TextUtils.equals(config, "v8") && !a) {
            com.jingdong.a.z(a.c.j2v8);
        } else if (TextUtils.equals(config, "x5")) {
            com.jingdong.a.z(a.c.x5);
        } else {
            com.jingdong.a.z(a.c.webview);
        }
    }
}
