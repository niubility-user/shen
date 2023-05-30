package com.facebook.react.packagerconnection;

import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public interface RequestHandler {
    void onNotification(@Nullable Object obj);

    void onRequest(@Nullable Object obj, Responder responder);
}
