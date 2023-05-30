package com.tencent.mapsdk.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class h extends p {
    public short b;

    /* renamed from: c */
    public byte f16628c;
    public int d;

    /* renamed from: e */
    public int f16629e;

    /* renamed from: f */
    public String f16630f;

    /* renamed from: g */
    public String f16631g;

    /* renamed from: h */
    public byte[] f16632h;

    /* renamed from: i */
    public int f16633i;

    /* renamed from: j */
    public Map<String, String> f16634j;

    /* renamed from: k */
    public Map<String, String> f16635k;

    /* renamed from: n */
    public static final /* synthetic */ boolean f16627n = true;

    /* renamed from: l */
    public static byte[] f16625l = null;

    /* renamed from: m */
    public static Map<String, String> f16626m = null;

    public h() {
        this.b = (short) 0;
        this.f16628c = (byte) 0;
        this.d = 0;
        this.f16629e = 0;
        this.f16630f = null;
        this.f16631g = null;
        this.f16633i = 0;
    }

    public h(short s, byte b, int i2, int i3, String str, String str2, byte[] bArr, int i4, Map<String, String> map, Map<String, String> map2) {
        this.b = (short) 0;
        this.f16628c = (byte) 0;
        this.d = 0;
        this.f16629e = 0;
        this.f16630f = null;
        this.f16631g = null;
        this.f16633i = 0;
        this.b = s;
        this.f16628c = b;
        this.d = i2;
        this.f16629e = i3;
        this.f16630f = str;
        this.f16631g = str2;
        this.f16632h = bArr;
        this.f16633i = i4;
        this.f16634j = map;
        this.f16635k = map2;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f16627n) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.b, "iVersion");
        kVar.a(this.f16628c, "cPacketType");
        kVar.a(this.d, "iMessageType");
        kVar.a(this.f16629e, "iRequestId");
        kVar.a(this.f16630f, "sServantName");
        kVar.a(this.f16631g, "sFuncName");
        kVar.a(this.f16632h, "sBuffer");
        kVar.a(this.f16633i, "iTimeout");
        kVar.a((Map) this.f16634j, AnnoConst.Constructor_Context);
        kVar.a((Map) this.f16635k, "status");
    }

    public boolean equals(Object obj) {
        h hVar = (h) obj;
        return q.b(1, (int) hVar.b) && q.b(1, (int) hVar.f16628c) && q.b(1, hVar.d) && q.b(1, hVar.f16629e) && q.a((Object) 1, (Object) hVar.f16630f) && q.a((Object) 1, (Object) hVar.f16631g) && q.a((Object) 1, (Object) hVar.f16632h) && q.b(1, hVar.f16633i) && q.a((Object) 1, (Object) hVar.f16634j) && q.a((Object) 1, (Object) hVar.f16635k);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void readFrom(m mVar) {
        try {
            this.b = mVar.a(this.b, 1, true);
            this.f16628c = mVar.a(this.f16628c, 2, true);
            this.d = mVar.a(this.d, 3, true);
            this.f16629e = mVar.a(this.f16629e, 4, true);
            this.f16630f = mVar.b(5, true);
            this.f16631g = mVar.b(6, true);
            if (f16625l == null) {
                f16625l = new byte[]{0};
            }
            this.f16632h = mVar.a(f16625l, 7, true);
            this.f16633i = mVar.a(this.f16633i, 8, true);
            if (f16626m == null) {
                HashMap hashMap = new HashMap();
                f16626m = hashMap;
                hashMap.put("", "");
            }
            this.f16634j = (Map) mVar.a((m) f16626m, 9, true);
            if (f16626m == null) {
                HashMap hashMap2 = new HashMap();
                f16626m = hashMap2;
                hashMap2.put("", "");
            }
            this.f16635k = (Map) mVar.a((m) f16626m, 10, true);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("RequestPacket decode error " + i.a(this.f16632h));
            throw new RuntimeException(e2);
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.b, 1);
        nVar.a(this.f16628c, 2);
        nVar.a(this.d, 3);
        nVar.a(this.f16629e, 4);
        nVar.a(this.f16630f, 5);
        nVar.a(this.f16631g, 6);
        nVar.a(this.f16632h, 7);
        nVar.a(this.f16633i, 8);
        nVar.a((Map) this.f16634j, 9);
        nVar.a((Map) this.f16635k, 10);
    }
}
