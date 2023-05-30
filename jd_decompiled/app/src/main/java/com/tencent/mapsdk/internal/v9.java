package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class v9 extends m9 {
    public String a;

    public v9() {
    }

    public v9(String str) {
        this.a = str;
    }

    @Override // com.tencent.mapsdk.internal.m9
    public int a() {
        return b().length;
    }

    @Override // com.tencent.mapsdk.internal.m9
    public void a(byte[] bArr) {
        this.a = new String(bArr);
    }

    @Override // com.tencent.mapsdk.internal.m9
    public byte[] b() {
        String str = this.a;
        return str != null ? str.getBytes() : new byte[0];
    }

    public String toString() {
        return "StringData{strData='" + this.a + "'}";
    }
}
