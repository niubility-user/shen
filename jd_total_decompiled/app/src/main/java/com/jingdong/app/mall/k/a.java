package com.jingdong.app.mall.k;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.alibaba.android.patronus.Patrons;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.mall.LoginStateReceiver;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.app.mall.utils.k;
import com.jingdong.app.mall.utils.l;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.InitApplication;
import com.jingdong.common.network.cronet.CronetComponentHelper;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.login.LoginUserHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.jdcrashreport.JDCrashReportConfig;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.cronet.ICronetBridge;
import tv.danmaku.ijk.media.ext.cache.JDPlayerVideoCache;
import tv.danmaku.ijk.media.ext.cache.VideoCacheConfig;
import tv.danmaku.ijk.media.ext.identify.HostAppInfo;
import tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke;

/* loaded from: classes4.dex */
public class a implements com.jingdong.app.mall.k.f {
    public static long a;
    public static boolean b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.k.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0339a implements l.a {
        C0339a(a aVar) {
        }

        @Override // com.jingdong.app.mall.utils.l.a
        public void a(boolean z) {
            LBSSceneSwitchHelper.appUpgradeInit(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends JDCrashReportConfig.c {
        b(a aVar) {
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportConfig.c
        public String a() {
            String currentMode;
            try {
                currentMode = JDBModeUtils.getCurrentMode();
                Log.d("ProcessInit", "app currentMode:" + currentMode);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (TextUtils.isEmpty(currentMode)) {
                return "1";
            }
            char c2 = '\uffff';
            switch (currentMode.hashCode()) {
                case 48:
                    if (currentMode.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (currentMode.equals("1")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (currentMode.equals("2")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            return c2 != 0 ? c2 != 1 ? c2 != 2 ? "1" : "2" : "3" : "1";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements JDCrashReportConfig.d {
        c(a aVar) {
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportConfig.d
        public boolean a() {
            return SwitchQueryFetcher.isXTime();
        }
    }

    /* loaded from: classes4.dex */
    class d implements SwitchQueryFetcher.FetchListener {
        d(a aVar) {
        }

        @Override // com.jingdong.common.utils.SwitchQueryFetcher.FetchListener
        public void onFetchEnd(boolean z) {
            if (z) {
                k.a = true;
            }
        }
    }

    /* loaded from: classes4.dex */
    class e implements PlayerReportInvoke {
        e(a aVar) {
        }

        @Override // tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke
        public void onMtaReportClick(Context context, String str, String str2) {
            if (context != null) {
                JDMtaUtils.onClick(context, str, str2);
            }
        }

        @Override // tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke
        public void onPerfReport(WeakReference<Context> weakReference, String str, String str2, HashMap<String, String> hashMap) {
            if (weakReference == null || !PerformanceReporter.getIsNeedReport(weakReference.get(), str, str2)) {
                return;
            }
            PerformanceReporter.reportPlayerData(hashMap);
        }
    }

    /* loaded from: classes4.dex */
    class f implements ICronetBridge {
        private boolean a;

        f() {
        }

        @Override // tv.danmaku.ijk.media.cronet.ICronetBridge
        public boolean isCronetPrepared() {
            if (ProcessUtil.isMainProcess()) {
                boolean c2 = com.jingdong.app.mall.network.a.a().c();
                this.a = c2;
                return c2;
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.cronet.ICronetBridge
        public boolean loadPluginPlayerSo() {
            if (this.a) {
                return a.this.g(CronetComponentHelper.METHOD_NAME_CRONET_LOADER_PLUGIN_SO);
            }
            return false;
        }

        @Override // tv.danmaku.ijk.media.cronet.ICronetBridge
        public boolean loadQuicSo() {
            if (this.a) {
                return a.this.g("loadQuicSo");
            }
            return false;
        }
    }

    /* loaded from: classes4.dex */
    class g implements HostAppInfo.ActivityInfoCallback {
        g(a aVar) {
        }

        @Override // tv.danmaku.ijk.media.ext.identify.HostAppInfo.ActivityInfoCallback
        public String getCurActivityClsName() {
            if (ProcessUtil.isMainProcess()) {
                try {
                    if (ActivityManagerUtil.getScreenManager().currentActivity() != null && ActivityManagerUtil.getScreenManager().currentActivity().getComponentName() != null) {
                        return ActivityManagerUtil.getScreenManager().currentActivity().getComponentName().getClassName();
                    }
                } catch (Exception unused) {
                }
                return "";
            }
            return "";
        }

        @Override // tv.danmaku.ijk.media.ext.identify.HostAppInfo.ActivityInfoCallback
        public boolean isMainProcess() {
            return ProcessUtil.isMainProcess();
        }
    }

    /* loaded from: classes.dex */
    public static class h {
        public static com.jingdong.app.mall.k.f a() {
            if (ProcessUtil.isMainProcess()) {
                return new com.jingdong.app.mall.k.d();
            }
            if (ProcessUtil.isPushProcess()) {
                return new com.jingdong.app.mall.k.g();
            }
            if (ProcessUtil.isSafeModeProcess()) {
                return new com.jingdong.app.mall.k.h();
            }
            if (ProcessUtil.isWatchDogProcess()) {
                return new i();
            }
            if (ProcessUtil.isErrorProcess()) {
                return new com.jingdong.app.mall.k.b();
            }
            if (ProcessUtil.getProcessName(JdSdk.getInstance().getApplication()) != null && (ProcessUtil.getProcessName(JdSdk.getInstance().getApplication()).contains(":manto") || ProcessUtil.getProcessName(JdSdk.getInstance().getApplication()).contains(":tools"))) {
                return new com.jingdong.app.mall.k.e();
            }
            return new a();
        }
    }

    private void c(String str) {
        if (SwitchQueryFetcher.isXTime()) {
            return;
        }
        JdCrashReport.init(new JDCrashReportConfig.Builder().setContext(a()).setAppId("fba8ae5a5078417d90ae1355af234d4f").setDeviceUniqueId(StatisticsReportUtil.readDeviceUUID()).setUserId(str).setPartner(Configuration.getProperty(Configuration.PARTNER)).addFilters("com.((jingdong.(?!aura.core))|jd.)\\S+", "\\S+jd.\\S+").enableRecover(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_CRASH_SETTINGS, ABTestUtils.KEY_CONFIG_CRASH_RECOVER_STACK_ENABLE, "1").equals("1")).setRecoverInfo(com.jingdong.app.mall.utils.i.d(a())).setCustomRecoverView(new com.jingdong.app.mall.d()).setDowngradeCallback(new c(this)).setCustomParamConfig(new b(this)).setReportUV(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_CRASH_SETTINGS, "uvReportConfig", "1").equals("1") ? ProcessUtil.isMainProcess() : true).build(), false);
        JdCrashReport.setCrashHandleCallback(com.jingdong.app.mall.utils.i.c());
    }

    private void i() {
        l.a(new C0339a(this));
        l.d();
    }

    public Application a() {
        return JdSdk.getInstance().getApplication();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        InitApplication.instance(a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        SwitchQueryFetcher.getFetcher().addFetchListener(new d(this));
        JdImageToolKit.initialize(JdImageToolKit.newBuilder(a().getApplicationContext()).setReportHandlerImpl(k.b()).setNetworkParameterImpl(k.h()).setImageControllerImpl(k.f()).setOtherDependencyImpl(k.i()).setNetStatReporter(k.g()).setHttpDnsDependency(k.c()).setCDNDomainResolver(k.a()).setAvifWhitePageEnable(k.d()).setGifWhitePageEnable(k.e()).build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        if (b) {
            return;
        }
        try {
            if ("1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_PATRONS, "enable", "0"))) {
                b = Patrons.init(JdSdk.getInstance().getApplicationContext(), null) == 0;
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f() {
        try {
            JDPlayerSdk.getInstance().init(new JDPlayerSdk.JDPlayerConfig.Builder().appContext(JdSdk.getInstance().getApplicationContext()).googleChannel(JdSdk.getInstance().isGoogleChannel()).mtaInitInfo(JDMtaUtils.getMaInitCommonInfo(JdSdk.getInstance().getApplicationContext())).vsrKey("aiGcJGreFzXVPzzqXS49Ion+6LS68++yjM+3g0DZjWpVIQipQdLKhwj8OjuvHVKATgYeu0NCxJM51QFFlD7fi7ueKScFiHngXxbl9DJg2Mq8Gi8OiuwvGrgaWaHwMcqFaO/bPVIAC37mp/0Ss1TgEhUQFFbYYqcgsjPzclQIniU=").appId("9958c3b89e3b0ece82e8929ead8f1b592935c12e").hostAppInfo(new HostAppInfo("jdmall", PackageInfoUtil.getVersionName(), String.valueOf(PackageInfoUtil.getVersionCode()), new g(this))).cronetBridge(new f()).reportInvoke(new e(this)).enableDebug(false).build());
            JDPlayerVideoCache.getInstance().init(new VideoCacheConfig.Builder().appContext(JdSdk.getInstance().getApplicationContext()).maxSyncPreload(1).preloadSize(409600L).enablePreload(true).build());
        } catch (Throwable unused) {
        }
    }

    public boolean g(String str) {
        Object invoke;
        try {
            Method method = CronetComponentHelper.getMethod(CronetComponentHelper.CLASS_NAME_CRONET_SO_LOADER, str, new Class[0]);
            if (method != null && (invoke = method.invoke(null, new Object[0])) != null) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
        try {
            com.jd.manto.a.e(false);
            com.jd.manto.a.a(a(), Boolean.FALSE);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.app.mall.k.f
    public void onBaseContextAttached(Context context) {
        String str;
        a = System.currentTimeMillis();
        if (!Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getJdSharedPreferences().getBoolean(Configuration.HAS_INIT_TIP, false)) {
            JDMtaUtils.acceptProtocal(true);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.out");
        intentFilter.addAction("com.jingdong.action.user.login.in");
        a().registerReceiver(new LoginStateReceiver(), intentFilter);
        try {
            str = LoginUserHelper.getInstance().getLoginUser().getLoginUserName();
        } catch (Throwable unused) {
            str = "";
        }
        c(str);
        BaseApplication.initOnCreateInBase();
        com.jingdong.app.mall.utils.d.i();
        i();
    }

    @Override // com.jingdong.app.mall.k.f
    public void onCreate() {
        WebUtils.webViewSetPath(JdSdk.getInstance().getApplication());
        com.jingdong.app.mall.utils.d.k();
        com.jingdong.jdsdk.d.b.a(false, JdSdk.getInstance().getApplicationContext());
        e();
    }
}
