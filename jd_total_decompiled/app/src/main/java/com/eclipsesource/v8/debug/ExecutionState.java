package com.eclipsesource.v8.debug;

import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.debug.mirror.Frame;

/* loaded from: classes.dex */
public class ExecutionState implements Releasable {
    private static final String FRAME = "frame";
    private static final String FRAME_COUNT = "frameCount";
    private static final String PREPARE_STEP = "prepareStep";
    private V8Object v8Object;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExecutionState(V8Object v8Object) {
        this.v8Object = v8Object.twin();
    }

    @Override // com.eclipsesource.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        V8Object v8Object = this.v8Object;
        if (v8Object == null || v8Object.isReleased()) {
            return;
        }
        this.v8Object.close();
        this.v8Object = null;
    }

    public Frame getFrame(int i2) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(i2);
        V8Object v8Object = null;
        try {
            v8Object = this.v8Object.executeObjectFunction(FRAME, v8Array);
            return new Frame(v8Object);
        } finally {
            v8Array.close();
            if (v8Object != null) {
                v8Object.close();
            }
        }
    }

    public int getFrameCount() {
        return this.v8Object.executeIntegerFunction(FRAME_COUNT, null);
    }

    public void prepareStep(StepAction stepAction) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(stepAction.index);
        try {
            this.v8Object.executeVoidFunction(PREPARE_STEP, v8Array);
        } finally {
            v8Array.close();
        }
    }

    @Override // com.eclipsesource.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }
}
