package com.tencent.mapsdk.core.components.protocol.jce.rtt;

import com.tencent.mapsdk.internal.k;
import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.q;
import g.i.a.a.a;

/* loaded from: classes9.dex */
public final class RttResponse extends a implements Cloneable {
    public static byte[] b;

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ boolean f16192c = true;
    public byte[] result;

    static {
        b = r0;
        byte[] bArr = {0};
    }

    public RttResponse() {
        this.result = null;
    }

    public RttResponse(byte[] bArr) {
        this.result = null;
        this.result = bArr;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "navsns.RttResponse";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f16192c) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        new k(sb, i2).a(this.result, "result");
    }

    @Override // com.tencent.mapsdk.internal.p
    public void displaySimple(StringBuilder sb, int i2) {
        new k(sb, i2).a(this.result, false);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return q.a((Object) this.result, (Object) ((RttResponse) obj).result);
    }

    public byte[] getResult() {
        return this.result;
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
        this.result = mVar.a(b, 0, true);
    }

    public void setResult(byte[] bArr) {
        this.result = bArr;
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.result, 0);
    }
}
