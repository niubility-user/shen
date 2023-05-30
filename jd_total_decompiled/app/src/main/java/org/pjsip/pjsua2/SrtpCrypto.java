package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SrtpCrypto {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected SrtpCrypto(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(SrtpCrypto srtpCrypto) {
        if (srtpCrypto == null) {
            return 0L;
        }
        return srtpCrypto.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SrtpCrypto(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getFlags() {
        return pjsua2JNI.SrtpCrypto_flags_get(this.swigCPtr, this);
    }

    public String getKey() {
        return pjsua2JNI.SrtpCrypto_key_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.SrtpCrypto_name_get(this.swigCPtr, this);
    }

    public void setFlags(long j2) {
        pjsua2JNI.SrtpCrypto_flags_set(this.swigCPtr, this, j2);
    }

    public void setKey(String str) {
        pjsua2JNI.SrtpCrypto_key_set(this.swigCPtr, this, str);
    }

    public void setName(String str) {
        pjsua2JNI.SrtpCrypto_name_set(this.swigCPtr, this, str);
    }

    public SrtpCrypto() {
        this(pjsua2JNI.new_SrtpCrypto(), true);
    }
}
