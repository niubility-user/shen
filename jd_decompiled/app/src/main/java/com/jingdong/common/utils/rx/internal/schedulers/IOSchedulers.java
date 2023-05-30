package com.jingdong.common.utils.rx.internal.schedulers;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.utils.PersonalSwitchManager;
import com.jingdong.common.utils.rx.PersonalIOScheduler;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/jingdong/common/utils/rx/internal/schedulers/IOSchedulers;", "", "Lrx/Scheduler;", "io", "()Lrx/Scheduler;", "", RemoteMessageConst.Notification.PRIORITY, "(I)Lrx/Scheduler;", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class IOSchedulers {
    public static final IOSchedulers INSTANCE = new IOSchedulers();
    private static final String TAG = "IOSchedulers";

    private IOSchedulers() {
    }

    @JvmStatic
    @NotNull
    public static final Scheduler io() {
        return io(0);
    }

    @JvmStatic
    @NotNull
    public static final Scheduler io(int r3) {
        try {
            if (PersonalSwitchManager.getSwitch(PersonalSwitchManager.RX_JAVA_IO_SCHEDULER_KEY)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "get PersonalIOScheduler");
                }
                return PersonalIOScheduler.INSTANCE.getINSTANCE().setPriority(r3);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "get Schedulers");
            }
            Scheduler io = Schedulers.io();
            Intrinsics.checkExpressionValueIsNotNull(io, "Schedulers.io()");
            return io;
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, "get Schedulers error", th);
            }
            JdCrashReport.postCaughtException(new IllegalArgumentException("IOSchedulers io get error", th));
            Scheduler io2 = Schedulers.io();
            Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
            return io2;
        }
    }
}
