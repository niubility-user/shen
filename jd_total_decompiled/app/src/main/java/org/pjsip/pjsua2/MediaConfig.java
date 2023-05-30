package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class MediaConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaConfig mediaConfig) {
        if (mediaConfig == null) {
            return 0L;
        }
        return mediaConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAudioFramePtime() {
        return pjsua2JNI.MediaConfig_audioFramePtime_get(this.swigCPtr, this);
    }

    public long getChannelCount() {
        return pjsua2JNI.MediaConfig_channelCount_get(this.swigCPtr, this);
    }

    public long getClockRate() {
        return pjsua2JNI.MediaConfig_clockRate_get(this.swigCPtr, this);
    }

    public long getEcOptions() {
        return pjsua2JNI.MediaConfig_ecOptions_get(this.swigCPtr, this);
    }

    public long getEcTailLen() {
        return pjsua2JNI.MediaConfig_ecTailLen_get(this.swigCPtr, this);
    }

    public boolean getHasIoqueue() {
        return pjsua2JNI.MediaConfig_hasIoqueue_get(this.swigCPtr, this);
    }

    public long getIlbcMode() {
        return pjsua2JNI.MediaConfig_ilbcMode_get(this.swigCPtr, this);
    }

    public int getJbInit() {
        return pjsua2JNI.MediaConfig_jbInit_get(this.swigCPtr, this);
    }

    public int getJbMax() {
        return pjsua2JNI.MediaConfig_jbMax_get(this.swigCPtr, this);
    }

    public int getJbMaxPre() {
        return pjsua2JNI.MediaConfig_jbMaxPre_get(this.swigCPtr, this);
    }

    public int getJbMinPre() {
        return pjsua2JNI.MediaConfig_jbMinPre_get(this.swigCPtr, this);
    }

    public long getMaxMediaPorts() {
        return pjsua2JNI.MediaConfig_maxMediaPorts_get(this.swigCPtr, this);
    }

    public boolean getNoVad() {
        return pjsua2JNI.MediaConfig_noVad_get(this.swigCPtr, this);
    }

    public long getPtime() {
        return pjsua2JNI.MediaConfig_ptime_get(this.swigCPtr, this);
    }

    public long getQuality() {
        return pjsua2JNI.MediaConfig_quality_get(this.swigCPtr, this);
    }

    public long getRxDropPct() {
        return pjsua2JNI.MediaConfig_rxDropPct_get(this.swigCPtr, this);
    }

    public int getSndAutoCloseTime() {
        return pjsua2JNI.MediaConfig_sndAutoCloseTime_get(this.swigCPtr, this);
    }

    public long getSndClockRate() {
        return pjsua2JNI.MediaConfig_sndClockRate_get(this.swigCPtr, this);
    }

    public long getSndPlayLatency() {
        return pjsua2JNI.MediaConfig_sndPlayLatency_get(this.swigCPtr, this);
    }

    public long getSndRecLatency() {
        return pjsua2JNI.MediaConfig_sndRecLatency_get(this.swigCPtr, this);
    }

    public long getThreadCnt() {
        return pjsua2JNI.MediaConfig_threadCnt_get(this.swigCPtr, this);
    }

    public long getTxDropPct() {
        return pjsua2JNI.MediaConfig_txDropPct_get(this.swigCPtr, this);
    }

    public boolean getVidPreviewEnableNative() {
        return pjsua2JNI.MediaConfig_vidPreviewEnableNative_get(this.swigCPtr, this);
    }

    public void setAudioFramePtime(long j2) {
        pjsua2JNI.MediaConfig_audioFramePtime_set(this.swigCPtr, this, j2);
    }

    public void setChannelCount(long j2) {
        pjsua2JNI.MediaConfig_channelCount_set(this.swigCPtr, this, j2);
    }

    public void setClockRate(long j2) {
        pjsua2JNI.MediaConfig_clockRate_set(this.swigCPtr, this, j2);
    }

    public void setEcOptions(long j2) {
        pjsua2JNI.MediaConfig_ecOptions_set(this.swigCPtr, this, j2);
    }

    public void setEcTailLen(long j2) {
        pjsua2JNI.MediaConfig_ecTailLen_set(this.swigCPtr, this, j2);
    }

    public void setHasIoqueue(boolean z) {
        pjsua2JNI.MediaConfig_hasIoqueue_set(this.swigCPtr, this, z);
    }

    public void setIlbcMode(long j2) {
        pjsua2JNI.MediaConfig_ilbcMode_set(this.swigCPtr, this, j2);
    }

    public void setJbInit(int i2) {
        pjsua2JNI.MediaConfig_jbInit_set(this.swigCPtr, this, i2);
    }

    public void setJbMax(int i2) {
        pjsua2JNI.MediaConfig_jbMax_set(this.swigCPtr, this, i2);
    }

    public void setJbMaxPre(int i2) {
        pjsua2JNI.MediaConfig_jbMaxPre_set(this.swigCPtr, this, i2);
    }

    public void setJbMinPre(int i2) {
        pjsua2JNI.MediaConfig_jbMinPre_set(this.swigCPtr, this, i2);
    }

    public void setMaxMediaPorts(long j2) {
        pjsua2JNI.MediaConfig_maxMediaPorts_set(this.swigCPtr, this, j2);
    }

    public void setNoVad(boolean z) {
        pjsua2JNI.MediaConfig_noVad_set(this.swigCPtr, this, z);
    }

    public void setPtime(long j2) {
        pjsua2JNI.MediaConfig_ptime_set(this.swigCPtr, this, j2);
    }

    public void setQuality(long j2) {
        pjsua2JNI.MediaConfig_quality_set(this.swigCPtr, this, j2);
    }

    public void setRxDropPct(long j2) {
        pjsua2JNI.MediaConfig_rxDropPct_set(this.swigCPtr, this, j2);
    }

    public void setSndAutoCloseTime(int i2) {
        pjsua2JNI.MediaConfig_sndAutoCloseTime_set(this.swigCPtr, this, i2);
    }

    public void setSndClockRate(long j2) {
        pjsua2JNI.MediaConfig_sndClockRate_set(this.swigCPtr, this, j2);
    }

    public void setSndPlayLatency(long j2) {
        pjsua2JNI.MediaConfig_sndPlayLatency_set(this.swigCPtr, this, j2);
    }

    public void setSndRecLatency(long j2) {
        pjsua2JNI.MediaConfig_sndRecLatency_set(this.swigCPtr, this, j2);
    }

    public void setThreadCnt(long j2) {
        pjsua2JNI.MediaConfig_threadCnt_set(this.swigCPtr, this, j2);
    }

    public void setTxDropPct(long j2) {
        pjsua2JNI.MediaConfig_txDropPct_set(this.swigCPtr, this, j2);
    }

    public void setVidPreviewEnableNative(boolean z) {
        pjsua2JNI.MediaConfig_vidPreviewEnableNative_set(this.swigCPtr, this, z);
    }

    public MediaConfig() {
        this(pjsua2JNI.new_MediaConfig(), true);
    }
}
