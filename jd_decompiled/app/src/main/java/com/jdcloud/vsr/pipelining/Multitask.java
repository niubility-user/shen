package com.jdcloud.vsr.pipelining;

import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;

/* loaded from: classes18.dex */
public class Multitask extends CustomPipeline {

    /* loaded from: classes18.dex */
    public enum RepetitionPolicy {
        REPEAT_ALWAYS,
        REPEAT_UPDATE,
        IGNORE_IF_UPTODATE,
        IGNORE_ALWAYS
    }

    public Multitask(JDTContext jDTContext) {
        super(jDTContext, newMultitask(jDTContext));
    }

    private native int getRepetitionPolicy(long j2, long j3);

    private static native long newMultitask(JDTContext jDTContext);

    private native void setRepetitionPolicy(long j2, long j3, int i2);

    public TaskHolder addTask(Task task, RepetitionPolicy repetitionPolicy) {
        TaskHolder addTask = addTask(task);
        setRepetitionPolicy(this.handle, addTask.handle, repetitionPolicy.ordinal());
        return addTask;
    }

    public RepetitionPolicy getRepetitionPolicy(TaskHolder taskHolder) {
        return RepetitionPolicy.values()[getRepetitionPolicy(this.handle, taskHolder.handle)];
    }

    public void setRepetitionPolicy(TaskHolder taskHolder, RepetitionPolicy repetitionPolicy) {
        setRepetitionPolicy(this.handle, taskHolder.handle, repetitionPolicy.ordinal());
    }
}
