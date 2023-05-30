package com.meizu.cloud.pushsdk.f.f.d;

import com.meizu.cloud.pushsdk.f.f.a;
import com.meizu.cloud.pushsdk.f.g.c;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class a extends com.meizu.cloud.pushsdk.f.f.a {

    /* renamed from: j  reason: collision with root package name */
    private static final String f15944j = "a";

    /* renamed from: k  reason: collision with root package name */
    private static ScheduledExecutorService f15945k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.meizu.cloud.pushsdk.f.f.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class RunnableC0766a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.meizu.cloud.pushsdk.f.f.b f15946g;

        RunnableC0766a(a aVar, com.meizu.cloud.pushsdk.f.f.b bVar) {
            this.f15946g = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f15946g.a();
        }
    }

    /* loaded from: classes14.dex */
    class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.meizu.cloud.pushsdk.f.d.b f15947g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f15948h;

        b(com.meizu.cloud.pushsdk.f.d.b bVar, boolean z) {
            this.f15947g = bVar;
            this.f15948h = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.super.d(this.f15947g, this.f15948h);
        }
    }

    public a(a.C0765a c0765a) {
        super(c0765a);
        com.meizu.cloud.pushsdk.f.c.h.b.c(this.f15923f);
        h();
    }

    @Override // com.meizu.cloud.pushsdk.f.f.a
    public void d(com.meizu.cloud.pushsdk.f.d.b bVar, boolean z) {
        com.meizu.cloud.pushsdk.f.c.h.b.d(new b(bVar, z));
    }

    public void h() {
        if (f15945k == null && this.d) {
            c.e(f15944j, "Session checking has been resumed.", new Object[0]);
            com.meizu.cloud.pushsdk.f.f.b bVar = this.f15921c;
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            f15945k = newSingleThreadScheduledExecutor;
            RunnableC0766a runnableC0766a = new RunnableC0766a(this, bVar);
            long j2 = this.f15922e;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(runnableC0766a, j2, j2, this.f15924g);
        }
    }
}
