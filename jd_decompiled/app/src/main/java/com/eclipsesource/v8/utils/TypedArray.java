package com.eclipsesource.v8.utils;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8ArrayBuffer;
import com.eclipsesource.v8.V8TypedArray;

/* loaded from: classes.dex */
public class TypedArray {
    private V8TypedArray typedArray;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypedArray(V8TypedArray v8TypedArray) {
        this.typedArray = (V8TypedArray) v8TypedArray.twin().setWeak();
    }

    public V8TypedArray getV8TypedArray() {
        return (V8TypedArray) this.typedArray.twin();
    }

    public boolean isAvailable() {
        return !this.typedArray.isReleased();
    }

    public TypedArray(V8 v8, ArrayBuffer arrayBuffer, int i2, int i3, int i4) {
        V8ArrayBuffer v8ArrayBuffer = arrayBuffer.getV8ArrayBuffer();
        V8TypedArray v8TypedArray = new V8TypedArray(v8, v8ArrayBuffer, i2, i3, i4);
        try {
            this.typedArray = (V8TypedArray) v8TypedArray.twin().setWeak();
        } finally {
            v8ArrayBuffer.close();
            v8TypedArray.close();
        }
    }
}
