package l.b.a;

import java.util.EventObject;

/* loaded from: classes11.dex */
public class c extends EventObject {
    public static final int ERROR = 2;
    public static final int INFO = 32;
    public static final int PACKAGES_REFRESHED = 4;
    public static final int STARTED = 1;
    public static final int STARTING = 0;
    public static final int STARTLEVEL_CHANGED = 8;
    public static final int STOPPED = 64;
    public static final int STOPPED_BOOTCLASSPATH_MODIFIED = 256;
    public static final int STOPPED_UPDATE = 128;
    public static final int WAIT_TIMEDOUT = 512;
    public static final int WARNING = 16;
    static final long serialVersionUID = 207051004521261705L;
    private final d bundle;
    private final Throwable throwable;
    private final int type;

    @Deprecated
    public c(int i2, Object obj) {
        super(obj);
        this.type = i2;
        this.bundle = null;
        this.throwable = null;
    }

    public d getBundle() {
        return this.bundle;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public int getType() {
        return this.type;
    }

    public c(int i2, d dVar, Throwable th) {
        super(dVar);
        this.type = i2;
        this.bundle = dVar;
        this.throwable = th;
    }
}
