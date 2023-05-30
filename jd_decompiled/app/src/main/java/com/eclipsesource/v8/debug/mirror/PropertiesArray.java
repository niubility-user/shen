package com.eclipsesource.v8.debug.mirror;

import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/* loaded from: classes.dex */
public class PropertiesArray implements Releasable {
    private V8Array v8Array;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropertiesArray(V8Array v8Array) {
        this.v8Array = v8Array.twin();
    }

    @Override // com.eclipsesource.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.v8Array.isReleased()) {
            return;
        }
        this.v8Array.close();
    }

    public PropertyMirror getProperty(int i2) {
        V8Object object = this.v8Array.getObject(i2);
        try {
            return new PropertyMirror(object);
        } finally {
            object.close();
        }
    }

    public int length() {
        return this.v8Array.length();
    }

    @Override // com.eclipsesource.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }
}
