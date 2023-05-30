package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class IntVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IntVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(IntVector intVector) {
        if (intVector == null) {
            return 0L;
        }
        return intVector.swigCPtr;
    }

    public void add(int i2) {
        pjsua2JNI.IntVector_add(this.swigCPtr, this, i2);
    }

    public long capacity() {
        return pjsua2JNI.IntVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.IntVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_IntVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int get(int i2) {
        return pjsua2JNI.IntVector_get(this.swigCPtr, this, i2);
    }

    public boolean isEmpty() {
        return pjsua2JNI.IntVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.IntVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, int i3) {
        pjsua2JNI.IntVector_set(this.swigCPtr, this, i2, i3);
    }

    public long size() {
        return pjsua2JNI.IntVector_size(this.swigCPtr, this);
    }

    public IntVector() {
        this(pjsua2JNI.new_IntVector__SWIG_0(), true);
    }

    public IntVector(long j2) {
        this(pjsua2JNI.new_IntVector__SWIG_1(j2), true);
    }
}
