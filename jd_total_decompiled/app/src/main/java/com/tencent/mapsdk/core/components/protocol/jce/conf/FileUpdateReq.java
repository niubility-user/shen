package com.tencent.mapsdk.core.components.protocol.jce.conf;

import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import g.i.a.a.a;

/* loaded from: classes9.dex */
public final class FileUpdateReq extends a {
    public int iVersion;
    public String sMd5;
    public String sName;

    public FileUpdateReq() {
        this.sName = "";
        this.iVersion = 0;
        this.sMd5 = "";
    }

    public FileUpdateReq(String str, int i2, String str2) {
        this.sName = "";
        this.iVersion = 0;
        this.sMd5 = "";
        this.sName = str;
        this.iVersion = i2;
        this.sMd5 = str2;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "MapConfProtocol.FileUpdateReq";
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        this.sName = mVar.b(0, true);
        this.iVersion = mVar.a(this.iVersion, 1, true);
        this.sMd5 = mVar.b(2, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("FileUpdateReq{");
        stringBuffer.append("sName='");
        stringBuffer.append(this.sName);
        stringBuffer.append('\'');
        stringBuffer.append(", iVersion=");
        stringBuffer.append(this.iVersion);
        stringBuffer.append(", sMd5='");
        stringBuffer.append(this.sMd5);
        stringBuffer.append('\'');
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.sName, 0);
        nVar.a(this.iVersion, 1);
        String str = this.sMd5;
        if (str != null) {
            nVar.a(str, 2);
        }
    }
}
