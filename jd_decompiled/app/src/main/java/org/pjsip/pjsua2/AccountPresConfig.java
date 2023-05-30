package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountPresConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountPresConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountPresConfig accountPresConfig) {
        if (accountPresConfig == null) {
            return 0L;
        }
        return accountPresConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountPresConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipHeaderVector getHeaders() {
        long AccountPresConfig_headers_get = pjsua2JNI.AccountPresConfig_headers_get(this.swigCPtr, this);
        if (AccountPresConfig_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(AccountPresConfig_headers_get, false);
    }

    public String getPidfTupleId() {
        return pjsua2JNI.AccountPresConfig_pidfTupleId_get(this.swigCPtr, this);
    }

    public boolean getPublishEnabled() {
        return pjsua2JNI.AccountPresConfig_publishEnabled_get(this.swigCPtr, this);
    }

    public boolean getPublishQueue() {
        return pjsua2JNI.AccountPresConfig_publishQueue_get(this.swigCPtr, this);
    }

    public long getPublishShutdownWaitMsec() {
        return pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_get(this.swigCPtr, this);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountPresConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setPidfTupleId(String str) {
        pjsua2JNI.AccountPresConfig_pidfTupleId_set(this.swigCPtr, this, str);
    }

    public void setPublishEnabled(boolean z) {
        pjsua2JNI.AccountPresConfig_publishEnabled_set(this.swigCPtr, this, z);
    }

    public void setPublishQueue(boolean z) {
        pjsua2JNI.AccountPresConfig_publishQueue_set(this.swigCPtr, this, z);
    }

    public void setPublishShutdownWaitMsec(long j2) {
        pjsua2JNI.AccountPresConfig_publishShutdownWaitMsec_set(this.swigCPtr, this, j2);
    }

    public AccountPresConfig() {
        this(pjsua2JNI.new_AccountPresConfig(), true);
    }
}
