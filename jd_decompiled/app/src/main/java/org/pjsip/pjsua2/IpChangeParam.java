package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class IpChangeParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected IpChangeParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(IpChangeParam ipChangeParam) {
        if (ipChangeParam == null) {
            return 0L;
        }
        return ipChangeParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_IpChangeParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getRestartLisDelay() {
        return pjsua2JNI.IpChangeParam_restartLisDelay_get(this.swigCPtr, this);
    }

    public boolean getRestartListener() {
        return pjsua2JNI.IpChangeParam_restartListener_get(this.swigCPtr, this);
    }

    public void setRestartLisDelay(long j2) {
        pjsua2JNI.IpChangeParam_restartLisDelay_set(this.swigCPtr, this, j2);
    }

    public void setRestartListener(boolean z) {
        pjsua2JNI.IpChangeParam_restartListener_set(this.swigCPtr, this, z);
    }

    public IpChangeParam() {
        this(pjsua2JNI.new_IpChangeParam(), true);
    }
}
