package com.xiaomi.push;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes11.dex */
public class a6 extends Exception {
    private j6 a;

    /* renamed from: a  reason: collision with other field name */
    private k6 f106a;

    /* renamed from: a  reason: collision with other field name */
    private Throwable f107a;

    public a6() {
        this.a = null;
        this.f106a = null;
        this.f107a = null;
    }

    public a6(j6 j6Var) {
        this.a = null;
        this.f106a = null;
        this.f107a = null;
        this.a = j6Var;
    }

    public a6(String str) {
        super(str);
        this.a = null;
        this.f106a = null;
        this.f107a = null;
    }

    public a6(String str, Throwable th) {
        super(str);
        this.a = null;
        this.f106a = null;
        this.f107a = null;
        this.f107a = th;
    }

    public a6(Throwable th) {
        this.a = null;
        this.f106a = null;
        this.f107a = null;
        this.f107a = th;
    }

    public Throwable a() {
        return this.f107a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        j6 j6Var;
        k6 k6Var;
        String message = super.getMessage();
        return (message != null || (k6Var = this.f106a) == null) ? (message != null || (j6Var = this.a) == null) ? message : j6Var.toString() : k6Var.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f107a != null) {
            printStream.println("Nested Exception: ");
            this.f107a.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f107a != null) {
            printWriter.println("Nested Exception: ");
            this.f107a.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        k6 k6Var = this.f106a;
        if (k6Var != null) {
            sb.append(k6Var);
        }
        j6 j6Var = this.a;
        if (j6Var != null) {
            sb.append(j6Var);
        }
        if (this.f107a != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.f107a);
        }
        return sb.toString();
    }
}
