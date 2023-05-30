package jpbury;

import com.google.gson.annotations.JsonAdapter;
import java.util.HashMap;

@JsonAdapter(q.class)
/* loaded from: classes11.dex */
public class p {
    public static final String d = "logLevel";
    private final j a;
    private final HashMap<String, String> b;

    /* renamed from: c  reason: collision with root package name */
    private final int f20139c;

    public p(j jVar, HashMap<String, String> hashMap, int i2) {
        this.a = jVar;
        this.b = hashMap;
        this.f20139c = i2;
    }

    public j a() {
        return this.a;
    }

    public int b() {
        return this.f20139c;
    }

    public HashMap<String, String> c() {
        return this.b;
    }
}
