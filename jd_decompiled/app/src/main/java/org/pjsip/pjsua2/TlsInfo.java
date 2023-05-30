package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class TlsInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public TlsInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(TlsInfo tlsInfo) {
        if (tlsInfo == null) {
            return 0L;
        }
        return tlsInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TlsInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pj_ssl_cipher getCipher() {
        return pj_ssl_cipher.swigToEnum(pjsua2JNI.TlsInfo_cipher_get(this.swigCPtr, this));
    }

    public String getCipherName() {
        return pjsua2JNI.TlsInfo_cipherName_get(this.swigCPtr, this);
    }

    public boolean getEstablished() {
        return pjsua2JNI.TlsInfo_established_get(this.swigCPtr, this);
    }

    public String getLocalAddr() {
        return pjsua2JNI.TlsInfo_localAddr_get(this.swigCPtr, this);
    }

    public SslCertInfo getLocalCertInfo() {
        long TlsInfo_localCertInfo_get = pjsua2JNI.TlsInfo_localCertInfo_get(this.swigCPtr, this);
        if (TlsInfo_localCertInfo_get == 0) {
            return null;
        }
        return new SslCertInfo(TlsInfo_localCertInfo_get, false);
    }

    public long getProtocol() {
        return pjsua2JNI.TlsInfo_protocol_get(this.swigCPtr, this);
    }

    public String getRemoteAddr() {
        return pjsua2JNI.TlsInfo_remoteAddr_get(this.swigCPtr, this);
    }

    public SslCertInfo getRemoteCertInfo() {
        long TlsInfo_remoteCertInfo_get = pjsua2JNI.TlsInfo_remoteCertInfo_get(this.swigCPtr, this);
        if (TlsInfo_remoteCertInfo_get == 0) {
            return null;
        }
        return new SslCertInfo(TlsInfo_remoteCertInfo_get, false);
    }

    public StringVector getVerifyMsgs() {
        long TlsInfo_verifyMsgs_get = pjsua2JNI.TlsInfo_verifyMsgs_get(this.swigCPtr, this);
        if (TlsInfo_verifyMsgs_get == 0) {
            return null;
        }
        return new StringVector(TlsInfo_verifyMsgs_get, false);
    }

    public long getVerifyStatus() {
        return pjsua2JNI.TlsInfo_verifyStatus_get(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return pjsua2JNI.TlsInfo_isEmpty(this.swigCPtr, this);
    }

    public void setCipher(pj_ssl_cipher pj_ssl_cipherVar) {
        pjsua2JNI.TlsInfo_cipher_set(this.swigCPtr, this, pj_ssl_cipherVar.swigValue());
    }

    public void setCipherName(String str) {
        pjsua2JNI.TlsInfo_cipherName_set(this.swigCPtr, this, str);
    }

    public void setEstablished(boolean z) {
        pjsua2JNI.TlsInfo_established_set(this.swigCPtr, this, z);
    }

    public void setLocalAddr(String str) {
        pjsua2JNI.TlsInfo_localAddr_set(this.swigCPtr, this, str);
    }

    public void setLocalCertInfo(SslCertInfo sslCertInfo) {
        pjsua2JNI.TlsInfo_localCertInfo_set(this.swigCPtr, this, SslCertInfo.getCPtr(sslCertInfo), sslCertInfo);
    }

    public void setProtocol(long j2) {
        pjsua2JNI.TlsInfo_protocol_set(this.swigCPtr, this, j2);
    }

    public void setRemoteAddr(String str) {
        pjsua2JNI.TlsInfo_remoteAddr_set(this.swigCPtr, this, str);
    }

    public void setRemoteCertInfo(SslCertInfo sslCertInfo) {
        pjsua2JNI.TlsInfo_remoteCertInfo_set(this.swigCPtr, this, SslCertInfo.getCPtr(sslCertInfo), sslCertInfo);
    }

    public void setVerifyMsgs(StringVector stringVector) {
        pjsua2JNI.TlsInfo_verifyMsgs_set(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public void setVerifyStatus(long j2) {
        pjsua2JNI.TlsInfo_verifyStatus_set(this.swigCPtr, this, j2);
    }

    public TlsInfo() {
        this(pjsua2JNI.new_TlsInfo(), true);
    }
}
