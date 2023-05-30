package com.eclipsesource.v8;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class V8Array extends V8Object {

    /* loaded from: classes.dex */
    public static class Undefined extends V8Array {
        @Override // com.eclipsesource.v8.V8Object
        public V8Object add(String str, boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object addUndefined(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Value, com.eclipsesource.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // com.eclipsesource.v8.V8Object
        public boolean contains(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Value
        public boolean equals(Object obj) {
            return (obj instanceof V8Object) && ((V8Object) obj).isUndefined();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Array executeArrayFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public boolean executeBooleanFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public double executeDoubleFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public int executeIntegerFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object executeObjectFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public String executeStringFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public void executeVoidFunction(String str, V8Array v8Array) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public Object get(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Array getArray(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public boolean getBoolean(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public boolean[] getBooleans(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public byte getByte(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public byte[] getBytes(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public double getDouble(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public double[] getDoubles(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public int getInteger(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int[] getIntegers(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public String[] getKeys() {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object getObject(String str) throws V8ResultUndefined {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Value
        public V8 getRuntime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public String getString(String str) throws V8ResultUndefined {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public String[] getStrings(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public int getType(String str) throws V8ResultUndefined {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Value
        public int hashCode() {
            return R2.attr.floatingActionButtonStyle;
        }

        @Override // com.eclipsesource.v8.V8Value
        public boolean isReleased() {
            return false;
        }

        @Override // com.eclipsesource.v8.V8Value
        public boolean isUndefined() {
            return true;
        }

        @Override // com.eclipsesource.v8.V8Array
        public int length() {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array push(boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array pushUndefined() {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object registerJavaMethod(JavaCallback javaCallback, String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Value, com.eclipsesource.v8.Releasable
        @Deprecated
        public void release() {
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object setPrototype(V8Object v8Object) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array, com.eclipsesource.v8.V8Object
        public String toString() {
            return "undefined";
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object add(String str, double d) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array getArray(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public boolean getBoolean(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getBooleans(int i2, int i3, boolean[] zArr) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getBytes(int i2, int i3, byte[] bArr) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public double getDouble(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getDoubles(int i2, int i3, double[] dArr) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getInteger(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getIntegers(int i2, int i3, int[] iArr) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Object getObject(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public String getString(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getStrings(int i2, int i3, String[] strArr) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getType() {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array push(double d) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object registerJavaMethod(JavaVoidCallback javaVoidCallback, String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object add(String str, int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getType(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array push(int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object registerJavaMethod(Object obj, String str, String str2, Class<?>[] clsArr, boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object add(String str, String str2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public int getType(int i2, int i3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array push(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array, com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
        public Undefined twin() {
            return (Undefined) super.twin();
        }

        @Override // com.eclipsesource.v8.V8Object
        public V8Object add(String str, V8Value v8Value) {
            throw new UnsupportedOperationException();
        }

        @Override // com.eclipsesource.v8.V8Array
        public V8Array push(V8Value v8Value) {
            throw new UnsupportedOperationException();
        }
    }

    protected V8Array() {
    }

    @Override // com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
    protected V8Value createTwin() {
        return new V8Array(this.v8);
    }

    public Object get(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGet(v8.getV8RuntimePtr(), 6, this.objectHandle, i2);
    }

    public V8Array getArray(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        Object arrayGet = v8.arrayGet(v8.getV8RuntimePtr(), 5, this.objectHandle, i2);
        if (arrayGet != null && !(arrayGet instanceof V8Array)) {
            throw new V8ResultUndefined();
        }
        return (V8Array) arrayGet;
    }

    public boolean getBoolean(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetBoolean(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    public boolean[] getBooleans(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetBooleans(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public byte getByte(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetByte(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    public byte[] getBytes(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetBytes(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public double getDouble(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetDouble(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    public double[] getDoubles(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetDoubles(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public int getInteger(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetInteger(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    public int[] getIntegers(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetIntegers(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public V8Object getObject(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        Object arrayGet = v8.arrayGet(v8.getV8RuntimePtr(), 6, this.objectHandle, i2);
        if (arrayGet != null && !(arrayGet instanceof V8Object)) {
            throw new V8ResultUndefined();
        }
        return (V8Object) arrayGet;
    }

    public String getString(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetString(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    public String[] getStrings(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetStrings(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public int getType(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.getType(v8.getV8RuntimePtr(), getHandle(), i2);
    }

    @Override // com.eclipsesource.v8.V8Value
    public void initialize(long j2, Object obj) {
        long initNewV8Array = this.v8.initNewV8Array(j2);
        this.released = false;
        addObjectReference(initNewV8Array);
    }

    public int length() {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.arrayGetSize(v8.getV8RuntimePtr(), getHandle());
    }

    public V8Array push(int i2) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        v8.addArrayIntItem(v8.getV8RuntimePtr(), getHandle(), i2);
        return this;
    }

    public V8Array pushNull() {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        v8.addArrayNullItem(v8.getV8RuntimePtr(), getHandle());
        return this;
    }

    public V8Array pushUndefined() {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        v8.addArrayUndefinedItem(v8.getV8RuntimePtr(), getHandle());
        return this;
    }

    @Override // com.eclipsesource.v8.V8Object
    public String toString() {
        return (this.released || this.v8.isReleased()) ? "[Array released]" : super.toString();
    }

    public V8Array(V8 v8) {
        super(v8);
        v8.checkThread();
    }

    @Override // com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
    public V8Array twin() {
        return (V8Array) super.twin();
    }

    public V8Array(V8 v8, Object obj) {
        super(v8, obj);
    }

    public int getBooleans(int i2, int i3, boolean[] zArr) {
        this.v8.checkThread();
        checkReleased();
        if (i3 <= zArr.length) {
            V8 v8 = this.v8;
            return v8.arrayGetBooleans(v8.getV8RuntimePtr(), getHandle(), i2, i3, zArr);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getBytes(int i2, int i3, byte[] bArr) {
        this.v8.checkThread();
        checkReleased();
        if (i3 <= bArr.length) {
            V8 v8 = this.v8;
            return v8.arrayGetBytes(v8.getV8RuntimePtr(), getHandle(), i2, i3, bArr);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getDoubles(int i2, int i3, double[] dArr) {
        this.v8.checkThread();
        checkReleased();
        if (i3 <= dArr.length) {
            V8 v8 = this.v8;
            return v8.arrayGetDoubles(v8.getV8RuntimePtr(), getHandle(), i2, i3, dArr);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getIntegers(int i2, int i3, int[] iArr) {
        this.v8.checkThread();
        checkReleased();
        if (i3 <= iArr.length) {
            V8 v8 = this.v8;
            return v8.arrayGetIntegers(v8.getV8RuntimePtr(), getHandle(), i2, i3, iArr);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getStrings(int i2, int i3, String[] strArr) {
        this.v8.checkThread();
        checkReleased();
        if (i3 <= strArr.length) {
            V8 v8 = this.v8;
            return v8.arrayGetStrings(v8.getV8RuntimePtr(), getHandle(), i2, i3, strArr);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getType() {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.getArrayType(v8.getV8RuntimePtr(), getHandle());
    }

    public V8Array push(boolean z) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        v8.addArrayBooleanItem(v8.getV8RuntimePtr(), getHandle(), z);
        return this;
    }

    public int getType(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        return v8.getType(v8.getV8RuntimePtr(), getHandle(), i2, i3);
    }

    public V8Array push(double d) {
        this.v8.checkThread();
        checkReleased();
        V8 v8 = this.v8;
        v8.addArrayDoubleItem(v8.getV8RuntimePtr(), getHandle(), d);
        return this;
    }

    public V8Array push(String str) {
        this.v8.checkThread();
        checkReleased();
        if (str == null) {
            V8 v8 = this.v8;
            v8.addArrayNullItem(v8.getV8RuntimePtr(), getHandle());
        } else if (str.equals(V8.getUndefined())) {
            V8 v82 = this.v8;
            v82.addArrayUndefinedItem(v82.getV8RuntimePtr(), getHandle());
        } else {
            V8 v83 = this.v8;
            v83.addArrayStringItem(v83.getV8RuntimePtr(), getHandle(), str);
        }
        return this;
    }

    public V8Array push(V8Value v8Value) {
        this.v8.checkThread();
        checkReleased();
        this.v8.checkRuntime(v8Value);
        if (v8Value == null) {
            V8 v8 = this.v8;
            v8.addArrayNullItem(v8.getV8RuntimePtr(), getHandle());
        } else if (v8Value.equals(V8.getUndefined())) {
            V8 v82 = this.v8;
            v82.addArrayUndefinedItem(v82.getV8RuntimePtr(), getHandle());
        } else {
            V8 v83 = this.v8;
            v83.addArrayObjectItem(v83.getV8RuntimePtr(), getHandle(), v8Value.getHandle());
        }
        return this;
    }

    public V8Array push(Object obj) {
        this.v8.checkThread();
        checkReleased();
        boolean z = obj instanceof V8Value;
        if (z) {
            this.v8.checkRuntime((V8Value) obj);
        }
        if (obj == null) {
            V8 v8 = this.v8;
            v8.addArrayNullItem(v8.getV8RuntimePtr(), getHandle());
        } else if (obj.equals(V8.getUndefined())) {
            V8 v82 = this.v8;
            v82.addArrayUndefinedItem(v82.getV8RuntimePtr(), getHandle());
        } else if (obj instanceof Double) {
            V8 v83 = this.v8;
            v83.addArrayDoubleItem(v83.getV8RuntimePtr(), getHandle(), ((Double) obj).doubleValue());
        } else if (obj instanceof Integer) {
            V8 v84 = this.v8;
            v84.addArrayIntItem(v84.getV8RuntimePtr(), getHandle(), ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            V8 v85 = this.v8;
            v85.addArrayDoubleItem(v85.getV8RuntimePtr(), getHandle(), ((Float) obj).doubleValue());
        } else if (obj instanceof Number) {
            V8 v86 = this.v8;
            v86.addArrayDoubleItem(v86.getV8RuntimePtr(), getHandle(), ((Number) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            V8 v87 = this.v8;
            v87.addArrayBooleanItem(v87.getV8RuntimePtr(), getHandle(), ((Boolean) obj).booleanValue());
        } else if (obj instanceof String) {
            V8 v88 = this.v8;
            v88.addArrayStringItem(v88.getV8RuntimePtr(), getHandle(), (String) obj);
        } else if (z) {
            V8 v89 = this.v8;
            v89.addArrayObjectItem(v89.getV8RuntimePtr(), getHandle(), ((V8Value) obj).getHandle());
        } else {
            throw new IllegalArgumentException();
        }
        return this;
    }
}
