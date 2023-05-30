package com.jdcloud.vsr.utils;

import com.jdcloud.vsr.JDTContext;
import com.jdcloud.vsr.Task;

/* loaded from: classes18.dex */
public abstract class Callback extends Task {
    public Callback(JDTContext jDTContext) {
        super(jDTContext, newCallbackTask());
        updateCallback();
    }

    private static native long newCallbackTask();

    private native void updateCallback();

    public abstract void run();
}
