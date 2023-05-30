package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallTsxStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallTsxStateParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallTsxStateParam onCallTsxStateParam) {
        if (onCallTsxStateParam == null) {
            return 0L;
        }
        return onCallTsxStateParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTsxStateParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipEvent getE() {
        long OnCallTsxStateParam_e_get = pjsua2JNI.OnCallTsxStateParam_e_get(this.swigCPtr, this);
        if (OnCallTsxStateParam_e_get == 0) {
            return null;
        }
        return new SipEvent(OnCallTsxStateParam_e_get, false);
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallTsxStateParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public OnCallTsxStateParam() {
        this(pjsua2JNI.new_OnCallTsxStateParam(), true);
    }
}
