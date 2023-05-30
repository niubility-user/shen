package jpbury;

import android.os.Build;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class z {
    public static final int a = 0;
    public static final int b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f20175c = 2;

    /* renamed from: g  reason: collision with root package name */
    private static final String[] f20178g = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/cache/", "/data/", "/dev/"};
    private static final String d = "su";

    /* renamed from: e  reason: collision with root package name */
    private static final String f20176e = "busybox";

    /* renamed from: f  reason: collision with root package name */
    private static final String f20177f = "magisk";

    /* renamed from: h  reason: collision with root package name */
    private static final String[] f20179h = {d, f20176e, f20177f};

    private static ArrayList<String> a() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(f20178g));
        String str = System.getenv("PATH");
        if (str != null && !"".equals(str)) {
            String[] split = str.split(":");
            int length = split.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = split[i2];
                if (!str2.endsWith("/")) {
                    str2 = str2 + '/';
                }
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        return arrayList;
    }

    public static int b() {
        if (d() || c()) {
            return 2;
        }
        Iterator<String> it = a().iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                File file = new File(it.next());
                if (file.exists() && file.isDirectory()) {
                    for (String str : f20179h) {
                        try {
                        } catch (Throwable th) {
                            th.printStackTrace();
                            z = true;
                        }
                        if (new File(file, str).exists()) {
                            return 2;
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z ? 0 : 1;
    }

    private static boolean c() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return "1".equals((String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, "ro.build.selinux"));
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static boolean d() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }
}
