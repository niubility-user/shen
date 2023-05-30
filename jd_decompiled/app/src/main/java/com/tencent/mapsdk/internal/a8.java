package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.z7;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class a8 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    public boolean f16234i;

    /* renamed from: j  reason: collision with root package name */
    public List<z7> f16235j;

    public a8(boolean z) {
        this.f16234i = true;
        this.f16235j = null;
        this.f16234i = z;
        this.f16235j = new ArrayList();
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        int size;
        List<z7> list = this.f16235j;
        if (list == null || (size = list.size()) == 0) {
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            z7 z7Var = this.f16235j.get(i2);
            if (z7Var != null) {
                if (this.f16234i) {
                    z7Var.a(f2, interpolator);
                } else {
                    z7Var.a(f2, z7Var.d());
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(long j2) {
        int size;
        super.a(j2);
        List<z7> list = this.f16235j;
        if (list == null || (size = list.size()) == 0) {
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            z7 z7Var = this.f16235j.get(i2);
            if (z7Var != null) {
                z7Var.a(j2);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(z7.b bVar) {
        int size;
        super.a(bVar);
        List<z7> list = this.f16235j;
        if (list == null || (size = list.size()) == 0) {
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            z7 z7Var = this.f16235j.get(i2);
            if (z7Var != null) {
                z7Var.a(bVar);
            }
        }
    }

    public void a(z7 z7Var) {
        if (z7Var == null) {
            return;
        }
        this.f16235j.add(z7Var);
    }

    @Override // com.tencent.mapsdk.internal.z7
    public boolean a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        List<z7> list;
        int size;
        boolean a = super.a(geoPoint, geoPoint2);
        if (!a || (list = this.f16235j) == null || (size = list.size()) == 0) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            z7 z7Var = this.f16235j.get(i2);
            if (z7Var != null) {
                a = a && z7Var.a(geoPoint, geoPoint2);
            }
        }
        return a;
    }

    public void i() {
        List<z7> list = this.f16235j;
        if (list == null) {
            return;
        }
        list.clear();
    }
}
