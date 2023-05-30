package com.tencent.wcdb;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class CursorWrapper implements Cursor {
    protected final Cursor mCursor;

    public CursorWrapper(Cursor cursor) {
        this.mCursor = cursor;
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mCursor.close();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void copyStringToBuffer(int i2, CharArrayBuffer charArrayBuffer) {
        this.mCursor.copyStringToBuffer(i2, charArrayBuffer);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void deactivate() {
        this.mCursor.deactivate();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public byte[] getBlob(int i2) {
        return this.mCursor.getBlob(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getColumnCount() {
        return this.mCursor.getColumnCount();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getColumnIndex(String str) {
        return this.mCursor.getColumnIndex(str);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        return this.mCursor.getColumnIndexOrThrow(str);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public String getColumnName(int i2) {
        return this.mCursor.getColumnName(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mCursor.getColumnNames();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getCount() {
        return this.mCursor.getCount();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public double getDouble(int i2) {
        return this.mCursor.getDouble(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle getExtras() {
        return this.mCursor.getExtras();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public float getFloat(int i2) {
        return this.mCursor.getFloat(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getInt(int i2) {
        return this.mCursor.getInt(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public long getLong(int i2) {
        return this.mCursor.getLong(i2);
    }

    @Override // android.database.Cursor
    public Uri getNotificationUri() {
        return null;
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getPosition() {
        return this.mCursor.getPosition();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public short getShort(int i2) {
        return this.mCursor.getShort(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public String getString(int i2) {
        return this.mCursor.getString(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public int getType(int i2) {
        return this.mCursor.getType(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean getWantsAllOnMoveCalls() {
        return this.mCursor.getWantsAllOnMoveCalls();
    }

    public Cursor getWrappedCursor() {
        return this.mCursor;
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isAfterLast() {
        return this.mCursor.isAfterLast();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isBeforeFirst() {
        return this.mCursor.isBeforeFirst();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isClosed() {
        return this.mCursor.isClosed();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isFirst() {
        return this.mCursor.isFirst();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isLast() {
        return this.mCursor.isLast();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean isNull(int i2) {
        return this.mCursor.isNull(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean move(int i2) {
        return this.mCursor.move(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean moveToFirst() {
        return this.mCursor.moveToFirst();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean moveToLast() {
        return this.mCursor.moveToLast();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean moveToNext() {
        return this.mCursor.moveToNext();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean moveToPosition(int i2) {
        return this.mCursor.moveToPosition(i2);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean moveToPrevious() {
        return this.mCursor.moveToPrevious();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
        this.mCursor.registerContentObserver(contentObserver);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mCursor.registerDataSetObserver(dataSetObserver);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean requery() {
        return this.mCursor.requery();
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle respond(Bundle bundle) {
        return this.mCursor.respond(bundle);
    }

    @Override // android.database.Cursor
    public void setExtras(Bundle bundle) {
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        this.mCursor.setNotificationUri(contentResolver, uri);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
        this.mCursor.unregisterContentObserver(contentObserver);
    }

    @Override // com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mCursor.unregisterDataSetObserver(dataSetObserver);
    }
}
