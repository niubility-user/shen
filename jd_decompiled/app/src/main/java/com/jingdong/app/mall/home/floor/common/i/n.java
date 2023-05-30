package com.jingdong.app.mall.home.floor.common.i;

import android.content.res.Resources;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;

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

    /* JADX WARN: Removed duplicated region for block: B:114:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean n() {
        /*
            com.jingdong.app.mall.home.floor.common.i.v r0 = com.jingdong.app.mall.home.floor.common.i.n.f9337c
            boolean r0 = r0.c()
            if (r0 == 0) goto Lf
            com.jingdong.app.mall.home.floor.common.i.v r0 = com.jingdong.app.mall.home.floor.common.i.n.f9337c
            boolean r0 = r0.a()
            return r0
        Lf:
            java.lang.String r0 = a()
            java.lang.String r1 = "Xiaomi"
            boolean r0 = r1.equals(r0)
            r2 = 1
            if (r0 != 0) goto L94
            java.lang.String r0 = b()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L94
            java.lang.String r0 = com.jingdong.sdk.baseinfo.BaseInfo.getDeviceManufacture()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L31
            goto L94
        L31:
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.io.File r5 = android.os.Environment.getRootDirectory()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.String r6 = "build.prop"
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r0.load(r3)     // Catch: java.lang.Exception -> L4e java.lang.Throwable -> L85
            r3.close()     // Catch: java.lang.Exception -> L5f
            goto L65
        L4e:
            r4 = move-exception
            goto L54
        L50:
            r0 = move-exception
            goto L87
        L52:
            r4 = move-exception
            r3 = r1
        L54:
            java.lang.String r5 = com.jingdong.app.mall.home.floor.common.i.n.a     // Catch: java.lang.Throwable -> L85
            com.jingdong.app.mall.home.o.a.f.s0(r5, r4)     // Catch: java.lang.Throwable -> L85
            if (r3 == 0) goto L65
            r3.close()     // Catch: java.lang.Exception -> L5f
            goto L65
        L5f:
            r3 = move-exception
            java.lang.String r4 = com.jingdong.app.mall.home.floor.common.i.n.a
            com.jingdong.app.mall.home.o.a.f.s0(r4, r3)
        L65:
            java.lang.String r3 = "ro.miui.ui.version.code"
            java.lang.String r3 = r0.getProperty(r3, r1)
            if (r3 != 0) goto L7f
            java.lang.String r3 = "ro.miui.ui.version.name"
            java.lang.String r3 = r0.getProperty(r3, r1)
            if (r3 != 0) goto L7f
            java.lang.String r3 = "ro.miui.internal.storage"
            java.lang.String r0 = r0.getProperty(r3, r1)
            if (r0 == 0) goto L7e
            goto L7f
        L7e:
            r2 = 0
        L7f:
            com.jingdong.app.mall.home.floor.common.i.v r0 = com.jingdong.app.mall.home.floor.common.i.n.f9337c
            r0.d(r2)
            return r2
        L85:
            r0 = move-exception
            r1 = r3
        L87:
            if (r1 == 0) goto L93
            r1.close()     // Catch: java.lang.Exception -> L8d
            goto L93
        L8d:
            r1 = move-exception
            java.lang.String r2 = com.jingdong.app.mall.home.floor.common.i.n.a
            com.jingdong.app.mall.home.o.a.f.s0(r2, r1)
        L93:
            throw r0
        L94:
            com.jingdong.app.mall.home.floor.common.i.v r0 = com.jingdong.app.mall.home.floor.common.i.n.f9337c
            r0.d(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.common.i.n.n():boolean");
    }

    public static boolean o(String str) {
        return (k() || l() || i() || g() || e() || m() || TextUtils.isEmpty(str) || !str.toLowerCase().contains(".png")) ? false : true;
    }
}
