package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.mapsdk.internal.l9;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public final class n9 {
    private bb a = new bb();
    private ConcurrentHashMap<String, Map<String, l9>> b = new ConcurrentHashMap<>();

    /* loaded from: classes9.dex */
    public static class a<D extends m9> extends ua<String, D> {

        /* renamed from: i */
        private l9.b<D> f16887i;

        public a(int i2, l9.b<D> bVar) {
            super(i2);
            this.f16887i = bVar;
        }

        public int a(@NonNull String str, D d) {
            if (d != null) {
                return d.a();
            }
            return 0;
        }

        @Override // com.tencent.mapsdk.internal.ua
        public void a(boolean z, @NonNull String str, @NonNull D d, @Nullable D d2) {
            l9.b<D> bVar;
            if (!z || (bVar = this.f16887i) == null) {
                return;
            }
            bVar.a(d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.tencent.mapsdk.internal.ua
        public /* synthetic */ int c(@NonNull String str, Object obj) {
            return a(str, (String) ((m9) obj));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v5, types: [com.tencent.mapsdk.internal.l9] */
    public <D extends m9, C extends l9<D>> C a(Class<D> cls, l9.a aVar, Class<C> cls2) {
        C c2;
        Map<String, l9> map = this.b.get(cls2.getName());
        String a2 = p9.a(cls.getName() + aVar.toString());
        String name = cls2.getName();
        Constructor<?> constructor = null;
        if (map != null) {
            c2 = (C) map.get(a2);
            if (c2 != null) {
                return c2;
            }
        } else {
            c2 = null;
        }
        for (Constructor<?> constructor2 : cls2.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == aVar.getClass()) {
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            try {
                c2 = (l9) constructor.newInstance(aVar);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        if (c2 != null) {
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(a2, c2);
            this.b.put(name, map);
        }
        return c2;
    }

    public String a(String str) {
        return this.a.a(str);
    }
}
