package com.eclipsesource.v8;

/* loaded from: classes.dex */
public class V8Function extends V8Object {
    public V8Function(V8 v8, JavaCallback javaCallback) {
        super(v8, javaCallback);
    }

    public Object call(V8Object v8Object, V8Array v8Array) {
        this.v8.checkThread();
        checkReleased();
        this.v8.checkRuntime(v8Object);
        this.v8.checkRuntime(v8Array);
        if (v8Object == null) {
            v8Object = this.v8;
        }
        long handle = v8Array == null ? 0L : v8Array.getHandle();
        if (v8Object.isUndefined()) {
            v8Object = this.v8;
        }
        long handle2 = v8Object.getHandle();
        V8 v8 = this.v8;
        return v8.executeFunction(v8.getV8RuntimePtr(), handle2, this.objectHandle, handle);
    }

    @Override // com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
    protected V8Value createTwin() {
        return new V8Function(this.v8);
    }

    @Override // com.eclipsesource.v8.V8Value
    public void initialize(long j2, Object obj) {
        if (obj == null) {
            super.initialize(j2, null);
            return;
        }
        long[] initNewV8Function = this.v8.initNewV8Function(j2);
        this.v8.createAndRegisterMethodDescriptor((JavaCallback) obj, initNewV8Function[1]);
        this.released = false;
        addObjectReference(initNewV8Function[0]);
    }

    @Override // com.eclipsesource.v8.V8Object
    public String toString() {
        return (this.released || this.v8.isReleased()) ? "[Function released]" : super.toString();
    }

    protected V8Function(V8 v8) {
        this(v8, null);
    }

    @Override // com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
    public V8Function twin() {
        return (V8Function) super.twin();
    }
}
