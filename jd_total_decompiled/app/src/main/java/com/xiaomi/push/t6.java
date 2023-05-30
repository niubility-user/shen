package com.xiaomi.push;

import com.xiaomi.push.l;

/* loaded from: classes11.dex */
class t6 extends l.b {
    final /* synthetic */ Runnable a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t6(Runnable runnable) {
        this.a = runnable;
    }

    @Override // com.xiaomi.push.l.b
    public void b() {
        this.a.run();
    }
}
