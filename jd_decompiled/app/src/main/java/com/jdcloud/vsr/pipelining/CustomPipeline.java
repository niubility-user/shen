package com.jdcloud.vsr.pipelining;

import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;

/* loaded from: classes18.dex */
public class CustomPipeline extends Task {
    /* JADX INFO: Access modifiers changed from: protected */
    public CustomPipeline(JDTContext jDTContext, long j2) {
        super(jDTContext, j2);
    }

    private native long addTask(long j2, TaskHolder taskHolder, Task task);

    private native TaskHolder getTask(long j2, int i2);

    private native int getTaskCount(long j2);

    private native int getTaskIndex(long j2, long j3);

    private native long insertTask(long j2, TaskHolder taskHolder, Task task, long j3);

    private native void measure(long j2);

    private native boolean removeTask(long j2, long j3);

    public TaskHolder addTask(Task task) {
        TaskHolder taskHolder = new TaskHolder(task);
        taskHolder.handle = addTask(this.handle, taskHolder, task);
        return taskHolder;
    }

    public TaskHolder getTask(int i2) {
        return getTask(this.handle, i2);
    }

    public int getTaskCount() {
        return getTaskCount(this.handle);
    }

    public int getTaskIndex(TaskHolder taskHolder) {
        return getTaskIndex(this.handle, taskHolder.handle);
    }

    public TaskHolder insertTask(Task task, TaskHolder taskHolder) {
        TaskHolder taskHolder2 = new TaskHolder(task);
        taskHolder2.handle = insertTask(this.handle, taskHolder2, task, taskHolder.handle);
        return taskHolder2;
    }

    public void measure() {
        measure(this.handle);
    }

    public boolean removeTask(TaskHolder taskHolder) {
        return removeTask(this.handle, taskHolder.handle);
    }
}
