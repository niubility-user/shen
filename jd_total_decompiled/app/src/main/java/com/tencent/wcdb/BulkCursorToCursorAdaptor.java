package com.tencent.wcdb;

import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.RemoteException;
import com.tencent.wcdb.AbstractCursor;
import com.tencent.wcdb.support.Log;

/* loaded from: classes9.dex */
public final class BulkCursorToCursorAdaptor extends AbstractWindowedCursor {
    private static final String TAG = "BulkCursor";
    private IBulkCursor mBulkCursor;
    private String[] mColumns;
    private int mCount;
    private AbstractCursor.SelfContentObserver mObserverBridge = new AbstractCursor.SelfContentObserver(this);
    private boolean mWantsAllOnMoveCalls;

    private void throwIfCursorIsClosed() {
        if (this.mBulkCursor == null) {
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        IBulkCursor iBulkCursor = this.mBulkCursor;
        if (iBulkCursor != null) {
            try {
                try {
                    iBulkCursor.close();
                } catch (RemoteException unused) {
                    Log.w(TAG, "Remote process exception when closing");
                }
            } finally {
                this.mBulkCursor = null;
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractWindowedCursor, com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void copyStringToBuffer(int i2, CharArrayBuffer charArrayBuffer) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void deactivate() {
        super.deactivate();
        IBulkCursor iBulkCursor = this.mBulkCursor;
        if (iBulkCursor != null) {
            try {
                iBulkCursor.deactivate();
            } catch (RemoteException unused) {
                Log.w(TAG, "Remote process exception when deactivating");
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public String[] getColumnNames() {
        throwIfCursorIsClosed();
        return this.mColumns;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getCount() {
        throwIfCursorIsClosed();
        return this.mCount;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle getExtras() {
        throwIfCursorIsClosed();
        try {
            return this.mBulkCursor.getExtras();
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    public IContentObserver getObserver() {
        try {
            return (IContentObserver) this.mObserverBridge.getClass().getMethod("getContentObserver", new Class[0]).invoke(this.mObserverBridge, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public void initialize(BulkCursorDescriptor bulkCursorDescriptor) {
        this.mBulkCursor = bulkCursorDescriptor.cursor;
        String[] strArr = bulkCursorDescriptor.columnNames;
        this.mColumns = strArr;
        this.mRowIdColumnIndex = DatabaseUtils.findRowIdColumnIndex(strArr);
        this.mWantsAllOnMoveCalls = bulkCursorDescriptor.wantsAllOnMoveCalls;
        this.mCount = bulkCursorDescriptor.count;
        CursorWindow cursorWindow = bulkCursorDescriptor.window;
        if (cursorWindow != null) {
            setWindow(cursorWindow);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[RETURN] */
    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.CrossProcessCursor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onMove(int i2, int i3) {
        throwIfCursorIsClosed();
        try {
            CursorWindow cursorWindow = this.mWindow;
            if (cursorWindow != null && i3 >= cursorWindow.getStartPosition() && i3 < this.mWindow.getStartPosition() + this.mWindow.getNumRows()) {
                if (this.mWantsAllOnMoveCalls) {
                    this.mBulkCursor.onMove(i3);
                }
                return this.mWindow == null;
            }
            setWindow(this.mBulkCursor.getWindow(i3));
            if (this.mWindow == null) {
            }
        } catch (RemoteException unused) {
            Log.e(TAG, "Unable to get window because the remote process is dead");
            return false;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean requery() {
        throwIfCursorIsClosed();
        try {
            int requery = this.mBulkCursor.requery(getObserver());
            this.mCount = requery;
            if (requery != -1) {
                this.mPos = -1;
                closeWindow();
                super.requery();
                return true;
            }
            deactivate();
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Unable to requery because the remote process exception " + e2.getMessage());
            deactivate();
            return false;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle respond(Bundle bundle) {
        throwIfCursorIsClosed();
        try {
            return this.mBulkCursor.respond(bundle);
        } catch (RemoteException e2) {
            Log.w(TAG, "respond() threw RemoteException, returning an empty bundle.", e2);
            return Bundle.EMPTY;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }
}
