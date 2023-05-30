package com.jingdong.sdk.jdupgrade.a;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdupgrade.ApkDownloadCallback;
import com.jingdong.sdk.jdupgrade.BaseInfoProvider;
import com.jingdong.sdk.jdupgrade.DownloadView;
import com.jingdong.sdk.jdupgrade.JDMallUpgradeInfoCallBack;
import com.jingdong.sdk.jdupgrade.R;
import com.jingdong.sdk.jdupgrade.RemindView;
import com.jingdong.sdk.jdupgrade.UpgradeConfig;
import com.jingdong.sdk.jdupgrade.UpgradeDialogPopupRequest;
import com.jingdong.sdk.jdupgrade.VersionInfoCallback;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.j;
import java.io.File;

/* loaded from: classes7.dex */
public class c {
    private static final String a = "c";
    private static Application b;

    /* renamed from: c */
    private static UpgradeConfig f15041c;
    private static volatile boolean d;

    /* renamed from: e */
    private static volatile boolean f15042e;

    /* renamed from: f */
    private static BaseInfoProvider f15043f;

    /* loaded from: classes7.dex */
    public static class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15044g;

        /* renamed from: h */
        final /* synthetic */ File f15045h;

        /* renamed from: i */
        final /* synthetic */ ApkDownloadCallback f15046i;

        /* renamed from: j */
        final /* synthetic */ String f15047j;

        /* renamed from: k */
        final /* synthetic */ boolean f15048k;

        /* renamed from: com.jingdong.sdk.jdupgrade.a.c$a$a */
        /* loaded from: classes7.dex */
        class C0726a implements j.e {
            C0726a() {
                a.this = r1;
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(int i2) {
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(Throwable th, String str) {
                if (th != null) {
                    th.printStackTrace();
                }
                System.err.println("downloadApk errorCode:" + str);
                ApkDownloadCallback apkDownloadCallback = a.this.f15046i;
                if (apkDownloadCallback != null) {
                    apkDownloadCallback.onError();
                }
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onProgress(int i2, long j2, long j3) {
                ApkDownloadCallback apkDownloadCallback = a.this.f15046i;
                if (apkDownloadCallback != null) {
                    apkDownloadCallback.onProgress(i2, j2, j3);
                }
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onStart() {
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onSuccess(String str) {
                ApkDownloadCallback apkDownloadCallback = a.this.f15046i;
                if (apkDownloadCallback != null) {
                    apkDownloadCallback.onSuccess(str);
                }
            }
        }

        a(String str, File file, ApkDownloadCallback apkDownloadCallback, String str2, boolean z) {
            this.f15044g = str;
            this.f15045h = file;
            this.f15046i = apkDownloadCallback;
            this.f15047j = str2;
            this.f15048k = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.a(false, this.f15044g, this.f15045h.getAbsolutePath() + File.separator + com.jingdong.sdk.jdupgrade.a.j.c.b(this.f15044g) + ".apk", new C0726a(), true, this.f15047j, this.f15048k);
        }
    }

    public static String A() {
        BaseInfoProvider baseInfoProvider = f15043f;
        if (baseInfoProvider != null) {
            String netWorkType = baseInfoProvider.getNetWorkType();
            return !TextUtils.isEmpty(netWorkType) ? netWorkType : "";
        }
        return "";
    }

    public static int B() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getOsVersionCode() : Build.VERSION.SDK_INT;
    }

    public static String C() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getOsVersionName() : "";
    }

    public static int D() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getUpgradeCancelResId();
    }

    public static com.jingdong.sdk.jdupgrade.inner.ui.d E() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getUpgradeCancelTextStyle();
    }

    public static int F() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getUpgradeConfirmResId();
    }

    public static com.jingdong.sdk.jdupgrade.inner.ui.d G() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getUpgradeConfirmTextStyle();
    }

    public static int H() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getUpgradeContentResId();
    }

    public static com.jingdong.sdk.jdupgrade.inner.ui.d I() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getUpgradeContentTextStyle();
    }

    public static int J() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getUpgradeHeaderResId();
    }

    public static int K() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getUpgradeDialogResId();
    }

    public static com.jingdong.sdk.jdupgrade.inner.ui.d L() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getUpgradeTitleTextStyle();
    }

    public static boolean M() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isAutoDownloadWithWifi();
    }

    public static boolean N() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isAutoInstallAfterDownload();
    }

    public static boolean O() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isIgnoreUserRejectInUnlimitedCheck();
    }

    public static boolean P() {
        return f15042e;
    }

    public static boolean Q() {
        return d;
    }

    public static boolean R() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isPreEnvironment();
    }

    public static boolean S() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isUseCustomDownloadView();
    }

    public static boolean T() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isUseCustomRemindView();
    }

    public static boolean U() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return false;
        }
        return upgradeConfig.isShowToast();
    }

    public static int a() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getAcceptUpgradeType();
    }

    public static String a(String str) {
        return c() + CartConstant.KEY_YB_INFO_LINK + str + ".apk";
    }

    public static void a(Application application, UpgradeConfig upgradeConfig, BaseInfoProvider baseInfoProvider) {
        if (f15042e) {
            h.b(a, "init is called already");
        } else if (upgradeConfig == null || !upgradeConfig.isValid()) {
            h.b(a, "UpgradeConfig is null or invalid");
        } else if (application == null || !com.jingdong.sdk.jdupgrade.a.j.b.a(application)) {
            h.b(a, "not main process");
        } else {
            f15043f = baseInfoProvider;
            f15042e = true;
            b = application;
            f15041c = upgradeConfig;
            com.jingdong.sdk.jdupgrade.a.j.g.a(application, upgradeConfig);
            e.c();
        }
    }

    public static void a(JDMallUpgradeInfoCallBack jDMallUpgradeInfoCallBack) {
        d = true;
        new f(jDMallUpgradeInfoCallBack).b();
    }

    public static void a(VersionInfoCallback versionInfoCallback) {
        new g(versionInfoCallback).b();
    }

    public static void a(String str, String str2, boolean z, ApkDownloadCallback apkDownloadCallback) {
        if (apkDownloadCallback != null) {
            apkDownloadCallback.onStart();
        }
        if (TextUtils.isEmpty(str2)) {
            h.c("", "fileMd5 is null");
            if (apkDownloadCallback != null) {
                apkDownloadCallback.onError();
            }
        } else if (j() == null || TextUtils.isEmpty(str)) {
            h.b("", "downloadApk cxt or url is null");
            if (apkDownloadCallback != null) {
                apkDownloadCallback.onError();
            }
        } else {
            File externalCacheDir = j().getExternalCacheDir();
            if (externalCacheDir != null) {
                j.c().execute(new a(str, externalCacheDir, apkDownloadCallback, str2, z));
                return;
            }
            h.b("", "downloadApk downloadDir is null");
            if (apkDownloadCallback != null) {
                apkDownloadCallback.onError();
            }
        }
    }

    public static void a(boolean z) {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return;
        }
        upgradeConfig.setAutoDownloadWithWifi(z);
    }

    public static boolean a(com.jingdong.sdk.jdupgrade.a.i.j jVar) {
        return jVar != null && jVar.e() && O();
    }

    public static String b() {
        UpgradeConfig upgradeConfig;
        return (!f15042e || (upgradeConfig = f15041c) == null) ? "" : upgradeConfig.getAppId();
    }

    public static String c() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppPackageName() : "";
    }

    public static String d() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppPartnerName() : "";
    }

    public static String e() {
        UpgradeConfig upgradeConfig;
        return (!f15042e || (upgradeConfig = f15041c) == null) ? "" : upgradeConfig.getAppSecret();
    }

    public static String f() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppUUID() : "";
    }

    public static String g() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppUserID() : "";
    }

    public static String h() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppVersionCode() : "";
    }

    public static String i() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getAppVersionName() : "";
    }

    public static Context j() {
        return b;
    }

    public static DownloadView k() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getCustomDownloadView();
    }

    public static RemindView l() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getCustomRemindView();
    }

    public static String m() {
        BaseInfoProvider baseInfoProvider = f15043f;
        if (baseInfoProvider != null) {
            String deviceBrandName = baseInfoProvider.getDeviceBrandName();
            return !TextUtils.isEmpty(deviceBrandName) ? deviceBrandName : "";
        }
        return "";
    }

    public static String n() {
        BaseInfoProvider baseInfoProvider = f15043f;
        if (baseInfoProvider != null) {
            String deviceModelName = baseInfoProvider.getDeviceModelName();
            return !TextUtils.isEmpty(deviceModelName) ? deviceModelName : "";
        }
        return "";
    }

    public static String o() {
        BaseInfoProvider baseInfoProvider = f15043f;
        return baseInfoProvider != null ? baseInfoProvider.getDeviceSupportedABIs() : "";
    }

    public static Drawable p() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getDialogBackgroundDrawable();
    }

    public static int[] q() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getDialogLayoutMargin();
    }

    public static UpgradeDialogPopupRequest r() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getDialogPopupRequest();
    }

    public static File s() {
        return com.jingdong.sdk.jdupgrade.a.j.d.a();
    }

    public static int t() {
        UpgradeConfig upgradeConfig = f15041c;
        return upgradeConfig != null ? upgradeConfig.getDownloadRetryInterval() : UpgradeConfig.MAX_RETRY_TIMES;
    }

    public static int u() {
        UpgradeConfig upgradeConfig = f15041c;
        return upgradeConfig != null ? upgradeConfig.getDownloadRetryTimes() : UpgradeConfig.MAX_RETRY_TIMES;
    }

    public static String v() {
        UpgradeConfig upgradeConfig = f15041c;
        return upgradeConfig == null ? "" : upgradeConfig.getDownloadTips();
    }

    public static Integer w() {
        int i2;
        UpgradeConfig upgradeConfig;
        Context j2 = j();
        if (f15042e && (upgradeConfig = f15041c) != null) {
            int intValue = upgradeConfig.getLogoId().intValue();
            try {
                if (j2.getResources().getDrawable(intValue) != null) {
                    return Integer.valueOf(intValue);
                }
                int i3 = j2.getApplicationInfo().icon;
                if (i3 != 0) {
                    f15041c.setLogoId(Integer.valueOf(i3));
                    return Integer.valueOf(i3);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                h.b(a, th.getMessage());
            }
        }
        if (j2 == null || j2.getApplicationInfo() == null || (i2 = j2.getApplicationInfo().icon) == 0) {
            i2 = R.drawable.default_upgrade_notification_icon;
        } else {
            f15041c.setLogoId(Integer.valueOf(i2));
        }
        return Integer.valueOf(i2);
    }

    public static int x() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getInstallCancelResId();
    }

    public static int y() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return 0;
        }
        return upgradeConfig.getInstallConfirmResId();
    }

    public static Drawable z() {
        UpgradeConfig upgradeConfig;
        if (!f15042e || (upgradeConfig = f15041c) == null) {
            return null;
        }
        return upgradeConfig.getLoadingProgressBarDrawable();
    }
}
