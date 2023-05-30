package com.tencent.wcdb.database;

import com.tencent.wcdb.CursorWindowAllocationException;

/* loaded from: classes9.dex */
public class ChunkedCursorWindow extends SQLiteClosable {
    public static final long CHUNK_NOT_FOUND = -1;
    private int mNumColumns = 0;
    long mWindowPtr;

    public ChunkedCursorWindow(int i2) {
        long nativeCreate = nativeCreate(i2);
        this.mWindowPtr = nativeCreate;
        if (nativeCreate == 0) {
            throw new CursorWindowAllocationException("Cursor window allocation failed.");
        }
    }

    private void dispose() {
        long j2 = this.mWindowPtr;
        if (j2 != 0) {
            nativeDispose(j2);
            this.mWindowPtr = 0L;
        }
    }

    private static native void nativeClear(long j2);

    private static native long nativeCreate(int i2);

    private static native void nativeDispose(long j2);

    private static native void nativeEndRow(long j2, long j3);

    private static native byte[] nativeGetBlob(long j2, int i2);

    private static native double nativeGetDouble(long j2, int i2);

    private static native long nativeGetLong(long j2, int i2);

    private static native int nativeGetNumChunks(long j2);

    private static native long nativeGetRow(long j2, int i2);

    private static native String nativeGetString(long j2, int i2);

    private static native int nativeGetType(long j2, int i2);

    private static native long nativeRemoveChunk(long j2, int i2);

    private static native boolean nativeSetNumColumns(long j2, int i2);

    public void clear() {
        acquireReference();
        try {
            nativeClear(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endRowUnsafe(long j2) {
        if (j2 == 0) {
            return;
        }
        nativeEndRow(this.mWindowPtr, j2);
        releaseReference();
    }

    protected void finalize() throws Throwable {
        try {
            dispose();
        } finally {
            super.finalize();
        }
    }

    public byte[] getBlob(int i2, int i3) {
        long rowUnsafe = getRowUnsafe(i2);
        if (rowUnsafe != 0) {
            try {
                return nativeGetBlob(rowUnsafe, i3);
            } finally {
                endRowUnsafe(rowUnsafe);
            }
        }
        throw new IllegalStateException("Couldn't read row " + i2 + ", column " + i3 + " from ChunkedCursorWindow.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getBlobUnsafe(long j2, int i2) {
        return nativeGetBlob(j2, i2);
    }

    public double getDouble(int i2, int i3) {
        long rowUnsafe = getRowUnsafe(i2);
        if (rowUnsafe != 0) {
            try {
                return nativeGetDouble(rowUnsafe, i3);
            } finally {
                endRowUnsafe(rowUnsafe);
            }
        }
        throw new IllegalStateException("Couldn't read row " + i2 + ", column " + i3 + " from ChunkedCursorWindow.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getDoubleUnsafe(long j2, int i2) {
        return nativeGetDouble(j2, i2);
    }

    public long getLong(int i2, int i3) {
        long rowUnsafe = getRowUnsafe(i2);
        if (rowUnsafe != 0) {
            try {
                return nativeGetLong(rowUnsafe, i3);
            } finally {
                endRowUnsafe(rowUnsafe);
            }
        }
        throw new IllegalStateException("Couldn't read row " + i2 + ", column " + i3 + " from ChunkedCursorWindow.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getLongUnsafe(long j2, int i2) {
        return nativeGetLong(j2, i2);
    }

    public int getNumChunks() {
        acquireReference();
        try {
            return nativeGetNumChunks(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    public int getNumColumns() {
        return this.mNumColumns;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getRowUnsafe(int i2) {
        acquireReference();
        long nativeGetRow = nativeGetRow(this.mWindowPtr, i2);
        if (nativeGetRow == 0) {
            releaseReference();
        }
        return nativeGetRow;
    }

    public String getString(int i2, int i3) {
        long rowUnsafe = getRowUnsafe(i2);
        if (rowUnsafe != 0) {
            try {
                return nativeGetString(rowUnsafe, i3);
            } finally {
                endRowUnsafe(rowUnsafe);
            }
        }
        throw new IllegalStateException("Couldn't read row " + i2 + ", column " + i3 + " from ChunkedCursorWindow.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getStringUnsafe(long j2, int i2) {
        return nativeGetString(j2, i2);
    }

    public int getType(int i2, int i3) {
        long rowUnsafe = getRowUnsafe(i2);
        if (rowUnsafe != 0) {
            try {
                return nativeGetType(rowUnsafe, i3);
            } finally {
                endRowUnsafe(rowUnsafe);
            }
        }
        throw new IllegalStateException("Couldn't read row " + i2 + ", column " + i3 + " from ChunkedCursorWindow.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTypeUnsafe(long j2, int i2) {
        return nativeGetType(j2, i2);
    }

    @Override // com.tencent.wcdb.database.SQLiteClosable
    protected void onAllReferencesReleased() {
        dispose();
    }

    public long removeChunk(int i2) {
        acquireReference();
        try {
            return nativeRemoveChunk(this.mWindowPtr, i2);
        } finally {
            releaseReference();
        }
    }

    public boolean setNumColumns(int i2) {
        acquireReference();
        try {
            boolean nativeSetNumColumns = nativeSetNumColumns(this.mWindowPtr, i2);
            if (nativeSetNumColumns) {
                this.mNumColumns = i2;
            }
            return nativeSetNumColumns;
        } finally {
            releaseReference();
        }
    }
}
