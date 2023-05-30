package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnSelectAccountParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnSelectAccountParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnSelectAccountParam onSelectAccountParam) {
        if (onSelectAccountParam == null) {
            return 0L;
        }
        return onSelectAccountParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnSelectAccountParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getAccountIndex() {
        return pjsua2JNI.OnSelectAccountParam_accountIndex_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnSelectAccountParam_rdata_get = pjsua2JNI.OnSelectAccountParam_rdata_get(this.swigCPtr, this);
        if (OnSelectAccountParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnSelectAccountParam_rdata_get, false);
    }

    public void setAccountIndex(int i2) {
        pjsua2JNI.OnSelectAccountParam_accountIndex_set(this.swigCPtr, this, i2);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnSelectAccountParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public OnSelectAccountParam() {
        this(pjsua2JNI.new_OnSelectAccountParam(), true);
    }
}
