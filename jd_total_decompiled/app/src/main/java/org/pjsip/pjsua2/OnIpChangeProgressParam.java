package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnIpChangeProgressParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public OnIpChangeProgressParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(OnIpChangeProgressParam onIpChangeProgressParam) {
        if (onIpChangeProgressParam == null) {
            return 0L;
        }
        return onIpChangeProgressParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIpChangeProgressParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getAccId() {
        return pjsua2JNI.OnIpChangeProgressParam_accId_get(this.swigCPtr, this);
    }

    public int getCallId() {
        return pjsua2JNI.OnIpChangeProgressParam_callId_get(this.swigCPtr, this);
    }

    public pjsua_ip_change_op getOp() {
        return pjsua_ip_change_op.swigToEnum(pjsua2JNI.OnIpChangeProgressParam_op_get(this.swigCPtr, this));
    }

    public RegProgressParam getRegInfo() {
        long OnIpChangeProgressParam_regInfo_get = pjsua2JNI.OnIpChangeProgressParam_regInfo_get(this.swigCPtr, this);
        if (OnIpChangeProgressParam_regInfo_get == 0) {
            return null;
        }
        return new RegProgressParam(OnIpChangeProgressParam_regInfo_get, false);
    }

    public int getStatus() {
        return pjsua2JNI.OnIpChangeProgressParam_status_get(this.swigCPtr, this);
    }

    public int getTransportId() {
        return pjsua2JNI.OnIpChangeProgressParam_transportId_get(this.swigCPtr, this);
    }

    public void setAccId(int i2) {
        pjsua2JNI.OnIpChangeProgressParam_accId_set(this.swigCPtr, this, i2);
    }

    public void setCallId(int i2) {
        pjsua2JNI.OnIpChangeProgressParam_callId_set(this.swigCPtr, this, i2);
    }

    public void setOp(pjsua_ip_change_op pjsua_ip_change_opVar) {
        pjsua2JNI.OnIpChangeProgressParam_op_set(this.swigCPtr, this, pjsua_ip_change_opVar.swigValue());
    }

    public void setRegInfo(RegProgressParam regProgressParam) {
        pjsua2JNI.OnIpChangeProgressParam_regInfo_set(this.swigCPtr, this, RegProgressParam.getCPtr(regProgressParam), regProgressParam);
    }

    public void setStatus(int i2) {
        pjsua2JNI.OnIpChangeProgressParam_status_set(this.swigCPtr, this, i2);
    }

    public void setTransportId(int i2) {
        pjsua2JNI.OnIpChangeProgressParam_transportId_set(this.swigCPtr, this, i2);
    }

    public OnIpChangeProgressParam() {
        this(pjsua2JNI.new_OnIpChangeProgressParam(), true);
    }
}
