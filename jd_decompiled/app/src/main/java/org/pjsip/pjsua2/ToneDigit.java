package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDigit extends pjmedia_tone_digit {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ToneDigit(long j2, boolean z) {
        super(pjsua2JNI.ToneDigit_SWIGUpcast(j2), z);
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDigit toneDigit) {
        if (toneDigit == null) {
            return 0L;
        }
        return toneDigit.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.pjmedia_tone_digit
    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigit(j2);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.pjmedia_tone_digit
    protected void finalize() {
        delete();
    }

    public ToneDigit() {
        this(pjsua2JNI.new_ToneDigit(), true);
    }
}
