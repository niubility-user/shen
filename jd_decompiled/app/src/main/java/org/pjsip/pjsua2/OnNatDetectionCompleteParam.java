package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnNatDetectionCompleteParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnNatDetectionCompleteParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnNatDetectionCompleteParam onNatDetectionCompleteParam) {
        if (onNatDetectionCompleteParam == null) {
            return 0L;
        }
        return onNatDetectionCompleteParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnNatDetectionCompleteParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pj_stun_nat_type getNatType() {
        return pj_stun_nat_type.swigToEnum(pjsua2JNI.OnNatDetectionCompleteParam_natType_get(this.swigCPtr, this));
    }

    public String getNatTypeName() {
        return pjsua2JNI.OnNatDetectionCompleteParam_natTypeName_get(this.swigCPtr, this);
    }

    public String getReason() {
        return pjsua2JNI.OnNatDetectionCompleteParam_reason_get(this.swigCPtr, this);
    }

    public int getStatus() {
        return pjsua2JNI.OnNatDetectionCompleteParam_status_get(this.swigCPtr, this);
    }

    public void setNatType(pj_stun_nat_type pj_stun_nat_typeVar) {
        pjsua2JNI.OnNatDetectionCompleteParam_natType_set(this.swigCPtr, this, pj_stun_nat_typeVar.swigValue());
    }

    public void setNatTypeName(String str) {
        pjsua2JNI.OnNatDetectionCompleteParam_natTypeName_set(this.swigCPtr, this, str);
    }

    public void setReason(String str) {
        pjsua2JNI.OnNatDetectionCompleteParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatus(int i2) {
        pjsua2JNI.OnNatDetectionCompleteParam_status_set(this.swigCPtr, this, i2);
    }

    public OnNatDetectionCompleteParam() {
        this(pjsua2JNI.new_OnNatDetectionCompleteParam(), true);
    }
}
