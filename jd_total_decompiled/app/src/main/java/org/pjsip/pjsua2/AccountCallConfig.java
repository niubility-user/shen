package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AccountCallConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountCallConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountCallConfig accountCallConfig) {
        if (accountCallConfig == null) {
            return 0L;
        }
        return accountCallConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountCallConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsua_call_hold_type getHoldType() {
        return pjsua_call_hold_type.swigToEnum(pjsua2JNI.AccountCallConfig_holdType_get(this.swigCPtr, this));
    }

    public pjsua_100rel_use getPrackUse() {
        return pjsua_100rel_use.swigToEnum(pjsua2JNI.AccountCallConfig_prackUse_get(this.swigCPtr, this));
    }

    public long getTimerMinSESec() {
        return pjsua2JNI.AccountCallConfig_timerMinSESec_get(this.swigCPtr, this);
    }

    public long getTimerSessExpiresSec() {
        return pjsua2JNI.AccountCallConfig_timerSessExpiresSec_get(this.swigCPtr, this);
    }

    public pjsua_sip_timer_use getTimerUse() {
        return pjsua_sip_timer_use.swigToEnum(pjsua2JNI.AccountCallConfig_timerUse_get(this.swigCPtr, this));
    }

    public void setHoldType(pjsua_call_hold_type pjsua_call_hold_typeVar) {
        pjsua2JNI.AccountCallConfig_holdType_set(this.swigCPtr, this, pjsua_call_hold_typeVar.swigValue());
    }

    public void setPrackUse(pjsua_100rel_use pjsua_100rel_useVar) {
        pjsua2JNI.AccountCallConfig_prackUse_set(this.swigCPtr, this, pjsua_100rel_useVar.swigValue());
    }

    public void setTimerMinSESec(long j2) {
        pjsua2JNI.AccountCallConfig_timerMinSESec_set(this.swigCPtr, this, j2);
    }

    public void setTimerSessExpiresSec(long j2) {
        pjsua2JNI.AccountCallConfig_timerSessExpiresSec_set(this.swigCPtr, this, j2);
    }

    public void setTimerUse(pjsua_sip_timer_use pjsua_sip_timer_useVar) {
        pjsua2JNI.AccountCallConfig_timerUse_set(this.swigCPtr, this, pjsua_sip_timer_useVar.swigValue());
    }

    public AccountCallConfig() {
        this(pjsua2JNI.new_AccountCallConfig(), true);
    }
}
