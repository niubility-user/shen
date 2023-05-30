package com.tencent.mapsdk.core.components.protocol.jce.rtt;

import com.tencent.mapsdk.internal.k;
import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.q;
import com.tencent.mapsdk.internal.q2;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class RttRequest extends a implements Cloneable {

    /* renamed from: c  reason: collision with root package name */
    public static int f16190c;
    public static ArrayList<Long> d;
    public ArrayList<Integer> bounds;
    public ArrayList<Long> crcs;
    public int type;
    public short zip;
    public short zoom;

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ boolean f16191e = true;
    public static ArrayList<Integer> b = new ArrayList<>();

    static {
        b.add(0);
        f16190c = 0;
        d = new ArrayList<>();
        d.add(0L);
    }

    public RttRequest() {
        this.bounds = null;
        this.zoom = (short) 0;
        this.zip = (short) 0;
        this.type = q2.f17008e.a();
        this.crcs = null;
    }

    public RttRequest(ArrayList<Integer> arrayList, short s, short s2, int i2, ArrayList<Long> arrayList2) {
        this.bounds = null;
        this.zoom = (short) 0;
        this.zip = (short) 0;
        this.type = q2.f17008e.a();
        this.crcs = null;
        this.bounds = arrayList;
        this.zoom = s;
        this.zip = s2;
        this.type = i2;
        this.crcs = arrayList2;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "navsns.RttRequest";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f16191e) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a((Collection) this.bounds, "bounds");
        kVar.a(this.zoom, "zoom");
        kVar.a(this.zip, "zip");
        kVar.a(this.type, "type");
        kVar.a((Collection) this.crcs, "crcs");
    }

    @Override // com.tencent.mapsdk.internal.p
    public void displaySimple(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a((Collection) this.bounds, true);
        kVar.a(this.zoom, true);
        kVar.a(this.zip, true);
        kVar.a(this.type, true);
        kVar.a((Collection) this.crcs, false);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        RttRequest rttRequest = (RttRequest) obj;
        return q.a((Object) this.bounds, (Object) rttRequest.bounds) && q.b(this.zoom, rttRequest.zoom) && q.b(this.zip, rttRequest.zip) && q.b(this.type, rttRequest.type) && q.a((Object) this.crcs, (Object) rttRequest.crcs);
    }

    public ArrayList<Integer> getBounds() {
        return this.bounds;
    }

    public ArrayList<Long> getCrcs() {
        return this.crcs;
    }

    public int getType() {
        return this.type;
    }

    public short getZip() {
        return this.zip;
    }

    public short getZoom() {
        return this.zoom;
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        this.bounds = (ArrayList) mVar.a((m) b, 0, true);
        this.zoom = mVar.a(this.zoom, 1, true);
        this.zip = mVar.a(this.zip, 2, true);
        this.type = mVar.a(this.type, 3, false);
        this.crcs = (ArrayList) mVar.a((m) d, 4, false);
    }

    public void setBounds(ArrayList<Integer> arrayList) {
        this.bounds = arrayList;
    }

    public void setCrcs(ArrayList<Long> arrayList) {
        this.crcs = arrayList;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setZip(short s) {
        this.zip = s;
    }

    public void setZoom(short s) {
        this.zoom = s;
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a((Collection) this.bounds, 0);
        nVar.a(this.zoom, 1);
        nVar.a(this.zip, 2);
        nVar.a(this.type, 3);
        ArrayList<Long> arrayList = this.crcs;
        if (arrayList != null) {
            nVar.a((Collection) arrayList, 4);
        }
    }
}
