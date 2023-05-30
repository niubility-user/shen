package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountRegConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountRegConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountRegConfig accountRegConfig) {
        if (accountRegConfig == null) {
            return 0L;
        }
        return accountRegConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountRegConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContactParams() {
        return pjsua2JNI.AccountRegConfig_contactParams_get(this.swigCPtr, this);
    }

    public long getDelayBeforeRefreshSec() {
        return pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_get(this.swigCPtr, this);
    }

    public boolean getDropCallsOnFail() {
        return pjsua2JNI.AccountRegConfig_dropCallsOnFail_get(this.swigCPtr, this);
    }

    public long getFirstRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_get(this.swigCPtr, this);
    }

    public SipHeaderVector getHeaders() {
        long AccountRegConfig_headers_get = pjsua2JNI.AccountRegConfig_headers_get(this.swigCPtr, this);
        if (AccountRegConfig_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(AccountRegConfig_headers_get, false);
    }

    public long getProxyUse() {
        return pjsua2JNI.AccountRegConfig_proxyUse_get(this.swigCPtr, this);
    }

    public long getRandomRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_randomRetryIntervalSec_get(this.swigCPtr, this);
    }

    public boolean getRegisterOnAdd() {
        return pjsua2JNI.AccountRegConfig_registerOnAdd_get(this.swigCPtr, this);
    }

    public String getRegistrarUri() {
        return pjsua2JNI.AccountRegConfig_registrarUri_get(this.swigCPtr, this);
    }

    public long getRetryIntervalSec() {
        return pjsua2JNI.AccountRegConfig_retryIntervalSec_get(this.swigCPtr, this);
    }

    public long getTimeoutSec() {
        return pjsua2JNI.AccountRegConfig_timeoutSec_get(this.swigCPtr, this);
    }

    public long getUnregWaitMsec() {
        return pjsua2JNI.AccountRegConfig_unregWaitMsec_get(this.swigCPtr, this);
    }

    public void setContactParams(String str) {
        pjsua2JNI.AccountRegConfig_contactParams_set(this.swigCPtr, this, str);
    }

    public void setDelayBeforeRefreshSec(long j2) {
        pjsua2JNI.AccountRegConfig_delayBeforeRefreshSec_set(this.swigCPtr, this, j2);
    }

    public void setDropCallsOnFail(boolean z) {
        pjsua2JNI.AccountRegConfig_dropCallsOnFail_set(this.swigCPtr, this, z);
    }

    public void setFirstRetryIntervalSec(long j2) {
        pjsua2JNI.AccountRegConfig_firstRetryIntervalSec_set(this.swigCPtr, this, j2);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.AccountRegConfig_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setProxyUse(long j2) {
        pjsua2JNI.AccountRegConfig_proxyUse_set(this.swigCPtr, this, j2);
    }

    public void setRandomRetryIntervalSec(long j2) {
        pjsua2JNI.AccountRegConfig_randomRetryIntervalSec_set(this.swigCPtr, this, j2);
    }

    public void setRegisterOnAdd(boolean z) {
        pjsua2JNI.AccountRegConfig_registerOnAdd_set(this.swigCPtr, this, z);
    }

    public void setRegistrarUri(String str) {
        pjsua2JNI.AccountRegConfig_registrarUri_set(this.swigCPtr, this, str);
    }

    public void setRetryIntervalSec(long j2) {
        pjsua2JNI.AccountRegConfig_retryIntervalSec_set(this.swigCPtr, this, j2);
    }

    public void setTimeoutSec(long j2) {
        pjsua2JNI.AccountRegConfig_timeoutSec_set(this.swigCPtr, this, j2);
    }

    public void setUnregWaitMsec(long j2) {
        pjsua2JNI.AccountRegConfig_unregWaitMsec_set(this.swigCPtr, this, j2);
    }

    public AccountRegConfig() {
        this(pjsua2JNI.new_AccountRegConfig(), true);
    }
}
