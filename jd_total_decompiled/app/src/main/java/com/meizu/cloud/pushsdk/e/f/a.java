package com.meizu.cloud.pushsdk.e.f;

import com.meizu.cloud.pushsdk.e.d.e;
import com.meizu.cloud.pushsdk.e.d.i;
import com.meizu.cloud.pushsdk.e.d.k;
import java.io.File;
import java.io.IOException;

/* loaded from: classes14.dex */
public final class a {
    private static String a;

    public static k a(com.meizu.cloud.pushsdk.e.b.b bVar) throws com.meizu.cloud.pushsdk.e.c.a {
        try {
            i.b bVar2 = new i.b();
            bVar2.e(bVar.v());
            b(bVar2, bVar);
            bVar2.i();
            i h2 = bVar2.h();
            bVar.e(new e());
            k a2 = bVar.l().a(h2);
            com.meizu.cloud.pushsdk.e.i.b.h(a2, bVar.m(), bVar.n());
            return a2;
        } catch (IOException e2) {
            try {
                File file = new File(bVar.m() + File.separator + bVar.n());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            throw new com.meizu.cloud.pushsdk.e.c.a(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void b(i.b bVar, com.meizu.cloud.pushsdk.e.b.b bVar2) {
        String str;
        com.meizu.cloud.pushsdk.e.d.c o;
        if (bVar2.w() == null) {
            String str2 = a;
            if (str2 != null) {
                bVar2.f(str2);
                str = a;
            }
            o = bVar2.o();
            if (o == null) {
                bVar.b(o);
                if (bVar2.w() == null || o.e().contains("User-Agent")) {
                    return;
                }
                bVar.g("User-Agent", bVar2.w());
                return;
            }
            return;
        }
        str = bVar2.w();
        bVar.g("User-Agent", str);
        o = bVar2.o();
        if (o == null) {
        }
    }

    public static k c(com.meizu.cloud.pushsdk.e.b.b bVar) throws com.meizu.cloud.pushsdk.e.c.a {
        try {
            i.b bVar2 = new i.b();
            bVar2.e(bVar.v());
            b(bVar2, bVar);
            int p = bVar.p();
            if (p == 0) {
                bVar2.i();
            } else if (p == 1) {
                bVar2.n(bVar.r());
            } else if (p == 2) {
                bVar2.o(bVar.r());
            } else if (p == 3) {
                bVar2.d(bVar.r());
            } else if (p == 4) {
                bVar2.m();
            } else if (p == 5) {
                bVar2.j(bVar.r());
            }
            i h2 = bVar2.h();
            bVar.e(new e());
            return bVar.l().a(h2);
        } catch (IOException e2) {
            throw new com.meizu.cloud.pushsdk.e.c.a(e2);
        }
    }

    public static k d(com.meizu.cloud.pushsdk.e.b.b bVar) throws com.meizu.cloud.pushsdk.e.c.a {
        try {
            i.b bVar2 = new i.b();
            bVar2.e(bVar.v());
            b(bVar2, bVar);
            bVar2.n(new b(bVar.q(), bVar.u()));
            i h2 = bVar2.h();
            bVar.e(new e());
            return bVar.l().a(h2);
        } catch (IOException e2) {
            throw new com.meizu.cloud.pushsdk.e.c.a(e2);
        }
    }
}
