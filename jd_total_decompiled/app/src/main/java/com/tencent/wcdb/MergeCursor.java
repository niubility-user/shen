package com.tencent.wcdb;

import android.database.ContentObserver;
import android.database.DataSetObserver;

/* loaded from: classes9.dex */
public class MergeCursor extends AbstractCursor {
    private Cursor mCursor;
    private Cursor[] mCursors;
    private DataSetObserver mObserver = new DataSetObserver() { // from class: com.tencent.wcdb.MergeCursor.1
        @Override // android.database.DataSetObserver
        public void onChanged() {
            MergeCursor.this.mPos = -1;
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            MergeCursor.this.mPos = -1;
        }
    };

    public MergeCursor(Cursor[] cursorArr) {
        this.mCursors = cursorArr;
        int i2 = 0;
        this.mCursor = cursorArr[0];
        while (true) {
            Cursor[] cursorArr2 = this.mCursors;
            if (i2 >= cursorArr2.length) {
                return;
            }
            if (cursorArr2[i2] != null) {
                cursorArr2[i2].registerDataSetObserver(this.mObserver);
            }
            i2++;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].close();
            }
        }
        super.close();
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void deactivate() {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].deactivate();
            }
        }
        super.deactivate();
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public byte[] getBlob(int i2) {
        return this.mCursor.getBlob(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public String[] getColumnNames() {
        Cursor cursor = this.mCursor;
        return cursor != null ? cursor.getColumnNames() : new String[0];
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getCount() {
        int length = this.mCursors.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i3] != null) {
                i2 += cursorArr[i3].getCount();
            }
        }
        return i2;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public double getDouble(int i2) {
        return this.mCursor.getDouble(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public float getFloat(int i2) {
        return this.mCursor.getFloat(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getInt(int i2) {
        return this.mCursor.getInt(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public long getLong(int i2) {
        return this.mCursor.getLong(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public short getShort(int i2) {
        return this.mCursor.getShort(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public String getString(int i2) {
        return this.mCursor.getString(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getType(int i2) {
        return this.mCursor.getType(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isNull(int i2) {
        return this.mCursor.isNull(i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.CrossProcessCursor
    public boolean onMove(int i2, int i3) {
        this.mCursor = null;
        int length = this.mCursors.length;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 >= length) {
                break;
            }
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i4] != null) {
                if (i3 < cursorArr[i4].getCount() + i5) {
                    this.mCursor = this.mCursors[i4];
                    break;
                }
                i5 += this.mCursors[i4].getCount();
            }
            i4++;
        }
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            return cursor.moveToPosition(i3 - i5);
        }
        return false;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].registerContentObserver(contentObserver);
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].registerDataSetObserver(dataSetObserver);
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean requery() {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null && !cursorArr[i2].requery()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].unregisterContentObserver(contentObserver);
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        int length = this.mCursors.length;
        for (int i2 = 0; i2 < length; i2++) {
            Cursor[] cursorArr = this.mCursors;
            if (cursorArr[i2] != null) {
                cursorArr[i2].unregisterDataSetObserver(dataSetObserver);
            }
        }
    }
}
