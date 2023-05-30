package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class MediaTransportInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaTransportInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(MediaTransportInfo mediaTransportInfo) {
        if (mediaTransportInfo == null) {
            return 0L;
        }
        return mediaTransportInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaTransportInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getSrcRtcpName() {
        return pjsua2JNI.MediaTransportInfo_srcRtcpName_get(this.swigCPtr, this);
    }

    public String getSrcRtpName() {
        return pjsua2JNI.MediaTransportInfo_srcRtpName_get(this.swigCPtr, this);
    }

    public void setSrcRtcpName(String str) {
        pjsua2JNI.MediaTransportInfo_srcRtcpName_set(this.swigCPtr, this, str);
    }

    public void setSrcRtpName(String str) {
        pjsua2JNI.MediaTransportInfo_srcRtpName_set(this.swigCPtr, this, str);
    }

    public MediaTransportInfo() {
        this(pjsua2JNI.new_MediaTransportInfo(), true);
    }
}
