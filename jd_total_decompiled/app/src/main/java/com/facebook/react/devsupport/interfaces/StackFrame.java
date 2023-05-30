package com.facebook.react.devsupport.interfaces;

import org.json.JSONObject;

/* loaded from: classes12.dex */
public interface StackFrame {
    int getColumn();

    String getFile();

    String getFileName();

    int getLine();

    String getMethod();

    JSONObject toJSON();
}
