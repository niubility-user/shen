package com.jingdong.sdk.jdcrashreport.e.b;

import com.jingdong.sdk.jdcrashreport.b.p;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class a {
    private final BlockingQueue<String> a;
    private final b b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i2, long j2) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(i2 > 0 ? i2 * 5 : 5);
        this.a = linkedBlockingQueue;
        b bVar = new b(linkedBlockingQueue, i2);
        this.b = bVar;
        com.jingdong.sdk.jdcrashreport.b.d.b(bVar, j2, j2);
    }

    public void a(int i2) {
        this.b.a(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(CrashInfo crashInfo) {
        synchronized (this.a) {
            try {
                this.a.offer(p.a(crashInfo.toString()));
            } catch (Exception unused) {
            }
        }
    }
}
