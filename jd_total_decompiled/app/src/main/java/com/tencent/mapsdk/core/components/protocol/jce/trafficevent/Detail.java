package com.tencent.mapsdk.core.components.protocol.jce.trafficevent;

import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.p;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class Detail extends a {
    public static Basic b = new Basic();

    /* renamed from: c  reason: collision with root package name */
    public static ArrayList<Float> f16198c = new ArrayList<>();
    public Basic basic;
    public ArrayList<Float> coord;

    static {
        f16198c.add(Float.valueOf(0.0f));
    }

    public Detail() {
        this.basic = null;
        this.coord = null;
    }

    public Detail(Basic basic, ArrayList<Float> arrayList) {
        this.basic = null;
        this.coord = null;
        this.basic = basic;
        this.coord = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        this.basic = (Basic) mVar.b((p) b, 0, true);
        this.coord = (ArrayList) mVar.a((m) f16198c, 1, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a((p) this.basic, 0);
        ArrayList<Float> arrayList = this.coord;
        if (arrayList != null) {
            nVar.a((Collection) arrayList, 1);
        }
    }
}
