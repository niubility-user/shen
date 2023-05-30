package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class TransportConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public TransportConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(TransportConfig transportConfig) {
        if (transportConfig == null) {
            return 0L;
        }
        return transportConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TransportConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getBoundAddress() {
        return pjsua2JNI.TransportConfig_boundAddress_get(this.swigCPtr, this);
    }

    public long getPort() {
        return pjsua2JNI.TransportConfig_port_get(this.swigCPtr, this);
    }

    public long getPortRange() {
        return pjsua2JNI.TransportConfig_portRange_get(this.swigCPtr, this);
    }

    public String getPublicAddress() {
        return pjsua2JNI.TransportConfig_publicAddress_get(this.swigCPtr, this);
    }

    public pj_qos_params getQosParams() {
        long TransportConfig_qosParams_get = pjsua2JNI.TransportConfig_qosParams_get(this.swigCPtr, this);
        if (TransportConfig_qosParams_get == 0) {
            return null;
        }
        return new pj_qos_params(TransportConfig_qosParams_get, false);
    }

    public pj_qos_type getQosType() {
        return pj_qos_type.swigToEnum(pjsua2JNI.TransportConfig_qosType_get(this.swigCPtr, this));
    }

    public TlsConfig getTlsConfig() {
        long TransportConfig_tlsConfig_get = pjsua2JNI.TransportConfig_tlsConfig_get(this.swigCPtr, this);
        if (TransportConfig_tlsConfig_get == 0) {
            return null;
        }
        return new TlsConfig(TransportConfig_tlsConfig_get, false);
    }

    public void setBoundAddress(String str) {
        pjsua2JNI.TransportConfig_boundAddress_set(this.swigCPtr, this, str);
    }

    public void setPort(long j2) {
        pjsua2JNI.TransportConfig_port_set(this.swigCPtr, this, j2);
    }

    public void setPortRange(long j2) {
        pjsua2JNI.TransportConfig_portRange_set(this.swigCPtr, this, j2);
    }

    public void setPublicAddress(String str) {
        pjsua2JNI.TransportConfig_publicAddress_set(this.swigCPtr, this, str);
    }

    public void setQosParams(pj_qos_params pj_qos_paramsVar) {
        pjsua2JNI.TransportConfig_qosParams_set(this.swigCPtr, this, pj_qos_params.getCPtr(pj_qos_paramsVar), pj_qos_paramsVar);
    }

    public void setQosType(pj_qos_type pj_qos_typeVar) {
        pjsua2JNI.TransportConfig_qosType_set(this.swigCPtr, this, pj_qos_typeVar.swigValue());
    }

    public void setTlsConfig(TlsConfig tlsConfig) {
        pjsua2JNI.TransportConfig_tlsConfig_set(this.swigCPtr, this, TlsConfig.getCPtr(tlsConfig), tlsConfig);
    }

    public TransportConfig() {
        this(pjsua2JNI.new_TransportConfig(), true);
    }
}
