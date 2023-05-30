package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDigitVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ToneDigitVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDigitVector toneDigitVector) {
        if (toneDigitVector == null) {
            return 0L;
        }
        return toneDigitVector.swigCPtr;
    }

    public void add(ToneDigit toneDigit) {
        pjsua2JNI.ToneDigitVector_add(this.swigCPtr, this, ToneDigit.getCPtr(toneDigit), toneDigit);
    }

    public long capacity() {
        return pjsua2JNI.ToneDigitVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDigitVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public ToneDigit get(int i2) {
        return new ToneDigit(pjsua2JNI.ToneDigitVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDigitVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.ToneDigitVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, ToneDigit toneDigit) {
        pjsua2JNI.ToneDigitVector_set(this.swigCPtr, this, i2, ToneDigit.getCPtr(toneDigit), toneDigit);
    }

    public long size() {
        return pjsua2JNI.ToneDigitVector_size(this.swigCPtr, this);
    }

    public ToneDigitVector() {
        this(pjsua2JNI.new_ToneDigitVector__SWIG_0(), true);
    }

    public ToneDigitVector(long j2) {
        this(pjsua2JNI.new_ToneDigitVector__SWIG_1(j2), true);
    }
}
