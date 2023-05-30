package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class EpConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected EpConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(EpConfig epConfig) {
        if (epConfig == null) {
            return 0L;
        }
        return epConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_EpConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public LogConfig getLogConfig() {
        long EpConfig_logConfig_get = pjsua2JNI.EpConfig_logConfig_get(this.swigCPtr, this);
        if (EpConfig_logConfig_get == 0) {
            return null;
        }
        return new LogConfig(EpConfig_logConfig_get, false);
    }

    public MediaConfig getMedConfig() {
        long EpConfig_medConfig_get = pjsua2JNI.EpConfig_medConfig_get(this.swigCPtr, this);
        if (EpConfig_medConfig_get == 0) {
            return null;
        }
        return new MediaConfig(EpConfig_medConfig_get, false);
    }

    public UaConfig getUaConfig() {
        long EpConfig_uaConfig_get = pjsua2JNI.EpConfig_uaConfig_get(this.swigCPtr, this);
        if (EpConfig_uaConfig_get == 0) {
            return null;
        }
        return new UaConfig(EpConfig_uaConfig_get, false);
    }

    public void setLogConfig(LogConfig logConfig) {
        pjsua2JNI.EpConfig_logConfig_set(this.swigCPtr, this, LogConfig.getCPtr(logConfig), logConfig);
    }

    public void setMedConfig(MediaConfig mediaConfig) {
        pjsua2JNI.EpConfig_medConfig_set(this.swigCPtr, this, MediaConfig.getCPtr(mediaConfig), mediaConfig);
    }

    public void setUaConfig(UaConfig uaConfig) {
        pjsua2JNI.EpConfig_uaConfig_set(this.swigCPtr, this, UaConfig.getCPtr(uaConfig), uaConfig);
    }

    public EpConfig() {
        this(pjsua2JNI.new_EpConfig(), true);
    }
}
