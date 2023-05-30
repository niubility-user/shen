package jd.wjlogin_sdk.util;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes.dex */
public class k {
    private static final String a = "com.stat.login.logAnalytics";
    private static final String b = "trace";

    /* renamed from: c */
    private static final String f19977c = "loglog.dt";
    private static q d;

    /* renamed from: e */
    private static boolean f19978e;

    public static void a(Context context, boolean z) {
        f19978e = z;
        if (z && d == null) {
            q qVar = new q(a, b, f19977c);
            d = qVar;
            qVar.a(context);
        }
    }

    public static void a(String str) {
        q qVar;
        try {
            if (f19978e && (qVar = d) != null) {
                qVar.a(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(String str, File file, boolean z) {
        FileWriter fileWriter = null;
        try {
            try {
                try {
                    FileWriter fileWriter2 = new FileWriter(file, z);
                    try {
                        fileWriter2.write(i.a(System.currentTimeMillis()) + " Pid:" + Process.myPid() + ":::: " + str);
                        fileWriter2.write("\r\n");
                        fileWriter2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileWriter = fileWriter2;
                        e.printStackTrace();
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileWriter = fileWriter2;
                        if (fileWriter != null) {
                            try {
                                fileWriter.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }
}
