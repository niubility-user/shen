package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class MediaFormat {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaFormat(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            return 0L;
        }
        return mediaFormat.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormat(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getId() {
        return pjsua2JNI.MediaFormat_id_get(this.swigCPtr, this);
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.MediaFormat_type_get(this.swigCPtr, this));
    }

    public void setId(long j2) {
        pjsua2JNI.MediaFormat_id_set(this.swigCPtr, this, j2);
    }

    public void setType(pjmedia_type pjmedia_typeVar) {
        pjsua2JNI.MediaFormat_type_set(this.swigCPtr, this, pjmedia_typeVar.swigValue());
    }

    public MediaFormat() {
        this(pjsua2JNI.new_MediaFormat(), true);
    }
}
