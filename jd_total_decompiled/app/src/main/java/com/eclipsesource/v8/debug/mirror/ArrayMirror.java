package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class ArrayMirror extends ObjectMirror {
    private static final String LENGTH = "length";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isArray() {
        return true;
    }

    public int length() {
        return this.v8Object.executeIntegerFunction("length", null);
    }
}
