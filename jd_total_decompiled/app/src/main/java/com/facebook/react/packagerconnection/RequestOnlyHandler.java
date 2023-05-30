package com.facebook.react.packagerconnection;

import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class RequestOnlyHandler implements RequestHandler {
    private static final String TAG = JSPackagerClient.class.getSimpleName();

    @Override // com.facebook.react.packagerconnection.RequestHandler
    public final void onNotification(@Nullable Object obj) {
        FLog.e(TAG, "Notification is not supported");
    }

    @Override // com.facebook.react.packagerconnection.RequestHandler
    public abstract void onRequest(@Nullable Object obj, Responder responder);
}
