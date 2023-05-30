package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDigitMapDigit {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ToneDigitMapDigit(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDigitMapDigit toneDigitMapDigit) {
        if (toneDigitMapDigit == null) {
            return 0L;
        }
        return toneDigitMapDigit.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitMapDigit(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getDigit() {
        return pjsua2JNI.ToneDigitMapDigit_digit_get(this.swigCPtr, this);
    }

    public int getFreq1() {
        return pjsua2JNI.ToneDigitMapDigit_freq1_get(this.swigCPtr, this);
    }

    public int getFreq2() {
        return pjsua2JNI.ToneDigitMapDigit_freq2_get(this.swigCPtr, this);
    }

    public void setDigit(String str) {
        pjsua2JNI.ToneDigitMapDigit_digit_set(this.swigCPtr, this, str);
    }

    public void setFreq1(int i2) {
        pjsua2JNI.ToneDigitMapDigit_freq1_set(this.swigCPtr, this, i2);
    }

    public void setFreq2(int i2) {
        pjsua2JNI.ToneDigitMapDigit_freq2_set(this.swigCPtr, this, i2);
    }

    public ToneDigitMapDigit() {
        this(pjsua2JNI.new_ToneDigitMapDigit(), true);
    }
}
