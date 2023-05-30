package l.b.a;

/* loaded from: classes.dex */
public class b extends Exception {
    public static final int ACTIVATOR_ERROR = 5;
    public static final int DUPLICATE_BUNDLE_ERROR = 9;
    public static final int INVALID_OPERATION = 2;
    public static final int MANIFEST_ERROR = 3;
    public static final int NATIVECODE_ERROR = 8;
    public static final int RESOLVE_ERROR = 4;
    public static final int SECURITY_ERROR = 6;
    public static final int START_TRANSIENT_ERROR = 10;
    public static final int STATECHANGE_ERROR = 7;
    public static final int UNSPECIFIED = 0;
    public static final int UNSUPPORTED_OPERATION = 1;
    static final long serialVersionUID = 3571095144220455665L;
    private final int type;

    public b(String str, Throwable th) {
        this(str, 0, th);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return super.getCause();
    }

    public Throwable getNestedException() {
        return getCause();
    }

    public int getType() {
        return this.type;
    }

    @Override // java.lang.Throwable
    public Throwable initCause(Throwable th) {
        return super.initCause(th);
    }

    public b(String str) {
        this(str, 0);
    }

    public b(String str, int i2, Throwable th) {
        super(str, th);
        this.type = i2;
    }

    public b(String str, int i2) {
        super(str);
        this.type = i2;
    }
}
