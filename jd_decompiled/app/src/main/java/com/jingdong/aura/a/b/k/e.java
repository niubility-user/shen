package com.jingdong.aura.a.b.k;

import android.annotation.SuppressLint;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.a.b.i;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class e implements l.b.a.d, e.a.a.a.a, e.a.a.b.a {
    private final Dictionary<String, String> a;

    static {
        com.jingdong.aura.core.util.l.c.a("SystemBundle");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e() {
        Hashtable hashtable = new Hashtable();
        this.a = hashtable;
        hashtable.put("Bundle-Name", "System Bundle");
        hashtable.put("Bundle-Version", "1.4.7.7");
        hashtable.put("Bundle-Vendor", "Aura");
        i iVar = new i(this, this, null, new String[]{e.a.a.b.a.class.getName(), e.a.a.a.a.class.getName()});
        com.jingdong.aura.core.util.i.a(b.d(), e.a.a.b.a.class.getName(), iVar);
        com.jingdong.aura.core.util.i.a(b.d(), e.a.a.a.a.class.getName(), iVar);
        b.f().add(iVar);
    }

    @Override // l.b.a.d
    public void a() {
    }

    public void a(int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"UseSparseArrays"})
    public void a(l.b.a.d[] dVarArr, int i2, boolean z) {
        int g2;
        if (b.g() != i2) {
            boolean z2 = i2 > b.g();
            int g3 = z2 ? i2 - b.g() : b.g() - i2;
            HashMap hashMap = new HashMap(0);
            for (int i3 = 0; i3 < dVarArr.length; i3++) {
                if (dVarArr[i3] != b.h() && (z || ((h) dVarArr[i3]).j())) {
                    h hVar = (h) dVarArr[i3];
                    if (z2) {
                        g2 = (hVar.h() - b.g()) - 1;
                    } else {
                        g2 = b.g() - hVar.h();
                    }
                    if (g2 >= 0 && g2 < g3) {
                        com.jingdong.aura.core.util.i.a(hashMap, Integer.valueOf(g2), hVar);
                    }
                }
            }
            for (int i4 = 0; i4 < g3; i4++) {
                if (z2) {
                    b.a(b.g() + 1);
                } else {
                    b.a(b.g() - 1);
                }
                List list = (List) hashMap.get(Integer.valueOf(i4));
                if (list != null) {
                    h[] hVarArr = (h[]) list.toArray(new h[list.size()]);
                    for (int i5 = 0; i5 < hVarArr.length; i5++) {
                        if (z2) {
                            try {
                                System.out.println("STARTING " + hVarArr[i5].b());
                                hVarArr[i5].n();
                            } catch (Throwable th) {
                                th.printStackTrace();
                                th.printStackTrace();
                                b.a(2, b.h(), th);
                            }
                        } else if (hVarArr[i5].k() != 1) {
                            System.out.println("STOPPING " + hVarArr[i5].b());
                            try {
                                hVarArr[(hVarArr.length - i5) - 1].o();
                            } catch (l.b.a.b e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
            b.a(i2);
        }
    }

    @Override // l.b.a.d
    public String b() {
        return "System Bundle";
    }

    @Override // l.b.a.d
    public void c() {
        throw new l.b.a.b("Cannot uninstall the System Bundle");
    }

    public String toString() {
        return "SystemBundle";
    }
}
