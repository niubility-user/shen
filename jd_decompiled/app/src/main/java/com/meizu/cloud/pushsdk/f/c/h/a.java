package com.meizu.cloud.pushsdk.f.c.h;

import com.meizu.cloud.pushsdk.e.d.i;
import com.meizu.cloud.pushsdk.f.c.a;
import com.meizu.cloud.pushsdk.f.c.e;
import com.meizu.cloud.pushsdk.f.c.f;
import com.meizu.cloud.pushsdk.f.c.g;
import com.meizu.cloud.pushsdk.f.e.d;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes14.dex */
public class a extends com.meizu.cloud.pushsdk.f.c.a {
    private final String q;
    private d r;
    private int s;

    /* renamed from: com.meizu.cloud.pushsdk.f.c.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class RunnableC0762a implements Runnable {
        RunnableC0762a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((com.meizu.cloud.pushsdk.f.c.a) a.this).p.compareAndSet(false, true)) {
                a.this.t();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Callable<Integer> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ i f15890g;

        b(i iVar) {
            this.f15890g = iVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Integer call() {
            return Integer.valueOf(a.this.a(this.f15890g));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements Callable<Boolean> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Long f15892g;

        c(Long l2) {
            this.f15892g = l2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            return Boolean.valueOf(a.this.r.a(this.f15892g.longValue()));
        }
    }

    public a(a.C0761a c0761a) {
        super(c0761a);
        String simpleName = a.class.getSimpleName();
        this.q = simpleName;
        com.meizu.cloud.pushsdk.f.e.a aVar = new com.meizu.cloud.pushsdk.f.e.a(this.f15862c, this.f15869k);
        this.r = aVar;
        if (aVar.a()) {
            return;
        }
        this.r = new com.meizu.cloud.pushsdk.f.e.c(this.f15869k);
        com.meizu.cloud.pushsdk.f.g.c.f(simpleName, "init memory store", new Object[0]);
    }

    private LinkedList<g> m(LinkedList<e> linkedList) {
        LinkedList<g> linkedList2 = new LinkedList<>();
        LinkedList linkedList3 = new LinkedList();
        Iterator<e> it = linkedList.iterator();
        while (it.hasNext()) {
            linkedList3.add(com.meizu.cloud.pushsdk.f.c.h.b.b(q(it.next().b())));
        }
        com.meizu.cloud.pushsdk.f.g.c.e(this.q, "Request Futures: %s", Integer.valueOf(linkedList3.size()));
        for (int i2 = 0; i2 < linkedList3.size(); i2++) {
            int i3 = -1;
            try {
                i3 = ((Integer) ((Future) linkedList3.get(i2)).get(5L, TimeUnit.SECONDS)).intValue();
            } catch (InterruptedException e2) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Request Future was interrupted: %s", e2.getMessage());
            } catch (ExecutionException e3) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Request Future failed: %s", e3.getMessage());
            } catch (TimeoutException e4) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Request Future had a timeout: %s", e4.getMessage());
            }
            if (linkedList.get(i2).c()) {
                linkedList2.add(new g(true, linkedList.get(i2).a()));
            } else {
                linkedList2.add(new g(i(i3), linkedList.get(i2).a()));
            }
        }
        return linkedList2;
    }

    private Callable<Boolean> n(Long l2) {
        return new c(l2);
    }

    private LinkedList<Boolean> p(LinkedList<Long> linkedList) {
        boolean z;
        LinkedList<Boolean> linkedList2 = new LinkedList<>();
        LinkedList linkedList3 = new LinkedList();
        Iterator<Long> it = linkedList.iterator();
        while (it.hasNext()) {
            linkedList3.add(com.meizu.cloud.pushsdk.f.c.h.b.b(n(it.next())));
        }
        com.meizu.cloud.pushsdk.f.g.c.e(this.q, "Removal Futures: %s", Integer.valueOf(linkedList3.size()));
        for (int i2 = 0; i2 < linkedList3.size(); i2++) {
            try {
                z = ((Boolean) ((Future) linkedList3.get(i2)).get(5L, TimeUnit.SECONDS)).booleanValue();
            } catch (InterruptedException e2) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Removal Future was interrupted: %s", e2.getMessage());
                z = false;
                linkedList2.add(Boolean.valueOf(z));
            } catch (ExecutionException e3) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Removal Future failed: %s", e3.getMessage());
                z = false;
                linkedList2.add(Boolean.valueOf(z));
            } catch (TimeoutException e4) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Removal Future had a timeout: %s", e4.getMessage());
                z = false;
                linkedList2.add(Boolean.valueOf(z));
            }
            linkedList2.add(Boolean.valueOf(z));
        }
        return linkedList2;
    }

    private Callable<Integer> q(i iVar) {
        return new b(iVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (com.meizu.cloud.pushsdk.f.g.e.j(this.f15862c)) {
            if (this.r.b() > 0) {
                this.s = 0;
                LinkedList<g> m2 = m(d(this.r.c()));
                com.meizu.cloud.pushsdk.f.g.c.g(this.q, "Processing emitter results.", new Object[0]);
                LinkedList<Long> linkedList = new LinkedList<>();
                Iterator<g> it = m2.iterator();
                int i2 = 0;
                int i3 = 0;
                while (it.hasNext()) {
                    g next = it.next();
                    if (next.b()) {
                        linkedList.addAll(next.a());
                        i2 += next.a().size();
                    } else {
                        i3 += next.a().size();
                        com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Request sending failed but we will retry later.", new Object[0]);
                    }
                }
                p(linkedList);
                com.meizu.cloud.pushsdk.f.g.c.e(this.q, "Success Count: %s", Integer.valueOf(i2));
                com.meizu.cloud.pushsdk.f.g.c.e(this.q, "Failure Count: %s", Integer.valueOf(i3));
                f fVar = this.f15863e;
                if (fVar != null) {
                    if (i3 != 0) {
                        fVar.a(i2, i3);
                    } else {
                        fVar.a(i2);
                    }
                }
                if (i3 > 0 && i2 == 0) {
                    if (com.meizu.cloud.pushsdk.f.g.e.j(this.f15862c)) {
                        com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Ensure collector path is valid: %s", k());
                    }
                    com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter loop stopping: failures.", new Object[0]);
                }
            } else {
                int i4 = this.s;
                if (i4 >= this.f15868j) {
                    com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter loop stopping: empty limit reached.", new Object[0]);
                    this.p.compareAndSet(true, false);
                    f fVar2 = this.f15863e;
                    if (fVar2 != null) {
                        fVar2.a(true);
                        return;
                    }
                    return;
                }
                this.s = i4 + 1;
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter database empty: " + this.s, new Object[0]);
                try {
                    this.f15872n.sleep(this.f15867i);
                } catch (InterruptedException e2) {
                    com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter thread sleep interrupted: " + e2.toString(), new Object[0]);
                }
            }
            t();
            return;
        }
        com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter loop stopping: emitter offline.", new Object[0]);
        this.p.compareAndSet(true, false);
    }

    @Override // com.meizu.cloud.pushsdk.f.c.a
    public void h(com.meizu.cloud.pushsdk.f.b.a aVar, boolean z) {
        this.r.a(aVar);
        com.meizu.cloud.pushsdk.f.g.c.f(this.q, "isRunning " + this.p + " attemptEmit " + z, new Object[0]);
        if (!z) {
            try {
                this.f15872n.sleep(1L);
            } catch (InterruptedException e2) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.q, "Emitter add thread sleep interrupted: " + e2.toString(), new Object[0]);
            }
        }
        if (this.p.compareAndSet(false, true)) {
            t();
        }
    }

    @Override // com.meizu.cloud.pushsdk.f.c.a
    public void j() {
        com.meizu.cloud.pushsdk.f.c.h.b.d(new RunnableC0762a());
    }
}
