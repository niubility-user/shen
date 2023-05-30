package jpbury;

import jpbury.e;

/* loaded from: classes11.dex */
public class d {
    private final boolean a;
    private final int b;

    public d(e.a aVar) {
        this(aVar.b(), aVar.a());
    }

    public d(boolean z, int i2) {
        this.a = z;
        this.b = i2;
    }

    public static d a() {
        return new d(true, 0);
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.a;
    }
}
