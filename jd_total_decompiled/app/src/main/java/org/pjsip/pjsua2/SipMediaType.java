package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class SipMediaType {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipMediaType(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipMediaType sipMediaType) {
        if (sipMediaType == null) {
            return 0L;
        }
        return sipMediaType.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMediaType(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getSubType() {
        return pjsua2JNI.SipMediaType_subType_get(this.swigCPtr, this);
    }

    public String getType() {
        return pjsua2JNI.SipMediaType_type_get(this.swigCPtr, this);
    }

    public void setSubType(String str) {
        pjsua2JNI.SipMediaType_subType_set(this.swigCPtr, this, str);
    }

    public void setType(String str) {
        pjsua2JNI.SipMediaType_type_set(this.swigCPtr, this, str);
    }

    public SipMediaType() {
        this(pjsua2JNI.new_SipMediaType(), true);
    }
}
