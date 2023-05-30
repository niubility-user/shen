package com.facebook.react.bridge;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface WritableArray extends ReadableArray {
    void pushArray(@Nullable WritableArray writableArray);

    void pushBoolean(boolean z);

    void pushDouble(double d);

    void pushInt(int i2);

    void pushMap(@Nullable WritableMap writableMap);

    void pushNull();

    void pushString(@Nullable String str);
}
