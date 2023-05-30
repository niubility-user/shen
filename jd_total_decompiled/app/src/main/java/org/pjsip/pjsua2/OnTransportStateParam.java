package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnTransportStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnTransportStateParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnTransportStateParam onTransportStateParam) {
        if (onTransportStateParam == null) {
            return 0L;
        }
        return onTransportStateParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTransportStateParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getHnd() {
        long OnTransportStateParam_hnd_get = pjsua2JNI.OnTransportStateParam_hnd_get(this.swigCPtr, this);
        if (OnTransportStateParam_hnd_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnTransportStateParam_hnd_get, false);
    }

    public int getLastError() {
        return pjsua2JNI.OnTransportStateParam_lastError_get(this.swigCPtr, this);
    }

    public pjsip_transport_state getState() {
        return pjsip_transport_state.swigToEnum(pjsua2JNI.OnTransportStateParam_state_get(this.swigCPtr, this));
    }

    public TlsInfo getTlsInfo() {
        long OnTransportStateParam_tlsInfo_get = pjsua2JNI.OnTransportStateParam_tlsInfo_get(this.swigCPtr, this);
        if (OnTransportStateParam_tlsInfo_get == 0) {
            return null;
        }
        return new TlsInfo(OnTransportStateParam_tlsInfo_get, false);
    }

    public String getType() {
        return pjsua2JNI.OnTransportStateParam_type_get(this.swigCPtr, this);
    }

    public void setHnd(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnTransportStateParam_hnd_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setLastError(int i2) {
        pjsua2JNI.OnTransportStateParam_lastError_set(this.swigCPtr, this, i2);
    }

    public void setState(pjsip_transport_state pjsip_transport_stateVar) {
        pjsua2JNI.OnTransportStateParam_state_set(this.swigCPtr, this, pjsip_transport_stateVar.swigValue());
    }

    public void setTlsInfo(TlsInfo tlsInfo) {
        pjsua2JNI.OnTransportStateParam_tlsInfo_set(this.swigCPtr, this, TlsInfo.getCPtr(tlsInfo), tlsInfo);
    }

    public void setType(String str) {
        pjsua2JNI.OnTransportStateParam_type_set(this.swigCPtr, this, str);
    }

    public OnTransportStateParam() {
        this(pjsua2JNI.new_OnTransportStateParam(), true);
    }
}
