package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class UaConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public UaConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(UaConfig uaConfig) {
        if (uaConfig == null) {
            return 0L;
        }
        return uaConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_UaConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getMainThreadOnly() {
        return pjsua2JNI.UaConfig_mainThreadOnly_get(this.swigCPtr, this);
    }

    public long getMaxCalls() {
        return pjsua2JNI.UaConfig_maxCalls_get(this.swigCPtr, this);
    }

    public boolean getMwiUnsolicitedEnabled() {
        return pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_get(this.swigCPtr, this);
    }

    public StringVector getNameserver() {
        long UaConfig_nameserver_get = pjsua2JNI.UaConfig_nameserver_get(this.swigCPtr, this);
        if (UaConfig_nameserver_get == 0) {
            return null;
        }
        return new StringVector(UaConfig_nameserver_get, false);
    }

    public int getNatTypeInSdp() {
        return pjsua2JNI.UaConfig_natTypeInSdp_get(this.swigCPtr, this);
    }

    public boolean getStunIgnoreFailure() {
        return pjsua2JNI.UaConfig_stunIgnoreFailure_get(this.swigCPtr, this);
    }

    public StringVector getStunServer() {
        long UaConfig_stunServer_get = pjsua2JNI.UaConfig_stunServer_get(this.swigCPtr, this);
        if (UaConfig_stunServer_get == 0) {
            return null;
        }
        return new StringVector(UaConfig_stunServer_get, false);
    }

    public boolean getStunTryIpv6() {
        return pjsua2JNI.UaConfig_stunTryIpv6_get(this.swigCPtr, this);
    }

    public long getThreadCnt() {
        return pjsua2JNI.UaConfig_threadCnt_get(this.swigCPtr, this);
    }

    public String getUserAgent() {
        return pjsua2JNI.UaConfig_userAgent_get(this.swigCPtr, this);
    }

    public void setMainThreadOnly(boolean z) {
        pjsua2JNI.UaConfig_mainThreadOnly_set(this.swigCPtr, this, z);
    }

    public void setMaxCalls(long j2) {
        pjsua2JNI.UaConfig_maxCalls_set(this.swigCPtr, this, j2);
    }

    public void setMwiUnsolicitedEnabled(boolean z) {
        pjsua2JNI.UaConfig_mwiUnsolicitedEnabled_set(this.swigCPtr, this, z);
    }

    public void setNameserver(StringVector stringVector) {
        pjsua2JNI.UaConfig_nameserver_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setNatTypeInSdp(int i2) {
        pjsua2JNI.UaConfig_natTypeInSdp_set(this.swigCPtr, this, i2);
    }

    public void setStunIgnoreFailure(boolean z) {
        pjsua2JNI.UaConfig_stunIgnoreFailure_set(this.swigCPtr, this, z);
    }

    public void setStunServer(StringVector stringVector) {
        pjsua2JNI.UaConfig_stunServer_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setStunTryIpv6(boolean z) {
        pjsua2JNI.UaConfig_stunTryIpv6_set(this.swigCPtr, this, z);
    }

    public void setThreadCnt(long j2) {
        pjsua2JNI.UaConfig_threadCnt_set(this.swigCPtr, this, j2);
    }

    public void setUserAgent(String str) {
        pjsua2JNI.UaConfig_userAgent_set(this.swigCPtr, this, str);
    }

    public UaConfig() {
        this(pjsua2JNI.new_UaConfig(), true);
    }
}
