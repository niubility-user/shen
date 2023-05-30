package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected AccountConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(AccountConfig accountConfig) {
        if (accountConfig == null) {
            return 0L;
        }
        return accountConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AccountCallConfig getCallConfig() {
        long AccountConfig_callConfig_get = pjsua2JNI.AccountConfig_callConfig_get(this.swigCPtr, this);
        if (AccountConfig_callConfig_get == 0) {
            return null;
        }
        return new AccountCallConfig(AccountConfig_callConfig_get, false);
    }

    public String getIdUri() {
        return pjsua2JNI.AccountConfig_idUri_get(this.swigCPtr, this);
    }

    public AccountIpChangeConfig getIpChangeConfig() {
        long AccountConfig_ipChangeConfig_get = pjsua2JNI.AccountConfig_ipChangeConfig_get(this.swigCPtr, this);
        if (AccountConfig_ipChangeConfig_get == 0) {
            return null;
        }
        return new AccountIpChangeConfig(AccountConfig_ipChangeConfig_get, false);
    }

    public AccountMediaConfig getMediaConfig() {
        long AccountConfig_mediaConfig_get = pjsua2JNI.AccountConfig_mediaConfig_get(this.swigCPtr, this);
        if (AccountConfig_mediaConfig_get == 0) {
            return null;
        }
        return new AccountMediaConfig(AccountConfig_mediaConfig_get, false);
    }

    public AccountMwiConfig getMwiConfig() {
        long AccountConfig_mwiConfig_get = pjsua2JNI.AccountConfig_mwiConfig_get(this.swigCPtr, this);
        if (AccountConfig_mwiConfig_get == 0) {
            return null;
        }
        return new AccountMwiConfig(AccountConfig_mwiConfig_get, false);
    }

    public AccountNatConfig getNatConfig() {
        long AccountConfig_natConfig_get = pjsua2JNI.AccountConfig_natConfig_get(this.swigCPtr, this);
        if (AccountConfig_natConfig_get == 0) {
            return null;
        }
        return new AccountNatConfig(AccountConfig_natConfig_get, false);
    }

    public AccountPresConfig getPresConfig() {
        long AccountConfig_presConfig_get = pjsua2JNI.AccountConfig_presConfig_get(this.swigCPtr, this);
        if (AccountConfig_presConfig_get == 0) {
            return null;
        }
        return new AccountPresConfig(AccountConfig_presConfig_get, false);
    }

    public int getPriority() {
        return pjsua2JNI.AccountConfig_priority_get(this.swigCPtr, this);
    }

    public AccountRegConfig getRegConfig() {
        long AccountConfig_regConfig_get = pjsua2JNI.AccountConfig_regConfig_get(this.swigCPtr, this);
        if (AccountConfig_regConfig_get == 0) {
            return null;
        }
        return new AccountRegConfig(AccountConfig_regConfig_get, false);
    }

    public AccountSipConfig getSipConfig() {
        long AccountConfig_sipConfig_get = pjsua2JNI.AccountConfig_sipConfig_get(this.swigCPtr, this);
        if (AccountConfig_sipConfig_get == 0) {
            return null;
        }
        return new AccountSipConfig(AccountConfig_sipConfig_get, false);
    }

    public void setCallConfig(AccountCallConfig accountCallConfig) {
        pjsua2JNI.AccountConfig_callConfig_set(this.swigCPtr, this, AccountCallConfig.getCPtr(accountCallConfig), accountCallConfig);
    }

    public void setIdUri(String str) {
        pjsua2JNI.AccountConfig_idUri_set(this.swigCPtr, this, str);
    }

    public void setIpChangeConfig(AccountIpChangeConfig accountIpChangeConfig) {
        pjsua2JNI.AccountConfig_ipChangeConfig_set(this.swigCPtr, this, AccountIpChangeConfig.getCPtr(accountIpChangeConfig), accountIpChangeConfig);
    }

    public void setMediaConfig(AccountMediaConfig accountMediaConfig) {
        pjsua2JNI.AccountConfig_mediaConfig_set(this.swigCPtr, this, AccountMediaConfig.getCPtr(accountMediaConfig), accountMediaConfig);
    }

    public void setMwiConfig(AccountMwiConfig accountMwiConfig) {
        pjsua2JNI.AccountConfig_mwiConfig_set(this.swigCPtr, this, AccountMwiConfig.getCPtr(accountMwiConfig), accountMwiConfig);
    }

    public void setNatConfig(AccountNatConfig accountNatConfig) {
        pjsua2JNI.AccountConfig_natConfig_set(this.swigCPtr, this, AccountNatConfig.getCPtr(accountNatConfig), accountNatConfig);
    }

    public void setPresConfig(AccountPresConfig accountPresConfig) {
        pjsua2JNI.AccountConfig_presConfig_set(this.swigCPtr, this, AccountPresConfig.getCPtr(accountPresConfig), accountPresConfig);
    }

    public void setPriority(int i2) {
        pjsua2JNI.AccountConfig_priority_set(this.swigCPtr, this, i2);
    }

    public void setRegConfig(AccountRegConfig accountRegConfig) {
        pjsua2JNI.AccountConfig_regConfig_set(this.swigCPtr, this, AccountRegConfig.getCPtr(accountRegConfig), accountRegConfig);
    }

    public void setSipConfig(AccountSipConfig accountSipConfig) {
        pjsua2JNI.AccountConfig_sipConfig_set(this.swigCPtr, this, AccountSipConfig.getCPtr(accountSipConfig), accountSipConfig);
    }

    public AccountConfig() {
        this(pjsua2JNI.new_AccountConfig(), true);
    }
}
