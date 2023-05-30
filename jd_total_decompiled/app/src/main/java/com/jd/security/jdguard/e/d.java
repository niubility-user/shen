package com.jd.security.jdguard.e;

import android.content.Context;
import com.jingdong.common.guard.JDGuardHelper;
import java.util.HashMap;
import java.util.Random;

/* loaded from: classes17.dex */
public class d implements c {

    /* loaded from: classes17.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jd.security.jdguard.e.a.values().length];
            a = iArr;
            try {
                iArr[com.jd.security.jdguard.e.a.init.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jd.security.jdguard.e.a.eid.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jd.security.jdguard.e.a.env.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jd.security.jdguard.e.a.eva.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jd.security.jdguard.e.a.sign.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public d(Context context) {
    }

    static boolean f(int i2) {
        return (i2 == 0 || i2 == -6102 || i2 == -1103 || i2 == -1104) ? false : true;
    }

    static boolean g(int i2) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random.nextInt(100) < i2;
    }

    private void h(com.jd.security.jdguard.e.a aVar, int i2, long j2) {
        b bVar = new b();
        bVar.f6936c = 0;
        bVar.a = i2;
        bVar.b = j2;
        i(aVar, bVar);
    }

    private void i(com.jd.security.jdguard.e.a aVar, b bVar) {
        if (aVar == null || bVar == null || com.jd.security.jdguard.b.e() == null || !com.jd.security.jdguard.b.e().a()) {
            return;
        }
        int i2 = a.a[aVar.ordinal()];
        if (i2 == 1) {
            if (f(bVar.a)) {
                j(aVar, bVar.a, bVar.b, bVar.f6936c, 1);
            } else {
                j(aVar, bVar.a, bVar.b, bVar.f6936c, 0);
            }
        } else if (i2 == 2) {
            j(aVar, bVar.a, bVar.b, bVar.f6936c, 0);
        } else if (i2 == 3 || i2 == 4 || i2 == 5) {
            if (f(bVar.a)) {
                j(aVar, bVar.a, bVar.b, bVar.f6936c, 1);
            } else if (g(com.jd.security.jdguard.b.e().c())) {
                j(aVar, bVar.a, bVar.b, bVar.f6936c, 0);
            }
        }
    }

    private void j(com.jd.security.jdguard.e.a aVar, int i2, long j2, int i3, int i4) {
        com.jd.security.jdguard.c e2 = com.jd.security.jdguard.b.e();
        if (e2 == null || e2.e() == null) {
            return;
        }
        String name = aVar.name();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("r", Integer.toString(i2));
        hashMap.put("s", Integer.toString(i3));
        hashMap.put("t", Long.toString(j2));
        e2.e().onSendStreamData(hashMap, name, JDGuardHelper.TAG, i4);
    }

    @Override // com.jd.security.jdguard.e.c
    public void a(int i2, long j2) {
        h(com.jd.security.jdguard.e.a.eid, i2, j2);
    }

    @Override // com.jd.security.jdguard.e.c
    public void b(int i2, long j2) {
        h(com.jd.security.jdguard.e.a.env, i2, j2);
    }

    @Override // com.jd.security.jdguard.e.c
    public void c(int i2, long j2) {
        h(com.jd.security.jdguard.e.a.eva, i2, j2);
    }

    @Override // com.jd.security.jdguard.e.c
    public void d(int i2, long j2) {
        h(com.jd.security.jdguard.e.a.init, i2, j2);
    }

    @Override // com.jd.security.jdguard.e.c
    public void e(int i2, int i3, long j2) {
        b bVar = new b();
        bVar.b = j2;
        bVar.a = i2;
        bVar.f6936c = i3;
        i(com.jd.security.jdguard.e.a.sign, bVar);
    }
}
