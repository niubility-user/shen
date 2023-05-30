package com.jingdong.app.mall.k;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.caas.caasservice.HwCaasUtils;
import com.jd.cashier.app.jdlibcutter.initialize.CashierSdkInitializer;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.ICustomHost;
import com.jd.dynamic.base.interfaces.IABConfig;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.yoga.android.YogaInit;
import com.jd.framework.json.JDJSONObject;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.XRenderAdapter;
import com.jd.skin.lib.JDSkinSDK;
import com.jingdong.app.mall.JDApp;
import com.jingdong.app.mall.aura.p;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.app.mall.dynamicImpl.DynamicMtaImpl;
import com.jingdong.app.mall.dynamicImpl.m;
import com.jingdong.app.mall.dynamicImpl.q;
import com.jingdong.app.mall.dynamicImpl.r;
import com.jingdong.app.mall.dynamicImpl.s;
import com.jingdong.app.mall.dynamicImpl.t;
import com.jingdong.app.mall.dynamicImpl.u;
import com.jingdong.app.mall.service.ADService;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.aura.wrapper.mhCallback.ImHCallBack;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.appupdate.UpdateSharedPreferenceUtil;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.deeplinkhelper.unittransform.UnitTransformHelper;
import com.jingdong.common.dynamic.CustomViewImpl;
import com.jingdong.common.handle.JDRiskHandleHelper;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.JDReactManager;
import com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback;
import com.jingdong.common.jdreactFramework.preload.JDReactCommonPreloadManager;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.login.DeviceFingerUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.model.mta.JDMtaPageEventIds;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.phonecharge.unittransformimpl.PhoneOrFlowCharge;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AppPartnerUtil;
import com.jingdong.common.utils.AuraPreLoadBundleHelper;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonNightModeUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSecUtils;
import com.jingdong.common.utils.ParseUtil;
import com.jingdong.common.utils.ServerConfigFetcher;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.utils.caas.CaasKitHelper;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebSyncLoginReceiver;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.web.xrender.XRenderAdapterImpl;
import com.jingdong.common.xbridge.BridgeManager;
import com.jingdong.common.xbridge.XWidgetManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.utils.JDDnsUtil;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.pdj.libdjbasecore.app.BaseCoreHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.ServiceBind;
import com.tencent.tencentmap.mapsdk.maps.TencentMapInitializer;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class d extends com.jingdong.app.mall.k.a {

    /* renamed from: c */
    private Handler f11147c = new Handler();

    /* loaded from: classes4.dex */
    class a implements ImHCallBack {
        a(d dVar) {
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onPauseActivity() {
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onPauseActivityFinishing() {
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onServiceArgs() {
            p.a();
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onSleeping() {
            p.a();
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onStopActivityHide() {
            p.a();
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onStopActivityShow() {
            p.a();
        }

        @Override // com.jingdong.aura.wrapper.mhCallback.ImHCallBack
        public void onStopService() {
            p.a();
        }
    }

    /* loaded from: classes4.dex */
    class b implements Application.ActivityLifecycleCallbacks {
        b(d dVar) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            if (OKLog.I) {
                OKLog.i("JDApp", "onActivityCreated: " + activity.getClass().getSimpleName());
            }
            ActivityManagerUtil.getScreenManager().pushActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            if (OKLog.I) {
                OKLog.i("JDApp", "onActivityDestroyed: " + activity.getClass().getSimpleName());
            }
            ActivityManagerUtil.getScreenManager().popActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
        }
    }

    /* loaded from: classes4.dex */
    public class c implements ICustomHost {
        c(d dVar) {
        }

        @Override // com.jd.dynamic.base.ICustomHost
        public String getHost() {
            return HostConfig.getInstance().getHost(Configuration.DYNAMIC_SDK_HOST);
        }
    }

    /* renamed from: com.jingdong.app.mall.k.d$d */
    /* loaded from: classes4.dex */
    public class C0340d implements IABConfig {
        C0340d(d dVar) {
        }

        @Override // com.jd.dynamic.base.interfaces.IABConfig
        public Map<String, Map<String, String>> getAbData() {
            Map<String, Map<String, Map<String, String>>> allConfig = JDMobileConfig.getInstance().getAllConfig();
            if (allConfig != null) {
                return allConfig.get("DynamicSdk");
            }
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public class e implements DeepDarkChangeManager.OnUIModeChangeListener {
        e(d dVar) {
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            DynamicSdk.getEngine().notifyDarkStatus(i2 == 1);
        }
    }

    /* loaded from: classes4.dex */
    public class f implements IConfig {
        f(d dVar) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getAppSource() {
            return CashierSdkGlobalConfig.CASHIER_SDK_SOURCE;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getConfig() {
            return null;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getJdPaySource() {
            return "0";
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getLbsBusinessId() {
            return "3ec559ecab741969546695d4c1f725ed";
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getLoginBusinessId() {
            return CashierSdkGlobalConfig.LOGIN_BUSINESS_ID;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getPayAppID() {
            IHostConfig hostConfig = DependInitializer.getHostConfig();
            return (hostConfig == null || !"api.m.jd.com".equalsIgnoreCase(hostConfig.getHost())) ? "android_app_beta" : "jd_android_app4";
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getPayAppKey() {
            IHostConfig hostConfig = DependInitializer.getHostConfig();
            return (hostConfig == null || !"api.m.jd.com".equalsIgnoreCase(hostConfig.getHost())) ? "6fg7weDfF6gh" : "e53jfgRgd7Hk";
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getQQAppId() {
            return "100273020";
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getQQCallBackName() {
            return CashierSdkGlobalConfig.QQ_CALL_BACK_NAME;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getRiskTokenBusinessId() {
            return CashierSdkGlobalConfig.RISK_TOKEN_BUSINESS_ID;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IConfig
        public String getWXAppId() {
            return "wxe75a2e68877315fb";
        }
    }

    /* loaded from: classes4.dex */
    public class g implements BackForegroundWatcher.BackForegroundListener {
        long a;

        g(d dVar) {
        }

        private void a() {
            if (SwitchQueryFetcher.getSwitchIntValue("mp_refresh_start_image", 0) < 1 || !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                return;
            }
            long j2 = this.a;
            if (j2 != 0 && j2 + (r0 * 60000) <= System.currentTimeMillis()) {
                try {
                    Intent intent = new Intent(JdSdk.getInstance().getApplication(), ADService.class);
                    intent.putExtra("foreToBackTime", this.a);
                    JdSdk.getInstance().getApplication().startService(intent);
                } catch (Throwable unused) {
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:74:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0132  */
        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onBackToForeground() {
            /*
                Method dump skipped, instructions count: 386
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.k.d.g.onBackToForeground():void");
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            this.a = System.currentTimeMillis();
            if (CaasKitHelper.getInstance().isCaasKitInit() && CaasKitHelper.getInstance().getCurrentState() == HwCaasUtils.CallState.ACTIVE_CALL && !CaasKitHelper.getInstance().isPausing()) {
                CaasKitHelper.getInstance().pauseShareSpecial();
            }
            OpenLinkTimeManager.getInstance().onForeToBackground();
            JDSecUtils.report("appForeToBackground", null);
            KeplerJumpUtils.cancelShow();
        }
    }

    /* loaded from: classes4.dex */
    public class h implements JDMoblieConfigListener {
        AtomicBoolean a = new AtomicBoolean(true);

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a(h hVar) {
            }

            @Override // java.lang.Runnable
            public void run() {
                AuraPreLoadBundleHelper.getInstance().preLoadBundleAtHome();
            }
        }

        h() {
            d.this = r2;
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
        public void onConfigUpdate() {
            if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "loadBundle", "delayTime", "");
                if (OKLog.D) {
                    OKLog.d("AuraPreLoadBundleHelper", "initPreLoadBundle-delayTime=" + config);
                }
                if (d.this.f11147c != null) {
                    d.this.f11147c.postDelayed(new a(this), ParseUtil.parseInt(config, 200));
                }
            }
            if (this.a.compareAndSet(true, false)) {
                com.jingdong.app.mall.g.f.c();
                ActivityNumController.updateConfig();
                d.this.e();
            }
            d.this.s();
        }
    }

    private String l() {
        Intent a2 = com.jingdong.app.mall.c.a();
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put("coldOrHot", (Object) "1");
            jDJSONObject.put("sourceApp", (Object) "");
            if (a2 != null) {
                jDJSONObject.put("openAppUrl", (Object) (a2.getData() != null ? a2.getData().toString() : ""));
            } else {
                jDJSONObject.put("startFromUrl", (Object) "3");
                jDJSONObject.put("openAppUrl", (Object) "");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jDJSONObject.toString();
    }

    private void m() {
        CashierSdkInitializer.initSdk(a().getApplicationContext(), new f(this));
    }

    private void n() {
        ServiceBind.bindService(com.jingdong.app.mall.j.b.a());
    }

    private void o() {
        boolean equals = JDMobileConfig.getInstance().getConfig("DynamicSdk", "common", "initFetch", "0").equals("1");
        boolean equals2 = JDMobileConfig.getInstance().getConfig("DynamicSdk", "common", "firstQeuryFetch", "1").equals("1");
        try {
            YogaInit.initYoga(a().getApplicationContext());
        } catch (Exception e2) {
            IExceptionHandler.DynamicExceptionData dynamicExceptionData = new IExceptionHandler.DynamicExceptionData();
            dynamicExceptionData.type = "YogaInit";
            dynamicExceptionData.systemCode = "jdApp";
            dynamicExceptionData.errorMsg = "JDApp init Yoga with exception";
            dynamicExceptionData.exception = e2;
            new r().handException(dynamicExceptionData);
        }
        DynamicSdk.init(DynamicSdk.newBuilder(a().getApplicationContext()).setAbConfig(new C0340d(this)).withAppType(CashierSdkGlobalConfig.CASHIER_SDK_SOURCE).withSystemCodes("search").withCacheDir("dynamic").useDebug(false).pkgType(3).useFetchAtInit(equals).useFetchAtFirstRequest(equals2).setLocalResourcePath("tempList").useLog(false).setCustomHost(new c(this)).setNetWorkRequest(new t()).setUniConfig(new u()).setAppRouter(new m()).setImageLoader(new s()).setDynamicMta(new DynamicMtaImpl()).setDarkSwitch(new q()).setCustomView(new CustomViewImpl()).setExceptionHandler(new r()).build());
        com.jingdong.app.mall.dynamicImpl.p.a();
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(new e(this));
        DynamicPrepareFetcher.prepareFetch(DynamicPrepareFetcher.KEY_PREPARE_MODEL_LAUNCH);
    }

    private void p() {
        AppPartnerUtil.saveOriginalPartner();
        if (!a().getSharedPreferences("privacy", 0).getBoolean("privacy_has_show", false) && TextUtils.isEmpty(LoginUserBase.getLoginUserName()) && !CommonBase.activityIsGuided(Constants.MAINACTIVITY_FULLNAME)) {
            ServerConfigFetcher.getFetcher().fetch(null, null);
        }
        r();
        JDDnsUtil.getInstance();
        JDMobileConfig.getInstance().forceCheckUpdate();
        DeviceFingerUtil.init();
        com.jingdong.app.mall.utils.p.b("App_StartUp", "", "", d.class.getName(), "", "", l(), true, true, true);
        com.jingdong.app.mall.open.c.a().b(a(), JDApp.getInstance().isLazyInit());
        ShoppingBaseController.setCommon(CommonUtilEx.getInstance());
        LoginUserBase.init();
        CommonBase.getJdSharedPreferences().edit().putLong("last_quest_time_UnifyRequestDataHolder", 0L).apply();
        JDReactAuraHelper.getInstance().setCommonInvokeInterface(JDReactManager.createCommonInvokeInterface(), JdSdk.getInstance().getApplication().getApplicationContext(), JdSdk.getInstance().getApplication(), new JDReactExtendHelperCallback());
        JDReactCommonPreloadManager.getInstance().setReactPackageFactory(JDReactManager.createReactPackageFactory());
        if (JDReactHelper.newInstance().isPreloadCommon()) {
            JDReactCommonPreloadManager.getInstance().setEnable(true);
            JDReactCommonPreloadManager.getInstance().preloadCommonBundle();
        }
        BackForegroundWatcher.getInstance().registerListener(new g(this));
        UnitTransformHelper.getInstance().setPhoneOrFlowCharge(new PhoneOrFlowCharge());
        try {
            if ("1".equals(ConfigUtil.getStringFromPreference("liveBundleSwitch", "0"))) {
                if (Log.D) {
                    Log.d("ProcessInit", "KEY_SWITCH_LIVE ON");
                }
                com.jingdong.app.mall.aura.e.n(AuraBundleInfos.getBundleNameFromBundleId(33));
            } else if (Log.D) {
                Log.d("ProcessInit", "KEY_SWITCH_LIVE OFF");
            }
        } catch (Throwable unused) {
            ExceptionReporter.reportLive(JDNetworkConstant.LIVE_LOAD_ERRCODE, "", "");
        }
        if (!LoginUserBase.hasLogin()) {
            UserUtil.getWJLoginHelper().getLoginConfig();
        }
        CommonNightModeUtils.optionsDefaultMode();
        q();
        JDLocationManager.getInstance().init();
        TencentMapInitializer.setAgreePrivacy(true);
        JDReactManager.init();
        com.jingdong.app.mall.g.f.c();
        Application application = JdSdk.getInstance().getApplication();
        WebUtils.initWeb();
        XRender.getInstance().initXRender();
        WebHybridUtils.initHybrid(false);
        if (UnAndroidUtils.mateXEasyClient(application) || UnAndroidUtils.mateXEasyClientNew(application)) {
            WebHybridUtils.loadBuildInConfig();
        }
        BridgeManager.registerPlugin();
        XWidgetManager.registerWidget();
        HybridSDK.registerAdapter(XRenderAdapter.NAME, XRenderAdapterImpl.class);
        f();
        try {
            JDSkinSDK.getInstance().setIsNeedLogined(false).setLoginState(LoginUserBase.hasLogin()).setAppID("jingdong").init(JdSdk.getInstance().getApplication());
        } catch (Exception unused2) {
        }
        com.jingdong.app.mall.utils.m.e(JdSdk.getInstance().getApplication());
        JDRiskHandleHelper.init();
    }

    private void q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.out");
        intentFilter.addAction("com.jingdong.action.user.login.in");
        a().registerReceiver(new WebSyncLoginReceiver(), intentFilter);
    }

    private void r() {
        JDMobileConfig.getInstance().registerListener(new h());
    }

    public void s() {
        try {
            boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "upgradeByWifiStatus", "enable", "0"));
            if (UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_SETTED_KEY, false, -1)) {
                return;
            }
            UpdateSharedPreferenceUtil.putBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, equals, 2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.app.mall.k.a, com.jingdong.app.mall.k.f
    public void onBaseContextAttached(Context context) {
        com.jingdong.app.mall.home.s.a.b().m("mainProcessInit", "onBaseContextAttached");
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(1, 1);
        }
        Thread.setDefaultUncaughtExceptionHandler(com.jingdong.app.mall.crash.g.a());
        super.onBaseContextAttached(context);
        SystemClock.elapsedRealtime();
        com.jingdong.app.mall.c.b();
        com.jingdong.app.mall.aura.e.j(a());
        com.jingdong.app.mall.home.s.a.b().l("mainProcessInit", "onBaseContextAttached");
    }

    @Override // com.jingdong.app.mall.k.a, com.jingdong.app.mall.k.f
    public void onCreate() {
        com.jingdong.app.mall.home.s.a.b().m("mainProcessInit", "onCreate");
        X5InitUtil.checkDisableX5Core();
        super.onCreate();
        b();
        Context applicationContext = a().getApplicationContext();
        if (SwitchQueryFetcher.getSwitchBooleanValue("x5InitInApp", false) || UnAndroidUtils.mateXEasyClient(applicationContext) || UnAndroidUtils.mateXEasyClientNew(applicationContext)) {
            WebUtils.fix64();
            if (JDPrivacyHelper.isAcceptPrivacy(applicationContext)) {
                X5InitUtil.preloadX5(a().getApplicationContext());
            }
        }
        d();
        new JDMtaPageEventIds().init();
        BaseApplication.openWakeLock();
        com.jingdong.sdk.deeplink.b.a().b(a().getApplicationContext());
        com.jingdong.app.mall.k.c.b().a(new com.jingdong.app.mall.k.j.b());
        com.jingdong.jdpush_new.a.c();
        com.jingdong.app.mall.aura.i.f();
        DeepLinkSwitch.getInstance().setSwitchListener(com.jingdong.app.mall.aura.i.c());
        AuraBundleConfig.getInstance().setConfigListener(com.jingdong.app.mall.aura.e.d());
        p();
        com.jingdong.jdsdk.c.a.a(a());
        h();
        com.jingdong.app.mall.aura.e.k(a());
        AuraConfig.registMHCallback(new a(this));
        DeepDarkChangeManager.getInstance().postValueActive();
        o();
        XView2Manager.getInstance().install(JdSdk.getInstance().getApplication());
        a().registerActivityLifecycleCallbacks(new b(this));
        n();
        m();
        BaseCoreHelper.getInstance().setApplication(a());
        com.jingdong.app.mall.home.s.a.b().l("mainProcessInit", "onCreate");
    }
}
