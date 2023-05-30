package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecParamInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecParamInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecParamInfo codecParamInfo) {
        if (codecParamInfo == null) {
            return 0L;
        }
        return codecParamInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecParamInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAvgBps() {
        return pjsua2JNI.CodecParamInfo_avgBps_get(this.swigCPtr, this);
    }

    public long getChannelCnt() {
        return pjsua2JNI.CodecParamInfo_channelCnt_get(this.swigCPtr, this);
    }

    public long getClockRate() {
        return pjsua2JNI.CodecParamInfo_clockRate_get(this.swigCPtr, this);
    }

    public pjmedia_format_id getFmtId() {
        return pjmedia_format_id.swigToEnum(pjsua2JNI.CodecParamInfo_fmtId_get(this.swigCPtr, this));
    }

    public long getFrameLen() {
        return pjsua2JNI.CodecParamInfo_frameLen_get(this.swigCPtr, this);
    }

    public long getMaxBps() {
        return pjsua2JNI.CodecParamInfo_maxBps_get(this.swigCPtr, this);
    }

    public long getMaxRxFrameSize() {
        return pjsua2JNI.CodecParamInfo_maxRxFrameSize_get(this.swigCPtr, this);
    }

    public long getPcmBitsPerSample() {
        return pjsua2JNI.CodecParamInfo_pcmBitsPerSample_get(this.swigCPtr, this);
    }

    public long getPt() {
        return pjsua2JNI.CodecParamInfo_pt_get(this.swigCPtr, this);
    }

    public void setAvgBps(long j2) {
        pjsua2JNI.CodecParamInfo_avgBps_set(this.swigCPtr, this, j2);
    }

    public void setChannelCnt(long j2) {
        pjsua2JNI.CodecParamInfo_channelCnt_set(this.swigCPtr, this, j2);
    }

    public void setClockRate(long j2) {
        pjsua2JNI.CodecParamInfo_clockRate_set(this.swigCPtr, this, j2);
    }

    public void setFmtId(pjmedia_format_id pjmedia_format_idVar) {
        pjsua2JNI.CodecParamInfo_fmtId_set(this.swigCPtr, this, pjmedia_format_idVar.swigValue());
    }

    public void setFrameLen(long j2) {
        pjsua2JNI.CodecParamInfo_frameLen_set(this.swigCPtr, this, j2);
    }

    public void setMaxBps(long j2) {
        pjsua2JNI.CodecParamInfo_maxBps_set(this.swigCPtr, this, j2);
    }

    public void setMaxRxFrameSize(long j2) {
        pjsua2JNI.CodecParamInfo_maxRxFrameSize_set(this.swigCPtr, this, j2);
    }

    public void setPcmBitsPerSample(long j2) {
        pjsua2JNI.CodecParamInfo_pcmBitsPerSample_set(this.swigCPtr, this, j2);
    }

    public void setPt(long j2) {
        pjsua2JNI.CodecParamInfo_pt_set(this.swigCPtr, this, j2);
    }

    public CodecParamInfo() {
        this(pjsua2JNI.new_CodecParamInfo(), true);
    }
}
