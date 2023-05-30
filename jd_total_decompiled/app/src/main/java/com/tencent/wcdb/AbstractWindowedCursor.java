package com.tencent.wcdb;

import android.database.CharArrayBuffer;

/* loaded from: classes9.dex */
public abstract class AbstractWindowedCursor extends AbstractCursor {
    protected CursorWindow mWindow;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.wcdb.AbstractCursor
    public void checkPosition() {
        super.checkPosition();
        if (this.mWindow == null) {
            throw new StaleDataException("Attempting to access a closed CursorWindow.Most probable cause: cursor is deactivated prior to calling this method.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearOrCreateWindow(String str) {
        CursorWindow cursorWindow = this.mWindow;
        if (cursorWindow == null) {
            this.mWindow = new CursorWindow(str);
        } else {
            cursorWindow.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void closeWindow() {
        CursorWindow cursorWindow = this.mWindow;
        if (cursorWindow != null) {
            cursorWindow.close();
            this.mWindow = null;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void copyStringToBuffer(int i2, CharArrayBuffer charArrayBuffer) {
        checkPosition();
        this.mWindow.copyStringToBuffer(this.mPos, i2, charArrayBuffer);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public byte[] getBlob(int i2) {
        checkPosition();
        return this.mWindow.getBlob(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public double getDouble(int i2) {
        checkPosition();
        return this.mWindow.getDouble(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public float getFloat(int i2) {
        checkPosition();
        return this.mWindow.getFloat(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getInt(int i2) {
        checkPosition();
        return this.mWindow.getInt(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public long getLong(int i2) {
        checkPosition();
        return this.mWindow.getLong(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public short getShort(int i2) {
        checkPosition();
        return this.mWindow.getShort(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public String getString(int i2) {
        checkPosition();
        return this.mWindow.getString(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getType(int i2) {
        checkPosition();
        return this.mWindow.getType(this.mPos, i2);
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.mWindow;
    }

    public boolean hasWindow() {
        return this.mWindow != null;
    }

    @Deprecated
    public boolean isBlob(int i2) {
        return getType(i2) == 4;
    }

    @Deprecated
    public boolean isFloat(int i2) {
        return getType(i2) == 2;
    }

    @Deprecated
    public boolean isLong(int i2) {
        return getType(i2) == 1;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isNull(int i2) {
        checkPosition();
        return this.mWindow.getType(this.mPos, i2) == 0;
    }

    @Deprecated
    public boolean isString(int i2) {
        return getType(i2) == 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tencent.wcdb.AbstractCursor
    public void onDeactivateOrClose() {
        super.onDeactivateOrClose();
        closeWindow();
    }

    public void setWindow(CursorWindow cursorWindow) {
        if (cursorWindow != this.mWindow) {
            closeWindow();
            this.mWindow = cursorWindow;
        }
    }
}
