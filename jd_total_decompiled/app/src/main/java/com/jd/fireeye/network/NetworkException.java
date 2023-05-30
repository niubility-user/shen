package com.jd.fireeye.network;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes13.dex */
public class NetworkException extends IOException {
    private int a;

    public NetworkException(int i2) {
        this.a = i2;
    }

    public int a() {
        return this.a;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStream.println("ErrorCode = " + a());
        super.printStackTrace(printStream);
    }

    public NetworkException(Throwable th, int i2) {
        super(th);
        this.a = i2;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        printWriter.println("ErrorCode = " + a());
        super.printStackTrace(printWriter);
    }
}
