package com.facebook.react.bridge.queue;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public class MessageQueueThreadHandler extends Handler {
    private QueueThreadExceptionHandler mExceptionHandler;

    public MessageQueueThreadHandler(Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        super(looper);
        this.mExceptionHandler = queueThreadExceptionHandler;
    }

    public void destroy() {
        this.mExceptionHandler = null;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Exception e2) {
            this.mExceptionHandler.handleException(e2);
        }
    }
}
