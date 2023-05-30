package g.e.a.h;

import android.content.Context;
import g.e.a.e;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes12.dex */
public abstract class a implements e {
    private static final Map<String, a> a = new HashMap();
    private static final Object b = new Object();

    public static a b(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        return c(context, context.getPackageName());
    }

    public static a c(Context context, String str) {
        a aVar;
        synchronized (b) {
            Map<String, a> map = a;
            aVar = map.get(str);
            if (aVar == null) {
                aVar = new g.e.a.h.c.e(context, str);
                map.put(str, aVar);
            }
        }
        return aVar;
    }
}
