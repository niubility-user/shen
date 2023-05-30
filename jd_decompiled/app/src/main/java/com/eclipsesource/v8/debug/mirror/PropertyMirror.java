package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class PropertyMirror extends Mirror {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertyMirror(V8Object v8Object) {
        super(v8Object);
    }

    public String getName() {
        return this.v8Object.executeStringFunction("name", null);
    }

    public Mirror getValue() {
        V8Object executeObjectFunction = this.v8Object.executeObjectFunction("value", null);
        try {
            return Mirror.createMirror(executeObjectFunction);
        } finally {
            executeObjectFunction.close();
        }
    }

    @Override // com.eclipsesource.v8.debug.mirror.Mirror
    public boolean isProperty() {
        return true;
    }
}
