package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountMwiConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountMwiConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountMwiConfig accountMwiConfig) {
        if (accountMwiConfig == null) {
            return 0L;
        }
        return accountMwiConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountMwiConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getEnabled() {
        return pjsua2JNI.AccountMwiConfig_enabled_get(this.swigCPtr, this);
    }

    public long getExpirationSec() {
        return pjsua2JNI.AccountMwiConfig_expirationSec_get(this.swigCPtr, this);
    }

    public void setEnabled(boolean z) {
        pjsua2JNI.AccountMwiConfig_enabled_set(this.swigCPtr, this, z);
    }

    public void setExpirationSec(long j2) {
        pjsua2JNI.AccountMwiConfig_expirationSec_set(this.swigCPtr, this, j2);
    }

    public AccountMwiConfig() {
        this(pjsua2JNI.new_AccountMwiConfig(), true);
    }
}
