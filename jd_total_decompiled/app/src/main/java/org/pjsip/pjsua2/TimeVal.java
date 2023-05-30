package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class TimeVal {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TimeVal(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TimeVal timeVal) {
        if (timeVal == null) {
            return 0L;
        }
        return timeVal.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TimeVal(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getMsec() {
        return pjsua2JNI.TimeVal_msec_get(this.swigCPtr, this);
    }

    public int getSec() {
        return pjsua2JNI.TimeVal_sec_get(this.swigCPtr, this);
    }

    public void setMsec(int i2) {
        pjsua2JNI.TimeVal_msec_set(this.swigCPtr, this, i2);
    }

    public void setSec(int i2) {
        pjsua2JNI.TimeVal_sec_set(this.swigCPtr, this, i2);
    }

    public TimeVal() {
        this(pjsua2JNI.new_TimeVal(), true);
    }
}
