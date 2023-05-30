package com.jingdong.sdk.baseinfo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.TransportInfo;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.ScreenSize;
import com.jd.android.sdk.coreinfo.deviceUtil.AndroidUtil;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.android.sdk.coreinfo.util.Supplier;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jd.android.sdk.oaid.OaidManager;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfoConfig;
import com.jingdong.sdk.baseinfo.c.e;
import com.jingdong.sdk.baseinfo.db.PrivacyInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class BaseInfo {
    public static final int FULL_PACKAGE = 0;
    public static final int MINI_PACKAGE = 1;
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_4G = "4g";
    public static final String NETWORK_TYPE_5G = "5g";
    public static final String NETWORK_TYPE_ETHERNET = "enterNet";
    public static final String NETWORK_TYPE_MOBILE = "mobile";
    public static final String NETWORK_TYPE_NONE = "none";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String TAG = "BaseInfo";
    private static final int UNKNOWN_NUM = 0;
    private static final String UNKNOWN_TEXT = "";
    private static int appPackageType = 0;
    private static IDataCacheScheme dataCacheScheme = null;
    private static boolean hasInitialized = false;
    private static boolean isMainProcess = true;
    private static String sAndroidId = "";
    private static String sAndroidId2 = "";
    private static BaseInfoConfig sConfig = null;
    private static Context sContext = null;
    private static String sDeviceBrand = "";
    private static String sDeviceId = "";
    private static String sDeviceId2 = "";
    private static String sDeviceModel = "";
    private static String sDeviceName = "";
    private static String sHardwareSerialNo = "";
    private static IBackForegroundCheck sIBackForegroundCheck = null;
    private static IBuildConfigGetter sIBuildConfigGetter = null;
    private static IDensityRelateCheck sIDensityRelateCheck = null;
    private static IPrivacyCheck sIPrivacyCheck = null;
    private static IPrivacyInfoCallback sIPrivacyInfoCallback = null;
    private static String sManufacture = "";
    private static String sNetworkType = "";
    private static int sNetworkTypeInt = 0;
    private static String sOSFingerPrint = "";
    private static String sOSName = "";
    private static String sProductName = "";
    private static String sSimCountryIso = "";
    private static String sSimOperatorName = "";
    private static String sSimSerialNo = "";
    private static String sSubscriberId = "";
    private static String sSubscriberId2 = "";
    private static String sWifiMacAddress = "";
    private static String sWifiMacAddress2 = "";

    public static boolean checkPipes() {
        return CoreInfo.Device.checkPipes();
    }

    public static void deletePrivacyInfos(PrivacyInfo[] privacyInfoArr, ResultCallback<Integer> resultCallback, boolean z) {
        if (isMainProcess) {
            a.a(privacyInfoArr, resultCallback, z);
        }
    }

    public static void getAllPrivacyInfos(ResultCallback<List<PrivacyInfo>> resultCallback, boolean z) {
        if (isMainProcess) {
            a.a(resultCallback, z);
        }
    }

    public static String getAndroidId() {
        if (isAgreedPrivacy() && isAppForeground()) {
            return getAndroidId0();
        }
        return sAndroidId;
    }

    public static String getAndroidId0() {
        String androidId;
        if (TextUtils.isEmpty(sAndroidId)) {
            IDataCacheScheme iDataCacheScheme = dataCacheScheme;
            if (iDataCacheScheme == null || iDataCacheScheme.getScheme() != 1) {
                IDataCacheScheme iDataCacheScheme2 = dataCacheScheme;
                androidId = (iDataCacheScheme2 == null || iDataCacheScheme2.getScheme() != 2) ? CoreInfo.Device.getAndroidId(sContext) : com.jingdong.sdk.baseinfo.c.a.b(sContext);
            } else {
                androidId = com.jingdong.sdk.baseinfo.c.a.a(sContext);
            }
            sAndroidId = androidId;
            onPrivacyInfoCalled("AndroidId", "AndroidId", sAndroidId);
        }
        return sAndroidId;
    }

    public static String getAndroidIdForDeviceFinger() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sAndroidId2)) {
                String androidIdForDeviceFinger = CoreInfo.Device.getAndroidIdForDeviceFinger(sContext);
                sAndroidId2 = androidIdForDeviceFinger;
                onPrivacyInfoCalled("AndroidId", "AndroidId", androidIdForDeviceFinger);
            }
            return sAndroidId2;
        }
        return sAndroidId2;
    }

    public static String getAndroidIdWithAOPBySecure(ContentResolver contentResolver, String str) {
        return str.equals("android_id") ? getAndroidId() : Settings.Secure.getString(contentResolver, str);
    }

    public static String getAndroidIdWithAOPBySystem(ContentResolver contentResolver, String str) {
        return str.equals("android_id") ? getAndroidId() : Settings.System.getString(contentResolver, str);
    }

    public static int getAndroidSDKVersion() {
        return CoreInfo.System.getAndroidSDKVersion();
    }

    public static String getAndroidVersion() {
        return CoreInfo.System.getAndroidVersion();
    }

    public static long getAppFirstInstallTime() {
        return CoreInfo.App.getFirstInstallTime(sContext);
    }

    public static long getAppLastUpdateTime() {
        return CoreInfo.App.getLastUpdateTime(sContext);
    }

    public static String getAppName() {
        IBuildConfigGetter iBuildConfigGetter = sIBuildConfigGetter;
        return (iBuildConfigGetter == null || TextUtils.isEmpty(iBuildConfigGetter.getAppName())) ? CoreInfo.App.getAppName(sContext) : sIBuildConfigGetter.getAppName();
    }

    public static String getAppPackageName() {
        IBuildConfigGetter iBuildConfigGetter = sIBuildConfigGetter;
        return (iBuildConfigGetter == null || TextUtils.isEmpty(iBuildConfigGetter.getPackageName())) ? CoreInfo.App.getPackageName(sContext) : sIBuildConfigGetter.getPackageName();
    }

    public static int getAppPackageType() {
        return appPackageType;
    }

    public static long getAppSignatureHash() {
        return CoreInfo.App.getSignatureHash(sContext);
    }

    public static int getAppVersionCode() {
        IBuildConfigGetter iBuildConfigGetter = sIBuildConfigGetter;
        return (iBuildConfigGetter == null || iBuildConfigGetter.getVersionCode() == -1) ? CoreInfo.App.getVersionCode(sContext) : sIBuildConfigGetter.getVersionCode();
    }

    public static String getAppVersionName() {
        IBuildConfigGetter iBuildConfigGetter = sIBuildConfigGetter;
        return (iBuildConfigGetter == null || TextUtils.isEmpty(iBuildConfigGetter.getVersionName())) ? CoreInfo.App.getVersionName(sContext) : sIBuildConfigGetter.getVersionName();
    }

    public static int getBatteryLevel() {
        return CoreInfo.Device.getBatteryLevel(sContext);
    }

    public static int getBatteryPlugged() {
        return CoreInfo.Device.getBatteryPlugged(sContext);
    }

    public static int getBatteryScale() {
        return CoreInfo.Device.getBatteryScale(sContext);
    }

    public static int getBatteryStatus() {
        return CoreInfo.Device.getBatteryStatus(sContext);
    }

    public static String getBluetoothMac() {
        return getBluetoothMac(sContext);
    }

    public static String getBluetoothMac(Context context) {
        return (!NeedAgreePrivacyField.getBluetoothMac || isAgreedPrivacy()) ? CoreInfo.Device.getBluetoothMac(context) : "";
    }

    public static String getBluetoothName() {
        return getBluetoothName(sContext);
    }

    public static String getBluetoothName(Context context) {
        return (!NeedAgreePrivacyField.getBluetoothName || isAgreedPrivacy()) ? CoreInfo.Device.getBluetoothName(context) : "";
    }

    public static String getBoard() {
        return CoreInfo.Device.getBoard();
    }

    public static String getBoardPlatform() {
        return CoreInfo.Device.getBoardPlatform();
    }

    public static String getBootloaderVersion() {
        return CoreInfo.Device.getBootloaderVersion();
    }

    public static String getCPUMaxFreq() {
        return CoreInfo.Device.getCPUMaxFreq();
    }

    public static String getCPUNum() {
        return CoreInfo.Device.getCPUNum();
    }

    public static String getCPUSerialNo() {
        return CoreInfo.Device.getCPUSerialNo();
    }

    @Deprecated
    public static List<PackageInfo> getCachedInstalledPkgs(Context context, int i2) {
        return getInstalledPkgs(context, i2);
    }

    @Deprecated
    public static int getCellId() {
        return getCellId(sContext);
    }

    @Deprecated
    public static int getCellId(Context context) {
        return CoreInfo.Device.getCellId(context);
    }

    public static CellLocation getCellLocationForDeviceFinger() {
        if (isAgreedPrivacy() && isAppForeground()) {
            return CoreInfo.Device.getCellLocationForDeviceFinger(sContext);
        }
        return null;
    }

    public static BaseInfoConfig getConfig() {
        return sConfig;
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getCountry() {
        return CoreInfo.System.getCountry(sContext);
    }

    public static String getCpuCurFreq() {
        return CoreInfo.Device.getCpuCurFreq();
    }

    public static String getCpuMinFreq() {
        return CoreInfo.Device.getCpuMinFreq();
    }

    public static String getCpuName() {
        return CoreInfo.Device.getCpuName();
    }

    public static float getDensity() {
        if (useOriginalUIRelated()) {
            float density = CoreInfo.Device.getDensity(sContext);
            onPrivacyInfoCalled("Density", "\u5c4f\u5e55\u5bc6\u5ea6", String.valueOf(density));
            return density;
        }
        return 1.0f;
    }

    public static String getDensityDpi() {
        return String.valueOf(getDensityDpiInt());
    }

    public static int getDensityDpiInt() {
        if (useOriginalUIRelated()) {
            int densityDpiInt = CoreInfo.Device.getDensityDpiInt(sContext);
            onPrivacyInfoCalled("DensityDpi", "\u5c4f\u5e55\u5206\u8fa8\u7387", String.valueOf(densityDpiInt));
            return densityDpiInt;
        }
        return 160;
    }

    public static String getDeviceBrand() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sDeviceBrand)) {
                String brand = CoreInfo.Device.getBrand();
                sDeviceBrand = brand;
                onPrivacyInfoCalled("DeviceBrand", "\u8bbe\u5907\u54c1\u724c", brand);
            }
            return sDeviceBrand;
        }
        return sDeviceBrand;
    }

    public static String getDeviceId() {
        return "";
    }

    public static String getDeviceIdForDeviceFinger() {
        return "";
    }

    public static String getDeviceManufacture() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sManufacture)) {
                String manufacture = CoreInfo.Device.getManufacture();
                sManufacture = manufacture;
                onPrivacyInfoCalled("Manufacture", "\u751f\u4ea7\u5382\u5546", manufacture);
            }
            return sManufacture;
        }
        return sManufacture;
    }

    public static String getDeviceModel() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sDeviceModel)) {
                String model = CoreInfo.Device.getModel();
                sDeviceModel = model;
                onPrivacyInfoCalled("DeviceModel", "\u8bbe\u5907\u578b\u53f7", model);
            }
            return sDeviceModel;
        }
        return sDeviceModel;
    }

    @Deprecated
    public static String getDeviceName() {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sDeviceName)) {
                String deviceName = CoreInfo.Device.getDeviceName();
                sDeviceName = deviceName;
                onPrivacyInfoCalled("DeviceName", "\u8bbe\u5907\u540d\u79f0", deviceName);
            }
            return sDeviceName;
        }
        return sDeviceName;
    }

    public static String getDeviceProductName() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sProductName)) {
                String productName = CoreInfo.Device.getProductName();
                sProductName = productName;
                onPrivacyInfoCalled("ProductName", "\u4ea7\u54c1\u540d\u79f0", productName);
            }
            return sProductName;
        }
        return sProductName;
    }

    public static String[] getDeviceSuppportedABIs() {
        return CoreInfo.Device.getSuppportedABIs();
    }

    public static String getDisplayMetrics() {
        if (useOriginalUIRelated()) {
            String displayMetrics = CoreInfo.Device.getDisplayMetrics(sContext);
            onPrivacyInfoCalled("ScreenSize", "\u5c4f\u5e55\u5c3a\u5bf8", displayMetrics);
            return displayMetrics;
        }
        return "";
    }

    public static DisplayMetrics getDisplayMetricsObject() {
        if (useOriginalUIRelated()) {
            DisplayMetrics displayMetricsObject = CoreInfo.Device.getDisplayMetricsObject(sContext);
            if (displayMetricsObject != null) {
                onPrivacyInfoCalled("DisplayMetrics", "\u5c4f\u5e55\u4fe1\u606f", "DisplayMetrics Object");
            }
            return displayMetricsObject;
        }
        return null;
    }

    public static DisplayMetrics getDisplayMetricsObjectWithAOP(Resources resources) {
        return Resources.getSystem() == resources ? resources.getDisplayMetrics() : getDisplayMetricsObject();
    }

    public static String getDisplayMetricsWithNavigationBar() {
        return getDisplayMetricsWithNavigationBar(sContext);
    }

    public static String getDisplayMetricsWithNavigationBar(Context context) {
        if (useOriginalUIRelated()) {
            String displayMetricsWithNavigationBar = CoreInfo.Device.getDisplayMetricsWithNavigationBar(context);
            onPrivacyInfoCalled("ScreenSize", "\u5c4f\u5e55\u5c3a\u5bf8", displayMetricsWithNavigationBar);
            return displayMetricsWithNavigationBar;
        }
        return "";
    }

    public static long getElapsedRealtime() {
        return CoreInfo.System.getElapsedRealtime();
    }

    public static long getExternalStorageSize() {
        return CoreInfo.Device.getExternalStorageSize();
    }

    public static int getGateway() {
        if (isAgreedPrivacy() && isAppForeground()) {
            return CoreInfo.Device.getGateway(sContext);
        }
        return 0;
    }

    public static String getHardwareName() {
        return CoreInfo.Device.getHardwareName();
    }

    @Deprecated
    public static String getHardwareSerialNo() {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sHardwareSerialNo)) {
                String hardwareSerialNo = CoreInfo.Device.getHardwareSerialNo();
                sHardwareSerialNo = hardwareSerialNo;
                onPrivacyInfoCalled("HardwareSerialNo", "\u786c\u4ef6\u5e8f\u5217\u53f7", hardwareSerialNo);
            }
            return sHardwareSerialNo;
        }
        return sHardwareSerialNo;
    }

    public static String getHostName() {
        return CoreInfo.Device.getHostName();
    }

    public static List<PackageInfo> getInstalledPkgs(int i2) {
        return getInstalledPkgs(sContext, i2);
    }

    public static List<PackageInfo> getInstalledPkgs(Context context, int i2) {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            List<PackageInfo> installedPkgs = CoreInfo.System.getInstalledPkgs(context, i2);
            onPrivacyInfoCalled("InstalledPkgs", "\u8f6f\u4ef6\u5217\u8868", com.jingdong.sdk.baseinfo.c.e.a(installedPkgs, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.8
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return ((PackageInfo) obj).packageName;
                }
            }));
            return installedPkgs;
        }
        return new ArrayList();
    }

    public static List getInstalledPkgsWithAOP(PackageManager packageManager, int i2) {
        return getInstalledPkgs(i2);
    }

    public static String getIpAddressFromWifiInfo() {
        return getIpAddressFromWifiInfo(sContext);
    }

    public static String getIpAddressFromWifiInfo(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            String ipAddressFromWifiInfo = CoreInfo.Device.getIpAddressFromWifiInfo(context);
            onPrivacyInfoCalled("NetAddress", "IP\u5730\u5740", ipAddressFromWifiInfo);
            return ipAddressFromWifiInfo;
        }
        return "";
    }

    public static String getLanguage() {
        return CoreInfo.System.getLanguage(sContext);
    }

    public static String getLastOAID() {
        if (isAgreedPrivacy()) {
            String lastOAID = OaidManager.getLastOAID(sContext);
            Logger.d(TAG, "getLastOAID() --> ".concat(String.valueOf(lastOAID)));
            return lastOAID;
        }
        return "";
    }

    public static JSONArray getLastTimePrivacyMethodCallRecords() {
        return isMainProcess ? com.jingdong.sdk.baseinfo.b.b.b() : new JSONArray();
    }

    public static String getLinuxVersion() {
        return CoreInfo.Device.getLinuxVersion();
    }

    public static long getMemAvailSize() {
        return CoreInfo.Device.getMemAvailSize(sContext);
    }

    public static String[] getMemInfo() {
        return CoreInfo.Device.getMemInfo();
    }

    public static long getMemTotalSize() {
        return CoreInfo.Device.getMemTotalSize(sContext);
    }

    @Deprecated
    public static String getNetAddressInfo() {
        if (isAgreedPrivacy() && isAppForeground()) {
            String netAddressInfo = CoreInfo.Device.getNetAddressInfo();
            onPrivacyInfoCalled("NetAddress", "IP\u5730\u5740", netAddressInfo);
            return netAddressInfo;
        }
        return "";
    }

    public static String[][] getNetAddresses() {
        if (isAgreedPrivacy() && isAppForeground()) {
            String[][] netAddresses = CoreInfo.Device.getNetAddresses();
            if (netAddresses != null && netAddresses.length == 2) {
                onPrivacyInfoCalled("NetAddress", "IP\u5730\u5740", "IPv4: " + com.jingdong.sdk.baseinfo.c.e.a(Arrays.asList(netAddresses[0]), new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.12
                    @Override // com.jingdong.sdk.baseinfo.c.e.b
                    public final String toString(Object obj) {
                        return (String) obj;
                    }
                }, DYConstants.DY_REGEX_COMMA) + ", IPv6: " + com.jingdong.sdk.baseinfo.c.e.a(Arrays.asList(netAddresses[1]), new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.2
                    @Override // com.jingdong.sdk.baseinfo.c.e.b
                    public final String toString(Object obj) {
                        return (String) obj;
                    }
                }, DYConstants.DY_REGEX_COMMA));
            }
            return netAddresses;
        }
        return (String[][]) Array.newInstance(String.class, 0, 0);
    }

    public static List<String> getNetAddressesForIPv4() {
        if (isAgreedPrivacy() && isAppForeground()) {
            List<String> netAddressesForIPv4 = CoreInfo.Device.getNetAddressesForIPv4();
            onPrivacyInfoCalled("NetAddressIPv4", "IPv4\u5730\u5740", com.jingdong.sdk.baseinfo.c.e.a(netAddressesForIPv4, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.3
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return (String) obj;
                }
            }));
            return netAddressesForIPv4;
        }
        return new ArrayList();
    }

    public static List<String> getNetAddressesForIPv6() {
        if (isAgreedPrivacy() && isAppForeground()) {
            List<String> netAddressesForIPv6 = CoreInfo.Device.getNetAddressesForIPv6();
            onPrivacyInfoCalled("NetAddressIPv6", "IPv6\u5730\u5740", com.jingdong.sdk.baseinfo.c.e.a(netAddressesForIPv6, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.4
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return (String) obj;
                }
            }));
            return netAddressesForIPv6;
        }
        return new ArrayList();
    }

    public static int getNetmask() {
        if (isAgreedPrivacy() && isAppForeground()) {
            return CoreInfo.Device.getNetmask(sContext);
        }
        return 0;
    }

    public static String getNetworkISO() {
        return getNetworkISO(sContext);
    }

    public static String getNetworkISO(Context context) {
        return CoreInfo.Device.getNetworkISO(context);
    }

    public static String getNetworkOperator() {
        return getNetworkOperator(sContext);
    }

    public static String getNetworkOperator(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            String networkOperator = CoreInfo.Device.getNetworkOperator(context);
            onPrivacyInfoCalled("NetworkOperator", "\u7f51\u7edc\u8fd0\u8425\u5546MCC+MNC\u4fe1\u606f", networkOperator);
            return networkOperator;
        }
        return "";
    }

    public static String getNetworkOperatorName() {
        return getNetworkOperatorName(sContext);
    }

    public static String getNetworkOperatorName(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            String networkOperatorName = CoreInfo.Device.getNetworkOperatorName(context);
            onPrivacyInfoCalled("NetworkOperatorName", "\u7f51\u7edc\u8fd0\u8425\u5546\u540d\u79f0", networkOperatorName);
            return networkOperatorName;
        }
        return "";
    }

    public static String getNetworkType() {
        if (isAgreedPrivacy() && isAppForeground()) {
            String networkType = CoreInfo.Device.getNetworkType(sContext);
            sNetworkType = networkType;
            onPrivacyInfoCalled("NetworkType", "\u7f51\u7edc\u7c7b\u578b", networkType);
            return sNetworkType;
        }
        return sNetworkType;
    }

    public static String getNetworkTypeForDeviceFinger() {
        return (isAgreedPrivacy() && isAppForeground()) ? CoreInfo.Device.getNetworkTypeForDeviceFinger(sContext) : "";
    }

    @Deprecated
    public static int getNetworkTypeInt() {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            int networkTypeInt = CoreInfo.Device.getNetworkTypeInt(sContext);
            sNetworkTypeInt = networkTypeInt;
            onPrivacyInfoCalled("NetworkTypeInt", "\u7f51\u7edc\u7c7b\u578b\u503c", String.valueOf(networkTypeInt));
            return sNetworkTypeInt;
        }
        return sNetworkTypeInt;
    }

    public static String getOAID() {
        if (isAgreedPrivacy()) {
            String oaid = OaidManager.getInstance().getOaidInfo().getOAID();
            if (TextUtils.isEmpty(oaid)) {
                oaid = getLastOAID();
                if (!TextUtils.isEmpty(oaid)) {
                    OaidManager.getInstance().getOaidInfo().setOAIDValid(true);
                    OaidManager.getInstance().getOaidInfo().setOAID(oaid);
                }
            }
            Logger.d(TAG, "getOAID() --> ".concat(String.valueOf(oaid)));
            return oaid;
        }
        return "";
    }

    public static boolean getOAIDStatus() {
        OaidManager.getInstance();
        return OaidManager.getOAIDStatus();
    }

    public static String getOSFingerprint() {
        if (isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sOSFingerPrint)) {
                String oSFingerprint = CoreInfo.System.getOSFingerprint();
                sOSFingerPrint = oSFingerprint;
                onPrivacyInfoCalled("OSFingerprint", "\u7cfb\u7edf\u6307\u7eb9", oSFingerprint);
            }
            return sOSFingerPrint;
        }
        return sOSFingerPrint;
    }

    public static String getOSName() {
        if (TextUtils.isEmpty(sOSName)) {
            String oSName = CoreInfo.System.getOSName();
            sOSName = oSName;
            onPrivacyInfoCalled("OSName", "\u7cfb\u7edf\u540d\u79f0", oSName);
        }
        return sOSName;
    }

    public static String getOSVersionTags() {
        return CoreInfo.System.getOSVersionTags();
    }

    public static String getOSVersionType() {
        return CoreInfo.System.getOSVersionType();
    }

    public static void getPrivacyInfosByTimestamp(long j2, long j3, ResultCallback<List<PrivacyInfo>> resultCallback, boolean z) {
        if (isMainProcess) {
            a.a(j2, j3, resultCallback, z);
        }
    }

    public static JSONArray getPrivacyMethodCallRecordsOnLogout() {
        return isMainProcess ? com.jingdong.sdk.baseinfo.b.b.c() : new JSONArray();
    }

    public static String getRadioVersion() {
        return CoreInfo.Device.getRadioVersion();
    }

    public static ScreenSize getRealScreenSize() {
        if (useOriginalUIRelated()) {
            ScreenSize realScreenSize = CoreInfo.Device.getRealScreenSize(sContext);
            if (realScreenSize != null) {
                onPrivacyInfoCalled("ScreenSize", "\u5c4f\u5e55\u5c3a\u5bf8", realScreenSize.widthPixels + ProxyConfig.MATCH_ALL_SCHEMES + realScreenSize.heightPixels);
            }
            return realScreenSize;
        }
        return new ScreenSize(-1, -1);
    }

    public static String getRomName() {
        return CoreInfo.System.getRomName();
    }

    public static long getRomSize() {
        return CoreInfo.Device.getRomSize();
    }

    @Deprecated
    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
        return getRunningAppProcesses(sContext);
    }

    @Deprecated
    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses(Context context) {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = CoreInfo.App.getRunningAppProcesses(context);
            onPrivacyInfoCalled("RunningAppProcesses", "\u8fdb\u7a0b\u5217\u8868", com.jingdong.sdk.baseinfo.c.e.a(runningAppProcesses, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.7
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return ((ActivityManager.RunningAppProcessInfo) obj).processName;
                }
            }));
            return runningAppProcesses;
        }
        return new ArrayList();
    }

    @Deprecated
    public static List<ActivityManager.RunningServiceInfo> getRunningServices(int i2) {
        return getRunningServices(sContext, i2);
    }

    @Deprecated
    public static List<ActivityManager.RunningServiceInfo> getRunningServices(Context context) {
        return getRunningServices(context, Integer.MAX_VALUE);
    }

    @Deprecated
    public static List<ActivityManager.RunningServiceInfo> getRunningServices(Context context, int i2) {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            List<ActivityManager.RunningServiceInfo> runningServices = CoreInfo.App.getRunningServices(context, i2);
            onPrivacyInfoCalled("RunningServices", "Service\u5217\u8868", com.jingdong.sdk.baseinfo.c.e.a(runningServices, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.5
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return ((ActivityManager.RunningServiceInfo) obj).service.getClassName();
                }
            }));
            return runningServices;
        }
        return new ArrayList();
    }

    public static List getRunningServicesWithAOP(ActivityManager activityManager, int i2) {
        return getRunningServices(i2);
    }

    public static List<ActivityManager.RunningTaskInfo> getRunningTasks(int i2) {
        return getRunningTasks(sContext, i2);
    }

    @Deprecated
    public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context) {
        return getRunningTasks(context, Integer.MAX_VALUE);
    }

    @Deprecated
    public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context, int i2) {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            List<ActivityManager.RunningTaskInfo> runningTasks = CoreInfo.App.getRunningTasks(context, i2);
            onPrivacyInfoCalled("RunningTasks", "\u4efb\u52a1\u5217\u8868", com.jingdong.sdk.baseinfo.c.e.a(runningTasks, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.6
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return String.valueOf(((ActivityManager.RunningTaskInfo) obj).id);
                }
            }));
            return runningTasks;
        }
        return new ArrayList();
    }

    public static List getRunningTasksWithAOP(ActivityManager activityManager, int i2) {
        return getRunningTasks(i2);
    }

    public static String getSDCardId() {
        return CoreInfo.Device.getSDCardId();
    }

    public static float getScaledDensity() {
        if (useOriginalUIRelated()) {
            float scaledDensity = CoreInfo.Device.getScaledDensity(sContext);
            onPrivacyInfoCalled("ScaledDensity", "\u5b57\u4f53\u5927\u5c0f", String.valueOf(scaledDensity));
            return scaledDensity;
        }
        return 1.0f;
    }

    public static int getScreenHeight() {
        if (useOriginalUIRelated()) {
            int screenHeight = CoreInfo.Device.getScreenHeight(sContext);
            onPrivacyInfoCalled("ScreenHeight", "\u5c4f\u5e55\u9ad8\u5ea6", String.valueOf(screenHeight));
            return screenHeight;
        }
        return 320;
    }

    public static int getScreenHeight2() {
        if (useOriginalUIRelated()) {
            int screenHeight2 = CoreInfo.Device.getScreenHeight2();
            onPrivacyInfoCalled("ScreenHeight", "\u5c4f\u5e55\u9ad8\u5ea6", String.valueOf(screenHeight2));
            return screenHeight2;
        }
        return 320;
    }

    public static int getScreenWidth() {
        if (useOriginalUIRelated()) {
            int screenWidth = CoreInfo.Device.getScreenWidth(sContext);
            onPrivacyInfoCalled("ScreenWidth", "\u5c4f\u5e55\u5bbd\u5ea6", String.valueOf(screenWidth));
            return screenWidth;
        }
        return 240;
    }

    public static int getScreenWidth2() {
        if (useOriginalUIRelated()) {
            int screenWidth2 = CoreInfo.Device.getScreenWidth2();
            onPrivacyInfoCalled("ScreenWidth", "\u5c4f\u5e55\u5bbd\u5ea6", String.valueOf(screenWidth2));
            return screenWidth2;
        }
        return 240;
    }

    public static List<Sensor> getSensorList() {
        return getSensorList(sContext);
    }

    public static List<Sensor> getSensorList(Context context) {
        return !isAgreedPrivacy() ? new ArrayList() : CoreInfo.Device.getSensorList(context);
    }

    public static String getSimCountryIso() {
        if (isAgreedPrivacy()) {
            if (isAppForeground()) {
                if (TextUtils.isEmpty(sSimCountryIso)) {
                    sSimCountryIso = CoreInfo.Device.getSimCountryIso(sContext);
                }
                return sSimCountryIso;
            }
            return sSimCountryIso;
        }
        return "";
    }

    public static String getSimOperator() {
        return (!NeedAgreePrivacyField.getSimOperator || isAgreedPrivacy()) ? CoreInfo.Device.getSimOperator(sContext) : "";
    }

    public static String getSimOperatorName() {
        if (isAgreedPrivacy()) {
            if (isAppForeground()) {
                if (TextUtils.isEmpty(sSimOperatorName)) {
                    sSimOperatorName = CoreInfo.Device.getSimOperatorName(sContext);
                }
                return sSimOperatorName;
            }
            return sSimOperatorName;
        }
        return "";
    }

    @Deprecated
    public static String getSimSerialNo() {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sSimSerialNo)) {
                String simSerialNo = CoreInfo.Device.getSimSerialNo(sContext);
                sSimSerialNo = simSerialNo;
                onPrivacyInfoCalled("SimSerialNo", "SIM\u5361\u5e8f\u5217\u53f7", simSerialNo);
            }
            return sSimSerialNo;
        }
        return sSimSerialNo;
    }

    public static int getStatusBarHeight() {
        return getStatusBarHeight(sContext);
    }

    public static int getStatusBarHeight(Context context) {
        return CoreInfo.App.getStatusBarHeight(context);
    }

    public static String getSubscriberId() {
        return "";
    }

    public static String getSubscriberIdForDeviceFinger() {
        return "";
    }

    public static String getTimeZoneID() {
        return CoreInfo.System.getTimeZoneID();
    }

    public static String getUserAgent() {
        return getUserAgent(sContext);
    }

    public static String getUserAgent(Context context) {
        return CoreInfo.Device.getUserAgent(context);
    }

    public static String getUserAgent(Context context, long j2) {
        return CoreInfo.Device.getUserAgent(context, j2);
    }

    public static String getWifiBSSID() {
        return getWifiBSSID(sContext);
    }

    public static String getWifiBSSID(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            String wifiBSSID = CoreInfo.Device.getWifiBSSID(context);
            onPrivacyInfoCalled("WifiBSSID", "\u5f53\u524d\u8fde\u63a5WIFI\u7684BSSID\u4fe1\u606f", wifiBSSID);
            return wifiBSSID;
        }
        return "";
    }

    public static List<String> getWifiBSSIDList() {
        return getWifiBSSIDList(sContext);
    }

    public static List<String> getWifiBSSIDList(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            List<String> wifiBSSIDList = CoreInfo.Device.getWifiBSSIDList(context);
            onPrivacyInfoCalled("WifiList", "\u9644\u8fd1WIFI\u4fe1\u606f", "BSSID: " + com.jingdong.sdk.baseinfo.c.e.a(wifiBSSIDList, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.9
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return (String) obj;
                }
            }));
            return wifiBSSIDList;
        }
        return new ArrayList();
    }

    public static Map<String, String> getWifiBssidAndSsidMap() {
        return getWifiBssidAndSsidMap(sContext);
    }

    public static Map<String, String> getWifiBssidAndSsidMap(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            Map<String, String> wifiBssidAndSsidMap = CoreInfo.Device.getWifiBssidAndSsidMap(context);
            onPrivacyInfoCalled("WifiList", "\u9644\u8fd1WIFI\u4fe1\u606f", com.jingdong.sdk.baseinfo.c.e.a(wifiBssidAndSsidMap.entrySet(), new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.11
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    Map.Entry entry = (Map.Entry) obj;
                    return "(BSSID:" + ((String) entry.getKey()) + ",SSID:" + ((String) entry.getValue()) + ")";
                }
            }));
            return wifiBssidAndSsidMap;
        }
        return new HashMap();
    }

    public static WifiInfo getWifiInfoByNetworkCallback() {
        return com.jingdong.sdk.baseinfo.a.b.a().f14680c;
    }

    public static WifiInfo getWifiInfoCache() {
        return com.jingdong.sdk.baseinfo.a.b.a().b;
    }

    public static WifiInfo getWifiInfoCacheOrFromSystem() {
        if (isAgreedPrivacy()) {
            return !isAppForeground() ? com.jingdong.sdk.baseinfo.a.b.a().b : com.jingdong.sdk.baseinfo.a.b.a().g(sContext);
        }
        return null;
    }

    public static int getWifiLinkSpeed() {
        return getWifiLinkSpeed(sContext);
    }

    public static int getWifiLinkSpeed(Context context) {
        WifiInfo wifiInfo;
        if (isAgreedPrivacy() && isAppForeground()) {
            int wifiLinkSpeed = CoreInfo.Device.getWifiLinkSpeed(context);
            return (wifiLinkSpeed != -1 || (wifiInfo = com.jingdong.sdk.baseinfo.a.b.a().f14680c) == null) ? wifiLinkSpeed : wifiInfo.getLinkSpeed();
        }
        return -1;
    }

    @Deprecated
    public static List<String> getWifiList(Context context) {
        return getWifiBSSIDList(context);
    }

    @Deprecated
    public static String getWifiMacAddress() {
        if (Build.VERSION.SDK_INT >= 30) {
            return "";
        }
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sWifiMacAddress)) {
                String wifiMacAddress = CoreInfo.Device.getWifiMacAddress(sContext);
                sWifiMacAddress = wifiMacAddress;
                onPrivacyInfoCalled("MacAddress", "MAC\u5730\u5740", wifiMacAddress);
            }
            return sWifiMacAddress;
        }
        return sWifiMacAddress;
    }

    @Deprecated
    public static String getWifiMacAddressForDeviceFinger() {
        if (CoreInfo.sensitiveApi != null && isAgreedPrivacy() && isAppForeground()) {
            if (TextUtils.isEmpty(sWifiMacAddress2)) {
                String wifiMacAddressForDeviceFinger = CoreInfo.Device.getWifiMacAddressForDeviceFinger(sContext);
                sWifiMacAddress2 = wifiMacAddressForDeviceFinger;
                onPrivacyInfoCalled("MacAddress", "MAC\u5730\u5740", wifiMacAddressForDeviceFinger);
            }
            return sWifiMacAddress2;
        }
        return sWifiMacAddress2;
    }

    @Deprecated
    public static String getWifiMacAddressFromCacheOrSystem() {
        return getWifiMacAddress();
    }

    public static int getWifiRssi() {
        if (isAgreedPrivacy() && isAppForeground() && com.jingdong.sdk.baseinfo.a.b.a().c(sContext)) {
            WifiInfo wifiInfoByNetworkCallback = getWifiInfoByNetworkCallback();
            if (wifiInfoByNetworkCallback == null) {
                wifiInfoByNetworkCallback = getWifiInfoCacheOrFromSystem();
            }
            if (wifiInfoByNetworkCallback != null) {
                return wifiInfoByNetworkCallback.getRssi();
            }
            return 0;
        }
        return 0;
    }

    public static int getWifiRssi(Context context) {
        return getWifiRssi();
    }

    public static int getWifiRssiFromWifiManager() {
        WifiInfo e2;
        if (isAgreedPrivacy() && isAppForeground() && (e2 = com.jingdong.sdk.baseinfo.a.b.a().e(sContext)) != null) {
            return e2.getRssi();
        }
        return 0;
    }

    public static String getWifiSSID() {
        return getWifiSSID(sContext);
    }

    public static String getWifiSSID(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            String wifiSSID = CoreInfo.Device.getWifiSSID(context);
            onPrivacyInfoCalled("WifiSSID", "\u5f53\u524d\u8fde\u63a5WIFI\u7684SSID\u4fe1\u606f", wifiSSID);
            return wifiSSID;
        }
        return "";
    }

    public static List<String> getWifiSSIDList() {
        return getWifiSSIDList(sContext);
    }

    public static List<String> getWifiSSIDList(Context context) {
        if (isAgreedPrivacy() && isAppForeground()) {
            List<String> wifiSSIDList = CoreInfo.Device.getWifiSSIDList(context);
            onPrivacyInfoCalled("WifiList", "\u9644\u8fd1WIFI\u4fe1\u606f", "SSID: " + com.jingdong.sdk.baseinfo.c.e.a(wifiSSIDList, new e.b() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.10
                @Override // com.jingdong.sdk.baseinfo.c.e.b
                public final String toString(Object obj) {
                    return (String) obj;
                }
            }));
            return wifiSSIDList;
        }
        return new ArrayList();
    }

    @Deprecated
    public static synchronized void init(Context context) {
        synchronized (BaseInfo.class) {
            init(new BaseInfoConfig.Builder().setContext(context).build(), false);
        }
    }

    @Deprecated
    public static synchronized void init(Context context, boolean z) {
        synchronized (BaseInfo.class) {
            init(new BaseInfoConfig.Builder().setContext(context).build(), z);
        }
    }

    public static synchronized void init(BaseInfoConfig baseInfoConfig, boolean z) {
        synchronized (BaseInfo.class) {
            if (hasInitialized) {
                return;
            }
            try {
                Logger.enableLogger(z);
            } catch (Exception e2) {
                Logger.e(TAG, "", e2);
            }
            if (baseInfoConfig == null) {
                return;
            }
            if (baseInfoConfig.getContext() == null) {
                return;
            }
            String str = "init BaseInfo sdk: context=" + baseInfoConfig.getContext() + ", enableLogger=" + z;
            sConfig = baseInfoConfig;
            sContext = baseInfoConfig.getContext();
            sIPrivacyCheck = baseInfoConfig.getPrivacyCheck();
            sIBackForegroundCheck = baseInfoConfig.getBackForegroundCheck();
            sIDensityRelateCheck = baseInfoConfig.getDensityRelateCheck();
            sIBuildConfigGetter = baseInfoConfig.getBuildConfigGetter();
            sIPrivacyInfoCallback = baseInfoConfig.getPrivacyInfoCallback();
            dataCacheScheme = baseInfoConfig.getDataCacheScheme();
            setAndroidIdSupplierInCoreInfo();
            com.jingdong.sdk.baseinfo.a.b a = com.jingdong.sdk.baseinfo.a.b.a();
            Context context = sContext;
            com.jingdong.sdk.baseinfo.a.b.b(context);
            a.a(context);
            if (baseInfoConfig.getSensitiveApi() != null) {
                CoreInfo.sensitiveApi = baseInfoConfig.getSensitiveApi();
                Logger.d(TAG, "set SensitiveApiImpl " + baseInfoConfig.getSensitiveApi().getClass().getName());
            }
            boolean z2 = !com.jingdong.sdk.baseinfo.c.b.a(Process.myPid()).contains(":");
            isMainProcess = z2;
            if (z2 && sIPrivacyInfoCallback != null) {
                com.jingdong.sdk.baseinfo.b.b.a();
                a.a(sContext);
                AppForeBackgroundSwitchMonitor.init((Application) sContext.getApplicationContext());
            }
            hasInitialized = true;
        }
    }

    public static boolean isAgreedPrivacy() {
        IPrivacyCheck iPrivacyCheck = sIPrivacyCheck;
        return iPrivacyCheck != null && iPrivacyCheck.isUserAgreed();
    }

    public static boolean isAppForeground() {
        String str;
        if (sIBackForegroundCheck == null) {
            str = "mIBackForegroundCheck is null";
        } else {
            str = "mIBackForegroundCheck.isAppForeground() -->  " + sIBackForegroundCheck.isAppForeground();
        }
        Logger.i(TAG, str);
        IBackForegroundCheck iBackForegroundCheck = sIBackForegroundCheck;
        return iBackForegroundCheck != null && iBackForegroundCheck.isAppForeground();
    }

    public static boolean isBluetoothAvailabel() {
        if (!NeedAgreePrivacyField.isBluetoothAvailable || isAgreedPrivacy()) {
            return CoreInfo.Device.isBluetoothAvailabel();
        }
        return false;
    }

    public static boolean isBluetoothEnabled() {
        if (!NeedAgreePrivacyField.isBluetoothEnabled || isAgreedPrivacy()) {
            return CoreInfo.Device.isBluetoothEnabled();
        }
        return false;
    }

    @TargetApi(23)
    public static boolean isFingerprintAvailable() {
        return CoreInfo.Device.isFingerprintAvailable(sContext);
    }

    public static boolean isGPSAvailable() {
        return CoreInfo.Device.isGPSAvailable(sContext);
    }

    public static boolean isNFCAvailable() {
        return isNFCAvailable(sContext);
    }

    public static boolean isNFCAvailable(Context context) {
        return CoreInfo.Device.isNFCAvailable(context);
    }

    public static boolean isNFCEnabled() {
        return isNFCEnabled(sContext);
    }

    public static boolean isNFCEnabled(Context context) {
        return CoreInfo.Device.isNFCEnabled(context);
    }

    @TargetApi(17)
    public static boolean isNavigationBarVisible(Activity activity) {
        return CoreInfo.App.isNavigationBarVisible(activity);
    }

    public static boolean isPkgInstalled(Context context, String str) {
        if (isAgreedPrivacy() && isAppForeground()) {
            return CoreInfo.System.isPkgInstalled(context, str);
        }
        return false;
    }

    public static boolean isPkgInstalled(String str) {
        return isPkgInstalled(sContext, str);
    }

    public static boolean isQEmuDriverFile() {
        return CoreInfo.Device.isQEmuDriverFile();
    }

    public static boolean isRoot() {
        return CoreInfo.Device.isRoot();
    }

    public static boolean isSensorAvailable(int i2) {
        if (isAgreedPrivacy()) {
            return CoreInfo.Device.isSensorAvailable(sContext, i2);
        }
        return false;
    }

    public static boolean isStorageRemovable() {
        return CoreInfo.Device.isStorageRemovable(sContext);
    }

    public static void onPrivacyInfoCalled(String str, String str2, String str3) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && isMainProcess && sIPrivacyInfoCallback != null) {
                Logger.d(TAG, "onPrivacyCalled: " + str2 + ",value=" + str3);
                long currentTimeMillis = System.currentTimeMillis();
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                if (sIPrivacyInfoCallback.isNeedRecord(str, str2, str3, currentTimeMillis)) {
                    String userPin = sIPrivacyInfoCallback.getUserPin();
                    int privacyMethodRecordSwitch = getConfig().getPrivacyMethodRecordSwitch();
                    if (privacyMethodRecordSwitch == 2) {
                        com.jingdong.sdk.baseinfo.b.b.a(str, str2, str3, userPin, currentTimeMillis);
                    } else if (privacyMethodRecordSwitch == 1) {
                        a.a(str, str2, str3, userPin, currentTimeMillis);
                    }
                }
            }
        } catch (Exception e2) {
            Logger.e(TAG, "", e2);
        }
    }

    public static void registerNetworkCallback() {
        try {
            final com.jingdong.sdk.baseinfo.a.b a = com.jingdong.sdk.baseinfo.a.b.a();
            Context context = sContext;
            if (a.a) {
                return;
            }
            a.a = true;
            if (Build.VERSION.SDK_INT >= 29) {
                ((ConnectivityManager) context.getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().addTransportType(0).addTransportType(1).addTransportType(5).build(), new ConnectivityManager.NetworkCallback() { // from class: com.jingdong.sdk.baseinfo.a.b.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                        TransportInfo transportInfo = networkCapabilities.getTransportInfo();
                        if (Logger.D) {
                            Logger.d("BaseInfo.Network", "onCapabilitiesChanged TransportInfo:".concat(String.valueOf(transportInfo)));
                        }
                        if (transportInfo instanceof WifiInfo) {
                            a.f14680c = (WifiInfo) transportInfo;
                        }
                    }
                });
                Logger.d("BaseInfo.Network", "registerNetworkCallback");
            }
        } catch (Exception e2) {
            Logger.e(TAG, "", e2);
        }
    }

    static void setAndroidIdSupplierInCoreInfo() {
        AndroidUtil.androidIdSupplier = new Supplier() { // from class: com.jingdong.sdk.baseinfo.b
            @Override // com.jd.android.sdk.coreinfo.util.Supplier
            public final Object get() {
                String androidId0;
                androidId0 = BaseInfo.getAndroidId0();
                return androidId0;
            }
        };
    }

    public static void setAppPackageType(int i2) {
        appPackageType = i2;
    }

    @Deprecated
    public static void setBackForegroundCheckUtil(IBackForegroundCheck iBackForegroundCheck) {
        if (sIBackForegroundCheck == null) {
            sIBackForegroundCheck = iBackForegroundCheck;
        }
    }

    @Deprecated
    public static void setBuildConfigGetter(IBuildConfigGetter iBuildConfigGetter) {
        if (sIBuildConfigGetter == null) {
            sIBuildConfigGetter = iBuildConfigGetter;
        }
    }

    @Deprecated
    public static void setDensityRelateCheck(IDensityRelateCheck iDensityRelateCheck) {
        if (sIDensityRelateCheck == null) {
            sIDensityRelateCheck = iDensityRelateCheck;
        }
    }

    @Deprecated
    public static void setPrivacyCheckUtil(IPrivacyCheck iPrivacyCheck) {
        if (sIPrivacyCheck == null) {
            sIPrivacyCheck = iPrivacyCheck;
        }
    }

    public static void setUserAgent(String str) {
        CoreInfo.Device.setUserAgent(str);
    }

    public static void startRequestOaidInfo(final OaidInfoRequestListener oaidInfoRequestListener) {
        if (isAgreedPrivacy()) {
            Logger.d(TAG, "startRequestOaidInfo(), isAgreedPrivacy true");
            OaidManager.getInstance().startRequestOaidInfo(sContext, new OaidInfoRequestListener() { // from class: com.jingdong.sdk.baseinfo.BaseInfo.1
                @Override // com.jd.android.sdk.oaid.OaidInfoRequestListener
                public final void onResult(OaidInfo oaidInfo) {
                    BaseInfo.onPrivacyInfoCalled("OAID", "OAID", oaidInfo.getOAID());
                    OaidInfoRequestListener oaidInfoRequestListener2 = oaidInfoRequestListener;
                    if (oaidInfoRequestListener2 != null) {
                        oaidInfoRequestListener2.onResult(oaidInfo);
                    }
                }
            });
        }
    }

    public static boolean useOriginalUIRelated() {
        if (sIDensityRelateCheck == null) {
            Logger.i(TAG, "mIDensityRelateCheck is null");
            return isAgreedPrivacy();
        }
        Logger.i(TAG, "mIDensityRelateCheck.isOriginalCall() -->  " + sIDensityRelateCheck.isOriginalCall());
        return sIDensityRelateCheck.isOriginalCall();
    }
}
