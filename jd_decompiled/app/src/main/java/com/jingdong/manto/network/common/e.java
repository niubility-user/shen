package com.jingdong.manto.network.common;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes16.dex */
class e extends ResponseBody {
    private final ResponseBody a;
    private BufferedSource b;

    /* renamed from: c  reason: collision with root package name */
    private long f13877c;
    private final b d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends ForwardingSource {
        long a;

        a(Source source) {
            super(source);
            this.a = 0L;
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer buffer, long j2) {
            long read = super.read(buffer, j2);
            int i2 = (read > (-1L) ? 1 : (read == (-1L) ? 0 : -1));
            this.a += i2 != 0 ? read : 0L;
            if (e.this.d != null && e.this.a != null) {
                e.this.d.a(this.a, e.this.a.contentLength(), i2 == 0);
            }
            return read;
        }
    }

    public e(ResponseBody responseBody, b bVar) {
        this.a = responseBody;
        this.d = bVar;
    }

    private Source a(Source source) {
        return new a(source);
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        long contentLength = this.a.contentLength();
        this.f13877c = contentLength;
        return contentLength;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.a.contentType();
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        if (this.b == null) {
            this.b = Okio.buffer(a(this.a.source()));
        }
        return this.b;
    }
}
