package k.a.a.g;

/* loaded from: classes11.dex */
public class a {
    private b a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f20269c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private Exception f20270e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f20271f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f20272g;

    /* renamed from: k.a.a.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public enum EnumC0856a {
        SUCCESS,
        WORK_IN_PROGRESS,
        ERROR,
        CANCELLED
    }

    /* loaded from: classes11.dex */
    public enum b {
        READY,
        BUSY
    }

    /* loaded from: classes11.dex */
    public enum c {
        NONE,
        ADD_ENTRY,
        REMOVE_ENTRY,
        CALCULATE_CRC,
        EXTRACT_ENTRY,
        MERGE_ZIP_FILES,
        SET_COMMENT,
        RENAME_FILE
    }

    public a() {
        f();
    }

    private void f() {
        c cVar = c.NONE;
        this.a = b.READY;
    }

    public void a() {
        EnumC0856a enumC0856a = EnumC0856a.SUCCESS;
        this.d = 100;
        f();
    }

    public void b(Exception exc) {
        EnumC0856a enumC0856a = EnumC0856a.ERROR;
        this.f20270e = exc;
        f();
    }

    public void c() {
        f();
        this.b = 0L;
        this.f20269c = 0L;
        this.d = 0;
    }

    public b d() {
        return this.a;
    }

    public boolean e() {
        return this.f20271f;
    }

    public void g(c cVar) {
    }

    public void h(String str) {
    }

    public void i(EnumC0856a enumC0856a) {
    }

    public void j(b bVar) {
        this.a = bVar;
    }

    public void k(long j2) {
        this.b = j2;
    }

    public void l(long j2) {
        long j3 = this.f20269c + j2;
        this.f20269c = j3;
        long j4 = this.b;
        if (j4 > 0) {
            int i2 = (int) ((j3 * 100) / j4);
            this.d = i2;
            if (i2 > 100) {
                this.d = 100;
            }
        }
        while (this.f20272g) {
            try {
                Thread.sleep(150L);
            } catch (InterruptedException unused) {
            }
        }
    }
}
