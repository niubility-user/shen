package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallMediaTransportStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallMediaTransportStateParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallMediaTransportStateParam onCallMediaTransportStateParam) {
        if (onCallMediaTransportStateParam == null) {
            return 0L;
        }
        return onCallMediaTransportStateParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaTransportStateParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getMedIdx() {
        return pjsua2JNI.OnCallMediaTransportStateParam_medIdx_get(this.swigCPtr, this);
    }

    public int getSipErrorCode() {
        return pjsua2JNI.OnCallMediaTransportStateParam_sipErrorCode_get(this.swigCPtr, this);
    }

    public pjsua_med_tp_st getState() {
        return pjsua_med_tp_st.swigToEnum(pjsua2JNI.OnCallMediaTransportStateParam_state_get(this.swigCPtr, this));
    }

    public int getStatus() {
        return pjsua2JNI.OnCallMediaTransportStateParam_status_get(this.swigCPtr, this);
    }

    public void setMedIdx(long j2) {
        pjsua2JNI.OnCallMediaTransportStateParam_medIdx_set(this.swigCPtr, this, j2);
    }

    public void setSipErrorCode(int i2) {
        pjsua2JNI.OnCallMediaTransportStateParam_sipErrorCode_set(this.swigCPtr, this, i2);
    }

    public void setState(pjsua_med_tp_st pjsua_med_tp_stVar) {
        pjsua2JNI.OnCallMediaTransportStateParam_state_set(this.swigCPtr, this, pjsua_med_tp_stVar.swigValue());
    }

    public void setStatus(int i2) {
        pjsua2JNI.OnCallMediaTransportStateParam_status_set(this.swigCPtr, this, i2);
    }

    public OnCallMediaTransportStateParam() {
        this(pjsua2JNI.new_OnCallMediaTransportStateParam(), true);
    }
}
