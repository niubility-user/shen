package com.jingdong.jdexreport.record;

import android.content.Context;
import com.jingdong.jdexreport.e.d;
import java.util.Vector;

/* loaded from: classes.dex */
public class a implements Runnable {
    private final com.jingdong.jdexreport.b.a a;
    private boolean b = false;

    /* renamed from: c */
    private JDExReportDbImpl f12615c;

    public a(com.jingdong.jdexreport.b.a aVar, JDExReportDbImpl jDExReportDbImpl, Context context) {
        this.a = aVar;
        this.f12615c = jDExReportDbImpl;
    }

    public void a() {
        this.b = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.b) {
            Vector<d> vector = this.f12615c.recordCacheVec;
            if (vector == null) {
                this.b = true;
            } else {
                synchronized (vector) {
                    while (vector.isEmpty()) {
                        try {
                            vector.wait();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                    int size = vector.size() - 1;
                    d dVar = vector.get(size);
                    long recordNum = this.f12615c.getRecordNum();
                    com.jingdong.jdexreport.a.a.a.a("JDExReport", "[db] add record:" + dVar.b());
                    this.a.a(dVar, recordNum);
                    this.f12615c.incrementRecordNum();
                    vector.remove(size);
                    this.f12615c.judgeLimitAndSendMessage();
                    vector.notifyAll();
                }
            }
        }
    }
}
