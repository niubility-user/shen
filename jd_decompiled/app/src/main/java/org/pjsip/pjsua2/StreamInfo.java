package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class StreamInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(StreamInfo streamInfo) {
        if (streamInfo == null) {
            return 0L;
        }
        return streamInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StreamInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CodecParam getAudCodecParam() {
        long StreamInfo_audCodecParam_get = pjsua2JNI.StreamInfo_audCodecParam_get(this.swigCPtr, this);
        if (StreamInfo_audCodecParam_get == 0) {
            return null;
        }
        return new CodecParam(StreamInfo_audCodecParam_get, false);
    }

    public long getCodecClockRate() {
        return pjsua2JNI.StreamInfo_codecClockRate_get(this.swigCPtr, this);
    }

    public String getCodecName() {
        return pjsua2JNI.StreamInfo_codecName_get(this.swigCPtr, this);
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.StreamInfo_dir_get(this.swigCPtr, this));
    }

    public pjmedia_tp_proto getProto() {
        return pjmedia_tp_proto.swigToEnum(pjsua2JNI.StreamInfo_proto_get(this.swigCPtr, this));
    }

    public String getRemoteRtcpAddress() {
        return pjsua2JNI.StreamInfo_remoteRtcpAddress_get(this.swigCPtr, this);
    }

    public String getRemoteRtpAddress() {
        return pjsua2JNI.StreamInfo_remoteRtpAddress_get(this.swigCPtr, this);
    }

    public long getRxPt() {
        return pjsua2JNI.StreamInfo_rxPt_get(this.swigCPtr, this);
    }

    public long getTxPt() {
        return pjsua2JNI.StreamInfo_txPt_get(this.swigCPtr, this);
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.StreamInfo_type_get(this.swigCPtr, this));
    }

    public void setAudCodecParam(CodecParam codecParam) {
        pjsua2JNI.StreamInfo_audCodecParam_set(this.swigCPtr, this, CodecParam.getCPtr(codecParam), codecParam);
    }

    public void setCodecClockRate(long j2) {
        pjsua2JNI.StreamInfo_codecClockRate_set(this.swigCPtr, this, j2);
    }

    public void setCodecName(String str) {
        pjsua2JNI.StreamInfo_codecName_set(this.swigCPtr, this, str);
    }

    public void setDir(pjmedia_dir pjmedia_dirVar) {
        pjsua2JNI.StreamInfo_dir_set(this.swigCPtr, this, pjmedia_dirVar.swigValue());
    }

    public void setProto(pjmedia_tp_proto pjmedia_tp_protoVar) {
        pjsua2JNI.StreamInfo_proto_set(this.swigCPtr, this, pjmedia_tp_protoVar.swigValue());
    }

    public void setRemoteRtcpAddress(String str) {
        pjsua2JNI.StreamInfo_remoteRtcpAddress_set(this.swigCPtr, this, str);
    }

    public void setRemoteRtpAddress(String str) {
        pjsua2JNI.StreamInfo_remoteRtpAddress_set(this.swigCPtr, this, str);
    }

    public void setRxPt(long j2) {
        pjsua2JNI.StreamInfo_rxPt_set(this.swigCPtr, this, j2);
    }

    public void setTxPt(long j2) {
        pjsua2JNI.StreamInfo_txPt_set(this.swigCPtr, this, j2);
    }

    public void setType(pjmedia_type pjmedia_typeVar) {
        pjsua2JNI.StreamInfo_type_set(this.swigCPtr, this, pjmedia_typeVar.swigValue());
    }

    public StreamInfo() {
        this(pjsua2JNI.new_StreamInfo(), true);
    }
}
