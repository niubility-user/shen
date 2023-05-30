package g.e.a;

import android.content.Context;

/* loaded from: classes12.dex */
public abstract class d {
    public static d a(e eVar) {
        return com.huawei.agconnect.core.c.b.g(eVar);
    }

    public static d c() {
        return com.huawei.agconnect.core.c.b.f();
    }

    public static synchronized void e(Context context) {
        synchronized (d.class) {
            com.huawei.agconnect.core.c.b.j(context);
        }
    }

    public abstract Context b();

    public abstract e d();
}
