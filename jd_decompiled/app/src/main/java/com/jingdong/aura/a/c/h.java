package com.jingdong.aura.a.c;

/* loaded from: classes4.dex */
public class h implements l.b.a.f {
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("FrameworkLifecycleHandler");

    @Override // l.b.a.f
    public void a(l.b.a.c cVar) {
        int type = cVar.getType();
        if (type != 0) {
            if (type != 1) {
                a.c("unspported event type " + cVar.getType());
                return;
            }
            a();
        }
    }

    private void a() {
        long currentTimeMillis = System.currentTimeMillis();
        l.a.registerActivityLifecycleCallbacks(new com.jingdong.aura.core.shadow.a());
        try {
            com.jingdong.aura.core.runing.resource.a.a(l.a, l.d, null);
        } catch (Throwable th) {
            a.a("Failed to newDelegateResources", th);
        }
        a.a("started() spend " + (System.currentTimeMillis() - currentTimeMillis) + " milliseconds");
    }
}
