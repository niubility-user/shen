package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class th {
    private final String a = "_night";
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private final int f17288c;
    private final List<zh> d;

    /* loaded from: classes9.dex */
    public class a implements Comparator<zh> {
        public a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(zh zhVar, zh zhVar2) {
            return zhVar2.d() - zhVar.d();
        }
    }

    public th(int i2, int i3, List<zh> list) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.d = copyOnWriteArrayList;
        this.b = i2;
        this.f17288c = i3;
        copyOnWriteArrayList.addAll(list);
        Collections.sort(list, new a());
    }

    public int a() {
        return this.f17288c;
    }

    public Object[] a(v5 v5Var, boolean z) {
        for (zh zhVar : this.d) {
            if (zhVar.c(v5Var)) {
                Bitmap a2 = zhVar.a(z);
                StringBuilder sb = new StringBuilder();
                sb.append(zhVar.a());
                sb.append(z ? "_night" : "");
                String sb2 = sb.toString();
                return (!z || zhVar.b() == null || zhVar.b().length() <= 0) ? new Object[]{sb2, zhVar.c(), a2} : new Object[]{sb2, zhVar.b(), a2};
            }
        }
        return null;
    }

    public int b() {
        return this.b;
    }
}
