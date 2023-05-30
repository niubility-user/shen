package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallTxOfferParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallTxOfferParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallTxOfferParam onCallTxOfferParam) {
        if (onCallTxOfferParam == null) {
            return 0L;
        }
        return onCallTxOfferParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTxOfferParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CallSetting getOpt() {
        long OnCallTxOfferParam_opt_get = pjsua2JNI.OnCallTxOfferParam_opt_get(this.swigCPtr, this);
        if (OnCallTxOfferParam_opt_get == 0) {
            return null;
        }
        return new CallSetting(OnCallTxOfferParam_opt_get, false);
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallTxOfferParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public OnCallTxOfferParam() {
        this(pjsua2JNI.new_OnCallTxOfferParam(), true);
    }
}
