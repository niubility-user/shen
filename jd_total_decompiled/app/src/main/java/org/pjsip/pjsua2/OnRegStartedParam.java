package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnRegStartedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnRegStartedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnRegStartedParam onRegStartedParam) {
        if (onRegStartedParam == null) {
            return 0L;
        }
        return onRegStartedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnRegStartedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getRenew() {
        return pjsua2JNI.OnRegStartedParam_renew_get(this.swigCPtr, this);
    }

    public void setRenew(boolean z) {
        pjsua2JNI.OnRegStartedParam_renew_set(this.swigCPtr, this, z);
    }

    public OnRegStartedParam() {
        this(pjsua2JNI.new_OnRegStartedParam(), true);
    }
}
