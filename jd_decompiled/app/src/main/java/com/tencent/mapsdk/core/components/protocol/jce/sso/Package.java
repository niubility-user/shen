package com.tencent.mapsdk.core.components.protocol.jce.sso;

import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.tencent.mapsdk.internal.k;
import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.q;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes9.dex */
public final class Package extends a implements Cloneable {
    public static int b;

    /* renamed from: c  reason: collision with root package name */
    public static byte[] f16194c;
    public static byte[] d;

    /* renamed from: e  reason: collision with root package name */
    public static ArrayList<Tag> f16195e;

    /* renamed from: f  reason: collision with root package name */
    public static final /* synthetic */ boolean f16196f = true;
    public byte[] busiBuff;
    public byte cEncodeType;
    public int eCmd;
    public byte[] head;
    public int iSeqNo;
    public String sAppId;
    public short shVer;
    public String strSubCmd;
    public String uin;
    public ArrayList<Tag> vTag;

    public Package() {
        this.shVer = (short) 0;
        this.eCmd = 0;
        this.strSubCmd = "";
        this.iSeqNo = 0;
        this.cEncodeType = (byte) 0;
        this.sAppId = "";
        this.uin = "";
        this.head = null;
        this.busiBuff = null;
        this.vTag = null;
    }

    public Package(short s, int i2, String str, int i3, byte b2, String str2, String str3, byte[] bArr, byte[] bArr2, ArrayList<Tag> arrayList) {
        this.shVer = (short) 0;
        this.eCmd = 0;
        this.strSubCmd = "";
        this.iSeqNo = 0;
        this.cEncodeType = (byte) 0;
        this.sAppId = "";
        this.uin = "";
        this.head = null;
        this.busiBuff = null;
        this.vTag = null;
        this.shVer = s;
        this.eCmd = i2;
        this.strSubCmd = str;
        this.iSeqNo = i3;
        this.cEncodeType = b2;
        this.sAppId = str2;
        this.uin = str3;
        this.head = bArr;
        this.busiBuff = bArr2;
        this.vTag = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "sosomap.Package";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f16196f) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.shVer, "shVer");
        kVar.a(this.eCmd, "eCmd");
        kVar.a(this.strSubCmd, "strSubCmd");
        kVar.a(this.iSeqNo, "iSeqNo");
        kVar.a(this.cEncodeType, "cEncodeType");
        kVar.a(this.sAppId, "sAppId");
        kVar.a(this.uin, "uin");
        kVar.a(this.head, DataCompassUtils.MODULE_TYPE_HEAD);
        kVar.a(this.busiBuff, "busiBuff");
        kVar.a((Collection) this.vTag, "vTag");
    }

    @Override // com.tencent.mapsdk.internal.p
    public void displaySimple(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.shVer, true);
        kVar.a(this.eCmd, true);
        kVar.a(this.strSubCmd, true);
        kVar.a(this.iSeqNo, true);
        kVar.a(this.cEncodeType, true);
        kVar.a(this.sAppId, true);
        kVar.a(this.uin, true);
        kVar.a(this.head, true);
        kVar.a(this.busiBuff, true);
        kVar.a((Collection) this.vTag, false);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Package r4 = (Package) obj;
        return q.b(this.shVer, r4.shVer) && q.b(this.eCmd, r4.eCmd) && q.a((Object) this.strSubCmd, (Object) r4.strSubCmd) && q.b(this.iSeqNo, r4.iSeqNo) && q.b(this.cEncodeType, r4.cEncodeType) && q.a((Object) this.sAppId, (Object) r4.sAppId) && q.a((Object) this.uin, (Object) r4.uin) && q.a((Object) this.head, (Object) r4.head) && q.a((Object) this.busiBuff, (Object) r4.busiBuff) && q.a((Object) this.vTag, (Object) r4.vTag);
    }

    public byte[] getBusiBuff() {
        return this.busiBuff;
    }

    public byte[] getHead() {
        return this.head;
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
        this.shVer = mVar.a(this.shVer, 0, true);
        this.eCmd = mVar.a(this.eCmd, 1, true);
        this.strSubCmd = mVar.b(2, true);
        this.iSeqNo = mVar.a(this.iSeqNo, 3, false);
        this.cEncodeType = mVar.a(this.cEncodeType, 4, false);
        this.sAppId = mVar.b(5, false);
        this.uin = mVar.b(6, false);
        if (f16194c == null) {
            f16194c = r0;
            byte[] bArr = {0};
        }
        this.head = mVar.a(f16194c, 7, false);
        if (d == null) {
            d = r0;
            byte[] bArr2 = {0};
        }
        this.busiBuff = mVar.a(d, 8, false);
        if (f16195e == null) {
            f16195e = new ArrayList<>();
            f16195e.add(new Tag());
        }
        this.vTag = (ArrayList) mVar.a((m) f16195e, 9, false);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.shVer, 0);
        nVar.a(this.eCmd, 1);
        nVar.a(this.strSubCmd, 2);
        nVar.a(this.iSeqNo, 3);
        nVar.a(this.cEncodeType, 4);
        String str = this.sAppId;
        if (str != null) {
            nVar.a(str, 5);
        }
        String str2 = this.uin;
        if (str2 != null) {
            nVar.a(str2, 6);
        }
        byte[] bArr = this.head;
        if (bArr != null) {
            nVar.a(bArr, 7);
        }
        byte[] bArr2 = this.busiBuff;
        if (bArr2 != null) {
            nVar.a(bArr2, 8);
        }
        ArrayList<Tag> arrayList = this.vTag;
        if (arrayList != null) {
            nVar.a((Collection) arrayList, 9);
        }
    }
}
