package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnBuddyEvSubStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnBuddyEvSubStateParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnBuddyEvSubStateParam onBuddyEvSubStateParam) {
        if (onBuddyEvSubStateParam == null) {
            return 0L;
        }
        return onBuddyEvSubStateParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnBuddyEvSubStateParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipEvent getE() {
        long OnBuddyEvSubStateParam_e_get = pjsua2JNI.OnBuddyEvSubStateParam_e_get(this.swigCPtr, this);
        if (OnBuddyEvSubStateParam_e_get == 0) {
            return null;
        }
        return new SipEvent(OnBuddyEvSubStateParam_e_get, false);
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnBuddyEvSubStateParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public OnBuddyEvSubStateParam() {
        this(pjsua2JNI.new_OnBuddyEvSubStateParam(), true);
    }
}
