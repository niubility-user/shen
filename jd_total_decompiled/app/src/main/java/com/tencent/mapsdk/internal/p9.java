package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.core.utils.cache.DiskCache;
import com.tencent.mapsdk.core.utils.cache.MemoryCache;
import com.tencent.mapsdk.internal.l9;
import com.tencent.mapsdk.internal.r9;
import java.lang.reflect.GenericDeclaration;

/* loaded from: classes9.dex */
public class p9 {
    private static n9 a = new n9();

    public static <D extends m9> j9<D> a(l9<D> l9Var) {
        if (l9Var instanceof j9) {
            return (j9) l9Var;
        }
        return null;
    }

    public static <D extends m9> j9<D> a(Class<D> cls, l9.a... aVarArr) {
        return a(b(cls, aVarArr));
    }

    public static String a(String str) {
        return a.a(str);
    }

    public static <D extends m9> l9<D> b(Class<D> cls, l9.a... aVarArr) {
        GenericDeclaration genericDeclaration;
        l9.a aVar;
        n9 n9Var;
        l9<D> l9Var = null;
        if (cls != null && aVarArr != null && aVarArr.length != 0) {
            o9 o9Var = new o9();
            boolean z = aVarArr.length > 1;
            for (l9.a aVar2 : aVarArr) {
                if (aVar2 instanceof MemoryCache.a) {
                    genericDeclaration = MemoryCache.class;
                    n9Var = a;
                    aVar = (MemoryCache.a) aVar2;
                } else {
                    if (aVar2 instanceof r9.d) {
                        r9.d dVar = (r9.d) aVar2;
                        if (dVar.b() == r9.b.DISK) {
                            genericDeclaration = DiskCache.class;
                            n9Var = a;
                            aVar = dVar;
                        } else if (dVar.b() == r9.b.DB) {
                            genericDeclaration = q9.class;
                            n9Var = a;
                            aVar = dVar;
                        }
                    }
                    if (z && l9Var != null) {
                        o9Var.a(l9Var);
                    }
                }
                l9Var = n9Var.a(cls, aVar, genericDeclaration);
                if (z) {
                    o9Var.a(l9Var);
                }
            }
            if (z) {
                return o9Var;
            }
        }
        return l9Var;
    }

    public static <D extends m9> s9<D> b(l9<D> l9Var) {
        if (l9Var instanceof s9) {
            return (s9) l9Var;
        }
        return null;
    }

    public static <D extends m9> s9<D> c(Class<D> cls, l9.a... aVarArr) {
        return b(b(cls, aVarArr));
    }
}
