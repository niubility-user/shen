package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class BuddyConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected BuddyConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(BuddyConfig buddyConfig) {
        if (buddyConfig == null) {
            return 0L;
        }
        return buddyConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getSubscribe() {
        return pjsua2JNI.BuddyConfig_subscribe_get(this.swigCPtr, this);
    }

    public String getUri() {
        return pjsua2JNI.BuddyConfig_uri_get(this.swigCPtr, this);
    }

    public void setSubscribe(boolean z) {
        pjsua2JNI.BuddyConfig_subscribe_set(this.swigCPtr, this, z);
    }

    public void setUri(String str) {
        pjsua2JNI.BuddyConfig_uri_set(this.swigCPtr, this, str);
    }

    public BuddyConfig() {
        this(pjsua2JNI.new_BuddyConfig(), true);
    }
}
