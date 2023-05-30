package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDigitMapVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ToneDigitMapVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDigitMapVector toneDigitMapVector) {
        if (toneDigitMapVector == null) {
            return 0L;
        }
        return toneDigitMapVector.swigCPtr;
    }

    public void add(ToneDigitMapDigit toneDigitMapDigit) {
        pjsua2JNI.ToneDigitMapVector_add(this.swigCPtr, this, ToneDigitMapDigit.getCPtr(toneDigitMapDigit), toneDigitMapDigit);
    }

    public long capacity() {
        return pjsua2JNI.ToneDigitMapVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDigitMapVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitMapVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public ToneDigitMapDigit get(int i2) {
        return new ToneDigitMapDigit(pjsua2JNI.ToneDigitMapVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDigitMapVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.ToneDigitMapVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, ToneDigitMapDigit toneDigitMapDigit) {
        pjsua2JNI.ToneDigitMapVector_set(this.swigCPtr, this, i2, ToneDigitMapDigit.getCPtr(toneDigitMapDigit), toneDigitMapDigit);
    }

    public long size() {
        return pjsua2JNI.ToneDigitMapVector_size(this.swigCPtr, this);
    }

    public ToneDigitMapVector() {
        this(pjsua2JNI.new_ToneDigitMapVector__SWIG_0(), true);
    }

    public ToneDigitMapVector(long j2) {
        this(pjsua2JNI.new_ToneDigitMapVector__SWIG_1(j2), true);
    }
}
