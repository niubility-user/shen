package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecFmtpVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecFmtpVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecFmtpVector codecFmtpVector) {
        if (codecFmtpVector == null) {
            return 0L;
        }
        return codecFmtpVector.swigCPtr;
    }

    public void add(CodecFmtp codecFmtp) {
        pjsua2JNI.CodecFmtpVector_add(this.swigCPtr, this, CodecFmtp.getCPtr(codecFmtp), codecFmtp);
    }

    public long capacity() {
        return pjsua2JNI.CodecFmtpVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CodecFmtpVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecFmtpVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CodecFmtp get(int i2) {
        return new CodecFmtp(pjsua2JNI.CodecFmtpVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CodecFmtpVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.CodecFmtpVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, CodecFmtp codecFmtp) {
        pjsua2JNI.CodecFmtpVector_set(this.swigCPtr, this, i2, CodecFmtp.getCPtr(codecFmtp), codecFmtp);
    }

    public long size() {
        return pjsua2JNI.CodecFmtpVector_size(this.swigCPtr, this);
    }

    public CodecFmtpVector() {
        this(pjsua2JNI.new_CodecFmtpVector__SWIG_0(), true);
    }

    public CodecFmtpVector(long j2) {
        this(pjsua2JNI.new_CodecFmtpVector__SWIG_1(j2), true);
    }
}
