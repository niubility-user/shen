package com.jd.android.sdk.coreinfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.deviceUtil.AndroidUtil;
import com.jd.android.sdk.coreinfo.util.CommonUtil;
import com.jd.android.sdk.coreinfo.util.FileReaderUtil;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.android.sdk.coreinfo.util.Supplier;
import com.jd.android.sdk.coreinfo.util.d;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b {
    private static long A = 0;
    private static long B = 0;
    private static boolean C = false;
    private static boolean D = false;
    private static boolean E = false;
    private static boolean F = false;
    private static boolean G = false;
    private static boolean H = false;
    private static boolean I = false;
    private static boolean J = false;
    private static CellLocation K = null;
    private static final String[] L = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static String a = "";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f1674c = "";
    private static String d = "";

    /* renamed from: e  reason: collision with root package name */
    private static String f1675e = "";

    /* renamed from: f  reason: collision with root package name */
    private static String f1676f = "";

    /* renamed from: g  reason: collision with root package name */
    private static String f1677g = "";

    /* renamed from: h  reason: collision with root package name */
    private static String f1678h = "";

    /* renamed from: i  reason: collision with root package name */
    private static String f1679i = "";

    /* renamed from: j  reason: collision with root package name */
    private static String f1680j = "";

    /* renamed from: k  reason: collision with root package name */
    private static String f1681k = "";

    /* renamed from: l  reason: collision with root package name */
    private static String f1682l = "";

    /* renamed from: m  reason: collision with root package name */
    private static String f1683m = "";

    /* renamed from: n  reason: collision with root package name */
    private static String[] f1684n = null;
    private static String o = "";
    private static String p = "";
    private static String q = "";
    private static String r = "";
    private static String s = "";
    private static String t = "";
    private static String u = "";
    private static String v = "";
    private static String w = "";
    private static String x = "";
    private static String y = "";
    private static long z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean A() {
        if (!J) {
            I = BluetoothAdapter.getDefaultAdapter() != null;
            J = true;
        }
        return I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean B() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean C() {
        File file = new File("/proc/tty/drivers");
        if (file.exists() && file.canRead()) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return new String(bArr).indexOf("goldfish") != -1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean D() {
        try {
            String[] strArr = {"/dev/socket/qemud", "/dev/qemu_pipe"};
            boolean z2 = false;
            for (int i2 = 0; i2 < 2; i2++) {
                if (new File(strArr[i2]).exists()) {
                    z2 = true;
                }
            }
            return z2;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int E() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String E(Context context) {
        WifiInfo V = V(context);
        if (V != null) {
            String ssid = V.getSSID();
            if (!TextUtils.isEmpty(ssid) && !"<unknown ssid>".equals(ssid)) {
                return ssid;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CellLocation F() {
        return K;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<NetworkInterface> G() {
        return Collections.list(NetworkInterface.getNetworkInterfaces());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> H(Context context) {
        ArrayList arrayList = new ArrayList();
        List<ScanResult> W = W(context);
        if (W != null && !W.isEmpty()) {
            Iterator<ScanResult> it = W.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().SSID);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> I(Context context) {
        HashMap hashMap = new HashMap();
        List<ScanResult> W = W(context);
        if (W != null && !W.isEmpty()) {
            for (ScanResult scanResult : W) {
                hashMap.put(scanResult.BSSID, scanResult.SSID);
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int J(Context context) {
        WifiInfo V = V(context);
        if (V != null) {
            return V.getLinkSpeed();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String K(Context context) {
        try {
            String networkCountryIso = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getNetworkCountryIso();
            return TextUtils.isEmpty(networkCountryIso) ? "" : networkCountryIso;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String L(Context context) {
        WifiInfo V;
        if (context == null) {
            Logger.e("Baseinfo.DeviceInfo", "getIpAddressFromWifiInfo context is null");
            return "";
        }
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isConnected() || (V = V(context)) == null) {
            return "";
        }
        int ipAddress = V.getIpAddress();
        return (ipAddress & 255) + OrderISVUtil.MONEY_DECIMAL + ((ipAddress >> 8) & 255) + OrderISVUtil.MONEY_DECIMAL + ((ipAddress >> 16) & 255) + OrderISVUtil.MONEY_DECIMAL + ((ipAddress >> 24) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Sensor> M(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String N(Context context) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            String address = defaultAdapter != null ? defaultAdapter.getAddress() : "";
            if (TextUtils.isEmpty(address) || TextUtils.equals("02:00:00:00:00:00", address)) {
                address = Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
            }
            return TextUtils.isEmpty(address) ? "" : address;
        } catch (Exception unused) {
            return "";
        }
    }

    private static DisplayMetrics O() {
        try {
            return Resources.getSystem().getDisplayMetrics();
        } catch (Exception e2) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getSystemDisplayMetricsObject()", e2);
            return null;
        }
    }

    private static ArrayList<NetworkInterface> P() {
        Supplier<ArrayList<NetworkInterface>> supplier = CoreInfo.Device.networkInterfacesSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        return Collections.list(NetworkInterface.getNetworkInterfaces());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int Q(Context context) {
        if (context != null) {
            try {
                return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int R(Context context) {
        if (context != null) {
            try {
                return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("level", -1);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int S(Context context) {
        if (context != null) {
            try {
                return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("scale", -1);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    private static boolean T(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE);
        try {
            Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
            method.setAccessible(true);
            Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    if (((Boolean) obj.getClass().getMethod("isRemovable", new Class[0]).invoke(obj, new Object[0])).booleanValue() && ((String) obj.getClass().getMethod("getState", new Class[0]).invoke(obj, new Object[0])).equals("mounted")) {
                        return true;
                    }
                }
            }
        } catch (Exception e2) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call storageIsRemovable()", e2);
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    private static String U(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return "";
        }
        try {
            if (((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)) != null) {
                int t2 = t(context);
                if (t2 != 20) {
                    switch (t2) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                            return "2g";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            return "3g";
                        case 13:
                            return "4g";
                        default:
                            return "mobile";
                    }
                }
                return "5g";
            }
            return "";
        } catch (Throwable th) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getMobileDataType()", th);
            return "mobile";
        }
    }

    private static WifiInfo V(Context context) {
        Supplier<WifiInfo> supplier = CoreInfo.Device.wifiInfoSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        return B(context);
    }

    private static List<ScanResult> W(Context context) {
        Supplier<List<ScanResult>> supplier = CoreInfo.Device.wifiListSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        return C(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        return sensitiveApi != null ? sensitiveApi.getDeviceName() : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b() {
        if (TextUtils.isEmpty(f1678h)) {
            f1678h = CommonUtil.ensureNotEmpty(Build.PRODUCT);
        }
        return f1678h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c() {
        if (TextUtils.isEmpty(f1679i)) {
            f1679i = CommonUtil.ensureNotEmpty(Build.MANUFACTURER);
        }
        return f1679i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d() {
        if (TextUtils.isEmpty(f1680j)) {
            f1680j = CommonUtil.ensureNotEmpty(Build.BRAND);
        }
        return f1680j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e() {
        if (TextUtils.isEmpty(f1681k)) {
            if ("xiaomi".equalsIgnoreCase(d())) {
                f1681k = d.a("ro.product.marketname", "");
            }
            if (TextUtils.isEmpty(f1681k)) {
                f1681k = CommonUtil.ensureNotEmpty(Build.MODEL);
            }
        }
        return f1681k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String f() {
        if (TextUtils.isEmpty(f1682l)) {
            f1682l = CommonUtil.ensureNotEmpty(Build.BOARD);
        }
        return f1682l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g() {
        if (TextUtils.isEmpty(f1683m)) {
            f1683m = CommonUtil.ensureNotEmpty(d.a("ro.board.platform", ""));
        }
        return f1683m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] h() {
        String[] strArr = f1684n;
        if (strArr == null || strArr.length == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                f1684n = Build.SUPPORTED_ABIS;
            } else {
                String str = Build.CPU_ABI2;
                if (TextUtils.isEmpty(str)) {
                    f1684n = new String[]{Build.CPU_ABI};
                } else {
                    f1684n = new String[]{Build.CPU_ABI, str};
                }
            }
        }
        return f1684n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String i() {
        if (TextUtils.isEmpty(o)) {
            o = CommonUtil.ensureNotEmpty(Build.getRadioVersion());
        }
        return o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String j() {
        if (TextUtils.isEmpty(p)) {
            p = CommonUtil.ensureNotEmpty(Build.HARDWARE);
        }
        return p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static String k() {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        return sensitiveApi != null ? sensitiveApi.getHardwareSerialNo() : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String l() {
        if (TextUtils.isEmpty(r)) {
            r = CommonUtil.ensureNotEmpty(Build.BOOTLOADER);
        }
        return r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String m() {
        if (TextUtils.isEmpty(s)) {
            s = CommonUtil.ensureNotEmpty(Build.HOST);
        }
        return s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String n() {
        if (TextUtils.isEmpty(t)) {
            t = CommonUtil.ensureNotEmpty(com.jd.android.sdk.coreinfo.deviceUtil.b.a());
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String o() {
        LineNumberReader lineNumberReader;
        Throwable th;
        InputStreamReader inputStreamReader;
        String str;
        if (TextUtils.isEmpty(u)) {
            try {
                inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo | grep Serial").getInputStream());
                try {
                    lineNumberReader = new LineNumberReader(inputStreamReader);
                    for (int i2 = 0; i2 < 100; i2 = i2 + 1 + 1) {
                        try {
                            String readLine = lineNumberReader.readLine();
                            if (readLine != null && readLine.indexOf("Serial") >= 0) {
                                str = readLine.substring(readLine.indexOf(":") + 1).trim();
                                break;
                            }
                        } catch (Throwable th2) {
                            try {
                                th2.printStackTrace();
                            } catch (Throwable th3) {
                                th = th3;
                                try {
                                    th.printStackTrace();
                                    str = "";
                                    u = CommonUtil.ensureNotEmpty(str);
                                    return u;
                                } catch (Throwable th4) {
                                    if (inputStreamReader != null) {
                                        try {
                                            inputStreamReader.close();
                                        } catch (Exception unused) {
                                        }
                                    }
                                    if (lineNumberReader != null) {
                                        try {
                                            lineNumberReader.close();
                                        } catch (Exception unused2) {
                                        }
                                    }
                                    throw th4;
                                }
                            }
                        }
                    }
                } catch (Throwable th5) {
                    lineNumberReader = null;
                    th = th5;
                }
            } catch (Throwable th6) {
                lineNumberReader = null;
                th = th6;
                inputStreamReader = null;
            }
            str = "";
            u = CommonUtil.ensureNotEmpty(str);
        }
        return u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String p() {
        if (TextUtils.isEmpty(v)) {
            try {
                v = String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new com.jd.android.sdk.coreinfo.a.a()).length);
            } catch (Exception e2) {
                Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getCPUNum()", e2);
                return "1";
            }
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String q() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        if (TextUtils.isEmpty(w)) {
            try {
                fileReader = new FileReader("/proc/cpuinfo");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    try {
                        String[] split = bufferedReader.readLine().split(":\\s+", 2);
                        if (split.length >= 2) {
                            w = split[1];
                        }
                        try {
                            fileReader.close();
                        } catch (IOException unused) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            th.printStackTrace();
                        } finally {
                            if (fileReader != null) {
                                try {
                                    fileReader.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused3) {
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                fileReader = null;
            }
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
        }
        return w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004d, code lost:
        if (r2 == null) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String r() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        if (TextUtils.isEmpty(y)) {
            FileReader fileReader2 = null;
            try {
                fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Exception unused) {
                    bufferedReader = null;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                }
                try {
                    y = CommonUtil.ensureNotEmpty(bufferedReader.readLine().trim());
                    try {
                        fileReader.close();
                    } catch (IOException unused2) {
                    }
                } catch (Exception unused3) {
                    fileReader2 = fileReader;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (IOException unused4) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException unused5) {
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused6) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused7) {
                bufferedReader = null;
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                fileReader = null;
            }
            try {
                bufferedReader.close();
            } catch (IOException unused8) {
            }
        }
        return y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String s() {
        if (TextUtils.isEmpty(x)) {
            String read = FileReaderUtil.read("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq", false);
            if (read.length() == 0) {
                return "";
            }
            x = CommonUtil.ensureNotEmpty(read);
        }
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String t() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (Exception unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
            }
            try {
                String trim = bufferedReader.readLine().trim();
                try {
                    fileReader.close();
                } catch (IOException unused2) {
                }
                try {
                    bufferedReader.close();
                } catch (IOException unused3) {
                    return trim;
                }
            } catch (Exception unused4) {
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (IOException unused5) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused6) {
                    }
                }
                return "";
            } catch (Throwable th3) {
                th = th3;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused7) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused8) {
                    }
                }
                throw th;
            }
        } catch (Exception unused9) {
            bufferedReader = null;
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            fileReader = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0026, code lost:
        if (r2.contains("test-keys") == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean u() {
        boolean z2;
        try {
            String[] strArr = L;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z2 = false;
                    break;
                } else if (new File(strArr[i2]).exists()) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            String str = Build.TAGS;
            if (str != null) {
            }
            return z2;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String v() {
        return CommonUtil.ensureNotEmpty(FileReaderUtil.read("/sys/block/mmcblk0/device/cid", false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long w() {
        if (A <= 0) {
            try {
                A = a(Environment.getDataDirectory().getPath());
            } catch (Exception e2) {
                Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getRomSize()", e2);
                return 0L;
            }
        }
        return A;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long x() {
        if (B <= 0) {
            try {
                B = a(Environment.getExternalStorageDirectory().getPath());
            } catch (Exception e2) {
                Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getExternalStorageSize()", e2);
                return 0L;
            }
        }
        return B;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y() {
        DisplayMetrics O = O();
        if (O == null) {
            return 240;
        }
        return O.widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z() {
        DisplayMetrics O = O();
        if (O == null) {
            return 320;
        }
        return O.heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int F(Context context) {
        WifiInfo wifiInfo;
        if (!TextUtils.equals(s(context), "wifi") || (wifiInfo = CoreInfo.Device.getWifiInfo(context)) == null) {
            return 0;
        }
        return wifiInfo.getRssi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> G(Context context) {
        ArrayList arrayList = new ArrayList();
        List<ScanResult> W = W(context);
        if (W != null && !W.isEmpty()) {
            Iterator<ScanResult> it = W.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().BSSID);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static String a(Context context) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        return sensitiveApi != null ? sensitiveApi.getSimSerialNo(context) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return 320;
        }
        return m2.heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WifiInfo B(Context context) {
        if (context == null) {
            Logger.e("Baseinfo.DeviceInfo", "getWifiConnectionInfo context is null");
            return null;
        } else if (!com.jd.android.sdk.coreinfo.util.b.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            Logger.e("Baseinfo.DeviceInfo", "ACCESS_FINE_LOCATION permission is not granted, give up get wifi info.");
            return null;
        } else {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null) {
                    return wifiManager.getConnectionInfo();
                }
            } catch (Exception e2) {
                Logger.e("Baseinfo.DeviceInfo", "", e2);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> J() {
        ArrayList arrayList = new ArrayList();
        try {
            ArrayList<NetworkInterface> P = P();
            if (P != null) {
                Iterator<NetworkInterface> it = P.iterator();
                while (it.hasNext()) {
                    Enumeration<InetAddress> inetAddresses = it.next().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            String hostAddress = nextElement.getHostAddress();
                            if (!TextUtils.isEmpty(hostAddress) && (nextElement instanceof Inet4Address)) {
                                arrayList.add(hostAddress);
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long M() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    public static long O(Context context) {
        String str = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))), 1024);
            String str2 = null;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (readLine.startsWith("MemTotal")) {
                            str = readLine;
                        } else {
                            str = str;
                            if (readLine.startsWith("MemFree")) {
                                str2 = readLine;
                                str = str;
                            }
                        }
                        if (str != 0 && str2 != null) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception unused) {
                    str = bufferedReader;
                    if (str != null) {
                        try {
                            str.close();
                            return 0L;
                        } catch (IOException unused2) {
                            return 0L;
                        }
                    }
                    return 0L;
                } catch (Throwable th) {
                    th = th;
                    str = bufferedReader;
                    if (str != null) {
                        try {
                            str.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            long j2 = new long[]{b(str), b(str2), memoryInfo.availMem}[0];
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
            return j2;
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static long a(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        }
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(Context context) {
        DhcpInfo dhcpInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
                return 0;
            }
            return dhcpInfo.gateway;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String y(Context context) {
        if (TextUtils.isEmpty(f1676f)) {
            f1676f = CommonUtil.ensureNotEmpty(AndroidUtil.a(context));
        }
        return f1676f;
    }

    public static String z(Context context) {
        try {
            return CommonUtil.ensureNotEmpty(((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getNetworkOperator());
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String D(Context context) {
        WifiInfo V = V(context);
        if (V != null) {
            String bssid = V.getBSSID();
            if (!TextUtils.isEmpty(bssid) && !"02:00:00:00:00:00".equals(bssid)) {
                return bssid;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> K() {
        ArrayList arrayList = new ArrayList();
        try {
            ArrayList<NetworkInterface> P = P();
            if (P != null) {
                Iterator<NetworkInterface> it = P.iterator();
                while (it.hasNext()) {
                    Enumeration<InetAddress> inetAddresses = it.next().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            String hostAddress = nextElement.getHostAddress();
                            if (!TextUtils.isEmpty(hostAddress) && (nextElement instanceof Inet6Address)) {
                                arrayList.add(hostAddress);
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int P(Context context) {
        if (context != null) {
            try {
                return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return "";
        }
        try {
            return CommonUtil.ensureNotEmpty(((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperator());
        } catch (Throwable th) {
            Logger.w("Baseinfo.DeviceInfo", "DeviceInfo.getSimOperator() exception: " + th.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
                return telephonyManager != null ? CommonUtil.ensureNotEmpty(telephonyManager.getSimOperatorName()) : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    public static String d(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
                return telephonyManager != null ? CommonUtil.ensureNotEmpty(telephonyManager.getSimCountryIso()) : "";
            } catch (Throwable unused) {
                return "";
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long f(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return 0L;
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.availMem >> 10;
        } catch (Exception e2) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getMemAvailSize()", e2);
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return 160;
        }
        return m2.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return 240;
        }
        return m2.widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String l(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return "";
        }
        return m2.widthPixels + ProxyConfig.MATCH_ALL_SCHEMES + m2.heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DisplayMetrics m(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return null;
        }
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Exception e2) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getDisplayMetricsObject()", e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float g(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return 1.0f;
        }
        return m2.density;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean n(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                List<StorageVolume> storageVolumes = ((StorageManager) context.getSystemService(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE)).getStorageVolumes();
                if (storageVolumes != null) {
                    for (StorageVolume storageVolume : storageVolumes) {
                        if (storageVolume.isRemovable() && storageVolume.getState().equals("mounted")) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return T(context);
        } catch (Exception e2) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call storageIsRemovable()", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(23)
    public static boolean p(Context context) {
        FingerprintManager fingerprintManager;
        if (!F) {
            if (context == null) {
                Logger.w("Baseinfo.DeviceInfo", "context is null");
                return false;
            }
            if (Build.VERSION.SDK_INT >= 23 && (fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint")) != null) {
                try {
                    E = fingerprintManager.isHardwareDetected();
                    F = true;
                } catch (Throwable th) {
                    Logger.e("Baseinfo.DeviceInfo", "An error occors when call isFingerprintAvailable()", th);
                }
            }
            return false;
        }
        return E;
    }

    public static String A(Context context) {
        try {
            return CommonUtil.ensureNotEmpty(((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getNetworkOperatorName());
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static String H() {
        String stringBuffer;
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            ArrayList<NetworkInterface> P = P();
            if (P != null) {
                Iterator<NetworkInterface> it = P.iterator();
                while (it.hasNext()) {
                    Enumeration<InetAddress> inetAddresses = it.next().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            String hostAddress = nextElement.getHostAddress();
                            if (!TextUtils.isEmpty(hostAddress)) {
                                if (nextElement instanceof Inet4Address) {
                                    hostAddress = hostAddress + "%ipv4";
                                }
                                if (stringBuffer2.length() == 0) {
                                    stringBuffer2.append(hostAddress);
                                } else {
                                    stringBuffer2.append(", ");
                                    stringBuffer2.append(hostAddress);
                                }
                            }
                        }
                    }
                }
            }
            stringBuffer = stringBuffer2.toString();
        } catch (Throwable unused) {
        }
        return !TextUtils.isEmpty(stringBuffer) ? stringBuffer : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[][] I() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            ArrayList<NetworkInterface> P = P();
            if (P != null) {
                Iterator<NetworkInterface> it = P.iterator();
                while (it.hasNext()) {
                    Enumeration<InetAddress> inetAddresses = it.next().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            String hostAddress = nextElement.getHostAddress();
                            if (!TextUtils.isEmpty(hostAddress)) {
                                if (nextElement instanceof Inet4Address) {
                                    arrayList.add(hostAddress);
                                } else if (nextElement instanceof Inet6Address) {
                                    arrayList2.add(hostAddress);
                                }
                            }
                        }
                    }
                }
            }
            return new String[][]{(String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()])};
        } catch (Throwable unused) {
            return (String[][]) Array.newInstance(String.class, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] N() {
        String[] strArr = new String[3];
        strArr[0] = "";
        strArr[1] = "";
        strArr[2] = "";
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                for (int i2 = 0; i2 < 3; i2++) {
                    try {
                        strArr[i2] = bufferedReader2.readLine();
                    } catch (FileNotFoundException unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return strArr;
                    } catch (IOException unused2) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return strArr;
                    } catch (Throwable unused3) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return strArr;
                    }
                }
                bufferedReader2.close();
            } catch (FileNotFoundException unused4) {
            } catch (IOException unused5) {
            } catch (Throwable unused6) {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public static String s(Context context) {
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return "";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.getType() == 0) {
                    return CoreInfo.sensitiveApi != null ? U(context) : "mobile";
                }
                try {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                    if (networkInfo != null && networkInfo.getState() != null) {
                        NetworkInfo.State state = networkInfo.getState();
                        if (state == NetworkInfo.State.CONNECTED) {
                            return "wifi";
                        }
                        if (state == NetworkInfo.State.CONNECTING) {
                            return "wifi";
                        }
                    }
                } catch (Throwable th) {
                    Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getNetworkType()", th);
                }
                try {
                    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(9);
                    if (networkInfo2 != null && networkInfo2.getState() != null) {
                        NetworkInfo.State state2 = networkInfo2.getState();
                        if (state2 == NetworkInfo.State.CONNECTED) {
                            return BaseInfo.NETWORK_TYPE_ETHERNET;
                        }
                        if (state2 == NetworkInfo.State.CONNECTING) {
                            return BaseInfo.NETWORK_TYPE_ETHERNET;
                        }
                    }
                } catch (Throwable th2) {
                    Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getNetworkType()", th2);
                }
                return "";
            }
            return "none";
        } catch (Throwable th3) {
            Logger.e("Baseinfo.DeviceInfo", "An exception happends when call getNetworkType()", th3);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String u(Context context) {
        NetworkInfo activeNetworkInfo;
        String str = "";
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
                    return "";
                }
                str = activeNetworkInfo.getSubtypeName();
                return activeNetworkInfo.getType() == 1 ? activeNetworkInfo.getTypeName() : str;
            } catch (Throwable unused) {
                return str;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(Context context) {
        DhcpInfo dhcpInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
                return 0;
            }
            return dhcpInfo.netmask;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String x(Context context) {
        if (TextUtils.isEmpty(f1675e)) {
            if (context == null) {
                Logger.w("Baseinfo.DeviceInfo", "context is null");
                return "";
            }
            f1675e = CommonUtil.ensureNotEmpty(Settings.Secure.getString(context.getContentResolver(), "android_id"));
        }
        return f1675e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context, int i2) {
        List<Sensor> sensorList;
        if (context == null) {
            Logger.w("Baseinfo.DeviceInfo", "context is null");
            return false;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        return (sensorManager == null || (sensorList = sensorManager.getSensorList(i2)) == null || sensorList.size() <= 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long e(Context context) {
        long parseLong;
        if (z <= 0) {
            if (Build.VERSION.SDK_INT < 16) {
                try {
                    String read = FileReaderUtil.read("/proc/meminfo", true);
                    if (read.length() == 0) {
                        parseLong = 0;
                    } else {
                        int indexOf = read.indexOf("MemTotal:");
                        parseLong = Long.parseLong(read.substring(indexOf + 9, read.indexOf("kB", indexOf)).trim());
                    }
                    z = parseLong;
                } catch (Exception e2) {
                    Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getMemTotalSizeLowerAPI16()", e2);
                    return 0L;
                }
            } else if (context == null) {
                Logger.w("Baseinfo.DeviceInfo", "context is null");
                return 0L;
            } else {
                try {
                    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                    ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                    z = memoryInfo.totalMem >> 10;
                } catch (Exception e3) {
                    Logger.e("Baseinfo.DeviceInfo", "An exception happens when call getMemTotalSize()", e3);
                    return 0L;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String L() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            String name = defaultAdapter != null ? defaultAdapter.getName() : "";
            return TextUtils.isEmpty(name) ? "" : name;
        } catch (Exception unused) {
            return "";
        }
    }

    private static long b(String str) {
        int indexOf;
        String trim;
        int lastIndexOf;
        long j2;
        if (str == null || (indexOf = str.indexOf(58)) == -1 || (lastIndexOf = (trim = str.substring(indexOf + 1).trim()).lastIndexOf(32)) == -1) {
            return -1L;
        }
        String substring = trim.substring(lastIndexOf + 1);
        try {
            long parseLong = Long.parseLong(trim.substring(0, lastIndexOf).trim());
            if ("kb".equalsIgnoreCase(substring)) {
                j2 = 1024;
            } else if ("mb".equalsIgnoreCase(substring)) {
                j2 = 1048576;
            } else if (!"gb".equalsIgnoreCase(substring)) {
                return parseLong;
            } else {
                j2 = IjkMediaMeta.AV_CH_STEREO_RIGHT;
            }
            return parseLong * j2;
        } catch (Exception unused) {
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float h(Context context) {
        DisplayMetrics m2 = m(context);
        if (m2 == null) {
            return 1.0f;
        }
        return m2.scaledDensity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<ScanResult> C(Context context) {
        if (context == null) {
            Logger.e("Baseinfo.DeviceInfo", "getWifiScanResultList context is null");
            return null;
        } else if (!com.jd.android.sdk.coreinfo.util.b.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            Logger.e("Baseinfo.DeviceInfo", "ACCESS_FINE_LOCATION permission is not granted, give up scan wifi list.");
            return null;
        } else {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null) {
                    return wifiManager.getScanResults();
                }
            } catch (Exception e2) {
                Logger.e("Baseinfo.DeviceInfo", "", e2);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(Context context) {
        SensitiveApi sensitiveApi = CoreInfo.sensitiveApi;
        if (sensitiveApi != null) {
            return sensitiveApi.getNetworkType(context);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean o(Context context) {
        if (!D) {
            boolean z2 = false;
            if (context == null) {
                Logger.w("Baseinfo.DeviceInfo", "context is null");
                return false;
            }
            LocationManager locationManager = (LocationManager) context.getSystemService(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
            if (locationManager == null) {
                return false;
            }
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders != null && allProviders.contains("gps")) {
                z2 = true;
            }
            C = z2;
            D = true;
        }
        return C;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean r(Context context) {
        NfcAdapter defaultAdapter;
        return q(context) && (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) != null && defaultAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean q(Context context) {
        if (!H) {
            if (context == null) {
                Logger.w("Baseinfo.DeviceInfo", "context is null");
                return false;
            } else if (Build.VERSION.SDK_INT < 9) {
                return false;
            } else {
                G = context.getPackageManager().hasSystemFeature("android.hardware.nfc");
                H = true;
            }
        }
        return G;
    }
}
