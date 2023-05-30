package jpbury;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jpbury.e;
import jpbury.v;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes11.dex */
public class c {
    private static final String b = "version";

    /* renamed from: c */
    private static final String f20075c = "1.0.0";
    public static final String d = "1";

    /* renamed from: e */
    private static final String f20076e = "bury_config";

    /* renamed from: f */
    public static final String f20077f = "needLog";

    /* renamed from: g */
    public static final String f20078g = "logLevel";

    /* renamed from: h */
    public static final MediaType f20079h = MediaType.parse("application/json; charset=utf-8");

    /* renamed from: i */
    public static final String f20080i = "ERROR_COUNT";

    /* renamed from: j */
    public static final String f20081j = "ERROR_TIME";
    private volatile d a;

    /* loaded from: classes11.dex */
    public class a implements v.c {
        public a() {
            c.this = r1;
        }

        @Override // jpbury.v.c
        public void a() {
        }

        @Override // jpbury.v.c
        public void a(@Nullable e.a aVar) {
            if (aVar != null) {
                c.this.a = new d(aVar);
                c cVar = c.this;
                cVar.a(cVar.a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class b {
        private final int a;
        private final long b;

        public b(int i2, long j2) {
            this.a = i2;
            this.b = j2;
        }

        public int a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    /* renamed from: jpbury.c$c */
    /* loaded from: classes11.dex */
    public static final class C0854c {
        public static final c a = new c(null);

        private C0854c() {
        }
    }

    private c() {
        this.a = d.a();
    }

    public /* synthetic */ c(a aVar) {
        this();
    }

    @Nullable
    private Request a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        return new Request.Builder().url(str).header("version", "1.0.0").post(RequestBody.create(f20079h, str2)).build();
    }

    public void a(d dVar) {
        SharedPreferences c2 = c();
        if (c2 == null) {
            return;
        }
        c2.edit().putBoolean(f20077f, dVar.c()).putInt("logLevel", dVar.b()).apply();
    }

    public static c b() {
        return C0854c.a;
    }

    private SharedPreferences c() {
        Context b2 = a0.b();
        if (b2 == null) {
            return null;
        }
        return b2.getSharedPreferences(f20076e, 0);
    }

    private void d() {
        SharedPreferences c2 = c();
        if (c2 == null) {
            return;
        }
        this.a = new d(c2.getBoolean(f20077f, true), c2.getInt("logLevel", 0));
    }

    public b a() {
        SharedPreferences c2 = c();
        if (c2 == null) {
            return null;
        }
        return new b(c2.getInt(f20080i, 0), c2.getLong(f20081j, 0L));
    }

    public void a(int i2, long j2) {
        SharedPreferences c2 = c();
        if (c2 == null) {
            return;
        }
        c2.edit().putInt(f20080i, i2).putLong(f20081j, j2).apply();
    }

    public void a(String str, m mVar) {
        Request a2;
        d();
        n a3 = n.a(mVar);
        if (a3 == null || (a2 = a(str, a3.a())) == null) {
            return;
        }
        v.a().a(a2, new a());
    }

    public void a(@NonNull s sVar, @NonNull v.c cVar) {
        if (!sVar.i()) {
            if (!this.a.c()) {
                cVar.a(null);
                return;
            } else if (sVar.c() < this.a.b()) {
                cVar.a(null);
                return;
            }
        }
        o a2 = o.a(sVar);
        if (a2 == null) {
            cVar.a(null);
            return;
        }
        Request a3 = a(sVar.f(), a2.a());
        if (a3 == null) {
            cVar.a(null);
        } else {
            v.a().a(a3, cVar);
        }
    }
}
