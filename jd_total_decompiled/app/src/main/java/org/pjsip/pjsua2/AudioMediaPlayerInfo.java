package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AudioMediaPlayerInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioMediaPlayerInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(AudioMediaPlayerInfo audioMediaPlayerInfo) {
        if (audioMediaPlayerInfo == null) {
            return 0L;
        }
        return audioMediaPlayerInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaPlayerInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjmedia_format_id getFormatId() {
        return pjmedia_format_id.swigToEnum(pjsua2JNI.AudioMediaPlayerInfo_formatId_get(this.swigCPtr, this));
    }

    public long getPayloadBitsPerSample() {
        return pjsua2JNI.AudioMediaPlayerInfo_payloadBitsPerSample_get(this.swigCPtr, this);
    }

    public long getSizeBytes() {
        return pjsua2JNI.AudioMediaPlayerInfo_sizeBytes_get(this.swigCPtr, this);
    }

    public long getSizeSamples() {
        return pjsua2JNI.AudioMediaPlayerInfo_sizeSamples_get(this.swigCPtr, this);
    }

    public void setFormatId(pjmedia_format_id pjmedia_format_idVar) {
        pjsua2JNI.AudioMediaPlayerInfo_formatId_set(this.swigCPtr, this, pjmedia_format_idVar.swigValue());
    }

    public void setPayloadBitsPerSample(long j2) {
        pjsua2JNI.AudioMediaPlayerInfo_payloadBitsPerSample_set(this.swigCPtr, this, j2);
    }

    public void setSizeBytes(long j2) {
        pjsua2JNI.AudioMediaPlayerInfo_sizeBytes_set(this.swigCPtr, this, j2);
    }

    public void setSizeSamples(long j2) {
        pjsua2JNI.AudioMediaPlayerInfo_sizeSamples_set(this.swigCPtr, this, j2);
    }

    public AudioMediaPlayerInfo() {
        this(pjsua2JNI.new_AudioMediaPlayerInfo(), true);
    }
}
