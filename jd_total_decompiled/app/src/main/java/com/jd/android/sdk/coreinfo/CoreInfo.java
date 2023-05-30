package com.jd.android.sdk.coreinfo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.telephony.CellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.android.sdk.coreinfo.deviceUtil.a;
import com.jd.android.sdk.coreinfo.deviceUtil.d;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.android.sdk.coreinfo.util.Supplier;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CoreInfo {
    private static final String TAG = "BaseInfo.CoreInfo";
    public static SensitiveApi sensitiveApi;

    /* loaded from: classes.dex */
    public static class System {
        public static int getAndroidSDKVersion() {
            int g2 = c.g();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAndroidSDKVersion() --> ".concat(String.valueOf(g2)));
            return g2;
        }

        public static String getAndroidVersion() {
            String f2 = c.f();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAndroidVersion() --> ".concat(String.valueOf(f2)));
            return f2;
        }

        public static String getCountry(Context context) {
            String a = c.a(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCountry() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static long getElapsedRealtime() {
            long h2 = c.h();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getElapsedRealtime() --> ".concat(String.valueOf(h2)));
            return h2;
        }

        public static List<PackageInfo> getInstalledPkgs(Context context, int i2) {
            List<PackageInfo> a = c.a(context, i2);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getInstalledPkgs() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static String getLanguage(Context context) {
            String b = c.b(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getLanguage() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static String getOSFingerprint() {
            String d = c.d();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getOSFingerprint() --> ".concat(String.valueOf(d)));
            return d;
        }

        public static String getOSName() {
            String a = c.a();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getOSName() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static String getOSVersionTags() {
            String c2 = c.c();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getOSVersionTags() --> ".concat(String.valueOf(c2)));
            return c2;
        }

        public static String getOSVersionType() {
            String b = c.b();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getOSVersionType() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static String getRomName() {
            String e2 = c.e();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRomName() --> ".concat(String.valueOf(e2)));
            return e2;
        }

        public static String getTimeZoneID() {
            String i2 = c.i();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getTimeZoneID() --> ".concat(String.valueOf(i2)));
            return i2;
        }

        public static boolean isPkgInstalled(Context context, String str) {
            boolean a = c.a(context, str);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isPkgInstalled() --> ".concat(String.valueOf(a)));
            return a;
        }
    }

    /* loaded from: classes.dex */
    public static class App {
        public static String getAppName(Context context) {
            String a = a.a(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAppName() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static long getFirstInstallTime(Context context) {
            long e2 = a.e(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getFirstInstallTime() --> ".concat(String.valueOf(e2)));
            return e2;
        }

        public static long getLastUpdateTime(Context context) {
            long f2 = a.f(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getLastUpdateTime() --> ".concat(String.valueOf(f2)));
            return f2;
        }

        public static String getPackageName(Context context) {
            String b = a.b(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getPackageName() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses(Context context) {
            List<ActivityManager.RunningAppProcessInfo> i2 = a.i(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRunningAppProcesses() --> ".concat(String.valueOf(i2)));
            return i2;
        }

        public static List<ActivityManager.RunningServiceInfo> getRunningServices(Context context, int i2) {
            List<ActivityManager.RunningServiceInfo> a = a.a(context, i2);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRunningServices() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context, int i2) {
            List<ActivityManager.RunningTaskInfo> b = a.b(context, i2);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRunningTasks() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static long getSignatureHash(Context context) {
            long g2 = a.g(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSignatureHash() --> ".concat(String.valueOf(g2)));
            return g2;
        }

        public static int getStatusBarHeight(Context context) {
            int h2 = a.h(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getStatusBarHeight() --> ".concat(String.valueOf(h2)));
            return h2;
        }

        public static int getVersionCode(Context context) {
            int d = a.d(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getVersionCode() --> ".concat(String.valueOf(d)));
            return d;
        }

        public static String getVersionName(Context context) {
            String c2 = a.c(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getVersionName() --> ".concat(String.valueOf(c2)));
            return c2;
        }

        @TargetApi(17)
        public static boolean isNavigationBarVisible(Activity activity) {
            boolean a = a.a(activity);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isNavigationBarVisible() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static List<ActivityManager.RunningServiceInfo> getRunningServices(Context context) {
            return getRunningServices(context, Integer.MAX_VALUE);
        }

        public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context) {
            return getRunningTasks(context, Integer.MAX_VALUE);
        }
    }

    /* loaded from: classes.dex */
    public static class Device {
        public static Supplier<ArrayList<NetworkInterface>> networkInterfacesSupplier;
        public static Supplier<WifiInfo> wifiInfoSupplier;
        public static Supplier<List<ScanResult>> wifiListSupplier;

        public static boolean checkPipes() {
            boolean D = b.D();
            Logger.debugWithStackTrace(CoreInfo.TAG, "checkPipes() --> ".concat(String.valueOf(D)));
            return D;
        }

        public static String getAndroidId(Context context) {
            String x = b.x(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAndroidId() --> ".concat(String.valueOf(x)));
            return x;
        }

        public static String getAndroidIdForDeviceFinger(Context context) {
            String y = b.y(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAndroidIdForDeviceFinger() --> ".concat(String.valueOf(y)));
            return y;
        }

        public static long getAvailableInternalMemorySize(Context context) {
            long M = b.M();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getAvailableInternalMemorySize() --> ".concat(String.valueOf(M)));
            return M;
        }

        public static int getBatteryLevel(Context context) {
            int R = b.R(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBatteryLevel() --> ".concat(String.valueOf(R)));
            return R;
        }

        public static int getBatteryPlugged(Context context) {
            int Q = b.Q(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBatteryPlugged() --> ".concat(String.valueOf(Q)));
            return Q;
        }

        public static int getBatteryScale(Context context) {
            int S = b.S(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBatteryScale() --> ".concat(String.valueOf(S)));
            return S;
        }

        public static int getBatteryStatus(Context context) {
            int P = b.P(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBatteryStatus() --> ".concat(String.valueOf(P)));
            return P;
        }

        public static String getBluetoothMac(Context context) {
            String N = b.N(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBluetoothMac() --> ".concat(String.valueOf(N)));
            return N;
        }

        public static String getBluetoothName(Context context) {
            String L = b.L();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBluetoothName() --> ".concat(String.valueOf(L)));
            return L;
        }

        public static String getBoard() {
            String f2 = b.f();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBoard() --> ".concat(String.valueOf(f2)));
            return f2;
        }

        public static String getBoardPlatform() {
            String g2 = b.g();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBoardPlatform() --> ".concat(String.valueOf(g2)));
            return g2;
        }

        public static String getBootloaderVersion() {
            String l2 = b.l();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBootloaderVersion() --> ".concat(String.valueOf(l2)));
            return l2;
        }

        public static String getBrand() {
            String d = b.d();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getBrand() --> ".concat(String.valueOf(d)));
            return d;
        }

        public static String getCPUMaxFreq() {
            String s = b.s();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCPUMaxFreq() --> ".concat(String.valueOf(s)));
            return s;
        }

        public static String getCPUNum() {
            String p = b.p();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCPUNum() --> ".concat(String.valueOf(p)));
            return p;
        }

        public static String getCPUSerialNo() {
            String o = b.o();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCPUSerialNo() --> ".concat(String.valueOf(o)));
            return o;
        }

        public static int getCellId(Context context) {
            b.E();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCellId() --> -1");
            return -1;
        }

        public static CellLocation getCellLocationForDeviceFinger(Context context) {
            CellLocation F = b.F();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCellLocationForDeviceFinger() --> ".concat(String.valueOf(F)));
            return F;
        }

        public static String getCpuCurFreq() {
            String t = b.t();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCpuCurFreq() --> ".concat(String.valueOf(t)));
            return t;
        }

        public static String getCpuMinFreq() {
            String r = b.r();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCpuMinFreq() --> ".concat(String.valueOf(r)));
            return r;
        }

        public static String getCpuName() {
            String q = b.q();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getCpuName() --> ".concat(String.valueOf(q)));
            return q;
        }

        public static float getDensity(Context context) {
            float g2 = b.g(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDensity() --> ".concat(String.valueOf(g2)));
            return g2;
        }

        public static String getDensityDpi(Context context) {
            String valueOf = String.valueOf(getDensityDpiInt(context));
            Logger.d(CoreInfo.TAG, "getDensityDpi() --> ".concat(String.valueOf(valueOf)));
            return valueOf;
        }

        public static int getDensityDpiInt(Context context) {
            int i2 = b.i(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDensityDpiInt() --> ".concat(String.valueOf(i2)));
            return i2;
        }

        public static String getDeviceId(Context context) {
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDeviceId() --> ".concat(""));
            return "";
        }

        public static String getDeviceIdForDeviceFinger(Context context) {
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDeviceIdForDeviceFinger() --> ".concat(""));
            return "";
        }

        public static String getDeviceName() {
            String a = b.a();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDeviceName() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static String getDisplayMetrics(Context context) {
            String l2 = b.l(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDisplayMetrics() --> ".concat(String.valueOf(l2)));
            return l2;
        }

        public static DisplayMetrics getDisplayMetricsObject(Context context) {
            DisplayMetrics m2 = b.m(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDisplayMetricsObject() --> ".concat(String.valueOf(m2)));
            return m2;
        }

        public static String getDisplayMetricsWithNavigationBar(Context context) {
            String c0065a = com.jd.android.sdk.coreinfo.deviceUtil.a.a(context).toString();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getDisplayMetricsWithNavigationBar() --> ".concat(String.valueOf(c0065a)));
            return c0065a;
        }

        public static long getExternalStorageSize() {
            long x = b.x();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getExternalStorageSize() --> ".concat(String.valueOf(x)));
            return x;
        }

        public static int getGateway(Context context) {
            int v = b.v(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getGateway() --> ".concat(String.valueOf(v)));
            return v;
        }

        public static String getHardwareName() {
            String j2 = b.j();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getHardwareName() --> ".concat(String.valueOf(j2)));
            return j2;
        }

        public static String getHardwareSerialNo() {
            String k2 = b.k();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getHardwareSerialNo() --> ".concat(String.valueOf(k2)));
            return k2;
        }

        public static String getHostName() {
            String m2 = b.m();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getHostName() --> ".concat(String.valueOf(m2)));
            return m2;
        }

        public static String getIpAddressFromWifiInfo(Context context) {
            String L = b.L(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getIpAddressFromWifiInfo() --> ".concat(String.valueOf(L)));
            return L;
        }

        public static String getLinuxVersion() {
            String n2 = b.n();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getLinuxVersion() --> ".concat(String.valueOf(n2)));
            return n2;
        }

        public static String getManufacture() {
            String c2 = b.c();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getManufacture() --> ".concat(String.valueOf(c2)));
            return c2;
        }

        public static long getMemAvailSize(Context context) {
            long f2 = b.f(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getMemAvailSize() --> ".concat(String.valueOf(f2)));
            return f2;
        }

        public static String[] getMemInfo() {
            String[] N = b.N();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getMemInfo() --> ".concat(String.valueOf(N)));
            return N;
        }

        public static long getMemState(Context context) {
            long O = b.O(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getMemState() --> ".concat(String.valueOf(O)));
            return O;
        }

        public static long getMemTotalSize(Context context) {
            long e2 = b.e(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getMemTotalSize() --> ".concat(String.valueOf(e2)));
            return e2;
        }

        public static String getModel() {
            String e2 = b.e();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getModel() --> ".concat(String.valueOf(e2)));
            return e2;
        }

        @Deprecated
        public static String getNetAddressInfo() {
            String H = b.H();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetAddressInfo() --> ".concat(String.valueOf(H)));
            return H;
        }

        public static String[][] getNetAddresses() {
            String[][] I = b.I();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetAddresses() --> ".concat(String.valueOf(I)));
            return I;
        }

        public static List<String> getNetAddressesForIPv4() {
            List<String> J = b.J();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetAddressesForIPv4() --> ".concat(String.valueOf(J)));
            return J;
        }

        public static List<String> getNetAddressesForIPv6() {
            List<String> K = b.K();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetAddressesForIPv6() --> ".concat(String.valueOf(K)));
            return K;
        }

        public static int getNetmask(Context context) {
            int w = b.w(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetmask() --> ".concat(String.valueOf(w)));
            return w;
        }

        public static String getNetworkISO(Context context) {
            String K = b.K(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkISO() --> ".concat(String.valueOf(K)));
            return K;
        }

        public static ArrayList<NetworkInterface> getNetworkInterfaces() {
            ArrayList<NetworkInterface> G = b.G();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkInterfaces() --> ".concat(String.valueOf(G)));
            return G;
        }

        public static String getNetworkOperator(Context context) {
            String z = b.z(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkOperator() --> ".concat(String.valueOf(z)));
            return z;
        }

        public static String getNetworkOperatorName(Context context) {
            String A = b.A(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkOperatorName() --> ".concat(String.valueOf(A)));
            return A;
        }

        public static String getNetworkType(Context context) {
            String s = b.s(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkType() --> ".concat(String.valueOf(s)));
            return s;
        }

        public static String getNetworkTypeForDeviceFinger(Context context) {
            String u = b.u(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkTypeForDeviceFinger() --> ".concat(String.valueOf(u)));
            return u;
        }

        @Deprecated
        public static int getNetworkTypeInt(Context context) {
            int t = b.t(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getNetworkTypeInt() --> ".concat(String.valueOf(t)));
            return t;
        }

        public static String getProductName() {
            String b = b.b();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getProductName() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static String getRadioVersion() {
            String i2 = b.i();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRadioVersion() --> ".concat(String.valueOf(i2)));
            return i2;
        }

        public static ScreenSize getRealScreenSize(Context context) {
            a.C0065a a = com.jd.android.sdk.coreinfo.deviceUtil.a.a(context);
            ScreenSize screenSize = new ScreenSize(a.a, a.b);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRealScreenSize() --> ".concat(String.valueOf(screenSize)));
            return screenSize;
        }

        public static long getRomSize() {
            long w = b.w();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getRomSize() --> ".concat(String.valueOf(w)));
            return w;
        }

        public static String getSDCardId() {
            String v = b.v();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSDCardId() --> ".concat(String.valueOf(v)));
            return v;
        }

        public static float getScaledDensity(Context context) {
            float h2 = b.h(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getScaledDensity() --> ".concat(String.valueOf(h2)));
            return h2;
        }

        public static int getScreenHeight(Context context) {
            int k2 = b.k(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getScreenHeight() --> ".concat(String.valueOf(k2)));
            return k2;
        }

        public static int getScreenHeight2() {
            int z = b.z();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getScreenHeight2() --> ".concat(String.valueOf(z)));
            return z;
        }

        public static int getScreenWidth(Context context) {
            int j2 = b.j(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getScreenWidth() --> ".concat(String.valueOf(j2)));
            return j2;
        }

        public static int getScreenWidth2() {
            int y = b.y();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getScreenWidth2() --> ".concat(String.valueOf(y)));
            return y;
        }

        public static List<Sensor> getSensorList(Context context) {
            List<Sensor> M = b.M(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSensorList() --> ".concat(String.valueOf(M)));
            return M;
        }

        public static String getSimCountryIso(Context context) {
            String d = b.d(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSimCountryIso() --> ".concat(String.valueOf(d)));
            return d;
        }

        public static String getSimOperator(Context context) {
            String b = b.b(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSimOperator() --> ".concat(String.valueOf(b)));
            return b;
        }

        public static String getSimOperatorName(Context context) {
            String c2 = b.c(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSimOperatorName() --> ".concat(String.valueOf(c2)));
            return c2;
        }

        public static String getSimSerialNo(Context context) {
            String a = b.a(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSimSerialNo() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static String getSubscriberId(Context context) {
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSubscriberId() --> ".concat(""));
            return "";
        }

        public static String getSubscriberIdForDeviceFinger(Context context) {
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSubscriberIdForDeviceFinger() --> ".concat(""));
            return "";
        }

        public static String[] getSuppportedABIs() {
            String[] h2 = b.h();
            Logger.debugWithStackTrace(CoreInfo.TAG, "getSuppportedABIs() --> ".concat(String.valueOf(h2)));
            return h2;
        }

        public static String getUserAgent(Context context, long j2) {
            String a = d.a(context, j2);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getUserAgent() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static String getWifiBSSID(Context context) {
            String D = b.D(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiBSSID() --> ".concat(String.valueOf(D)));
            return D;
        }

        public static List<String> getWifiBSSIDList(Context context) {
            List<String> G = b.G(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiBSSIDList() --> ".concat(String.valueOf(G)));
            return G;
        }

        public static Map<String, String> getWifiBssidAndSsidMap(Context context) {
            Map<String, String> I = b.I(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiBssidAndSsidMap() --> ".concat(String.valueOf(I)));
            return I;
        }

        public static WifiInfo getWifiInfo(Context context) {
            WifiInfo B = b.B(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiInfo() --> ".concat(String.valueOf(B)));
            if (!Logger.printStack) {
                Logger.d(CoreInfo.TAG, Logger.getStackTrace());
            }
            return B;
        }

        public static int getWifiLinkSpeed(Context context) {
            int J = b.J(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiLinkSpeed() --> ".concat(String.valueOf(J)));
            return J;
        }

        public static List<String> getWifiList(Context context) {
            List<String> G = b.G(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiList() --> ".concat(String.valueOf(G)));
            return G;
        }

        @Deprecated
        public static String getWifiMacAddress(Context context) {
            SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
            String wifiMacAddress = sensitiveApi != null ? sensitiveApi.getWifiMacAddress(context) : "";
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiMacAddress() --> ".concat(String.valueOf(wifiMacAddress)));
            return wifiMacAddress;
        }

        @Deprecated
        public static String getWifiMacAddressForDeviceFinger(Context context) {
            SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
            String wifiMacAddressForDeviceFinger = sensitiveApi != null ? sensitiveApi.getWifiMacAddressForDeviceFinger(context) : "";
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiMacAddressForDeviceFinger() --> ".concat(String.valueOf(wifiMacAddressForDeviceFinger)));
            return wifiMacAddressForDeviceFinger;
        }

        @Deprecated
        public static String getWifiMacAddressOver23() {
            SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
            return sensitiveApi != null ? sensitiveApi.getWifiMacAddressOver23() : "";
        }

        public static int getWifiRssi(Context context) {
            int F = b.F(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiRssi() --> ".concat(String.valueOf(F)));
            return F;
        }

        public static String getWifiSSID(Context context) {
            String E = b.E(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiSSID() --> ".concat(String.valueOf(E)));
            return E;
        }

        public static List<String> getWifiSSIDList(Context context) {
            List<String> H = b.H(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiSSIDList() --> ".concat(String.valueOf(H)));
            return H;
        }

        public static List<ScanResult> getWifiScanResultList(Context context) {
            List<ScanResult> C = b.C(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getWifiScanResultList() --> ".concat(String.valueOf(C)));
            if (!Logger.printStack) {
                Logger.d(CoreInfo.TAG, Logger.getStackTrace());
            }
            return C;
        }

        public static boolean isBluetoothAvailabel() {
            boolean A = b.A();
            Logger.debugWithStackTrace(CoreInfo.TAG, "isBluetoothAvailabel() --> ".concat(String.valueOf(A)));
            return A;
        }

        public static boolean isBluetoothEnabled() {
            boolean B = b.B();
            Logger.debugWithStackTrace(CoreInfo.TAG, "isBluetoothEnabled() --> ".concat(String.valueOf(B)));
            return B;
        }

        @TargetApi(23)
        public static boolean isFingerprintAvailable(Context context) {
            boolean p = b.p(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isFingerprintAvailable() --> ".concat(String.valueOf(p)));
            return p;
        }

        public static boolean isGPSAvailable(Context context) {
            boolean o = b.o(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isGPSAvailable() --> ".concat(String.valueOf(o)));
            return o;
        }

        public static boolean isNFCAvailable(Context context) {
            boolean q = b.q(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isNFCAvailable() --> ".concat(String.valueOf(q)));
            return q;
        }

        public static boolean isNFCEnabled(Context context) {
            boolean r = b.r(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isNFCEnabled() --> ".concat(String.valueOf(r)));
            return r;
        }

        public static boolean isQEmuDriverFile() {
            boolean C = b.C();
            Logger.debugWithStackTrace(CoreInfo.TAG, "isQEmuDriverFile() --> ".concat(String.valueOf(C)));
            return C;
        }

        public static boolean isRoot() {
            boolean u = b.u();
            Logger.debugWithStackTrace(CoreInfo.TAG, "isRoot() --> ".concat(String.valueOf(u)));
            return u;
        }

        public static boolean isSensorAvailable(Context context, int i2) {
            boolean a = b.a(context, i2);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isSensorAvailable() --> ".concat(String.valueOf(a)));
            return a;
        }

        public static boolean isStorageRemovable(Context context) {
            boolean n2 = b.n(context);
            Logger.debugWithStackTrace(CoreInfo.TAG, "isStorageRemovable() --> ".concat(String.valueOf(n2)));
            return n2;
        }

        public static void setUserAgent(String str) {
            if (!TextUtils.isEmpty(str)) {
                d.a = str;
            }
            Logger.debugWithStackTrace(CoreInfo.TAG, "setUserAgent() --> ".concat(String.valueOf(str)));
        }

        public static String getUserAgent(Context context) {
            String a = d.a(context, 1000L);
            Logger.debugWithStackTrace(CoreInfo.TAG, "getUserAgent() --> ".concat(String.valueOf(a)));
            return a;
        }
    }
}
