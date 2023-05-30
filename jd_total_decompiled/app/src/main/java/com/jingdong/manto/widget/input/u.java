package com.jingdong.manto.widget.input;

import androidx.collection.ArrayMap;

/* loaded from: classes16.dex */
public final class u {
    private static final u b = new u();
    private final ArrayMap<com.jingdong.manto.q.r, Integer> a = new ArrayMap<>();

    public static u a() {
        return b;
    }

    public final void a(com.jingdong.manto.q.r rVar) {
        if (rVar != null) {
            Integer num = this.a.get(rVar);
            if (num == null) {
                num = 0;
            }
            this.a.put(rVar, Integer.valueOf(num.intValue() + 1));
            rVar.setFocusable(false);
            rVar.setFocusableInTouchMode(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(com.jingdong.manto.q.r rVar) {
        if (rVar != null) {
            this.a.remove(rVar);
            rVar.setFocusable(true);
            rVar.setFocusableInTouchMode(true);
        }
    }

    public final void c(com.jingdong.manto.q.r rVar) {
        if (rVar != null) {
            if (this.a.get(rVar) != null) {
                Integer valueOf = Integer.valueOf(r0.intValue() - 1);
                if (valueOf.intValue() > 0) {
                    this.a.put(rVar, valueOf);
                    return;
                }
            }
            b(rVar);
        }
    }
}
