package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: classes.dex */
public class UnknownCppException extends CppException {
    @DoNotStrip
    public UnknownCppException() {
        super("Unknown");
    }

    @DoNotStrip
    public UnknownCppException(String str) {
        super(str);
    }
}
