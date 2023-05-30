package com.jd.jdsec.a.k;

import android.content.Context;
import android.hardware.Sensor;
import android.text.TextUtils;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes13.dex */
public class i {
    private static long a;
    private static String[] b = {"/data/app/com.bluestacks.appmart-1.apk", "/data/app/com.bluestacks.BstCommandProcessor-1.apk", "/data/app/com.bluestacks.help-1.apk", "/data/app/com.bluestacks.home-1.apk", "/data/app/com.bluestacks.s2p-1.apk", "/data/app/com.bluestacks.searchapp-1.apk", "/data/bluestacks.prop", "/data/data/com.androVM.vmconfig", "/data/data/com.bluestacks.accelerometerui", "/data/data/com.bluestacks.appfinder", "/data/data/com.bluestacks.appmart", "/data/data/com.bluestacks.appsettings", "/data/data/com.bluestacks.BstCommandProcessor", "/data/data/com.bluestacks.bstfolder", "/data/data/com.bluestacks.help", "/data/data/com.bluestacks.home", "/data/data/com.bluestacks.s2p", "/data/data/com.bluestacks.searchapp", "/data/data/com.bluestacks.settings", "/data/data/com.bluestacks.setup", "/data/data/com.bluestacks.spotlight", "/mnt/prebundledapps/bluestacks.prop.orig", "/dev/socket/genyd", "/dev/socket/baseband_genyd", "/dev/socket/qemud", "/dev/qemu_pipe", "ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc", "fstab.andy", "ueventd.andy.rc", "fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace"};

    /* renamed from: c  reason: collision with root package name */
    private static String[] f2724c = {"goldfish"};

    public static Boolean a(Context context) {
        if (BaseInfo.getDeviceModel().contains("vmos")) {
            return Boolean.TRUE;
        }
        if (BaseInfo.getDeviceManufacture().contains("vmos")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static String b(String str) {
        String c2 = com.jd.jdsec.a.l.e.c(str);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        return c2;
    }

    public static boolean c() {
        int i2 = 0;
        while (true) {
            String[] strArr = b;
            if (i2 >= strArr.length) {
                return false;
            }
            if (new File(strArr[i2]).exists()) {
                return true;
            }
            i2++;
        }
    }

    public static boolean d(String[] strArr) {
        for (String str : strArr) {
            if (new File(str + "xposed").exists()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean e() {
        if (com.jd.jdsec.a.l.a.b("cat /proc/self/cgroup") == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Long f(Context context) {
        try {
            Boolean valueOf = Boolean.valueOf(!a.b(context));
            if (b("ro.product.cpu.abi").contains("x86")) {
                a |= 1;
            }
            if (b("ro.product.cpu.abilist").contains("x86")) {
                a |= 2;
            }
            if (com.jd.jdsec.a.l.a.b("uname -m").contains("i686")) {
                a |= 4;
            }
            if (!k(context)) {
                a |= 8;
            }
            if (!m(context) && valueOf.booleanValue()) {
                a |= 16;
            }
            if (e().booleanValue()) {
                a |= 32;
            }
            if (c()) {
                a |= 64;
            }
            if (j()) {
                a |= 128;
            }
            if (TextUtils.isEmpty(b("gsm.version.baseband")) && valueOf.booleanValue()) {
                a |= 256;
            }
            String l2 = l();
            if (l2.contains(ProductListConstant.INTEL) || l2.contains("amd")) {
                a |= 512;
            }
            if (h(context).booleanValue()) {
                a |= 1024;
            }
            if (a(context).booleanValue()) {
                a |= 2048;
            }
            if (i()) {
                a |= 4096;
            }
            String[] strArr = {"/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
            if (d(strArr)) {
                a |= IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT;
            }
            if (g(strArr)) {
                a |= IjkMediaMeta.AV_CH_TOP_BACK_LEFT;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }

    public static boolean g(String[] strArr) {
        for (String str : strArr) {
            if (new File(str + "su").exists()) {
                return true;
            }
            if (new File(str + "busybox").exists()) {
                return true;
            }
        }
        return false;
    }

    public static Boolean h(Context context) {
        Iterator<Sensor> it = com.jd.jdsec.a.g.E(context).iterator();
        while (it.hasNext()) {
            if (it.next().getName().contains("Goldfish")) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static boolean i() {
        File[] fileArr = {new File("/proc/tty/drivers"), new File("/proc/cpuinfo")};
        for (int i2 = 0; i2 < 2; i2++) {
            File file = fileArr[i2];
            if (file.exists() && file.canRead()) {
                byte[] bArr = new byte[1024];
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String str = new String(bArr);
                for (String str2 : f2724c) {
                    if (str.indexOf(str2) != -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean j() {
        String[] strArr = {"sdk", "sdk_x86", "sdk_google", "Andy", "Droid4X", "nox", "vbox86p", "aries"};
        String[] strArr2 = {"Genymotion", "Andy", "nox", "TiantianVM"};
        String[] strArr3 = {"Emulator", "google_sdk", "Droid4X", "TiantianVM", "Andy", "Android SDK built for x86_64", "Android SDK built for x86"};
        String[] strArr4 = {"vbox86", "nox", "ttVM_x86"};
        String deviceProductName = BaseInfo.getDeviceProductName();
        String deviceManufacture = BaseInfo.getDeviceManufacture();
        String deviceModel = BaseInfo.getDeviceModel();
        String hardwareName = BaseInfo.getHardwareName();
        com.jd.jdsec.a.l.b.e("JDSec.Security.MonitorCheck", deviceProductName + deviceManufacture + deviceModel + hardwareName);
        for (int i2 = 0; i2 < 8; i2++) {
            if (deviceProductName.contains(strArr[i2])) {
                return true;
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            if (deviceManufacture.contains(strArr2[i3])) {
                return true;
            }
        }
        for (int i4 = 0; i4 < 7; i4++) {
            if (deviceModel.contains(strArr3[i4])) {
                return true;
            }
        }
        for (int i5 = 0; i5 < 3; i5++) {
            if (hardwareName.contains(strArr4[i5])) {
                return true;
            }
        }
        String oSFingerprint = BaseInfo.getOSFingerprint();
        return oSFingerprint.contains("generic/sdk/generic") || oSFingerprint.contains("generic_x86/sdk_x86/generic_x86") || oSFingerprint.contains("Andy") || oSFingerprint.contains("ttVM_Hdragon") || oSFingerprint.contains("generic/google_sdk/generic") || oSFingerprint.contains("vbox86p") || oSFingerprint.contains("generic/vbox86p/vbox86p");
    }

    private static boolean k(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static String l() {
        try {
            Process start = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (IOException unused) {
            return "";
        }
    }

    private static boolean m(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }
}
