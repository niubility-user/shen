package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnNatCheckStunServersCompleteParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnNatCheckStunServersCompleteParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam) {
        if (onNatCheckStunServersCompleteParam == null) {
            return 0L;
        }
        return onNatCheckStunServersCompleteParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnNatCheckStunServersCompleteParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getAddr() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_addr_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_name_get(this.swigCPtr, this);
    }

    public int getStatus() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_status_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getUserData() {
        long OnNatCheckStunServersCompleteParam_userData_get = pjsua2JNI.OnNatCheckStunServersCompleteParam_userData_get(this.swigCPtr, this);
        if (OnNatCheckStunServersCompleteParam_userData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnNatCheckStunServersCompleteParam_userData_get, false);
    }

    public void setAddr(String str) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_addr_set(this.swigCPtr, this, str);
    }

    public void setName(String str) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_name_set(this.swigCPtr, this, str);
    }

    public void setStatus(int i2) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_status_set(this.swigCPtr, this, i2);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public OnNatCheckStunServersCompleteParam() {
        this(pjsua2JNI.new_OnNatCheckStunServersCompleteParam(), true);
    }
}
