package k.a.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public class a extends IOException {
    private static final long serialVersionUID = 1;
    private EnumC0855a type;

    /* renamed from: k.a.a.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public enum EnumC0855a {
        WRONG_PASSWORD,
        TASK_CANCELLED_EXCEPTION,
        CHECKSUM_MISMATCH,
        UNKNOWN_COMPRESSION_METHOD,
        FILE_NOT_FOUND,
        UNSUPPORTED_ENCRYPTION,
        UNKNOWN
    }

    public a(String str) {
        super(str);
        this.type = EnumC0855a.UNKNOWN;
    }

    public EnumC0855a getType() {
        return this.type;
    }

    public a(Exception exc) {
        super(exc);
        this.type = EnumC0855a.UNKNOWN;
    }

    public a(String str, Exception exc) {
        super(str, exc);
        this.type = EnumC0855a.UNKNOWN;
    }

    public a(String str, EnumC0855a enumC0855a) {
        super(str);
        this.type = EnumC0855a.UNKNOWN;
        this.type = enumC0855a;
    }

    public a(String str, Throwable th, EnumC0855a enumC0855a) {
        super(str, th);
        this.type = EnumC0855a.UNKNOWN;
        this.type = enumC0855a;
    }
}
