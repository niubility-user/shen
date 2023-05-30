package g.e.a;

import java.util.Arrays;

/* loaded from: classes12.dex */
public final class b {
    public static final b b = new b(0);

    /* renamed from: c  reason: collision with root package name */
    public static final b f19448c = new b(1);
    public static final b d = new b(2);

    /* renamed from: e  reason: collision with root package name */
    public static final b f19449e = new b(3);

    /* renamed from: f  reason: collision with root package name */
    public static final b f19450f = new b(4);
    private final int a;

    private b(int i2) {
        this.a = i2;
    }

    private int b(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public String a() {
        int i2 = this.a;
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "UNKNOWN" : "SG" : "RU" : "DE" : "CN";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && b.class == obj.getClass() && this.a == ((b) obj).a;
    }

    public int hashCode() {
        return b(Integer.valueOf(this.a));
    }
}
