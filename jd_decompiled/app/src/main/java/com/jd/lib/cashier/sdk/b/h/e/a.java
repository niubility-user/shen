package com.jd.lib.cashier.sdk.b.h.e;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public abstract class a<B extends FragmentActivity> {
    private static final String b;
    private final B a;

    /* renamed from: com.jd.lib.cashier.sdk.b.h.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    static final class RunnableC0102a implements Runnable {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.core.utils.a f2874h;

        RunnableC0102a(com.jd.lib.cashier.sdk.core.utils.a aVar) {
            this.f2874h = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> e2 = a.this.e();
            if (e2.contains(this.f2874h)) {
                return;
            }
            e2.clear();
            e2.put(this.f2874h);
        }
    }

    /* loaded from: classes14.dex */
    static final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.jd.lib.cashier.sdk.core.utils.a aVar = (com.jd.lib.cashier.sdk.core.utils.a) CollectionsKt.first(a.this.e());
            if (aVar instanceof com.jd.lib.cashier.sdk.core.utils.a) {
                aVar.a(a.this.a);
                a.this.e().remove(aVar);
            }
        }
    }

    static {
        String simpleName = a.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "AbsTaskOperator::class.java.simpleName");
        b = simpleName;
    }

    public a(@NotNull B b2) {
        this.a = b2;
    }

    public void b(@NotNull com.jd.lib.cashier.sdk.core.utils.a aVar) {
        if (g0.a(this.a)) {
            this.a.runOnUiThread(new RunnableC0102a(aVar));
        }
    }

    public void c() {
        e().clear();
    }

    public void d() {
        try {
            if (g0.a(this.a)) {
                this.a.runOnUiThread(new b());
            }
        } catch (Exception e2) {
            r.b(b, "consumeReFreshTask e = " + e2);
        }
    }

    @NotNull
    public abstract LinkedBlockingQueue<com.jd.lib.cashier.sdk.core.utils.a> e();
}
