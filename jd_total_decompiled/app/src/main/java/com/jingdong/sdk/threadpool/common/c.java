package com.jingdong.sdk.threadpool.common;

import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes10.dex */
public class c implements RejectedExecutionHandler {
    @Override // java.util.concurrent.RejectedExecutionHandler
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        LogUtil.e("  rejected:: " + Thread.currentThread() + "   " + runnable.toString(), "runnable class name::" + runnable.getClass().getName());
        ThreadManager.getRejectedHandler().post(runnable);
    }
}
