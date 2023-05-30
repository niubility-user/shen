package com.jingdong.app.mall.aura;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.aura.DownGradeUtils;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes19.dex */
public class AuraMonitorReporter {
    private static final String AURA_BI_UPLOAD_CRASH = "AuraMaiDianUploadCrash";
    private static final String AURA_MONITOR_KEY = "AuraMonitor";
    private static final String AURA_MONITOR_NAMESPACE = "AuraMonitor";
    private static final String AURA_SWITCH_CLOSE = "1";
    private static final String AURA_SWITCH_OPEN = "2";
    private static final String AURA_UFO_PAGE = "bundle_activity_not_found";
    private static final ExecutorService EXECUTOR;
    private static final ThreadFactory FACTORY;
    private static final String MONITOR_BUNDLE_CLASSLOADER_V2_WHITE_LIST = "bundleClassLoaderWhiteList";
    private static final String MONITOR_CHECK_INSTALL_BUNDLE_BY_BUNDLE_NAME_WHITE_LIST = "checkInstallBundleByBundleNameWhiteList";
    private static final String MONITOR_CHECK_INSTALL_BUNDLE_WHITE_LIST = "checkInstallBundleIfNeedWhiteList";
    private static final String MONITOR_FRAGMENT_HELPER_WHITE_LIST = "auraFragmentHelperWhiteList";
    private static final String MONITOR_LOAD_BUNDLE_WHITE_LIST = "classLoadFromBundleWhiteList";
    private static final String MONITOR_LOAD_CLASS_FROM_INSTALL_BUNDLE_WHITE_LIST = "loadClassFromInstalledBundlesWhiteList";
    private static final String MONITOR_LOCATION_NULL_CHECK_WHITE_LIST = "bundleLocationNullCheckWhiteList";
    private static final String MONITOR_PROVIDED_WHITE_LIST = "providedWhiteList";
    private static final String MONITOR_READ_BUNDLE_META_WHITE_LIST = "readBundleMetaWhiteList";
    private static final String MONITOR_RESTORE_BUNDLE_WHITE_LIST = "restoreBundleWhiteList";
    private static final String MONITOR_SO_MD5_CONFIG_EMPTY_CHECK_WHITE_LIST = "soMd5ConfigEmptyCheckWhiteList";
    private static final String MONITOR_SWITCH = "monitorSwitch";
    private static final String MONITOR_TYPE_BUNDLE_CLASSLOADER_V2 = "BundleClassLoaderV2";
    private static final String MONITOR_TYPE_CHECK_INSTALL_BUNDLE = "checkInstallBundleIfNeed";
    private static final String MONITOR_TYPE_CHECK_INSTALL_BUNDLE_BY_BUNDLE_NAME = "checkInstallBundleByBundleName";
    private static final String MONITOR_TYPE_FRAGMENT_HELPER = "AuraFragmentHelper";
    private static final String MONITOR_TYPE_LOAD_BUNDLE = "ClassLoadFromBundle";
    private static final String MONITOR_TYPE_LOAD_CLASS_FROM_INSTALL_BUNDLE = "loadClassFromInstalledBundles";
    private static final String MONITOR_TYPE_LOCATION_NULL_CHECK = "bundleLocationNullCheck";
    private static final String MONITOR_TYPE_PROVIDED_BUNDLE_INSTALL = "ProvidedBundleInstall";
    private static final String MONITOR_TYPE_READ_BUNDLE_META = "readBundleMeta";
    private static final String MONITOR_TYPE_RESTORE_BUNDLE = "RestoreBundle";
    private static final String MONITOR_TYPE_SO_MD5_CONFIG_CHECK = "soMd5ConfigCheck";
    private static final String MONITOR_TYPE_SO_MD5_CONFIG_EMPTY_CHECK = "soMd5ConfigEmptyCheck";
    private static final String MONITOR_TYPE_SWITCH = "monitorTypeSwitch";
    private static final String MONITOR_TYPE_UFO_PAGE = "UFOPage";
    private static final String MONITOR_TYPE_WHITE_LIST = "typeWhiteList";
    private static final String MONITOR_UFO_WHITE_LIST = "ufoWhiteList";
    private static final String SO_MD5_CONFIG_EMPTY_CHECK_WHITE_LIST = "libJFace.so,libJdArMakeup.so,libJdArMakeupExtension.so,libJdFitShoes.so,libhuawei_arengine_ndk.so";
    private static final String TAG = "AuraMonitorReporter";

    static {
        b bVar = new ThreadFactory() { // from class: com.jingdong.app.mall.aura.b
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return AuraMonitorReporter.b(runnable);
            }
        };
        FACTORY = bVar;
        EXECUTOR = Executors.newSingleThreadExecutor(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(String str, int i2, String str2, String str3, String str4, String str5, Throwable th) {
        String str6;
        String str7;
        String str8;
        HashMap hashMap;
        String str9;
        String str10;
        String str11;
        try {
            Log.d(TAG, "bundleName:" + str + " bundleVersion:" + i2 + " type:" + str2 + " flag:" + str3 + " info:" + str4 + " from:" + str5);
            str6 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_SWITCH, "1");
            StringBuilder sb = new StringBuilder();
            sb.append("AuraMonitor monitorSwitch:");
            sb.append(str6);
            Log.d(TAG, sb.toString());
            try {
                if (!TextUtils.isEmpty(str6) && !"1".equals(str6)) {
                    String config = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_TYPE_SWITCH, "2");
                    String config2 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_TYPE_WHITE_LIST, "");
                    ArrayList arrayList = TextUtils.isEmpty(config2) ? null : new ArrayList(Arrays.asList(config2.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "typeWhiteList:" + arrayList);
                    if (!TextUtils.isEmpty(config) && "2".equals(config) && arrayList != null && !arrayList.contains(str2)) {
                        Log.d(TAG, "don't report type:" + str2);
                        return;
                    }
                    String config3 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_UFO_WHITE_LIST, "");
                    ArrayList arrayList2 = TextUtils.isEmpty(config3) ? null : new ArrayList(Arrays.asList(config3.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "ufoWhiteList: " + arrayList2);
                    if (MONITOR_TYPE_UFO_PAGE.equals(str2) && arrayList2 != null && !arrayList2.contains(str3)) {
                        Log.d(TAG, "monitor ufo page don't report, flag:" + str3);
                        return;
                    }
                    String config4 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_PROVIDED_WHITE_LIST, "");
                    ArrayList arrayList3 = TextUtils.isEmpty(config4) ? null : new ArrayList(Arrays.asList(config4.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "providedWhiteList: " + arrayList3);
                    if (MONITOR_TYPE_PROVIDED_BUNDLE_INSTALL.equals(str2) && arrayList3 != null && !arrayList3.contains(str3)) {
                        Log.d(TAG, "monitor provided bundle install, don't report, flag:" + str3);
                        return;
                    }
                    String config5 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_RESTORE_BUNDLE_WHITE_LIST, "");
                    ArrayList arrayList4 = TextUtils.isEmpty(config5) ? null : new ArrayList(Arrays.asList(config5.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "restoreBundleWhiteList: " + arrayList4);
                    if (MONITOR_TYPE_RESTORE_BUNDLE.equals(str2) && arrayList4 != null && !arrayList4.contains(str3)) {
                        Log.d(TAG, "monitor restore bundle, don't report, flag:" + str3);
                        return;
                    }
                    String config6 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_SO_MD5_CONFIG_EMPTY_CHECK_WHITE_LIST, SO_MD5_CONFIG_EMPTY_CHECK_WHITE_LIST);
                    ArrayList arrayList5 = TextUtils.isEmpty(config6) ? null : new ArrayList(Arrays.asList(config6.split(DYConstants.DY_REGEX_COMMA)));
                    if (MONITOR_TYPE_SO_MD5_CONFIG_EMPTY_CHECK.equals(str2) && arrayList5 != null && arrayList5.contains(str3)) {
                        Log.d(TAG, "monitor so md5 check, don't report, flag:" + str3);
                        return;
                    }
                    String config7 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_BUNDLE_CLASSLOADER_V2_WHITE_LIST, "");
                    ArrayList arrayList6 = TextUtils.isEmpty(config7) ? null : new ArrayList(Arrays.asList(config7.split(DYConstants.DY_REGEX_COMMA)));
                    if (MONITOR_TYPE_BUNDLE_CLASSLOADER_V2.equals(str2) && arrayList6 != null && !arrayList6.contains(str3)) {
                        Log.d(TAG, "public bundle class loader, don't report, flag:" + str3);
                        return;
                    }
                    String config8 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_LOAD_BUNDLE_WHITE_LIST, "");
                    ArrayList arrayList7 = TextUtils.isEmpty(config8) ? null : new ArrayList(Arrays.asList(config8.split(DYConstants.DY_REGEX_COMMA)));
                    if (MONITOR_TYPE_LOAD_BUNDLE.equals(str2) && arrayList7 != null && !arrayList7.contains(str3)) {
                        Log.d(TAG, "load bundle, don't report, flag:" + str3);
                        return;
                    }
                    String config9 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_FRAGMENT_HELPER_WHITE_LIST, "com.jd.lib.personal.view.fragment.JDPersonalFragment");
                    ArrayList arrayList8 = TextUtils.isEmpty(config9) ? null : new ArrayList(Arrays.asList(config9.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "fragmentHelperWhiteList: " + arrayList8);
                    if (MONITOR_TYPE_FRAGMENT_HELPER.equals(str2) && arrayList8 != null && !arrayList8.contains(str3)) {
                        Log.d(TAG, "monitor fragment helper, don't report, flag:" + str3);
                        return;
                    }
                    String config10 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_CHECK_INSTALL_BUNDLE_WHITE_LIST, "com.jd.lib.personal.view.fragment.JDPersonalFragment");
                    ArrayList arrayList9 = TextUtils.isEmpty(config10) ? null : new ArrayList(Arrays.asList(config10.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "checkInstallBundleWhiteList: " + arrayList9);
                    if (MONITOR_TYPE_CHECK_INSTALL_BUNDLE.equals(str2) && arrayList9 != null && !arrayList9.contains(str3)) {
                        Log.d(TAG, "check install bundle, don't report, flag:" + str3);
                        return;
                    }
                    String config11 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_LOAD_CLASS_FROM_INSTALL_BUNDLE_WHITE_LIST, "com.jd.lib.personal.view.fragment.JDPersonalFragment");
                    ArrayList arrayList10 = TextUtils.isEmpty(config11) ? null : new ArrayList(Arrays.asList(config11.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "loadClassFromInstallBundleWhiteList: " + arrayList10);
                    if (MONITOR_TYPE_LOAD_CLASS_FROM_INSTALL_BUNDLE.equals(str2) && arrayList10 != null && !arrayList10.contains(str3)) {
                        Log.d(TAG, "load class from install bundle, don't report, flag:" + str3);
                        return;
                    }
                    String config12 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_CHECK_INSTALL_BUNDLE_BY_BUNDLE_NAME_WHITE_LIST, "com.jd.lib.personal");
                    ArrayList arrayList11 = TextUtils.isEmpty(config12) ? null : new ArrayList(Arrays.asList(config12.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "checkByBundleNameWhiteList: " + arrayList11);
                    if (MONITOR_TYPE_CHECK_INSTALL_BUNDLE_BY_BUNDLE_NAME.equals(str2) && arrayList11 != null && !arrayList11.contains(str3)) {
                        Log.d(TAG, "check install bundle by bundle name, don't report, flag:" + str3);
                        return;
                    }
                    String config13 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_LOCATION_NULL_CHECK_WHITE_LIST, "com.jd.lib.personal");
                    ArrayList arrayList12 = TextUtils.isEmpty(config13) ? null : new ArrayList(Arrays.asList(config13.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "locationNullCheckWhiteList: " + arrayList12);
                    if (MONITOR_TYPE_LOCATION_NULL_CHECK.equals(str2) && arrayList12 != null && !arrayList12.contains(str3)) {
                        Log.d(TAG, "location null check, don't report, flag:" + str3);
                        return;
                    }
                    String config14 = JDMobileConfig.getInstance().getConfig("AuraMonitor", "AuraMonitor", MONITOR_READ_BUNDLE_META_WHITE_LIST, "com.jd.lib.personal");
                    ArrayList arrayList13 = TextUtils.isEmpty(config14) ? null : new ArrayList(Arrays.asList(config14.split(DYConstants.DY_REGEX_COMMA)));
                    Log.d(TAG, "readBundleMetaWhiteList: " + arrayList13);
                    if (MONITOR_TYPE_READ_BUNDLE_META.equals(str2) && arrayList13 != null && !arrayList13.contains(str3)) {
                        Log.d(TAG, "read bundle meta, don't report, flag:" + str3);
                        return;
                    }
                    HashMap hashMap2 = new HashMap(16);
                    hashMap2.put("bundleName", String.valueOf(str));
                    hashMap2.put("bundleVersion", String.valueOf(i2));
                    hashMap2.put("bundleState", String.valueOf(AuraConfig.isBundlePrepered(str == null ? "" : str)));
                    hashMap2.put("NotPreparedProvidedBundles", String.valueOf(AuraConfig.getNotPreparedProvidedBundles(str == null ? "" : str)));
                    hashMap2.put("info", String.valueOf(str4));
                    hashMap2.put("from", String.valueOf(str5));
                    hashMap2.put("flag", String.valueOf(str3));
                    hashMap2.put(NotificationMessageSummary.MSGTYPE, th == null ? "3" : "2");
                    if (TextUtils.isEmpty(str2) || !MONITOR_TYPE_UFO_PAGE.equals(str2)) {
                        str7 = TAG;
                        str8 = "";
                        hashMap = hashMap2;
                        str9 = " info:";
                        str10 = " type:";
                        str11 = " bundleVersion:";
                    } else {
                        String versionName = PackageInfoUtil.getVersionName();
                        String valueOf = String.valueOf(PackageInfoUtil.getVersionCode());
                        str7 = TAG;
                        hashMap = hashMap2;
                        str11 = " bundleVersion:";
                        str8 = "";
                        str9 = " info:";
                        str10 = " type:";
                        try {
                            String a = l.a(str, i2, versionName, valueOf, str3, "enter ufo page");
                            Log.d(str7, "ufo page bi report,event id:bundle_activity_not_found msg:" + a);
                            e.q(AURA_UFO_PAGE, str3, "UFO", a);
                        } catch (Throwable th2) {
                            th = th2;
                            str6 = str7;
                            Log.d(str6, "aura monitor:" + th);
                            th.printStackTrace();
                            return;
                        }
                    }
                    e.q(AURA_BI_UPLOAD_CRASH, str8, "AuraControl.uploadCrash", str8 + PackageInfoUtil.getVersionCode());
                    Log.d(str7, "start report bundleName:" + str + str11 + i2 + str10 + str2 + " flag:" + str3 + str9 + str4 + " from:" + str5);
                    JdCrashReport.postCaughtException(new AuraMonitor(hashMap.toString(), th), str2);
                    return;
                }
                Log.d(TAG, "Aura monitor is close,don't report!");
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            str6 = TAG;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Thread b(Runnable runnable) {
        return new Thread(runnable, "aura_monitor_thread");
    }

    public static void monitor(final String str, final int i2, final String str2, final String str3, final String str4, final String str5, final Throwable th) {
        if (DownGradeUtils.isDownGrade()) {
            Log.d(TAG, "uploadCrash: x time not report ");
            return;
        }
        try {
            EXECUTOR.execute(new Runnable() { // from class: com.jingdong.app.mall.aura.a
                @Override // java.lang.Runnable
                public final void run() {
                    AuraMonitorReporter.a(str, i2, str2, str3, str4, str5, th);
                }
            });
        } catch (Throwable th2) {
            Log.e(TAG, "aura monitor report exception:" + th2);
        }
    }
}
