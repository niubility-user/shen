package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SipRxData {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SipRxData(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(SipRxData sipRxData) {
        if (sipRxData == null) {
            return 0L;
        }
        return sipRxData.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipRxData(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getInfo() {
        return pjsua2JNI.SipRxData_info_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjRxData() {
        long SipRxData_pjRxData_get = pjsua2JNI.SipRxData_pjRxData_get(this.swigCPtr, this);
        if (SipRxData_pjRxData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipRxData_pjRxData_get, false);
    }

    public String getSrcAddress() {
        return pjsua2JNI.SipRxData_srcAddress_get(this.swigCPtr, this);
    }

    public String getWholeMsg() {
        return pjsua2JNI.SipRxData_wholeMsg_get(this.swigCPtr, this);
    }

    public void setInfo(String str) {
        pjsua2JNI.SipRxData_info_set(this.swigCPtr, this, str);
    }

    public void setPjRxData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipRxData_pjRxData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setSrcAddress(String str) {
        pjsua2JNI.SipRxData_srcAddress_set(this.swigCPtr, this, str);
    }

    public void setWholeMsg(String str) {
        pjsua2JNI.SipRxData_wholeMsg_set(this.swigCPtr, this, str);
    }

    public SipRxData() {
        this(pjsua2JNI.new_SipRxData(), true);
    }
}
