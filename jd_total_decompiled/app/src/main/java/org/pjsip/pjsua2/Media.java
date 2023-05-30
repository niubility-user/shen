package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class Media {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public Media(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(Media media) {
        if (media == null) {
            return 0L;
        }
        return media.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Media(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.Media_getType(this.swigCPtr, this));
    }
}
