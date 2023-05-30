package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SslCertName {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected SslCertName(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(SslCertName sslCertName) {
        if (sslCertName == null) {
            return 0L;
        }
        return sslCertName.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SslCertName(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getName() {
        return pjsua2JNI.SslCertName_name_get(this.swigCPtr, this);
    }

    public pj_ssl_cert_name_type getType() {
        return pj_ssl_cert_name_type.swigToEnum(pjsua2JNI.SslCertName_type_get(this.swigCPtr, this));
    }

    public void setName(String str) {
        pjsua2JNI.SslCertName_name_set(this.swigCPtr, this, str);
    }

    public void setType(pj_ssl_cert_name_type pj_ssl_cert_name_typeVar) {
        pjsua2JNI.SslCertName_type_set(this.swigCPtr, this, pj_ssl_cert_name_typeVar.swigValue());
    }

    public SslCertName() {
        this(pjsua2JNI.new_SslCertName(), true);
    }
}
