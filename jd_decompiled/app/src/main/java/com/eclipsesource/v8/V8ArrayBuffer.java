package com.eclipsesource.v8;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public class V8ArrayBuffer extends V8Value {
    ByteBuffer byteBuffer;

    public V8ArrayBuffer(V8 v8, int i2) {
        super(v8);
        initialize(v8.getV8RuntimePtr(), Integer.valueOf(i2));
        ByteBuffer createV8ArrayBufferBackingStore = v8.createV8ArrayBufferBackingStore(v8.getV8RuntimePtr(), this.objectHandle, i2);
        this.byteBuffer = createV8ArrayBufferBackingStore;
        createV8ArrayBufferBackingStore.order(ByteOrder.nativeOrder());
    }

    public final byte[] array() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.array();
    }

    public final int arrayOffset() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.arrayOffset();
    }

    public final int capacity() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.capacity();
    }

    public final V8ArrayBuffer clear() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.clear();
        return this;
    }

    public V8ArrayBuffer compact() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.compact();
        return this;
    }

    @Override // com.eclipsesource.v8.V8Value
    protected V8Value createTwin() {
        return new V8ArrayBuffer(this.v8, this.byteBuffer);
    }

    public int doubleLimit() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.asDoubleBuffer().limit();
    }

    public final V8ArrayBuffer flip() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.flip();
        return this;
    }

    public int floatLimit() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.asFloatBuffer().limit();
    }

    public byte get() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.get();
    }

    public char getChar() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getChar();
    }

    public double getDouble() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getDouble();
    }

    public float getFloat() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getFloat();
    }

    public int getInt() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getInt();
    }

    public long getLong() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getLong();
    }

    public short getShort() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getShort();
    }

    public final boolean hasArray() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.hasArray();
    }

    public final boolean hasRemaining() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.hasRemaining();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.eclipsesource.v8.V8Value
    public void initialize(long j2, Object obj) {
        this.v8.checkThread();
        if (obj instanceof ByteBuffer) {
            ByteBuffer byteBuffer = (ByteBuffer) obj;
            int limit = byteBuffer.limit();
            V8 v8 = this.v8;
            this.objectHandle = v8.initNewV8ArrayBuffer(v8.getV8RuntimePtr(), byteBuffer, limit);
        } else {
            int intValue = ((Integer) obj).intValue();
            V8 v82 = this.v8;
            this.objectHandle = v82.initNewV8ArrayBuffer(v82.getV8RuntimePtr(), intValue);
        }
        this.released = false;
        addObjectReference(this.objectHandle);
    }

    public int intLimit() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.asIntBuffer().limit();
    }

    public boolean isDirect() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.isDirect();
    }

    public boolean isReadOnly() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.isReadOnly();
    }

    public int limit() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.limit();
    }

    public final V8ArrayBuffer mark() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.mark();
        return this;
    }

    public final ByteOrder order() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.order();
    }

    public final int position() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.position();
    }

    public V8ArrayBuffer put(byte b) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.put(b);
        return this;
    }

    public V8ArrayBuffer putChar(char c2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putChar(c2);
        return this;
    }

    public V8ArrayBuffer putDouble(double d) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putDouble(d);
        return this;
    }

    public V8ArrayBuffer putFloat(float f2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putFloat(f2);
        return this;
    }

    public V8ArrayBuffer putInt(int i2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putInt(i2);
        return this;
    }

    public V8ArrayBuffer putLong(long j2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putLong(j2);
        return this;
    }

    public V8ArrayBuffer putShort(short s) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putShort(s);
        return this;
    }

    public final int remaining() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.remaining();
    }

    public final V8ArrayBuffer reset() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.reset();
        return this;
    }

    public final V8ArrayBuffer rewind() {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.rewind();
        return this;
    }

    public int shortLimit() {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.asShortBuffer().limit();
    }

    @Override // com.eclipsesource.v8.V8Value
    public V8ArrayBuffer twin() {
        this.v8.checkThread();
        checkReleased();
        return (V8ArrayBuffer) super.twin();
    }

    public byte get(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.get(i2);
    }

    public char getChar(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getChar(i2);
    }

    public double getDouble(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getDouble(i2);
    }

    public float getFloat(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getFloat(i2);
    }

    public int getInt(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getInt(i2);
    }

    public long getLong(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getLong(i2);
    }

    public short getShort(int i2) {
        this.v8.checkThread();
        checkReleased();
        return this.byteBuffer.getShort(i2);
    }

    public final V8ArrayBuffer limit(int i2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.limit(i2);
        return this;
    }

    public final V8ArrayBuffer order(ByteOrder byteOrder) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.order(byteOrder);
        return this;
    }

    public final V8ArrayBuffer position(int i2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.position(i2);
        return this;
    }

    public V8ArrayBuffer put(int i2, byte b) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.put(i2, b);
        return this;
    }

    public V8ArrayBuffer putChar(int i2, char c2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putChar(i2, c2);
        return this;
    }

    public V8ArrayBuffer putDouble(int i2, double d) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putDouble(i2, d);
        return this;
    }

    public V8ArrayBuffer putFloat(int i2, float f2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putFloat(i2, f2);
        return this;
    }

    public V8ArrayBuffer putInt(int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.asIntBuffer().put(i2, i3);
        return this;
    }

    public V8ArrayBuffer putLong(int i2, long j2) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putLong(i2, j2);
        return this;
    }

    public V8ArrayBuffer putShort(int i2, short s) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.putShort(i2, s);
        return this;
    }

    public V8ArrayBuffer(V8 v8, ByteBuffer byteBuffer) {
        super(v8);
        byteBuffer = byteBuffer == null ? ByteBuffer.allocateDirect(0) : byteBuffer;
        if (byteBuffer.isDirect()) {
            initialize(v8.getV8RuntimePtr(), byteBuffer);
            this.byteBuffer = byteBuffer;
            byteBuffer.order(ByteOrder.nativeOrder());
            return;
        }
        throw new IllegalArgumentException("ByteBuffer must be a allocated as a direct ByteBuffer");
    }

    public V8ArrayBuffer get(byte[] bArr, int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.get(bArr, i2, i3);
        return this;
    }

    public V8ArrayBuffer put(ByteBuffer byteBuffer) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.put(byteBuffer);
        return this;
    }

    public V8ArrayBuffer get(byte[] bArr) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.get(bArr);
        return this;
    }

    public V8ArrayBuffer put(byte[] bArr, int i2, int i3) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.put(bArr, i2, i3);
        return this;
    }

    public final V8ArrayBuffer put(byte[] bArr) {
        this.v8.checkThread();
        checkReleased();
        this.byteBuffer.put(bArr);
        return this;
    }
}
