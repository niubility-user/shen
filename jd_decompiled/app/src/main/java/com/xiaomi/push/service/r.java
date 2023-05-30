package com.xiaomi.push.service;

import com.xiaomi.push.c5;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class r implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ List f19173g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f19174h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(List list, boolean z) {
        this.f19173g = list;
        this.f19174h = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean f2;
        boolean f3;
        f2 = q.f("www.baidu.com:80");
        Iterator it = this.f19173g.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            if (!f2) {
                f3 = q.f(str);
                if (!f3) {
                    f2 = false;
                    if (!f2 && !this.f19174h) {
                        break;
                    }
                }
            }
            f2 = true;
            if (!f2) {
            }
        }
        c5.b(f2 ? 1 : 2);
    }
}
