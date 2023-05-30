package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecFmtp {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecFmtp(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecFmtp codecFmtp) {
        if (codecFmtp == null) {
            return 0L;
        }
        return codecFmtp.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecFmtp(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getName() {
        return pjsua2JNI.CodecFmtp_name_get(this.swigCPtr, this);
    }

    public String getVal() {
        return pjsua2JNI.CodecFmtp_val_get(this.swigCPtr, this);
    }

    public void setName(String str) {
        pjsua2JNI.CodecFmtp_name_set(this.swigCPtr, this, str);
    }

    public void setVal(String str) {
        pjsua2JNI.CodecFmtp_val_set(this.swigCPtr, this, str);
    }

    public CodecFmtp() {
        this(pjsua2JNI.new_CodecFmtp(), true);
    }
}
