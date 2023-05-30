package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class LogConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public LogConfig(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(LogConfig logConfig) {
        if (logConfig == null) {
            return 0L;
        }
        return logConfig.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogConfig(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getConsoleLevel() {
        return pjsua2JNI.LogConfig_consoleLevel_get(this.swigCPtr, this);
    }

    public long getDecor() {
        return pjsua2JNI.LogConfig_decor_get(this.swigCPtr, this);
    }

    public long getFileFlags() {
        return pjsua2JNI.LogConfig_fileFlags_get(this.swigCPtr, this);
    }

    public String getFilename() {
        return pjsua2JNI.LogConfig_filename_get(this.swigCPtr, this);
    }

    public long getLevel() {
        return pjsua2JNI.LogConfig_level_get(this.swigCPtr, this);
    }

    public long getMsgLogging() {
        return pjsua2JNI.LogConfig_msgLogging_get(this.swigCPtr, this);
    }

    public LogWriter getWriter() {
        long LogConfig_writer_get = pjsua2JNI.LogConfig_writer_get(this.swigCPtr, this);
        if (LogConfig_writer_get == 0) {
            return null;
        }
        return new LogWriter(LogConfig_writer_get, false);
    }

    public void setConsoleLevel(long j2) {
        pjsua2JNI.LogConfig_consoleLevel_set(this.swigCPtr, this, j2);
    }

    public void setDecor(long j2) {
        pjsua2JNI.LogConfig_decor_set(this.swigCPtr, this, j2);
    }

    public void setFileFlags(long j2) {
        pjsua2JNI.LogConfig_fileFlags_set(this.swigCPtr, this, j2);
    }

    public void setFilename(String str) {
        pjsua2JNI.LogConfig_filename_set(this.swigCPtr, this, str);
    }

    public void setLevel(long j2) {
        pjsua2JNI.LogConfig_level_set(this.swigCPtr, this, j2);
    }

    public void setMsgLogging(long j2) {
        pjsua2JNI.LogConfig_msgLogging_set(this.swigCPtr, this, j2);
    }

    public void setWriter(LogWriter logWriter) {
        pjsua2JNI.LogConfig_writer_set(this.swigCPtr, this, LogWriter.getCPtr(logWriter), logWriter);
    }

    public LogConfig() {
        this(pjsua2JNI.new_LogConfig(), true);
    }
}
