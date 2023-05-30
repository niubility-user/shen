package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountIpChangeConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountIpChangeConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountIpChangeConfig accountIpChangeConfig) {
        if (accountIpChangeConfig == null) {
            return 0L;
        }
        return accountIpChangeConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountIpChangeConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getHangupCalls() {
        return pjsua2JNI.AccountIpChangeConfig_hangupCalls_get(this.swigCPtr, this);
    }

    public long getReinviteFlags() {
        return pjsua2JNI.AccountIpChangeConfig_reinviteFlags_get(this.swigCPtr, this);
    }

    public boolean getShutdownTp() {
        return pjsua2JNI.AccountIpChangeConfig_shutdownTp_get(this.swigCPtr, this);
    }

    public void setHangupCalls(boolean z) {
        pjsua2JNI.AccountIpChangeConfig_hangupCalls_set(this.swigCPtr, this, z);
    }

    public void setReinviteFlags(long j2) {
        pjsua2JNI.AccountIpChangeConfig_reinviteFlags_set(this.swigCPtr, this, j2);
    }

    public void setShutdownTp(boolean z) {
        pjsua2JNI.AccountIpChangeConfig_shutdownTp_set(this.swigCPtr, this, z);
    }

    public AccountIpChangeConfig() {
        this(pjsua2JNI.new_AccountIpChangeConfig(), true);
    }
}
