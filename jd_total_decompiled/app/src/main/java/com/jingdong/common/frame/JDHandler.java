package com.jingdong.common.frame;

import android.os.Handler;
import android.os.Message;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDHandler extends Handler {
    private static final String TAG = "JDHandler";

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }
}
