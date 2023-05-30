package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecParamSetting {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecParamSetting(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecParamSetting codecParamSetting) {
        if (codecParamSetting == null) {
            return 0L;
        }
        return codecParamSetting.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecParamSetting(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getCng() {
        return pjsua2JNI.CodecParamSetting_cng_get(this.swigCPtr, this);
    }

    public CodecFmtpVector getDecFmtp() {
        long CodecParamSetting_decFmtp_get = pjsua2JNI.CodecParamSetting_decFmtp_get(this.swigCPtr, this);
        if (CodecParamSetting_decFmtp_get == 0) {
            return null;
        }
        return new CodecFmtpVector(CodecParamSetting_decFmtp_get, false);
    }

    public CodecFmtpVector getEncFmtp() {
        long CodecParamSetting_encFmtp_get = pjsua2JNI.CodecParamSetting_encFmtp_get(this.swigCPtr, this);
        if (CodecParamSetting_encFmtp_get == 0) {
            return null;
        }
        return new CodecFmtpVector(CodecParamSetting_encFmtp_get, false);
    }

    public long getFrmPerPkt() {
        return pjsua2JNI.CodecParamSetting_frmPerPkt_get(this.swigCPtr, this);
    }

    public boolean getPenh() {
        return pjsua2JNI.CodecParamSetting_penh_get(this.swigCPtr, this);
    }

    public boolean getPlc() {
        return pjsua2JNI.CodecParamSetting_plc_get(this.swigCPtr, this);
    }

    public boolean getReserved() {
        return pjsua2JNI.CodecParamSetting_reserved_get(this.swigCPtr, this);
    }

    public boolean getVad() {
        return pjsua2JNI.CodecParamSetting_vad_get(this.swigCPtr, this);
    }

    public void setCng(boolean z) {
        pjsua2JNI.CodecParamSetting_cng_set(this.swigCPtr, this, z);
    }

    public void setDecFmtp(CodecFmtpVector codecFmtpVector) {
        pjsua2JNI.CodecParamSetting_decFmtp_set(this.swigCPtr, this, CodecFmtpVector.getCPtr(codecFmtpVector), codecFmtpVector);
    }

    public void setEncFmtp(CodecFmtpVector codecFmtpVector) {
        pjsua2JNI.CodecParamSetting_encFmtp_set(this.swigCPtr, this, CodecFmtpVector.getCPtr(codecFmtpVector), codecFmtpVector);
    }

    public void setFrmPerPkt(long j2) {
        pjsua2JNI.CodecParamSetting_frmPerPkt_set(this.swigCPtr, this, j2);
    }

    public void setPenh(boolean z) {
        pjsua2JNI.CodecParamSetting_penh_set(this.swigCPtr, this, z);
    }

    public void setPlc(boolean z) {
        pjsua2JNI.CodecParamSetting_plc_set(this.swigCPtr, this, z);
    }

    public void setReserved(boolean z) {
        pjsua2JNI.CodecParamSetting_reserved_set(this.swigCPtr, this, z);
    }

    public void setVad(boolean z) {
        pjsua2JNI.CodecParamSetting_vad_set(this.swigCPtr, this, z);
    }

    public CodecParamSetting() {
        this(pjsua2JNI.new_CodecParamSetting(), true);
    }
}
