package com.tencent.mapsdk.internal;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class d extends f {
    private static final long o = 1;

    public d() {
        h hVar = this.f16486i;
        hVar.b = (short) 2;
        hVar.f16628c = (byte) 0;
        hVar.d = 0;
        hVar.f16633i = 0;
        hVar.f16632h = new byte[0];
        hVar.f16634j = new HashMap();
        this.f16486i.f16635k = new HashMap();
    }

    public void a(byte b) {
        this.f16486i.f16628c = b;
    }

    public void a(Map<String, String> map) {
        this.f16486i.f16634j = map;
    }

    public void a(short s) {
        this.f16486i.b = s;
        if (s == 3) {
            g();
        }
    }

    public void b(Map<String, String> map) {
        this.f16486i.f16635k = map;
    }

    public void c(int i2) {
        this.f16486i.d = i2;
    }

    public void d(int i2) {
        this.f16486i.f16633i = i2;
    }

    public void d(byte[] bArr) {
        this.f16486i.f16632h = bArr;
    }

    public byte[] r() {
        return this.f16486i.f16632h;
    }

    public Map<String, String> s() {
        return this.f16486i.f16634j;
    }

    public int t() {
        return this.f16486i.d;
    }

    public byte u() {
        return this.f16486i.f16628c;
    }

    public int v() {
        String str = this.f16486i.f16635k.get("STATUS_RESULT_CODE");
        if (str != null) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    public String w() {
        String str = this.f16486i.f16635k.get("STATUS_RESULT_DESC");
        return str != null ? str : "";
    }

    public Map<String, String> x() {
        return this.f16486i.f16635k;
    }

    public int y() {
        return this.f16486i.f16633i;
    }

    public short z() {
        return this.f16486i.b;
    }
}
