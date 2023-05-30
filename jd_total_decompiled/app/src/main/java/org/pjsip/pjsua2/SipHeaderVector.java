package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SipHeaderVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipHeaderVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipHeaderVector sipHeaderVector) {
        if (sipHeaderVector == null) {
            return 0L;
        }
        return sipHeaderVector.swigCPtr;
    }

    public void add(SipHeader sipHeader) {
        pjsua2JNI.SipHeaderVector_add(this.swigCPtr, this, SipHeader.getCPtr(sipHeader), sipHeader);
    }

    public long capacity() {
        return pjsua2JNI.SipHeaderVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.SipHeaderVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipHeaderVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipHeader get(int i2) {
        return new SipHeader(pjsua2JNI.SipHeaderVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.SipHeaderVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.SipHeaderVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, SipHeader sipHeader) {
        pjsua2JNI.SipHeaderVector_set(this.swigCPtr, this, i2, SipHeader.getCPtr(sipHeader), sipHeader);
    }

    public long size() {
        return pjsua2JNI.SipHeaderVector_size(this.swigCPtr, this);
    }

    public SipHeaderVector() {
        this(pjsua2JNI.new_SipHeaderVector__SWIG_0(), true);
    }

    public SipHeaderVector(long j2) {
        this(pjsua2JNI.new_SipHeaderVector__SWIG_1(j2), true);
    }
}
