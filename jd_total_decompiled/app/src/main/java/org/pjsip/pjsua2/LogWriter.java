package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class LogWriter {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public LogWriter(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(LogWriter logWriter) {
        if (logWriter == null) {
            return 0L;
        }
        return logWriter.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogWriter(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.LogWriter_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.LogWriter_change_ownership(this, this.swigCPtr, true);
    }

    public void write(LogEntry logEntry) {
        pjsua2JNI.LogWriter_write(this.swigCPtr, this, LogEntry.getCPtr(logEntry), logEntry);
    }

    public LogWriter() {
        this(pjsua2JNI.new_LogWriter(), true);
        pjsua2JNI.LogWriter_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
