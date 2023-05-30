package com.jd.cashier.app.jdlibcutter.impl.thread;

import com.jd.cashier.app.jdlibcutter.protocol.thread.IThreadPool;
import com.jingdong.sdk.threadpool.ThreadManager;

/* loaded from: classes13.dex */
public class JDThreadPool implements IThreadPool {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.thread.IThreadPool
    public void pushJob(Runnable runnable) {
        if (runnable != null) {
            try {
                ThreadManager.light().post(runnable);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
