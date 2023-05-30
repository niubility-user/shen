package com.jdcloud.vsr.pipelining;

import com.jdcloud.vsr.Task;

/* loaded from: classes18.dex */
public class TaskHolder {
    protected long handle = 0;
    private Task task;

    /* JADX INFO: Access modifiers changed from: protected */
    public TaskHolder(Task task) {
        this.task = task;
    }

    private native float getRunTime(long j2);

    public float getRunTime() {
        return getRunTime(this.handle);
    }

    public Task getTask() {
        return this.task;
    }
}
