package com.jingdong.aura.wrapper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.IBinder;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.wrapper.listener.AuraDebugTimeListener;
import com.jingdong.aura.wrapper.listener.AuraEventListener;
import com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener;
import com.jingdong.aura.wrapper.listener.AuraPageCallback;
import com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener;
import com.jingdong.aura.wrapper.listener.IMobileLogCallback;
import com.jingdong.aura.wrapper.mhCallback.ImHCallBack;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class AuraConfig {
    public static final String ACTION_BROADCAST_BUNDLES_INSTALLED = "com.jingdong.aura.action.BUNDLES_INSTALLED";
    private static IApkcenterHelper apkcenterHelper;
    private static final String TAG = "AuraConfig";
    private static final com.jingdong.aura.core.util.l.b log = com.jingdong.aura.core.util.l.c.a(TAG);
    public static String[] DELAY = new String[0];
    public static String[] AUTO = new String[0];
    public static String[] STORE = new String[0];

    /* loaded from: classes4.dex */
    public interface IApkcenterHelper {
        void deleteBundleDownloadCache(String str);
    }

    public static void cleanAuraCache() {
        com.jingdong.aura.a.b.c.e();
    }

    public static void deleteBundleDownloadCache(String str) {
        try {
            IApkcenterHelper iApkcenterHelper = apkcenterHelper;
            if (iApkcenterHelper != null) {
                iApkcenterHelper.deleteBundleDownloadCache(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void enableLog(boolean z) {
        com.jingdong.aura.a.b.c.a(z);
    }

    public static void ensureActivityResources(Activity activity) {
        com.jingdong.aura.a.b.c.a(activity);
    }

    public static boolean ensureDiskSizeOfBundle(String str) {
        return com.jingdong.aura.a.b.c.a(str);
    }

    public static Activity getActivity(IBinder iBinder) {
        return com.jingdong.aura.a.b.c.a(iBinder);
    }

    public static boolean getArtUseNativeOdex() {
        return com.jingdong.aura.a.b.c.h();
    }

    public static File getAuraCacheDir() {
        return com.jingdong.aura.a.b.c.i();
    }

    public static AuraDebugTimeListener getAuraDebugTimeListener() {
        return com.jingdong.aura.a.b.c.j();
    }

    public static String getAuraVersion() {
        return com.jingdong.aura.a.b.c.k();
    }

    public static String getBundleNameForComponet(String str) {
        return com.jingdong.aura.a.b.c.b(str);
    }

    public static long getBundleSize(String str) {
        return com.jingdong.aura.a.b.c.c(str);
    }

    public static long getBundleVersionCode(String str) {
        return com.jingdong.aura.a.b.c.d(str);
    }

    public static List<String> getDependencyForBundle(String str) {
        return com.jingdong.aura.a.b.c.e(str);
    }

    public static String getInstalledBundlesVersion() {
        return com.jingdong.aura.a.b.c.o();
    }

    public static String getInternalBundleImpl() {
        return com.jingdong.aura.a.b.c.p();
    }

    public static String getInternalBundleInfo() {
        return com.jingdong.aura.a.b.c.q();
    }

    public static String getInternalBundlePathInfo() {
        return com.jingdong.aura.a.b.c.r();
    }

    public static String getInternalLibInfo() {
        return com.jingdong.aura.a.b.c.s();
    }

    public static String getInternalSourceApkInfo() {
        return com.jingdong.aura.a.b.c.t();
    }

    public static boolean getIsCheckDexMd5() {
        return com.jingdong.aura.a.b.c.u();
    }

    public static boolean getIsCheckDexOnlyRootPhone() {
        return com.jingdong.aura.a.b.c.v();
    }

    public static boolean getIsHangWait() {
        return com.jingdong.aura.a.b.c.x();
    }

    public static boolean getIsSetExtraClassLoaderForIntent() {
        return com.jingdong.aura.a.b.c.y();
    }

    public static boolean getIsUseAuraDexOpt() {
        return com.jingdong.aura.a.b.c.z();
    }

    public static boolean getIsUseMainThreadDealResource() {
        return com.jingdong.aura.a.b.c.B();
    }

    public static boolean getIsUseNewResourcesStrategy() {
        return com.jingdong.aura.a.b.c.C();
    }

    public static boolean getIsUseOptDexCache() {
        return com.jingdong.aura.a.b.c.D();
    }

    public static long getMaxBundleRevision(String str) {
        return com.jingdong.aura.a.b.c.f(str);
    }

    public static ArrayList<String> getNotPreparedProvidedBundles(String str) {
        return com.jingdong.aura.a.b.c.g(str);
    }

    public static long getOriVersionCode(String str) {
        return com.jingdong.aura.a.b.c.h(str);
    }

    public static List<Map<String, String>> getProvidedBundleInfos() {
        return com.jingdong.aura.a.b.c.G();
    }

    public static int getResourceFactoryType() {
        return com.jingdong.aura.a.b.c.H();
    }

    public static SharedPreferences getSharedPreferences() {
        return com.jingdong.aura.a.b.c.I();
    }

    public static int getWaitSeconds() {
        return com.jingdong.aura.a.b.c.L();
    }

    public static void installBundleNative(String str, File file) {
        com.jingdong.aura.a.b.c.a(str, file);
    }

    public static boolean isBundleLoaded(String str) {
        return com.jingdong.aura.a.b.c.i(str);
    }

    public static boolean isBundlePrepered(String str) {
        return com.jingdong.aura.a.b.c.j(str);
    }

    public static boolean isBundleProvided(String str) {
        return com.jingdong.aura.a.b.c.k(str);
    }

    public static boolean isUseAura() {
        return com.jingdong.aura.a.b.c.N();
    }

    public static boolean isUseBundle(String str) {
        return com.jingdong.aura.a.b.c.l(str);
    }

    public static void registMHCallback(ImHCallBack imHCallBack) {
        com.jingdong.aura.a.b.c.a(imHCallBack);
    }

    public static void registerMobileLogCallback(IMobileLogCallback iMobileLogCallback) {
        com.jingdong.aura.a.b.c.a(iMobileLogCallback);
    }

    public static void registerMonitorConfigListener(AuraMonitorConfigListener auraMonitorConfigListener) {
        com.jingdong.aura.a.b.c.a(auraMonitorConfigListener);
    }

    public static void registerPrivacyListener(AuraPrivacyInfoListener auraPrivacyInfoListener) {
        com.jingdong.aura.a.b.c.a(auraPrivacyInfoListener);
    }

    public static void setApkcenterHelper(IApkcenterHelper iApkcenterHelper) {
        apkcenterHelper = iApkcenterHelper;
    }

    public static void setArtUseNativeOdex(boolean z) {
        com.jingdong.aura.a.b.c.b(z);
    }

    public static void setAuraDebugTimeListener(AuraDebugTimeListener auraDebugTimeListener) {
        com.jingdong.aura.a.b.c.a(auraDebugTimeListener);
    }

    public static void setAuraEventListener(AuraEventListener auraEventListener) {
        e.a(auraEventListener);
    }

    public static void setAuraMobileConfig(String str) {
        com.jingdong.aura.a.b.c.m(str);
    }

    public static void setAutoBundles(String[] strArr) {
        AUTO = strArr;
    }

    public static void setBlackList(Set<String> set, Set<String> set2) {
        com.jingdong.aura.a.b.c.a(set, set2);
    }

    public static void setBundleAbi(String str) {
        com.jingdong.aura.a.b.c.n(str);
    }

    public static void setClassNotFoundCallback(AuraPageCallback auraPageCallback) {
        com.jingdong.aura.a.b.c.a(auraPageCallback);
    }

    public static void setDowngradeBundleList(List<AuraDowngradeBundle> list) {
        com.jingdong.aura.a.b.c.a(list);
    }

    public static void setEnabled(boolean z) {
        com.jingdong.aura.a.b.c.c(z);
    }

    public static void setHostSupportAbiType(int i2) {
        com.jingdong.aura.a.b.c.b(i2);
    }

    public static void setIsArm64(boolean z) {
        com.jingdong.aura.a.b.c.d(z);
    }

    public static void setIsCheckDexMd5(boolean z) {
        com.jingdong.aura.a.b.c.e(z);
    }

    public static void setIsCheckDexOnlyRootPhone(boolean z) {
        com.jingdong.aura.a.b.c.f(z);
    }

    public static void setIsDebugBuildConfig(boolean z) {
        com.jingdong.aura.a.b.c.g(z);
    }

    public static void setIsHangWait(boolean z) {
        com.jingdong.aura.a.b.c.h(z);
    }

    public static void setIsSetExtraClassLoaderForIntent(boolean z) {
        com.jingdong.aura.a.b.c.i(z);
    }

    public static void setIsUseAuraDexOpt(boolean z) {
        com.jingdong.aura.a.b.c.j(z);
    }

    public static void setIsUseAuraUtils(boolean z) {
        com.jingdong.aura.a.b.c.k(z);
    }

    public static void setIsUseMainThreadDealResource(boolean z) {
        com.jingdong.aura.a.b.c.l(z);
    }

    public static void setIsUseNewResourcesStrategy(boolean z) {
        com.jingdong.aura.a.b.c.m(z);
    }

    public static void setIsUseOptDexCache(boolean z) {
        com.jingdong.aura.a.b.c.n(z);
    }

    public static void setWaitSeconds(int i2) {
        com.jingdong.aura.a.b.c.c(i2);
    }
}
