package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AudioMedia extends Media {
    private transient long swigCPtr;

    public AudioMedia(long j2, boolean z) {
        super(pjsua2JNI.AudioMedia_SWIGUpcast(j2), z);
        this.swigCPtr = j2;
    }

    public static long getCPtr(AudioMedia audioMedia) {
        if (audioMedia == null) {
            return 0L;
        }
        return audioMedia.swigCPtr;
    }

    public static ConfPortInfo getPortInfoFromId(int i2) throws Exception {
        return new ConfPortInfo(pjsua2JNI.AudioMedia_getPortInfoFromId(i2), true);
    }

    public static AudioMedia typecastFromMedia(Media media) {
        long AudioMedia_typecastFromMedia = pjsua2JNI.AudioMedia_typecastFromMedia(Media.getCPtr(media), media);
        if (AudioMedia_typecastFromMedia == 0) {
            return null;
        }
        return new AudioMedia(AudioMedia_typecastFromMedia, false);
    }

    public void adjustRxLevel(float f2) throws Exception {
        pjsua2JNI.AudioMedia_adjustRxLevel(this.swigCPtr, this, f2);
    }

    public void adjustTxLevel(float f2) throws Exception {
        pjsua2JNI.AudioMedia_adjustTxLevel(this.swigCPtr, this, f2);
    }

    @Override // org.pjsip.pjsua2.Media
    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMedia(j2);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.Media
    protected void finalize() {
        delete();
    }

    public int getPortId() {
        return pjsua2JNI.AudioMedia_getPortId(this.swigCPtr, this);
    }

    public ConfPortInfo getPortInfo() throws Exception {
        return new ConfPortInfo(pjsua2JNI.AudioMedia_getPortInfo(this.swigCPtr, this), true);
    }

    public long getRxLevel() throws Exception {
        return pjsua2JNI.AudioMedia_getRxLevel(this.swigCPtr, this);
    }

    public long getTxLevel() throws Exception {
        return pjsua2JNI.AudioMedia_getTxLevel(this.swigCPtr, this);
    }

    public void startTransmit(AudioMedia audioMedia) throws Exception {
        pjsua2JNI.AudioMedia_startTransmit(this.swigCPtr, this, getCPtr(audioMedia), audioMedia);
    }

    public void stopTransmit(AudioMedia audioMedia) throws Exception {
        pjsua2JNI.AudioMedia_stopTransmit(this.swigCPtr, this, getCPtr(audioMedia), audioMedia);
    }
}
