package g.j.b.b;

import android.content.Context;
import android.os.Process;
import com.xiaomi.push.y4;

/* loaded from: classes11.dex */
public class a {
    public static void a(Context context, g.j.b.a.a aVar, g.j.b.c.a aVar2, g.j.b.c.b bVar) {
        g.j.a.a.a.c.B("init in  pid :" + Process.myPid() + " threadId: " + Thread.currentThread().getId());
        b.e(context).h(aVar, aVar2, bVar);
        if (y4.j(context)) {
            g.j.a.a.a.c.B("init in process\u3000start scheduleJob");
            b.e(context).g();
        }
    }

    public static void b(Context context, g.j.b.a.b bVar) {
        if (bVar != null) {
            b.e(context).i(bVar);
        }
    }

    public static void c(Context context, g.j.b.a.c cVar) {
        if (cVar != null) {
            b.e(context).j(cVar);
        }
    }

    public static void d(Context context, g.j.b.a.a aVar) {
        if (aVar == null) {
            return;
        }
        b.e(context).p(aVar.g(), aVar.h(), aVar.c(), aVar.e());
    }
}
