package com.jd.phc.i.d;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public enum d {
    UNKNOWN_ERROR("N001", "unknown"),
    D_ENCRYPT_ERROR("C001", "rsa encrypt error"),
    D_DECRYPT_ERROR("C002", "dsecret decrypt error"),
    TIMEOUT_ERROR("C003", "timeout"),
    NO_CONNECT_ERROR("C004", "no connect"),
    EID_ERROR("C005", "eid is null"),
    STATE_ERROR("R001", "response error"),
    SERVER_ERROR("S001", "server error");
    
    private String mDesc;
    private String mErrorCode;

    d(String str, String str2) {
        setErrorCode(str);
        setDesc(str2);
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public d setDesc(String str) {
        this.mDesc = str;
        return this;
    }

    public d setErrorCode(String str) {
        this.mErrorCode = str;
        return this;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "[" + this.mErrorCode + "]" + this.mDesc;
    }

    public d setDesc(Throwable th) {
        PrintWriter printWriter;
        StringWriter stringWriter;
        StringWriter stringWriter2 = null;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
            } catch (Exception unused) {
                printWriter = null;
            } catch (Throwable th2) {
                th = th2;
                printWriter = null;
            }
        } catch (Exception unused2) {
            printWriter = null;
        } catch (Throwable th3) {
            th = th3;
            printWriter = null;
        }
        try {
            th.printStackTrace(printWriter);
            this.mDesc = stringWriter.toString();
            com.jd.phc.i.a.a(stringWriter);
        } catch (Exception unused3) {
            stringWriter2 = stringWriter;
            com.jd.phc.i.a.a(stringWriter2);
            com.jd.phc.i.a.a(printWriter);
            return this;
        } catch (Throwable th4) {
            th = th4;
            stringWriter2 = stringWriter;
            com.jd.phc.i.a.a(stringWriter2);
            com.jd.phc.i.a.a(printWriter);
            throw th;
        }
        com.jd.phc.i.a.a(printWriter);
        return this;
    }
}
