package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnTypingIndicationParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnTypingIndicationParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnTypingIndicationParam onTypingIndicationParam) {
        if (onTypingIndicationParam == null) {
            return 0L;
        }
        return onTypingIndicationParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTypingIndicationParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContactUri() {
        return pjsua2JNI.OnTypingIndicationParam_contactUri_get(this.swigCPtr, this);
    }

    public String getFromUri() {
        return pjsua2JNI.OnTypingIndicationParam_fromUri_get(this.swigCPtr, this);
    }

    public boolean getIsTyping() {
        return pjsua2JNI.OnTypingIndicationParam_isTyping_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnTypingIndicationParam_rdata_get = pjsua2JNI.OnTypingIndicationParam_rdata_get(this.swigCPtr, this);
        if (OnTypingIndicationParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnTypingIndicationParam_rdata_get, false);
    }

    public String getToUri() {
        return pjsua2JNI.OnTypingIndicationParam_toUri_get(this.swigCPtr, this);
    }

    public void setContactUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_contactUri_set(this.swigCPtr, this, str);
    }

    public void setFromUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_fromUri_set(this.swigCPtr, this, str);
    }

    public void setIsTyping(boolean z) {
        pjsua2JNI.OnTypingIndicationParam_isTyping_set(this.swigCPtr, this, z);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnTypingIndicationParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setToUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_toUri_set(this.swigCPtr, this, str);
    }

    public OnTypingIndicationParam() {
        this(pjsua2JNI.new_OnTypingIndicationParam(), true);
    }
}
