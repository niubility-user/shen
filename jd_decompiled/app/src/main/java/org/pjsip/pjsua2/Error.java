package org.pjsip.pjsua2;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* loaded from: classes.dex */
public class Error extends Exception {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected Error(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(Error error) {
        if (error == null) {
            return 0L;
        }
        return error.swigCPtr;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new NotSerializableException("Check ticket #1868!");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException("Check ticket #1868!");
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Error(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return getTitle();
    }

    public String getReason() {
        return pjsua2JNI.Error_reason_get(this.swigCPtr, this);
    }

    public String getSrcFile() {
        return pjsua2JNI.Error_srcFile_get(this.swigCPtr, this);
    }

    public int getSrcLine() {
        return pjsua2JNI.Error_srcLine_get(this.swigCPtr, this);
    }

    public int getStatus() {
        return pjsua2JNI.Error_status_get(this.swigCPtr, this);
    }

    public String getTitle() {
        return pjsua2JNI.Error_title_get(this.swigCPtr, this);
    }

    public String info(boolean z) {
        return pjsua2JNI.Error_info__SWIG_0(this.swigCPtr, this, z);
    }

    public void setReason(String str) {
        pjsua2JNI.Error_reason_set(this.swigCPtr, this, str);
    }

    public void setSrcFile(String str) {
        pjsua2JNI.Error_srcFile_set(this.swigCPtr, this, str);
    }

    public void setSrcLine(int i2) {
        pjsua2JNI.Error_srcLine_set(this.swigCPtr, this, i2);
    }

    public void setStatus(int i2) {
        pjsua2JNI.Error_status_set(this.swigCPtr, this, i2);
    }

    public void setTitle(String str) {
        pjsua2JNI.Error_title_set(this.swigCPtr, this, str);
    }

    public String info() {
        return pjsua2JNI.Error_info__SWIG_1(this.swigCPtr, this);
    }

    public Error() {
        this(pjsua2JNI.new_Error__SWIG_0(), true);
    }

    public Error(int i2, String str, String str2, String str3, int i3) {
        this(pjsua2JNI.new_Error__SWIG_1(i2, str, str2, str3, i3), true);
    }
}
