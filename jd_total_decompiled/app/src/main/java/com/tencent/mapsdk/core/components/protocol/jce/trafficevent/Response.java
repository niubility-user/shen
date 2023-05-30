package com.tencent.mapsdk.core.components.protocol.jce.trafficevent;

import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class Response extends a {
    public static ArrayList<Detail> b = new ArrayList<>();
    public ArrayList<Detail> detail;
    public short error;
    public String msg;

    static {
        b.add(new Detail());
    }

    public Response() {
        this.error = (short) 0;
        this.msg = "";
        this.detail = null;
    }

    public Response(short s, String str, ArrayList<Detail> arrayList) {
        this.error = (short) 0;
        this.msg = "";
        this.detail = null;
        this.error = s;
        this.msg = str;
        this.detail = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        this.error = mVar.a(this.error, 0, true);
        this.msg = mVar.b(1, false);
        this.detail = (ArrayList) mVar.a((m) b, 2, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.error, 0);
        String str = this.msg;
        if (str != null) {
            nVar.a(str, 1);
        }
        ArrayList<Detail> arrayList = this.detail;
        if (arrayList != null) {
            nVar.a((Collection) arrayList, 2);
        }
    }
}
