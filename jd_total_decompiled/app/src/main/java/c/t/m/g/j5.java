package c.t.m.g;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class j5 {
    public static boolean a;
    public static Context b;

    /* renamed from: c  reason: collision with root package name */
    public static t5 f502c;

    public static String a(Context context) {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : BaseInfo.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static void b(Context context, File file) {
        b = context;
        if (a) {
            b5.b(context, file);
        }
    }

    public static void c(t5 t5Var) {
        f502c = t5Var;
    }

    public static void d(String str, int i2, String str2) {
        if (a) {
            String a2 = a(b);
            String name = Thread.currentThread().getName();
            StringBuilder sb = new StringBuilder("p:");
            sb.append(a2);
            sb.append(",t:");
            sb.append(name);
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(str2);
            String str3 = System.currentTimeMillis() + ",p:" + a2 + ",t:" + name + DYConstants.DY_REGEX_COMMA + str2;
            if (f502c != null) {
                new SimpleDateFormat("HH:mm:ss").format(new Date());
                f502c.a(str, str2);
            }
            b5 a3 = b5.a();
            if (a3 != null) {
                a3.d(str, i2, str3);
            }
        }
    }

    public static void e(String str, String str2) {
        d(str, 4, str2);
    }
}
