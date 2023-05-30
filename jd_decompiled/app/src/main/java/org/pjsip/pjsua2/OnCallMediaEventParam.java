package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallMediaEventParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallMediaEventParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallMediaEventParam onCallMediaEventParam) {
        if (onCallMediaEventParam == null) {
            return 0L;
        }
        return onCallMediaEventParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaEventParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaEvent getEv() {
        long OnCallMediaEventParam_ev_get = pjsua2JNI.OnCallMediaEventParam_ev_get(this.swigCPtr, this);
        if (OnCallMediaEventParam_ev_get == 0) {
            return null;
        }
        return new MediaEvent(OnCallMediaEventParam_ev_get, false);
    }

    public long getMedIdx() {
        return pjsua2JNI.OnCallMediaEventParam_medIdx_get(this.swigCPtr, this);
    }

    public void setEv(MediaEvent mediaEvent) {
        pjsua2JNI.OnCallMediaEventParam_ev_set(this.swigCPtr, this, MediaEvent.getCPtr(mediaEvent), mediaEvent);
    }

    public void setMedIdx(long j2) {
        pjsua2JNI.OnCallMediaEventParam_medIdx_set(this.swigCPtr, this, j2);
    }

    public OnCallMediaEventParam() {
        this(pjsua2JNI.new_OnCallMediaEventParam(), true);
    }
}
