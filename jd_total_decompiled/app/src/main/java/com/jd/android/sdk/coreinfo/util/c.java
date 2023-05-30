package com.jd.android.sdk.coreinfo.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes12.dex */
public final class c extends Thread {
    private boolean a;
    private InputStream b;

    /* renamed from: c  reason: collision with root package name */
    private StringBuilder f1692c = new StringBuilder();
    private volatile boolean d;

    public c(InputStream inputStream, boolean z) {
        this.a = false;
        this.d = false;
        this.b = inputStream;
        this.d = false;
        this.a = z;
    }

    public final String a() {
        if (!this.d) {
            synchronized (this) {
                try {
                    if (!this.d) {
                        wait();
                    }
                }
            }
        }
        return this.f1692c.toString();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.b));
        } catch (IOException unused) {
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String property = this.a ? System.getProperty("line.separator") : "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    this.f1692c.append(readLine + property);
                } else {
                    a(bufferedReader);
                    a(this.b);
                    this.d = true;
                    synchronized (this) {
                        notify();
                    }
                    return;
                }
            }
        } catch (IOException unused2) {
            bufferedReader2 = bufferedReader;
            a(bufferedReader2);
            a(this.b);
            this.d = true;
            synchronized (this) {
                notify();
            }
        } catch (Throwable th3) {
            th = th3;
            a(bufferedReader);
            a(this.b);
            this.d = true;
            synchronized (this) {
                notify();
            }
            throw th;
        }
    }

    private static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }
}
