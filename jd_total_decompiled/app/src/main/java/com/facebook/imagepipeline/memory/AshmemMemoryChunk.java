package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.os.SharedMemory;
import android.system.ErrnoException;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@TargetApi(27)
/* loaded from: classes.dex */
public class AshmemMemoryChunk implements MemoryChunk, Closeable {
    private static final String TAG = "AshmemMemoryChunk";
    @Nullable
    private ByteBuffer mByteBuffer;
    private final long mId;
    @Nullable
    private SharedMemory mSharedMemory;

    @VisibleForTesting
    public AshmemMemoryChunk() {
        this.mSharedMemory = null;
        this.mByteBuffer = null;
        this.mId = System.identityHashCode(this);
    }

    public AshmemMemoryChunk(int i2) {
        Preconditions.checkArgument(i2 > 0);
        try {
            SharedMemory create = SharedMemory.create(TAG, i2);
            this.mSharedMemory = create;
            this.mByteBuffer = create.mapReadWrite();
            this.mId = System.identityHashCode(this);
        } catch (ErrnoException e2) {
            throw new RuntimeException("Fail to create AshmemMemory", e2);
        }
    }

    private void doCopy(int i2, MemoryChunk memoryChunk, int i3, int i4) {
        if (!(memoryChunk instanceof AshmemMemoryChunk)) {
            throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
        }
        Preconditions.checkState(!isClosed());
        Preconditions.checkState(!memoryChunk.isClosed());
        MemoryChunkUtil.checkBounds(i2, memoryChunk.getSize(), i3, i4, getSize());
        this.mByteBuffer.position(i2);
        memoryChunk.getByteBuffer().position(i3);
        byte[] bArr = new byte[i4];
        this.mByteBuffer.get(bArr, 0, i4);
        memoryChunk.getByteBuffer().put(bArr, 0, i4);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!isClosed()) {
            SharedMemory.unmap(this.mByteBuffer);
            this.mSharedMemory.close();
            this.mByteBuffer = null;
            this.mSharedMemory = null;
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public void copy(int i2, MemoryChunk memoryChunk, int i3, int i4) {
        Preconditions.checkNotNull(memoryChunk);
        if (memoryChunk.getUniqueId() == getUniqueId()) {
            String str = "Copying from AshmemMemoryChunk " + Long.toHexString(getUniqueId()) + " to AshmemMemoryChunk " + Long.toHexString(memoryChunk.getUniqueId()) + " which are the same ";
            Preconditions.checkArgument(false);
        }
        if (memoryChunk.getUniqueId() < getUniqueId()) {
            synchronized (memoryChunk) {
                synchronized (this) {
                    doCopy(i2, memoryChunk, i3, i4);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (memoryChunk) {
                doCopy(i2, memoryChunk, i3, i4);
            }
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.mByteBuffer;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of an  AshmemMemoryChunk");
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public int getSize() {
        Preconditions.checkState(!isClosed());
        return this.mSharedMemory.getSize();
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getUniqueId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized boolean isClosed() {
        boolean z;
        if (this.mByteBuffer != null) {
            z = this.mSharedMemory == null;
        }
        return z;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized byte read(int i2) {
        boolean z = true;
        Preconditions.checkState(!isClosed());
        Preconditions.checkArgument(i2 >= 0);
        if (i2 >= getSize()) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return this.mByteBuffer.get(i2);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int read(int i2, byte[] bArr, int i3, int i4) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        adjustByteCount = MemoryChunkUtil.adjustByteCount(i2, i4, getSize());
        MemoryChunkUtil.checkBounds(i2, bArr.length, i3, adjustByteCount, getSize());
        this.mByteBuffer.position(i2);
        this.mByteBuffer.get(bArr, i3, adjustByteCount);
        return adjustByteCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int write(int i2, byte[] bArr, int i3, int i4) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        adjustByteCount = MemoryChunkUtil.adjustByteCount(i2, i4, getSize());
        MemoryChunkUtil.checkBounds(i2, bArr.length, i3, adjustByteCount, getSize());
        this.mByteBuffer.position(i2);
        this.mByteBuffer.put(bArr, i3, adjustByteCount);
        return adjustByteCount;
    }
}
