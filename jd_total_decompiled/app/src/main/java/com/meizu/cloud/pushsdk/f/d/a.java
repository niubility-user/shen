package com.meizu.cloud.pushsdk.f.d;

import com.meizu.cloud.pushsdk.f.b.c;
import com.meizu.cloud.pushsdk.f.g.d;
import com.meizu.cloud.pushsdk.f.g.e;
import com.meizu.cloud.pushsdk.notification.model.NotificationStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private final List<com.meizu.cloud.pushsdk.f.b.b> a;
    private final long b;

    /* renamed from: c  reason: collision with root package name */
    private final String f15894c;

    /* renamed from: com.meizu.cloud.pushsdk.f.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static abstract class AbstractC0763a<T extends AbstractC0763a<T>> {
        private List<com.meizu.cloud.pushsdk.f.b.b> a = new LinkedList();
        private long b = System.currentTimeMillis();

        /* renamed from: c  reason: collision with root package name */
        private String f15895c = e.c();

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract T a();

        public T b(long j2) {
            this.b = j2;
            return a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(AbstractC0763a<?> abstractC0763a) {
        d.a(((AbstractC0763a) abstractC0763a).a);
        d.a(((AbstractC0763a) abstractC0763a).f15895c);
        d.c(!((AbstractC0763a) abstractC0763a).f15895c.isEmpty(), "eventId cannot be empty");
        this.a = ((AbstractC0763a) abstractC0763a).a;
        this.b = ((AbstractC0763a) abstractC0763a).b;
        this.f15894c = ((AbstractC0763a) abstractC0763a).f15895c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c a(c cVar) {
        cVar.a(NotificationStyle.EXPANDABLE_IMAGE_URL, b());
        cVar.a("ts", Long.toString(d()));
        return cVar;
    }

    public String b() {
        return this.f15894c;
    }

    public List<com.meizu.cloud.pushsdk.f.b.b> c() {
        return new ArrayList(this.a);
    }

    public long d() {
        return this.b;
    }
}
