package com.jdjr.risk.device.c;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class af implements Runnable {
    private boolean a;
    private InputStream b;

    /* renamed from: c  reason: collision with root package name */
    private StringBuilder f7346c;
    private volatile boolean d;

    public af(InputStream inputStream) {
        this.a = false;
        this.d = false;
        this.b = inputStream;
        this.f7346c = new StringBuilder();
        this.d = false;
    }

    public af(InputStream inputStream, boolean z) {
        this.a = false;
        this.d = false;
        this.b = inputStream;
        this.f7346c = new StringBuilder();
        this.d = false;
        this.a = z;
    }

    private void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }

    public String a() {
        if (!this.d) {
            synchronized (this) {
                try {
                    if (!this.d) {
                        wait();
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return this.f7346c.toString();
    }

    @Override // java.lang.Runnable
    public void run() {
        BufferedReader bufferedReader;
        Throwable th;
        Closeable closeable = null;
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
                if (readLine == null) {
                    a(bufferedReader);
                    a(this.b);
                    this.d = true;
                    synchronized (this) {
                        notify();
                    }
                    return;
                }
                this.f7346c.append(readLine + property);
            }
        } catch (IOException unused2) {
            closeable = bufferedReader;
            a(closeable);
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
}
