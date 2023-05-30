package com.jdcn.fido.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class StreamGobbler extends Thread {
    private StringBuilder buf;
    private boolean enter;
    private InputStream inputStream;
    private volatile boolean isStopped;

    public StreamGobbler(InputStream inputStream) {
        this.enter = false;
        this.isStopped = false;
        this.inputStream = inputStream;
        this.buf = new StringBuilder();
        this.isStopped = false;
    }

    public StreamGobbler(InputStream inputStream, boolean z) {
        this.enter = false;
        this.isStopped = false;
        this.inputStream = inputStream;
        this.buf = new StringBuilder();
        this.isStopped = false;
        this.enter = z;
    }

    private void streamClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }

    public String getContent() {
        if (!this.isStopped) {
            synchronized (this) {
                try {
                    if (!this.isStopped) {
                        wait();
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return this.buf.toString();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        BufferedReader bufferedReader;
        Throwable th;
        Closeable closeable = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        } catch (IOException unused) {
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String property = this.enter ? System.getProperty("line.separator") : "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    streamClose(bufferedReader);
                    streamClose(this.inputStream);
                    this.isStopped = true;
                    synchronized (this) {
                        notify();
                    }
                    return;
                }
                this.buf.append(readLine + property);
            }
        } catch (IOException unused2) {
            closeable = bufferedReader;
            streamClose(closeable);
            streamClose(this.inputStream);
            this.isStopped = true;
            synchronized (this) {
                notify();
            }
        } catch (Throwable th3) {
            th = th3;
            streamClose(bufferedReader);
            streamClose(this.inputStream);
            this.isStopped = true;
            synchronized (this) {
                notify();
            }
            throw th;
        }
    }
}
