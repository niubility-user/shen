package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnStreamDestroyedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnStreamDestroyedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnStreamDestroyedParam onStreamDestroyedParam) {
        if (onStreamDestroyedParam == null) {
            return 0L;
        }
        return onStreamDestroyedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnStreamDestroyedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getStream() {
        long OnStreamDestroyedParam_stream_get = pjsua2JNI.OnStreamDestroyedParam_stream_get(this.swigCPtr, this);
        if (OnStreamDestroyedParam_stream_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnStreamDestroyedParam_stream_get, false);
    }

    public long getStreamIdx() {
        return pjsua2JNI.OnStreamDestroyedParam_streamIdx_get(this.swigCPtr, this);
    }

    public void setStream(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnStreamDestroyedParam_stream_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setStreamIdx(long j2) {
        pjsua2JNI.OnStreamDestroyedParam_streamIdx_set(this.swigCPtr, this, j2);
    }

    public OnStreamDestroyedParam() {
        this(pjsua2JNI.new_OnStreamDestroyedParam(), true);
    }
}
