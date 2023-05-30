package com.tencent.mapsdk.internal;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class f extends e {

    /* renamed from: l  reason: collision with root package name */
    public static final int f16483l = 4;

    /* renamed from: m  reason: collision with root package name */
    public static HashMap<String, byte[]> f16484m;

    /* renamed from: n  reason: collision with root package name */
    public static HashMap<String, HashMap<String, byte[]>> f16485n;

    /* renamed from: i  reason: collision with root package name */
    public h f16486i;

    /* renamed from: j  reason: collision with root package name */
    private int f16487j;

    /* renamed from: k  reason: collision with root package name */
    private int f16488k;

    public f() {
        h hVar = new h();
        this.f16486i = hVar;
        this.f16487j = 0;
        this.f16488k = 0;
        hVar.b = (short) 2;
    }

    public f(boolean z) {
        h hVar = new h();
        this.f16486i = hVar;
        this.f16487j = 0;
        this.f16488k = 0;
        if (z) {
            g();
        } else {
            hVar.b = (short) 2;
        }
    }

    private void p() {
        m mVar = new m(this.f16486i.f16632h);
        mVar.a(this.d);
        if (f16485n == null) {
            f16485n = new HashMap<>();
            HashMap<String, byte[]> hashMap = new HashMap<>();
            hashMap.put("", new byte[0]);
            f16485n.put("", hashMap);
        }
        this.a = mVar.a((Map) f16485n, 0, false);
        this.b = new HashMap<>();
    }

    private void q() {
        m mVar = new m(this.f16486i.f16632h);
        mVar.a(this.d);
        if (f16484m == null) {
            HashMap<String, byte[]> hashMap = new HashMap<>();
            f16484m = hashMap;
            hashMap.put("", new byte[0]);
        }
        this.f16434f = mVar.a((Map) f16484m, 0, false);
    }

    public void a(int i2) {
        this.f16488k = i2;
    }

    public void a(m mVar) {
        this.f16486i.readFrom(mVar);
    }

    public void a(n nVar) {
        this.f16486i.writeTo(nVar);
    }

    @Override // com.tencent.mapsdk.internal.e, com.tencent.mapsdk.internal.c
    public <T> void a(String str, T t) {
        if (!str.startsWith(OrderISVUtil.MONEY_DECIMAL)) {
            super.a(str, (String) t);
            return;
        }
        throw new IllegalArgumentException("put name can not startwith . , now is " + str);
    }

    public void a(StringBuilder sb, int i2) {
        this.f16486i.display(sb, i2);
    }

    @Override // com.tencent.mapsdk.internal.e, com.tencent.mapsdk.internal.c
    public void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        allocate.put(bArr2).flip();
        this.f16487j = allocate.getInt();
        try {
            m mVar = new m(bArr, 4);
            mVar.a(this.d);
            this.f16486i.readFrom(mVar);
            if (this.f16486i.b == 3) {
                q();
                return;
            }
            this.f16434f = null;
            p();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void b(int i2) {
        this.f16486i.f16629e = i2;
    }

    @Override // com.tencent.mapsdk.internal.e
    public void b(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        allocate.put(bArr2).flip();
        this.f16487j = allocate.getInt();
        try {
            m mVar = new m(bArr, 4);
            mVar.a(this.d);
            this.f16486i.readFrom(mVar);
            p();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.tencent.mapsdk.internal.e, com.tencent.mapsdk.internal.c
    public byte[] b() {
        h hVar = this.f16486i;
        if (hVar.b == 2) {
            String str = hVar.f16630f;
            if (str == null || str.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            String str2 = this.f16486i.f16631g;
            if (str2 == null || str2.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (hVar.f16630f == null) {
                hVar.f16630f = "";
            }
            if (hVar.f16631g == null) {
                hVar.f16631g = "";
            }
        }
        n nVar = new n(0);
        nVar.a(this.d);
        short s = this.f16486i.b;
        nVar.a((Map) ((s == 2 || s == 1) ? this.a : this.f16434f), 0);
        this.f16486i.f16632h = q.b(nVar.a());
        n nVar2 = new n(0);
        nVar2.a(this.d);
        this.f16486i.writeTo(nVar2);
        byte[] b = q.b(nVar2.a());
        int length = b.length + 4;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length).put(b).flip();
        return allocate.array();
    }

    @Override // com.tencent.mapsdk.internal.e
    public void c(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            m mVar = new m(bArr, 4);
            mVar.a(this.d);
            this.f16486i.readFrom(mVar);
            q();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void f(String str) {
        this.f16486i.f16631g = str;
    }

    @Override // com.tencent.mapsdk.internal.e
    public void g() {
        super.g();
        this.f16486i.b = (short) 3;
    }

    public void g(String str) {
        this.f16486i.f16630f = str;
    }

    public byte[] h() {
        n nVar = new n(0);
        nVar.a(this.d);
        nVar.a((Map) this.a, 0);
        byte[] b = q.b(nVar.a());
        n nVar2 = new n(0);
        nVar2.a(this.d);
        nVar2.a(this.f16486i.b, 1);
        nVar2.a(this.f16486i.f16628c, 2);
        nVar2.a(this.f16486i.f16629e, 3);
        nVar2.a(this.f16486i.d, 4);
        nVar2.a(this.f16488k, 5);
        nVar2.a(b, 6);
        nVar2.a((Map) this.f16486i.f16635k, 7);
        return q.b(nVar2.a());
    }

    public f i() {
        f fVar = new f();
        fVar.b(n());
        fVar.g(o());
        fVar.f(j());
        fVar.b(this.d);
        fVar.f16486i.b = this.f16486i.b;
        return fVar;
    }

    public String j() {
        return this.f16486i.f16631g;
    }

    public int k() {
        return this.f16488k;
    }

    public int l() {
        return this.f16486i.b;
    }

    public int m() {
        return this.f16487j;
    }

    public int n() {
        return this.f16486i.f16629e;
    }

    public String o() {
        return this.f16486i.f16630f;
    }
}
