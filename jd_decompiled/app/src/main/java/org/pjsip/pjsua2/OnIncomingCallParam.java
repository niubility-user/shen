package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnIncomingCallParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnIncomingCallParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnIncomingCallParam onIncomingCallParam) {
        if (onIncomingCallParam == null) {
            return 0L;
        }
        return onIncomingCallParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIncomingCallParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCallId() {
        return pjsua2JNI.OnIncomingCallParam_callId_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnIncomingCallParam_rdata_get = pjsua2JNI.OnIncomingCallParam_rdata_get(this.swigCPtr, this);
        if (OnIncomingCallParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnIncomingCallParam_rdata_get, false);
    }

    public void setCallId(int i2) {
        pjsua2JNI.OnIncomingCallParam_callId_set(this.swigCPtr, this, i2);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnIncomingCallParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public OnIncomingCallParam() {
        this(pjsua2JNI.new_OnIncomingCallParam(), true);
    }
}
