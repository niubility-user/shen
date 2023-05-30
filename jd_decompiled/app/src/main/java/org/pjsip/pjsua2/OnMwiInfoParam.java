package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnMwiInfoParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnMwiInfoParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnMwiInfoParam onMwiInfoParam) {
        if (onMwiInfoParam == null) {
            return 0L;
        }
        return onMwiInfoParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnMwiInfoParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipRxData getRdata() {
        long OnMwiInfoParam_rdata_get = pjsua2JNI.OnMwiInfoParam_rdata_get(this.swigCPtr, this);
        if (OnMwiInfoParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnMwiInfoParam_rdata_get, false);
    }

    public pjsip_evsub_state getState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.OnMwiInfoParam_state_get(this.swigCPtr, this));
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnMwiInfoParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setState(pjsip_evsub_state pjsip_evsub_stateVar) {
        pjsua2JNI.OnMwiInfoParam_state_set(this.swigCPtr, this, pjsip_evsub_stateVar.swigValue());
    }

    public OnMwiInfoParam() {
        this(pjsua2JNI.new_OnMwiInfoParam(), true);
    }
}
