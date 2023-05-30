package l.b.a;

import java.util.EventObject;

/* loaded from: classes11.dex */
public class a extends EventObject {
    public static final int INSTALLED = 1;
    public static final int LAZY_ACTIVATION = 512;
    public static final int LOADED = 0;
    public static final int RESOLVED = 32;
    public static final int STARTED = 2;
    public static final int STARTING = 128;
    public static final int STOPPED = 4;
    public static final int STOPPING = 256;
    public static final int UNINSTALLED = 16;
    public static final int UNRESOLVED = 64;
    public static final int UPDATED = 8;
    static final long serialVersionUID = 4080640865971756012L;
    private final d bundle;
    private final int type;

    public a(int i2, d dVar) {
        super(dVar);
        this.bundle = dVar;
        this.type = i2;
    }

    public d getBundle() {
        return this.bundle;
    }

    public int getType() {
        return this.type;
    }
}
