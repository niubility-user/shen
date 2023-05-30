package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnStreamCreatedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnStreamCreatedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnStreamCreatedParam onStreamCreatedParam) {
        if (onStreamCreatedParam == null) {
            return 0L;
        }
        return onStreamCreatedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnStreamCreatedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getDestroyPort() {
        return pjsua2JNI.OnStreamCreatedParam_destroyPort_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPPort() {
        long OnStreamCreatedParam_pPort_get = pjsua2JNI.OnStreamCreatedParam_pPort_get(this.swigCPtr, this);
        if (OnStreamCreatedParam_pPort_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnStreamCreatedParam_pPort_get, false);
    }

    public SWIGTYPE_p_void getStream() {
        long OnStreamCreatedParam_stream_get = pjsua2JNI.OnStreamCreatedParam_stream_get(this.swigCPtr, this);
        if (OnStreamCreatedParam_stream_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnStreamCreatedParam_stream_get, false);
    }

    public long getStreamIdx() {
        return pjsua2JNI.OnStreamCreatedParam_streamIdx_get(this.swigCPtr, this);
    }

    public void setDestroyPort(boolean z) {
        pjsua2JNI.OnStreamCreatedParam_destroyPort_set(this.swigCPtr, this, z);
    }

    public void setPPort(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamCreatedParam_pPort_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setStream(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamCreatedParam_stream_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setStreamIdx(long j2) {
        pjsua2JNI.OnStreamCreatedParam_streamIdx_set(this.swigCPtr, this, j2);
    }

    public OnStreamCreatedParam() {
        this(pjsua2JNI.new_OnStreamCreatedParam(), true);
    }
}
