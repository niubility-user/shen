package com.tencent.mapsdk.internal;

import android.util.Pair;
import androidx.annotation.NonNull;
import com.tencent.map.lib.models.CommonParamsModelClass;
import com.tencent.mapsdk.internal.tc;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class cd extends tc<dd> {
    public cd(qi qiVar) {
        super(qiVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public Pair<VectorOverlay, TencentMap.IClickedObject> a(LatLng latLng, long j2, String str, String str2) {
        Pair<VectorOverlay, TencentMap.IClickedObject> pair = new Pair<>(null, null);
        if (j2 > 0) {
            for (int i2 = 0; i2 < this.f17272c.size(); i2++) {
                ed edVar = (ed) this.f17272c.valueAt(i2);
                if (edVar.x() == j2) {
                    tc.a aVar = new tc.a(latLng, str, str2);
                    edVar.a(aVar);
                    return new Pair<>(edVar, aVar);
                }
            }
        }
        return pair;
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized ed a(@NonNull dd ddVar) {
        return (ed) super.a((cd) ddVar);
    }

    public List<CommonParamsModelClass.AnimationInfo> a(long j2) {
        int b = this.b.b(j2);
        if (b <= 0) {
            return null;
        }
        float[] c2 = this.b.c(j2);
        String[] d = this.b.d(j2);
        if (c2 == null || d == null || c2.length != d.length || c2.length != b) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < b; i2++) {
            arrayList.add(new CommonParamsModelClass.AnimationInfo(i2, d[i2], c2[i2]));
        }
        return arrayList;
    }

    public void a(long j2, float f2, float f3, float f4) {
        for (int i2 = 0; i2 < this.f17272c.size(); i2++) {
            if (((ed) this.f17272c.valueAt(i2)).x() == j2) {
                this.b.a(j2, f2, f3, f4);
            }
        }
    }

    public void a(long j2, int i2) {
        for (int i3 = 0; i3 < this.f17272c.size(); i3++) {
            if (((ed) this.f17272c.valueAt(i3)).x() == j2) {
                this.b.a(j2, i2);
            }
        }
    }

    public void a(long j2, int i2, float f2, boolean z) {
        for (int i3 = 0; i3 < this.f17272c.size(); i3++) {
            if (((ed) this.f17272c.valueAt(i3)).x() == j2) {
                this.b.a(j2, i2, f2, z);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
        ((ed) scVar).y();
    }

    public int b(long j2) {
        return Math.max(this.b.a(j2), 0);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public sc<dd> b(dd ddVar) {
        return new ed(this, ddVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public boolean b() {
        for (int i2 = 0; i2 < this.f17272c.size(); i2++) {
            if (((ed) this.f17272c.valueAt(i2)).isClickEnabled()) {
                return true;
            }
        }
        return false;
    }

    public List<CommonParamsModelClass.MaterialVariantInfo> c(long j2) {
        String[] f2 = this.b.f(j2);
        ArrayList arrayList = new ArrayList();
        if (f2 != null) {
            for (String str : f2) {
                CommonParamsModelClass.MaterialVariantInfo materialVariantInfo = new CommonParamsModelClass.MaterialVariantInfo();
                materialVariantInfo.variantName = str;
                arrayList.add(materialVariantInfo);
            }
        }
        return arrayList;
    }

    public void d(long j2) {
        for (int i2 = 0; i2 < this.f17272c.size(); i2++) {
            if (((ed) this.f17272c.valueAt(i2)).x() == j2) {
                this.b.i(j2);
            }
        }
    }

    public void e(long j2) {
        for (int i2 = 0; i2 < this.f17272c.size(); i2++) {
            if (((ed) this.f17272c.valueAt(i2)).x() == j2) {
                this.b.j(j2);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void f() {
        if (this.b.m()) {
            this.b.T();
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void g() {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            ed edVar = (ed) this.d.get(this.d.keyAt(i2));
            ArrayList arrayList = new ArrayList();
            if (edVar.f().a().isBuildingHidden() && edVar.f().a().getLatLngBounds() != null) {
                arrayList.add(edVar.f().a().getLatLngBounds());
            }
            this.b.a(arrayList);
            edVar.a(this.b.a(edVar.f()));
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void i() {
        int size = this.f17276h.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.h(((sc) this.f17276h.get(this.f17276h.keyAt(i2))).x());
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void j() {
        int size = this.f17274f.size();
        for (int i2 = 0; i2 < size; i2++) {
            ed edVar = (ed) this.f17274f.get(this.f17274f.keyAt(i2));
            ArrayList arrayList = new ArrayList();
            if (edVar.f().a().isBuildingHidden() && edVar.f().a().getLatLngBounds() != null) {
                arrayList.add(edVar.f().a().getLatLngBounds());
            }
            this.b.a(arrayList);
            this.b.a(edVar.x(), edVar.f());
        }
    }

    public cd k() {
        return this;
    }
}
