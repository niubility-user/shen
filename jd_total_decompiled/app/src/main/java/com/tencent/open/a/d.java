package com.tencent.open.a;

import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes9.dex */
class d implements g {
    private Response a;
    private String b = null;

    /* renamed from: c  reason: collision with root package name */
    private int f17637c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f17638e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Response response, int i2) {
        this.a = response;
        this.d = i2;
        this.f17637c = response.code();
        ResponseBody body = this.a.body();
        if (body != null) {
            this.f17638e = (int) body.contentLength();
        } else {
            this.f17638e = 0;
        }
    }

    @Override // com.tencent.open.a.g
    public String a() throws IOException {
        if (this.b == null) {
            ResponseBody body = this.a.body();
            if (body != null) {
                this.b = body.string();
            }
            if (this.b == null) {
                this.b = "";
            }
        }
        return this.b;
    }

    @Override // com.tencent.open.a.g
    public int b() {
        return this.f17638e;
    }

    @Override // com.tencent.open.a.g
    public int c() {
        return this.d;
    }

    @Override // com.tencent.open.a.g
    public int d() {
        return this.f17637c;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + hashCode() + this.b + this.f17637c + this.d + this.f17638e;
    }
}
