package com.meizu.cloud.pushsdk.f.e;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes14.dex */
public class c implements d {
    private final int a;
    private final AtomicLong b = new AtomicLong(0);

    /* renamed from: c  reason: collision with root package name */
    private final Map<Long, byte[]> f15919c = new ConcurrentHashMap();
    private final List<Long> d = new CopyOnWriteArrayList();

    public c(int i2) {
        this.a = i2;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public void a(com.meizu.cloud.pushsdk.f.b.a aVar) {
        c(aVar);
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public boolean a() {
        return true;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public boolean a(long j2) {
        return this.d.remove(Long.valueOf(j2)) && this.f15919c.remove(Long.valueOf(j2)) != null;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public long b() {
        return this.d.size();
    }

    public long c(com.meizu.cloud.pushsdk.f.b.a aVar) {
        byte[] f2 = a.f(aVar.a());
        long andIncrement = this.b.getAndIncrement();
        this.d.add(Long.valueOf(andIncrement));
        this.f15919c.put(Long.valueOf(andIncrement), f2);
        return andIncrement;
    }

    @Override // com.meizu.cloud.pushsdk.f.e.d
    public com.meizu.cloud.pushsdk.f.c.c c() {
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        int b = (int) b();
        int i2 = this.a;
        if (b > i2) {
            b = i2;
        }
        for (int i3 = 0; i3 < b; i3++) {
            Long l2 = this.d.get(i3);
            if (l2 != null) {
                com.meizu.cloud.pushsdk.f.b.c cVar = new com.meizu.cloud.pushsdk.f.b.c();
                cVar.c(a.e(this.f15919c.get(l2)));
                com.meizu.cloud.pushsdk.f.g.c.g("MemoryStore", " current key " + l2 + " payload " + cVar, new Object[0]);
                linkedList.add(l2);
                arrayList.add(cVar);
            }
        }
        return new com.meizu.cloud.pushsdk.f.c.c(arrayList, linkedList);
    }
}
