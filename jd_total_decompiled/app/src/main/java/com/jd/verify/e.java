package com.jd.verify;

import android.content.Context;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes18.dex */
public class e {
    private static f a;
    private static boolean b;

    public static void a(Context context, boolean z) {
        b = z;
        if (z && a == null) {
            f fVar = new f("com.stat.logAnalytics", "trace", "log.dt");
            a = fVar;
            fVar.a(context);
        }
    }

    public static void a(String str) {
        f fVar;
        try {
            if (b && (fVar = a) != null) {
                fVar.a(str);
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
                        fileWriter2.write(c.a(System.currentTimeMillis()) + ":::: " + str);
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
