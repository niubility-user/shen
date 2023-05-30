package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class StringMirror extends ValueMirror {
    /* JADX INFO: Access modifiers changed from: package-private */
    public StringMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isString() {
        return true;
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public String toString() {
        return this.v8Object.executeStringFunction("toText", null);
    }
}
