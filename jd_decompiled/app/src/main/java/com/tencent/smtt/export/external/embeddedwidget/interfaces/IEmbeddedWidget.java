package com.tencent.smtt.export.external.embeddedwidget.interfaces;

import android.webkit.ValueCallback;

/* loaded from: classes9.dex */
public interface IEmbeddedWidget {

    /* loaded from: classes9.dex */
    public enum EventResponseType {
        UNKNOWN,
        CONSUME_EVENT,
        NOT_CONSUME_EVENT
    }

    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    long getWidgetId();

    void onClientError(IEmbeddedWidgetClient iEmbeddedWidgetClient);

    void setEventResponseType(EventResponseType eventResponseType);
}
