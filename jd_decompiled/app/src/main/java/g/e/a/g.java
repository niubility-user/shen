package g.e.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public final class g {
    private static final Map<String, a> a = new HashMap();

    /* loaded from: classes12.dex */
    public interface a {
        String a(e eVar);
    }

    public static Map<String, a> a() {
        return a;
    }

    public static void b(String str, a aVar) {
        a.put(str, aVar);
    }
}
