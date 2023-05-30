package com.eclipsesource.v8.inspector;

/* loaded from: classes.dex */
public interface V8InspectorDelegate {
    void onResponse(String str);

    void waitFrontendMessageOnPause();
}
