package g.e.a.h.c;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class c extends g.e.a.c {
    private static final Map<String, g.e.a.c> a = new HashMap();
    private static final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static String f19453c;

    private c(Context context, String str) {
        g.e.a.h.a.c(context, str);
    }

    public static g.e.a.c a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        String packageName = context.getPackageName();
        f19453c = packageName;
        return b(context, packageName);
    }

    public static g.e.a.c b(Context context, String str) {
        g.e.a.c cVar;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName can not be empty");
        }
        synchronized (b) {
            Map<String, g.e.a.c> map = a;
            cVar = map.get(str);
            if (cVar == null) {
                map.put(str, new c(context, str));
            }
        }
        return cVar;
    }
}
