package com.tencent.mapsdk.core.components.protocol.jce.conf;

import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class SCFileUpdateRsp extends a {
    public static ArrayList<FileUpdateRsp> b = new ArrayList<>();
    public int iRet;
    public ArrayList<FileUpdateRsp> vItems;

    static {
        b.add(new FileUpdateRsp());
    }

    public SCFileUpdateRsp() {
        this.iRet = 0;
        this.vItems = null;
    }

    public SCFileUpdateRsp(int i2, ArrayList<FileUpdateRsp> arrayList) {
        this.iRet = 0;
        this.vItems = null;
        this.iRet = i2;
        this.vItems = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "MapConfProtocol.SCFileUpdateRsp";
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        this.iRet = mVar.a(this.iRet, 0, true);
        this.vItems = (ArrayList) mVar.a((m) b, 1, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SCFileUpdateRsp{");
        stringBuffer.append("iRet=");
        stringBuffer.append(this.iRet);
        stringBuffer.append(", vItems=");
        stringBuffer.append(this.vItems);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.iRet, 0);
        ArrayList<FileUpdateRsp> arrayList = this.vItems;
        if (arrayList != null) {
            nVar.a((Collection) arrayList, 1);
        }
    }
}
