package com.jingdong.app.mall.aura;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.aura.DownGradeUtils;
import com.jingdong.aura.core.runing.resource.DelegateResourcesUtils;
import com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundFragment;
import com.jingdong.aura.sdk.provided.ui.IToastUtils;
import com.jingdong.aura.sdk.update.AuraUpdate;
import com.jingdong.aura.sdk.update.AuraUpdateConfig;
import com.jingdong.aura.sdk.update.config.IMobileConfig;
import com.jingdong.aura.sdk.update.provider.IUserIdProvider;
import com.jingdong.aura.sdk.update.report.CommonReporter;
import com.jingdong.aura.sdk.update.updater.CommonBundleInfoProvider;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.aura.wrapper.AuraInitializer;
import com.jingdong.aura.wrapper.listener.AuraEventListener;
import com.jingdong.common.apkcenter.ApkCenter;
import com.jingdong.common.apkcenter.ApkDownloadController;
import com.jingdong.common.auraSetting.AuraGlobalSetting;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes19.dex */
public class e {
    private static AuraInitializer a;
    private static final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c */
    public static boolean f7928c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a extends CommonBundleInfoProvider {
        a() {
        }

        @Override // com.jingdong.aura.sdk.update.updater.CommonBundleInfoProvider, com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
        public List<String> getBundleDownloadOrder() {
            return AuraBundleInfos.getBundleDownloadOrder();
        }

        @Override // com.jingdong.aura.sdk.update.updater.CommonBundleInfoProvider, com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
        public String getBundleNameFromUpdateID(String str) {
            return AuraBundleInfos.getBundleNameFromUpdateID(str);
        }

        @Override // com.jingdong.aura.sdk.update.updater.CommonBundleInfoProvider, com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
        public List<String> getProvidedWifiDownloadList() {
            ArrayList arrayList = new ArrayList();
            if (!DownGradeUtils.isDownGrade()) {
                arrayList.add(AuraBundleInfos.getUpdateIdFromBundleName(AuraBundleInfos.getBundleNameFromBundleId(86)));
            }
            Log.i("AuraControl", "ProvidedWifiDownloadList:" + arrayList);
            return arrayList;
        }

        @Override // com.jingdong.aura.sdk.update.updater.CommonBundleInfoProvider, com.jingdong.aura.sdk.update.updater.IBundleInfoProvider
        public String getUpdateIdFromBundleName(String str) {
            return AuraBundleInfos.getUpdateIdFromBundleName(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements IMobileConfig {
        b() {
        }

        @Override // com.jingdong.aura.sdk.update.config.IMobileConfig
        public boolean isCloseUpdate() {
            return DownGradeUtils.isDownGrade();
        }

        @Override // com.jingdong.aura.sdk.update.config.IMobileConfig
        public boolean isWifiAutoDownload() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c implements IUserIdProvider {
        c() {
        }

        @Override // com.jingdong.aura.sdk.update.provider.IUserIdProvider
        public String getUserId() {
            return LoginUserBase.getUserPin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class d implements AuraConfig.IApkcenterHelper {
        d() {
        }

        @Override // com.jingdong.aura.wrapper.AuraConfig.IApkcenterHelper
        public void deleteBundleDownloadCache(String str) {
            ApkCenter.deleteDownloadCache(AuraBundleInfos.getUpdateIdFromBundleName(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.aura.e$e */
    /* loaded from: classes19.dex */
    public class C0237e implements AuraGlobalSetting.IConfigurationFetcher {
        C0237e() {
        }

        @Override // com.jingdong.common.auraSetting.AuraGlobalSetting.IConfigurationFetcher
        public Configuration getConfiguration(Activity activity) {
            return DelegateResourcesUtils.getRawConfiguration(activity);
        }
    }

    /* loaded from: classes19.dex */
    public class f implements AuraEventListener {
        f() {
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraEventListener
        public void onCloseAura(String str, int i2, String str2, String str3) {
            if (JdSdk.getInstance().getApplication() == null) {
                return;
            }
            e.q("AuraMaiDianClose", str + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + str2, "AuraEventListener.onCloseAura", "" + PackageInfoUtil.getVersionCode());
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraEventListener
        public void onTrace(String str, int i2, String str2, String str3, Throwable th) {
            e.t(str, i2, str2, str3, th);
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraEventListener
        public void onTrace(String str, int i2, String str2, String str3, String str4, String str5, Throwable th) {
            AuraMonitorReporter.monitor(str, i2, str2, str3, str4, str5, th);
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraEventListener
        public void onTrace(String str, String str2, int i2, String str3, String str4) {
            if (JdSdk.getInstance().getApplication() == null) {
                return;
            }
            e.q(str, str2 + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + str3, str4, "" + PackageInfoUtil.getVersionCode());
        }
    }

    /* loaded from: classes19.dex */
    public class g implements AuraFragmentHelper.c {
        g() {
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public ArrayList<String> a(String str) {
            return AuraConfig.getNotPreparedProvidedBundles(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public Fragment b(ArrayList<String> arrayList, String str) {
            ProvidedBundleNotFoundFragment providedBundleNotFoundFragment = new ProvidedBundleNotFoundFragment();
            providedBundleNotFoundFragment.setNotPreparedBundles(arrayList, str);
            return providedBundleNotFoundFragment;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public String c(String str) {
            return AuraConfig.getBundleNameForComponet(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public void d(Activity activity) {
            try {
                AuraConfig.ensureActivityResources(activity);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public void e(String str, String str2, String str3, Throwable th) {
            if (str == null) {
                str = "";
            }
            AuraMonitorReporter.monitor("", -1, "AuraFragmentHelper", str, str2, str3, th);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraFragmentHelper.c
        public boolean isSwitchOpen(String str) {
            return DeepLinkSwitch.getInstance().isSwitchOpen(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class h implements AuraBundleConfig.a {
        h() {
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public ArrayList<String> a(String str) {
            return AuraConfig.getNotPreparedProvidedBundles(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public Fragment b(ArrayList<String> arrayList, String str) {
            ProvidedBundleNotFoundFragment providedBundleNotFoundFragment = new ProvidedBundleNotFoundFragment();
            providedBundleNotFoundFragment.setNotPreparedBundles(arrayList, str);
            return providedBundleNotFoundFragment;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public String c(String str) {
            return AuraConfig.getBundleNameForComponet(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public void d(Activity activity) {
            try {
                AuraConfig.ensureActivityResources(activity);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public void e(String str, File file) {
            try {
                AuraConfig.installBundleNative(str, file);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public long f(String str) {
            return AuraConfig.getOriVersionCode(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public void g() {
            e.b();
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public SharedPreferences getAuraSharedPreferences() {
            return JdSdk.getInstance().getApplication().getSharedPreferences("aura_update_config", 0);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public long getBundleVersionCode(String str) {
            return AuraConfig.getBundleVersionCode(str);
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public boolean h(String str) {
            try {
                e.n(str);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public String i() {
            return e.g();
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public boolean isBundlePrepared(String str) {
            boolean isBundlePrepered = AuraConfig.isBundlePrepered(str);
            if (!isBundlePrepered) {
                com.jingdong.app.mall.aura.j.e();
            }
            return isBundlePrepered;
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public List<Map<String, String>> j() {
            return AuraConfig.getProvidedBundleInfos();
        }

        @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.a
        public boolean k(String str) {
            return AuraConfig.isBundleLoaded(str);
        }
    }

    /* loaded from: classes19.dex */
    public class i implements Callable<Object> {

        /* renamed from: g */
        final /* synthetic */ String f7929g;

        /* renamed from: h */
        final /* synthetic */ String f7930h;

        /* renamed from: i */
        final /* synthetic */ String f7931i;

        /* renamed from: j */
        final /* synthetic */ String f7932j;

        i(String str, String str2, String str3, String str4) {
            this.f7929g = str;
            this.f7930h = str2;
            this.f7931i = str3;
            this.f7932j = str4;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            e.q(this.f7929g, this.f7930h, this.f7931i, this.f7932j);
            return null;
        }
    }

    /* loaded from: classes19.dex */
    public class j implements JDMoblieConfigListener {
        j() {
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
        public void onConfigUpdate() {
            try {
                if (DownGradeUtils.isDownGrade()) {
                    AuraConfig.setAuraMobileConfig("1");
                } else {
                    String config = JDMobileConfig.getInstance().getConfig("JDAuraEngine", "AuraMobileConfig", "AuraMobileConfig", "1");
                    Log.i("AuraControl", "update mobile config:" + config);
                    AuraConfig.setAuraMobileConfig(config);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class k implements IToastUtils {
        k() {
        }

        @Override // com.jingdong.aura.sdk.provided.ui.IToastUtils
        public void shortToast(Context context, String str) {
            ToastUtils.shortToast(context, str);
        }
    }

    private static void a(String str, String str2, String str3, String str4) {
        if (DownGradeUtils.isDownGrade()) {
            Log.i("AuraControl", "asyncSendExposureData X time not report");
            return;
        }
        Log.i("AuraControl", "asyncSendExposureData");
        f.f.c(new i(str, str2, str3, str4), f.f.f19368i);
    }

    public static void b() {
        AuraConfig.cleanAuraCache();
    }

    private static void c() {
        com.jingdong.app.mall.aura.internal.c.f().b();
        com.jingdong.app.mall.aura.h.d().a();
    }

    public static AuraBundleConfig.a d() {
        return new h();
    }

    private static AuraFragmentHelper.c e() {
        return new g();
    }

    public static String f() {
        return AuraConfig.getAuraVersion();
    }

    public static String g() {
        return AuraConfig.getInstalledBundlesVersion();
    }

    public static void h() {
        if (DownGradeUtils.isDownGrade()) {
            Log.i("AuraControl", "getNetRequestOnResume x time not request ");
            return;
        }
        Log.i("AuraControl", "getNetRequestOnResume");
        com.jingdong.app.mall.aura.j.e();
    }

    public static int i() {
        return AuraConfig.getResourceFactoryType();
    }

    public static void j(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append("init: state: ");
        AtomicBoolean atomicBoolean = b;
        sb.append(atomicBoolean.get());
        Log.i("AuraControl", sb.toString());
        if (atomicBoolean.get()) {
            return;
        }
        atomicBoolean.set(true);
        AuraBundleInfos.init("jingdong", JdSdk.getInstance().getApplication());
        AuraUpdate.initAuraServiceLoader();
        AuraConfig.setIsDebugBuildConfig(false);
        AuraConfig.enableLog(false);
        AuraConfig.setBundleAbi("armeabi-v7a");
        AuraConfig.setIsArm64(false);
        AuraConfig.setHostSupportAbiType(1);
        AuraConfig.registerPrivacyListener(new o(application));
        AuraConfig.registerMobileLogCallback(new com.jingdong.app.mall.aura.g());
        AuraConfig.registerMonitorConfigListener(new AuraMonitorConfig());
        AuraFragmentHelper.getInstance().setContext(application);
        AuraFragmentHelper.getInstance().registIAuraFragmentSetting(e());
        if (!ProcessUtil.isMainProcess() && !ProcessUtil.isPushProcess() && !ProcessUtil.isMantoProcess()) {
            Log.d("AuraControl", "isn't MainProcess or push process, so init Aura to false");
            AuraConfig.setEnabled(false);
            return;
        }
        Log.d("AuraControl", "is MainProcess or push process, will init Aura by config");
        s();
        AuraConfig.setClassNotFoundCallback(new com.jingdong.app.mall.aura.internal.a());
        boolean g2 = com.jingdong.app.mall.aura.internal.c.f().g();
        Log.d("AuraControl", "init Aura by config = " + g2);
        try {
            AuraConfig.setEnabled(g2);
            if (com.jingdong.jdsdk.config.Configuration.isBeta()) {
                if (CommonBase.getJdSharedPreferences().getBoolean("callOnTime", true)) {
                    AuraConfig.setAuraDebugTimeListener(com.jingdong.app.mall.crash.b.a());
                } else {
                    AuraConfig.setAuraDebugTimeListener(null);
                }
            }
            boolean l2 = l(application);
            if (l2) {
                f7928c = true;
                c();
            }
            AuraConfig.setAutoBundles(AuraBundleInfos.getAutoBundles());
            com.jingdong.app.mall.aura.h.d().f();
            a("AuraMaiDianServerConfig", String.valueOf(g2), "AuraControl.init", "" + PackageInfoUtil.getVersionCode());
            a("AuraMaiDianStartUp", String.valueOf(AuraConfig.isUseAura()), "AuraControl.init", "" + PackageInfoUtil.getVersionCode());
            if (AuraConfig.isUseAura()) {
                if (a == null) {
                    a = new AuraInitializer(application, application.getPackageName(), l2);
                }
                a.init();
                a.startUp(null);
            }
            if (l2) {
                ApkDownloadController.getInStance().cleanApkCenter();
            }
            if (m()) {
                b();
            }
            AuraConfig.setApkcenterHelper(new d());
            AuraState.updateAuraEngineState("success", "", null);
        } catch (Throwable th) {
            AuraConfig.setEnabled(false);
            a("AuraMaiDianClose", "", "AuraControl.init", "" + PackageInfoUtil.getVersionCode());
            String format = String.format("%s-%s", UUID.randomUUID(), Long.valueOf(System.currentTimeMillis()));
            AuraState.updateAuraEngineState("fail", format, th);
            AuraMonitorReporter.monitor("", 0, "initAuraEngine", format, "", "", th);
            th.printStackTrace();
        }
        com.jingdong.app.mall.aura.j.d(application);
        AuraGlobalSetting.setConfigurationFetcher(new C0237e());
    }

    public static void k(Application application) {
        r();
        try {
            String readInstallationId = StatisticsReportUtil.readInstallationId();
            AuraUpdateConfig.Builder userIdProvider = new AuraUpdateConfig.Builder(application).setAppKey("fba8ae5a5078417d90ae1355af234d4f").setAppSecret("93f59362cb30881af1e91d12d948b1ee").setUserIdProvider(new c());
            if (readInstallationId == null) {
                readInstallationId = "";
            }
            AuraUpdate.init(userIdProvider.setUuid(readInstallationId).setChannel(com.jingdong.jdsdk.config.Configuration.getProperty(com.jingdong.jdsdk.config.Configuration.PARTNER)).enableLog(false).setMobileConfig(new b()).setBundleInfoProvider(new a()).setDownloader(new m()).setReporter(new l()).setPrivacyFieldProvider(new com.jingdong.app.mall.aura.k()).setToastUtils(new k()).build());
            AuraState.updateAuraUpdateState("success", "", null);
        } catch (Throwable th) {
            String format = String.format("%s-%s", UUID.randomUUID(), Long.valueOf(System.currentTimeMillis()));
            AuraState.updateAuraUpdateState("fail", format, th);
            AuraMonitorReporter.monitor("", 0, "initAuraUpdate", format, "", "", th);
        }
    }

    private static boolean l(Application application) {
        if (ProcessUtil.isMainProcess()) {
            try {
                return application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionCode > application.getSharedPreferences("aura_config", 0).getInt("last_version_code", 0);
            } catch (Throwable th) {
                t("AuraControl.isNewVersion", 0, th.toString(), "", null);
                th.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private static boolean m() {
        if (ProcessUtil.isMainProcess()) {
            String oSName = BaseInfo.getOSName();
            String stringFromPreference = ConfigUtil.getStringFromPreference("storedSystemId", "-1");
            if (TextUtils.isEmpty(oSName)) {
                return false;
            }
            if (!TextUtils.isEmpty(stringFromPreference) && !"-1".equals(stringFromPreference)) {
                if (oSName.equals(stringFromPreference)) {
                    return false;
                }
                p(oSName);
                return true;
            }
            p(oSName);
            return false;
        }
        return false;
    }

    public static void n(String str) throws Exception {
        if (a != null) {
            AuraInitializer.loadBundle(str);
        }
    }

    public static void o() {
        try {
            AuraInitializer auraInitializer = a;
            if (auraInitializer != null) {
                auraInitializer.preInstallBundles();
            }
        } catch (Throwable th) {
            t("preInstallBundles failed", 0, "", "", th);
        }
    }

    private static void p(String str) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("storedSystemId", str);
        try {
            edit.apply();
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    public static void q(String str, String str2, String str3, String str4) {
        if (DownGradeUtils.isDownGrade()) {
            Log.i("AuraControl", "sendExposureData X time not report");
            return;
        }
        Log.i("AuraControl", "sendExposureData");
        JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplication(), str3, "", str4, str, str2, "", "", "");
    }

    private static void r() {
        try {
            if (DownGradeUtils.isDownGrade()) {
                AuraConfig.setAuraMobileConfig("1");
            } else {
                String config = JDMobileConfig.getInstance().getConfig("JDAuraEngine", "AuraMobileConfig", "AuraMobileConfig", "1");
                Log.i("AuraControl", "mobile config:" + config);
                AuraConfig.setAuraMobileConfig(config);
            }
            JDMobileConfig.getInstance().registerListener(new j());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void s() {
        AuraConfig.setAuraEventListener(new f());
    }

    public static void t(String str, int i2, String str2, String str3, Throwable th) {
        AuraMonitorReporter.monitor(str, i2, "AuraMonitor", "", str2, str3, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class l extends CommonReporter {
        l() {
        }

        @Override // com.jingdong.aura.sdk.update.report.CommonReporter, com.jingdong.aura.sdk.update.report.IReporter
        public void onException(String str, int i2, String str2, String str3, Throwable th) {
            e.t(str, i2, str2, str3, th);
        }

        @Override // com.jingdong.aura.sdk.update.report.CommonReporter, com.jingdong.aura.sdk.update.report.IReporter
        public void onTrace(String str, String str2, int i2, String str3, String str4) {
            e.q(str, str3, "UpdateListener.onEnd", "" + PackageInfoUtil.getVersionCode());
        }

        @Override // com.jingdong.aura.sdk.update.report.CommonReporter, com.jingdong.aura.sdk.update.report.IReporter
        public void onTrace(String str, String str2, String str3) {
            Log.i("AuraControl", "onTrace: eventId:" + str + " param:" + str2 + " from:" + str3);
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(PackageInfoUtil.getVersionCode());
            e.q(str, str2, "UpdateListener.onEnd", sb.toString());
        }
    }
}
