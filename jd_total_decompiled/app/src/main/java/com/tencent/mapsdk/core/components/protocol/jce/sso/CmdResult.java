package com.tencent.mapsdk.core.components.protocol.jce.sso;

import com.tencent.mapsdk.internal.k;
import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.q;
import g.i.a.a.a;

/* loaded from: classes9.dex */
public final class CmdResult extends a implements Cloneable {
    public static final /* synthetic */ boolean b = true;
    public int iErrCode;
    public int iSubErrCode;
    public String strErrDesc;

    public CmdResult() {
        this.iErrCode = 0;
        this.strErrDesc = "";
        this.iSubErrCode = 0;
    }

    public CmdResult(int i2, String str, int i3) {
        this.iErrCode = 0;
        this.strErrDesc = "";
        this.iSubErrCode = 0;
        this.iErrCode = i2;
        this.strErrDesc = str;
        this.iSubErrCode = i3;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "sosomap.CmdResult";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (b) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.iErrCode, "iErrCode");
        kVar.a(this.strErrDesc, "strErrDesc");
        kVar.a(this.iSubErrCode, "iSubErrCode");
    }

    @Override // com.tencent.mapsdk.internal.p
    public void displaySimple(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.iErrCode, true);
        kVar.a(this.strErrDesc, true);
        kVar.a(this.iSubErrCode, false);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        CmdResult cmdResult = (CmdResult) obj;
        return q.b(this.iErrCode, cmdResult.iErrCode) && q.a((Object) this.strErrDesc, (Object) cmdResult.strErrDesc) && q.b(this.iSubErrCode, cmdResult.iSubErrCode);
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
        this.iErrCode = mVar.a(this.iErrCode, 0, true);
        this.strErrDesc = mVar.b(1, true);
        this.iSubErrCode = mVar.a(this.iSubErrCode, 2, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.iErrCode, 0);
        nVar.a(this.strErrDesc, 1);
        nVar.a(this.iSubErrCode, 2);
    }
}
