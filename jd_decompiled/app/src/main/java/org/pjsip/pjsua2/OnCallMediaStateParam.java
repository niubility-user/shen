package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class OnCallMediaStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallMediaStateParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallMediaStateParam onCallMediaStateParam) {
        if (onCallMediaStateParam == null) {
            return 0L;
        }
        return onCallMediaStateParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaStateParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public OnCallMediaStateParam() {
        this(pjsua2JNI.new_OnCallMediaStateParam(), true);
    }
}
