package com.facebook.react.bridge;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class NativeArgumentsParseException extends JSApplicationCausedNativeException {
    public NativeArgumentsParseException(String str) {
        super(str);
    }

    public NativeArgumentsParseException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
