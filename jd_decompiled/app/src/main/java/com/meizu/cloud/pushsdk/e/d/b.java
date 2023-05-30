package com.meizu.cloud.pushsdk.e.d;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class b extends j {

    /* renamed from: c  reason: collision with root package name */
    private static final g f15797c = g.a("application/x-www-form-urlencoded");
    private final List<String> a;
    private final List<String> b;

    /* renamed from: com.meizu.cloud.pushsdk.e.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static final class C0759b {
        private final List<String> a = new ArrayList();
        private final List<String> b = new ArrayList();

        public C0759b a(String str, String str2) {
            this.a.add(f.g(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.b.add(f.g(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        public b b() {
            return new b(this.a, this.b);
        }

        public C0759b c(String str, String str2) {
            this.a.add(f.g(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.b.add(f.g(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }
    }

    private b(List<String> list, List<String> list2) {
        this.a = m.d(list);
        this.b = m.d(list2);
    }

    private long h(com.meizu.cloud.pushsdk.e.h.c cVar, boolean z) {
        com.meizu.cloud.pushsdk.e.h.b bVar = z ? new com.meizu.cloud.pushsdk.e.h.b() : cVar.c();
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                bVar.j(38);
            }
            bVar.l(this.a.get(i2));
            bVar.j(61);
            bVar.l(this.b.get(i2));
        }
        if (z) {
            long A = bVar.A();
            bVar.u();
            return A;
        }
        return 0L;
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public long a() {
        return h(null, true);
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public void f(com.meizu.cloud.pushsdk.e.h.c cVar) {
        h(cVar, false);
    }

    @Override // com.meizu.cloud.pushsdk.e.d.j
    public g g() {
        return f15797c;
    }
}
