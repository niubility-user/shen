package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class pc {
    private e1 a;
    private qi b;

    /* renamed from: c  reason: collision with root package name */
    private HashMap<Integer, te> f16980c = new HashMap<>();
    private HashMap<Integer, te> d = new HashMap<>();

    public pc(e1 e1Var, bc bcVar, qi qiVar) {
        this.a = e1Var;
        this.b = qiVar;
    }

    public static Bitmap a(String str) {
        return a7.f16233e.a(str);
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, te> entry : this.f16980c.entrySet()) {
            Integer key = entry.getKey();
            entry.getValue();
            if (!this.d.containsKey(key)) {
                arrayList.add(Integer.valueOf(key.intValue()));
            }
        }
        int size = arrayList.size();
        if (size <= 0) {
            return;
        }
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        this.b.a(iArr, size);
    }

    private void e() {
        this.f16980c.clear();
        this.f16980c.putAll(this.d);
        this.d.clear();
    }

    public e1 a() {
        return this.a;
    }

    public void a(te teVar) {
        int i2 = teVar.w;
        if (i2 > 0 && this.f16980c.containsKey(Integer.valueOf(i2))) {
            if (teVar.y()) {
                this.b.b(teVar);
                if (teVar.C()) {
                    a7.f16233e.a(teVar.j(), teVar.a(0));
                    teVar.h(false);
                }
            }
            teVar.d(false);
            this.d.put(Integer.valueOf(teVar.w), teVar);
            return;
        }
        int a = this.b.a(teVar);
        teVar.w = a;
        if (a > 0) {
            a7.f16233e.a(teVar.j(), teVar.a(0));
            teVar.h(false);
            teVar.d(false);
            this.d.put(Integer.valueOf(teVar.w), teVar);
        }
    }

    public float b() {
        return this.a.h().q();
    }

    public void c() {
        d();
        e();
    }
}
