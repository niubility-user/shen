package com.jdjr.risk.device.c;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/* loaded from: classes18.dex */
public class ak {
    static String a = "VirtualMachineDetect";
    static int b;

    /* renamed from: c  reason: collision with root package name */
    private static String[] f7347c = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    private static String[] d = {"goldfish"};

    /* renamed from: e  reason: collision with root package name */
    private static String[] f7348e = {"/data/app/com.bluestacks.appmart-1.apk", "/data/app/com.bluestacks.BstCommandProcessor-1.apk", "/data/app/com.bluestacks.help-1.apk", "/data/app/com.bluestacks.home-1.apk", "/data/app/com.bluestacks.s2p-1.apk", "/data/app/com.bluestacks.searchapp-1.apk", "/data/bluestacks.prop", "/data/data/com.androVM.vmconfig", "/data/data/com.bluestacks.accelerometerui", "/data/data/com.bluestacks.appfinder", "/data/data/com.bluestacks.appmart", "/data/data/com.bluestacks.appsettings", "/data/data/com.bluestacks.BstCommandProcessor", "/data/data/com.bluestacks.bstfolder", "/data/data/com.bluestacks.help", "/data/data/com.bluestacks.home", "/data/data/com.bluestacks.s2p", "/data/data/com.bluestacks.searchapp", "/data/data/com.bluestacks.settings", "/data/data/com.bluestacks.setup", "/data/data/com.bluestacks.spotlight", "/data/youwave_id", "/dev/vboxguest", "/dev/vboxuser", "/fstab.vbox86", "/init.vbox86.rc", "/mnt/prebundledapps/bluestacks.prop.orig", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s2", "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s3", "/mnt/sdcard/bstfolder/InputMapper/com.bluestacks.appmart.cfg", "/mnt/sdcard/buildroid-gapps-ics-20120317-signed.tgz", "/mnt/sdcard/windows/InputMapper/com.bluestacks.appmart.cfg", "/proc/irq/9/vboxguest", "/sys/bus/pci/drivers/vboxguest", "/sys/bus/pci/drivers/vboxguest/0000:00:04.0", "/sys/bus/pci/drivers/vboxguest/bind", "/sys/bus/pci/drivers/vboxguest/module", "/sys/bus/pci/drivers/vboxguest/new_id", "/sys/bus/pci/drivers/vboxguest/remove_id", "/sys/bus/pci/drivers/vboxguest/uevent", "/sys/bus/pci/drivers/vboxguest/unbind", "/sys/bus/platform/drivers/qemu_pipe", "/sys/bus/platform/drivers/qemu_trace", "/sys/class/bdi/vboxsf-c", "/sys/class/misc/vboxguest", "/sys/class/misc/vboxuser", "/sys/devices/virtual/bdi/vboxsf-c", "/sys/devices/virtual/misc/vboxguest", "/sys/devices/virtual/misc/vboxguest/dev", "/sys/devices/virtual/misc/vboxguest/power", "/sys/devices/virtual/misc/vboxguest/subsystem", "/sys/devices/virtual/misc/vboxguest/uevent", "/sys/devices/virtual/misc/vboxuser", "/sys/devices/virtual/misc/vboxuser/dev", "/sys/devices/virtual/misc/vboxuser/power", "/sys/devices/virtual/misc/vboxuser/subsystem", "/sys/devices/virtual/misc/vboxuser/uevent", "/sys/module/vboxguest", "/sys/module/vboxguest/coresize", "/sys/module/vboxguest/drivers", "/sys/module/vboxguest/drivers/pci:vboxguest", "/sys/module/vboxguest/holders", "/sys/module/vboxguest/holders/vboxsf", "/sys/module/vboxguest/initsize", "/sys/module/vboxguest/initstate", "/sys/module/vboxguest/notes", "/sys/module/vboxguest/notes/.note.gnu.build-id", "/sys/module/vboxguest/parameters", "/sys/module/vboxguest/parameters/log", "/sys/module/vboxguest/parameters/log_dest", "/sys/module/vboxguest/parameters/log_flags", "/sys/module/vboxguest/refcnt", "/sys/module/vboxguest/sections", "/sys/module/vboxguest/sections/.altinstructions", "/sys/module/vboxguest/sections/.altinstr_replacement", "/sys/module/vboxguest/sections/.bss", "/sys/module/vboxguest/sections/.data", "/sys/module/vboxguest/sections/.devinit.data", "/sys/module/vboxguest/sections/.exit.text", "/sys/module/vboxguest/sections/.fixup", "/sys/module/vboxguest/sections/.gnu.linkonce.this_module", "/sys/module/vboxguest/sections/.init.text", "/sys/module/vboxguest/sections/.note.gnu.build-id", "/sys/module/vboxguest/sections/.rodata", "/sys/module/vboxguest/sections/.rodata.str1.1", "/sys/module/vboxguest/sections/.smp_locks", "/sys/module/vboxguest/sections/.strtab", "/sys/module/vboxguest/sections/.symtab", "/sys/module/vboxguest/sections/.text", "/sys/module/vboxguest/sections/__ex_table", "/sys/module/vboxguest/sections/__ksymtab", "/sys/module/vboxguest/sections/__ksymtab_strings", "/sys/module/vboxguest/sections/__param", "/sys/module/vboxguest/srcversion", "/sys/module/vboxguest/taint", "/sys/module/vboxguest/uevent", "/sys/module/vboxguest/version", "/sys/module/vboxsf", "/sys/module/vboxsf/coresize", "/sys/module/vboxsf/holders", "/sys/module/vboxsf/initsize", "/sys/module/vboxsf/initstate", "/sys/module/vboxsf/notes", "/sys/module/vboxsf/notes/.note.gnu.build-id", "/sys/module/vboxsf/refcnt", "/sys/module/vboxsf/sections", "/sys/module/vboxsf/sections/.bss", "/sys/module/vboxsf/sections/.data", "/sys/module/vboxsf/sections/.exit.text", "/sys/module/vboxsf/sections/.gnu.linkonce.this_module", "/sys/module/vboxsf/sections/.init.text", "/sys/module/vboxsf/sections/.note.gnu.build-id", "/sys/module/vboxsf/sections/.rodata", "/sys/module/vboxsf/sections/.rodata.str1.1", "/sys/module/vboxsf/sections/.smp_locks", "/sys/module/vboxsf/sections/.strtab", "/sys/module/vboxsf/sections/.symtab", "/sys/module/vboxsf/sections/.text", "/sys/module/vboxsf/sections/__bug_table", "/sys/module/vboxsf/sections/__param", "/sys/module/vboxsf/srcversion", "/sys/module/vboxsf/taint", "/sys/module/vboxsf/uevent", "/sys/module/vboxsf/version", "/sys/module/vboxvideo", "/sys/module/vboxvideo/coresize", "/sys/module/vboxvideo/holders", "/sys/module/vboxvideo/initsize", "/sys/module/vboxvideo/initstate", "/sys/module/vboxvideo/notes", "/sys/module/vboxvideo/notes/.note.gnu.build-id", "/sys/module/vboxvideo/refcnt", "/sys/module/vboxvideo/sections", "/sys/module/vboxvideo/sections/.data", "/sys/module/vboxvideo/sections/.exit.text", "/sys/module/vboxvideo/sections/.gnu.linkonce.this_module", "/sys/module/vboxvideo/sections/.init.text", "/sys/module/vboxvideo/sections/.note.gnu.build-id", "/sys/module/vboxvideo/sections/.rodata.str1.1", "/sys/module/vboxvideo/sections/.strtab", "/sys/module/vboxvideo/sections/.symtab", "/sys/module/vboxvideo/sections/.text", "/sys/module/vboxvideo/srcversion", "/sys/module/vboxvideo/taint", "/sys/module/vboxvideo/uevent", "/sys/module/vboxvideo/version", "/system/app/bluestacksHome.apk", "/system/bin/androVM-prop", "/system/bin/androVM-vbox-sf", "/system/bin/androVM_setprop", "/system/bin/get_androVM_host", "/system/bin/mount.vboxsf", "/system/etc/init.androVM.sh", "/system/etc/init.buildroid.sh", "/system/lib/hw/audio.primary.vbox86.so", "/system/lib/hw/camera.vbox86.so", "/system/lib/hw/gps.vbox86.so", "/system/lib/hw/gralloc.vbox86.so", "/system/lib/hw/sensors.vbox86.so", "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest", "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest/vboxguest.ko", "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf", "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf/vboxsf.ko", "/system/lib/vboxguest.ko", "/system/lib/vboxsf.ko", "/system/lib/vboxvideo.ko", "/system/usr/idc/androVM_Virtual_Input.idc", "/system/usr/keylayout/androVM_Virtual_Input.kl", "/system/xbin/mount.vboxsf", "/ueventd.android_x86.rc", "/ueventd.vbox86.rc"};

    private static Boolean a(String str) {
        boolean z = false;
        FileInputStream fileInputStream = null;
        try {
            try {
                File file = new File(str);
                if (file.exists() && file.canRead()) {
                    byte[] bArr = new byte[1024];
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        fileInputStream2.read(bArr);
                        String str2 = new String(bArr);
                        String[] strArr = d;
                        int length = strArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                fileInputStream = fileInputStream2;
                                break;
                            } else if (str2.indexOf(strArr[i2]) != -1) {
                                fileInputStream = fileInputStream2;
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                    } catch (Exception unused) {
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return Boolean.valueOf(z);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return Boolean.valueOf(z);
    }

    private static boolean a() {
        try {
            String a2 = j.a(new String[]{"/system/bin/cat", "/proc/cpuinfo"});
            if (!TextUtils.isEmpty(a2)) {
                if (!a2.contains(ProductListConstant.INTEL)) {
                    if (a2.contains("amd")) {
                    }
                }
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:88:0x014c, code lost:
        if (com.jingdong.sdk.baseinfo.BaseInfo.getOSFingerprint().contains("generic/vbox86p/vbox86p") != false) goto L91;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Boolean b(Context context) {
        boolean z;
        boolean z2 = false;
        try {
            String n2 = g.n();
            z = true;
            if (!TextUtils.isEmpty(n2) && (n2.contains("sdk") || n2.contains("sdk_x86") || n2.contains("sdk_google") || n2.contains("Andy") || n2.contains("Droid4X") || n2.contains("nox") || n2.contains("vbox86p") || n2.contains("aries"))) {
                z2 = true;
            }
            String m2 = g.m();
            if (!TextUtils.isEmpty(m2) && (m2.contains("Genymotion") || m2.contains("Andy") || m2.contains("nox") || m2.contains("TiantianVM"))) {
                z2 = true;
            }
            if (BaseInfo.getDeviceBrand().contains("Andy")) {
                z2 = true;
            }
            String k2 = g.k();
            if (!TextUtils.isEmpty(k2) && (k2.contains("Andy") || k2.contains("Droid4X") || k2.contains("nox") || k2.contains("vbox86p") || k2.contains("aries"))) {
                z2 = true;
            }
            String b2 = g.b();
            if (!TextUtils.isEmpty(k2) && (b2.contains("Emulator") || b2.contains("google_sdk") || b2.contains("Droid4X") || b2.contains("TiantianVM") || b2.contains("Andy") || b2.contains("Android SDK built for x86_64") || b2.contains("Android SDK built for x86"))) {
                z2 = true;
            }
            String str = Build.HARDWARE;
            if (str.contains("vbox86") || str.contains("nox") || str.contains("ttVM_x86")) {
                z2 = true;
            }
            if (!BaseInfo.getOSFingerprint().contains("generic/sdk/generic") && !BaseInfo.getOSFingerprint().contains("generic_x86/sdk_x86/generic_x86") && !BaseInfo.getOSFingerprint().contains("Andy") && !BaseInfo.getOSFingerprint().contains("ttVM_Hdragon") && !BaseInfo.getOSFingerprint().contains("generic/google_sdk/generic") && !BaseInfo.getOSFingerprint().contains("vbox86p")) {
            }
        } catch (Exception unused) {
        }
        return Boolean.valueOf(z);
        z = z2;
        return Boolean.valueOf(z);
    }

    private boolean b() {
        try {
            if (Build.VERSION.SDK_INT < 28) {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod(IMantoServerRequester.GET, String.class);
                declaredMethod.setAccessible(true);
                return "1".equals(declaredMethod.invoke(null, "ro.kernel.qemu").toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private static boolean c() {
        int i2 = 0;
        while (true) {
            String[] strArr = f7347c;
            if (i2 >= strArr.length) {
                return false;
            }
            if (new File(strArr[i2]).exists()) {
                return true;
            }
            i2++;
        }
    }

    private static boolean c(Context context) {
        SensorManager sensorManager;
        try {
            if (!BaseInfo.isAgreedPrivacy() || (sensorManager = (SensorManager) context.getSystemService("sensor")) == null) {
                return false;
            }
            return sensorManager.getDefaultSensor(5) == null;
        } catch (Exception unused) {
            return false;
        }
    }

    private static Boolean d() {
        int i2 = 0;
        while (true) {
            String[] strArr = f7348e;
            if (i2 >= strArr.length) {
                return Boolean.FALSE;
            }
            if (new File(strArr[i2]).exists()) {
                return Boolean.TRUE;
            }
            i2++;
        }
    }

    private static boolean e() {
        String a2 = j.a().a("gsm.version.baseband");
        return (a2 != null && a2.contains("1.0.0.0")) | TextUtils.isEmpty(a2);
    }

    private static boolean f() {
        String a2 = j.a().a("ro.build.flavor");
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        return a2.contains("sdk_gphone") | a2.contains("vbox");
    }

    public int a(Context context) {
        b = 0;
        try {
            if (a()) {
                b++;
            }
            if (b()) {
                b++;
            }
            if (c()) {
                b++;
            }
            if (a("/proc/tty/drivers").booleanValue() || a("/proc/cpuinfo").booleanValue()) {
                b++;
            }
            if (b(context).booleanValue()) {
                b++;
            }
            if (d().booleanValue()) {
                b++;
            }
            if (e()) {
                b++;
            }
            if (f()) {
                b++;
            }
            if (c(context)) {
                b++;
            }
        } catch (Exception unused) {
        }
        return b;
    }
}
