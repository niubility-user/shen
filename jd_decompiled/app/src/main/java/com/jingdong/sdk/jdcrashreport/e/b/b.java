package com.jingdong.sdk.jdcrashreport.e.b;

import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.p;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class b implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private final Queue<String> f14918g;

    /* renamed from: h  reason: collision with root package name */
    private int f14919h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Queue<String> queue, int i2) {
        this.f14918g = queue;
        this.f14919h = Math.max(0, i2);
    }

    public void a(int i2) {
        this.f14919h = Math.max(0, i2);
    }

    @Override // java.lang.Runnable
    public void run() {
        LinkedList linkedList = new LinkedList();
        synchronized (this.f14918g) {
            if (this.f14918g.isEmpty()) {
                return;
            }
            for (int i2 = 0; i2 < this.f14919h; i2++) {
                String poll = this.f14918g.poll();
                if (poll != null) {
                    try {
                        linkedList.add(CrashInfo.parse(new JSONObject(p.b(poll))));
                    } catch (Exception e2) {
                        r.c("JDCrashReport", "parse crash info from consumer queue failed.", e2);
                    }
                }
                if (this.f14918g.isEmpty()) {
                    break;
                }
            }
            j.e(linkedList);
        }
    }
}
