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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(com.meizu.cloud.pushsdk.e.d.i.b r3, com.meizu.cloud.pushsdk.e.b.b r4) {
        /*
            java.lang.String r0 = r4.w()
            java.lang.String r1 = "User-Agent"
            if (r0 == 0) goto L10
            java.lang.String r0 = r4.w()
        Lc:
            r3.g(r1, r0)
            goto L1a
        L10:
            java.lang.String r0 = com.meizu.cloud.pushsdk.e.f.a.a
            if (r0 == 0) goto L1a
            r4.f(r0)
            java.lang.String r0 = com.meizu.cloud.pushsdk.e.f.a.a
            goto Lc
        L1a:
            com.meizu.cloud.pushsdk.e.d.c r0 = r4.o()
            if (r0 == 0) goto L3a
            r3.b(r0)
            java.lang.String r2 = r4.w()
            if (r2 == 0) goto L3a
            java.util.Set r0 = r0.e()
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L3a
            java.lang.String r4 = r4.w()
            r3.g(r1, r4)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.e.f.a.b(com.meizu.cloud.pushsdk.e.d.i$b, com.meizu.cloud.pushsdk.e.b.b):void");
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
