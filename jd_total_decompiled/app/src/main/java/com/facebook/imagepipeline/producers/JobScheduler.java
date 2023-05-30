package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class JobScheduler {
    static final String QUEUE_TIME_KEY = "queueTime";
    private final Executor mExecutor;
    private final JobRunnable mJobRunnable;
    private final int mMinimumJobIntervalMs;
    private final Runnable mDoJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.1
        @Override // java.lang.Runnable
        public void run() {
            JobScheduler.this.doJob();
        }
    };
    private final Runnable mSubmitJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.2
        @Override // java.lang.Runnable
        public void run() {
            JobScheduler.this.submitJob();
        }
    };
    @VisibleForTesting
    @GuardedBy("this")
    EncodedImage mEncodedImage = null;
    @VisibleForTesting
    @GuardedBy("this")
    int mStatus = 0;
    @VisibleForTesting
    @GuardedBy("this")
    JobState mJobState = JobState.IDLE;
    @VisibleForTesting
    @GuardedBy("this")
    long mJobSubmitTime = 0;
    @VisibleForTesting
    @GuardedBy("this")
    long mJobStartTime = 0;

    /* renamed from: com.facebook.imagepipeline.producers.JobScheduler$3  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState;

        static {
            int[] iArr = new int[JobState.values().length];
            $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState = iArr;
            try {
                iArr[JobState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[JobState.QUEUED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[JobState.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[JobState.RUNNING_AND_PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface JobRunnable {
        void run(EncodedImage encodedImage, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class JobStartExecutorSupplier {
        private static ScheduledExecutorService sJobStarterExecutor;

        JobStartExecutorSupplier() {
        }

        static ScheduledExecutorService get() {
            if (sJobStarterExecutor == null) {
                sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            return sJobStarterExecutor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int i2) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doJob() {
        EncodedImage encodedImage;
        int i2;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            i2 = this.mStatus;
            this.mEncodedImage = null;
            this.mStatus = 0;
            this.mJobState = JobState.RUNNING;
            this.mJobStartTime = uptimeMillis;
        }
        try {
            if (shouldProcess(encodedImage, i2)) {
                this.mJobRunnable.run(encodedImage, i2);
            }
        } finally {
            EncodedImage.closeSafely(encodedImage);
            onJobFinished();
        }
    }

    private void enqueueJob(long j2) {
        Runnable decorateRunnable = FrescoInstrumenter.decorateRunnable(this.mSubmitJobRunnable, "JobScheduler_enqueueJob");
        if (j2 > 0) {
            JobStartExecutorSupplier.get().schedule(decorateRunnable, j2, TimeUnit.MILLISECONDS);
        } else {
            decorateRunnable.run();
        }
    }

    private void onJobFinished() {
        JobState jobState;
        long j2;
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            if (this.mJobState == JobState.RUNNING_AND_PENDING) {
                j2 = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                z = true;
                this.mJobSubmitTime = uptimeMillis;
                jobState = JobState.QUEUED;
            } else {
                jobState = JobState.IDLE;
                j2 = 0;
                z = false;
            }
            this.mJobState = jobState;
        }
        if (z) {
            enqueueJob(j2 - uptimeMillis);
        }
    }

    private static boolean shouldProcess(EncodedImage encodedImage, int i2) {
        return BaseConsumer.isLast(i2) || BaseConsumer.statusHasFlag(i2, 4) || EncodedImage.isValid(encodedImage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitJob() {
        this.mExecutor.execute(FrescoInstrumenter.decorateRunnable(this.mDoJobRunnable, "JobScheduler_submitJob"));
    }

    public void clearJob() {
        EncodedImage encodedImage;
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mStatus = 0;
        }
        EncodedImage.closeSafely(encodedImage);
    }

    public synchronized long getQueuedTime() {
        return this.mJobStartTime - this.mJobSubmitTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean scheduleJob() {
        JobState jobState;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            boolean z = false;
            if (shouldProcess(this.mEncodedImage, this.mStatus)) {
                int i2 = AnonymousClass3.$SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[this.mJobState.ordinal()];
                long j2 = 0;
                if (i2 == 1) {
                    long max = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                    this.mJobSubmitTime = uptimeMillis;
                    jobState = JobState.QUEUED;
                    j2 = max;
                    z = true;
                } else if (i2 != 3) {
                    if (z) {
                        enqueueJob(j2 - uptimeMillis);
                    }
                    return true;
                } else {
                    jobState = JobState.RUNNING_AND_PENDING;
                }
                this.mJobState = jobState;
                if (z) {
                }
                return true;
            }
            return false;
        }
    }

    public boolean updateJob(EncodedImage encodedImage, int i2) {
        EncodedImage encodedImage2;
        if (shouldProcess(encodedImage, i2)) {
            synchronized (this) {
                encodedImage2 = this.mEncodedImage;
                this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
                this.mStatus = i2;
            }
            EncodedImage.closeSafely(encodedImage2);
            return true;
        }
        return false;
    }
}
