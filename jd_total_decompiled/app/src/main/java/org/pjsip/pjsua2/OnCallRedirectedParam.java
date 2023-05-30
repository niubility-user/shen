package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallRedirectedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallRedirectedParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallRedirectedParam onCallRedirectedParam) {
        if (onCallRedirectedParam == null) {
            return 0L;
        }
        return onCallRedirectedParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallRedirectedParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipEvent getE() {
        long OnCallRedirectedParam_e_get = pjsua2JNI.OnCallRedirectedParam_e_get(this.swigCPtr, this);
        if (OnCallRedirectedParam_e_get == 0) {
            return null;
        }
        return new SipEvent(OnCallRedirectedParam_e_get, false);
    }

    public String getTargetUri() {
        return pjsua2JNI.OnCallRedirectedParam_targetUri_get(this.swigCPtr, this);
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallRedirectedParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public void setTargetUri(String str) {
        pjsua2JNI.OnCallRedirectedParam_targetUri_set(this.swigCPtr, this, str);
    }

    public OnCallRedirectedParam() {
        this(pjsua2JNI.new_OnCallRedirectedParam(), true);
    }
}
