package com.jingdong.aura.a.b;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.aura.R;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.reflection.Hack;
import com.jingdong.aura.core.runing.resource.DelegateResourcesUtils;
import com.jingdong.aura.core.ui.WelcomeActivity;
import com.jingdong.aura.wrapper.AuraDowngradeBundle;
import com.jingdong.aura.wrapper.listener.AuraDebugTimeListener;
import com.jingdong.aura.wrapper.listener.AuraMonitorConfigListener;
import com.jingdong.aura.wrapper.listener.AuraPageCallback;
import com.jingdong.aura.wrapper.listener.AuraPrivacyInfoListener;
import com.jingdong.aura.wrapper.listener.IMobileLogCallback;
import com.jingdong.aura.wrapper.mhCallback.ImHCallBack;
import com.jingdong.aura.wrapper.monitor.DefaultMobileLogImpl;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class c {
    private static boolean b = false;

    /* renamed from: c */
    private static boolean f12090c = false;
    private static boolean d = false;

    /* renamed from: e */
    private static boolean f12091e = true;

    /* renamed from: f */
    private static boolean f12092f = true;

    /* renamed from: g */
    private static boolean f12093g = true;

    /* renamed from: h */
    private static boolean f12094h = true;

    /* renamed from: i */
    private static boolean f12095i = true;

    /* renamed from: j */
    private static boolean f12096j = true;

    /* renamed from: k */
    private static boolean f12097k = true;

    /* renamed from: l */
    private static boolean f12098l = false;

    /* renamed from: m */
    private static boolean f12099m = true;

    /* renamed from: n */
    private static int f12100n = 2;
    private static String o = "armeabi";
    private static boolean p = false;
    private static int q = 26;
    private static boolean r;
    static Context s;
    private static AuraDebugTimeListener t;
    private static AuraPageCallback u;
    private static AuraPrivacyInfoListener x;
    private static AuraMonitorConfigListener y;
    private static AuraPageCallback v = new a();
    private static Map<String, Boolean> w = new HashMap();
    private static IMobileLogCallback z = new DefaultMobileLogImpl();
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("AuraConfigInternal");

    /* loaded from: classes4.dex */
    class a implements AuraPageCallback {
        a() {
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
        public Intent getClassNotFoundPage(Intent intent) {
            if (c.u != null) {
                return c.u.getClassNotFoundPage(intent);
            }
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(c.s, WelcomeActivity.class);
            intent.addFlags(268435456);
            return intent;
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
        public String getProvidedBundleNotFoundPageName() {
            return c.u == null ? "" : c.u.getProvidedBundleNotFoundPageName();
        }

        @Override // com.jingdong.aura.wrapper.listener.AuraPageCallback
        public Intent isRedirectToLoadingDexPage(Intent intent) {
            if (c.u == null) {
                return null;
            }
            return c.u.isRedirectToLoadingDexPage(intent);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Application application = l.a;
            if (application != null) {
                Toast.makeText(application, R.string.aura_nospace, 0).show();
            }
        }
    }

    /* renamed from: com.jingdong.aura.a.b.c$c */
    /* loaded from: classes4.dex */
    public class RunnableC0393c implements Runnable {
        RunnableC0393c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Application application = l.a;
            if (application != null) {
                Toast.makeText(application, R.string.aura_nospace, 0).show();
            }
        }
    }

    public static boolean A() {
        return f12099m;
    }

    public static boolean B() {
        return f12098l;
    }

    public static boolean C() {
        return f12097k;
    }

    public static boolean D() {
        return f12094h;
    }

    public static IMobileLogCallback E() {
        IMobileLogCallback iMobileLogCallback = z;
        return iMobileLogCallback == null ? new DefaultMobileLogImpl() : iMobileLogCallback;
    }

    public static AuraPrivacyInfoListener F() {
        return x;
    }

    public static List<Map<String, String>> G() {
        return com.jingdong.aura.a.a.a.c().d();
    }

    public static int H() {
        return com.jingdong.aura.core.runing.resource.a.c();
    }

    public static SharedPreferences I() {
        return com.jingdong.aura.core.util.b.a();
    }

    public static int J() {
        return 3;
    }

    public static boolean K() {
        return p;
    }

    public static int L() {
        return f12100n;
    }

    public static boolean M() {
        return r;
    }

    public static boolean N() {
        return b;
    }

    public static boolean O() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.isMonitorProvidedInstallFail()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("monitorProvidedBundleInstall error");
        }
        a.a("monitorSwitch:" + z2);
        return z2;
    }

    public static boolean P() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.providedBundleActivityResultCheck()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("providedBundleActivityResultCheck error");
        }
        a.a("providedBundleActivityResultCheck:" + z2);
        return z2;
    }

    public static boolean Q() {
        return w();
    }

    public static boolean R() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.splitApkConfig()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("splitApkFlag error");
        }
        a.a("splitApkFlag:" + z2);
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0009, code lost:
        if (r1.startBundleByBackUp() != false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean S() {
        boolean z2 = true;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
            }
            z2 = false;
        } catch (Throwable unused) {
            a.d("startBundleByBackUp error");
        }
        a.a("startBundleByBackUp:" + z2);
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0009, code lost:
        if (r1.updateMetaConfig() != false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean T() {
        boolean z2 = true;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
            }
            z2 = false;
        } catch (Throwable unused) {
            a.d("updateMetaConfig error");
        }
        a.a("updateMetaConfig:" + z2);
        return z2;
    }

    public static boolean U() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.verityBundleZipSign()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("verityBundleZipSign error");
        }
        a.a("verityBundleZipSign:" + z2);
        return z2;
    }

    public static void b(int i2) {
    }

    public static void b(boolean z2) {
        d = z2;
    }

    public static void c(boolean z2) {
        a.e("setEnabled called, enabled = " + z2);
        b = z2;
    }

    public static long d(String str) {
        if (com.jingdong.aura.a.b.k.b.b(str) != null) {
            return ((h) r0).l();
        }
        long c2 = com.jingdong.aura.a.b.l.h.c(new File(i(), str));
        return c2 > 0 ? c2 : com.jingdong.aura.a.a.a.c().h(str);
    }

    public static void e() {
        com.jingdong.aura.a.b.k.b.p();
    }

    public static void f(boolean z2) {
        f12096j = z2;
    }

    public static void g(boolean z2) {
        f12090c = z2;
    }

    public static boolean h() {
        return d;
    }

    public static File i() {
        return com.jingdong.aura.a.b.k.b.b();
    }

    public static void j(boolean z2) {
        f12093g = z2;
    }

    public static String k() {
        return "1.4.7.7";
    }

    public static boolean k(String str) {
        return com.jingdong.aura.a.a.a.c().i(str);
    }

    public static boolean l(String str) {
        if (w.containsKey(str)) {
            return w.get(str).booleanValue();
        }
        return true;
    }

    public static AuraPageCallback m() {
        return v;
    }

    public static void n(boolean z2) {
        f12094h = z2;
    }

    public static String o() {
        try {
            return com.jingdong.aura.a.b.k.b.a();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p() {
        try {
            return com.jingdong.aura.a.a.d.a();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String q() {
        try {
            return com.jingdong.aura.a.a.d.b();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String r() {
        try {
            return com.jingdong.aura.a.a.d.c();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String s() {
        try {
            return com.jingdong.aura.a.a.d.d();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String t() {
        try {
            return com.jingdong.aura.a.a.d.e();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean u() {
        return f12091e;
    }

    public static boolean v() {
        return f12096j;
    }

    public static boolean w() {
        return f12090c;
    }

    public static boolean x() {
        return f12095i;
    }

    public static boolean y() {
        return f12092f;
    }

    public static boolean z() {
        return f12093g;
    }

    public static void a(Context context) {
        s = context;
        if (x == null) {
            x = new com.jingdong.aura.wrapper.privacy.a(s);
        }
        AuraMonitorConfigListener auraMonitorConfigListener = y;
        if (auraMonitorConfigListener == null) {
            auraMonitorConfigListener = new com.jingdong.aura.wrapper.monitor.a();
        }
        y = auraMonitorConfigListener;
    }

    public static String b(String str) {
        return com.jingdong.aura.a.a.a.c().b(str);
    }

    public static void e(boolean z2) {
        f12091e = z2;
    }

    public static long f(String str) {
        return com.jingdong.aura.a.b.l.h.c(new File(i(), str));
    }

    public static boolean g() {
        return F().getPrivacyState();
    }

    public static void h(boolean z2) {
        f12095i = z2;
    }

    public static void i(boolean z2) {
        f12092f = z2;
    }

    public static AuraDebugTimeListener j() {
        return t;
    }

    public static void k(boolean z2) {
        f12099m = z2;
    }

    public static void m(boolean z2) {
        f12097k = z2;
    }

    public static void n(String str) {
        o = str;
    }

    public static boolean b() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.bundleLocationNullCheck()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("bundleLocationNullCheck error");
        }
        a.a("bundleLocationNullCheck:" + z2);
        return z2;
    }

    public static void c(int i2) {
        f12100n = i2;
    }

    public static List<String> e(String str) {
        return com.jingdong.aura.a.a.a.c().d(str);
    }

    public static int f() {
        int i2 = 0;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                i2 = auraMonitorConfigListener.dynamicBundleInfoListAbTest();
            }
        } catch (Throwable unused) {
            a.d("dynamicBundleInfoListAbTest error");
        }
        a.a("abSwitch:" + i2);
        return i2;
    }

    public static ArrayList<String> g(String str) {
        if (!TextUtils.isEmpty(str) && com.jingdong.aura.a.b.k.b.b(str) == null) {
            List<String> d2 = com.jingdong.aura.a.a.a.c().d(str);
            ArrayList<String> arrayList = new ArrayList();
            ArrayList<String> arrayList2 = new ArrayList<>();
            if (com.jingdong.aura.a.a.a.c().i(str)) {
                arrayList.add(str);
            }
            if (d2 != null) {
                for (String str2 : d2) {
                    if (com.jingdong.aura.a.a.a.c().i(str2)) {
                        arrayList.add(str2);
                    }
                }
            }
            for (String str3 : arrayList) {
                if (!j(str3)) {
                    arrayList2.add(str3);
                }
            }
            return arrayList2;
        }
        return null;
    }

    public static long h(String str) {
        return com.jingdong.aura.a.a.a.c().h(str);
    }

    public static boolean i(String str) {
        return com.jingdong.aura.a.b.k.b.b(str) != null;
    }

    public static boolean j(String str) {
        if (com.jingdong.aura.a.b.k.b.b(str) != null) {
            return true;
        }
        long c2 = com.jingdong.aura.a.b.l.h.c(new File(i(), str));
        return c2 > 0 && c2 >= com.jingdong.aura.a.a.a.c().h(str);
    }

    public static void l(boolean z2) {
        f12098l = z2;
    }

    public static void m(String str) {
        int parseInt;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String trim = str.trim();
            if (!TextUtils.isEmpty(trim) && (parseInt = Integer.parseInt(trim, 16)) > 0) {
                if ((parseInt & 1) > 0) {
                    q = 24;
                } else {
                    q = 26;
                }
                if ((parseInt & 2) > 0) {
                    p = true;
                } else {
                    p = false;
                }
                com.jingdong.aura.core.util.l.b bVar = a;
                bVar.b("dex2oatQuickenSdkVersion:" + q);
                bVar.b("updateConfigurations:" + p);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int n() {
        return q;
    }

    public static long c(String str) {
        return com.jingdong.aura.a.a.a.c().c(str);
    }

    public static String l() {
        return o;
    }

    public static boolean c() {
        boolean z2 = false;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
                if (auraMonitorConfigListener.bundleSoInfoCheck()) {
                    z2 = true;
                }
            }
        } catch (Throwable unused) {
            a.d("bundleSoInfoCheck error");
        }
        a.a("bundleSoInfoCheck:" + z2);
        return z2;
    }

    public static boolean a(String str) {
        if (com.jingdong.aura.a.b.k.b.b(str) == null && com.jingdong.aura.a.b.l.h.c(new File(i(), str)) <= 0) {
            return a(1);
        }
        return true;
    }

    public static void d(boolean z2) {
        r = z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0009, code lost:
        if (r1.classNotFoundRunningTaskCheck() != false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean d() {
        boolean z2 = true;
        try {
            AuraMonitorConfigListener auraMonitorConfigListener = y;
            if (auraMonitorConfigListener != null) {
            }
            z2 = false;
        } catch (Throwable unused) {
            a.d("classNotFoundRunningTaskCheck error");
        }
        a.a("classNotFoundRunningTaskCheck:" + z2);
        return z2;
    }

    public static boolean a(int i2) {
        while (!a(i2, false)) {
            if (!com.jingdong.aura.a.b.l.h.a(i())) {
                a.c("no disk space left on phone!");
                new Handler(Looper.getMainLooper()).post(new b());
                return false;
            }
        }
        return true;
    }

    public static boolean a(int i2, boolean z2) {
        long j2 = i2 * 2;
        long a2 = com.jingdong.aura.core.util.i.a(true);
        if (a2 < j2) {
            a.e("check disk size: currentFreeSize = " + a2 + "M, Need min size = " + j2 + "M");
            if (Build.VERSION.SDK_INT < 18 || com.jingdong.aura.core.util.i.a(false) < j2) {
                if (z2) {
                    new Handler(Looper.getMainLooper()).post(new RunnableC0393c());
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public static void a(boolean z2) {
        if (z2) {
            com.jingdong.aura.core.util.l.c.a = 2;
        } else {
            com.jingdong.aura.core.util.l.c.a = 4;
        }
    }

    public static void a(AuraPageCallback auraPageCallback) {
        u = auraPageCallback;
    }

    public static void a(Set<String> set, Set<String> set2) {
        com.jingdong.aura.a.b.b.a(set, set2);
    }

    public static Activity a(IBinder iBinder) {
        try {
            Hack.e eVar = com.jingdong.aura.core.reflection.b.f12151e;
            if (eVar == null) {
                return null;
            }
            return (Activity) eVar.a(com.jingdong.aura.core.reflection.a.c(), iBinder);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(List<AuraDowngradeBundle> list) {
        d.a(list);
    }

    public static void a(AuraDebugTimeListener auraDebugTimeListener) {
        t = auraDebugTimeListener;
    }

    public static void a(Activity activity) {
        DelegateResourcesUtils.ensureResourcesInjected(activity);
    }

    public static void a(AuraPrivacyInfoListener auraPrivacyInfoListener) {
        x = auraPrivacyInfoListener;
    }

    public static void a(AuraMonitorConfigListener auraMonitorConfigListener) {
        y = auraMonitorConfigListener;
    }

    public static void a(IMobileLogCallback iMobileLogCallback) {
        z = iMobileLogCallback;
    }

    public static void a(String str, File file) {
        if (file != null && file.exists()) {
            l.b.a.d b2 = com.jingdong.aura.a.b.k.b.b(str);
            if (b2 != null) {
                File e2 = ((h) b2).d().e().e();
                if (e2.exists()) {
                    File file2 = new File(e2, "lib");
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    String name = file.getName();
                    File file3 = new File(file2, name);
                    File file4 = new File(file2, name + ".md5");
                    if (file3.exists() && file4.exists()) {
                        com.jingdong.aura.core.util.l.b bVar = a;
                        bVar.b("file exist!");
                        if (com.jingdong.aura.core.util.d.a(file3.getAbsolutePath(), file.getAbsolutePath())) {
                            bVar.b("file exist, and md5 is same\uff0cnot install again!");
                            return;
                        }
                        bVar.b("file exist, but md5 not same, so install!");
                    }
                    file3.delete();
                    file4.delete();
                    com.jingdong.aura.core.util.d.a(new FileInputStream(file), file3);
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file4));
                    dataOutputStream.writeUTF(com.jingdong.aura.core.util.d.b(file.getAbsolutePath()));
                    dataOutputStream.close();
                    return;
                }
                a.b(str + ":revisionDir not exist!");
                throw new RuntimeException("bundle dir not exist!");
            }
            a.b(str + ":bundle not install!");
            throw new RuntimeException(str + ": not installed");
        }
        a.b(file + ": not exist!");
        throw new RuntimeException(file + ": not exist!");
    }

    public static void a(ImHCallBack imHCallBack) {
        com.jingdong.aura.wrapper.mhCallback.a.a().a(imHCallBack);
    }
}
