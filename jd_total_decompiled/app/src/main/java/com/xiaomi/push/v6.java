package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.l;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class v6 extends l.b {
    final /* synthetic */ Context a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v6(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.l.b
    public void b() {
        Object obj;
        ArrayList arrayList;
        List list;
        List list2;
        obj = u6.d;
        synchronized (obj) {
            list = u6.f19259e;
            arrayList = new ArrayList(list);
            list2 = u6.f19259e;
            list2.clear();
        }
        u6.o(this.a, arrayList);
    }
}
