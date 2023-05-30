package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallReplacedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallReplacedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallReplacedParam onCallReplacedParam) {
        if (onCallReplacedParam == null) {
            return 0L;
        }
        return onCallReplacedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallReplacedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getNewCallId() {
        return pjsua2JNI.OnCallReplacedParam_newCallId_get(this.swigCPtr, this);
    }

    public void setNewCallId(int i2) {
        pjsua2JNI.OnCallReplacedParam_newCallId_set(this.swigCPtr, this, i2);
    }

    public OnCallReplacedParam() {
        this(pjsua2JNI.new_OnCallReplacedParam(), true);
    }
}
