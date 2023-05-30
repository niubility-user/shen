package com.tencent.wcdb;

import android.content.res.Resources;
import android.database.CharArrayBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.wcdb.database.SQLiteClosable;

/* loaded from: classes9.dex */
public class CursorWindow extends SQLiteClosable implements Parcelable {
    public static final Parcelable.Creator<CursorWindow> CREATOR;
    private static final String STATS_TAG = "WCDB.CursorWindowStats";
    private static int sCursorWindowSize;
    private final String mName;
    private int mStartPos;
    public long mWindowPtr;

    static {
        int identifier = Resources.getSystem().getIdentifier("config_cursorWindowSize", "integer", "android");
        if (identifier != 0) {
            sCursorWindowSize = Resources.getSystem().getInteger(identifier) * 1024;
        } else {
            sCursorWindowSize = 2097152;
        }
        CREATOR = new Parcelable.Creator<CursorWindow>() { // from class: com.tencent.wcdb.CursorWindow.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CursorWindow createFromParcel(Parcel parcel) {
                return new CursorWindow(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CursorWindow[] newArray(int i2) {
                return new CursorWindow[i2];
            }
        };
    }

    private void dispose() {
        long j2 = this.mWindowPtr;
        if (j2 != 0) {
            nativeDispose(j2);
            this.mWindowPtr = 0L;
        }
    }

    private static native boolean nativeAllocRow(long j2);

    private static native void nativeClear(long j2);

    private static native void nativeCopyStringToBuffer(long j2, int i2, int i3, CharArrayBuffer charArrayBuffer);

    private static native long nativeCreate(String str, int i2);

    private static native void nativeDispose(long j2);

    private static native void nativeFreeLastRow(long j2);

    private static native byte[] nativeGetBlob(long j2, int i2, int i3);

    private static native double nativeGetDouble(long j2, int i2, int i3);

    private static native long nativeGetLong(long j2, int i2, int i3);

    private static native int nativeGetNumRows(long j2);

    private static native String nativeGetString(long j2, int i2, int i3);

    private static native int nativeGetType(long j2, int i2, int i3);

    private static native boolean nativePutBlob(long j2, byte[] bArr, int i2, int i3);

    private static native boolean nativePutDouble(long j2, double d, int i2, int i3);

    private static native boolean nativePutLong(long j2, long j3, int i2, int i3);

    private static native boolean nativePutNull(long j2, int i2, int i3);

    private static native boolean nativePutString(long j2, String str, int i2, int i3);

    private static native boolean nativeSetNumColumns(long j2, int i2);

    public static CursorWindow newFromParcel(Parcel parcel) {
        return CREATOR.createFromParcel(parcel);
    }

    public static int windowSize(int i2) {
        int i3 = sCursorWindowSize;
        if (i2 > 0) {
            sCursorWindowSize = i2;
        }
        return i3;
    }

    public boolean allocRow() {
        acquireReference();
        try {
            return nativeAllocRow(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    public void clear() {
        acquireReference();
        try {
            this.mStartPos = 0;
            nativeClear(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    public void copyStringToBuffer(int i2, int i3, CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer != null) {
            acquireReference();
            try {
                nativeCopyStringToBuffer(this.mWindowPtr, i2 - this.mStartPos, i3, charArrayBuffer);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalArgumentException("CharArrayBuffer should not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected void finalize() throws Throwable {
        try {
            dispose();
        } finally {
            super.finalize();
        }
    }

    public void freeLastRow() {
        acquireReference();
        try {
            nativeFreeLastRow(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    public byte[] getBlob(int i2, int i3) {
        acquireReference();
        try {
            return nativeGetBlob(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public double getDouble(int i2, int i3) {
        acquireReference();
        try {
            return nativeGetDouble(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public float getFloat(int i2, int i3) {
        return (float) getDouble(i2, i3);
    }

    public int getInt(int i2, int i3) {
        return (int) getLong(i2, i3);
    }

    public long getLong(int i2, int i3) {
        acquireReference();
        try {
            return nativeGetLong(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public String getName() {
        return this.mName;
    }

    public int getNumRows() {
        acquireReference();
        try {
            return nativeGetNumRows(this.mWindowPtr);
        } finally {
            releaseReference();
        }
    }

    public short getShort(int i2, int i3) {
        return (short) getLong(i2, i3);
    }

    public int getStartPosition() {
        return this.mStartPos;
    }

    public String getString(int i2, int i3) {
        acquireReference();
        try {
            return nativeGetString(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public int getType(int i2, int i3) {
        acquireReference();
        try {
            return nativeGetType(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    @Deprecated
    public boolean isBlob(int i2, int i3) {
        int type = getType(i2, i3);
        return type == 4 || type == 0;
    }

    @Deprecated
    public boolean isFloat(int i2, int i3) {
        return getType(i2, i3) == 2;
    }

    @Deprecated
    public boolean isLong(int i2, int i3) {
        return getType(i2, i3) == 1;
    }

    @Deprecated
    public boolean isNull(int i2, int i3) {
        return getType(i2, i3) == 0;
    }

    @Deprecated
    public boolean isString(int i2, int i3) {
        int type = getType(i2, i3);
        return type == 3 || type == 0;
    }

    @Override // com.tencent.wcdb.database.SQLiteClosable
    protected void onAllReferencesReleased() {
        dispose();
    }

    public boolean putBlob(byte[] bArr, int i2, int i3) {
        acquireReference();
        try {
            return nativePutBlob(this.mWindowPtr, bArr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public boolean putDouble(double d, int i2, int i3) {
        acquireReference();
        try {
            return nativePutDouble(this.mWindowPtr, d, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public boolean putLong(long j2, int i2, int i3) {
        acquireReference();
        try {
            return nativePutLong(this.mWindowPtr, j2, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public boolean putNull(int i2, int i3) {
        acquireReference();
        try {
            return nativePutNull(this.mWindowPtr, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public boolean putString(String str, int i2, int i3) {
        acquireReference();
        try {
            return nativePutString(this.mWindowPtr, str, i2 - this.mStartPos, i3);
        } finally {
            releaseReference();
        }
    }

    public boolean setNumColumns(int i2) {
        acquireReference();
        try {
            return nativeSetNumColumns(this.mWindowPtr, i2);
        } finally {
            releaseReference();
        }
    }

    public void setStartPosition(int i2) {
        this.mStartPos = i2;
    }

    public String toString() {
        return getName() + " {" + Long.toHexString(this.mWindowPtr) + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        throw new UnsupportedOperationException();
    }

    public CursorWindow(String str) {
        this.mStartPos = 0;
        str = (str == null || str.length() == 0) ? "<unnamed>" : str;
        this.mName = str;
        long nativeCreate = nativeCreate(str, sCursorWindowSize);
        this.mWindowPtr = nativeCreate;
        if (nativeCreate != 0) {
            return;
        }
        throw new CursorWindowAllocationException("Cursor window allocation of " + (sCursorWindowSize / 1024) + " kb failed. ");
    }

    @Deprecated
    public CursorWindow(boolean z) {
        this((String) null);
    }

    private CursorWindow(Parcel parcel) {
        throw new UnsupportedOperationException();
    }
}
