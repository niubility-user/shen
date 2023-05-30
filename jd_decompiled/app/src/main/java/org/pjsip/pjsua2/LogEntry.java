package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class LogEntry {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public LogEntry(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    public static long getCPtr(LogEntry logEntry) {
        if (logEntry == null) {
            return 0L;
        }
        return logEntry.swigCPtr;
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogEntry(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getLevel() {
        return pjsua2JNI.LogEntry_level_get(this.swigCPtr, this);
    }

    public String getMsg() {
        return pjsua2JNI.LogEntry_msg_get(this.swigCPtr, this);
    }

    public int getThreadId() {
        return pjsua2JNI.LogEntry_threadId_get(this.swigCPtr, this);
    }

    public String getThreadName() {
        return pjsua2JNI.LogEntry_threadName_get(this.swigCPtr, this);
    }

    public void setLevel(int i2) {
        pjsua2JNI.LogEntry_level_set(this.swigCPtr, this, i2);
    }

    public void setMsg(String str) {
        pjsua2JNI.LogEntry_msg_set(this.swigCPtr, this, str);
    }

    public void setThreadId(int i2) {
        pjsua2JNI.LogEntry_threadId_set(this.swigCPtr, this, i2);
    }

    public void setThreadName(String str) {
        pjsua2JNI.LogEntry_threadName_set(this.swigCPtr, this, str);
    }

    public LogEntry() {
        this(pjsua2JNI.new_LogEntry(), true);
    }
}
