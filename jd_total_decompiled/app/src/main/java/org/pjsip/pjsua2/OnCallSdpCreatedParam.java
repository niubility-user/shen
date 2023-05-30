package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallSdpCreatedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallSdpCreatedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallSdpCreatedParam onCallSdpCreatedParam) {
        if (onCallSdpCreatedParam == null) {
            return 0L;
        }
        return onCallSdpCreatedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallSdpCreatedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SdpSession getRemSdp() {
        long OnCallSdpCreatedParam_remSdp_get = pjsua2JNI.OnCallSdpCreatedParam_remSdp_get(this.swigCPtr, this);
        if (OnCallSdpCreatedParam_remSdp_get == 0) {
            return null;
        }
        return new SdpSession(OnCallSdpCreatedParam_remSdp_get, false);
    }

    public SdpSession getSdp() {
        long OnCallSdpCreatedParam_sdp_get = pjsua2JNI.OnCallSdpCreatedParam_sdp_get(this.swigCPtr, this);
        if (OnCallSdpCreatedParam_sdp_get == 0) {
            return null;
        }
        return new SdpSession(OnCallSdpCreatedParam_sdp_get, false);
    }

    public void setRemSdp(SdpSession sdpSession) {
        pjsua2JNI.OnCallSdpCreatedParam_remSdp_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public void setSdp(SdpSession sdpSession) {
        pjsua2JNI.OnCallSdpCreatedParam_sdp_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public OnCallSdpCreatedParam() {
        this(pjsua2JNI.new_OnCallSdpCreatedParam(), true);
    }
}
