package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SslCertInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SslCertInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SslCertInfo sslCertInfo) {
        if (sslCertInfo == null) {
            return 0L;
        }
        return sslCertInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SslCertInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getIssuerCn() {
        return pjsua2JNI.SslCertInfo_issuerCn_get(this.swigCPtr, this);
    }

    public String getIssuerInfo() {
        return pjsua2JNI.SslCertInfo_issuerInfo_get(this.swigCPtr, this);
    }

    public String getRaw() {
        return pjsua2JNI.SslCertInfo_raw_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_unsigned_char getSerialNo() {
        long SslCertInfo_serialNo_get = pjsua2JNI.SslCertInfo_serialNo_get(this.swigCPtr, this);
        if (SslCertInfo_serialNo_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(SslCertInfo_serialNo_get, false);
    }

    public SWIGTYPE_p_vectorT_pj__SslCertName_t getSubjectAltName() {
        long SslCertInfo_subjectAltName_get = pjsua2JNI.SslCertInfo_subjectAltName_get(this.swigCPtr, this);
        if (SslCertInfo_subjectAltName_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_vectorT_pj__SslCertName_t(SslCertInfo_subjectAltName_get, false);
    }

    public String getSubjectCn() {
        return pjsua2JNI.SslCertInfo_subjectCn_get(this.swigCPtr, this);
    }

    public String getSubjectInfo() {
        return pjsua2JNI.SslCertInfo_subjectInfo_get(this.swigCPtr, this);
    }

    public TimeVal getValidityEnd() {
        long SslCertInfo_validityEnd_get = pjsua2JNI.SslCertInfo_validityEnd_get(this.swigCPtr, this);
        if (SslCertInfo_validityEnd_get == 0) {
            return null;
        }
        return new TimeVal(SslCertInfo_validityEnd_get, false);
    }

    public boolean getValidityGmt() {
        return pjsua2JNI.SslCertInfo_validityGmt_get(this.swigCPtr, this);
    }

    public TimeVal getValidityStart() {
        long SslCertInfo_validityStart_get = pjsua2JNI.SslCertInfo_validityStart_get(this.swigCPtr, this);
        if (SslCertInfo_validityStart_get == 0) {
            return null;
        }
        return new TimeVal(SslCertInfo_validityStart_get, false);
    }

    public long getVersion() {
        return pjsua2JNI.SslCertInfo_version_get(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return pjsua2JNI.SslCertInfo_isEmpty(this.swigCPtr, this);
    }

    public void setIssuerCn(String str) {
        pjsua2JNI.SslCertInfo_issuerCn_set(this.swigCPtr, this, str);
    }

    public void setIssuerInfo(String str) {
        pjsua2JNI.SslCertInfo_issuerInfo_set(this.swigCPtr, this, str);
    }

    public void setRaw(String str) {
        pjsua2JNI.SslCertInfo_raw_set(this.swigCPtr, this, str);
    }

    public void setSerialNo(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        pjsua2JNI.SslCertInfo_serialNo_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public void setSubjectAltName(SWIGTYPE_p_vectorT_pj__SslCertName_t sWIGTYPE_p_vectorT_pj__SslCertName_t) {
        pjsua2JNI.SslCertInfo_subjectAltName_set(this.swigCPtr, this, SWIGTYPE_p_vectorT_pj__SslCertName_t.getCPtr(sWIGTYPE_p_vectorT_pj__SslCertName_t));
    }

    public void setSubjectCn(String str) {
        pjsua2JNI.SslCertInfo_subjectCn_set(this.swigCPtr, this, str);
    }

    public void setSubjectInfo(String str) {
        pjsua2JNI.SslCertInfo_subjectInfo_set(this.swigCPtr, this, str);
    }

    public void setValidityEnd(TimeVal timeVal) {
        pjsua2JNI.SslCertInfo_validityEnd_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public void setValidityGmt(boolean z) {
        pjsua2JNI.SslCertInfo_validityGmt_set(this.swigCPtr, this, z);
    }

    public void setValidityStart(TimeVal timeVal) {
        pjsua2JNI.SslCertInfo_validityStart_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public void setVersion(long j2) {
        pjsua2JNI.SslCertInfo_version_set(this.swigCPtr, this, j2);
    }

    public SslCertInfo() {
        this(pjsua2JNI.new_SslCertInfo(), true);
    }
}
