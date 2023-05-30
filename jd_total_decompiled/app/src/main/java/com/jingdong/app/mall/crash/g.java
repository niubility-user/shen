package com.jingdong.app.mall.crash;

import com.jingdong.app.mall.safemode.i;
import com.jingdong.common.utils.ProcessUtil;
import java.lang.Thread;

/* loaded from: classes3.dex */
public class g implements Thread.UncaughtExceptionHandler {
    public static g a() {
        return new g();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (ProcessUtil.isMainProcess() && ProcessUtil.isForeground() && !i.g().h()) {
            i.g().d(th.getStackTrace().toString());
        }
    }
}
