package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public CodecInfoVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(CodecInfoVector codecInfoVector) {
        if (codecInfoVector == null) {
            return 0L;
        }
        return codecInfoVector.swigCPtr;
    }

    public void add(CodecInfo codecInfo) {
        pjsua2JNI.CodecInfoVector_add(this.swigCPtr, this, CodecInfo.getCPtr(codecInfo), codecInfo);
    }

    public long capacity() {
        return pjsua2JNI.CodecInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CodecInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecInfoVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CodecInfo get(int i2) {
        long CodecInfoVector_get = pjsua2JNI.CodecInfoVector_get(this.swigCPtr, this, i2);
        if (CodecInfoVector_get == 0) {
            return null;
        }
        return new CodecInfo(CodecInfoVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CodecInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.CodecInfoVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, CodecInfo codecInfo) {
        pjsua2JNI.CodecInfoVector_set(this.swigCPtr, this, i2, CodecInfo.getCPtr(codecInfo), codecInfo);
    }

    public long size() {
        return pjsua2JNI.CodecInfoVector_size(this.swigCPtr, this);
    }

    public CodecInfoVector() {
        this(pjsua2JNI.new_CodecInfoVector__SWIG_0(), true);
    }

    public CodecInfoVector(long j2) {
        this(pjsua2JNI.new_CodecInfoVector__SWIG_1(j2), true);
    }
}
