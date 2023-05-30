package jd.wjweblogin.d;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes11.dex */
public class e {
    private static final String a = "com.stat.wjweblogin.logAnalytics";
    private static final String b = "trace";

    /* renamed from: c */
    private static final String f20062c = "loglog.dt";
    private static h d;

    /* renamed from: e */
    private static boolean f20063e;

    public static void a(Context context, boolean z) {
        f20063e = z;
        if (z && d == null) {
            h hVar = new h(a, b, f20062c);
            d = hVar;
            hVar.a(context);
        }
    }

    public static void a(String str) {
        h hVar;
        try {
            if (f20063e && (hVar = d) != null) {
                hVar.a(str);
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
                        fileWriter2.write(l.a(System.currentTimeMillis()) + " Pid:" + Process.myPid() + ":::: " + str);
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
