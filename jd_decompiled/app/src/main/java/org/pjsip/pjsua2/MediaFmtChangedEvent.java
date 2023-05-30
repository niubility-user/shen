package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class MediaFmtChangedEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaFmtChangedEvent(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaFmtChangedEvent mediaFmtChangedEvent) {
        if (mediaFmtChangedEvent == null) {
            return 0L;
        }
        return mediaFmtChangedEvent.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFmtChangedEvent(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getNewHeight() {
        return pjsua2JNI.MediaFmtChangedEvent_newHeight_get(this.swigCPtr, this);
    }

    public long getNewWidth() {
        return pjsua2JNI.MediaFmtChangedEvent_newWidth_get(this.swigCPtr, this);
    }

    public void setNewHeight(long j2) {
        pjsua2JNI.MediaFmtChangedEvent_newHeight_set(this.swigCPtr, this, j2);
    }

    public void setNewWidth(long j2) {
        pjsua2JNI.MediaFmtChangedEvent_newWidth_set(this.swigCPtr, this, j2);
    }

    public MediaFmtChangedEvent() {
        this(pjsua2JNI.new_MediaFmtChangedEvent(), true);
    }
}
