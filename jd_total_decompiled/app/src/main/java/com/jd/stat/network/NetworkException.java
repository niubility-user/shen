package com.jd.stat.network;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes18.dex */
public class NetworkException extends IOException {
    public static final int ERROR_NULL = -100;
    private int errorCode;

    public NetworkException(int i2) {
        this.errorCode = i2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStream.println("ErrorCode = " + getErrorCode());
        super.printStackTrace(printStream);
    }

    public void setErrorCode(int i2) {
        this.errorCode = i2;
    }

    public NetworkException(String str, int i2) {
        super(str);
        this.errorCode = i2;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        printWriter.println("ErrorCode = " + getErrorCode());
        super.printStackTrace(printWriter);
    }

    public NetworkException(String str, Throwable th, int i2) {
        super(str, th);
        this.errorCode = i2;
    }

    public NetworkException(Throwable th, int i2) {
        super(th);
        this.errorCode = i2;
    }
}
