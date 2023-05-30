package com.meizu.cloud.pushsdk.e.f;

import com.meizu.cloud.pushsdk.e.d.j;
import com.meizu.cloud.pushsdk.e.h.f;
import com.meizu.cloud.pushsdk.e.h.g;
import com.meizu.cloud.pushsdk.e.h.l;
import java.io.IOException;

/* loaded from: classes14.dex */
public class b extends j {
    private final j a;
    private com.meizu.cloud.pushsdk.e.h.c b;

    /* renamed from: c  reason: collision with root package name */
    private d f15834c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends f {

        /* renamed from: h  reason: collision with root package name */
        long f15835h;

        /* renamed from: i  reason: collision with root package name */
        long f15836i;

        a(l lVar) {
            super(lVar);
            this.f15835h = 0L;
            this.f15836i = 0L;
        }

        @Override // com.meizu.cloud.pushsdk.e.h.f, com.meizu.cloud.pushsdk.e.h.l
        public void c(com.meizu.cloud.pushsdk.e.h.b bVar, long j2) throws IOException {
            super.c(bVar, j2);
            if (this.f15836i == 0) {
                this.f15836i = b.this.a();
            }
            this.f15835h += j2;
            if (b.this.f15834c != null) {
                b.this.f15834c.obtainMessage(1, new com.meizu.cloud.pushsdk.e.g.a(this.f15835h, this.f15836i)).sendToTarget();
            }
        }
    }

    public b(j jVar, com.meizu.cloud.pushsdk.e.e.a aVar) {
        this.a = jVar;
        if (aVar != null) {
            this.f15834c = new d(aVar);
        }
    }

    private l i(l lVar) {
        return new a(lVar);
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public long a() throws IOException {
        return this.a.a();
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public void f(com.meizu.cloud.pushsdk.e.h.c cVar) throws IOException {
        if (this.b == null) {
            this.b = g.a(i(cVar));
        }
        this.a.f(this.b);
        this.b.flush();
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public com.meizu.cloud.pushsdk.e.d.g g() {
        return this.a.g();
    }
}
