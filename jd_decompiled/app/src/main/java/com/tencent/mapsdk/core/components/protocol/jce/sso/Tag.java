package com.tencent.mapsdk.core.components.protocol.jce.sso;

import com.tencent.mapsdk.internal.k;
import com.tencent.mapsdk.internal.m;
import com.tencent.mapsdk.internal.n;
import com.tencent.mapsdk.internal.q;
import g.i.a.a.a;

/* loaded from: classes9.dex */
public final class Tag extends a implements Cloneable {
    public static byte[] b;

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ boolean f16197c = true;
    public String strId;
    public byte[] value;

    public Tag() {
        this.strId = "";
        this.value = null;
    }

    public Tag(String str, byte[] bArr) {
        this.strId = "";
        this.value = null;
        this.strId = str;
        this.value = bArr;
    }

    @Override // com.tencent.mapsdk.internal.p
    public String className() {
        return "sosomap.Tag";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f16197c) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.mapsdk.internal.p
    public void display(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.strId, "strId");
        kVar.a(this.value, "value");
    }

    @Override // com.tencent.mapsdk.internal.p
    public void displaySimple(StringBuilder sb, int i2) {
        k kVar = new k(sb, i2);
        kVar.a(this.strId, true);
        kVar.a(this.value, false);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Tag tag = (Tag) obj;
        return q.a((Object) this.strId, (Object) tag.strId) && q.a((Object) this.value, (Object) tag.value);
    }

    public byte[] getValue() {
        return this.value;
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
        this.strId = mVar.b(0, true);
        if (b == null) {
            b = r2;
            byte[] bArr = {0};
        }
        this.value = mVar.a(b, 1, true);
    }

    @Override // com.tencent.mapsdk.internal.p
    public void writeTo(n nVar) {
        nVar.a(this.strId, 0);
        nVar.a(this.value, 1);
    }
}
