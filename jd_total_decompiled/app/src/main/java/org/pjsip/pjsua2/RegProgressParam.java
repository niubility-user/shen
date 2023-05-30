package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class RegProgressParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RegProgressParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RegProgressParam regProgressParam) {
        if (regProgressParam == null) {
            return 0L;
        }
        return regProgressParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RegProgressParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCode() {
        return pjsua2JNI.RegProgressParam_code_get(this.swigCPtr, this);
    }

    public boolean getIsRegister() {
        return pjsua2JNI.RegProgressParam_isRegister_get(this.swigCPtr, this);
    }

    public void setCode(int i2) {
        pjsua2JNI.RegProgressParam_code_set(this.swigCPtr, this, i2);
    }

    public void setIsRegister(boolean z) {
        pjsua2JNI.RegProgressParam_isRegister_set(this.swigCPtr, this, z);
    }

    public RegProgressParam() {
        this(pjsua2JNI.new_RegProgressParam(), true);
    }
}
