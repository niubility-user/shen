package com.jdjr.risk.device.c;

import android.content.Context;
import android.os.Debug;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;

/* loaded from: classes18.dex */
public class m {
    private static boolean a() {
        return Debug.isDebuggerConnected();
    }

    public static boolean a(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean b() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/status"));
            String str = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.contains("TracerPid")) {
                    str = readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                    break;
                }
            }
            bufferedReader.close();
            return !TextUtils.equals("0", str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        if (context != null) {
            boolean c2 = c(context);
            if (c2) {
                return c2;
            }
            boolean a = a();
            return !a ? b() : a;
        }
        return false;
    }

    private static boolean c(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }
}
