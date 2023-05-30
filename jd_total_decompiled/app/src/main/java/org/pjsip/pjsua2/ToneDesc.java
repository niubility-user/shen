package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class ToneDesc extends pjmedia_tone_desc {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public ToneDesc(long j2, boolean z) {
        super(pjsua2JNI.ToneDesc_SWIGUpcast(j2), z);
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDesc toneDesc) {
        if (toneDesc == null) {
            return 0L;
        }
        return toneDesc.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.pjmedia_tone_desc
    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDesc(j2);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.pjmedia_tone_desc
    protected void finalize() {
        delete();
    }

    public ToneDesc() {
        this(pjsua2JNI.new_ToneDesc(), true);
    }
}
