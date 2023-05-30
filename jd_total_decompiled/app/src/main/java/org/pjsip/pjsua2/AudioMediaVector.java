package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AudioMediaVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public AudioMediaVector(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(AudioMediaVector audioMediaVector) {
        if (audioMediaVector == null) {
            return 0L;
        }
        return audioMediaVector.swigCPtr;
    }

    public void add(AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_add(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long capacity() {
        return pjsua2JNI.AudioMediaVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AudioMediaVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaVector(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AudioMedia get(int i2) {
        long AudioMediaVector_get = pjsua2JNI.AudioMediaVector_get(this.swigCPtr, this, i2);
        if (AudioMediaVector_get == 0) {
            return null;
        }
        return new AudioMedia(AudioMediaVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AudioMediaVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j2) {
        pjsua2JNI.AudioMediaVector_reserve(this.swigCPtr, this, j2);
    }

    public void set(int i2, AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_set(this.swigCPtr, this, i2, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long size() {
        return pjsua2JNI.AudioMediaVector_size(this.swigCPtr, this);
    }

    public AudioMediaVector() {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_0(), true);
    }

    public AudioMediaVector(long j2) {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_1(j2), true);
    }
}
