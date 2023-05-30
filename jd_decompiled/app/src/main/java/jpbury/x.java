package jpbury;

import com.google.gson.Gson;

/* loaded from: classes11.dex */
public class x {
    private final Gson a;

    /* loaded from: classes11.dex */
    public static final class b {
        public static final x a = new x();

        private b() {
        }
    }

    private x() {
        this.a = new Gson();
    }

    public static Gson a() {
        return b.a.a;
    }
}
