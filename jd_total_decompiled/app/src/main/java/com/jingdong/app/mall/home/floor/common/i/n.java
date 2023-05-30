package com.jingdong.app.mall.home.floor.common.i;

import android.content.res.Resources;
import android.os.Environment;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* loaded from: classes4.dex */
public class n {
    private static final String a = "n";
    private static v b = new v();

    /* renamed from: c */
    private static v f9337c = new v();

    private static String a() {
        String deviceBrand = BaseInfo.getDeviceBrand();
        return TextUtils.isEmpty(deviceBrand) ? "" : deviceBrand;
    }

    public static String b() {
        String deviceModel = BaseInfo.getDeviceModel();
        return TextUtils.isEmpty(deviceModel) ? "" : deviceModel;
    }

    public static int c() {
        if (b.c()) {
            return b.b();
        }
        Resources resources = JdSdk.getInstance().getApplication().getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
        b.e(dimensionPixelSize);
        return dimensionPixelSize;
    }

    public static boolean d() {
        return "HONOR".equals(BaseInfo.getDeviceModel()) || "HONOR".equals(BaseInfo.getDeviceModel());
    }

    public static boolean e() {
        if (f()) {
            return b().contains("m3 note");
        }
        return false;
    }

    public static boolean f() {
        return "Meizu".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    public static boolean g() {
        if (n()) {
            return b().contains("MI 4");
        }
        return false;
    }

    public static boolean h() {
        return "Motorola".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    public static boolean i() {
        if (h()) {
            return b().contains("Motot G");
        }
        return false;
    }

    public static boolean j() {
        return "OnePlus".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    public static boolean k() {
        if (n()) {
            return b().contains("HM") || b().contains("REDMI") || b().contains("Redmi");
        }
        return false;
    }

    public static boolean l() {
        if (n()) {
            return b().contains("Redmi Note 3");
        }
        return false;
    }

    public static boolean m() {
        return b().contains("vivo X7");
    }

    /* JADX WARN: Removed duplicated region for block: B:173:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean n() {
        FileInputStream fileInputStream;
        if (f9337c.c()) {
            return f9337c.a();
        }
        boolean z = true;
        if (!Constant.DEVICE_XIAOMI.equals(a()) && !Constant.DEVICE_XIAOMI.equals(b()) && !Constant.DEVICE_XIAOMI.equals(BaseInfo.getDeviceManufacture())) {
            Properties properties = new Properties();
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    if (fileInputStream2 != null) {
                    }
                    throw th;
                }
                try {
                    try {
                        properties.load(fileInputStream);
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e3) {
                                com.jingdong.app.mall.home.o.a.f.s0(a, e3);
                            }
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    com.jingdong.app.mall.home.o.a.f.s0(a, e);
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (properties.getProperty("ro.miui.ui.version.code", null) == null) {
                        z = false;
                    }
                    f9337c.d(z);
                    return z;
                }
            } catch (Exception e5) {
                com.jingdong.app.mall.home.o.a.f.s0(a, e5);
            }
            if (properties.getProperty("ro.miui.ui.version.code", null) == null && properties.getProperty("ro.miui.ui.version.name", null) == null && properties.getProperty("ro.miui.internal.storage", null) == null) {
                z = false;
            }
            f9337c.d(z);
            return z;
        }
        f9337c.d(true);
        return true;
    }

    public static boolean o(String str) {
        return (k() || l() || i() || g() || e() || m() || TextUtils.isEmpty(str) || !str.toLowerCase().contains(".png")) ? false : true;
    }
}
