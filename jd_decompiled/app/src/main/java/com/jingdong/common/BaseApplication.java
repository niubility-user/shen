package com.jingdong.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import com.handmark.pulltorefresh.library.IPullToRefreshConfig;
import com.handmark.pulltorefresh.library.PullToRefreshConfig;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.global.theme.OnThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jd.lib.un.utils.config.OnConfigListener;
import com.jd.lib.un.utils.config.OnDeviceInfo;
import com.jd.lib.un.utils.config.UnUtilsConfig;
import com.jd.lib.un.voice.VoiceConfig;
import com.jd.lib.un.voice.asr.OnOptListener;
import com.jd.lib.un.voice.asr.UnAsrHelper;
import com.jd.sec.LogoManager;
import com.jd.security.jdguard.b;
import com.jd.security.jdguard.c;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.utils.j;
import com.jingdong.common.auraSetting.AuraGlobalSetting;
import com.jingdong.common.broadcastReceiver.StorageReceiver;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.eldermode.JDElderModeInitializer;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.guard.JDGuardHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.JDNetworkDependencyFactory;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.uniutil.UnQRCodeUtils;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.BitmapkitUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.lib.monitor.MonitorInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.listener.OnDomainResolveListener;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.un.lib.popup.JDTopPopupWindowHelper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BaseApplication {
    public static boolean ASYNC_DEX_ENABLE = true;
    private static final String TAG = "BaseApplication";
    private static Class errorActivityClass;
    private static Handler mHandler;
    public static long startRealTime;
    public static long startTime;
    private static Thread uiThread;
    private static PowerManager.WakeLock wakeLock;

    public static void closeWakeLock() {
        PowerManager.WakeLock wakeLock2 = wakeLock;
        if (wakeLock2 != null) {
            try {
                wakeLock2.release();
            } catch (Throwable unused) {
            }
            wakeLock = null;
        }
    }

    public static String getAId() {
        return BaseInfo.getAndroidId();
    }

    public static Class getErrorActivityClass() {
        return errorActivityClass;
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    @Deprecated
    public static Application getInstance() {
        return JdSdk.getInstance().getApplication();
    }

    public static ProgressBar getLoadingProgressBar() {
        return new JDProgressBar(JdSdk.getInstance().getApplication());
    }

    public static View getLottieLoadingView() {
        if (Build.VERSION.SDK_INT >= 16 && ABTestUtils.isLottieEnable()) {
            LottieLoadingView lottieLoadingView = new LottieLoadingView(JdSdk.getInstance().getApplicationContext());
            if (lottieLoadingView.initSuccess()) {
                if (OKLog.D) {
                    OKLog.d("LottieLoadingView", "switch is open and LottieLoadingView init success");
                }
                return lottieLoadingView;
            }
        }
        return getLoadingProgressBar();
    }

    public static Thread getUiThread() {
        return uiThread;
    }

    public static void initApp() {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.BaseApplication.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    StorageReceiver.registerReceiver(JdSdk.getInstance().getApplication());
                } catch (Throwable unused) {
                }
            }
        }, "asyncInit");
        unificationInit();
        try {
            jdGuardInit();
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        BitmapkitUtils.a = JdSdk.getInstance().getApplication();
        JDHttpDnsToolkit.initialize(JDHttpDnsToolkit.newBuilder(JdSdk.getInstance().getApplication()).setLogEnable(false).enableSafeMode(true).setReporter(JDNetworkDependencyFactory.getHttpDnsReporter()).setFailController(JDNetworkDependencyFactory.getHttpDnsFailureController()).setStatRepoter(JDNetworkDependencyFactory.getHttpDnsStatReporter()).setKeyParamProvider(JDNetworkDependencyFactory.getKeyParamProvider()).setIRestrictController(JDNetworkDependencyFactory.getRestrictController()));
        JDHttpTookit.initialize(JDHttpTookit.newBuilder(JdSdk.getInstance().getApplication()).setAppProxy(JDNetworkDependencyFactory.getAppProxy()).setRuntimeConfigImpl(JDNetworkDependencyFactory.getRuntimeConfigImpl()).setStatInfoConfigImpl(JDNetworkDependencyFactory.getStatInfoConfigImpl()).setSignatureHandler(JDNetworkDependencyFactory.getSignatureHandler()).setLoginUserControllerImpl(JDNetworkDependencyFactory.getLoginUserControllerImpl()).setExceptionReporter(JDNetworkDependencyFactory.getExceptionReportDelegate()).setNetworkControllerImpl(JDNetworkDependencyFactory.getNetworkControllerImpl()).setExternalDebugConfigImpl(JDNetworkDependencyFactory.getExternalDebugConfigImpl()).setCustomUIComponentImpl(JDNetworkDependencyFactory.getCustomUIComponentDependency()).setHttpDnsController(JDNetworkDependencyFactory.getHttpDnsControllerImpl()).setPhcEncryptionPlugin(JDNetworkDependencyFactory.getPhcEncryptionPlugin()).setHttpErrorDialogImpl(JDNetworkDependencyFactory.getHttpErrorAlertDialog()).setGateWayRespHeaderListener(JDNetworkDependencyFactory.getGatewayRespHeaderListenerImpl()).setGuardVerifyPlugin(JDNetworkDependencyFactory.getGuardVerifyPlugin()).setJDGuardPlugin(JDGuardHelper.getJDGuardPlugin()).setDownloadDomainResolver(JDNetworkDependencyFactory.getDownloadDomainResolver()).setBusinessJsonCodeListener(JDNetworkDependencyFactory.getBusinessJsonCodeEventListener()).setHardGuardVerifyPlugin(JDNetworkDependencyFactory.getHardGuardVerifyPlugin()).isPrintLog(Configuration.isBeta()).build());
        JDHttpDnsToolkit.getInstance().addDomainResolveListener("api.m.jd.com", new OnDomainResolveListener() { // from class: com.jingdong.common.BaseApplication.3
            @Override // com.jingdong.sdk.jdhttpdns.listener.OnDomainResolveListener
            public void onResolve(IpModel ipModel) {
                JDNetworkDependencyFactory.getHttpDnsControllerImpl().onHttpDnsReceived(new com.jingdong.common.network.IpModel(ipModel.host, ipModel.master, ipModel.v4, ipModel.v6, ipModel.updateTime, ipModel.ttl));
            }
        });
        JDHttpDnsToolkit.getInstance().addDomainResolveListener("apk.360buyimg.com", new OnDomainResolveListener() { // from class: com.jingdong.common.BaseApplication.4
            @Override // com.jingdong.sdk.jdhttpdns.listener.OnDomainResolveListener
            public void onResolve(IpModel ipModel) {
                if (OKLog.D) {
                    OKLog.d("httpDns", "\u76d1\u542c\u5230\u57df\u540d-> " + ipModel.host + " , masterVip-> " + ipModel.master);
                }
                j.c().g(true);
            }
        });
        JDElderModeInitializer.initialize(JdSdk.getInstance().getApplication());
        getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.BaseApplication.5
            @Override // java.lang.Runnable
            public void run() {
                BaseApplication.closeWakeLock();
            }
        }, 60000L);
    }

    public static void initOnCreateInBase() {
        if (ProcessUtil.isMainProcess()) {
            ABTestUtils.abTest900Style = TextUtils.equals("1", JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "900Style", "is900UIStyle"));
        }
        MonitorInfo.setRunStage(0);
        initStartTime();
        setUiThread(Thread.currentThread());
        JDJSON.init(false);
        try {
            DPIUtil.setDensity(BaseInfo.getDensity());
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        initApp();
    }

    public static void initStartTime() {
        startTime = SystemClock.uptimeMillis();
        startRealTime = SystemClock.elapsedRealtime();
    }

    public static void jdGuardInit() {
        c.InterfaceC0212c interfaceC0212c = new c.InterfaceC0212c() { // from class: com.jingdong.common.BaseApplication.1
            @Override // com.jd.security.jdguard.c.InterfaceC0212c
            public String getDfpEid() {
                return LogoManager.getInstance(JdSdk.getInstance().getApplication()).getLogo();
            }

            @Override // com.jd.security.jdguard.d.c.e
            public Map<String, String> getEvaConfigs() {
                return JDGuardHelper.getEvaConfigs();
            }

            @Override // com.jd.security.jdguard.core.d
            public Map<String, String> getJDGConfigs() {
                return JDGuardHelper.getJDGConfigs();
            }

            @Override // com.jd.security.jdguard.c.InterfaceC0212c
            public void onSendStreamData(HashMap<String, String> hashMap, String str, String str2, int i2) {
                JDGuardHelper.sendStreamData(hashMap, str, str2, i2);
            }
        };
        c.b bVar = new c.b();
        bVar.p(JdSdk.getInstance().getApplication());
        bVar.m(JDGuardHelper.getAppKey());
        bVar.u(JDGuardHelper.getPicName());
        bVar.v(JDGuardHelper.getSecName());
        bVar.r(Configuration.isBeta());
        bVar.o(interfaceC0212c);
        bVar.q(JDGuardHelper.needEventPush());
        bVar.s(JDGuardHelper.getEventPushPercentage());
        bVar.t(true);
        b.g(bVar.n());
    }

    public static void openWakeLock() {
        try {
            PowerManager.WakeLock newWakeLock = ((PowerManager) JdSdk.getInstance().getApplication().getSystemService("power")).newWakeLock(1, "MyWakelockTag");
            wakeLock = newWakeLock;
            newWakeLock.acquire();
        } catch (Throwable unused) {
        }
    }

    public static void setErrorActivityClass(Class cls) {
        errorActivityClass = cls;
    }

    public static void setUiThread(Thread thread) {
        if (uiThread == null) {
            uiThread = thread;
        }
    }

    private static void unificationInit() {
        a.g().o(JdSdk.getInstance().getApplication());
        a.g().u(HostConfig.getInstance().getHost(HostConstants.COMMON_NEW_HOST));
        a.g().t(new a.InterfaceC0167a() { // from class: com.jingdong.common.BaseApplication.6
            @Override // com.jd.lib.un.business.widget.a.InterfaceC0167a
            public boolean enable() {
                return LoginUserBase.hasLogin();
            }
        });
        a.g().s(new com.jd.lib.un.business.widget.b() { // from class: com.jingdong.common.BaseApplication.7
            @Override // com.jd.lib.un.business.widget.b
            public boolean isDarkMode() {
                return DeepDarkChangeManager.getInstance().getUIMode() == 1;
            }

            @Override // com.jd.lib.un.business.widget.b
            public boolean isElderMode() {
                return JDElderModeUtils.isElderMode();
            }
        });
        ToastUtils.init(JdSdk.getInstance().getApplication(), new ToastUtils.OnThemeConfig() { // from class: com.jingdong.common.BaseApplication.8
            @Override // com.jingdong.sdk.jdtoast.ToastUtils.OnThemeConfig
            public boolean isElder() {
                String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "jdToastAutoElder");
                if (TextUtils.isEmpty(config)) {
                    config = "1";
                }
                if (TextUtils.equals(config, "1")) {
                    return JDElderModeUtils.isElderMode();
                }
                return false;
            }
        });
        PullToRefreshConfig.getInstance().setiPullToRefreshConfig(new IPullToRefreshConfig() { // from class: com.jingdong.common.BaseApplication.9
            @Override // com.handmark.pulltorefresh.library.IPullToRefreshConfig
            public boolean lottieEnable() {
                return ABTestUtils.isLottieEnable();
            }
        });
        UnWidgetThemeController.getInstance().setOnThemeConfig(new OnThemeConfig() { // from class: com.jingdong.common.BaseApplication.10
            @Override // com.jd.lib.un.global.theme.OnThemeConfig
            public boolean isDarkModel() {
                return DeepDarkChangeManager.getInstance().getUIMode() == 1;
            }

            @Override // com.jd.lib.un.global.theme.OnThemeConfig
            public boolean isElderModel() {
                return JDElderModeUtils.isElderMode();
            }

            @Override // com.jd.lib.un.global.theme.OnThemeConfig
            public boolean isVoiceEnable() {
                return false;
            }
        });
        UnWidgetThemeController.getInstance().setIOptionConfig(new com.jd.lib.un.global.a() { // from class: com.jingdong.common.BaseApplication.11
            @Override // com.jd.lib.un.global.a
            public Typeface getTypeface(Context context, int i2) {
                if (i2 == 1) {
                    return FontsUtil.getTypeFace(context, 4097);
                }
                if (i2 == 0) {
                    return FontsUtil.getTypeFace(context, 4099);
                }
                return FontsUtil.getTypeFace(context);
            }

            @Override // com.jd.lib.un.global.a
            public boolean isDialogAutoElder() {
                String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "jdDialogAutoElder");
                if (TextUtils.isEmpty(config)) {
                    config = "1";
                }
                return TextUtils.equals(config, "1");
            }

            @Override // com.jd.lib.un.global.a
            public void jdRouter(String str) {
                Uri parse;
                if (TextUtils.isEmpty(str) || (parse = Uri.parse(str.trim())) == null) {
                    return;
                }
                String scheme = parse.getScheme();
                if (TextUtils.isEmpty(scheme)) {
                    return;
                }
                String lowerCase = scheme.toLowerCase();
                if (TextUtils.equals(lowerCase, JDTopPopupWindowHelper.CUSTOM_ROUTER_TYPE)) {
                    JDRouter.to(JdSdk.getInstance().getApplication(), str).open();
                } else if (TextUtils.equals(lowerCase, "https")) {
                    DeepLinkMHelper.startWebActivity(JdSdk.getInstance().getApplicationContext(), str);
                } else if (JumpUtil.isJdScheme(lowerCase)) {
                    new OpenAppJumpBuilder.Builder(parse).build().jump(JdSdk.getInstance().getApplication());
                }
            }

            @Override // com.jd.lib.un.global.a
            public void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
                JDMtaUtils.sendClickDataWithExt(context, str, null, null, str4, str2, null, null, str3, null);
            }

            @Override // com.jd.lib.un.global.a
            public Bitmap qrCode(String str) {
                return UnQRCodeUtils.createQRCode(str);
            }

            @Override // com.jd.lib.un.global.a
            public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
                JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, null);
            }
        });
        UnUtilsConfig.getInstance().init(new OnConfigListener() { // from class: com.jingdong.common.BaseApplication.12
            @Override // com.jd.lib.un.utils.config.OnConfigListener
            public Application getApplication() {
                return JdSdk.getInstance().getApplication();
            }

            @Override // com.jd.lib.un.utils.config.OnConfigListener
            public android.content.res.Configuration getConfiguration(Activity activity) {
                return AuraGlobalSetting.getNormalConfiguration(activity);
            }
        });
        UnAsrHelper.optListener = new OnOptListener() { // from class: com.jingdong.common.BaseApplication.13
            @Override // com.jd.lib.un.voice.asr.OnOptListener
            public void clickMta(String str, String str2) {
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), str, "", "", "", "", "", "", str2, null);
            }
        };
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        VoiceConfig.getInstance().setId(applicationContext.getString(R.string.un_voice_id)).setTtsKey(applicationContext.getString(R.string.un_voice_key)).setTtsS(applicationContext.getString(R.string.un_voice_value));
        UnUtilsConfig.getInstance().setOnDeviceInfo(new OnDeviceInfo() { // from class: com.jingdong.common.BaseApplication.14
            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public float getDensity() {
                return BaseInfo.getDensity();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public int getDensityDpiInt() {
                return BaseInfo.getDensityDpiInt();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public String getDeviceManufacture() {
                return BaseInfo.getDeviceManufacture();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public String getDeviceModel() {
                return BaseInfo.getDeviceModel();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public String getDeviceProductName() {
                return BaseInfo.getDeviceProductName();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public float getScaledDensity() {
                return BaseInfo.getScaledDensity();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public int getScreenHeight() {
                return BaseInfo.getScreenHeight2();
            }

            @Override // com.jd.lib.un.utils.config.OnDeviceInfo
            public int getScreenWidth() {
                return BaseInfo.getScreenWidth2();
            }
        });
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal == null || TextUtils.isEmpty(addressGlobal.getSaveBusiness())) {
            return;
        }
        addressGlobal.setSaveBusiness("");
        AddressUtil.updateAddressGlobal(addressGlobal, false);
    }
}
