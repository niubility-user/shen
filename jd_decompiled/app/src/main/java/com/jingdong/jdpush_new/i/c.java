package com.jingdong.jdpush_new.i;

import com.jingdong.jdpush_new.j.g;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class c implements com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> {
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    private HashMap<Short, com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b>> a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.jdpush_new.j.d f12849g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.jdpush_new.g.b f12850h;

        a(c cVar, com.jingdong.jdpush_new.j.d dVar, com.jingdong.jdpush_new.g.b bVar) {
            this.f12849g = dVar;
            this.f12850h = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f12849g.accept(this.f12850h);
        }
    }

    @Override // com.jingdong.jdpush_new.j.d
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void accept(com.jingdong.jdpush_new.g.b bVar) {
        g.c("<--- " + bVar.toString());
        com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> dVar = this.a.get(Short.valueOf(bVar.getCommand()));
        if (dVar != null) {
            b.execute(new a(this, dVar, bVar));
            return;
        }
        g.c("no handler for msg :" + bVar);
    }

    public c b(Short sh, com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> dVar) {
        this.a.put(sh, dVar);
        return this;
    }
}
