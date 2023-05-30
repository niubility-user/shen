package com.jingdong.aura.a.a;

import com.jingdong.aura.core.util.h;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class c {
    private static c b;
    private List<a> a;

    /* loaded from: classes4.dex */
    public static class a {
        public String a;
        public List<String> b;

        public a() {
            if (com.jingdong.aura.a.b.c.f() == 2) {
                this.b = new ArrayList();
            } else {
                this.b = new CopyOnWriteArrayList();
            }
        }
    }

    private c() {
        if (com.jingdong.aura.a.b.c.f() == 2) {
            this.a = new ArrayList();
        } else {
            this.a = new CopyOnWriteArrayList();
        }
    }

    public static synchronized c a() {
        synchronized (c.class) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
            return b;
        }
        return b;
    }

    public a b(String str) {
        if (h.a(str)) {
            return null;
        }
        for (a aVar : this.a) {
            if (!h.a(aVar.a) && aVar.a.equals(str)) {
                return aVar;
            }
        }
        a aVar2 = new a();
        aVar2.a = new String(str);
        this.a.add(aVar2);
        return aVar2;
    }

    public String a(String str) {
        List<a> list = this.a;
        if (list == null || list.size() == 0 || str == null) {
            return null;
        }
        for (a aVar : this.a) {
            Iterator<String> it = aVar.b.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    return aVar.a;
                }
            }
        }
        return null;
    }

    public static void b() {
        List<String> a2 = com.jingdong.aura.a.a.a.c().a();
        if (a2 == null || a2.isEmpty()) {
            return;
        }
        for (String str : a2) {
            long c2 = com.jingdong.aura.a.b.l.h.c(new File(com.jingdong.aura.a.b.k.b.b(), str));
            if (c2 > 0) {
                String str2 = null;
                try {
                    String str3 = File.separator;
                    str2 = String.format("%s%s%s%s%s%d%s%s", com.jingdong.aura.a.b.k.b.b().getAbsolutePath(), str3, str, str3, "package_", Long.valueOf(c2), str3, "manual.ini");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                File file = new File(str2);
                if (file.exists()) {
                    a(str, file, "manualComponents");
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:96:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(java.lang.String r3, java.io.File r4, java.lang.String r5) {
        /*
            if (r4 != 0) goto L3
            return
        L3:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            java.util.Properties r4 = new java.util.Properties     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L61
            r4.<init>()     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L61
            r4.load(r1)     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L61
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L61
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L61
            r1.close()     // Catch: java.io.IOException -> L2c
            goto L30
        L1d:
            r4 = move-exception
            goto L23
        L1f:
            r3 = move-exception
            goto L63
        L21:
            r4 = move-exception
            r1 = r0
        L23:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L61
            if (r1 == 0) goto L30
            r1.close()     // Catch: java.io.IOException -> L2c
            goto L30
        L2c:
            r4 = move-exception
            r4.printStackTrace()
        L30:
            if (r0 == 0) goto L60
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L39
            goto L60
        L39:
            java.lang.String r4 = r0.trim()
            java.lang.String r5 = ","
            java.lang.String[] r4 = r4.split(r5)
            com.jingdong.aura.a.a.c r5 = a()
            com.jingdong.aura.a.a.c$a r3 = r5.b(r3)
            if (r3 == 0) goto L60
            java.util.List<java.lang.String> r5 = r3.b
            r5.clear()
            int r5 = r4.length
            r0 = 0
        L54:
            if (r0 >= r5) goto L60
            r1 = r4[r0]
            java.util.List<java.lang.String> r2 = r3.b
            r2.add(r1)
            int r0 = r0 + 1
            goto L54
        L60:
            return
        L61:
            r3 = move-exception
            r0 = r1
        L63:
            if (r0 == 0) goto L6d
            r0.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r4 = move-exception
            r4.printStackTrace()
        L6d:
            goto L6f
        L6e:
            throw r3
        L6f:
            goto L6e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.a.c.a(java.lang.String, java.io.File, java.lang.String):void");
    }
}
