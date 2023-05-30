package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CallMediaInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CallMediaInfoVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CallMediaInfoVector callMediaInfoVector) {
        if (callMediaInfoVector == null) {
            return 0L;
        }
        return callMediaInfoVector.swigCPtr;
    }

    public void add(CallMediaInfo callMediaInfo) {
        pjsua2JNI.CallMediaInfoVector_add(this.swigCPtr, this, CallMediaInfo.getCPtr(callMediaInfo), callMediaInfo);
    }

    public long capacity() {
        return pjsua2JNI.CallMediaInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CallMediaInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallMediaInfoVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CallMediaInfo get(int i2) {
        return new CallMediaInfo(pjsua2JNI.CallMediaInfoVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CallMediaInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.CallMediaInfoVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, CallMediaInfo callMediaInfo) {
        pjsua2JNI.CallMediaInfoVector_set(this.swigCPtr, this, i2, CallMediaInfo.getCPtr(callMediaInfo), callMediaInfo);
    }

    public long size() {
        return pjsua2JNI.CallMediaInfoVector_size(this.swigCPtr, this);
    }

    public CallMediaInfoVector() {
        this(pjsua2JNI.new_CallMediaInfoVector__SWIG_0(), true);
    }

    public CallMediaInfoVector(long j2) {
        this(pjsua2JNI.new_CallMediaInfoVector__SWIG_1(j2), true);
    }
}
