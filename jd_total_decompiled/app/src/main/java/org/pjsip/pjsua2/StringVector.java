package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class StringVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public StringVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(StringVector stringVector) {
        if (stringVector == null) {
            return 0L;
        }
        return stringVector.swigCPtr;
    }

    public void add(String str) {
        pjsua2JNI.StringVector_add(this.swigCPtr, this, str);
    }

    public long capacity() {
        return pjsua2JNI.StringVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.StringVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StringVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String get(int i2) {
        return pjsua2JNI.StringVector_get(this.swigCPtr, this, i2);
    }

    public boolean isEmpty() {
        return pjsua2JNI.StringVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.StringVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, String str) {
        pjsua2JNI.StringVector_set(this.swigCPtr, this, i2, str);
    }

    public long size() {
        return pjsua2JNI.StringVector_size(this.swigCPtr, this);
    }

    public StringVector() {
        this(pjsua2JNI.new_StringVector__SWIG_0(), true);
    }

    public StringVector(long j2) {
        this(pjsua2JNI.new_StringVector__SWIG_1(j2), true);
    }
}
