package jpbury;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class f0 {
    private static String a = "";
    private static long b;

    private f0() {
    }

    public static String a(Context context) {
        if (b == 0) {
            try {
                b = Build.VERSION.SDK_INT >= 28 ? context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode() : context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return String.valueOf(b);
    }

    public static String b(Context context) {
        return context.getPackageName();
    }

    public static String c(Context context) {
        if (TextUtils.isEmpty(a)) {
            try {
                a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return a;
    }
}
