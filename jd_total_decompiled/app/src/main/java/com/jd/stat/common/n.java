package com.jd.stat.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.jd.dynamic.DYConstants;
import com.jdpay.system.SystemInfo;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

@SuppressLint({"MissingPermission"})
/* loaded from: classes18.dex */
public class n {
    private static String a = "";
    private static String b = "device:%s,model:%s,product:%s,brand:%s,release:%s,display:%s,locale:%s";

    /* renamed from: c */
    private static String f6998c;

    /* loaded from: classes18.dex */
    public class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    private static int A() {
        try {
            return BluetoothAdapter.getDefaultAdapter() != null ? 1 : 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String a(Context context) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Formatter.formatFileSize(context, statFs.getAvailableBlocks() * statFs.getBlockSize());
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String b(Context context) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Formatter.formatFileSize(context, statFs.getBlockCount() * statFs.getBlockSize());
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String c(Context context) {
        long[] d = d(context);
        return d != null ? Formatter.formatFileSize(context, d[0]) : "unknow";
    }

    /* JADX WARN: Removed duplicated region for block: B:195:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long[] d(Context context) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))), 1024);
            String str = null;
            String str2 = null;
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.startsWith("MemTotal")) {
                                str = readLine;
                            } else if (readLine.startsWith("MemFree")) {
                                str2 = readLine;
                            }
                            if (str != null && str2 != null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                if (com.jd.stat.common.b.b.a) {
                                    e2.getLocalizedMessage();
                                }
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    if (com.jd.stat.common.b.b.a) {
                        e.getLocalizedMessage();
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            if (com.jd.stat.common.b.b.a) {
                                e4.getLocalizedMessage();
                            }
                        }
                    }
                    return null;
                }
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            long[] jArr = {a(str), a(str2), memoryInfo.availMem};
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                if (com.jd.stat.common.b.b.a) {
                    e5.getLocalizedMessage();
                }
            }
            return jArr;
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
    }

    public static String e(Context context) {
        int screenWidth = BaseInfo.getScreenWidth();
        return BaseInfo.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + screenWidth;
    }

    public static List<Sensor> f(Context context) {
        if (!com.jd.stat.security.c.k()) {
            return new ArrayList();
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager != null) {
            return sensorManager.getSensorList(-1);
        }
        return new ArrayList();
    }

    public static boolean g(Context context) {
        try {
            return ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean h(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        } catch (Exception unused) {
            return false;
        }
    }

    public static String i() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        IOException e2;
        FileNotFoundException e3;
        int i2;
        try {
            try {
                try {
                    fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
                } catch (FileNotFoundException e4) {
                    bufferedReader2 = null;
                    e3 = e4;
                    fileReader = null;
                } catch (IOException e5) {
                    bufferedReader2 = null;
                    e2 = e5;
                    fileReader = null;
                } catch (Throwable th) {
                    bufferedReader = null;
                    th = th;
                    fileReader = null;
                }
                try {
                    bufferedReader2 = new BufferedReader(fileReader);
                } catch (FileNotFoundException e6) {
                    bufferedReader2 = null;
                    e3 = e6;
                } catch (IOException e7) {
                    bufferedReader2 = null;
                    e2 = e7;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    i2 = Integer.parseInt(bufferedReader2.readLine().trim());
                    try {
                        fileReader.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                    try {
                        bufferedReader2.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    }
                } catch (FileNotFoundException e12) {
                    e3 = e12;
                    e3.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e13) {
                            e13.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    i2 = 0;
                    return com.jd.stat.common.b.g.a(i2) + "GHz";
                } catch (IOException e14) {
                    e2 = e14;
                    e2.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e15) {
                            e15.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    i2 = 0;
                    return com.jd.stat.common.b.g.a(i2) + "GHz";
                }
            } catch (IOException e16) {
                e16.printStackTrace();
                i2 = 0;
                return com.jd.stat.common.b.g.a(i2) + "GHz";
            }
            return com.jd.stat.common.b.g.a(i2) + "GHz";
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static String j() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        IOException e2;
        FileNotFoundException e3;
        try {
            try {
                try {
                    fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                } catch (FileNotFoundException e4) {
                    bufferedReader2 = null;
                    e3 = e4;
                    fileReader = null;
                } catch (IOException e5) {
                    bufferedReader2 = null;
                    e2 = e5;
                    fileReader = null;
                } catch (Throwable th) {
                    bufferedReader = null;
                    th = th;
                    fileReader = null;
                }
                try {
                    bufferedReader2 = new BufferedReader(fileReader);
                    try {
                        String readLine = bufferedReader2.readLine();
                        String str = com.jd.stat.common.b.g.a(readLine != null ? Integer.parseInt(readLine.trim()) : 0) + "GHz";
                        try {
                            fileReader.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            bufferedReader2.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                        return str;
                    } catch (FileNotFoundException e8) {
                        e3 = e8;
                        e3.printStackTrace();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                            return "";
                        }
                        return "";
                    } catch (IOException e10) {
                        e2 = e10;
                        e2.printStackTrace();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e11) {
                                e11.printStackTrace();
                            }
                        }
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                            return "";
                        }
                        return "";
                    }
                } catch (FileNotFoundException e12) {
                    bufferedReader2 = null;
                    e3 = e12;
                } catch (IOException e13) {
                    bufferedReader2 = null;
                    e2 = e13;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e14) {
                            e14.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e15) {
                            e15.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e16) {
                e16.printStackTrace();
                return "";
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static String k() {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader;
        BufferedReader bufferedReader2;
        IOException e2;
        FileNotFoundException e3;
        try {
            try {
                try {
                    fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
                } catch (FileNotFoundException e4) {
                    bufferedReader2 = null;
                    e3 = e4;
                    fileReader = null;
                } catch (IOException e5) {
                    bufferedReader2 = null;
                    e2 = e5;
                    fileReader = null;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    fileReader = null;
                }
                try {
                    bufferedReader2 = new BufferedReader(fileReader);
                } catch (FileNotFoundException e6) {
                    bufferedReader2 = null;
                    e3 = e6;
                } catch (IOException e7) {
                    bufferedReader2 = null;
                    e2 = e7;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    String str = com.jd.stat.common.b.g.a(Integer.parseInt(bufferedReader2.readLine().trim())) + "GHz";
                    try {
                        fileReader.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                    try {
                        bufferedReader2.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    }
                    return str;
                } catch (FileNotFoundException e12) {
                    e3 = e12;
                    e3.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e13) {
                            e13.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        return "";
                    }
                    return "";
                } catch (IOException e14) {
                    e2 = e14;
                    e2.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e15) {
                            e15.printStackTrace();
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        return "";
                    }
                    return "";
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e16) {
            e16.printStackTrace();
            return "";
        }
    }

    public static int l() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 1;
        }
    }

    public static String m() {
        try {
            String str = "";
            for (String str2 : BaseInfo.getNetAddressesForIPv4()) {
                str = TextUtils.isEmpty(str) ? str2 + "%ipv4" : str + DYConstants.DY_REGEX_COMMA + str2 + "%ipv4";
            }
            for (String str3 : BaseInfo.getNetAddressesForIPv6()) {
                str = TextUtils.isEmpty(str) ? str3 : str + DYConstants.DY_REGEX_COMMA + str3;
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String n() {
        return String.format(b, BaseInfo.getDeviceName(), BaseInfo.getDeviceModel(), BaseInfo.getDeviceProductName(), BaseInfo.getDeviceBrand(), Build.VERSION.RELEASE, BaseInfo.getOSName(), Locale.getDefault().toString());
    }

    public static String o() {
        return !com.jd.stat.security.c.k() ? "" : String.format(b, "", NativeInfo.getProp("ro.product.model"), "", "", NativeInfo.getProp("ro.build.version.release"), NativeInfo.getProp("ro.build.display.id"), NativeInfo.getProp("ro.product.locale"));
    }

    public static boolean p() {
        try {
            if (!new File("/system/bin/su").exists() && !new File("/system/xbin/su").exists() && !new File("/su/bin/su").exists()) {
                if (!new File("/su/xbin/su").exists()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String q() {
        String b2;
        if (com.jd.stat.security.c.k()) {
            if (!TextUtils.isEmpty(f6998c)) {
                return f6998c;
            }
            String deviceBrand = BaseInfo.getDeviceBrand();
            char c2 = '\uffff';
            switch (deviceBrand.hashCode()) {
                case -1675632421:
                    if (deviceBrand.equals(Constant.DEVICE_XIAOMI)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1106355917:
                    if (deviceBrand.equals("lenovo")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case -759499589:
                    if (deviceBrand.equals("xiaomi")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 50733:
                    if (deviceBrand.equals(SystemInfo.ROM_360)) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 2432928:
                    if (deviceBrand.equals("OPPO")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 3620012:
                    if (deviceBrand.equals("vivo")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 68924490:
                    if (deviceBrand.equals("HONOR")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 74224812:
                    if (deviceBrand.equals("Meizu")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 99462250:
                    if (deviceBrand.equals("honor")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 343319808:
                    if (deviceBrand.equals("OnePlus")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 1343164416:
                    if (deviceBrand.equals("SMARTISAN")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 1864941562:
                    if (deviceBrand.equals("samsung")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 2141820391:
                    if (deviceBrand.equals("HUAWEI")) {
                        c2 = 0;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                    b2 = b("ro.build.version.emui");
                    break;
                case 3:
                    b2 = b("ro.vivo.os.version");
                    break;
                case 4:
                    b2 = b("ro.build.version.opporom");
                    break;
                case 5:
                case 6:
                    b2 = "MIUI" + b("ro.miui.ui.version.name");
                    break;
                case 7:
                    b2 = b("ro.smartisan.version");
                    break;
                case '\b':
                    b2 = b("ro.build.uiversion");
                    break;
                case '\t':
                    b2 = b("ro.rom.version");
                    break;
                default:
                    b2 = b("ro.build.display.id");
                    break;
            }
            if (com.jd.stat.common.b.b.a) {
                com.jd.stat.common.b.b.b("JDMob.Security.Phone", "romVersion==" + b2);
            }
            f6998c = b2;
            return b2;
        }
        return "";
    }

    public static String r() {
        try {
            String b2 = b("gsm.version.baseband");
            return TextUtils.isEmpty(b2) ? com.jingdong.jdsdk.a.a.a : b2;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String s() {
        try {
            String property = System.getProperty("os.version");
            return TextUtils.isEmpty(property) ? com.jingdong.jdsdk.a.a.a : property;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String t() {
        try {
            String b2 = b("ro.modversion");
            return TextUtils.isEmpty(b2) ? com.jingdong.jdsdk.a.a.a : b2;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String u() {
        String property = System.getProperty("http.proxyHost", "");
        String property2 = System.getProperty("http.proxyPort", "");
        if (TextUtils.isEmpty(property) && TextUtils.isEmpty(property2)) {
            return "";
        }
        return property + ":" + property2;
    }

    @Deprecated
    public static boolean v() {
        try {
            if (com.jd.stat.security.d.a().I()) {
                return EncryptUtil.checkSimulator();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String w() {
        StringBuilder sb = new StringBuilder();
        List<Sensor> f2 = f(com.jd.stat.security.c.a);
        if (f2 != null) {
            for (int i2 = 0; i2 < f2.size() && i2 < 10; i2++) {
                Sensor sensor = f2.get(i2);
                sb.append(sensor.getName() + DYConstants.DY_REGEX_COMMA + sensor.getResolution() + DYConstants.DY_REGEX_COMMA + sensor.getVendor());
                sb.append(DYConstants.DY_REGEX_AT);
            }
        }
        return sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : "";
    }

    public static String x() {
        return Build.VERSION.SDK_INT >= 14 ? Build.getRadioVersion() : Build.RADIO;
    }

    public static String y() {
        String[] strArr = {"android.app.appsearch.AppSearchManager", "android.app.blob.BlobStoreManager", "android.app.role.RoleManager", "android.net.wifi.rtt.WifiRttManager", "android.companion.WifiDeviceFilter", "android.os.health.SystemHealthManager", "android.media.midi.MidiManager", "android.media.projection.MediaProjectionManager", "android.net.wifi.p2p.WifiP2pManager"};
        String str = "";
        for (int i2 = 0; i2 < 9; i2++) {
            str = String.format("%s%s", c(strArr[i2]), str);
        }
        return str;
    }

    private static int z() {
        try {
            String optString = MonitorService.f().a().optString("temperature");
            if (TextUtils.isEmpty(optString)) {
                return 0;
            }
            return !TextUtils.equals(optString, "0") ? 1 : 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean h() {
        File file = new File("/proc/tty/drivers");
        if (file.exists() && file.canRead()) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return new String(bArr).indexOf("goldfish") != -1;
        }
        return false;
    }

    public static String c() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i2 = 0; i2 < split.length; i2++) {
            }
            return split[1];
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return "unknow";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "unknow";
        }
    }

    public static boolean g() {
        try {
            String[] strArr = {"/dev/socket/qemud", "/dev/qemu_pipe"};
            boolean z = false;
            for (int i2 = 0; i2 < 2; i2++) {
                if (new File(strArr[i2]).exists()) {
                    z = true;
                }
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean e() {
        return a(1);
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public static String n(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].hashCode());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "c";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "c";
        }
    }

    public static String l(Context context) {
        if (context == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(o(context));
        stringBuffer.append(z());
        stringBuffer.append(A());
        return stringBuffer.toString();
    }

    public static long a(String str) {
        long j2;
        if (str != null) {
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                String trim = str.substring(indexOf + 1).trim();
                int lastIndexOf = trim.lastIndexOf(32);
                if (lastIndexOf != -1) {
                    String substring = trim.substring(lastIndexOf + 1);
                    try {
                        long parseLong = Long.parseLong(trim.substring(0, lastIndexOf).trim());
                        if ("kb".equalsIgnoreCase(substring)) {
                            j2 = 1024;
                        } else if ("mb".equalsIgnoreCase(substring)) {
                            j2 = 1048576;
                        } else if (!"gb".equalsIgnoreCase(substring)) {
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.c("JDMob.Security.Phone", "Unexpected mem unit format: " + trim);
                                return parseLong;
                            }
                            return parseLong;
                        } else {
                            j2 = IjkMediaMeta.AV_CH_STEREO_RIGHT;
                        }
                        return parseLong * j2;
                    } catch (Exception e2) {
                        if (com.jd.stat.common.b.b.a) {
                            e2.getLocalizedMessage();
                            return -1L;
                        }
                        return -1L;
                    }
                } else if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.a("JDMob.Security.Phone", "Unexpected mem value format: " + trim);
                    return -1L;
                } else {
                    return -1L;
                }
            } else if (com.jd.stat.common.b.b.a) {
                com.jd.stat.common.b.b.a("JDMob.Security.Phone", "Unexpected mem format: " + str);
                return -1L;
            } else {
                return -1L;
            }
        }
        return -1L;
    }

    public static String b() {
        if (a()) {
            try {
                File file = new File("/sys/class/mmc_host/mmc1");
                if (file.exists()) {
                    String str = null;
                    File[] listFiles = file.listFiles();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= listFiles.length) {
                            break;
                        } else if (listFiles[i2].toString().contains("mmc1:")) {
                            str = listFiles[i2].toString();
                            String str2 = (String) listFiles[i2].toString().subSequence(str.length() - 4, str.length());
                            break;
                        } else {
                            i2++;
                        }
                    }
                    return new BufferedReader(new FileReader(str + "/cid")).readLine();
                }
                return "unknow";
            } catch (Exception unused) {
                return "unknow";
            }
        }
        return "unknow";
    }

    public static boolean f() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static int m(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimState();
        } catch (Exception unused) {
            return 0;
        }
    }

    private static int o(Context context) {
        try {
            if (com.jd.stat.security.c.k()) {
                return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) != null ? 1 : 0;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static String c(String str) {
        try {
            Class.forName(str);
            return "1";
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return "0";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "c";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Process] */
    private static String b(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        IOException e2;
        try {
            try {
                str = Runtime.getRuntime().exec("getprop " + str);
            } catch (IOException e3) {
                bufferedReader = null;
                e2 = e3;
                str = 0;
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
                str = 0;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(str.getInputStream()), 1024);
            } catch (IOException e4) {
                bufferedReader = null;
                e2 = e4;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                if (str != 0) {
                    str.destroy();
                }
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                if (str != 0) {
                    str.destroy();
                    return readLine;
                }
                return readLine;
            } catch (IOException e7) {
                e2 = e7;
                e2.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                if (str != 0) {
                    str.destroy();
                }
                return "";
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean a(int i2) {
        if (c.a() < 9) {
            return false;
        }
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i3 = 0; i3 < numberOfCameras; i3++) {
            Camera.getCameraInfo(i3, cameraInfo);
            if (i2 == cameraInfo.facing) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        return a(0);
    }

    public static boolean i(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    }

    public static void j(Context context) {
        if (com.jd.stat.security.d.a().H()) {
            try {
                if (Build.VERSION.SDK_INT < 17) {
                    a = new WebView(context).getSettings().getUserAgentString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String k(Context context) {
        if (com.jd.stat.security.d.a().H()) {
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    a = WebSettings.getDefaultUserAgent(context);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return a;
        }
        return "";
    }
}
