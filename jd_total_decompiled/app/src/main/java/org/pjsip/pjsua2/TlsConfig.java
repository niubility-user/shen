package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class TlsConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TlsConfig tlsConfig) {
        if (tlsConfig == null) {
            return 0L;
        }
        return tlsConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TlsConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getCaListFile() {
        return pjsua2JNI.TlsConfig_CaListFile_get(this.swigCPtr, this);
    }

    public String getCertFile() {
        return pjsua2JNI.TlsConfig_certFile_get(this.swigCPtr, this);
    }

    public IntVector getCiphers() {
        long TlsConfig_ciphers_get = pjsua2JNI.TlsConfig_ciphers_get(this.swigCPtr, this);
        if (TlsConfig_ciphers_get == 0) {
            return null;
        }
        return new IntVector(TlsConfig_ciphers_get, false);
    }

    public pjsip_ssl_method getMethod() {
        return pjsip_ssl_method.swigToEnum(pjsua2JNI.TlsConfig_method_get(this.swigCPtr, this));
    }

    public long getMsecTimeout() {
        return pjsua2JNI.TlsConfig_msecTimeout_get(this.swigCPtr, this);
    }

    public String getPassword() {
        return pjsua2JNI.TlsConfig_password_get(this.swigCPtr, this);
    }

    public String getPrivKeyFile() {
        return pjsua2JNI.TlsConfig_privKeyFile_get(this.swigCPtr, this);
    }

    public long getProto() {
        return pjsua2JNI.TlsConfig_proto_get(this.swigCPtr, this);
    }

    public boolean getQosIgnoreError() {
        return pjsua2JNI.TlsConfig_qosIgnoreError_get(this.swigCPtr, this);
    }

    public pj_qos_params getQosParams() {
        long TlsConfig_qosParams_get = pjsua2JNI.TlsConfig_qosParams_get(this.swigCPtr, this);
        if (TlsConfig_qosParams_get == 0) {
            return null;
        }
        return new pj_qos_params(TlsConfig_qosParams_get, false);
    }

    public pj_qos_type getQosType() {
        return pj_qos_type.swigToEnum(pjsua2JNI.TlsConfig_qosType_get(this.swigCPtr, this));
    }

    public boolean getRequireClientCert() {
        return pjsua2JNI.TlsConfig_requireClientCert_get(this.swigCPtr, this);
    }

    public boolean getVerifyClient() {
        return pjsua2JNI.TlsConfig_verifyClient_get(this.swigCPtr, this);
    }

    public boolean getVerifyServer() {
        return pjsua2JNI.TlsConfig_verifyServer_get(this.swigCPtr, this);
    }

    public void setCaListFile(String str) {
        pjsua2JNI.TlsConfig_CaListFile_set(this.swigCPtr, this, str);
    }

    public void setCertFile(String str) {
        pjsua2JNI.TlsConfig_certFile_set(this.swigCPtr, this, str);
    }

    public void setCiphers(IntVector intVector) {
        pjsua2JNI.TlsConfig_ciphers_set(this.swigCPtr, this, IntVector.getCPtr(intVector), intVector);
    }

    public void setMethod(pjsip_ssl_method pjsip_ssl_methodVar) {
        pjsua2JNI.TlsConfig_method_set(this.swigCPtr, this, pjsip_ssl_methodVar.swigValue());
    }

    public void setMsecTimeout(long j2) {
        pjsua2JNI.TlsConfig_msecTimeout_set(this.swigCPtr, this, j2);
    }

    public void setPassword(String str) {
        pjsua2JNI.TlsConfig_password_set(this.swigCPtr, this, str);
    }

    public void setPrivKeyFile(String str) {
        pjsua2JNI.TlsConfig_privKeyFile_set(this.swigCPtr, this, str);
    }

    public void setProto(long j2) {
        pjsua2JNI.TlsConfig_proto_set(this.swigCPtr, this, j2);
    }

    public void setQosIgnoreError(boolean z) {
        pjsua2JNI.TlsConfig_qosIgnoreError_set(this.swigCPtr, this, z);
    }

    public void setQosParams(pj_qos_params pj_qos_paramsVar) {
        pjsua2JNI.TlsConfig_qosParams_set(this.swigCPtr, this, pj_qos_params.getCPtr(pj_qos_paramsVar), pj_qos_paramsVar);
    }

    public void setQosType(pj_qos_type pj_qos_typeVar) {
        pjsua2JNI.TlsConfig_qosType_set(this.swigCPtr, this, pj_qos_typeVar.swigValue());
    }

    public void setRequireClientCert(boolean z) {
        pjsua2JNI.TlsConfig_requireClientCert_set(this.swigCPtr, this, z);
    }

    public void setVerifyClient(boolean z) {
        pjsua2JNI.TlsConfig_verifyClient_set(this.swigCPtr, this, z);
    }

    public void setVerifyServer(boolean z) {
        pjsua2JNI.TlsConfig_verifyServer_set(this.swigCPtr, this, z);
    }

    public TlsConfig() {
        this(pjsua2JNI.new_TlsConfig(), true);
    }
}
