package com.tencent.open.a;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
class c implements g {
    private String a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f17634c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private String f17635e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, List<String>> f17636f = new HashMap();

    public c(HttpURLConnection httpURLConnection, String str, int i2, int i3, int i4, String str2) {
        Map<String, List<String>> headerFields;
        this.a = "";
        this.b = 0;
        this.f17634c = 0;
        this.d = 0;
        this.f17635e = "";
        this.a = str;
        this.b = i2;
        this.f17634c = i3;
        this.d = i4;
        this.f17635e = str2;
        if (httpURLConnection == null || (headerFields = httpURLConnection.getHeaderFields()) == null) {
            return;
        }
        this.f17636f.putAll(headerFields);
    }

    @Override // com.tencent.open.a.g
    public String a() {
        return this.a;
    }

    @Override // com.tencent.open.a.g
    public int b() {
        return this.b;
    }

    @Override // com.tencent.open.a.g
    public int c() {
        return this.f17634c;
    }

    @Override // com.tencent.open.a.g
    public int d() {
        return this.d;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + hashCode() + "\ncontent = [" + this.a + "]\nresponseSize = " + this.b + "\nrequestSize = " + this.f17634c + "\nresultCode = " + this.d + "\nerrorMsg = " + this.f17635e;
    }
}
