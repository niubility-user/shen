package com.jdjr.acr;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.security.keystore.KeyGenParameterSpec;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.view.Display;
import com.jdjr.tools.JDJRLog;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;
import tv.danmaku.ijk.media.pha.JDHPlayerJSEvent;

/* loaded from: classes18.dex */
public class AntiHookManager {
    private static final String TAG = "AntiDebugManager";
    private static ArrayList<HookJavaMethod> g_camerahookArrayList;
    private static ArrayList<HookJavaMethod> g_hookArrayList;
    private static ArrayList<HookJavaMethod> g_locationhookArrayList;
    private static AntiHookManager instance;
    private static HookJavaMethodUtils javaMethoUtils;
    private static final Object lock = new Object();

    static {
        ArrayList<HookJavaMethod> arrayList = new ArrayList<>();
        arrayList.add(new HookJavaMethod(0, Settings.System.class.getName(), "getString", new Class[]{ContentResolver.class, String.class}));
        arrayList.add(new HookJavaMethod(1, SharedPreferences.Editor.class.getName(), "putString", new Class[]{String.class, String.class}));
        arrayList.add(new HookJavaMethod(2, "android.os.SystemProperties", IMantoServerRequester.GET, new Class[]{String.class, String.class}));
        arrayList.add(new HookJavaMethod(4, TelephonyManager.class.getName(), "getSubscriberId", new Class[0]));
        arrayList.add(new HookJavaMethod(5, TelephonyManager.class.getName(), "getLine1Number", new Class[0]));
        arrayList.add(new HookJavaMethod(6, TelephonyManager.class.getName(), "getVoiceMailNumber", new Class[0]));
        arrayList.add(new HookJavaMethod(7, TelephonyManager.class.getName(), "getSimSerialNumber", new Class[0]));
        arrayList.add(new HookJavaMethod(8, TelephonyManager.class.getName(), "getNetworkCountryIso", new Class[0]));
        arrayList.add(new HookJavaMethod(9, TelephonyManager.class.getName(), "getNetworkOperatorName", new Class[0]));
        arrayList.add(new HookJavaMethod(9, TelephonyManager.class.getName(), "getNetworkOperator", new Class[0]));
        arrayList.add(new HookJavaMethod(10, TelephonyManager.class.getName(), "getSimOperatorName", new Class[0]));
        arrayList.add(new HookJavaMethod(11, TelephonyManager.class.getName(), "getSimOperator", new Class[0]));
        arrayList.add(new HookJavaMethod(12, TelephonyManager.class.getName(), "getSimCountryIso", new Class[0]));
        arrayList.add(new HookJavaMethod(13, TelephonyManager.class.getName(), "getPhoneType", new Class[0]));
        arrayList.add(new HookJavaMethod(14, TelephonyManager.class.getName(), "getSimState", new Class[0]));
        arrayList.add(new HookJavaMethod(15, TelephonyManager.class.getName(), "hasIccCard", new Class[0]));
        arrayList.add(new HookJavaMethod(16, TelephonyManager.class.getName(), JDHPlayerJSEvent.GET_NETTYPE, new Class[0]));
        arrayList.add(new HookJavaMethod(17, TelephonyManager.class.getName(), "getCellLocation", new Class[0]));
        arrayList.add(new HookJavaMethod(18, TelephonyManager.class.getName(), "getDeviceSoftwareVersion", new Class[0]));
        arrayList.add(new HookJavaMethod(20, WifiInfo.class.getName(), "getIpAddress", new Class[0]));
        arrayList.add(new HookJavaMethod(21, WifiInfo.class.getName(), "getNetworkId", new Class[0]));
        arrayList.add(new HookJavaMethod(22, WifiInfo.class.getName(), "getSSID", new Class[0]));
        arrayList.add(new HookJavaMethod(23, WifiInfo.class.getName(), "getBSSID", new Class[0]));
        arrayList.add(new HookJavaMethod(24, WifiManager.class.getName(), "getConnectionInfo", new Class[0]));
        arrayList.add(new HookJavaMethod(25, WifiManager.class.getName(), "getDhcpInfo", new Class[0]));
        arrayList.add(new HookJavaMethod(26, WifiManager.class.getName(), "getScanResults", new Class[0]));
        arrayList.add(new HookJavaMethod(27, NetworkInterface.class.getName(), "getNetworkInterfaces", new Class[0]));
        arrayList.add(new HookJavaMethod(28, NetworkInterface.class.getName(), "getHardwareAddress", new Class[0]));
        arrayList.add(new HookJavaMethod(29, Proxy.class.getName(), "getHost", new Class[]{Context.class}));
        arrayList.add(new HookJavaMethod(30, Proxy.class.getName(), "getPort", new Class[]{Context.class}));
        arrayList.add(new HookJavaMethod(31, System.class.getName(), "getProperty", new Class[]{String.class}));
        arrayList.add(new HookJavaMethod(32, PackageManager.class.getName(), "getInstallerPackageName", new Class[]{String.class}));
        String name = PackageManager.class.getName();
        Class cls = Integer.TYPE;
        arrayList.add(new HookJavaMethod(33, name, "getPackageInfo", new Class[]{String.class, cls}));
        arrayList.add(new HookJavaMethod(34, PackageManager.class.getName(), "getInstalledPackages", new Class[]{cls}));
        arrayList.add(new HookJavaMethod(35, File.class.getName(), "getAbsolutePath", new Class[0]));
        arrayList.add(new HookJavaMethod(36, ActivityManager.class.getName(), "getRunningTasks", new Class[]{cls}));
        arrayList.add(new HookJavaMethod(37, ComponentName.class.getName(), "getPackageName", new Class[0]));
        arrayList.add(new HookJavaMethod(38, Modifier.class.getName(), "isNative", new Class[]{cls}));
        arrayList.add(new HookJavaMethod(39, Debug.class.getName(), "isDebuggerConnected", new Class[0]));
        arrayList.add(new HookJavaMethod(40, TimeZone.class.getName(), "getRawOffset", new Class[0]));
        arrayList.add(new HookJavaMethod(41, TimeZone.class.getName(), "getDSTSavings", new Class[0]));
        arrayList.add(new HookJavaMethod(42, Locale.class.getName(), "getLanguage", new Class[0]));
        arrayList.add(new HookJavaMethod(43, Intent.class.getName(), "getIntent", new Class[]{String.class}));
        arrayList.add(new HookJavaMethod(44, Intent.class.getName(), "getExtra", new Class[]{String.class}));
        String name2 = Intent.class.getName();
        Class cls2 = Boolean.TYPE;
        arrayList.add(new HookJavaMethod(45, name2, "getBooleanExtra", new Class[]{String.class, cls2}));
        arrayList.add(new HookJavaMethod(46, Intent.class.getName(), "getByteExtra", new Class[]{String.class, Byte.TYPE}));
        arrayList.add(new HookJavaMethod(47, Intent.class.getName(), "getShortExtra", new Class[]{String.class, Short.TYPE}));
        arrayList.add(new HookJavaMethod(48, Intent.class.getName(), "getCharExtra", new Class[]{String.class, Character.TYPE}));
        arrayList.add(new HookJavaMethod(49, Intent.class.getName(), "getIntExtra", new Class[]{String.class, cls}));
        String name3 = Intent.class.getName();
        Class cls3 = Long.TYPE;
        arrayList.add(new HookJavaMethod(50, name3, "getLongExtra", new Class[]{String.class, cls3}));
        String name4 = Intent.class.getName();
        Class cls4 = Float.TYPE;
        arrayList.add(new HookJavaMethod(51, name4, "getFloatExtra", new Class[]{String.class, cls4}));
        arrayList.add(new HookJavaMethod(52, Intent.class.getName(), "getDoubleExtra", new Class[]{String.class, Double.TYPE}));
        arrayList.add(new HookJavaMethod(53, Intent.class.getName(), "getStringExtra", new Class[]{String.class}));
        arrayList.add(new HookJavaMethod(54, Display.class.getName(), "getWidth", new Class[0]));
        arrayList.add(new HookJavaMethod(55, Display.class.getName(), "getHeight", new Class[0]));
        arrayList.add(new HookJavaMethod(57, BluetoothAdapter.class.getName(), "getName", new Class[0]));
        arrayList.add(new HookJavaMethod(58, Settings.Secure.class.getName(), "getString", new Class[]{ContentResolver.class, String.class}));
        arrayList.add(new HookJavaMethod(59, ActivityManager.class.getName(), "getMemoryInfo", new Class[]{ActivityManager.MemoryInfo.class}));
        arrayList.add(new HookJavaMethod(60, StatFs.class.getName(), "getBlockSize", new Class[0]));
        arrayList.add(new HookJavaMethod(61, StatFs.class.getName(), "getBlockSizeLong", new Class[0]));
        arrayList.add(new HookJavaMethod(62, StatFs.class.getName(), "getBlockCount", new Class[0]));
        arrayList.add(new HookJavaMethod(63, StatFs.class.getName(), "getBlockCountLong", new Class[0]));
        arrayList.add(new HookJavaMethod(64, StatFs.class.getName(), "getAvailableBlocks", new Class[0]));
        arrayList.add(new HookJavaMethod(65, StatFs.class.getName(), "getAvailableBlocksLong", new Class[0]));
        arrayList.add(new HookJavaMethod(66, InetAddress.class.getName(), "isLoopbackAddress", new Class[0]));
        arrayList.add(new HookJavaMethod(67, InetAddress.class.getName(), "getCanonicalHostName", new Class[0]));
        arrayList.add(new HookJavaMethod(68, InetAddress.class.getName(), "getHostAddress", new Class[0]));
        arrayList.add(new HookJavaMethod(69, InetAddress.class.getName(), "getHostName", new Class[0]));
        arrayList.add(new HookJavaMethod(70, InetAddress.class.getName(), "getLocalHost", new Class[0]));
        if (Build.VERSION.SDK_INT >= 23) {
            arrayList.add(new HookJavaMethod(71, KeyGenParameterSpec.Builder.class.getName(), "setUserAuthenticationRequired", new Class[]{cls2}));
            arrayList.add(new HookJavaMethod(72, KeyGenParameterSpec.Builder.class.getName(), "setUserAuthenticationValidityDurationSeconds", new Class[]{cls}));
        }
        g_hookArrayList = arrayList;
        ArrayList<HookJavaMethod> arrayList2 = new ArrayList<>();
        arrayList2.add(new HookJavaMethod(0, LocationManager.class.getName(), "getBestProvider", new Class[]{Criteria.class, cls2}));
        arrayList2.add(new HookJavaMethod(1, LocationManager.class.getName(), "getLastKnownLocation", new Class[]{String.class}));
        arrayList2.add(new HookJavaMethod(2, LocationManager.class.getName(), "requestLocationUpdates", new Class[]{String.class, cls3, cls4, LocationListener.class}));
        arrayList2.add(new HookJavaMethod(3, LocationManager.class.getName(), "requestLocationUpdates", new Class[]{String.class, cls3, cls4, LocationListener.class, Looper.class}));
        arrayList2.add(new HookJavaMethod(4, LocationManager.class.getName(), "requestLocationUpdates", new Class[]{cls3, cls4, Criteria.class, LocationListener.class, Looper.class}));
        arrayList2.add(new HookJavaMethod(5, LocationManager.class.getName(), "requestLocationUpdates", new Class[]{String.class, cls3, cls4, PendingIntent.class}));
        arrayList2.add(new HookJavaMethod(6, LocationManager.class.getName(), "requestLocationUpdates", new Class[]{cls3, cls4, Criteria.class, PendingIntent.class}));
        arrayList2.add(new HookJavaMethod(7, Location.class.getName(), "getLatitude", new Class[0]));
        arrayList2.add(new HookJavaMethod(8, Location.class.getName(), "getLongitude", new Class[0]));
        arrayList2.add(new HookJavaMethod(9, GsmCellLocation.class.getName(), "getLac", new Class[0]));
        arrayList2.add(new HookJavaMethod(10, GsmCellLocation.class.getName(), "getCid", new Class[0]));
        arrayList2.add(new HookJavaMethod(11, GsmCellLocation.class.getName(), "getPsc", new Class[0]));
        arrayList2.add(new HookJavaMethod(12, CdmaCellLocation.class.getName(), "getBaseStationId", new Class[0]));
        arrayList2.add(new HookJavaMethod(13, CdmaCellLocation.class.getName(), "getBaseStationLatitude", new Class[0]));
        arrayList2.add(new HookJavaMethod(14, CdmaCellLocation.class.getName(), "getBaseStationLongitude", new Class[0]));
        arrayList2.add(new HookJavaMethod(15, CdmaCellLocation.class.getName(), "getSystemId", new Class[0]));
        arrayList2.add(new HookJavaMethod(16, CdmaCellLocation.class.getName(), "getNetworkId", new Class[0]));
        g_locationhookArrayList = arrayList2;
        ArrayList<HookJavaMethod> arrayList3 = new ArrayList<>();
        arrayList3.add(new HookJavaMethod(0, Camera.class.getName(), "getCameraInfo", new Class[]{cls, Camera.CameraInfo.class}));
        arrayList3.add(new HookJavaMethod(1, Camera.class.getName(), "getCameraInfo", new Class[]{cls, Camera.CameraInfo.class}));
        arrayList3.add(new HookJavaMethod(2, Camera.class.getName(), "getCameraInfo", new Class[]{cls, Camera.CameraInfo.class}));
        arrayList3.add(new HookJavaMethod(3, Camera.class.getName(), "openLegacy", new Class[]{cls, cls}));
        arrayList3.add(new HookJavaMethod(4, Camera.class.getName(), "cameraInitVersion", new Class[]{cls, cls}));
        arrayList3.add(new HookJavaMethod(5, Camera.class.getName(), "cameraInitNormal", new Class[]{cls}));
        arrayList3.add(new HookJavaMethod(6, Camera.class.getName(), "stopPreview", new Class[0]));
        arrayList3.add(new HookJavaMethod(7, Camera.class.getName(), "setPreviewCallback", new Class[]{Camera.PreviewCallback.class}));
        arrayList3.add(new HookJavaMethod(8, Camera.class.getName(), "setOneShotPreviewCallback", new Class[]{Camera.PreviewCallback.class}));
        arrayList3.add(new HookJavaMethod(9, Camera.class.getName(), "setPreviewCallbackWithBuffer", new Class[]{Camera.PreviewCallback.class}));
        arrayList3.add(new HookJavaMethod(10, Camera.class.getName(), "addCallbackBuffer", new Class[]{byte[].class}));
        arrayList3.add(new HookJavaMethod(11, Camera.class.getName(), "addRawImageCallbackBuffer", new Class[]{byte[].class}));
        arrayList3.add(new HookJavaMethod(12, Camera.class.getName(), "addCallbackBuffer", new Class[]{byte[].class, cls}));
        arrayList3.add(new HookJavaMethod(13, Camera.class.getName(), "createPreviewAllocation", new Class[]{RenderScript.class, cls}));
        arrayList3.add(new HookJavaMethod(14, Camera.class.getName(), "setPreviewCallbackAllocation", new Class[]{Allocation.class}));
        arrayList3.add(new HookJavaMethod(15, Camera.class.getName(), "postEventFromNative", new Class[]{Object.class, cls, cls, cls, Object.class}));
        arrayList3.add(new HookJavaMethod(16, Camera.class.getName(), "takePicture", new Class[]{Camera.ShutterCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class}));
        arrayList3.add(new HookJavaMethod(17, Camera.class.getName(), "takePicture", new Class[]{Camera.ShutterCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class}));
        arrayList3.add(new HookJavaMethod(18, Camera.class.getName(), "setFaceDetectionListener", new Class[]{Camera.FaceDetectionListener.class}));
        arrayList3.add(new HookJavaMethod(19, Camera.class.getName(), "startFaceDetection", new Class[0]));
        arrayList3.add(new HookJavaMethod(20, Camera.class.getName(), "stopFaceDetection", new Class[0]));
        arrayList3.add(new HookJavaMethod(21, Camera.class.getName(), "setParameters", new Class[]{Camera.Parameters.class}));
        arrayList3.add(new HookJavaMethod(22, Camera.class.getName(), "getParameters", new Class[0]));
        arrayList3.add(new HookJavaMethod(23, Camera.class.getName(), "getEmptyParameters", new Class[0]));
        arrayList3.add(new HookJavaMethod(24, Camera.class.getName(), "getParametersCopy", new Class[]{Camera.Parameters.class}));
        arrayList3.add(new HookJavaMethod(25, Camera.Parameters.class.getName(), "getSupportedPreviewSizes", new Class[0]));
        arrayList3.add(new HookJavaMethod(26, Camera.Parameters.class.getName(), "getPreviewSize", new Class[0]));
        arrayList3.add(new HookJavaMethod(27, Camera.Parameters.class.getName(), IMantoServerRequester.GET, new Class[]{String.class}));
        arrayList3.add(new HookJavaMethod(28, Camera.Parameters.class.getName(), "getInt", new Class[]{String.class}));
        arrayList3.add(new HookJavaMethod(29, Camera.Parameters.class.getName(), "setPreviewSize", new Class[]{cls, cls}));
        arrayList3.add(new HookJavaMethod(30, Camera.Parameters.class.getName(), "set", new Class[]{String.class, String.class}));
        arrayList3.add(new HookJavaMethod(31, Camera.Parameters.class.getName(), "set", new Class[]{String.class, cls}));
        g_camerahookArrayList = arrayList3;
    }

    private AntiHookManager(Context context) {
        javaMethoUtils = HookJavaMethodUtils.newInstance(context);
    }

    private static boolean checkCameraMethodHooked(ArrayList<HookJavaMethod> arrayList, int i2) {
        HookJavaMethod hookJavaMethod;
        try {
            hookJavaMethod = arrayList.get(i2);
            hookJavaMethod.getHooked();
        } catch (IndexOutOfBoundsException unused) {
        }
        return hookJavaMethod.getHooked() != 0;
    }

    public static int checkJavaMethodHooked() {
        return checkMethodHooked(getHookArrayList());
    }

    public static int checkLocationJavaMethodHooked() {
        return checkMethodHooked(getLocationHookArrayList());
    }

    private static int checkMethodHooked(ArrayList<HookJavaMethod> arrayList) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).getHooked() != 0) {
                return 1;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c A[LOOP:0: B:14:0x002c->B:16:0x0032, LOOP_START, PHI: r0
      0x002c: PHI (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:13:0x002a, B:16:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<com.jdjr.acr.HookJavaMethod> getCameraMethodHookedList(java.util.ArrayList<com.jdjr.acr.JavaMethod> r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L26
            int r1 = r6.size()     // Catch: java.lang.Exception -> L29
            if (r1 <= 0) goto L26
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L29
            r1.<init>()     // Catch: java.lang.Exception -> L29
            r2 = 0
        Lf:
            int r3 = r6.size()     // Catch: java.lang.Exception -> L29
            if (r2 >= r3) goto L2a
            com.jdjr.acr.HookJavaMethod r3 = new com.jdjr.acr.HookJavaMethod     // Catch: java.lang.Exception -> L29
            java.lang.Object r4 = r6.get(r2)     // Catch: java.lang.Exception -> L29
            com.jdjr.acr.JavaMethod r4 = (com.jdjr.acr.JavaMethod) r4     // Catch: java.lang.Exception -> L29
            r3.<init>(r4)     // Catch: java.lang.Exception -> L29
            r1.add(r3)     // Catch: java.lang.Exception -> L29
            int r2 = r2 + 1
            goto Lf
        L26:
            java.util.ArrayList<com.jdjr.acr.HookJavaMethod> r1 = com.jdjr.acr.AntiHookManager.g_camerahookArrayList     // Catch: java.lang.Exception -> L29
            goto L2a
        L29:
            r1 = 0
        L2a:
            if (r1 == 0) goto L4a
        L2c:
            int r6 = r1.size()
            if (r0 >= r6) goto L4a
            java.lang.Object r6 = r1.get(r0)
            com.jdjr.acr.HookJavaMethod r6 = (com.jdjr.acr.HookJavaMethod) r6
            com.jdjr.acr.HookJavaMethodUtils r2 = com.jdjr.acr.AntiHookManager.javaMethoUtils
            java.lang.String r3 = r6.className
            java.lang.String r4 = r6.methodName
            java.lang.Class<?>[] r5 = r6.parameterTypes
            int r2 = r2.IsHooked(r3, r4, r5)
            r6.setHooked(r2)
            int r0 = r0 + 1
            goto L2c
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.acr.AntiHookManager.getCameraMethodHookedList(java.util.ArrayList):java.util.ArrayList");
    }

    private static ArrayList<HookJavaMethod> getHookArrayList() {
        for (int i2 = 0; i2 < g_hookArrayList.size(); i2++) {
            HookJavaMethod hookJavaMethod = g_hookArrayList.get(i2);
            hookJavaMethod.setHooked(javaMethoUtils.IsHooked(hookJavaMethod.className, hookJavaMethod.methodName, hookJavaMethod.parameterTypes));
        }
        return g_hookArrayList;
    }

    private static ArrayList<HookJavaMethod> getLocationHookArrayList() {
        for (int i2 = 0; i2 < g_locationhookArrayList.size(); i2++) {
            HookJavaMethod hookJavaMethod = g_locationhookArrayList.get(i2);
            hookJavaMethod.setHooked(javaMethoUtils.IsHooked(hookJavaMethod.className, hookJavaMethod.methodName, hookJavaMethod.parameterTypes));
        }
        return g_locationhookArrayList;
    }

    public static AntiHookManager newInstance(Context context) {
        JDJRLog.i(TAG, "AntiHookManager newInstance");
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new AntiHookManager(context);
                }
            }
        }
        return instance;
    }
}
