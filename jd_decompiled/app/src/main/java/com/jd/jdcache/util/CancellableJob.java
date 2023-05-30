package com.jd.jdcache.util;

import androidx.annotation.Keep;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jd/jdcache/util/CancellableJob;", "Lcom/jd/jdcache/util/ICancellable;", "", "msg", "", "cancel", "(Ljava/lang/String;)V", "Lkotlinx/coroutines/Job;", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class CancellableJob implements ICancellable {
    private final Job job;

    public CancellableJob(@Nullable Job job) {
        this.job = job;
    }

    @Override // com.jd.jdcache.util.ICancellable
    public void cancel(@Nullable String msg) {
        String str;
        Job job = this.job;
        if (job == null || job.isCompleted()) {
            return;
        }
        if (msg == null || msg.length() == 0) {
            str = "";
        } else {
            str = " Msg: " + msg;
        }
        this.job.cancel(new CancellationException("Job canceled manually." + str));
    }
}
