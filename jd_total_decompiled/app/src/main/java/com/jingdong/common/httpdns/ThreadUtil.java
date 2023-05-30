package com.jingdong.common.httpdns;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes5.dex */
public class ThreadUtil {
    public static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() { // from class: com.jingdong.common.httpdns.ThreadUtil.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }
}
