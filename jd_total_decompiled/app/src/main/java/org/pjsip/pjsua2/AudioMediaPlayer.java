package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AudioMediaPlayer extends AudioMedia {
    private transient long swigCPtr;

    protected AudioMediaPlayer(long j2, boolean z) {
        super(pjsua2JNI.AudioMediaPlayer_SWIGUpcast(j2), z);
        this.swigCPtr = j2;
    }

    protected static long getCPtr(AudioMediaPlayer audioMediaPlayer) {
        if (audioMediaPlayer == null) {
            return 0L;
        }
        return audioMediaPlayer.swigCPtr;
    }

    public static AudioMediaPlayer typecastFromAudioMedia(AudioMedia audioMedia) {
        long AudioMediaPlayer_typecastFromAudioMedia = pjsua2JNI.AudioMediaPlayer_typecastFromAudioMedia(AudioMedia.getCPtr(audioMedia), audioMedia);
        if (AudioMediaPlayer_typecastFromAudioMedia == 0) {
            return null;
        }
        return new AudioMediaPlayer(AudioMediaPlayer_typecastFromAudioMedia, false);
    }

    public void createPlayer(String str, long j2) throws Exception {
        pjsua2JNI.AudioMediaPlayer_createPlayer__SWIG_0(this.swigCPtr, this, str, j2);
    }

    public void createPlaylist(StringVector stringVector, String str, long j2) throws Exception {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_0(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, str, j2);
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaPlayer(j2);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.AudioMedia, org.pjsip.pjsua2.Media
    protected void finalize() {
        delete();
    }

    public AudioMediaPlayerInfo getInfo() throws Exception {
        return new AudioMediaPlayerInfo(pjsua2JNI.AudioMediaPlayer_getInfo(this.swigCPtr, this), true);
    }

    public long getPos() throws Exception {
        return pjsua2JNI.AudioMediaPlayer_getPos(this.swigCPtr, this);
    }

    public boolean onEof() {
        return getClass() == AudioMediaPlayer.class ? pjsua2JNI.AudioMediaPlayer_onEof(this.swigCPtr, this) : pjsua2JNI.AudioMediaPlayer_onEofSwigExplicitAudioMediaPlayer(this.swigCPtr, this);
    }

    public void setPos(long j2) throws Exception {
        pjsua2JNI.AudioMediaPlayer_setPos(this.swigCPtr, this, j2);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.AudioMediaPlayer_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.AudioMediaPlayer_change_ownership(this, this.swigCPtr, true);
    }

    public void createPlayer(String str) throws Exception {
        pjsua2JNI.AudioMediaPlayer_createPlayer__SWIG_1(this.swigCPtr, this, str);
    }

    public void createPlaylist(StringVector stringVector, String str) throws Exception {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_1(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, str);
    }

    public AudioMediaPlayer() {
        this(pjsua2JNI.new_AudioMediaPlayer(), true);
        pjsua2JNI.AudioMediaPlayer_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public void createPlaylist(StringVector stringVector) throws Exception {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_2(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }
}
