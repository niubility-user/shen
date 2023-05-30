package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AuthCredInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthCredInfoVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AuthCredInfoVector authCredInfoVector) {
        if (authCredInfoVector == null) {
            return 0L;
        }
        return authCredInfoVector.swigCPtr;
    }

    public void add(AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_add(this.swigCPtr, this, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }

    public long capacity() {
        return pjsua2JNI.AuthCredInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AuthCredInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfoVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AuthCredInfo get(int i2) {
        return new AuthCredInfo(pjsua2JNI.AuthCredInfoVector_get(this.swigCPtr, this, i2), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AuthCredInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.AuthCredInfoVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_set(this.swigCPtr, this, i2, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }

    public long size() {
        return pjsua2JNI.AuthCredInfoVector_size(this.swigCPtr, this);
    }

    public AuthCredInfoVector() {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_0(), true);
    }

    public AuthCredInfoVector(long j2) {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_1(j2), true);
    }
}
