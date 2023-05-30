package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class MediaFormatVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected MediaFormatVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(MediaFormatVector mediaFormatVector) {
        if (mediaFormatVector == null) {
            return 0L;
        }
        return mediaFormatVector.swigCPtr;
    }

    public void add(MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_add(this.swigCPtr, this, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public long capacity() {
        return pjsua2JNI.MediaFormatVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.MediaFormatVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormatVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFormat get(int i2) {
        long MediaFormatVector_get = pjsua2JNI.MediaFormatVector_get(this.swigCPtr, this, i2);
        if (MediaFormatVector_get == 0) {
            return null;
        }
        return new MediaFormat(MediaFormatVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.MediaFormatVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.MediaFormatVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_set(this.swigCPtr, this, i2, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public long size() {
        return pjsua2JNI.MediaFormatVector_size(this.swigCPtr, this);
    }

    public MediaFormatVector() {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_0(), true);
    }

    public MediaFormatVector(long j2) {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_1(j2), true);
    }
}
