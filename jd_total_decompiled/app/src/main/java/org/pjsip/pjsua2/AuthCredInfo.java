package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class AuthCredInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthCredInfo(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AuthCredInfo authCredInfo) {
        if (authCredInfo == null) {
            return 0L;
        }
        return authCredInfo.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfo(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getAkaAmf() {
        return pjsua2JNI.AuthCredInfo_akaAmf_get(this.swigCPtr, this);
    }

    public String getAkaK() {
        return pjsua2JNI.AuthCredInfo_akaK_get(this.swigCPtr, this);
    }

    public String getAkaOp() {
        return pjsua2JNI.AuthCredInfo_akaOp_get(this.swigCPtr, this);
    }

    public String getData() {
        return pjsua2JNI.AuthCredInfo_data_get(this.swigCPtr, this);
    }

    public int getDataType() {
        return pjsua2JNI.AuthCredInfo_dataType_get(this.swigCPtr, this);
    }

    public String getRealm() {
        return pjsua2JNI.AuthCredInfo_realm_get(this.swigCPtr, this);
    }

    public String getScheme() {
        return pjsua2JNI.AuthCredInfo_scheme_get(this.swigCPtr, this);
    }

    public String getUsername() {
        return pjsua2JNI.AuthCredInfo_username_get(this.swigCPtr, this);
    }

    public void setAkaAmf(String str) {
        pjsua2JNI.AuthCredInfo_akaAmf_set(this.swigCPtr, this, str);
    }

    public void setAkaK(String str) {
        pjsua2JNI.AuthCredInfo_akaK_set(this.swigCPtr, this, str);
    }

    public void setAkaOp(String str) {
        pjsua2JNI.AuthCredInfo_akaOp_set(this.swigCPtr, this, str);
    }

    public void setData(String str) {
        pjsua2JNI.AuthCredInfo_data_set(this.swigCPtr, this, str);
    }

    public void setDataType(int i2) {
        pjsua2JNI.AuthCredInfo_dataType_set(this.swigCPtr, this, i2);
    }

    public void setRealm(String str) {
        pjsua2JNI.AuthCredInfo_realm_set(this.swigCPtr, this, str);
    }

    public void setScheme(String str) {
        pjsua2JNI.AuthCredInfo_scheme_set(this.swigCPtr, this, str);
    }

    public void setUsername(String str) {
        pjsua2JNI.AuthCredInfo_username_set(this.swigCPtr, this, str);
    }

    public AuthCredInfo() {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_0(), true);
    }

    public AuthCredInfo(String str, String str2, String str3, int i2, String str4) {
        this(pjsua2JNI.new_AuthCredInfo__SWIG_1(str, str2, str3, i2, str4), true);
    }
}
