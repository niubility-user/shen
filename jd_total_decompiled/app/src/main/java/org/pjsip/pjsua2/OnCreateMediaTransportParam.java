package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCreateMediaTransportParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCreateMediaTransportParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCreateMediaTransportParam onCreateMediaTransportParam) {
        if (onCreateMediaTransportParam == null) {
            return 0L;
        }
        return onCreateMediaTransportParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCreateMediaTransportParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getFlags() {
        return pjsua2JNI.OnCreateMediaTransportParam_flags_get(this.swigCPtr, this);
    }

    public long getMediaIdx() {
        return pjsua2JNI.OnCreateMediaTransportParam_mediaIdx_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getMediaTp() {
        long OnCreateMediaTransportParam_mediaTp_get = pjsua2JNI.OnCreateMediaTransportParam_mediaTp_get(this.swigCPtr, this);
        if (OnCreateMediaTransportParam_mediaTp_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnCreateMediaTransportParam_mediaTp_get, false);
    }

    public void setFlags(long j2) {
        pjsua2JNI.OnCreateMediaTransportParam_flags_set(this.swigCPtr, this, j2);
    }

    public void setMediaIdx(long j2) {
        pjsua2JNI.OnCreateMediaTransportParam_mediaIdx_set(this.swigCPtr, this, j2);
    }

    public void setMediaTp(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnCreateMediaTransportParam_mediaTp_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public OnCreateMediaTransportParam() {
        this(pjsua2JNI.new_OnCreateMediaTransportParam(), true);
    }
}
