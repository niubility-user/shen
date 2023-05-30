package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class PendingJob {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected PendingJob(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(PendingJob pendingJob) {
        if (pendingJob == null) {
            return 0L;
        }
        return pendingJob.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PendingJob(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    public void execute(boolean z) {
        pjsua2JNI.PendingJob_execute(this.swigCPtr, this, z);
    }

    protected void finalize() {
        delete();
    }
}
