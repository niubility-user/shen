package com.tencent.smtt.utils;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/* loaded from: classes9.dex */
public class p {

    /* renamed from: e */
    private static p f17983e;
    public boolean a;
    private Context b;

    /* renamed from: c */
    private File f17984c;
    private boolean d;

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            pVar = f17983e;
        }
        return pVar;
    }

    private File c() {
        try {
            if (this.f17984c == null) {
                File file = new File(QbSdk.getTbsFolderDir(this.b), "core_private");
                this.f17984c = file;
                if (file == null || !file.isDirectory()) {
                    return null;
                }
            }
            File file2 = new File(this.f17984c, "debug.conf");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            return file2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(boolean z) {
        this.d = z;
        b();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x0064 -> B:91:0x0067). Please submit an issue!!! */
    public void b() {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Properties properties;
        File c2 = c();
        if (c2 == null) {
            return;
        }
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(c2));
                try {
                    properties = new Properties();
                    properties.load(bufferedInputStream);
                    properties.setProperty("setting_forceUseSystemWebview", Boolean.toString(this.a));
                    properties.setProperty("result_systemWebviewForceUsed", Boolean.toString(this.d));
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(c2));
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = null;
                }
                try {
                    properties.store(bufferedOutputStream, (String) null);
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    bufferedOutputStream.close();
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        th.printStackTrace();
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        bufferedOutputStream.close();
                    } catch (Throwable th4) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                bufferedInputStream = null;
                th = th5;
                bufferedOutputStream = null;
            }
        } catch (Exception e6) {
            while (true) {
                e6.printStackTrace();
                return;
            }
        }
    }
}
