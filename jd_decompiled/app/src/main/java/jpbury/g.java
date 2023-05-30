package jpbury;

import com.jdpay.bury.IdExtension;

/* loaded from: classes11.dex */
public class g {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final IdExtension f20116c;
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private final long f20117e;

    /* renamed from: f  reason: collision with root package name */
    private final String f20118f;

    /* renamed from: g  reason: collision with root package name */
    private final int f20119g;

    /* renamed from: h  reason: collision with root package name */
    private final f f20120h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f20121i;

    public g(String str, String str2, IdExtension idExtension, String str3, long j2, String str4, int i2, f fVar, boolean z) {
        this.a = str;
        this.b = str2;
        this.f20116c = idExtension;
        this.d = str3;
        this.f20117e = j2;
        this.f20118f = str4;
        this.f20119g = i2;
        this.f20120h = fVar;
        this.f20121i = z;
    }

    public static g a(f fVar, int i2, long j2) {
        return new g("event", t.f20140e, null, String.valueOf(i2), j2, null, 3, fVar, false);
    }

    public String a() {
        return this.f20118f;
    }

    public int b() {
        return this.f20119g;
    }

    public String c() {
        return this.b;
    }

    public IdExtension d() {
        return this.f20116c;
    }

    public f e() {
        return this.f20120h;
    }

    public long f() {
        return this.f20117e;
    }

    public String g() {
        return this.a;
    }

    public String h() {
        return this.d;
    }

    public boolean i() {
        return this.f20121i;
    }
}
