package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class RxMsgEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RxMsgEvent(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RxMsgEvent rxMsgEvent) {
        if (rxMsgEvent == null) {
            return 0L;
        }
        return rxMsgEvent.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RxMsgEvent(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipRxData getRdata() {
        long RxMsgEvent_rdata_get = pjsua2JNI.RxMsgEvent_rdata_get(this.swigCPtr, this);
        if (RxMsgEvent_rdata_get == 0) {
            return null;
        }
        return new SipRxData(RxMsgEvent_rdata_get, false);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.RxMsgEvent_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public RxMsgEvent() {
        this(pjsua2JNI.new_RxMsgEvent(), true);
    }
}
