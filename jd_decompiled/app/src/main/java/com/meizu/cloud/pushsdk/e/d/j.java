package com.meizu.cloud.pushsdk.e.d;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* loaded from: classes14.dex */
public abstract class j {

    /* loaded from: classes14.dex */
    public class a extends j {
        final /* synthetic */ g a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ byte[] f15827c;
        final /* synthetic */ int d;

        a(g gVar, int i2, byte[] bArr, int i3) {
            this.a = gVar;
            this.b = i2;
            this.f15827c = bArr;
            this.d = i3;
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public long a() {
            return this.b;
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public void f(com.meizu.cloud.pushsdk.e.h.c cVar) throws IOException {
            cVar.a(this.f15827c, this.d, this.b);
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public g g() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends j {
        final /* synthetic */ g a;
        final /* synthetic */ File b;

        b(g gVar, File file) {
            this.a = gVar;
            this.b = file;
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public long a() {
            return this.b.length();
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public void f(com.meizu.cloud.pushsdk.e.h.c cVar) throws IOException {
            com.meizu.cloud.pushsdk.e.h.m mVar = null;
            try {
                mVar = com.meizu.cloud.pushsdk.e.h.g.e(this.b);
                cVar.b(mVar);
            } finally {
                m.f(mVar);
            }
        }

        @Override // com.meizu.cloud.pushsdk.e.d.j
        public g g() {
            return this.a;
        }
    }

    public static j b(g gVar, File file) {
        if (file != null) {
            return new b(gVar, file);
        }
        throw new NullPointerException("content == null");
    }

    public static j c(g gVar, String str) {
        Charset charset = m.a;
        if (gVar != null) {
            Charset b2 = gVar.b();
            if (b2 == null) {
                gVar = g.a(gVar + "; charset=utf-8");
            } else {
                charset = b2;
            }
        }
        return d(gVar, str.getBytes(charset));
    }

    public static j d(g gVar, byte[] bArr) {
        return e(gVar, bArr, 0, bArr.length);
    }

    public static j e(g gVar, byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            m.e(bArr.length, i2, i3);
            return new a(gVar, i3, bArr, i2);
        }
        throw new NullPointerException("content == null");
    }

    public abstract long a() throws IOException;

    public abstract void f(com.meizu.cloud.pushsdk.e.h.c cVar) throws IOException;

    public abstract g g();
}
