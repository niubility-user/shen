package com.jingdong.aura.a.b.j;

import android.os.Build;
import com.jingdong.aura.a.b.f;
import com.jingdong.aura.a.b.h;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class b {
    private static final Map<String, ClassLoader> a = new HashMap();

    public static ClassLoader a(h hVar) {
        ClassLoader fVar;
        Map<String, ClassLoader> map = a;
        synchronized (map) {
            String b = hVar.b();
            if (map.containsKey(b)) {
                return map.get(b);
            }
            if (Build.VERSION.SDK_INT >= 29) {
                fVar = new a(hVar);
            } else {
                fVar = new f(hVar, hVar.i());
            }
            map.put(b, fVar);
            return fVar;
        }
    }
}
