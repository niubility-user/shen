package com.eclipsesource.v8.utils;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8ArrayBuffer;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ArrayBuffer {
    private V8ArrayBuffer arrayBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayBuffer(V8ArrayBuffer v8ArrayBuffer) {
        this.arrayBuffer = (V8ArrayBuffer) v8ArrayBuffer.twin().setWeak();
    }

    public V8ArrayBuffer getV8ArrayBuffer() {
        return this.arrayBuffer.twin();
    }

    public boolean isAvailable() {
        return !this.arrayBuffer.isReleased();
    }

    public ArrayBuffer(V8 v8, ByteBuffer byteBuffer) {
        V8ArrayBuffer v8ArrayBuffer = new V8ArrayBuffer(v8, byteBuffer);
        try {
            this.arrayBuffer = (V8ArrayBuffer) v8ArrayBuffer.twin().setWeak();
        } finally {
            v8ArrayBuffer.close();
        }
    }
}
