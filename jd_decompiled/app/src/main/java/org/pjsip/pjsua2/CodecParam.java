package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class CodecParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public CodecParam(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(CodecParam codecParam) {
        if (codecParam == null) {
            return 0L;
        }
        return codecParam.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecParam(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CodecParamInfo getInfo() {
        long CodecParam_info_get = pjsua2JNI.CodecParam_info_get(this.swigCPtr, this);
        if (CodecParam_info_get == 0) {
            return null;
        }
        return new CodecParamInfo(CodecParam_info_get, false);
    }

    public CodecParamSetting getSetting() {
        long CodecParam_setting_get = pjsua2JNI.CodecParam_setting_get(this.swigCPtr, this);
        if (CodecParam_setting_get == 0) {
            return null;
        }
        return new CodecParamSetting(CodecParam_setting_get, false);
    }

    public void setInfo(CodecParamInfo codecParamInfo) {
        pjsua2JNI.CodecParam_info_set(this.swigCPtr, this, CodecParamInfo.getCPtr(codecParamInfo), codecParamInfo);
    }

    public void setSetting(CodecParamSetting codecParamSetting) {
        pjsua2JNI.CodecParam_setting_set(this.swigCPtr, this, CodecParamSetting.getCPtr(codecParamSetting), codecParamSetting);
    }

    public CodecParam() {
        this(pjsua2JNI.new_CodecParam(), true);
    }
}
