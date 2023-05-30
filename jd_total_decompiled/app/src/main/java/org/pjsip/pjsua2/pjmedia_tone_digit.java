package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class pjmedia_tone_digit {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public pjmedia_tone_digit(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(pjmedia_tone_digit pjmedia_tone_digitVar) {
        if (pjmedia_tone_digitVar == null) {
            return 0L;
        }
        return pjmedia_tone_digitVar.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pjmedia_tone_digit(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public char getDigit() {
        return pjsua2JNI.pjmedia_tone_digit_digit_get(this.swigCPtr, this);
    }

    public short getOff_msec() {
        return pjsua2JNI.pjmedia_tone_digit_off_msec_get(this.swigCPtr, this);
    }

    public short getOn_msec() {
        return pjsua2JNI.pjmedia_tone_digit_on_msec_get(this.swigCPtr, this);
    }

    public short getVolume() {
        return pjsua2JNI.pjmedia_tone_digit_volume_get(this.swigCPtr, this);
    }

    public void setDigit(char c2) {
        pjsua2JNI.pjmedia_tone_digit_digit_set(this.swigCPtr, this, c2);
    }

    public void setOff_msec(short s) {
        pjsua2JNI.pjmedia_tone_digit_off_msec_set(this.swigCPtr, this, s);
    }

    public void setOn_msec(short s) {
        pjsua2JNI.pjmedia_tone_digit_on_msec_set(this.swigCPtr, this, s);
    }

    public void setVolume(short s) {
        pjsua2JNI.pjmedia_tone_digit_volume_set(this.swigCPtr, this, s);
    }

    public pjmedia_tone_digit() {
        this(pjsua2JNI.new_pjmedia_tone_digit(), true);
    }
}
