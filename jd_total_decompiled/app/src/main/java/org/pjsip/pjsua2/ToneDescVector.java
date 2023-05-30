package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDescVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ToneDescVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDescVector toneDescVector) {
        if (toneDescVector == null) {
            return 0L;
        }
        return toneDescVector.swigCPtr;
    }

    public void add(ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_add(this.swigCPtr, this, ToneDesc.getCPtr(toneDesc), toneDesc);
    }

    public long capacity() {
        return pjsua2JNI.ToneDescVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDescVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDescVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public ToneDesc get(int i2) {
        return new ToneDesc(pjsua2JNI.ToneDescVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDescVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.ToneDescVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_set(this.swigCPtr, this, i2, ToneDesc.getCPtr(toneDesc), toneDesc);
    }

    public long size() {
        return pjsua2JNI.ToneDescVector_size(this.swigCPtr, this);
    }

    public ToneDescVector() {
        this(pjsua2JNI.new_ToneDescVector__SWIG_0(), true);
    }

    public ToneDescVector(long j2) {
        this(pjsua2JNI.new_ToneDescVector__SWIG_1(j2), true);
    }
}
