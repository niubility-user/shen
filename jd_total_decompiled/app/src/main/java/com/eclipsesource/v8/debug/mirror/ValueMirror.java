package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class ValueMirror extends Mirror {
    private static final String VALUE = "value";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValueMirror(V8Object v8Object) {
        super(v8Object);
    }

    public Object getValue() {
        return this.v8Object.executeFunction("value", null);
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isValue() {
        return true;
    }
}
