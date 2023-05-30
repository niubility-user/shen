package com.xiaomi.push;

import com.xiaomi.push.i;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class k extends i.b {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ i f18788h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(i iVar, i.a aVar) {
        super(aVar);
        this.f18788h = iVar;
    }

    @Override // com.xiaomi.push.i.b
    void b() {
        Object obj;
        Map map;
        obj = this.f18788h.f18729c;
        synchronized (obj) {
            map = this.f18788h.b;
            map.remove(this.f18730g.b());
        }
    }
}
