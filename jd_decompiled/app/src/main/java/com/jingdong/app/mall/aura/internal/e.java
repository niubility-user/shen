package com.jingdong.app.mall.aura.internal;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes19.dex */
public class e implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private Queue<Runnable> f7940g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f7941h = false;

    public e(String str) {
    }

    public void a(String str) {
    }

    public synchronized void b(Runnable runnable) {
        if (this.f7940g == null) {
            this.f7940g = new ConcurrentLinkedQueue();
        }
        this.f7940g.add(runnable);
        a("Add to mWorkQueue ");
        if (!this.f7941h) {
            a("Start thread");
            this.f7941h = true;
            new Thread(this).start();
        } else {
            a("Thread is already running");
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable poll;
        a("Thread.run()");
        while (true) {
            synchronized (this) {
                Queue<Runnable> queue = this.f7940g;
                poll = queue != null ? queue.poll() : null;
                if (poll == null) {
                    a("Runnable is null, exit thread");
                    this.f7941h = false;
                    return;
                }
                a("Runnable not null");
            }
            try {
                a("Start work");
                poll.run();
                a("End work");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
