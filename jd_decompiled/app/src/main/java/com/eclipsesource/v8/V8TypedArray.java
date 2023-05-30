package com.eclipsesource.v8;

import cn.com.union.fido.bean.uafclient.ErrorCode;

/* loaded from: classes.dex */
public class V8TypedArray extends V8Array {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class V8ArrayData {
        private V8ArrayBuffer buffer;
        private int offset;
        private int size;
        private int type;

        public V8ArrayData(V8ArrayBuffer v8ArrayBuffer, int i2, int i3, int i4) {
            this.buffer = v8ArrayBuffer;
            this.offset = i2;
            this.size = i3;
            this.type = i4;
        }
    }

    public V8TypedArray(V8 v8, V8ArrayBuffer v8ArrayBuffer, int i2, int i3, int i4) {
        super(v8, new V8ArrayData(v8ArrayBuffer, i3, i4, i2));
    }

    private void checkArrayProperties(V8ArrayData v8ArrayData) {
        checkOffset(v8ArrayData);
        checkSize(v8ArrayData);
    }

    private void checkOffset(V8ArrayData v8ArrayData) {
        if (v8ArrayData.offset % getStructureSize(v8ArrayData.type) == 0) {
            return;
        }
        throw new IllegalStateException("RangeError: Start offset of Int32Array must be a multiple of " + getStructureSize(v8ArrayData.type));
    }

    private void checkSize(V8ArrayData v8ArrayData) {
        if (v8ArrayData.size >= 0) {
            if ((v8ArrayData.size * getStructureSize(v8ArrayData.type)) + v8ArrayData.offset > v8ArrayData.buffer.limit()) {
                throw new IllegalStateException("RangeError: Invalid typed array length");
            }
            return;
        }
        throw new IllegalStateException("RangeError: Invalid typed array length");
    }

    private long createTypedArray(long j2, V8ArrayData v8ArrayData) {
        int i2 = v8ArrayData.type;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 9) {
                    switch (i2) {
                        case 11:
                            return this.v8.initNewV8UInt8Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        case 12:
                            return this.v8.initNewV8UInt8ClampedArray(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        case 13:
                            return this.v8.initNewV8Int16Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        case 14:
                            return this.v8.initNewV8UInt16Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        case 15:
                            return this.v8.initNewV8UInt32Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        case 16:
                            return this.v8.initNewV8Float32Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
                        default:
                            throw new IllegalArgumentException("Cannot create a typed array of type " + V8Value.getStringRepresentation(v8ArrayData.type));
                    }
                }
                return this.v8.initNewV8Int8Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
            }
            return this.v8.initNewV8Float64Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
        }
        return this.v8.initNewV8Int32Array(j2, v8ArrayData.buffer.objectHandle, v8ArrayData.offset, v8ArrayData.size);
    }

    public static int getStructureSize(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 9) {
                    switch (i2) {
                        case 11:
                        case 12:
                            break;
                        case 13:
                        case 14:
                            return 2;
                        case 15:
                        case 16:
                            return 4;
                        default:
                            throw new IllegalArgumentException("Cannot create a typed array of type " + V8Value.getStringRepresentation(i2));
                    }
                }
                return 1;
            }
            return 8;
        }
        return 4;
    }

    @Override // com.eclipsesource.v8.V8Array, com.eclipsesource.v8.V8Object, com.eclipsesource.v8.V8Value
    protected V8Value createTwin() {
        this.v8.checkThread();
        checkReleased();
        return new V8TypedArray(this.v8);
    }

    @Override // com.eclipsesource.v8.V8Array
    public Object get(int i2) {
        this.v8.checkThread();
        checkReleased();
        int type = getType();
        if (type != 1) {
            if (type != 2) {
                if (type != 9) {
                    switch (type) {
                        case 11:
                            return Short.valueOf((short) (((Number) super.get(i2)).shortValue() & ErrorCode.UNKNOWN));
                        case 12:
                            return Short.valueOf((short) (((Number) super.get(i2)).byteValue() & 255));
                        case 13:
                            return Short.valueOf(((Number) super.get(i2)).shortValue());
                        case 14:
                            return Integer.valueOf(((Integer) super.get(i2)).intValue() & 65535);
                        case 15:
                            return Long.valueOf((-1) & ((Number) super.get(i2)).longValue());
                        case 16:
                            return Float.valueOf(((Number) super.get(i2)).floatValue());
                        default:
                            return null;
                    }
                }
                return Byte.valueOf(((Number) super.get(i2)).byteValue());
            }
            return super.get(i2);
        }
        return super.get(i2);
    }

    public V8ArrayBuffer getBuffer() {
        return (V8ArrayBuffer) get("buffer");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.eclipsesource.v8.V8Array, com.eclipsesource.v8.V8Value
    public void initialize(long j2, Object obj) {
        this.v8.checkThread();
        if (obj == null) {
            super.initialize(j2, obj);
            return;
        }
        V8ArrayData v8ArrayData = (V8ArrayData) obj;
        checkArrayProperties(v8ArrayData);
        long createTypedArray = createTypedArray(j2, v8ArrayData);
        this.released = false;
        addObjectReference(createTypedArray);
    }

    private V8TypedArray(V8 v8) {
        super(v8);
    }
}
