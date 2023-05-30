package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountSipConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountSipConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountSipConfig accountSipConfig) {
        if (accountSipConfig == null) {
            return 0L;
        }
        return accountSipConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountSipConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AuthCredInfoVector getAuthCreds() {
        long AccountSipConfig_authCreds_get = pjsua2JNI.AccountSipConfig_authCreds_get(this.swigCPtr, this);
        if (AccountSipConfig_authCreds_get == 0) {
            return null;
        }
        return new AuthCredInfoVector(AccountSipConfig_authCreds_get, false);
    }

    public String getAuthInitialAlgorithm() {
        return pjsua2JNI.AccountSipConfig_authInitialAlgorithm_get(this.swigCPtr, this);
    }

    public boolean getAuthInitialEmpty() {
        return pjsua2JNI.AccountSipConfig_authInitialEmpty_get(this.swigCPtr, this);
    }

    public String getContactForced() {
        return pjsua2JNI.AccountSipConfig_contactForced_get(this.swigCPtr, this);
    }

    public String getContactParams() {
        return pjsua2JNI.AccountSipConfig_contactParams_get(this.swigCPtr, this);
    }

    public String getContactUriParams() {
        return pjsua2JNI.AccountSipConfig_contactUriParams_get(this.swigCPtr, this);
    }

    public StringVector getProxies() {
        long AccountSipConfig_proxies_get = pjsua2JNI.AccountSipConfig_proxies_get(this.swigCPtr, this);
        if (AccountSipConfig_proxies_get == 0) {
            return null;
        }
        return new StringVector(AccountSipConfig_proxies_get, false);
    }

    public int getTransportId() {
        return pjsua2JNI.AccountSipConfig_transportId_get(this.swigCPtr, this);
    }

    public void setAuthCreds(AuthCredInfoVector authCredInfoVector) {
        pjsua2JNI.AccountSipConfig_authCreds_set(this.swigCPtr, this, AuthCredInfoVector.getCPtr(authCredInfoVector), authCredInfoVector);
    }

    public void setAuthInitialAlgorithm(String str) {
        pjsua2JNI.AccountSipConfig_authInitialAlgorithm_set(this.swigCPtr, this, str);
    }

    public void setAuthInitialEmpty(boolean z) {
        pjsua2JNI.AccountSipConfig_authInitialEmpty_set(this.swigCPtr, this, z);
    }

    public void setContactForced(String str) {
        pjsua2JNI.AccountSipConfig_contactForced_set(this.swigCPtr, this, str);
    }

    public void setContactParams(String str) {
        pjsua2JNI.AccountSipConfig_contactParams_set(this.swigCPtr, this, str);
    }

    public void setContactUriParams(String str) {
        pjsua2JNI.AccountSipConfig_contactUriParams_set(this.swigCPtr, this, str);
    }

    public void setProxies(StringVector stringVector) {
        pjsua2JNI.AccountSipConfig_proxies_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setTransportId(int i2) {
        pjsua2JNI.AccountSipConfig_transportId_set(this.swigCPtr, this, i2);
    }

    public AccountSipConfig() {
        this(pjsua2JNI.new_AccountSipConfig(), true);
    }
}
